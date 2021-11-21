package com.iot.apiaction;

import com.iot.action.BaseController;
import com.iot.bean.*;
import com.iot.service.*;
import com.iot.util.AuthToken;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Auther: Barry
 * @Date: 2020/4/14
 * @Description:
 */
@RestController
@RequestMapping("/api/truck")
public class EtruckAppAction extends BaseController {

    @Resource
    private EtruckService etruckService;
    @Resource
    private EtruckhospitalService etruckhospitalService;
    @Resource
    private EtruckproductionplantService etruckproductionplantService;
    @Resource
    private EtrucktreatmentplantService etrucktreatmentplantService;
    @Resource
    private EtrucktransportcompanyService etrucktransportcompanyService;
    @Resource
    private EtrucktreatmentplantreceiveService etrucktreatmentplantreceiveService;

    /**
     * @Auther: Barry
     * @Date: 2020/4/14
     * @Param: page
     * @Param: rows
     * @Param: userid
     * @Param: transportcompanyid
     * @Param: status
     * @Param: type
     * @Param: begintime
     * @Param: endtime
     * @Description: 司机登录 查看车次
     */
    @AuthToken
    @RequestMapping(value = "/selectTrack", method = RequestMethod.GET)
    public Rmap<Etruckv> selectTrack(Integer page, Integer rows, Integer userid, Integer transportcompanyid, String status, String type, Date begintime, Date endtime){
        Rmap<Etruckv> map = new Rmap<>();
        if(null == userid || null == transportcompanyid){
            map.setStatus(240);
            map.setMessage("参数不全");
            return map;
        }
        StringBuilder sql =new StringBuilder();
        sql.append("userid = "+userid).append(" and transportcompanyid = "+transportcompanyid);
        if (StringUtils.isNotBlank(status)) {
            sql.append(" and status = '").append(status).append("'");
        }
        if (StringUtils.isNotBlank(type)) {
            sql.append(" and type = '").append(type).append("'");
        }
        if(null != begintime && null != endtime){
            SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            sql.append(" and time >= '").append(format.format(begintime)).append("' and time <= '").append(format.format(endtime)).append("'");
        }
        map = etruckService.selectVByPage(page, rows, "id", "desc", sql.toString());
        if(null != map.getList()){
            map.setStatus(260);
        }else{
            map.setStatus(240);
        }
        return map;
    }

    /**
     * @Auther: Barry
     * @Date: 2020/4/16
     * @Param: page
     * @Param: rows
     * @Param: transportcompanyid
     * @Param: status
     * @Param: type
     * @Param: number
     * @Param: begintime
     * @Param: endtime
     * @Description: 查询车次
     */
    @AuthToken
    @RequestMapping(value = "/selectByTransportCompany", method = RequestMethod.GET)
    public Rmap<Etruckv> selectByTransportCompany(Integer transportcompanyid, String status, String type, String number, Date begintime, Date endtime){
        Rmap<Etruckv> map = new Rmap<>();
        if(null == transportcompanyid){
            map.setStatus(240);
            map.setMessage("参数不全");
            return map;
        }
        StringBuilder sql =new StringBuilder();
        sql.append("transportcompanyid = "+transportcompanyid);
        if (StringUtils.isNotBlank(status)) {
            sql.append(" and status = '").append(status).append("'");
        }
        if (StringUtils.isNotBlank(type)) {
            sql.append(" and type = '").append(type).append("'");
        }
        if (StringUtils.isNotBlank(number)) {
            sql.append(" and number like ").append("'%25").append(number).append("%25'");
        }
        if(null != begintime && null != endtime){
            SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            sql.append(" and time >= '").append(format.format(begintime)).append("' and time <= '").append(format.format(endtime)).append("'");
        }
        sql.append(" order by id desc");
        map = etruckService.selectVBySql(sql.toString());
        if(null != map.getList()){
            map.setStatus(260);
        }else{
            map.setStatus(240);
        }
        return map;
    }

