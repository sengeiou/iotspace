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
import java.util.*;

@RestController
@RequestMapping("/treatmentplant")
public class EtreatmentplantAction  extends BaseController{
    @Resource
    private EtreatmentplantService etreatmentplantService;
    @Resource
    private EhospitalService ehospitalService;
    @Resource
    private EtransportcompanyService etransportcompanyService;
    @Resource
    private EvehicleService evehicleService;
    @Resource
    private EtruckhospitalService etruckhospitalService;
    @Resource
    private EtrucktreatmentplantService etrucktreatmentplantService;

    //数据月份补全
    private static final String montharray[] = {"01","02","03","04","05","06","07","08","09","10","11","12"};


    /**
     * 首页 回收企业展示
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping(value = "/homeSelectByPage", method = RequestMethod.GET)
    public Rmap<Etreatmentplant> homeSelectByPage(Integer page, Integer rows, String province,String batch){
        Rmap<Etreatmentplant> map = new Rmap<Etreatmentplant>();
        StringBuilder sql =new StringBuilder();
        if(StringUtils.isNotBlank(province)){
            sql.append("province = '"+province+"'");
        }
        if(StringUtils.isNotBlank(batch)){
            if(sql.length() == 0){
               sql.append("batch = '"+batch+"'");
            }else{
                sql.append(" and batch = '"+batch+"'");
            }
        }
        map = etreatmentplantService.selectByPage(page, rows, "province", "desc", sql.toString() );
        if (null != map.getList()) {
            map.setStatus(260);
        }else{
            map.setStatus(240);
        }
        return map;
    }



    /**
     * @Auther: Barry
     * @Date: 2020/6/2
     * @Param: treatmentplantid
     * @Description: 某一处理厂 医院总量 三级医院数量 运输公司数量 车辆数量 （当月回收 玻璃/塑料重量 ）（总玻璃/塑料重量）
     */
    @AuthToken
    @RequestMapping(value = "/statistics", method = RequestMethod.GET)
    public Map<String,Object> statistics(Integer treatmentplantid){
        Map<String, Object> map = new HashMap<>();
        if(null == treatmentplantid){
            map.put("status",240);
            map.put("message","参数不全");
            return map;
        }
        //医院总数
        Rmap<Integer> hospitalNumMap = ehospitalService.hospitalNumBySql("transportcompanyid in ( select id from etransportcompany where treatmentplantid = "+treatmentplantid+")");
        if(null != hospitalNumMap.getObject()){
            map.put("status",260);
            map.put("hospitalcount",hospitalNumMap.getObject());
        }else{
            map.put("hospitalcount",null);
        }
        //三级医院总数
        hospitalNumMap = ehospitalService.hospitalNumBySql("transportcompanyid in ( select id from etransportcompany where treatmentplantid = "+treatmentplantid+")"+" and level = '三级医院'");
        if(null != hospitalNumMap.getObject()){
            map.put("status",260);
            map.put("levelhospitalcount",hospitalNumMap.getObject());
        }else{
            map.put("levelhospitalcount",null);
        }
        //运输公司个数
        Rmap<Integer> transportcompanyNumMap = etransportcompanyService.countBySql("treatmentplantid = "+treatmentplantid);
        if(null != transportcompanyNumMap.getObject()){
            map.put("status",260);
            map.put("transportcompanycount",transportcompanyNumMap.getObject());
        }else{
            map.put("transportcompanycount",null);
        }
        //车辆数量
        Rmap<Integer> vehicleNumMap = evehicleService.vehicleNumBySql("transportcompanyid in ( select id from etransportcompany where treatmentplantid = "+treatmentplantid+")");
        if(null != vehicleNumMap.getObject()){
            map.put("status",260);
            map.put("vehiclecount",vehicleNumMap.getObject());
        }else{
            map.put("vehiclecount",null);
        }
        //当月回收玻璃/塑料 重量
       /* StringBuilder sql =new StringBuilder();
        sql.append("t1.id = t2.truckid");
        sql.append(" and t1.type = '医疗机构-回收物流' ");
        sql.append(" and t1.transportcompanyid in ( select id from etransportcompany where treatmentplantid = "+treatmentplantid+")");
        sql.append(" and t2.status = '已审核'");
        sql.append(" and DATE_FORMAT(t2.time,'%Y%m') = DATE_FORMAT(CURDATE(),'%Y%m')");
        //当月回收玻璃重量
        Rmap<Integer> thisMonthSumgGlassweightMap = etruckhospitalService.sumGlassweightBySql(sql.toString());*/
        StringBuilder sql =new StringBuilder();
        sql.append("treatmentplantid = ").append(treatmentplantid);
        sql.append(" and status = '已审核' ");
        //玻璃总重量
        Rmap<Integer> sumgGlassweightMap = etrucktreatmentplantService.sumGlassweightBySql(sql.toString());
        if(null != sumgGlassweightMap.getObject()){
            map.put("status",260);
            map.put("glassweight",sumgGlassweightMap.getObject());
        }else{
            map.put("glassweight",null);
        }
        //塑料总重量
        Rmap<Integer> sumgplasticweightMap = etrucktreatmentplantService.sumPlasticweightBySql(sql.toString());
        if(null != sumgplasticweightMap.getObject()){
            map.put("status",260);
            map.put("plasticweight",sumgplasticweightMap.getObject());
        }else{
            map.put("plasticweight",null);
        }
        //当月 回收玻璃 塑料重量
        sql.append(" and DATE_FORMAT(time,'%Y%m') = DATE_FORMAT(CURDATE(),'%Y%m')");
        Rmap<Integer> thisMonthSumgGlassweightMap = etrucktreatmentplantService.sumGlassweightBySql(sql.toString());
        if(null != thisMonthSumgGlassweightMap.getObject()){
            map.put("status",260);
            map.put("thismonthglassweight",thisMonthSumgGlassweightMap.getObject());
        }else{
            map.put("thismonthglassweight",null);
        }
        //当月回收塑料重量
        /*Rmap<Integer> thisMonthSumPlasticMap = etruckhospitalService.sumPlasticweightBySql(sql.toString());*/
        Rmap<Integer> thisMonthSumPlasticMap = etrucktreatmentplantService.sumPlasticweightBySql(sql.toString());
        if(null != thisMonthSumPlasticMap.getObject()){
            map.put("status",260);
            map.put("thismonthplasticweight",thisMonthSumPlasticMap.getObject());
        }else{
            map.put("thismonthplasticweight",null);
        }
        if(!map.containsKey("status")){
            map.put("status",240);
        }
        return map;
    }

