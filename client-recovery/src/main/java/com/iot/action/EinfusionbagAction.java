package com.iot.action;

import com.iot.bean.Einfusionbag;
import com.iot.bean.Einfusionbagv;
import com.iot.bean.Rmap;
import com.iot.bean.Select;
import com.iot.service.EinfusionbagService;
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
@RequestMapping("/infusionbag")
public class EinfusionbagAction extends BaseController{
    @Resource
    private EinfusionbagService einfusionbagService;

    /**
     * @Auther: Barry
     * @Date: 2020/3/25
     * @Param:
     * @Description: 查询所有
     */
    @AuthToken
    @RequestMapping(value = "/selectAll", method = RequestMethod.GET)
    public Rmap<Einfusionbag> selectAll(){
        Rmap<Einfusionbag> map = einfusionbagService.selectAll();
        if(null != map.getList()){
            map.setStatus(260);
        }else{
            map.setStatus(240);
        }
        return map;
    }

    /**
     * @Auther: Barry
     * @Date: 2020/3/25
     * @Param: page
     * @Param: rows
     * @Param: barcode
     * @Param: name
     * @Param: garbagebagid
     * @Param: status
     * @Param: begintime
     * @Param: endtime
     * @Description: 分页查询
     */
    @AuthToken
    @RequestMapping(value = "/selectByPage", method = RequestMethod.GET)
    public Rmap<Einfusionbag> selectByPage(Integer page, Integer rows, String barcode, String name, Integer garbagebagid, String status, Date begintime, Date endtime){
        StringBuilder sql =new StringBuilder();
        if(StringUtils.isNotBlank(barcode)){
            sql.append("barcode like ").append("'%25").append(barcode).append("%25'");
        }
        if(StringUtils.isNotBlank(name)){
            if(sql.length() == 0){
                sql.append("name like ").append("'%25").append(name).append("%25'");
            }else{
                sql.append(" and name like ").append("'%25").append(name).append("%25'");
            }
        }
        if(null != garbagebagid){
            if(sql.length() == 0){
                sql.append("garbagebagid = ").append(garbagebagid);
            }else{
                sql.append(" and garbagebagid = ").append(garbagebagid);
            }
        }
        if(StringUtils.isNotBlank(status)){
            if(sql.length() == 0){
                sql.append("status = '").append(status).append("'");
            }else{
                sql.append(" and status = '").append(status).append("'");
            }
        }
        if(null != begintime && null != endtime){
            SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if(sql.length() == 0){
                sql.append("time >= '").append(format.format(begintime)).append("' and time <= '").append(format.format(endtime)).append("'");
            }else{
                sql.append(" and time >= '").append(format.format(begintime)).append("' and time <= '").append(format.format(endtime)).append("'");
            }
        }
        Rmap<Einfusionbag> map = einfusionbagService.selectByPage(page, rows, "id", "desc", sql.toString());
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
     * @Param: page
     * @Param: rows
     * @Param: barcode
     * @Param: name
     * @Param: garbagebagid
     * @Param: status
     * @Param: begintime
     * @Param: endtime
     * @Description: v分页查询
     */
    @AuthToken
    @RequestMapping(value = "/selectVByPage", method = RequestMethod.GET)
    public Rmap<Einfusionbagv> selectVByPage(Integer page, Integer rows, String barcode, String name, Integer garbagebagid, String status, Date begintime, Date endtime){
        StringBuilder sql =new StringBuilder();
        if(StringUtils.isNotBlank(barcode)){
            sql.append("barcode like ").append("'%25").append(barcode).append("%25'");
        }
        if(StringUtils.isNotBlank(name)){
            if(sql.length() == 0){
                sql.append("name like ").append("'%25").append(name).append("%25'");
            }else{
                sql.append(" and name like ").append("'%25").append(name).append("%25'");
            }
        }
        if(null != garbagebagid){
            if(sql.length() == 0){
                sql.append("garbagebagid = ").append(garbagebagid);
            }else{
                sql.append(" and garbagebagid = ").append(garbagebagid);
            }
        }
        if(StringUtils.isNotBlank(status)){
            if(sql.length() == 0){
                sql.append("status = '").append(status).append("'");
            }else{
                sql.append(" and status = '").append(status).append("'");
            }
        }
        if(null != begintime && null != endtime){
            SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if(sql.length() == 0){
                sql.append("time >= '").append(format.format(begintime)).append("' and time <= '").append(format.format(endtime)).append("'");
            }else{
                sql.append(" and time >= '").append(format.format(begintime)).append("' and time <= '").append(format.format(endtime)).append("'");
            }
        }
        Rmap<Einfusionbagv> map = einfusionbagService.selectVByPage(page, rows, "id", "desc", sql.toString());
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
    public Rmap<Einfusionbag> selectByPrimaryKey(Integer id){
        Rmap<Einfusionbag> map = einfusionbagService.selectByPrimaryKey(id);
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
    public Rmap<Einfusionbag> insert(Einfusionbag record) {
        Rmap<Einfusionbag> map = new Rmap<Einfusionbag>();
        if(isValidate(record.getBarcode(), 0)){
            Rmap<Einfusionbag> rmap = einfusionbagService.insert(record);
            if(null != rmap.getObject()){
                map.setStatus(260);
            }else{
                map.setStatus(240);
                if(null == rmap.getMessage()){
                    map.setMessage("添加输液袋失败");
                }else{
                    map.setMessage(rmap.getMessage());
                }
            }
        }else{
            map.setStatus(240);
            map.setMessage("该输液袋已存在");
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
    public Rmap<Einfusionbag> update(Einfusionbag record) {
        Rmap<Einfusionbag> map = new Rmap<Einfusionbag>();
        if(isValidate(record.getBarcode(), record.getId())){
            Rmap<Einfusionbag> rmap = einfusionbagService.update(record);
            if(null != rmap.getObject()){
                map.setStatus(260);
            }else{
                map.setStatus(240);
                if(null == rmap.getMessage()){
                    map.setMessage("修改输液袋失败");
                }else{
                    map.setMessage(rmap.getMessage());
                }
            }
        }else{
            map.setStatus(240);
            map.setMessage("该输液袋已存在");
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
    public Rmap<Einfusionbag> delete(Integer id) {
        Rmap<Einfusionbag> map = einfusionbagService.delete(id);
        if(null != map.getObject()){
            map.setStatus(260);
        }else {
            map.setStatus(240);
            if(null == map.getMessage()) {
                map.setMessage("删除输液袋失败");
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
    public Rmap<Einfusionbag> deletes(@RequestBody Integer[] ids){
        String strIds =  ArrayUtils.toString(ids, ",").replace('{', '(').replace('}', ')');
        Rmap<Einfusionbag> map = einfusionbagService.deletes(strIds);
        if(null != map.getList()){
            map.setStatus(260);
        }else{
            map.setStatus(240);
            if(null == map.getMessage()){
                map.setMessage("删除输液袋失败");
            }
        }
        return map;
    }

    @AuthToken
    @RequestMapping(value = "/statusList", method = RequestMethod.GET)
    public Rmap<Select> statusList(){
        Rmap<Select> map = new Rmap<Select>();
        List<Select> list = new ArrayList<Select>();
        Select select = new Select();
        select.setText("科室待用");
        select.setValue("科室待用");
        list.add(select);
        select = new Select();
        select.setText("使用中");
        select.setValue("使用中");
        list.add(select);
        select = new Select();
        select.setText("废弃");
        select.setValue("废弃");
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
    public boolean isValidate(String barcode, Integer id){
        Rmap<Einfusionbag> map = einfusionbagService.selectBySql("barcode = '" + barcode +"' and id != " + id);
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