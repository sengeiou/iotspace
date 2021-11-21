package com.iot.action;

import com.iot.bean.Ehospital;
import com.iot.bean.Etransportcompany;
import com.iot.bean.Rmap;
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
@RequestMapping("/transportcompany")
public class EtransportcompanyAction extends BaseController{
    @Resource
    private EtransportcompanyService etransportcompanyService;
    @Resource
    private EhospitalService ehospitalService;
    @Resource
    private EvehicleService evehicleService;
    @Resource
    private EtruckhospitalService etruckhospitalService;

    //数据月份补全
    private static final String montharray[] = {"01","02","03","04","05","06","07","08","09","10","11","12"};

    /**
     * @Auther: Barry
     * @Date: 2020/6/1
     * @Param: transportcompanyid
     * @Description: 某一运输公司下的医院
     */
    @AuthToken
    @RequestMapping(value = "/hospitalInfo", method = RequestMethod.GET)
    public Rmap<Ehospital> hospitalInfo(Integer transportcompanyid){
        Rmap<Ehospital> map = new Rmap<Ehospital>();
        if(null == transportcompanyid){
            map.setStatus(240);
            map.setMessage("参数不全");
            return map;
        }
        map = ehospitalService.selectBySql("transportcompanyid = "+transportcompanyid);
        if(null != map.getList()){
            map.setStatus(260);
        }else{
            map.setStatus(240);
        }
        return map;
    }


