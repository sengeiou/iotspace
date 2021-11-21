package com.iot.apiaction;

import com.iot.action.BaseController;
import com.iot.bean.*;
import com.iot.service.EtruckService;
import com.iot.service.EtruckproductionplantService;
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
 * @Description:
 */
@RestController
@RequestMapping("/api/truckproductionplant")
public class EtruckproductionplantAppAction extends BaseController {

    @Resource
    private EtruckproductionplantService etruckproductionplantService;
    @Resource
    private EtruckService etruckService;
    @Resource
    private EtrucktreatmentplantreceiveService etrucktreatmentplantreceiveService;


    /**
     * @Auther: Barry
     * @Date: 2020/4/15
     * @Param: truckid
     * @Param: userid
     * @Param: status
     * @Param: begintime
     * @Param: endtime
     * @Description: 生产厂送货 司机审核的记录
     */
    @AuthToken
    @RequestMapping(value = "/confirmUserRecord", method = RequestMethod.GET)
    public Rmap<Etruckproductionplantv> confirmUserRecord(Integer truckid, String status, Date begintime, Date endtime){
        Rmap<Etruckproductionplantv> map = new Rmap<>();
        StringBuilder sql =new StringBuilder();
        if(null == truckid ){
            map.setStatus(240);
            map.setMessage("参数不全");
            return map;
        }
        sql.append("truckid = "+truckid);
        if (StringUtils.isNotBlank(status)) {
            sql.append(" and status = '").append(status).append("'");
        }
        if(null != begintime && null != endtime){
            SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            sql.append(" and time >= '").append(format.format(begintime)).append("' and time <= '").append(format.format(endtime)).append("'");
        }
        map = etruckproductionplantService.selectVBySql(sql.toString());
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
     * @Param: truckproductionplantid
     * @Param: status
     * @Param: note
     * @Description: 司机审核 司机审核   回收利用企业-再利用企业
     */
    @AuthToken
    @RequestMapping(value = "/transporterAudit", method = RequestMethod.PUT)
    public Rmap<Etruckproductionplant> transporterAudit(Integer truckproductionplantid, String status, Integer userid, String note){
        Rmap<Etruckproductionplant> map = new Rmap<Etruckproductionplant>();
        if(null == truckproductionplantid || StringUtils.isBlank(status) || null == userid){
            map.setStatus(240);
            map.setMessage("参数不全");
            return map;
        }
        map = etruckproductionplantService.selectByPrimaryKey(truckproductionplantid);
        if(null != map.getObject()){
            Etruckproductionplant truckproductionplant = map.getObject();
            truckproductionplant.setStatus(status);
            truckproductionplant.setConfirmuserid(userid);
            if(null != note){
                truckproductionplant.setNote(note);
            }
            map = etruckproductionplantService.update(truckproductionplant);
            if(null != map.getObject()){
                map.setStatus(260);
                //更新车次
                if(status.equals("已审核")){
                    Rmap<Etruck> truckMap = etruckService.selectByPrimaryKey(truckproductionplant.getTruckid());
                    if(null != truckMap.getObject()){
                        Etruck etruck = truckMap.getObject();
                        etruck.setStatus("已完成");
                        etruckService.update(etruck);
                    }
                }
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
     * @Param: page
     * @Param: rows
     * @Param: userid
     * @Param: productionplantid
     * @Param: status
     * @Param: begintime
     * @Param: endtime
     * @Description: 生产厂提交的记录
     */
    @AuthToken
    @RequestMapping(value = "/submitUserRecord", method = RequestMethod.GET)
    public Rmap<Etruckproductionplantv> submitUserRecord(Integer page, Integer rows, Integer userid, Integer productionplantid, String status, Date begintime, Date endtime){
        Rmap<Etruckproductionplantv> map = new Rmap<>();
        StringBuilder sql =new StringBuilder();
        if(null == userid || null == productionplantid){
            map.setStatus(240);
            map.setMessage("参数不全");
            return map;
        }
        sql.append("submituserid = "+userid).append(" and productionplantid = ").append(productionplantid);
        if (StringUtils.isNotBlank(status)) {
            sql.append(" and status = '").append(status).append("'");
        }
        if(null != begintime && null != endtime){
            SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            sql.append(" and time >= '").append(format.format(begintime)).append("' and time <= '").append(format.format(endtime)).append("'");
        }
        map = etruckproductionplantService.selectVByPage(page, rows, "id", "desc", sql.toString());
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
     * @Description: 生产厂提交审核
     */
    @AuthToken
    @RequestMapping(value = "/createRecord", method = RequestMethod.POST)
    public Rmap<Etruckproductionplant> createRecord(Etruckproductionplant record) {
        Rmap<Etruckproductionplant> map = new  Rmap<Etruckproductionplant>();
        if(null == record.getTruckid()){
            map.setStatus(240);
            map.setMessage("参数不全");
            return map;
        }
        Rmap<Etrucktreatmentplantreceive> trucktreatmentplantreceiveMap = etrucktreatmentplantreceiveService.selectBySql("truckid = "+record.getTruckid()+" and status in ('待审核','审核不通过')");
        if(null != trucktreatmentplantreceiveMap.getList()){
            map.setStatus(240);
            map.setMessage("有审核不通过记录，不能创建");
            return map;
        }
        Rmap<Etruckproductionplant> productionplantMap = etruckproductionplantService.selectBySql("truckid = "+record.getTruckid());
        if(null != productionplantMap.getList()){
            map.setStatus(240);
            map.setMessage("已有提交，不能再次提交审核");
        }else{
            if(null == productionplantMap.getMessage()){
                record.setTime(new Date());
                map = etruckproductionplantService.insert(record);
                if (null != map.getObject()) {
                    map.setStatus(260);
                } else {
                    map.setStatus(240);
                    if (null == map.getMessage()) {
                        map.setMessage("提交审核失败");
                    }
                }
            }else{
                map.setStatus(240);
                map.setMessage(productionplantMap.getMessage());
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
    public Rmap<Etruckproductionplant> update(Etruckproductionplant record) {
        Rmap<Etruckproductionplant> map = etruckproductionplantService.update(record);
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
     * @Date: 2020/4/13
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
