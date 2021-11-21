package com.iot.action;

import com.iot.bean.Etrucktransportcompany;
import com.iot.bean.Etrucktransportcompanyv;
import com.iot.bean.Rmap;
import com.iot.bean.Select;
import com.iot.service.EtrucktransportcompanyService;
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
@RequestMapping("/trucktransportcompany")
public class EtrucktransportcompanyAction extends BaseController{
    @Resource
    private EtrucktransportcompanyService etrucktransportcompanyService;

    /**
     * @Auther: Barry
     * @Date: 2020/3/25
     * @Param:
     * @Description: 查询所有
     */
    @AuthToken
    @RequestMapping(value = "/selectAll", method = RequestMethod.GET)
    public Rmap<Etrucktransportcompany> selectAll(){
        Rmap<Etrucktransportcompany> map = etrucktransportcompanyService.selectAll();
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
     * @Param: number
     * @Param: name
     * @Param: phone
     * @Param: begintime
     * @Param: endtime
     * @Description: 分页查询车次
     */
    @AuthToken
    @RequestMapping(value = "/selectByPage", method = RequestMethod.GET)
    public Rmap<Etrucktransportcompany> selectByPage(Integer page, Integer rows, Integer truckid, Integer submituserid, Integer confirmuserid, Integer transportcompanyid, String status, Date begintime, Date endtime){
        Rmap<Etrucktransportcompany> map = new Rmap<Etrucktransportcompany>();
        StringBuilder sql = new StringBuilder();
        if(null != transportcompanyid){
            sql.append("transportcompanyid = " + transportcompanyid);
        }
        if (null != truckid) {
            if(sql.length() == 0){
                sql.append("truckid = ").append(truckid);
            }else{
                sql.append(" and truckid = ").append(truckid);
            }
        }
        if (null != submituserid) {
            if(sql.length() == 0){
                sql.append("submituserid = ").append(submituserid);
            }else{
                sql.append(" and submituserid = ").append(submituserid);
            }
        }
        if (null != confirmuserid) {
            if(sql.length() == 0){
                sql.append("confirmuserid = ").append(confirmuserid);
            }else{
                sql.append(" and confirmuserid = ").append(confirmuserid);
            }
        }
        if (null != transportcompanyid) {
            if(sql.length() == 0){
                sql.append("transportcompanyid = ").append(transportcompanyid);
            }else{
                sql.append(" and transportcompanyid = ").append(transportcompanyid);
            }
        }
        if (StringUtils.isNotBlank(status)) {
            if(sql.length() == 0){
                sql.append("status = '").append(status).append("'");
            }else{
                sql.append(" and status = '").append(status).append("'");
            }
        }
        if (null != begintime && null != endtime) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if(sql.length() == 0){
                sql.append("time >= '").append(format.format(begintime)).append("' and time <= '").append(format.format(endtime)).append("'");
            }else{
                sql.append(" and time >= '").append(format.format(begintime)).append("' and time <= '").append(format.format(endtime)).append("'");
            }
        }
        map = etrucktransportcompanyService.selectByPage(page, rows, "id", "desc", sql.toString());
        if (null != map.getList()) {
            map.setStatus(260);
        } else {
            map.setStatus(240);
        }
        return map;
    }

    /**
     * @Auther: Barry
     * @Date: 2020/4/17
     * @Param: truckid
     * @Description: 根据车次查询
     */
    @AuthToken
    @RequestMapping(value = "/selectByTruckid", method = RequestMethod.GET)
    public  Rmap<Etrucktransportcompanyv> selectByTruckid(Integer truckid){
        Rmap<Etrucktransportcompanyv> map = etrucktransportcompanyService.selectVBySql("truckid = "+truckid);
        if(null != map.getList()){
            map.setStatus(260);
        }else{
            map.setStatus(240);
        }
        return map;
    }