    /**
     * @Auther: Barry
     * @Date: 2020/6/1
     * @Param: transportcompanyid  运输公司id
     * @Description: 某一运输公司  医院总数；回收车辆总数；塑料回收总重量；玻璃回收总重量；（当月回收情况：玻璃重量，塑料重量）
     */
    @AuthToken
    @RequestMapping(value = "/statistics", method = RequestMethod.GET)
    public Map<String, Object> statistics(Integer transportcompanyid){
        Map<String, Object> map = new HashMap<>();
        if(null == transportcompanyid){
            map.put("status",240);
            map.put("message","参数不全");
            return map;
        }
        //医院总数
        Rmap<Integer> hospitalNumMap = ehospitalService.hospitalNumBySql("transportcompanyid = "+transportcompanyid);
        if(null != hospitalNumMap.getObject()){
            map.put("status",260);
            map.put("hospitalcount",hospitalNumMap.getObject());
        }else{
            map.put("hospitalcount",null);
        }
        //回收车辆总数
        Rmap<Integer> vehicleNumMap = evehicleService.vehicleNumBySql("transportcompanyid = "+transportcompanyid);
        if(null != vehicleNumMap.getObject()){
            map.put("status",260);
            map.put("vehiclecount",vehicleNumMap.getObject());
        }else{
            map.put("vehiclecount",null);
        }
        //玻璃回收总重量
        StringBuilder sql =new StringBuilder();
        sql.append("t1.id = t2.truckid");
        sql.append(" and t1.type = '医疗机构-回收物流' ");
        sql.append(" and t1.transportcompanyid = ").append(transportcompanyid);
        sql.append(" and t2.status = '已审核'");
        Rmap<Integer> sumglassweightMap = etruckhospitalService.sumGlassweightBySql(sql.toString());
        if(null != sumglassweightMap.getObject()){
            map.put("status",260);
            map.put("glassweight",sumglassweightMap.getObject());
        }else{
            map.put("glassweight",null);
        }
        //塑料回收总重量
        Rmap<Integer> sumPlasticweightMap = etruckhospitalService.sumPlasticweightBySql(sql.toString());
        if(null != sumPlasticweightMap.getObject()){
            map.put("status",260);
            map.put("plasticweight",sumPlasticweightMap.getObject());
        }else{
            map.put("plasticweight",null);
        }
        //当月回收玻璃重量
        sql.append(" and DATE_FORMAT(t2.time,'%Y%m') = DATE_FORMAT(CURDATE(),'%Y%m')");
        Rmap<Integer> thisMonthSumgGlassweightMap = etruckhospitalService.sumGlassweightBySql(sql.toString());
        if(null != thisMonthSumgGlassweightMap.getObject()){
            map.put("status",260);
            map.put("thismonthglassweight",thisMonthSumgGlassweightMap.getObject());
        }else{
            map.put("thismonthglassweight",null);
        }
        //当月回收塑料重量
        Rmap<Integer> thisMonthSumPlasticMap = etruckhospitalService.sumPlasticweightBySql(sql.toString());
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
     * @Date: 2020/6/1
     * @Param: transportcompanyid 运输公司id
     * @Param: flag 查询哪一年 0今年 1去年 2前年
     * @Description: 运输公司 某一年玻璃、塑料重量 ，按月分组
     */
    @AuthToken
    @RequestMapping(value = "/yearWeightStatistics", method = RequestMethod.GET)
    public Map<String, Object> yearWeightStatistics(Integer transportcompanyid,String flag){
        Map<String, Object> map = new HashMap<>();
        if(null == transportcompanyid || StringUtils.isBlank(flag)){
            map.put("status",240);
            map.put("message","参数不全");
            return map;
        }
        if(flag.equals("0") || flag.equals("1") || flag.equals("2")){
            StringBuilder sql =new StringBuilder();
            sql.append("t1.id = t2.truckid");
            sql.append(" and t1.type = '医疗机构-回收物流' ");
            sql.append(" and t1.transportcompanyid = ").append(transportcompanyid);
            sql.append(" and t2.status = '已审核'");
            sql.append(" and year(t2.time)=year(date_sub(now(),interval "+Integer.valueOf(flag)).append(" year))");
            sql.append(" group by DATE_FORMAT(t2.time,'%m')");
            //玻璃重量
            Rmap<Map<String, Object>> glassweightMap = etruckhospitalService.yearGlassweightGroupMonthSql(sql.toString());
            if(null != glassweightMap.getList()){
                map.put("status",260);
                List<Map<String, Object>> list = glassweightMap.getList();
                add(list);
                sort(list);
                map.put("glassweight", list);
            }else{
                map.put("glassweight", null);
            }
            //塑料重量
            Rmap<Map<String, Object>> plasticweightMap = etruckhospitalService.yearPlasticweightGroupMonthSql(sql.toString());
            if(null != plasticweightMap.getList()){
                map.put("status",260);
                List<Map<String, Object>> list = plasticweightMap.getList();
                add(list);
                sort(list);
                map.put("plasticweight", list);
            }else{
                map.put("plasticweight", null);
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
     * @Date: 2020/6/1
     * @Param: transportcompanyid 运输公司id
     * @Param: flag 查询哪一年 0今年 1去年 2前年
     * @Description: 运输公司 某一年玻璃、塑料数量 ，按月分组
     */
    @AuthToken
    @RequestMapping(value = "/yearNumberStatistics", method = RequestMethod.GET)
    public Map<String, Object> yearNumberStatistics(Integer transportcompanyid,String flag){
        Map<String, Object> map = new HashMap<>();
        if(null == transportcompanyid || StringUtils.isBlank(flag)){
            map.put("status",240);
            map.put("message","参数不全");
            return map;
        }
        if(flag.equals("0") || flag.equals("1") || flag.equals("2")){
            StringBuilder sql =new StringBuilder();
            sql.append("t1.id = t2.truckid");
            sql.append(" and t1.type = '医疗机构-回收物流' ");
            sql.append(" and t1.transportcompanyid = ").append(transportcompanyid);
            sql.append(" and t2.status = '已审核'");
            sql.append(" and year(t2.time)=year(date_sub(now(),interval "+Integer.valueOf(flag)).append(" year))");
            sql.append(" group by DATE_FORMAT(t2.time,'%m')");
            //玻璃重量
            Rmap<Map<String, Object>> glassnumberMap = etruckhospitalService.yearGlassnumberGroupMonthSql(sql.toString());
            if(null != glassnumberMap.getList()){
                map.put("status",260);
                List<Map<String, Object>> list = glassnumberMap.getList();
                add(list);
                sort(list);
                map.put("glassnumber", list);
            }else{
                map.put("glassnumber", null);
            }
            //塑料重量
            Rmap<Map<String, Object>> plasticnumberMap = etruckhospitalService.yearPlasticnumberGroupMonthSql(sql.toString());
            if(null != plasticnumberMap.getList()){
                map.put("status",260);
                List<Map<String, Object>> list = plasticnumberMap.getList();
                add(list);
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
     * @Date: 2020/4/9
     * @Param:
     * @Description: v查询所有
     */
    @AuthToken
    @RequestMapping(value = "/selectAll", method = RequestMethod.GET)
    public Rmap<Etransportcompany> selectAll(){
        Rmap<Etransportcompany> map = etransportcompanyService.selectAll();
        if (null != map.getList()) {
            map.setStatus(260);
        }else{
            map.setStatus(240);
        }
        return map;
    }

    /**
     * @Auther: Barry
     * @Date: 2020/4/9
     * @Param: id
     * @Description: 主键查询
     */
    @AuthToken
    @RequestMapping(value = "/selectByPrimaryKey", method = RequestMethod.GET)
    public Rmap<Etransportcompany> selectByPrimaryKey(Integer id){
        Rmap<Etransportcompany> map = etransportcompanyService.selectByPrimaryKey(id);
        if(null != map.getObject()){
            map.setStatus(260);
        }else{
            map.setStatus(240);
        }
        return map;
    }

    /**
     * @Auther: Barry
     * @Date: 2020/4/10
     * @Param: page
     * @Param: rows
     * @Param: name
     * @Param: address
     * @Param: leadername
     * @Param: leaderphone
     * @Description: 查询
     */
    @AuthToken
    @RequestMapping(value = "/selectByPage", method = RequestMethod.GET)
    public Rmap<Etransportcompany> selectByPage(Integer page, Integer rows, String name, String address, String leadername, String leaderphone) {
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
        Rmap<Etransportcompany> map = etransportcompanyService.selectByPage(page, rows, "id", "desc", sql.toString());
        if (null != map.getList()) {
            map.setStatus(260);
        }else{
            map.setStatus(240);
        }
        return map;
    }

    /**
     * @Auther: Barry
     * @Date: 2020/4/10
     * @Param: page
     * @Param: rows
     * @Param: name
     * @Param: address
     * @Param: leadername
     * @Param: leaderphone
     * @Description: 查询
     */
    @AuthToken
    @RequestMapping(value = "/selectVByPage", method = RequestMethod.GET)
    public Rmap<Etransportcompany> selectVByPage(Integer page, Integer rows, String name, String address, String leadername, String leaderphone,Integer treatmentplantid) {
        StringBuilder sql =new StringBuilder();
        if(StringUtils.isNotBlank(name)){
            sql.append("name like ").append("'%25").append(name).append("%25'");
        }
        if(null != treatmentplantid){
            if(sql.length() == 0){
                sql.append("treatmentplantid = "+treatmentplantid);
            }else{
                sql.append(" and treatmentplantid = "+treatmentplantid);
            }
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
        Rmap<Etransportcompany> map = etransportcompanyService.selectByPage(page, rows, "id", "desc", sql.toString());
        if (null != map.getList()) {
            map.setStatus(260);
        }else{
            map.setStatus(240);
        }
        return map;
    }

    /**
     * @Auther: Barry
     * @Date: 2020/6/1
     * @Param: treatmentplantid 处理厂id
     * @Description: 查询处理厂下的运输公司
     */
    @AuthToken
    @RequestMapping(value = "/selectByTreatmentplantId", method = RequestMethod.GET)
    public Rmap<Etransportcompany> selectByTreatmentplantId(Integer treatmentplantid){
        Rmap<Etransportcompany> map = new  Rmap<Etransportcompany>();
        if(null == treatmentplantid){
            map.setStatus(240);
            map.setMessage("参数不全");
            return map;
        }
        map = etransportcompanyService.selectBySql("treatmentplantid = "+treatmentplantid);
        if(null != map.getList()){
            map.setStatus(260);
        }else{
            map.setStatus(240);
        }
        return map;
    }

    /**
     * @Auther: Barry
     * @Date: 2019/9/10
     * @Param: record
     * @Description: 添加运输公司
     */
    @AuthToken
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public Rmap<Etransportcompany> insert(Etransportcompany record) {
        Rmap<Etransportcompany> map = new Rmap<Etransportcompany>();
        if(isValidate(record.getName(), 0)){
            map = etransportcompanyService.insert(record);
            if(null != map.getObject()){
                map.setStatus(260);
            }else{
                map.setStatus(240);
                if(null == map.getMessage()){
                    map.setMessage("添加运输公司失败");
                }
            }
        }else{
            map.setStatus(240);
            map.setMessage("该运输公司已存在");
        }
        return map;
    }

    /**
     * @Auther: Barry
     * @Date: 2019/9/10
     * @Param: record
     * @Description: 更新运输公司
     */
    @AuthToken
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public Rmap<Etransportcompany> update(Etransportcompany record) {
        Rmap<Etransportcompany> map = new Rmap<Etransportcompany>();
        if(isValidate(record.getName(), record.getId())){
            map = etransportcompanyService.update(record);
            if(null != map.getObject()){
                map.setStatus(260);
            }else{
                map.setStatus(240);
                if(null == map.getMessage()){
                    map.setMessage("修改运输公司失败");
                }
            }
        }else{
            map.setStatus(240);
            map.setMessage("该运输公司已存在");
        }
        return map;
    }

    /**
     * @Auther: Barry
     * @Date: 2019/9/9
     * @Param: id 运输公司主键id
     * @Description: 删除运输公司
     */
    @AuthToken
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public Rmap<Etransportcompany> delete(Integer id) {
        Rmap<Etransportcompany> map = etransportcompanyService.delete(id);
        if(null != map.getObject()){
            map.setStatus(260);
        }else {
            map.setStatus(240);
            if(null == map.getMessage()) {
                map.setMessage("删除运输公司失败");
            }
        }
        return map;
    }

    /**
     * @Auther: Barry
     * @Date: 2019/9/9
     * @Param: ids 运输公司主键id数组
     * @Description: 删除运输公司
     */
    @AuthToken
    @RequestMapping(value = "/deletes", method = RequestMethod.DELETE)
    public Rmap<Etransportcompany> deletes(@RequestBody Integer[] ids){
        String strIds =  ArrayUtils.toString(ids, ",").replace('{', '(').replace('}', ')');
        Rmap<Etransportcompany> map = etransportcompanyService.deletes(strIds);
        if(null != map.getList()){
            map.setStatus(260);
        }else{
            map.setStatus(240);
            if(null == map.getMessage()){
                map.setMessage("删除运输公司失败");
            }
        }
        return map;
    }

    /**
     * @Auther: Barry
     * @Date: 2019/9/10
     * @Param: item
     * @Param: projectid
     * @Param: id
     * @Description: 数据唯一性校验
     */
    public boolean isValidate(String name, Integer id){
        Rmap<Etransportcompany> map = etransportcompanyService.selectBySql("name = '" + name +"' and id != " + id);
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

    /**
     * @Auther: Barry
     * @Date: 2020/4/21
     * @Param: list
     * @Description: 数据月份补全
     */
    public List<Map<String, Object>> add(List<Map<String, Object>> list) {
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
}