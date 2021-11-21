package com.iot.action;

import com.iot.bean.*;
import com.iot.service.*;
import com.iot.util.AuthToken;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/truck")
public class EtruckAction  extends BaseController{

    @Resource
    private EtruckService etruckService;
    @Resource
    private EtruckhospitalService etruckhospitalService;
    @Resource
    private EtrucktransportcompanyService etrucktransportcompanyService;
    @Resource
    private EtrucktreatmentplantService etrucktreatmentplantService;
    @Resource
    private EtrucktreatmentplantreceiveService etrucktreatmentplantreceiveService;
    @Resource
    private EtruckproductionplantService etruckproductionplantService;


    /**
     * @Auther: Barry
     * @Date: 2020/3/25
     * @Param:
     * @Description: 查询所有
     */
    @AuthToken
    @RequestMapping(value = "/selectAll", method = RequestMethod.GET)
    public Rmap<Etruck> selectAll(){
        Rmap<Etruck> map = etruckService.selectAll();
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
    public Rmap<Etruck> selectByPage(Integer page, Integer rows, String number, String status,  String type, Integer transportcompanyid, Integer vehicleid,  Integer userid, Date begintime, Date endtime){
        StringBuilder sql =new StringBuilder();
        if(StringUtils.isNotBlank(number)){
            sql.append("number like ").append("'%25").append(number).append("%25'");
        }
        if(null != userid){
            if(sql.length() == 0){
                sql.append("userid = ").append(userid);
            }else{
                sql.append(" and userid = ").append(userid);
            }
        }
        if(null != vehicleid){
            if(sql.length() == 0){
                sql.append("vehicleid = ").append(vehicleid);
            }else{
                sql.append(" and vehicleid = ").append(vehicleid);
            }
        }
        if(null != transportcompanyid){
            if(sql.length() == 0){
                sql.append("transportcompanyid = ").append(transportcompanyid);
            }else{
                sql.append(" and transportcompanyid = ").append(transportcompanyid);
            }
        }
        if(StringUtils.isNotBlank(status)){
            if(sql.length() == 0){
                sql.append("status = '").append(status).append("'");
            }else{
                sql.append(" and status = '").append(status).append("'");
            }
        }
        if(StringUtils.isNotBlank(type)){
            if(sql.length() == 0){
                sql.append("type = '").append(type).append("'");
            }else{
                sql.append(" and type = '").append(type).append("'");
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
        Rmap<Etruck> map = etruckService.selectByPage(page, rows, "id", "desc", sql.toString());
        if (null != map.getList()) {
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
     * @Param: number
     * @Param: userid
     * @Param: begintime
     * @Param: endtime
     * @Description: v查询
     */
    @AuthToken
    @RequestMapping(value = "/selectVByPage", method = RequestMethod.GET)
    public Rmap<Etruckv> selectVByPage(Integer page, Integer rows, String number, String status, String type, Integer transportcompanyid, Integer vehicleid,  Integer userid, Date begintime, Date endtime){
        StringBuilder sql =new StringBuilder();
        if(StringUtils.isNotBlank(number)){
            sql.append("number like ").append("'%25").append(number).append("%25'");
        }
        if(null != userid){
            if(sql.length() == 0){
                sql.append("userid = ").append(userid);
            }else{
                sql.append(" and userid = ").append(userid);
            }
        }
        if(null != vehicleid){
            if(sql.length() == 0){
                sql.append("vehicleid = ").append(vehicleid);
            }else{
                sql.append(" and vehicleid = ").append(vehicleid);
            }
        }
        if(null != transportcompanyid){
            if(sql.length() == 0){
                sql.append("transportcompanyid = ").append(transportcompanyid);
            }else{
                sql.append(" and transportcompanyid = ").append(transportcompanyid);
            }
        }
        if(StringUtils.isNotBlank(status)){
            if(sql.length() == 0){
                sql.append("status = '").append(status).append("'");
            }else{
                sql.append(" and status = '").append(status).append("'");
            }
        }
        if(StringUtils.isNotBlank(type)){
            if(sql.length() == 0){
                sql.append("type = '").append(type).append("'");
            }else{
                sql.append(" and type = '").append(type).append("'");
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
        Rmap<Etruckv> map = etruckService.selectVByPage(page, rows, "id", "desc", sql.toString());
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
    public Rmap<Etruck> selectByPrimaryKey(Integer id){
        Rmap<Etruck> map = etruckService.selectByPrimaryKey(id);
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
    public Rmap<Etruck> insert(Etruck record) {
        record.setTime(new Date());
        Rmap<Etruck> map = etruckService.insert(record);
        if (null != map.getObject()) {
            map.setStatus(260);
        } else {
            map.setStatus(240);
            if (null == map.getMessage()) {
                map.setMessage("添加运输车次失败");
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
    public Rmap<Etruck> update(Etruck record) {
        Rmap<Etruck> map = etruckService.update(record);;
        if (null != map.getObject()) {
            map.setStatus(260);
        } else {
            map.setStatus(240);
            if (null == map.getMessage()) {
                map.setMessage("修改运输车次失败");
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
    public Rmap<Etruck> delete(Integer id) {
        Rmap<Etruck> map = etruckService.delete(id);
        if(null != map.getObject()){
            map.setStatus(260);
        }else {
            map.setStatus(240);
            if(null == map.getMessage()) {
                map.setMessage("删除车次失败");
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
    public Rmap<Etruck> deletes(@RequestBody Integer[] ids){
        String strIds =  ArrayUtils.toString(ids, ",").replace('{', '(').replace('}', ')');
        Rmap<Etruck> map = etruckService.deletes(strIds);
        if(null != map.getList()){
            map.setStatus(260);
        }else{
            map.setStatus(240);
            if(null == map.getMessage()){
                map.setMessage("删除车次失败");
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
        select.setText("未完成");
        select.setValue("未完成");
        list.add(select);
        select = new Select();
        select.setText("已完成");
        select.setValue("已完成");
        list.add(select);
        map.setStatus(260);
        map.setList(list);
        return map;
    }

    /**
     * @Auther: Barry
     * @Date: 2020/4/13
     * @Param:
     * @Description: 运输车次类型列表
     */
    @AuthToken
    @RequestMapping(value = "/typeList", method = RequestMethod.GET)
    public Rmap<Select> typeList(){
        Rmap<Select> map = new Rmap<Select>();
        List<Select> list = new ArrayList<Select>();
        Select select = new Select();
        select.setText("医疗机构-回收利用企业"); //医疗机构-回收利用企业
        select.setValue("医疗机构-回收利用企业");
        list.add(select);
        select = new Select();
        select.setText("医疗机构-回收物流"); //医疗机构-回收物流
        select.setValue("医疗机构-回收物流");
        list.add(select);
        select = new Select();
        select.setText("回收物流-回收利用企业"); //运输公司-处理厂
        select.setValue("回收物流-回收利用企业");
        list.add(select);
        select = new Select();
        select.setText("回收利用企业-再利用企业"); //运输公司-生产厂
        select.setValue("回收利用企业-再利用企业");
        list.add(select);
        map.setStatus(260);
        map.setList(list);
        return map;
    }

    /**
     * @Auther: Barry
     * @Date: 2020/4/28
     * @Param: truckid 车次id
     * @Param: type 车次类型
     * @Description: 运输公司某个车次记录
     */
    @AuthToken
    @RequestMapping(value = "/truckRecord", method = RequestMethod.GET)
    public Map<String,Object> truckRecord(Integer truckid,String type){
        Map<String,Object> map = new HashMap<>();
        if(null == truckid || StringUtils.isBlank(type)){
            map.put("status",240);
            map.put("message","参数不全");
            return map;
        }
        if(type.equals("医疗机构-回收物流")){
            //医疗机构
            Rmap<Etruckhospitalv> truckhospitalMap = etruckhospitalService.selectVBySql("truckid = "+truckid+" order by id desc");
            if(null != truckhospitalMap.getList()){
                map.put("truckhospital", truckhospitalMap.getList());
            }else{
                map.put("truckhospital", null);
            }
            //回收物流
            Rmap<Etrucktransportcompanyv> trucktransportcompanyMap = etrucktransportcompanyService.selectVBySql("truckid = "+truckid+" order by id desc");
            if(null != trucktransportcompanyMap.getList()){
                map.put("trucktransportcompany", trucktransportcompanyMap.getList());
            }else{
                map.put("trucktransportcompany", null);
            }
        }else if(type.equals("医疗机构-回收利用企业")){
            //医疗机构
            Rmap<Etruckhospitalv> truckhospitalMap = etruckhospitalService.selectVBySql("truckid = "+truckid+" order by id desc");
            if(null != truckhospitalMap.getList()){
                map.put("truckhospital", truckhospitalMap.getList());
            }else{
                map.put("truckhospital", null);
            }
            //回收利用企业
            Rmap<Etrucktreatmentplantv> trucktreatmentplantMap = etrucktreatmentplantService.selectVBySql("truckid = "+truckid+" order by id desc");
            if(null != trucktreatmentplantMap.getList()){
                map.put("trucktreatmentplant", trucktreatmentplantMap.getList());
            }else{
                map.put("trucktreatmentplant", null);
            }
        }else if(type.equals("回收物流-回收利用企业")){
            //回收物流
            Rmap<Etrucktransportcompanyv> trucktransportcompanyMap = etrucktransportcompanyService.selectVBySql("truckid = "+truckid+" order by id desc");
            if(null != trucktransportcompanyMap.getList()){
                map.put("trucktransportcompany", trucktransportcompanyMap.getList());
            }else{
                map.put("trucktransportcompany", null);
            }
            //回收利用企业
            Rmap<Etrucktreatmentplantv> trucktreatmentplantMap = etrucktreatmentplantService.selectVBySql("truckid = "+truckid+" order by id desc");
            if(null != trucktreatmentplantMap.getList()){
                map.put("trucktreatmentplant", trucktreatmentplantMap.getList());
            }else{
                map.put("trucktreatmentplant", null);
            }
        }else if(type.equals("回收利用企业-再利用企业")){
            //司机去处理厂收货
            Rmap<Etrucktreatmentplantreceivev> trucktreatmentplantreceiveMap = etrucktreatmentplantreceiveService.selectVBySql("truckid = "+truckid+" order by id desc");
            if(null != trucktreatmentplantreceiveMap.getList()){
                map.put("trucktreatmentplantreceive", trucktreatmentplantreceiveMap.getList());
            }else{
                map.put("trucktreatmentplantreceive", null);
            }
            //再利用企业
            Rmap<Etruckproductionplantv> truckproductionplantMap = etruckproductionplantService.selectVBySql("truckid = "+truckid+" order by id desc");
            if(null != truckproductionplantMap.getList()){
                map.put("truckproductionplant", truckproductionplantMap.getList());
            }else{
                map.put("truckproductionplant", null);
            }
        }else{
            map.put("status",240);
            map.put("message","车次类型错误");
        }
        if(!map.containsKey("status")){
            map.put("status",260);
        }
        return map;
    }

}