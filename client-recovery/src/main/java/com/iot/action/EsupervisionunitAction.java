package com.iot.action;

import com.iot.bean.*;
import com.iot.service.EhospitalService;
import com.iot.service.EsupervisionunitService;
import com.iot.util.AuthToken;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/supervisionunit")
public class EsupervisionunitAction extends BaseController{
    @Resource
    private EsupervisionunitService esupervisionunitService;
    @Resource
    private EhospitalService ehospitalService;

    /*
     * @Date 2020/8/5 10:03
     * @Description 根据pid查询
     * @Param
     * @Return
     */
    @AuthToken
    @RequestMapping(value = "/selectByPid", method = RequestMethod.GET)
    public Rmap<Esupervisionunit> selectByPid(Integer pid){
        Rmap<Esupervisionunit> map = new Rmap<Esupervisionunit>();
        if(null == pid){
            map.setStatus(240);
            map.setMessage("参数不全");
            return map;
        }
        map = esupervisionunitService.selectBySql("pid = "+pid);
        if(null != map.getList()){
            map.setStatus(260);
        }else{
            map.setStatus(240);
        }
        return map;
    }

    /**
     * @Auther: Barry
     * @Date: 2020/6/8
     * @Param: name
     * @Description: 名字模糊查询监管单位
     */
    @AuthToken
    @RequestMapping(value = "/selectByName", method = RequestMethod.GET)
    public Rmap<Esupervisionunit> selectByName(String name){
        Rmap<Esupervisionunit> map = new Rmap<Esupervisionunit>();
        if(StringUtils.isBlank(name)){
            map.setStatus(240);
            map.setMessage("参数不全");
            return map;
        }
        StringBuilder sql = new StringBuilder();
        sql.append("name like ").append("'%25").append(name).append("%25'");
        map = esupervisionunitService.selectBySql(sql.toString());
        if(null != map.getList()){
            map.setStatus(260);
        }else{
            map.setStatus(240);
        }
        return map;
    }

    /**
     * @Auther: Barry
     * @Date: 2020/6/5
     * @Param: hospitalid 医院id
     * @Description: 查询医院监管单位
     */
    @AuthToken
    @RequestMapping(value = "/selectByHospitalId", method = RequestMethod.GET)
    public Rmap<Esupervisionunit> selectByHospitalId(Integer hospitalid){
        Rmap<Esupervisionunit> map = new  Rmap<Esupervisionunit>();
        if(null == hospitalid){
            map.setStatus(240);
            map.setMessage("参数不全");
            return map;
        }
        Rmap<Ehospital> hospitalMap = ehospitalService.selectByPrimaryKey(hospitalid);
        if(null != hospitalMap.getObject()){

        }else{
            map.setStatus(240);
            if(null == hospitalMap.getMessage()){
                map.setMessage("未查询到医院信息");
            }else{
                map.setMessage(hospitalMap.getMessage());
            }
        }
        map = esupervisionunitService.selectByPrimaryKey(hospitalMap.getObject().getSupervisionunitid());
        if(null != map.getObject()){
            map.setStatus(260);
        }else{
            map.setStatus(240);
        }
        return map;
    }

    /**
     * @Auther: Barry
     * @Date: 2020/3/25
     * @Param:
     * @Description: 查询所有
     */
    @AuthToken
    @RequestMapping(value = "/selectAll", method = RequestMethod.GET)
    public Rmap<Esupervisionunit> selectAll(){
        Rmap<Esupervisionunit> map = esupervisionunitService.selectAll();
        if(null != map.getList()){
            map.setStatus(260);
        }else{
            map.setStatus(240);
        }
        return map;
    }

    /**
     * @Auther: Barry
     * @Date: 2020/4/7
     * @Param: page
     * @Param: rows
     * @Param: name
     * @Param: address
     * @Param: level
     * @Param: pid 必传
     * @Description: 查询
     */
    @AuthToken
    @RequestMapping(value = "/selectByPage", method = RequestMethod.GET)
    public Rmap<Esupervisionunit> selectByPage(Integer page, Integer rows, String name, String address, String level, Integer pid){
        StringBuilder sql = new StringBuilder();
        sql.append("pid = ").append(pid);
        if (StringUtils.isNotBlank(name)) {
            sql.append(" and name like ").append("'%25").append(name).append("%25'");
        }
        if (StringUtils.isNotBlank(address)) {
            sql.append(" and address like ").append("'%25").append(address).append("%25'");
        }
        if (StringUtils.isNotBlank(level)) {
            sql.append(" and level = '").append(level).append("'");
        }
        Rmap<Esupervisionunit> map = esupervisionunitService.selectByPage(page, rows, "id", "desc", sql.toString());
        if (null != map.getList()) {
            map.setStatus(260);
        } else {
            map.setStatus(240);
        }
        return map;
    }