    /**
     * @Auther: Barry
     * @Date: 2020/6/2
     * @Param: treatmentplantid
     * @Description: 玻璃总重量 按运输公司分组
     */
    @AuthToken
    @RequestMapping(value = "/glassweightSumGroupTransportcompany", method = RequestMethod.GET)
    public Map<String,Object> glassweightSumGroupTransportcompany(Integer treatmentplantid){
        Map<String,Object> map = new HashMap<>();
        if(null == treatmentplantid){
            map.put("status",240);
            map.put("message","参数不全");
            return map;
        }
        Rmap<Etransportcompany> transportcompanyMap = etransportcompanyService.selectBySql("treatmentplantid = "+treatmentplantid);
        if(null == transportcompanyMap.getList()){
            map.put("status",240);
            if(null == transportcompanyMap.getMessage()){
                map.put("message","未查询到运输公司信息");
            }else{
                map.put("message","未查询到运输公司信息异常");
            }
            return map;
        }
        StringBuilder sql =new StringBuilder();
        sql.append("t1.id = t2.truckid");
        sql.append(" and t1.type = '医疗机构-回收物流' ");
        sql.append(" and t1.transportcompanyid = t3.id");
        sql.append(" and t2.status = '已审核'");
        sql.append(" and t3.treatmentplantid = "+treatmentplantid);
        sql.append(" group by t3.name");
        Rmap<Map<String,Object>> glassweightMap = etruckhospitalService.sumGlassweightSql(sql.toString());
        if(null != glassweightMap.getList()){
            //数据补全
            List<Map<String, Object>> list = glassweightMap.getList();
            add(list, transportcompanyMap.getList());
            map.put("glassweight", list);
            map.put("status", 260);
        }else{
            if(null == glassweightMap.getMessage()){ //没查到数据进行补全
                List<Map<String, Object>> list = new ArrayList<>();
                add(list, transportcompanyMap.getList());
                map.put("glassweight", list);
                map.put("status", 260);
            }else{
                map.put("status", 240);
                map.put("message","服务异常");
            }
        }
        return map;
    }

