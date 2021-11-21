package com.iot.action;

import com.iot.bean.*;
import com.iot.service.EtrucktreatmentplantreceiveService;
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
@RequestMapping("/trucktreatmentplantreceive")
public class EtrucktreatmentplantreceiveAction extends BaseController{
    @Resource
    private EtrucktreatmentplantreceiveService etrucktreatmentplantreceiveService;

    /**
     * @Auther: Barry
     * @Date: 2020/3/25
     * @Param:
     * @Description: 查询所有
     */
    @AuthToken
    @RequestMapping(value = "/selectAll", method = RequestMethod.GET)
    public Rmap<Etrucktreatmentplantreceive> selectAll(){
        Rmap<Etrucktreatmentplantreceive> map = etrucktreatmentplantreceiveService.selectAll();
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
    public Rmap<Etrucktreatmentplantreceive> selectByPage(Integer page, Integer rows, Integer truckid, Integer submituserid, Integer confirmuserid, Integer treatmentplantid, String status, Date begintime, Date endtime){
        Rmap<Etrucktreatmentplantreceive> map = new Rmap<Etrucktreatmentplantreceive>();
        StringBuilder sql = new StringBuilder();
        if(null != treatmentplantid){
            sql.append("treatmentplantid = " + treatmentplantid);
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
        if (null != treatmentplantid) {
            if(sql.length() == 0){
                sql.append("treatmentplantid = ").append(treatmentplantid);
            }else{
                sql.append(" and treatmentplantid = ").append(treatmentplantid);
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
        map = etrucktreatmentplantreceiveService.selectByPage(page, rows, "id", "desc", sql.toString());
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
    public  Rmap<Etrucktreatmentplantreceivev> selectByTruckid(Integer truckid){
        Rmap<Etrucktreatmentplantreceivev> map = etrucktreatmentplantreceiveService.selectVBySql("truckid = "+truckid);
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
    public Rmap<Etrucktreatmentplantreceivev> selectVByPage(Integer page, Integer rows, Integer truckid, Integer submituserid, Integer confirmuserid, Integer treatmentplantid, String status, Date begintime, Date endtime){
        Rmap<Etrucktreatmentplantreceivev> map = new Rmap<Etrucktreatmentplantreceivev>();
        StringBuilder sql = new StringBuilder();
        if(null != treatmentplantid){
            sql.append("treatmentplantid = " + treatmentplantid);
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
        if (null != treatmentplantid) {
            if(sql.length() == 0){
                sql.append("treatmentplantid = ").append(treatmentplantid);
            }else{
                sql.append(" and treatmentplantid = ").append(treatmentplantid);
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
        map = etrucktreatmentplantreceiveService.selectVByPage(page, rows, "id", "desc", sql.toString());
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
    public Rmap<Etrucktreatmentplantreceive> selectByPrimaryKey(Integer id){
        Rmap<Etrucktreatmentplantreceive> map = etrucktreatmentplantreceiveService.selectByPrimaryKey(id);
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
    public Rmap<Etrucktreatmentplantreceive> insert(Etrucktreatmentplantreceive record) {
        Rmap<Etrucktreatmentplantreceive> map = new Rmap<Etrucktreatmentplantreceive>();
        Rmap<Etrucktreatmentplantreceive> rmap = etrucktreatmentplantreceiveService.insert(record);
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
    public Rmap<Etrucktreatmentplantreceive> update(Etrucktreatmentplantreceive record) {
        Rmap<Etrucktreatmentplantreceive> map = new Rmap<Etrucktreatmentplantreceive>();
        Rmap<Etrucktreatmentplantreceive> rmap = etrucktreatmentplantreceiveService.update(record);
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
    public Rmap<Etrucktreatmentplantreceive> delete(Integer id) {
        Rmap<Etrucktreatmentplantreceive> map = etrucktreatmentplantreceiveService.delete(id);
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
    public Rmap<Etrucktreatmentplantreceive> deletes(@RequestBody Integer[] ids){
        String strIds =  ArrayUtils.toString(ids, ",").replace('{', '(').replace('}', ')');
        Rmap<Etrucktreatmentplantreceive> map = etrucktreatmentplantreceiveService.deletes(strIds);
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
     * @Date: 2020/6/2
     * @Param: page
     * @Param: rows
     * @Param: treatmentplantid 处理厂id
     * @Param: status
     * @Param: begintime
     * @Param: endtime
     * @Description: 处理厂出入
     */
    @AuthToken
    @RequestMapping(value = "/selectOutBoundVoBySql", method = RequestMethod.GET)
    public Rmap<EtreatmentplantOutBoundVo> selectOutBoundVoBySql(Integer page, Integer rows, Integer treatmentplantid, String status, Date begintime, Date endtime){
        Rmap<EtreatmentplantOutBoundVo> map = new Rmap<EtreatmentplantOutBoundVo>();
        if(null == treatmentplantid){
            map.setStatus(240);
            map.setMessage("参数不全");
            return map;
        }
        StringBuilder sql = new StringBuilder();
        sql.append("t2.treatmentplantid = "+treatmentplantid);
        if (StringUtils.isNotBlank(status)) {
            sql.append(" and t2.status = '").append(status).append("'");
        }
        if (null != begintime && null != endtime) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            sql.append(" and t2.time >= '").append(format.format(begintime)).append("' and t2.time <= '").append(format.format(endtime)).append("'");
        }
        map = etrucktreatmentplantreceiveService.selectOutBoundVoByPage(page, rows, "t2.id", "desc", sql.toString());
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