    /**
     * @Auther: Barry
     * @Date: 2020/4/15
     * @Param:
     * @Description: 车次类型
     */
    @AuthToken
    @RequestMapping(value = "/typeList", method = RequestMethod.GET)
    public Rmap<Select> typeList(){
        Rmap<Select> map = new Rmap<Select>();
        List<Select> list = new ArrayList<Select>();
        Select select = new Select();
        select.setText("医疗机构-回收利用企业");
        select.setValue("医疗机构-回收利用企业");
        list.add(select);
        select = new Select();
        select.setText("医疗机构-回收物流");
        select.setValue("医疗机构-回收物流");
        list.add(select);
        select = new Select();
        select.setText("回收物流-回收利用企业");
        select.setValue("回收物流-回收利用企业");
        list.add(select);
        select = new Select();
        select.setText("回收利用企业-再利用企业");
        select.setValue("回收利用企业-再利用企业");
        list.add(select);
        map.setStatus(260);
        map.setList(list);
        return map;
    }

    /**
     * @Auther: Barry
     * @Date: 2020/4/15
     * @Param:
     * @Description: 车次状态
     */
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
     * @Date: 2020/4/23
     * @Param: page
     * @Param: rows
     * @Param: type 车次类型
     * @Param: status 审核记录状态
     * @Param: userid
     * @Description: 运输司机 审核记录
     */
/*
    @AuthToken
*/
    @RequestMapping(value = "/transporterTruckVerifyRecordList", method = RequestMethod.GET)
    public Map<String,Object> TransporterTruckVerifyRecord(Integer page, Integer rows,String type, String status,Integer userid){
        Map<String,Object> map = new HashMap<>();
        if(StringUtils.isBlank(type) || null == userid){
            map.put("status",240);
            map.put("message","参数不全");
            return map;
        }
        if(type.equals("回收利用企业-再利用企业")){
            StringBuilder sql =new StringBuilder();
            sql.append(" t1.userid = ").append(userid);
            sql.append(" and t1.type = '").append(type).append("'");
            sql.append(" and t1.id = t2.truckid");
            if(StringUtils.isNotEmpty(status)){
                sql.append(" and t2.status = '").append(status).append("'");
            }
            Rmap<Etruckproductionplantv> rmap = etruckproductionplantService.selectVByStatusAndSqlPage(page, rows, "t2.id", "desc", sql.toString());
            if(null != rmap.getList()){
                map.put("status",260);
                map.put("truckproductionplant",rmap.getList());
                map.put("total",rmap.getInfo().get("total"));
            }else{
                map.put("status",240);
                if(null == rmap.getMessage()){
                    map.put("message","暂无数据");
                }else{
                    map.put("message",rmap.getMessage());
                }
            }
        }else if(type.equals("医疗机构-回收利用企业")){
            StringBuilder sql =new StringBuilder();
            sql.append(" t1.userid = ").append(userid);
            sql.append(" and t1.type = '").append(type).append("'");
            sql.append(" and t1.id = t2.truckid");
            if(StringUtils.isNotEmpty(status)){
                sql.append(" and t2.status = '").append(status).append("'");
            }
            Rmap<Etrucktreatmentplantv> rmap = etrucktreatmentplantService.selectVByStatusAndSqlPage(page, rows, "t2.id", "desc", sql.toString());
            if(null != rmap.getList()){
                map.put("status",260);
                map.put("trucktreatmentplant",rmap.getList());
                map.put("total",rmap.getInfo().get("total"));
            }else{
                map.put("status",240);
                if(null == rmap.getMessage()){
                    map.put("message","暂无数据");
                }else{
                    map.put("message",rmap.getMessage());
                }
            }
        }else if(type.equals("回收物流-回收利用企业")){
            StringBuilder sql =new StringBuilder();
            sql.append(" t1.userid = ").append(userid);
            sql.append(" and t1.type = '").append(type).append("'");
            sql.append(" and t1.id = t2.truckid");
            if(StringUtils.isNotEmpty(status)){
                sql.append(" and t2.status = '").append(status).append("'");
            }
            Rmap<Etrucktreatmentplantv> rmap = etrucktreatmentplantService.selectVByStatusAndSqlPage(page, rows, "t2.id", "desc", sql.toString());
            if(null != rmap.getList()){
                map.put("status",260);
                map.put("trucktreatmentplant",rmap.getList());
                map.put("total",rmap.getInfo().get("total"));
            }else{
                map.put("status",240);
                if(null == rmap.getMessage()){
                    map.put("message","暂无数据");
                }else{
                    map.put("message",rmap.getMessage());
                }
            }
        }else if(type.equals("医疗机构-回收物流")){
            StringBuilder sql =new StringBuilder();
            sql.append(" t1.userid = ").append(userid);
            sql.append(" and t1.type = '").append(type).append("'");
            sql.append(" and t1.id = t2.truckid");
            if(StringUtils.isNotEmpty(status)){
                sql.append(" and t2.status = '").append(status).append("'");
            }
            Rmap<Etrucktransportcompanyv> rmap = etrucktransportcompanyService.selectVByStatusAndSqlPage(page, rows, "t2.id", "desc", sql.toString());
            if(null != rmap.getList()){
                map.put("status",260);
                map.put("trucktransportcompany",rmap.getList());
                map.put("total",rmap.getInfo().get("total"));
            }else{
                map.put("status",240);
                if(null == rmap.getMessage()){
                    map.put("message","暂无数据");
                }else{
                    map.put("message",rmap.getMessage());
                }
            }
        }else{
            map.put("status",240);
            map.put("message","参数错误");
            return map;
        }
        return map;
    }

