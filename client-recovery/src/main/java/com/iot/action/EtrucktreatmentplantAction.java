package com.iot.action;

import com.iot.bean.Etrucktreatmentplant;
import com.iot.bean.Etrucktreatmentplantv;
import com.iot.bean.Rmap;
import com.iot.bean.Select;
import com.iot.service.EtrucktreatmentplantService;
import com.iot.util.AuthToken;
import com.iot.util.ExcelExport;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/trucktreatmentplant")
public class EtrucktreatmentplantAction extends BaseController{
    @Resource
    private EtrucktreatmentplantService etrucktreatmentplantService;

    public static final String KEYS = "time,treatmentplant,transportcompany,number,plasticnumber,plasticweight,glassnumber,glassweight,submituser,confirmuser,note";

    public static final String TITLES = "时间,回收利用企业,回收物流,车牌号码,塑料数量,塑料重量,玻璃数量,玻璃重量,提交人,确认人,备注";

    public static final String SUM_KEYS = "plasticnumber,plasticweight,glassnumber,glassweight";

    public static final String SUM_TITLES = "塑料总袋数,塑料总重量,玻璃总袋数,玻璃总重量";

    /**
     * @Auther: Barry
     * @Date: 2020/3/25
     * @Param:
     * @Description: 查询所有
     */
    @AuthToken
    @RequestMapping(value = "/selectAll", method = RequestMethod.GET)
    public Rmap<Etrucktreatmentplant> selectAll(){
        Rmap<Etrucktreatmentplant> map = etrucktreatmentplantService.selectAll();
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
    public Rmap<Etrucktreatmentplant> selectByPage(Integer page, Integer rows, Integer truckid, Integer submituserid, Integer confirmuserid, Integer treatmentplantid, String status, Date begintime, Date endtime){
        Rmap<Etrucktreatmentplant> map = new Rmap<Etrucktreatmentplant>();
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
        map = etrucktreatmentplantService.selectByPage(page, rows, "id", "desc", sql.toString());
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
    public  Rmap<Etrucktreatmentplantv> selectByTruckid(Integer truckid){
        Rmap<Etrucktreatmentplantv> map = etrucktreatmentplantService.selectVBySql("truckid = "+truckid);
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
    public Rmap<Etrucktreatmentplantv> selectVByPage(Integer page, Integer rows, Integer truckid, Integer submituserid, Integer confirmuserid, Integer treatmentplantid, String status, Date begintime, Date endtime){
        Rmap<Etrucktreatmentplantv> map = new Rmap<Etrucktreatmentplantv>();
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
        map = etrucktreatmentplantService.selectVByPage(page, rows, "id", "desc", sql.toString());
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
    public Rmap<Etrucktreatmentplant> selectByPrimaryKey(Integer id){
        Rmap<Etrucktreatmentplant> map = etrucktreatmentplantService.selectByPrimaryKey(id);
        if(null != map.getObject()){
            map.setStatus(260);
        }else{
            map.setStatus(240);
        }
        return map;
    }

    /*
     * @Date 2020/9/7 14:45
     * @Description  回收利用企业入库记录
     * @Param
     * @Return
     */
    @AuthToken
    @RequestMapping(value = "/trucktreatmentplantList", method = RequestMethod.GET)
    public Rmap<Map<String, Object>> trucktreatmentplantList(Integer page, Integer rows,  Integer submituserid, Integer treatmentplantid, Integer transportcompanyid, String status, Date begintime, Date endtime){
        Rmap<Map<String, Object>> map = new Rmap<Map<String, Object>>();
        if(null == treatmentplantid){
            map.setStatus(240);
            map.setMessage("参数不全");
            return map;
        }
        StringBuilder sql = new StringBuilder();
        sql.append("t1.treatmentplantid = " + treatmentplantid);
        if (null != submituserid) {
            sql.append(" and t1.submituserid = ").append(submituserid);
        }
        if (null != transportcompanyid) {
            sql.append(" and t7.id = ").append(transportcompanyid);
        }
        if (StringUtils.isNotBlank(status)) {
            sql.append(" and t1.status = '").append(status).append("'");
        }
        if (null != begintime && null != endtime) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            sql.append(" and t1.time >= '").append(format.format(begintime)).append("' and t1.time <= '").append(format.format(endtime)).append("'");
        }
        map = etrucktreatmentplantService.trucktreatmentplantList(page, rows, "t1.id", "desc", sql.toString());
        if (null != map.getList()) {
            map.setStatus(260);
            //总数
            Rmap<Map<String, Object>> sumMap = etrucktreatmentplantService.trucktreatmentplantSum(sql.toString());
            map.getInfo().put("sum",sumMap.getObject());
        } else {
            map.setStatus(240);
        }
        return map;
    }

    /*
     * @Date 2020/9/7 15:02
     * @Description 回收利用企业 入库记录
     * @Param
     * @Return
     */
    @RequestMapping(value = "/trucktreatmentplantListExport")
    public void trucktreatmentplantListExport(Integer submituserid, Integer treatmentplantid, Integer transportcompanyid, String status, Date begintime, Date endtime, HttpServletResponse response){
        Rmap<Map<String, Object>> map = new Rmap<Map<String, Object>>();
        if(null == treatmentplantid){
            try {
                String message = "参数不全";
                OutputStream outputStream = response.getOutputStream();//获取OutputStream输出流
                response.setHeader("content-type", "text/html;charset=UTF-8");
                byte[] dataByteArr = message.getBytes("UTF-8");
                outputStream.write(dataByteArr);
                return;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }
        StringBuilder sql = new StringBuilder();
        sql.append("t1.treatmentplantid = " + treatmentplantid);
        if (null != submituserid) {
            sql.append(" and t1.submituserid = ").append(submituserid);
        }
        if (null != transportcompanyid) {
            sql.append(" and t7.id = ").append(transportcompanyid);
        }
        if (StringUtils.isNotBlank(status)) {
            sql.append(" and t1.status = '").append(status).append("'");
        }
        if (null != begintime && null != endtime) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            sql.append(" and t1.time >= '").append(format.format(begintime)).append("' and t1.time <= '").append(format.format(endtime)).append("'");
        }
        sql.append(" order by t1.id desc");
        map = etrucktreatmentplantService.trucktreatmentplantListExport(sql.toString());
        Rmap<Map<String, Object>> sumMap = etrucktreatmentplantService.trucktreatmentplantSum(sql.toString());
        List<Map<String, Object>> sumMapList = new ArrayList<>();
        sumMapList.add(sumMap.getObject());
        try {
            String fileName = "入库记录" + ".xls";
            String[] titles = TITLES.split(",");
            String[] keys = KEYS.split(",");
            ExcelExport.setResponseHeader(response, fileName);
            OutputStream out = response.getOutputStream();
            if(null != map.getList()){
                HSSFWorkbook hSSFWorkbook = ExcelExport.manyExport(out, titles, keys, map.getList());
                ExcelExport.manyExport(hSSFWorkbook,out, SUM_TITLES.split(","), SUM_KEYS.split(","), sumMapList);
            }else{
                HSSFWorkbook hSSFWorkbook = ExcelExport.manyExport(out, titles, keys, new ArrayList<Map<String, Object>>());
                ExcelExport.manyExport(hSSFWorkbook,out, SUM_TITLES.split(","), SUM_KEYS.split(","), sumMapList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @Auther: Barry
     * @Date: 2020/3/25
     * @Param: record
     * @Description: 创建车次
     */
    @AuthToken
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public Rmap<Etrucktreatmentplant> insert(Etrucktreatmentplant record) {
        Rmap<Etrucktreatmentplant> map = new Rmap<Etrucktreatmentplant>();
        Rmap<Etrucktreatmentplant> rmap = etrucktreatmentplantService.insert(record);
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
    public Rmap<Etrucktreatmentplant> update(Etrucktreatmentplant record) {
        Rmap<Etrucktreatmentplant> map = new Rmap<Etrucktreatmentplant>();
        Rmap<Etrucktreatmentplant> rmap = etrucktreatmentplantService.update(record);
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
    public Rmap<Etrucktreatmentplant> delete(Integer id) {
        Rmap<Etrucktreatmentplant> map = etrucktreatmentplantService.delete(id);
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
    public Rmap<Etrucktreatmentplant> deletes(@RequestBody Integer[] ids){
        String strIds =  ArrayUtils.toString(ids, ",").replace('{', '(').replace('}', ')');
        Rmap<Etrucktreatmentplant> map = etrucktreatmentplantService.deletes(strIds);
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