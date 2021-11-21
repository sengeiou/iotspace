package com.iot.apiaction;

import com.iot.action.BaseController;
import com.iot.bean.*;
import com.iot.service.EtruckService;
import com.iot.service.EtruckhospitalService;
import com.iot.service.EtrucktransportcompanyService;
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
 * @Description: 车次  给处理厂送货
 */
@RestController
@RequestMapping("/api/trucktransportcompany")
public class EtrucktransportcompanyAppAction extends BaseController {

    @Resource
    private EtrucktransportcompanyService etrucktransportcompanyService;
    @Resource
    private EtruckService etruckService;
    @Resource
    private EtruckhospitalService etruckhospitalService;





    /**
     * @Auther: Barry
     * @Date: 2020/4/15
     * @Param: trucktransportcompanyid
     * @Param: status
     * @Param: note
     * @Description: 司机审核   医疗机构-回收物流
     */
    @AuthToken
    @RequestMapping(value = "/transporterAudit", method = RequestMethod.PUT)
    public Rmap<Etrucktransportcompany> transporterAudit(Integer trucktransportcompanyid, String status, Integer userid, String note){
        Rmap<Etrucktransportcompany> map = new Rmap<Etrucktransportcompany>();
        if(null == trucktransportcompanyid || StringUtils.isBlank(status) || null == userid){
            map.setStatus(240);
            map.setMessage("参数不全");
            return map;
        }
        map = etrucktransportcompanyService.selectByPrimaryKey(trucktransportcompanyid);
        if(null != map.getObject()){
            if(status.equals("已审核")){
                Rmap<Etruck> truckMap = etruckService.selectByPrimaryKey(map.getObject().getTruckid());
                if(null != truckMap.getObject()){
                    if(truckMap.getObject().getType().equals("医疗机构-回收利用企业")){
                        Rmap<Etruckhospital> truckhospitalMap = etruckhospitalService.selectBySql("truckid = "+truckMap.getObject().getId()+" and status in ('待审核','审核不通过')");
                        if(null != truckhospitalMap.getList()){
                            map.setStatus(240);
                            map.setMessage("该车次有医院待审核或审核不通过记录");
                            return map;
                        }
                    }
                }else{
                    map.setStatus(240);
                    if(null == truckMap.getMessage()){
                        map.setMessage("未查询到车次信息");
                    }else{
                        map.setMessage(truckMap.getMessage());
                    }
                    return map;
                }
                Etrucktransportcompany truckproductionplant = map.getObject();
                truckproductionplant.setStatus(status);
                truckproductionplant.setConfirmuserid(userid);
                if(null != note){
                    truckproductionplant.setNote(note);
                }
                map = etrucktransportcompanyService.update(truckproductionplant);
                if(null != map.getObject()){
                    map.setStatus(260);
                    //更新车次
                    if (null != truckMap.getObject()) {
                        Etruck etruck = truckMap.getObject();
                        etruck.setStatus("已完成");
                        etruckService.update(etruck);
                    }

                }else{
                    if(null == map.getMessage()){
                        map.setMessage("审核失败");
                    }
                }
            }else{
                Etrucktransportcompany truckproductionplant = map.getObject();
                truckproductionplant.setStatus(status);
                truckproductionplant.setConfirmuserid(userid);
                if(null != note){
                    truckproductionplant.setNote(note);
                }
                map = etrucktransportcompanyService.update(truckproductionplant);
                if(null != map.getObject()){
                    map.setStatus(260);
                }else{
                    if(null == map.getMessage()){
                        map.setMessage("审核失败");
                    }
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
     * @Date: 2020/4/24
     * @Param: truckid
     * @Param: userid
     * @Param: status
     * @Param: begintime
     * @Param: endtime
     * @Description:      司机创建的
     */
    @AuthToken
    @RequestMapping(value = "/transporterRecordList", method = RequestMethod.GET)
    public Rmap<Etrucktransportcompanyv> submitUserRecord(Integer truckid, Integer userid, String status, Date begintime, Date endtime){
        Rmap<Etrucktransportcompanyv> map = new Rmap<>();
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
        map = etrucktransportcompanyService.selectVBySql(sql.toString());
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
     * @Description: 司机创建审核   回收物流-回收利用企业   司机收货
     */
    @AuthToken
    @RequestMapping(value = "/transporterCreateRecord", method = RequestMethod.POST)
    public Rmap<Etrucktransportcompany> createRecord(Etrucktransportcompany record) {
        Rmap<Etrucktransportcompany> map = new  Rmap<Etrucktransportcompany>();
        if(null == record.getTruckid()){
            map.setStatus(240);
            map.setMessage("参数不全");
            return map;
        }
        Rmap<Etrucktransportcompany>  trucktransportcompanyMap = etrucktransportcompanyService.selectBySql("truckid = "+record.getTruckid());
        if(null != trucktransportcompanyMap.getList()){
            map.setStatus(240);
            map.setMessage("已有提交，不能再次提交审核");
        }else{
            if(null == trucktransportcompanyMap.getMessage()){
                record.setTime(new Date());
                map = etrucktransportcompanyService.insert(record);
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
                map.setMessage(trucktransportcompanyMap.getMessage());
            }
        }
        return map;
    }

    /**
     * @Auther: Barry
     * @Date: 2020/4/15
     * @Param: record
     * @Description: 运输公司审核员创建审核   医疗机构-回收物流   司机送货
     */
    @AuthToken
    @RequestMapping(value = "/auditorCreateRecord", method = RequestMethod.POST)
    public Rmap<Etrucktransportcompany> auditorCreateRecord(Etrucktransportcompany record) {
        Rmap<Etrucktransportcompany> map = new  Rmap<Etrucktransportcompany>();
        if(null == record.getTruckid()){
            map.setStatus(240);
            map.setMessage("参数不全");
            return map;
        }
        Rmap<Etruckhospital> truckhospitalMap = etruckhospitalService.selectBySql("truckid = "+record.getTruckid()+" and status in ('待审核','审核不通过')");
        if(null != truckhospitalMap.getList()){
            map.setStatus(240);
            map.setMessage("有审核不通过记录，不能创建");
            return map;
        }
        Rmap<Etrucktransportcompany>  trucktransportcompanyMap = etrucktransportcompanyService.selectBySql("truckid = "+record.getTruckid());
        if(null != trucktransportcompanyMap.getList()){
            map.setStatus(240);
            map.setMessage("已有提交，不能再次提交审核");
        }else{
            if(null == trucktransportcompanyMap.getMessage()){
                record.setTime(new Date());
                map = etrucktransportcompanyService.insert(record);
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
                map.setMessage(trucktransportcompanyMap.getMessage());
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
    public Rmap<Etrucktransportcompany> update(Etrucktransportcompany record) {
        Rmap<Etrucktransportcompany> map = etrucktransportcompanyService.update(record);
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

    /**
     * @Auther: Barry
     * @Date: 2020/4/24
     * @Param: page
     * @Param: rows
     * @Param: userid
     * @Param: status 记录状态
     * @Description: 审核员创建的记录  回收物流-回收利用企业
     */
    @AuthToken
    @RequestMapping(value = "/auditorCreateRecordList", method = RequestMethod.GET)
    public Rmap<Etrucktransportcompanyv> auditorCreateRecordList(Integer page, Integer rows,Integer userid,String status) {
        Rmap<Etrucktransportcompanyv> map = new Rmap<Etrucktransportcompanyv>();
        if(null == userid){
            map.setMessage("参数不全");
            map.setStatus(240);
            return map;
        }
        StringBuilder sql =new StringBuilder();
        sql.append(" t1.type = '医疗机构-回收物流'");
        sql.append(" and t1.id = t2.truckid");
        sql.append(" and t2.submituserid = ").append(userid);
        if(StringUtils.isNotEmpty(status)){
            sql.append(" and t2.status = '").append(status).append("'");
        }
        map = etrucktransportcompanyService.selectVByStatusAndSqlPage(page, rows, "t2.id", "desc", sql.toString());
        if(null != map.getList()){
            map.setStatus(260);
        }else{
            map.setStatus(240);
        }
        return map;
    }

    /**
     * @Auther: Barry
     * @Date: 2020/4/24
     * @Param: page
     * @Param: rows
     * @Param: transportcompanyid
     * @Param: status 记录状态
     * @Description: 运输审核员 审核记录
     */
    @AuthToken
    @RequestMapping(value = "/auditorAuditRecordList", method = RequestMethod.GET)
    public Rmap<Etrucktransportcompanyv> auditorAuditRecordList(Integer page, Integer rows,Integer transportcompanyid,String status) {
        Rmap<Etrucktransportcompanyv> map = new Rmap<Etrucktransportcompanyv>();
        if(null == transportcompanyid){
            map.setMessage("参数不全");
            map.setStatus(240);
            return map;
        }
        StringBuilder sql =new StringBuilder();
        sql.append(" t1.type = '回收物流-回收利用企业'");
        sql.append(" and t1.id = t2.truckid");
        sql.append(" and t2.transportcompanyid = ").append(transportcompanyid);
        if(StringUtils.isNotEmpty(status)){
            sql.append(" and t2.status = '").append(status).append("'");
        }
        map = etrucktransportcompanyService.selectVByStatusAndSqlPage(page, rows, "t2.id", "desc", sql.toString());
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
     * @Param: trucktransportcompanyid
     * @Param: status
     * @Param: note
     * @Description: 审核员审核   回收物流-回收利用企业
     */
    @AuthToken
    @RequestMapping(value = "/auditorAudit", method = RequestMethod.PUT)
    public Rmap<Etrucktransportcompany> auditorAudit(Integer trucktransportcompanyid, String status, Integer userid, String note){
        Rmap<Etrucktransportcompany> map = new Rmap<Etrucktransportcompany>();
        if(null == trucktransportcompanyid || StringUtils.isBlank(status) || null == userid){
            map.setStatus(240);
            map.setMessage("参数不全");
            return map;
        }
        map = etrucktransportcompanyService.selectByPrimaryKey(trucktransportcompanyid);
        if(null != map.getObject()){
            Etrucktransportcompany truckproductionplant = map.getObject();
            truckproductionplant.setStatus(status);
            truckproductionplant.setConfirmuserid(userid);
            if(null != note){
                truckproductionplant.setNote(note);
            }
            map = etrucktransportcompanyService.update(truckproductionplant);
            if(null != map.getObject()){
                map.setStatus(260);
               /* if(status.equals("已审核")){
                    //更新车次
                    Rmap<Etruck> truckMap = etruckService.selectByPrimaryKey(map.getObject().getTruckid());
                    if (null != truckMap.getObject()) {
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
     * 回收物流-回收企业 应收
     * @param truckid 车次id
     * @return
     */
    @AuthToken
    @RequestMapping(value = "/selectByTruckid", method = RequestMethod.GET)
    public Rmap<Etrucktransportcompany> selectByTruckid(Integer truckid) {
        Rmap<Etrucktransportcompany> map = new Rmap<Etrucktransportcompany>();
        if(null == truckid){
            map.setStatus(240);
            map.setMessage("参数不全");
            return map;
        }
        map = etrucktransportcompanyService.selectBySql("truckid  = "+truckid);
        if(null != map.getList()){
            map.setStatus(260);
        }else{
            map.setStatus(240);
        }
        return map;
    }

}

