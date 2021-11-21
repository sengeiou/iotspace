package com.iot.action;

import com.iot.bean.*;
import com.iot.service.EsupervisionunitService;
import com.iot.service.EtruckhospitalService;
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
import java.util.*;

@RestController
@RequestMapping("/truckhospital")
public class EtruckhospitalAction extends BaseController{
    @Resource
    private EtruckhospitalService etruckhospitalService;
    @Resource
    private EsupervisionunitService esupervisionunitService;

    public static final String KEYS = "time,hospitalname,transportcompanyname,treatmentplantname,number,plasticnumber,plasticweight,glassnumber,glassweight,submituser,confirmuser";

    public static final String TITLES = "时间,医疗机构名称,回收物流,企业名称,车牌号码,塑料数量(袋),塑料重量(KG),玻璃数量(袋),玻璃重量(KG),收集人,交接人";


    public static final String SUM_KEYS = "plasticnumber,plasticweight,glassnumber,glassweight";

    public static final String SUM_TITLES = "塑料总袋数,塑料总重量,玻璃总袋数,玻璃总重量";


    /**
     * @Auther: Barry
     * @Date: 2020/5/29
     * @Param: page
     * @Param: rows
     * @Param: transportcompanyid
     * @Param: status
     * @Param: begintime
     * @Param: endtime
     * @Description: 某一运输公司下的收货 医院-回收物流
     */
    @AuthToken
    @RequestMapping(value = "/selectVByTransportcompanyIdAndPage", method = RequestMethod.GET)
    public Rmap<Etruckhospitalv> selectVByTransportcompanyIdAndPage(Integer page, Integer rows, Integer transportcompanyid,Integer hospitalid, String status, Date begintime, Date endtime){
        Rmap<Etruckhospitalv> map = new Rmap<Etruckhospitalv>();
        if(null == transportcompanyid){
            map.setMessage("参数不全");
            map.setStatus(240);
            return map;
        }
        StringBuilder sql = new StringBuilder();
        sql.append("t1.transportcompanyid = ").append(transportcompanyid);
        sql.append(" and t1.type = '医疗机构-回收物流'");
        if(null != hospitalid){
            sql.append(" and t2.hospitalid = "+hospitalid);
        }
        if(StringUtils.isNotBlank(status)){
            sql.append(" and t2.status = '").append(status).append("'");
        }
        if (null != begintime && null != endtime) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            sql.append(" and t2.time >= '").append(format.format(begintime)).append("' and t2.time <= '").append(format.format(endtime)).append("'");
        }
        map = etruckhospitalService.selectVByTransportcompanyIdAndPage(page, rows, "t2.id", "desc", sql.toString());
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
     * @Param:
     * @Description: 查询所有
     */
    @AuthToken
    @RequestMapping(value = "/selectAll", method = RequestMethod.GET)
    public Rmap<Etruckhospital> selectAll(){
        Rmap<Etruckhospital> map = etruckhospitalService.selectAll();
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
     * @Description: 分页查询车次 医院的车次记录
     */
    @AuthToken
    @RequestMapping(value = "/selectByPage", method = RequestMethod.GET)
    public Rmap<Etruckhospital> selectByPage(Integer page, Integer rows, Integer truckid, Integer submituserid, Integer hospitalid, Integer confirmuserid, String status, Date begintime, Date endtime){
        Rmap<Etruckhospital> map = new  Rmap<Etruckhospital>();
        StringBuilder sql = new StringBuilder();
        if(null != hospitalid){
            sql.append("hospitalid = " + hospitalid);
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
        map = etruckhospitalService.selectByPage(page, rows, "id", "desc", sql.toString());
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
     * @Param: truckid
     * @Param: submituserid
     * @Param: confirmuserid
     * @Param: status
     * @Param: begintime
     * @Param: endtime
     * @Description: v查询 医院的车次记录
     */
    @AuthToken
    @RequestMapping(value = "/selectVByPage", method = RequestMethod.GET)
    public Rmap<Etruckhospitalv> selectVByPage(Integer page, Integer rows, Integer truckid, Integer submituserid, Integer confirmuserid, Integer hospitalid, String status, Date begintime, Date endtime){
        Rmap<Etruckhospitalv> map = new Rmap<Etruckhospitalv>();
        StringBuilder sql = new StringBuilder();
        if(null != hospitalid){
            sql.append("hospitalid = " + hospitalid);
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
        map = etruckhospitalService.selectVByPage(page, rows, "id", "desc", sql.toString());
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
    public Rmap<Etruckhospital> selectByPrimaryKey(Integer id){
        Rmap<Etruckhospital> map = etruckhospitalService.selectByPrimaryKey(id);
        if(null != map.getObject()){
            map.setStatus(260);
        }else{
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
    public  Rmap<Etruckhospitalv> selectByTruckid(Integer truckid){
        Rmap<Etruckhospitalv> map = etruckhospitalService.selectVBySql("truckid = "+truckid);
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
     * @Param: record
     * @Description: 创建车次
     */
    @AuthToken
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public Rmap<Etruckhospital> insert(Etruckhospital record) {
        Rmap<Etruckhospital> map = etruckhospitalService.insert(record);
        if (null != map.getObject()) {
            map.setStatus(260);
        } else {
            map.setStatus(240);
            if (null == map.getMessage()) {
                map.setMessage("添加车次医院审核失败");
            }
        }
        return map;
    }
    /**
     * @Auther: Barry
     * @Date: 2020/4/15
     * @Param: record
     * @Description: 编辑
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

    /**
     * @Auther: Barry
     * @Date: 2020/3/10
     * @Param: id
     * @Description: 删除
     */
    @AuthToken
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public Rmap<Etruckhospital> delete(Integer id) {
        Rmap<Etruckhospital> map = etruckhospitalService.delete(id);
        if(null != map.getObject()){
            map.setStatus(260);
        }else {
            map.setStatus(240);
            if(null == map.getMessage()) {
                map.setMessage("删除车次医院审核失败");
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
    public Rmap<Etruckhospital> deletes(@RequestBody Integer[] ids){
        String strIds =  ArrayUtils.toString(ids, ",").replace('{', '(').replace('}', ')');
        Rmap<Etruckhospital> map = etruckhospitalService.deletes(strIds);
        if(null != map.getList()){
            map.setStatus(260);
        }else{
            map.setStatus(240);
            if(null == map.getMessage()){
                map.setMessage("删除车次医院审核失败");
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

    //统计

    /**
     * @Auther: Barry
     * @Date: 2020/4/20
     * @Param:
     * @Description: 统计各个省的塑料重量, 按照省份分组
     */
    @AuthToken
    @RequestMapping(value = "/statisticProvincePlasticWeight", method = RequestMethod.GET)
    public Map<String,Object> statisticProvincePlasticWeight(){
//        Rmap<Map<String, Object>> map = etruckhospitalService.statisticProvincePlasticWeight();
        Map<String,Object> map = new HashMap<>();
        List<Map<String, Object>> list = new ArrayList<>();
        Rmap<Esupervisionunit> supervisionunitMap = esupervisionunitService.selectBySql("level = '省'");
        if(null != supervisionunitMap.getList()){
            for(Esupervisionunit supervisionunit : supervisionunitMap.getList()){
                Rmap<Integer> plasticweight = etruckhospitalService.provincePlasticWeight(supervisionunit.getAddress());
                if(null != plasticweight.getObject()){
                    map.put("status",260);
                    Map<String,Object> staticMap = new HashMap<>();
                    staticMap.put("name",supervisionunit.getAddress());
                    staticMap.put("value",plasticweight.getObject());
//                    staticMap.put(supervisionunit.getAddress(),plasticweight.getObject());
                    list.add(staticMap);
                }
            }
        }else{
            map.put("status",240);
            if(null == supervisionunitMap.getMessage()){
                map.put("message","暂无省信息");
            }else{
                map.put("message","查询省信息异常");
            }
        }
        if(!map.containsKey("list")){
            map.put("list",list);
        }
        if(!map.containsKey("status")){
            map.put("status",240);
        }
        return map;
    }

    /**
     * @Auther: Barry
     * @Date: 2020/4/20
     * @Param:
     * @Description: 统计各个省的 玻璃重量 省份分组
     */
    @AuthToken
    @RequestMapping(value = "/statisticProvinceGlassWeight", method = RequestMethod.GET)
    public Map<String, Object> statisticProvinceGlassWeight(){
//        Rmap<Map<String, Object>> map = etruckhospitalService.statisticProvinceGlassWeight();
        Map<String,Object> map = new HashMap<>();
        List<Map<String,Object>> list = new ArrayList();
        Rmap<Esupervisionunit> supervisionunitMap = esupervisionunitService.selectBySql("level = '省'");
        if(null != supervisionunitMap.getList()){
            for(Esupervisionunit supervisionunit : supervisionunitMap.getList()){
                Rmap<Integer> glassweight = etruckhospitalService.provinceGlassWeight(supervisionunit.getAddress());
                if(null != glassweight.getObject()){
                    map.put("status",260);
                    Map<String,Object> staticMap = new HashMap<>();
                    staticMap.put("name",supervisionunit.getAddress());
                    staticMap.put("value",glassweight.getObject());
//                    map.put(supervisionunit.getAddress(),glassweight.getObject());
                    list.add(staticMap);
                }
            }
        }else{
            map.put("status",240);
            if(null == supervisionunitMap.getMessage()){
                map.put("message","暂无省信息");
            }else{
                map.put("message","查询省信息异常");
            }
        }
        if(!map.containsKey("list")){
            map.put("list",list);
        }
        if(!map.containsKey("status")){
            map.put("status",240);
        }
        return map;
    }

    /**
     * @Auther: Barry
     * @Date: 2020/4/20
     * @Param:
     * @Description: 统计某个省下的市 塑料重量 市分组
     */
    @AuthToken
    @RequestMapping(value = "/statisticCityPlasticWeight", method = RequestMethod.GET)
    public Map<String,Object> statisticCityPlasticWeight(String address){
//        Rmap<Map<String, Object>> map = etruckhospitalService.statisticCityPlasticWeight(address);
        Map<String,Object> map = new HashMap<>();
        List<Map<String,Object>> list = new ArrayList();
        if(StringUtils.isBlank(address)){
            map.put("status",240);
            map.put("message","参数不全");
            return map;
        }
        Rmap<Esupervisionunit> supervisionunitMap = esupervisionunitService.selectBySql("level = '市' "+" and pid = (select id from esupervisionunit where address = '"+ address+"' )");
        if(null != supervisionunitMap.getList()){
            for(Esupervisionunit supervisionunit : supervisionunitMap.getList()){
                Rmap<Integer> plasticweight = etruckhospitalService.cityPlasticWeight(supervisionunit.getAddress());
                if(null != plasticweight.getObject()){
                    map.put("status",260);
                    Map<String,Object> staticMap = new HashMap<>();
                    staticMap.put("name",supervisionunit.getAddress());
                    staticMap.put("value",plasticweight.getObject());
                    list.add(staticMap);
//                    map.put(supervisionunit.getAddress(),plasticweight.getObject());
                }
            }
        }else{
            map.put("status",240);
            if(null == supervisionunitMap.getMessage()){
                map.put("message","暂无市信息");
            }else{
                map.put("message","查询市信息异常");
            }
        }
        if(!map.containsKey("list")){
            map.put("list",list);
        }
        if(!map.containsKey("status")){
            map.put("status",240);
        }
        return map;
    }

    /**
     * @Auther: Barry
     * @Date: 2020/4/20
     * @Param:
     * @Description: 统计某个省下的市 玻璃重量
     */
    @AuthToken
    @RequestMapping(value = "/statisticCityGlassWeight", method = RequestMethod.GET)
    public Map<String,Object> statisticCityGlassWeight(String address){
//        Rmap<Map<String, Object>> map = etruckhospitalService.statisticCityGlassWeight(address);
        Map<String,Object> map = new HashMap<>();
        List<Map<String,Object>> list = new ArrayList();
        if(StringUtils.isBlank(address)){
            map.put("status",240);
            map.put("message","参数不全");
            return map;
        }
        Rmap<Esupervisionunit> supervisionunitMap = esupervisionunitService.selectBySql("level = '市' "+" and pid = (select id from esupervisionunit where address = '"+ address+"' )");
        if(null != supervisionunitMap.getList()){
            for(Esupervisionunit supervisionunit : supervisionunitMap.getList()){
                Rmap<Integer> glassweight = etruckhospitalService.cityGlassWeight(supervisionunit.getAddress());
                if(null != glassweight.getObject()){
                    map.put("status",260);
                    Map<String,Object> staticMap = new HashMap<>();
                    staticMap.put("name",supervisionunit.getAddress());
                    staticMap.put("value",glassweight.getObject());
                    list.add(staticMap);
//                    map.put(supervisionunit.getAddress(),glassweight.getObject());
                }
            }
        }else{
            map.put("status",240);
            if(null == supervisionunitMap.getMessage()){
                map.put("message","暂无市信息");
            }else{
                map.put("message","查询市信息异常");
            }
        }
        if(!map.containsKey("list")){
            map.put("list",list);
        }
        if(!map.containsKey("status")){
            map.put("status",240);
        }
        return map;
    }

    /**
     * @Auther: Barry
     * @Date: 2020/4/20
     * @Param:
     * @Description: 统计市下的区 塑料重量
     */
    @AuthToken
    @RequestMapping(value = "/statisticPlatPlasticWeight", method = RequestMethod.GET)
    public Rmap<Map<String, Object>> statisticPlatPlasticWeight(String address){
        if(address.equals("北京") || address.equals("天津") || address.equals("重庆") || address.equals("上海")){
            address = address+"市";
        }
        Rmap<Map<String, Object>> map = etruckhospitalService.statisticPlatPlasticWeight(address);
        if(null != map.getList()){
            map.setStatus(260);
        }else{
            map.setStatus(240);
        }
        return map;
    }

    /**
     * @Auther: Barry
     * @Date: 2020/4/20
     * @Param:
     * @Description: 统计市下的区 玻璃重量
     */
    @AuthToken
    @RequestMapping(value = "/statisticPlatGlassWeight", method = RequestMethod.GET)
    public Rmap<Map<String, Object>> statisticPlatGlassWeight(String address){
        if(address.equals("北京") || address.equals("天津") || address.equals("重庆") || address.equals("上海")){
            address = address+"市";
        }
        Rmap<Map<String, Object>> map = etruckhospitalService.statisticPlatGlassWeight(address);
        if(null != map.getList()){
            map.setStatus(260);
        }else{
            map.setStatus(240);
        }
        return map;
    }

    /**
     * @Auther: Barry
     * @Date: 2020/4/20
     * @Param:
     * @Description: 统计区下的医院  塑料重量
     */
    @AuthToken
    @RequestMapping(value = "/statisticHospitalPlasticWeight", method = RequestMethod.GET)
    public Rmap<Map<String, Object>> statisticHospitalPlasticWeight(String address, String paddress){
        if(paddress.equals("北京") || paddress.equals("天津") || paddress.equals("重庆") || paddress.equals("上海")){
            paddress = paddress+"市";
        }
        Rmap<Map<String, Object>> map = etruckhospitalService.statisticHospitalPlasticWeight(address, paddress);
        if(null != map.getList()){
            map.setStatus(260);
        }else{
            map.setStatus(240);
        }
        return map;
    }

    /**
     * @Auther: Barry
     * @Date: 2020/4/20
     * @Param:
     * @Description: 统计区下的医院 玻璃重量
     */
    @AuthToken
    @RequestMapping(value = "/statisticHospitalGlassWeight", method = RequestMethod.GET)
    public Rmap<Map<String, Object>> statisticHospitalGlassWeight(String address, String paddress){
        if(paddress.equals("北京") || paddress.equals("天津") || paddress.equals("重庆") || paddress.equals("上海")){
            paddress = paddress+"市";
        }
        Rmap<Map<String, Object>> map = etruckhospitalService.statisticHospitalGlassWeight(address, paddress);
        if(null != map.getList()){
            map.setStatus(260);
        }else{
            map.setStatus(240);
        }
        return map;
    }

    /**
     * @Auther: Barry
     * @Date: 2020/4/26
     * @Param: hospitalid 医院id
     * @Description: 某医院 玻璃重量
     */
    @AuthToken
    @RequestMapping(value = "/hospitalGlassWeight", method = RequestMethod.GET)
    public Rmap<Integer> hospitalGlassWeight(Integer hospitalid){
        Rmap<Integer> map = etruckhospitalService.hospitalGlassWeight(hospitalid);
        if(null != map.getObject()){
            map.setStatus(260);
        }else{
            map.setStatus(240);
        }
        return map;
    }

    /**
     * @Auther: Barry
     * @Date: 2020/4/26
     * @Param: hospitalid 医院id
     * @Description: 某医院 塑料重量
     */
    @AuthToken
    @RequestMapping(value = "/hospitalPlasticWeight", method = RequestMethod.GET)
    public Rmap<Integer> hospitalPlasticWeight(Integer hospitalid){
        Rmap<Integer> map = etruckhospitalService.hospitalPlasticWeight(hospitalid);
        if(null != map.getObject()){
            map.setStatus(260);
        }else{
            map.setStatus(240);
        }
        return map;
    }

    //查询回收利用企业某一医院交接记录
    @AuthToken
    @RequestMapping(value = "/truckhospitalList", method = RequestMethod.GET)
    public Rmap<Map<String, Object>> truckhospitalList(Integer page, Integer rows, Integer hospitalid, Integer treatmentplantid, Integer transportcompanyid, String status, Date begintime, Date endtime){
        Rmap<Map<String, Object>> map = new Rmap<Map<String, Object>>();
        StringBuilder sql = new StringBuilder();
        if(null == hospitalid || null == treatmentplantid){
            map.setStatus(240);
            map.setMessage("参数不全");
            return map;
        }
        sql.append("t1.hospitalid = "+hospitalid);
       /* sql.append(" and t1.hospitalid = t3.id");
        sql.append(" and t1.submituserid = t4.id");
        sql.append(" and t1.confirmuserid = t5.id");
        sql.append(" and t1.truckid = t2.id");
        sql.append(" and t2.vehicleid = t6.id");
        sql.append(" and t2.transportcompanyid = t7.id");*/
        if(null != transportcompanyid){
            sql.append(" and t7.id = "+transportcompanyid);
        }else{
            sql.append(" and t7.id in ( select id from etransportcompany where treatmentplantid = "+treatmentplantid+")");
        }
        if (StringUtils.isNotBlank(status)) {
            sql.append(" and t1.status = '").append(status).append("'");
        }
        if (null != begintime && null != endtime) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            sql.append(" and t1.time >= '").append(format.format(begintime)).append("' and t1.time <= '").append(format.format(endtime)).append("'");
        }
        map = etruckhospitalService.truckhospitalList(page, rows, "t1.id", "desc", sql.toString());
        if (null != map.getList()) {
            map.setStatus(260);
        } else {
            map.setStatus(240);
        }
        return map;
    }

    //查询监管单位某一医院交接记录
//    @AuthToken
    @RequestMapping(value = "/oneTruckhospitalList", method = RequestMethod.GET)
    public Rmap<Map<String, Object>> oneTruckhospitalList(Integer page, Integer rows, Integer hospitalid, String status, Date begintime, Date endtime){
        Rmap<Map<String, Object>> map = new Rmap<Map<String, Object>>();
        StringBuilder sql = new StringBuilder();
        if(null == hospitalid){
            map.setStatus(240);
            map.setMessage("参数不全");
            return map;
        }
        sql.append("t1.hospitalid = "+hospitalid);
      /*  sql.append(" and t1.hospitalid = t3.id");
        sql.append(" and t1.submituserid = t4.id");
        sql.append(" and t1.confirmuserid = t5.id");
        sql.append(" and t1.truckid = t2.id");
        sql.append(" and t2.vehicleid = t6.id");
        sql.append(" and t2.transportcompanyid = t7.id");*/
        if (StringUtils.isNotBlank(status)) {
            sql.append(" and t1.status = '").append(status).append("'");
        }
        if (null != begintime && null != endtime) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            sql.append(" and t1.time >= '").append(format.format(begintime)).append("' and t1.time <= '").append(format.format(endtime)).append("'");
        }
        map = etruckhospitalService.truckhospitalList(page, rows, "t1.id", "desc", sql.toString());
        if (null != map.getList()) {
            map.setStatus(260);
        } else {
            map.setStatus(240);
        }
        return map;
    }

    //回收利用企业 某一医院交接记录导出  医院id 回收利用企业id、时间必须传
    @RequestMapping(value = "/exportList")
    public void exportList(Integer hospitalid, Integer treatmentplantid, Integer transportcompanyid, Date begintime, Date endtime, HttpServletResponse response){
        Rmap<Map<String, Object>> map = new Rmap<Map<String, Object>>();
        StringBuilder sql = new StringBuilder();
        sql.append("t1.hospitalid = "+hospitalid);
      /*  sql.append(" and t1.hospitalid = t3.id");
        sql.append(" and t1.submituserid = t4.id");
        sql.append(" and t1.confirmuserid = t5.id");
        sql.append(" and t1.truckid = t2.id");
        sql.append(" and t2.vehicleid = t6.id");
        sql.append(" and t2.transportcompanyid = t7.id");*/
        if(null != transportcompanyid){
            sql.append(" and t7.id = "+transportcompanyid);
        }else{
            sql.append(" and t7.id in ( select id from etransportcompany where treatmentplantid = "+treatmentplantid+")");
        }
        sql.append(" and t1.status = '已审核'");
        if (null != begintime && null != endtime) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            sql.append(" and t1.time >= '").append(format.format(begintime)).append("' and t1.time <= '").append(format.format(endtime)).append("'");
        }
        sql.append(" order by t1.id desc");
        map = etruckhospitalService.truckhospitalExport(sql.toString());
        try {
            String fileName = "交接记录" + ".xls";
            String[] titles = TITLES.split(",");
            String[] keys = KEYS.split(",");
            ExcelExport.setResponseHeader(response, fileName);
            OutputStream out = response.getOutputStream();
            if(null != map.getList()){
                ExcelExport.export(out, titles, keys, map.getList());
            }else{
                ExcelExport.export(out, titles, keys, new ArrayList<Map<String, Object>>());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //监管单位 某一医院交接记录 医院id  时间必须传
    @RequestMapping(value = "/oneExportList")
    public void oneExportList(Integer hospitalid, Date begintime, Date endtime, HttpServletResponse response){
        Rmap<Map<String, Object>> map = new Rmap<Map<String, Object>>();
        StringBuilder sql = new StringBuilder();
        sql.append("t1.hospitalid = "+hospitalid);
      /*  sql.append(" and t1.hospitalid = t3.id");
        sql.append(" and t1.submituserid = t4.id");
        sql.append(" and t1.confirmuserid = t5.id");
        sql.append(" and t1.truckid = t2.id");
        sql.append(" and t2.vehicleid = t6.id");
        sql.append(" and t2.transportcompanyid = t7.id");
        sql.append(" and t1.status = '已审核'");*/
        if (null != begintime && null != endtime) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            sql.append(" and t1.time >= '").append(format.format(begintime)).append("' and t1.time <= '").append(format.format(endtime)).append("'");
        }
        sql.append(" order by t1.id desc");
        map = etruckhospitalService.truckhospitalExport(sql.toString());
        try {
            String fileName = "交接记录" + ".xls";
            String[] titles = TITLES.split(",");
            String[] keys = KEYS.split(",");
            ExcelExport.setResponseHeader(response, fileName);
            OutputStream out = response.getOutputStream();
            if(null != map.getList()){
                ExcelExport.export(out, titles, keys, map.getList());
            }else{
                ExcelExport.export(out, titles, keys, new ArrayList<Map<String, Object>>());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     * @Date 2020/9/7 11:04
     * @Description 回收利用企业查看医院结交记录
     * @Param treatmentplantid 回收利用企业id，vehicleid 车辆id, treatmentplantid 回收利用企业id, transporterid 运输人id,trucktype车次类型
     * @Return
     */
    @AuthToken
    @RequestMapping(value = "/treatmentplantTruckhospitalList", method = RequestMethod.GET)
    public Rmap<Map<String, Object>> treatmentplantTruckhospitalList(Integer page, Integer rows, Integer treatmentplantid, Integer transportcompanyid,Integer vehicleid, Integer transporterid, String trucktype, Date begintime, Date endtime){
        Rmap<Map<String, Object>> map = new Rmap<Map<String, Object>>();
        StringBuilder sql = new StringBuilder();
        if(null == treatmentplantid){
            map.setStatus(240);
            map.setMessage("参数不全");
            return map;
        }
        sql.append("t1.status = '"+"已审核'");
        if(null != transportcompanyid){
            sql.append(" and t7.id = "+transportcompanyid);
        }else{
            sql.append(" and t7.id in ( select id from etransportcompany where treatmentplantid = "+treatmentplantid+")");
        }
        if(null != vehicleid){
            sql.append(" and t6.id = "+vehicleid);
        }
        if(null != transporterid){
            sql.append(" and  t2.userid = "+transporterid);
        }
        if (StringUtils.isNotBlank(trucktype)) {
            sql.append(" and t2.type = '").append(trucktype).append("'");
        }
        if (null != begintime && null != endtime) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            sql.append(" and t1.time >= '").append(format.format(begintime)).append("' and t1.time <= '").append(format.format(endtime)).append("'");
        }
        map = etruckhospitalService.treatmentplantTruckhospitalList(page, rows, "t1.id", "desc", sql.toString());
        if (null != map.getList()) {
            map.setStatus(260);
            //sum(塑料袋数、塑料重量、玻璃袋数、玻璃重量)
            Rmap<Map<String, Object>> sumMap = etruckhospitalService.treatmentplantTruckhospitalSum(sql.toString());
            map.getInfo().put("sum", sumMap.getObject());
        } else {
            map.setStatus(240);
        }
        return map;
    }

    /*
     * @Date 2020/9/7 11:04
     * @Description 回收利用企业查看医院结交记录导出
     * @Param treatmentplantid 回收利用企业id，vehicleid 车辆id, treatmentplantid 回收利用企业id, transporterid 运输人id,trucktype车次类型
     * @Return
     */
    @RequestMapping(value = "/treatmentplantTruckhospitalExport")
    public void treatmentplantTruckhospitalExport(Integer treatmentplantid, Integer transportcompanyid,Integer vehicleid, Integer transporterid, String trucktype, Date begintime, Date endtime, HttpServletResponse response){
        Rmap<Map<String, Object>> map = new Rmap<Map<String, Object>>();
        StringBuilder sql = new StringBuilder();
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
        sql.append("t1.status = '"+"已审核'");
        if(null != transportcompanyid){
            sql.append(" and t7.id = "+transportcompanyid);
        }else{
            sql.append(" and t7.id in ( select id from etransportcompany where treatmentplantid = "+treatmentplantid+")");
        }
        if(null != vehicleid){
            sql.append(" and t6.id = "+vehicleid);
        }
        if(null != transporterid){
            sql.append(" and  t2.userid = "+transporterid);
        }
        if (StringUtils.isNotBlank(trucktype)) {
            sql.append(" and t2.type = '").append(trucktype).append("'");
        }
        if (null != begintime && null != endtime) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            sql.append(" and t1.time >= '").append(format.format(begintime)).append("' and t1.time <= '").append(format.format(endtime)).append("'");
        }
        sql.append(" order by t1.id desc");
        map = etruckhospitalService.treatmentplantTruckhospitalExport(sql.toString());
        Rmap<Map<String, Object>> sumMap = etruckhospitalService.treatmentplantTruckhospitalSum(sql.toString());
        List<Map<String, Object>> sumMapList = new ArrayList<>();
        sumMapList.add(sumMap.getObject());
        try {
            String fileName = "交接记录" + ".xls";
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


}