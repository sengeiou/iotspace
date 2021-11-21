package com.iot.apiaction;

import com.iot.action.BaseController;
import com.iot.bean.Etruckhospital;
import com.iot.bean.Etruckhospitalv;
import com.iot.bean.Rmap;
import com.iot.bean.Select;
import com.iot.service.EtruckhospitalService;
import com.iot.util.AuthToken;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Auther: Barry
 * @Date: 2020/4/13
 * @Description:
 */
@RestController
@RequestMapping("/api/truckhospital")
public class EtruckhospitalAppAction extends BaseController {

    @Resource
    private EtruckhospitalService etruckhospitalService;

    /**
     * @Auther: Barry
     * @Date: 2020/4/13
     * @Param: page
     * @Param: rows
     * @Param: status 待审核 已审核 审核不通过
     * @Description: 车次审核记录 医院审核人员登录
     */
    @AuthToken
    @RequestMapping(value = "/confirmUserRecord", method = RequestMethod.GET)
    public Rmap<Etruckhospitalv> confirmUserRecord(Integer page, Integer rows, Integer hospitalid, String status, Date begintime, Date endtime){
        Rmap<Etruckhospitalv> map = new Rmap<>();
        StringBuilder sql =new StringBuilder();
        if(null == hospitalid ){
            map.setStatus(240);
            map.setMessage("参数不全");
            return map;
        }
        sql.append("hospitalid = "+hospitalid);
        if (StringUtils.isNotBlank(status)) {
            sql.append(" and status = '").append(status).append("'");
        }
        if(null != begintime && null != endtime){
            SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            sql.append(" and time >= '").append(format.format(begintime)).append("' and time <= '").append(format.format(endtime)).append("'");
        }
        map = etruckhospitalService.selectVByPage(page, rows, "id", "desc", sql.toString());
        if(null != map.getList()){
            map.setStatus(260);
        }else{
            map.setStatus(240);
        }
        return map;
    }

    /**
     * @Auther: Barry
     * @Date: 2020/4/13
     * @Param: truckhospitalid  etruckhospital id
     * @Param: status 状态
     * @Param: note 备注
     * @Description: 车次审核 医院审核人员审核
     */
    @AuthToken
    @RequestMapping(value = "/audit", method = RequestMethod.PUT)
    public Rmap<Etruckhospital> audit(Integer truckhospitalid, String status, Integer userid, String note){
        Rmap<Etruckhospital> map = etruckhospitalService.selectByPrimaryKey(truckhospitalid);
        if(null != map.getObject()){
            Etruckhospital truckhospital = map.getObject();
            truckhospital.setStatus(status);
            truckhospital.setConfirmuserid(userid);
            if(null != note){
                truckhospital.setNote(note);
            }
            map = etruckhospitalService.update(truckhospital);
            if(null != map.getObject()){
                map.setStatus(260);
            }else{
                if(null == map.getMessage()){
                    map.setMessage("审核失败");
                }
            }
        }else{
            map.setStatus(240);
            if(null == map.getMessage()){
                map.setMessage("未查询到记录信息");
            }
        }
        return map;
    }

    /**
     * @Auther: Barry
     * @Date: 2020/4/15
     * @Param: userid 运输司机用户id
     * @Param: truckid 车次id
     * @Param: status 状态
     * @Param: begintime 开始时间
     * @Param: endtime 结束时间
     * @Description: 某一车次 运输司机提交的记录
     */
    @AuthToken
    @RequestMapping(value = "/submitUserRecord", method = RequestMethod.GET)
    public Rmap<Etruckhospitalv> submitUserRecord(Integer userid, Integer truckid, String status, Date begintime, Date endtime){
        Rmap<Etruckhospitalv> map = new Rmap<>();
        StringBuilder sql =new StringBuilder();
        if(null == userid || null == truckid){
            map.setStatus(240);
            map.setMessage("参数不全");
            return map;
        }
        sql.append("submituserid = "+userid).append(" and truckid = ").append(truckid);
        if (StringUtils.isNotBlank(status)) {
            sql.append(" and status = '").append(status).append("'");
        }
        if(null != begintime && null != endtime){
            SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            sql.append(" and time >= '").append(format.format(begintime)).append("' and time <= '").append(format.format(endtime)).append("'");
        }
        sql.append(" order by id desc");
        map = etruckhospitalService.selectVBySql(sql.toString());
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
     * @Param: record
     * @Description: 运输员  提交医院审核   医疗机构-回收利用企业 医疗机构-回收物流
     */
    @AuthToken
    @RequestMapping(value = "/transporterCreateRecord", method = RequestMethod.POST)
    public Rmap<Etruckhospital> createRecord(Etruckhospital record) {
        record.setTime(new Date());
        Rmap<Etruckhospital> map = etruckhospitalService.insert(record);
        if (null != map.getObject()) {
            map.setStatus(260);
        } else {
            map.setStatus(240);
            if (null == map.getMessage()) {
                map.setMessage("提交审核失败");
            }
        }
        return map;
    }

    /**
     * @Auther: Barry
     * @Date: 2020/4/15
     * @Param: record
     * @Description: 司机修改记录
     */
    @AuthToken
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public Rmap<Etruckhospital> update(Etruckhospital record) {
        Rmap<Etruckhospital> map = etruckhospitalService.update(record);
        if (null != map.getObject()) {
            map.setStatus(260);
        } else {
            map.setStatus(240);
            if (null == map.getMessage()) {
                map.setMessage("修改失败");
            }
        }
        return map;
    }

    /*
     * @Date 2020/9/8 10:00
     * @Description 车次医院应收总和
     * @Param
     * @Return
     */
    @AuthToken
    @RequestMapping(value = "/sumByTruckid", method = RequestMethod.GET)
    public Rmap<Map<String, Object>> sumByTruckid(Integer truckid){
        Rmap<Map<String, Object>> map = new  Rmap<Map<String, Object>>();
        if(null == truckid){
            map.setStatus(240);
            map.setMessage("参数不全");
            return map;
        }
        map = etruckhospitalService.sumByTruckid(truckid);
        if(null != map.getObject()){
            map.setStatus(260);
        }else{
            map.setStatus(240);
        }
        return map;
    }

    /**
     * @Auther: Barry
     * @Date: 2020/4/13
     * @Param:
     * @Description: 记录状态
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
        select.setText("审核不通过");
        select.setValue("审核不通过");
        list.add(select);
        map.setStatus(260);
        map.setList(list);
        return map;
    }

}