    /**
     * @Auther: Barry
     * @Date: 2020/4/23
     * @Param: page
     * @Param: rows
     * @Param: type 车次类型
     * @Param: status 审核记录状态
     * @Param: userid
     * @Description: 运输司机 提交记录
     */
/*
    @AuthToken
*/
    @RequestMapping(value = "/transporterTruckSubmitRecordList", method = RequestMethod.GET)
    public Map<String,Object> transporterTruckSubmitRecordList(Integer page, Integer rows,String type, String status,Integer userid){
        Map<String,Object> map = new HashMap<>();
        if(StringUtils.isBlank(type) || null == userid){
            map.put("status",240);
            map.put("message","参数不全");
            return map;
        }
        if(type.equals("回收利用企业-再利用企业")){ //处理厂收货
            StringBuilder sql =new StringBuilder();
            sql.append("submituserid = ").append(userid);
            if(StringUtils.isNotEmpty(status)){
                sql.append(" and status = '").append(status).append("'");
            }
            Rmap<Etrucktreatmentplantreceivev> rmap = etrucktreatmentplantreceiveService.selectVByPage(page, rows, "id", "desc", sql.toString());
            if(null != rmap.getList()){
                map.put("status",260);
                map.put("trucktreatmentplantreceive",rmap.getList());
                map.put("total",rmap.getInfo().get("total"));
            }else{
                map.put("status",240);
                if(null == rmap.getMessage()){
                    map.put("message","暂无数据");
                }else{
                    map.put("message",rmap.getMessage());
                }
            }
        }else if(type.equals("医疗机构-回收利用企业")){
            StringBuilder sql =new StringBuilder();
            sql.append("t1.type = '").append(type).append("'");
            sql.append(" and t1.id = t2.truckid");
            sql.append(" and t2.submituserid = ").append(userid);
            if(StringUtils.isNotEmpty(status)){
                sql.append(" and t2.status = '").append(status).append("'");
            }
            Rmap<Etruckhospitalv> rmap =etruckhospitalService.selectVByStatusAndSqlPage(page, rows, "id", "desc", sql.toString());
            if(null != rmap.getList()){
                map.put("status",260);
                map.put("truckhospital",rmap.getList());
                map.put("total",rmap.getInfo().get("total"));
            }else{
                map.put("status",240);
                if(null == rmap.getMessage()){
                    map.put("message","暂无数据");
                }else{
                    map.put("message",rmap.getMessage());
                }
            }
        }else if(type.equals("医疗机构-回收物流")){
            StringBuilder sql =new StringBuilder();
            sql.append("t1.type = '").append(type).append("'");
            sql.append(" and t1.id = t2.truckid");
            sql.append(" and t2.submituserid = ").append(userid);
            if(StringUtils.isNotEmpty(status)){
                sql.append(" and t2.status = '").append(status).append("'");
            }
            Rmap<Etruckhospitalv> rmap =etruckhospitalService.selectVByStatusAndSqlPage(page, rows, "id", "desc", sql.toString());
            if(null != rmap.getList()){
                map.put("status",260);
                map.put("truckhospital",rmap.getList());
                map.put("total",rmap.getInfo().get("total"));
            }else{
                map.put("status",240);
                if(null == rmap.getMessage()){
                    map.put("message","暂无数据");
                }else{
                    map.put("message",rmap.getMessage());
                }
            }
        }else if(type.equals("回收物流-回收利用企业")){ //运输公司收货
            StringBuilder sql =new StringBuilder();
            sql.append("t1.type = '").append(type).append("'");
            sql.append(" and t1.id = t2.truckid");
            sql.append(" and t2.submituserid = ").append(userid);
            if(StringUtils.isNotEmpty(status)){
                sql.append(" and t2.status = '").append(status).append("'");
            }
            Rmap<Etrucktransportcompanyv> rmap = etrucktransportcompanyService.selectVByStatusAndSqlPage(page, rows, "id", "desc", sql.toString());
            if(null != rmap.getList()){
                map.put("status",260);
                map.put("trucktransportcompany",rmap.getList());
                map.put("total",rmap.getInfo().get("total"));
            }else{
                map.put("status",240);
                if(null == rmap.getMessage()){
                    map.put("message","暂无数据");
                }else{
                    map.put("message",rmap.getMessage());
                }
            }
        }else{
            map.put("status",240);
            map.put("message","参数错误");
            return map;
        }
        return map;
    }