    /**
     * @Auther: Barry
     * @Date: 2020/6/2
     * @Param: treatmentplantid
     * @Description: 塑料总重量 按运输公司分组
     */
    @AuthToken
    @RequestMapping(value = "/plasticweightSumGroupTransportcompany", method = RequestMethod.GET)
    public Map<String,Object> plasticweightSumGroupTransportcompany(Integer treatmentplantid){
        Map<String,Object> map = new HashMap<>();
        if(null == treatmentplantid){
            map.put("status",240);
            map.put("message","参数不全");
            return map;
        }
        Rmap<Etransportcompany> transportcompanyMap = etransportcompanyService.selectBySql("treatmentplantid = "+treatmentplantid);
        if(null == transportcompanyMap.getList()){
            map.put("status",240);
            if(null == transportcompanyMap.getMessage()){
                map.put("message","未查询到运输公司信息");
            }else{
                map.put("message","未查询到运输公司信息异常");
            }
            return map;
        }
        StringBuilder sql =new StringBuilder();
        sql.append("t1.id = t2.truckid");
        sql.append(" and t1.type = '医疗机构-回收物流' ");
        sql.append(" and t1.transportcompanyid = t3.id");
        sql.append(" and t2.status = '已审核'");
        sql.append(" and t3.treatmentplantid = "+treatmentplantid);
        sql.append(" group by t3.name");
        Rmap<Map<String,Object>> plasticMap = etruckhospitalService.sumPlasticweightSql(sql.toString());
        if(null != plasticMap.getList()){
            //数据补全
            List<Map<String, Object>> list = plasticMap.getList();
            add(list, transportcompanyMap.getList());
            map.put("plasticweight", list);
            map.put("status", 260);
        }else{
            if(null == plasticMap.getMessage()){ //没查到数据进行补全
                List<Map<String, Object>> list = new ArrayList<>();
                add(list, transportcompanyMap.getList());
                map.put("plasticweight", list);
                map.put("status", 260);
            }else{
                map.put("status", 240);
                map.put("message","服务异常");
            }
        }
        return map;
    }

    /**
     * @Auther: Barry
     * @Date: 2020/4/21
     * @Param: list
     * @Description: 数据月份补全
     */
    public List<Map<String, Object>> addMonth(List<Map<String, Object>> list) {
        /* System.out.println(list.toString());*/
        for (String str : montharray) {
            if (!list.toString().contains("name="+str)) {
                Map<String, Object> map = new HashMap<>();
                map.put("name", str);
                map.put("value", 0);
                list.add(map);
            }
        }
        return list;
    }

    /**
     * @Auther: Barry
     * @Date: 2020/4/21
     * @Param: list
     * @Description: 排序 升序
     */
    public void sort(List<Map<String, Object>> list) {
        Collections.sort(list, new Comparator<Map<String, Object>>() {
            @Override
            public int compare(Map<String, Object> m1, Map<String, Object> m2) {
                String name1 = (String) m1.get("name");//name1是从你list里面拿出来的一个
                String name2 = (String) m2.get("name");
                return name1.compareTo(name2);
            }
        });
    }
    /**
     * @Auther: Barry
     * @Date: 2020/6/2
     * @Param: list
     * @Description: 数据补全
     */
    public List<Map<String, Object>> add(List<Map<String, Object>> list, List<Etransportcompany> transportcompanyList) {
        /* System.out.println(list.toString());*/
        for (Etransportcompany transportcompany : transportcompanyList) {
            if (!list.toString().contains("name=" + transportcompany.getName())) {
                Map<String, Object> map = new HashMap<>();
                map.put("name", transportcompany.getName());
                map.put("value", 0);
                list.add(map);
            }
        }
        return list;
    }

    /**
     * @Auther: Barry
     * @Date: 2020/6/2
     * @Param: treatmentplantid
     * @Description: 某一处理厂下的医院
     */
    @AuthToken
    @RequestMapping(value = "/hospitalInfo", method = RequestMethod.GET)
    public Rmap<Ehospital> hospitalInfo(Integer treatmentplantid){
        Rmap<Ehospital> map = new Rmap<Ehospital>();
        if(null == treatmentplantid){
            map.setStatus(240);
            map.setMessage("参数不全");
            return map;
        }
        map = ehospitalService.selectBySql("transportcompanyid in ( select id from etransportcompany where treatmentplantid = "+treatmentplantid+")");
        if(null != map.getList()){
            map.setStatus(260);
        }else{
            map.setStatus(240);
        }
        return map;
    }