    /**
     * @Auther: Barry
     * @Date: 2020/4/7
     * @Param: page
     * @Param: rows
     * @Param: name
     * @Param: address
     * @Param: level
     * @Param: pid
     * @Description: 查询
     */
    @AuthToken
    @RequestMapping(value = "/selectVByPage", method = RequestMethod.GET)
    public Rmap<Esupervisionunitv> selectVByPage(Integer page, Integer rows, String name, String address, String level, Integer pid){
        StringBuilder sql =new StringBuilder();
        sql.append("pid = ").append(pid);
        if (StringUtils.isNotBlank(name)) {
            sql.append(" and name like ").append("'%25").append(name).append("%25'");
        }
        if (StringUtils.isNotBlank(address)) {
            sql.append(" and address like ").append("'%25").append(address).append("%25'");
        }
        if (StringUtils.isNotBlank(level)) {
            sql.append(" and level = '").append(level).append("'");
        }
        Rmap<Esupervisionunitv> map = esupervisionunitService.selectVByPage(page, rows, "id", "desc", sql.toString());
        if (null != map.getList()) {
            map.setStatus(260);
        }else{
            map.setStatus(240);
        }
        return map;
    }

    /**
     * @Auther: Barry
     * @Date: 2020/3/25
     * @Param: id
     * @Description: 主键查询
     */
    @AuthToken
    @RequestMapping(value = "/selectByPrimaryKey", method = RequestMethod.GET)
    public Rmap<Esupervisionunit> selectByPrimaryKey(Integer id){
        Rmap<Esupervisionunit> map = esupervisionunitService.selectByPrimaryKey(id);
        if(null != map.getObject()){
            map.setStatus(260);
        }else{
            map.setStatus(240);
        }
        return map;
    }

    /**
     * @Auther: Barry
     * @Date: 2020/3/25
     * @Param: record
     * @Description: 添加
     */
    @AuthToken
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public Rmap<Esupervisionunit> insert(Esupervisionunit record) {
        Rmap<Esupervisionunit> map = new Rmap<Esupervisionunit>();
        if(isValidate(record.getName(), 0)){
            Rmap<Esupervisionunit> rmap = esupervisionunitService.insert(record);
            if(null != rmap.getObject()){
                map.setStatus(260);
            }else{
                map.setStatus(240);
                if(null == rmap.getMessage()){
                    map.setMessage("添加监管单位失败");
                }else{
                    map.setMessage(rmap.getMessage());
                }
            }
        }else{
            map.setStatus(240);
            map.setMessage("该监管单位已存在");
        }
        return map;
    }

    /**
     * @Auther: Barry
     * @Date: 2020/3/25
     * @Param: record
     * @Description: 更新
     */
    @AuthToken
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public Rmap<Esupervisionunit> update(Esupervisionunit record) {
        Rmap<Esupervisionunit> map = new Rmap<Esupervisionunit>();
        if(isValidate(record.getName(), record.getId())){
            Rmap<Esupervisionunit> rmap = esupervisionunitService.update(record);
            if(null != rmap.getObject()){
                map.setStatus(260);
            }else{
                map.setStatus(240);
                if(null == rmap.getMessage()){
                    map.setMessage("修改监管单位失败");
                }else{
                    map.setMessage(rmap.getMessage());
                }
            }
        }else{
            map.setStatus(240);
            map.setMessage("该监管单位已存在");
        }
        return map;
    }

    /**
     * @Auther: Barry
     * @Date: 2020/3/25
     * @Param: id
     * @Description: 删除
     */
    @AuthToken
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public Rmap<Esupervisionunit> delete(Integer id) {
        Rmap<Esupervisionunit> map = esupervisionunitService.delete(id);
        if(null != map.getObject()){
            map.setStatus(260);
        }else {
            map.setStatus(240);
            if(null == map.getMessage()) {
                map.setMessage("删除监管单位失败");
            }
        }
        return map;
    }

    /**
     * @Auther: Barry
     * @Date: 2020/3/25
     * @Param: ids
     * @Description: 批量删除
     */
    @AuthToken
    @RequestMapping(value = "/deletes", method = RequestMethod.DELETE)
    public Rmap<Esupervisionunit> deletes(@RequestBody Integer[] ids){
        String strIds =  ArrayUtils.toString(ids, ",").replace('{', '(').replace('}', ')');
        Rmap<Esupervisionunit> map = esupervisionunitService.deletes(strIds);
        if(null != map.getList()){
            map.setStatus(260);
        }else{
            map.setStatus(240);
            if(null == map.getMessage()){
                map.setMessage("删除监管单位失败");
            }
        }
        return map;
    }

    /**
     * @Auther: Barry
     * @Date: 2020/4/7
     * @Param:
     * @Description: 监管单位级别列表
     */
    @AuthToken
    @RequestMapping(value = "/levelList", method = RequestMethod.GET)
    public Rmap<Select> levelList(){
        Rmap<Select> map = new Rmap<Select>();
        List<Select> list = new ArrayList<Select>();
        Select select = new Select();
        select.setText("全国");
        select.setValue("全国");
        list.add(select);
        select = new Select();
        select.setText("省");
        select.setValue("省");
        list.add(select);
        select = new Select();
        select.setText("市");
        select.setValue("市");
        list.add(select);
        select = new Select();
        select.setText("区");
        select.setValue("区");
        list.add(select);
        map.setStatus(260);
        map.setList(list);
        return map;
    }

    /**
     * @Auther: Barry
     * @Date: 2020/3/25
     * @Param: barcode
     * @Param: id
     * @Description: 唯一性
     */
    public boolean isValidate(String name, Integer id){
        Rmap<Esupervisionunit> map = esupervisionunitService.selectBySql("name = '" + name +"' and id != " + id);
        if(null != map.getList()){
            return false;
        }else {
            if(null == map.getMessage()){
                return true;
            }else{
                return false;
            }
        }
    }
}