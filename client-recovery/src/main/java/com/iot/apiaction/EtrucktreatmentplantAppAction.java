package com.iot.apiaction;

import com.iot.action.BaseController;
import com.iot.bean.*;
import com.iot.service.EtruckService;
import com.iot.service.EtruckhospitalService;
import com.iot.service.EtrucktransportcompanyService;
import com.iot.service.EtrucktreatmentplantService;
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
@RequestMapping("/api/trucktreatmentplant")
public class EtrucktreatmentplantAppAction extends BaseController {

    @Resource
    private EtrucktreatmentplantService etrucktreatmentplantService;
    @Resource
    private EtruckService etruckService;
    @Resource
    private EtruckhospitalService etruckhospitalService;
    @Resource
    private EtrucktransportcompanyService etrucktransportcompanyService;

    /**
     * @Auther: Barry
     * @Date: 2020/4/15
     * @Param: userid 司机用户id
     * @Param: truckid 车次id
     * @Param: status 状态
     * @Param: begintime 开始时间
     * @Param: endtime 结束时间
     * @Description: 车次给处理厂送货，司机审核记录
     */
    @AuthToken
    @RequestMapping(value = "/confirmUserRecord", method = RequestMethod.GET)
    public Rmap<Etrucktreatmentplantv> confirmUserRecord(Integer truckid, String status, Date begintime, Date endtime){
        Rmap<Etrucktreatmentplantv> map = new Rmap<>();
        StringBuilder sql =new StringBuilder();
        if(null == truckid){
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
        map = etrucktreatmentplantService.selectVBySql(sql.toString());
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
     * @Param: page
     * @Param: rows
     * @Param: treatmentplantid
     * @Param: userid
     * @Param: status
     * @Param: begintime
     * @Param: endtime
     * @Description: 车次给处理厂送货 处理厂提交的审核记录
     */
    @AuthToken
    @RequestMapping(value = "/submitUserRecord", method = RequestMethod.GET)
    public Rmap<Etrucktreatmentplantv> submitUserRecord(Integer page, Integer rows, Integer treatmentplantid, Integer userid, String status, Date begintime, Date endtime){
        Rmap<Etrucktreatmentplantv> map = new Rmap<>();
        StringBuilder sql =new StringBuilder();
        if(null == userid || null == treatmentplantid ){
            map.setStatus(240);
            map.setMessage("参数不全");
            return map;
        }
        sql.append("submituserid = "+userid).append(" and treatmentplantid = "+treatmentplantid);
        if (StringUtils.isNotBlank(status)) {
            sql.append(" and status = '").append(status).append("'");
        }
        if(null != begintime && null != endtime){
            SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            sql.append(" and time >= '").append(format.format(begintime)).append("' and time <= '").append(format.format(endtime)).append("'");
        }
        map = etrucktreatmentplantService.selectVByPage(page, rows, "id", "desc", sql.toString());
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
     * @Param: trucktreatmentplantid
     * @Param: status
     * @Param: note
     * @Description: 司机审核   医疗机构-回收利用企业  回收物流-回收利用企业
     */
    @AuthToken
    @RequestMapping(value = "/transporterAudit", method = RequestMethod.PUT)
    public Rmap<Etrucktreatmentplant> transporterAudit(Integer trucktreatmentplantid, String status, Integer userid, String note){
        Rmap<Etrucktreatmentplant> map = new Rmap<Etrucktreatmentplant>();
        if(null == trucktreatmentplantid || StringUtils.isBlank(status) || null == userid){
            map.setStatus(240);
            map.setMessage("参数不全");
            return map;
        }
        map = etrucktreatmentplantService.selectByPrimaryKey(trucktreatmentplantid);
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
                Etrucktreatmentplant truckproductionplant = map.getObject();
                truckproductionplant.setStatus(status);
                truckproductionplant.setConfirmuserid(userid);
                if(null != note){
                    truckproductionplant.setNote(note);
                }
                map = etrucktreatmentplantService.update(truckproductionplant);
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
                Etrucktreatmentplant truckproductionplant = map.getObject();
                truckproductionplant.setStatus(status);
                truckproductionplant.setConfirmuserid(userid);
                if(null != note){
                    truckproductionplant.setNote(note);
                }
                map = etrucktreatmentplantService.update(truckproductionplant);
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
     * @Date: 2020/4/15
     * @Param: record
     * @Description: 创建审核
     */
    @AuthToken
    @RequestMapping(value = "/createRecord", method = RequestMethod.POST)
    public Rmap<Etrucktreatmentplant> createRecord(Etrucktreatmentplant record) {
        Rmap<Etrucktreatmentplant> map = new  Rmap<Etrucktreatmentplant>();
        if(null == record.getTruckid()){
            map.setStatus(240);
            map.setMessage("参数不全");
            return map;
        }
        Rmap<Etruck> truckMap = etruckService.selectByPrimaryKey(record.getTruckid());
        if(null != truckMap.getObject()){
            if(truckMap.getObject().getType().equals("医疗机构-回收利用企业")){
                Rmap<Etruckhospital> truckhospitalMap = etruckhospitalService.selectBySql("truckid = "+record.getTruckid()+" and status in ('待审核','审核不通过')");
                if(null != truckhospitalMap.getList()){
                    map.setStatus(240);
                    map.setMessage("有审核不通过记录，不能创建");
                    return map;
                }
            }else if(truckMap.getObject().getType().equals("回收物流-回收利用企业")){
                Rmap<Etrucktransportcompany> trucktransportcompanyMap = etrucktransportcompanyService.selectBySql("truckid = "+record.getTruckid()+" and status in ('待审核','审核不通过')");
                if(null != map.getList()){
                    map.setStatus(240);
                    map.setMessage("有审核不通过记录，不能创建");
                    return map;
                }
            }else{
                map.setStatus(240);
                map.setMessage("车次类型错误");
                return map;
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
        Rmap<Etrucktreatmentplant>  trucktreatmentplantMap = etrucktreatmentplantService.selectBySql("truckid = "+record.getTruckid());
        if(null != trucktreatmentplantMap.getList()){
            map.setStatus(240);
            map.setMessage("已有提交，不能再次提交审核");
        }else{
            if(null == trucktreatmentplantMap.getMessage()){
                record.setTime(new Date());
                map = etrucktreatmentplantService.insert(record);
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
                map.setMessage(trucktreatmentplantMap.getMessage());
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
    public Rmap<Etrucktreatmentplant> update(Etrucktreatmentplant record) {
        Rmap<Etrucktreatmentplant> map = etrucktreatmentplantService.update(record);
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