    /**
     * @Auther: Barry
     * @Date: 2020/4/16
     * @Param: page
     * @Param: rows
     * @Param: transportcompanyid
     * @Param: status
     * @Param: type
     * @Param: number
     * @Param: begintime
     * @Param: endtime
     * @Description:  处理厂审核员查询车次
     */
    @AuthToken
    @RequestMapping(value = "/treatmentplantauditorSelect", method = RequestMethod.GET)
    public Rmap<Etruckv> treatmentplantauditorSelect(Integer page, Integer rows,Integer transportcompanyid, String status, String number, Date begintime, Date endtime){
        Rmap<Etruckv> map = new Rmap<>();
        if(null == transportcompanyid){
            map.setStatus(240);
            map.setMessage("参数不全");
            return map;
        }
        StringBuilder sql =new StringBuilder();
        sql.append("transportcompanyid = "+transportcompanyid);
        sql.append(" and type in ('医疗机构-回收利用企业', '回收物流-回收利用企业')");
        if (StringUtils.isNotBlank(status)) {
            sql.append(" and status = '").append(status).append("'");
        }
        if (StringUtils.isNotBlank(number)) {
            sql.append(" and number like ").append("'%25").append(number).append("%25'");
        }
        if(null != begintime && null != endtime){
            SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            sql.append(" and time >= '").append(format.format(begintime)).append("' and time <= '").append(format.format(endtime)).append("'");
        }
        sql.append(" order by id desc");
        map = etruckService.selectVBySql(sql.toString());
        if(null != map.getList()){
            map.setStatus(260);
        }else{
            map.setStatus(240);
        }
        return map;
    }



}