    /**
     * @Auther: Barry
     * @Date: 2020/6/3
     * @Param: treatmentplantid
     * @Param: flag 0 今年 1去年 2前年
     * @Description: 年度重量/数量统计 按月分组
     */
    @AuthToken
    @RequestMapping(value = "/yearStatistics", method = RequestMethod.GET)
    public Map<String, Object> yearStatistics(Integer treatmentplantid,String flag){
        Map<String, Object> map = new HashMap<>();
        if(null == treatmentplantid || StringUtils.isBlank(flag)){
            map.put("status",240);
            map.put("message","参数不全");
            return map;
        }
        if(flag.equals("0") || flag.equals("1") || flag.equals("2")){
            StringBuilder sql =new StringBuilder();
            sql.append("treatmentplantid = "+treatmentplantid);
            sql.append(" and year(time)=year(date_sub(now(),interval "+Integer.valueOf(flag)).append(" year))");
            sql.append(" group by DATE_FORMAT(time,'%m')");
            //玻璃重量
            Rmap<Map<String, Object>> glassweightMap = etrucktreatmentplantService.yearGlassweightGroupMonthSql(sql.toString());
            if(null != glassweightMap.getList()){
                map.put("status",260);
                List<Map<String, Object>> list = glassweightMap.getList();
                addMonth(list);
                sort(list);
                map.put("glassweight", list);
            }else{
                map.put("glassweight", null);
            }
            //塑料重量
            Rmap<Map<String, Object>> plasticweightMap = etrucktreatmentplantService.yearPlasticweightGroupMonthSql(sql.toString());
            if(null != plasticweightMap.getList()){
                map.put("status",260);
                List<Map<String, Object>> list = plasticweightMap.getList();
                addMonth(list);
                sort(list);
                map.put("plasticweight", list);
            }else{
                map.put("plasticweight", null);
            }
            //玻璃数量
            Rmap<Map<String, Object>> glassnumberMap = etrucktreatmentplantService.yearGlassnumberGroupMonthSql(sql.toString());
            if(null != glassnumberMap.getList()){
                map.put("status",260);
                List<Map<String, Object>> list = glassnumberMap.getList();
                addMonth(list);
                sort(list);
                map.put("glassnumber", list);
            }else{
                map.put("glassnumber", null);
            }
            //塑料数量
            Rmap<Map<String, Object>> plasticnumberMap = etrucktreatmentplantService.yearPlasticnumberGroupMonthSql(sql.toString());
            if(null != plasticnumberMap.getList()){
                map.put("status",260);
                List<Map<String, Object>> list = plasticnumberMap.getList();
                addMonth(list);
                sort(list);
                map.put("plasticnumber", list);
            }else{
                map.put("plasticnumber", null);
            }
        }else{
            map.put("status",240);
            map.put("message","参数错误");
        }
        if(!map.containsKey("status")){
            map.put("status",240);
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
    public Rmap<Etreatmentplant> selectAll(){
        Rmap<Etreatmentplant> map = etreatmentplantService.selectAll();
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
     * @Param: name
     * @Param: address
     * @Param: leadername
     * @Param: leaderphone
     * @Description: 分页查询
     */
    @AuthToken
    @RequestMapping(value = "/selectByPage", method = RequestMethod.GET)
    public Rmap<Etreatmentplant> selectByPage(Integer page, Integer rows, String name, String address, String leadername, String leaderphone,String batch){
        StringBuilder sql =new StringBuilder();
        if(StringUtils.isNotBlank(name)){
            sql.append("name like ").append("'%25").append(name).append("%25'");
        }
        if(StringUtils.isNotBlank(address)){
            if(sql.length() == 0){
                sql.append("address like ").append("'%25").append(address).append("%25'");
            }else{
                sql.append(" and address like ").append("'%25").append(address).append("%25'");
            }
        }
        if(StringUtils.isNotBlank(leadername)){
            if(sql.length() == 0){
                sql.append("leadername like ").append("'%25").append(leadername).append("%25'");
            }else{
                sql.append(" and leadername like ").append("'%25").append(leadername).append("%25'");
            }
        }
        if(StringUtils.isNotBlank(leaderphone)){
            if(sql.length() == 0){
                sql.append("leaderphone like ").append("'%25").append(leaderphone).append("%25'");
            }else{
                sql.append(" and leaderphone like ").append("'%25").append(leaderphone).append("%25'");
            }
        }
        if(StringUtils.isNotBlank(batch)){
            if(sql.length() == 0){
                sql.append("batch = '"+batch+"'");
            }else{
                sql.append(" and batch = '"+batch+"'");
            }
        }
        Rmap<Etreatmentplant> map = etreatmentplantService.selectByPage(page, rows, "id", "desc", sql.toString());
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
    public Rmap<Etreatmentplant> selectByPrimaryKey(Integer id){
        Rmap<Etreatmentplant> map = etreatmentplantService.selectByPrimaryKey(id);
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
     * @Description: 添加
     */
    @AuthToken
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public Rmap<Etreatmentplant> insert(Etreatmentplant record) {
        Rmap<Etreatmentplant> map = new Rmap<Etreatmentplant>();
        if(isValidate(record.getName(), 0)){
            Rmap<Etreatmentplant> rmap = etreatmentplantService.insert(record);
            if(null != rmap.getObject()){
                map.setStatus(260);
            }else{
                map.setStatus(240);
                if(null == rmap.getMessage()){
                    map.setMessage("添加处理厂失败");
                }else{
                    map.setMessage(rmap.getMessage());
                }
            }
        }else{
            map.setStatus(240);
            map.setMessage("该处理厂已存在");
        }
        return map;
    }

    /**
     * @Auther: Barry
     * @Date: 2020/3/25
     * @Param: record
     * @Description: 更新
     */
    @AuthToken
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public Rmap<Etreatmentplant> update(Etreatmentplant record) {
        Rmap<Etreatmentplant> map = new Rmap<Etreatmentplant>();
        if(isValidate(record.getName(), record.getId())){
            Rmap<Etreatmentplant> rmap = etreatmentplantService.update(record);
            if(null != rmap.getObject()){
                map.setStatus(260);
            }else{
                map.setStatus(240);
                if(null == rmap.getMessage()){
                    map.setMessage("修改处理厂失败");
                }else{
                    map.setMessage(rmap.getMessage());
                }
            }
        }else{
            map.setStatus(240);
            map.setMessage("该处理厂已存在");
        }
        return map;
    }

    /**
     * @Auther: Barry
     * @Date: 2020/3/25
     * @Param: id
     * @Description: 删除
     */
    @AuthToken
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public Rmap<Etreatmentplant> delete(Integer id) {
        Rmap<Etreatmentplant> map = etreatmentplantService.delete(id);
        if(null != map.getObject()){
            map.setStatus(260);
        }else {
            map.setStatus(240);
            if(null == map.getMessage()) {
                map.setMessage("删除处理厂失败");
            }
        }
        return map;
    }

    /**
     * @Auther: Barry
     * @Date: 2020/3/25
     * @Param: ids
     * @Description: 批量删除
     */
    @AuthToken
    @RequestMapping(value = "/deletes", method = RequestMethod.DELETE)
    public Rmap<Etreatmentplant> deletes(@RequestBody Integer[] ids){
        String strIds =  ArrayUtils.toString(ids, ",").replace('{', '(').replace('}', ')');
        Rmap<Etreatmentplant> map = etreatmentplantService.deletes(strIds);
        if(null != map.getList()){
            map.setStatus(260);
        }else{
            map.setStatus(240);
            if(null == map.getMessage()){
                map.setMessage("删除处理厂失败");
            }
        }
        return map;
    }

    /**
     * 批次列表
     * @return
     */
    @AuthToken
    @RequestMapping(value = "/batchList", method = RequestMethod.GET)
    public Rmap<Select> batchList() {
        Rmap<Select> map = new Rmap<Select>();
        List<Select> list = new ArrayList<Select>();
        Select select = new Select();
        select.setText("第一批");
        select.setValue("第一批");
        list.add(select);
        select = new Select();
        select.setText("第二批");
        select.setValue("第二批");
        list.add(select);
        select = new Select();
        select.setText("第三批");
        select.setValue("第三批");
        list.add(select);
        select = new Select();
        select.setText("第四批");
        select.setValue("第四批");
        list.add(select);
        map.setStatus(260);
        map.setList(list);
        return map;
    }

    /**
     * 更新省市区
     * @param record
     * @return
     */
    @AuthToken
    @RequestMapping(value = "/updateProvince", method = RequestMethod.PUT)
    public Rmap<Etreatmentplant> updateProvince(Etreatmentplant record) {
        Rmap<Etreatmentplant> map = new Rmap<Etreatmentplant>();
        if(null == record.getId()){
            map.setStatus(240);
            map.setMessage("参数不全");
            return map;
        }
        Rmap<Etreatmentplant> rmap = etreatmentplantService.updateProvince(record);
        if (null != rmap.getObject()) {
            map.setStatus(260);
        } else {
            map.setStatus(240);
            if (null == rmap.getMessage()) {
                map.setMessage("修改处理厂失败");
            } else {
                map.setMessage(rmap.getMessage());
            }
        }
        return map;
    }

    /**
     * @Auther: Barry
     * @Date: 2020/3/25
     * @Param: name 处理厂名称
     * @Param: id
     * @Description: 唯一性校验
     */
    public boolean isValidate(String name, Integer id){
        Rmap<Etreatmentplant> map = etreatmentplantService.selectBySql("name = '" + name + "' and id != " + id);
        if(null != map.getList()){
            return false;
        }else {
            if(null == map.getMessage()){
                return true;
            }else{
                return false;
            }
        }
    }

}