    /**
     * @Auther: Barry
     * @Date: 2020/4/8
     * @Param: page
     * @Param: rows
     * @Param: truckid
     * @Param: submituserid
     * @Param: confirmuserid
     * @Param: status
     * @Param: begintime
     * @Param: endtime
     * @Description: v查询
     */
    @AuthToken
    @RequestMapping(value = "/selectVByPage", method = RequestMethod.GET)
    public Rmap<Etrucktransportcompanyv> selectVByPage(Integer page, Integer rows, Integer truckid, Integer submituserid, Integer confirmuserid, Integer transportcompanyid, String status, Date begintime, Date endtime){
        Rmap<Etrucktransportcompanyv> map = new Rmap<Etrucktransportcompanyv>();
        StringBuilder sql = new StringBuilder();
        if(null != transportcompanyid){
            sql.append("transportcompanyid = " + transportcompanyid);
        }
        if (null != truckid) {
            if(sql.length() == 0){
                sql.append("truckid = ").append(truckid);
            }else{
                sql.append(" and truckid = ").append(truckid);
            }
        }
        if (null != submituserid) {
            if(sql.length() == 0){
                sql.append("submituserid = ").append(submituserid);
            }else{
                sql.append(" and submituserid = ").append(submituserid);
            }
        }
        if (null != confirmuserid) {
            if(sql.length() == 0){
                sql.append("confirmuserid = ").append(confirmuserid);
            }else{
                sql.append(" and confirmuserid = ").append(confirmuserid);
            }
        }
        if (null != transportcompanyid) {
            if(sql.length() == 0){
                sql.append("transportcompanyid = ").append(transportcompanyid);
            }else{
                sql.append(" and transportcompanyid = ").append(transportcompanyid);
            }
        }
        if (StringUtils.isNotBlank(status)) {
            if(sql.length() == 0){
                sql.append("status = '").append(status).append("'");
            }else{
                sql.append(" and status = '").append(status).append("'");
            }
        }
        if (null != begintime && null != endtime) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if(sql.length() == 0){
                sql.append("time >= '").append(format.format(begintime)).append("' and time <= '").append(format.format(endtime)).append("'");
            }else{
                sql.append(" and time >= '").append(format.format(begintime)).append("' and time <= '").append(format.format(endtime)).append("'");
            }
        }
        map = etrucktransportcompanyService.selectVByPage(page, rows, "id", "desc", sql.toString());
        if (null != map.getList()) {
            map.setStatus(260);
        } else {
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
    public Rmap<Etrucktransportcompany> selectByPrimaryKey(Integer id){
        Rmap<Etrucktransportcompany> map = etrucktransportcompanyService.selectByPrimaryKey(id);
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
     * @Description: 创建车次
     */
    @AuthToken
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public Rmap<Etrucktransportcompany> insert(Etrucktransportcompany record) {
        Rmap<Etrucktransportcompany> map = new Rmap<Etrucktransportcompany>();
        Rmap<Etrucktransportcompany> rmap = etrucktransportcompanyService.insert(record);
        if (null != rmap.getObject()) {
            map.setStatus(260);
        } else {
            map.setStatus(240);
            if (null == rmap.getMessage()) {
                map.setMessage("添加车次处理厂审核失败");
            } else {
                map.setMessage(rmap.getMessage());
            }
        }
        return map;
    }

    /**
     * @Auther: Barry
     * @Date: 2020/3/25
     * @Param: record
     * @Description: 更新车次
     */
    @AuthToken
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public Rmap<Etrucktransportcompany> update(Etrucktransportcompany record) {
        Rmap<Etrucktransportcompany> map = new Rmap<Etrucktransportcompany>();
        Rmap<Etrucktransportcompany> rmap = etrucktransportcompanyService.update(record);
        if (null != rmap.getObject()) {
            map.setStatus(260);
        } else {
            map.setStatus(240);
            if (null == rmap.getMessage()) {
                map.setMessage("修改车次处理厂审核失败");
            } else {
                map.setMessage(rmap.getMessage());
            }
        }
        return map;
    }

    /**
     * @Auther: Barry
     * @Date: 2020/3/10
     * @Param: id
     * @Description: 删除
     */
    @AuthToken
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public Rmap<Etrucktransportcompany> delete(Integer id) {
        Rmap<Etrucktransportcompany> map = etrucktransportcompanyService.delete(id);
        if(null != map.getObject()){
            map.setStatus(260);
        }else {
            map.setStatus(240);
            if(null == map.getMessage()) {
                map.setMessage("删除车次处理厂审核失败");
            }
        }
        return map;
    }

    /**
     * @Auther: Barry
     * @Date: 2020/3/10
     * @Param: ids
     * @Description: 批量删除
     */
    @AuthToken
    @RequestMapping(value = "/deletes", method = RequestMethod.DELETE)
    public Rmap<Etrucktransportcompany> deletes(@RequestBody Integer[] ids){
        String strIds =  ArrayUtils.toString(ids, ",").replace('{', '(').replace('}', ')');
        Rmap<Etrucktransportcompany> map = etrucktransportcompanyService.deletes(strIds);
        if(null != map.getList()){
            map.setStatus(260);
        }else{
            map.setStatus(240);
            if(null == map.getMessage()){
                map.setMessage("删除车次处理厂审核失败");
            }
        }
        return map;
    }

    /**
     * @Auther: Barry
     * @Date: 2020/4/17
     * @Param:
     * @Description: 类型列表
     */
    @AuthToken
    @RequestMapping(value = "/statusList", method = RequestMethod.GET)
    public Rmap<Select> statusList(){
        Rmap<Select> map = new Rmap<Select>();
        List<Select> list = new ArrayList<Select>();
        Select select = new Select();
        select.setText("待审核");
        select.setValue("待审核");
        list.add(select);
        select = new Select();
        select.setText("已审核");
        select.setValue("已审核");
        list.add(select);
        select = new Select();
        select.setText("审核不通过");
        select.setValue("审核不通过");
        list.add(select);
        map.setStatus(260);
        map.setList(list);
        return map;
    }

}