package com.iot.apiaction;

import com.iot.action.BaseController;
import com.iot.bean.*;
import com.iot.service.EtruckService;
import com.iot.service.EtrucktreatmentplantreceiveService;
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


/**
 * @Auther: Barry
 * @Date: 2020/4/13
 * @Description: 车次 '车次去处理厂-收货
 */
@RestController
@RequestMapping("/api/trucktreatmentplantreceive")
public class EtrucktreatmentplantreceiveAppAction extends BaseController {

    @Resource
    private EtrucktreatmentplantreceiveService etrucktreatmentplantreceiveService;
    @Resource
    private EtruckService etruckService;

    /**
     * @Auther: Barry
     * @Date: 2020/4/15
     * @Param: page
     * @Param: rows
     * @Param: userid 处理厂用户id
     * @Param: treatmentplantid
     * @Param: status
     * @Param: begintime
     * @Param: endtime
     * @Description: 处理厂审核
     */
    @AuthToken
    @RequestMapping(value = "/confirmUserRecord", method = RequestMethod.GET)
    public Rmap<Etrucktreatmentplantreceivev> confirmUserRecord(Integer page, Integer rows,Integer treatmentplantid, String status, Date begintime, Date endtime){
        Rmap<Etrucktreatmentplantreceivev> map = new Rmap<>();
        StringBuilder sql =new StringBuilder();
        if(null == treatmentplantid){
            map.setStatus(240);
            map.setMessage("参数不全");
            return map;
        }
        sql.append("treatmentplantid = "+treatmentplantid);
        if (StringUtils.isNotBlank(status)) {
            sql.append(" and status = '").append(status).append("'");
        }
        if(null != begintime && null != endtime){
            SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            sql.append(" and time >= '").append(format.format(begintime)).append("' and time <= '").append(format.format(endtime)).append("'");
        }
        map = etrucktreatmentplantreceiveService.selectVByPage(page, rows, "id", "desc", sql.toString());
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
     * @Param: truckid 车次id
     * @Param: userid 司机id
     * @Param: status
     * @Param: begintime
     * @Param: endtime
     * @Description: 司机提交的审核记录
     */
    @AuthToken
    @RequestMapping(value = "/submitUserRecord", method = RequestMethod.GET)
    public Rmap<Etrucktreatmentplantreceivev> submitUserRecord(Integer truckid, Integer userid, String status, Date begintime, Date endtime){
        Rmap<Etrucktreatmentplantreceivev> map = new Rmap<>();
        StringBuilder sql =new StringBuilder();
        if(null == userid || null == truckid ){
            map.setStatus(240);
            map.setMessage("参数不全");
            return map;
        }
        sql.append("submituserid = "+userid).append(" and truckid = "+truckid);
        if (StringUtils.isNotBlank(status)) {
            sql.append(" and status = '").append(status).append("'");
        }
        if(null != begintime && null != endtime){
            SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            sql.append(" and time >= '").append(format.format(begintime)).append("' and time <= '").append(format.format(endtime)).append("'");
        }
        map = etrucktreatmentplantreceiveService.selectVBySql(sql.toString());
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
     * @Param: trucktreatmentplantreceiveid
     * @Param: status
     * @Param: note
     * @Description: 处理厂审核
     */
    @AuthToken
    @RequestMapping(value = "/audit", method = RequestMethod.PUT)
    public Rmap<Etrucktreatmentplantreceive> audit(Integer trucktreatmentplantreceiveid, String status, Integer userid, String note){
        Rmap<Etrucktreatmentplantreceive> map = new Rmap<Etrucktreatmentplantreceive>();
        if(null == trucktreatmentplantreceiveid || StringUtils.isBlank(status)){
            map.setStatus(240);
            map.setMessage("参数不全");
            return map;
        }
        map = etrucktreatmentplantreceiveService.selectByPrimaryKey(trucktreatmentplantreceiveid);
        if(null != map.getObject()){
            Etrucktreatmentplantreceive truckproductionplant = map.getObject();
            truckproductionplant.setStatus(status);
            truckproductionplant.setConfirmuserid(userid);
            if(null != note){
                truckproductionplant.setNote(note);
            }
            map = etrucktreatmentplantreceiveService.update(truckproductionplant);
            if(null != map.getObject()){
                map.setStatus(260);
                //更新车次
               /* if(status.equals("已审核")){
                    Rmap<Etruck> truckMap = etruckService.selectByPrimaryKey(truckproductionplant.getTruckid());
                    if(null != truckMap.getObject()){
                        Etruck etruck = truckMap.getObject();
                        etruck.setStatus("已完成");
                        etruckService.update(etruck);
                    }
                }*/
            }else{
                if(null == map.getMessage()){
                    map.setMessage("审核失败");
                }
            }
        }else{
            map.setStatus(240);
            if(null == map.getMessage()){
                map.setMessage("未查询到信息");
            }
        }
        return map;
    }

    /**
     * @Auther: Barry
     * @Date: 2020/4/15
     * @Param: record
     * @Description: 司机创建记录 回收利用企业-再利用企业 处理厂收货
     */
    @AuthToken
    @RequestMapping(value = "/transporterCreateRecord", method = RequestMethod.POST)
    public Rmap<Etrucktreatmentplantreceive> createRecord(Etrucktreatmentplantreceive record) {
        Rmap<Etrucktreatmentplantreceive>  map = etrucktreatmentplantreceiveService.selectBySql("truckid = "+record.getTruckid());
        if(null != map.getList()){
            map.setStatus(240);
            map.setMessage("已有提交，不能再次提交审核");
            return map;
        }
        record.setTime(new Date());
        map = etrucktreatmentplantreceiveService.insert(record);
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
     * @Description: 审核不通过 编辑
     */
    @AuthToken
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public Rmap<Etrucktreatmentplantreceive> update(Etrucktreatmentplantreceive record) {
        Rmap<Etrucktreatmentplantreceive> map = etrucktreatmentplantreceiveService.update(record);
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

    /**
     * @Auther: Barry
     * @Date: 2020/4/15
     * @Param:
     * @Description: 状态列表
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
