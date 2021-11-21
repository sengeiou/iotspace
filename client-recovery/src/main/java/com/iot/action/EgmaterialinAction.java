package com.iot.action;

import com.iot.bean.*;
import com.iot.service.EcmaterialcategoryService;
import com.iot.service.EcmaterialoutService;
import com.iot.service.EgmaterialcategoryService;
import com.iot.service.EgmaterialinService;
import com.iot.util.AuthToken;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/gmaterialin")
public class EgmaterialinAction extends BaseController{
    @Resource
    private EgmaterialinService egmaterialinService;
    @Resource
    private EcmaterialcategoryService ecmaterialcategoryService;
    @Resource
    private EgmaterialcategoryService egmaterialcategoryService;
    @Resource
    private EcmaterialoutService ecmaterialoutService;

    //数据月份补全
    private static final String montharray[] = {"01","02","03","04","05","06","07","08","09","10","11","12"};

    /**
     * 某一回收企业所有入库记录
     * @param treatmentplantid
     * @return
     */
    @AuthToken
    @RequestMapping(value = "/selectByTreatmentplantid", method = RequestMethod.GET)
    public Rmap<Egmaterialin> selectByTreatmentplantid(Integer treatmentplantid){
        Rmap<Egmaterialin> map = new  Rmap<Egmaterialin>();
        if(null == treatmentplantid){
            map.setStatus(240);
            map.setMessage("参数不全");
            return map;
        }
        map = egmaterialinService.selectBySql("treatmentplantid = "+treatmentplantid);
        if(null != map.getList()){
            map.setStatus(260);
        }else{
            map.setStatus(240);
        }
        return map;
    }

    /**
     * 查询所有
     * @return
     */
    @AuthToken
    @RequestMapping(value = "/selectAll", method = RequestMethod.GET)
    public Rmap<Egmaterialin> selectAll(){
        Rmap<Egmaterialin> map = egmaterialinService.selectAll();
        if(null != map.getList()){
            map.setStatus(260);
        }else{
            map.setStatus(240);
        }
        return map;
    }

    /**
     * 某一回收企业入库分页查询
     * @param page
     * @param rows
     * @param treatmentplantid
     * @param cmaterialcategoryid
     * @param begintime
     * @param endtime
     * @return
     */
    @AuthToken
    @RequestMapping(value = "/selectByPage", method = RequestMethod.GET)
    public Rmap<Egmaterialin> selectByPage(Integer page, Integer rows, Integer treatmentplantid, Integer cmaterialcategoryid, Integer gmaterialcategoryid, Date begintime, Date endtime){
        Rmap<Egmaterialin> map = new Rmap<Egmaterialin>();
        if(null == treatmentplantid){
            map.setStatus(240);
            map.setMessage("参数不全");
            return map;
        }
        StringBuilder sql =new StringBuilder();
        sql.append("treatmentplantid = "+treatmentplantid);
        if(null != cmaterialcategoryid){
            sql.append(" and cmaterialcategoryid = "+cmaterialcategoryid);
        }
        if(null != gmaterialcategoryid){
            sql.append(" and gmaterialcategoryid = "+gmaterialcategoryid);
        }
        if (null != begintime && null != endtime) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            sql.append(" and time >= '").append(format.format(begintime)).append("' and time <= '").append(format.format(endtime)).append("'");
        }
        map = egmaterialinService.selectByPage(page, rows, "id", "desc", sql.toString());
        if (null != map.getList()) {
            map.setStatus(260);
        }else{
            map.setStatus(240);
        }
        return map;
    }


    /**
     * 主键查询
     * @param id
     * @return
     */
    @AuthToken
    @RequestMapping(value = "/selectByPrimaryKey", method = RequestMethod.GET)
    public Rmap<Egmaterialin> selectByPrimaryKey(Integer id){
        Rmap<Egmaterialin> map = egmaterialinService.selectByPrimaryKey(id);
        if(null != map.getObject()){
            map.setStatus(260);
        }else{
            map.setStatus(240);
        }
        return map;
    }

    /**
     * v所有
     * @return
     */
    @AuthToken
    @RequestMapping(value = "/selectVAll", method = RequestMethod.GET)
    public Rmap<Egmaterialinv> selectVAll(){
        Rmap<Egmaterialinv> map = egmaterialinService.selectVAll();
        if(null != map.getList()){
            map.setStatus(260);
        }else{
            map.setStatus(240);
        }
        return map;
    }

    /**
     * 某一回收企业入库记录
     * @param page
     * @param rows
     * @param treatmentplantid
     * @param cmaterialcategoryid
     * @param begintime
     * @param endtime
     * @return
     */
    @AuthToken
    @RequestMapping(value = "/selectVByPage", method = RequestMethod.GET)
    public Rmap<Egmaterialinv> selectVByPage(Integer page, Integer rows, Integer treatmentplantid, Integer cmaterialcategoryid, Integer gmaterialcategoryid, Date begintime, Date endtime){
        Rmap<Egmaterialinv> map = new Rmap<Egmaterialinv>();
        if(null == treatmentplantid){
            map.setStatus(240);
            map.setMessage("参数不全");
            return map;
        }
        StringBuilder sql =new StringBuilder();
        sql.append("treatmentplantid = "+treatmentplantid);
        if(null != cmaterialcategoryid){
            sql.append(" and cmaterialcategoryid = "+cmaterialcategoryid);
        }
        if(null != gmaterialcategoryid){
            sql.append(" and gmaterialcategoryid = "+gmaterialcategoryid);
        }
        if (null != begintime && null != endtime) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            sql.append(" and time >= '").append(format.format(begintime)).append("' and time <= '").append(format.format(endtime)).append("'");
        }
        map = egmaterialinService.selectVByPage(page, rows, "id", "desc", sql.toString());
        if (null != map.getList()) {
            map.setStatus(260);
        }else{
            map.setStatus(240);
        }
        return map;
    }

    /**
     * 主键查询
     * @param id
     * @return
     */
    @AuthToken
    @RequestMapping(value = "/selectVByPrimaryKey", method = RequestMethod.GET)
    public Rmap<Egmaterialinv> selectVByPrimaryKey(Integer id){
        Rmap<Egmaterialinv> map = egmaterialinService.selectVByPrimaryKey(id);
        if(null != map.getObject()){
            map.setStatus(260);
        }else{
            map.setStatus(240);
        }
        return map;
    }

    /**
     *
     * @param record
     * @param cnumber 破碎料袋数
     * @param cweight 破碎料重量
     * @param cnote   破碎料备注
     * @return
     */
    @AuthToken
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public Rmap<Egmaterialin> insert(Egmaterialin record,Integer cnumber, Double cweight,String cnote) {
        Rmap<Egmaterialin> map = new Rmap<Egmaterialin>();
        if(null == record.getTreatmentplantid() || null == record.getCmaterialcategoryid() || null == record.getGmaterialcategoryid() || null == record.getWeight()
        || null == cweight){
            map.setStatus(240);
            map.setMessage("参数不全");
            return map;
        }
        //入库时间
        Date date = new Date();
        record.setTime(date);
        record.setSource("颗粒料");
        map = egmaterialinService.insert(record);
        if (null != map.getObject()) {
            map.setStatus(260);
            //更新颗粒料库存
            Rmap<Egmaterialcategory> gmaterialcategoryMap = egmaterialcategoryService.selectByPrimaryKey(record.getGmaterialcategoryid());
            if(null != gmaterialcategoryMap.getObject()){
                egmaterialcategoryService.updateStock(gmaterialcategoryMap.getObject().getId(),record.getWeight());
            }
            //更新破碎料库存
            Rmap<Ecmaterialcategory> cmaterialcategoryMap = ecmaterialcategoryService.selectByPrimaryKey(record.getCmaterialcategoryid());
            if(null != cmaterialcategoryMap.getObject()){
                ecmaterialcategoryService.updateStock(cmaterialcategoryMap.getObject().getId(),-cweight);
            }
            //生成破碎料出库
            Ecmaterialout cmaterialout = new Ecmaterialout();
            cmaterialout.setTime(date);
            cmaterialout.setDataid(map.getObject().getId());
            cmaterialout.setTreatmentplantid(record.getTreatmentplantid());
            cmaterialout.setCmaterialcategoryid(record.getCmaterialcategoryid());
            cmaterialout.setNumber(cnumber);
            cmaterialout.setWeight(cweight);
            cmaterialout.setSource("自用");
            cmaterialout.setNote(cnote);
            ecmaterialoutService.insert(cmaterialout);
        } else {
            map.setStatus(240);
            if (null == map.getMessage()) {
                map.setMessage("添加失败");
            }
        }
        return map;
    }

    /**
     * 编辑
     * @param record
     * @return
     */
    @AuthToken
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public Rmap<Egmaterialin> update(Egmaterialin record) {
        Rmap<Egmaterialin> map = new Rmap<Egmaterialin>();
        if(null == record.getId() || null == record.getWeight()){
            map.setStatus(240);
            map.setMessage("参数不全");
            return map;
        }
        map = egmaterialinService.update(record);
        if (null != map.getObject()) {
            map.setStatus(260);
        } else {
            map.setStatus(240);
            if (null == map.getMessage()) {
                map.setMessage("编辑失败");
            }
        }
        return map;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @AuthToken
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public Rmap<Egmaterialin> delete(Integer id) {
        Rmap<Egmaterialin> map = egmaterialinService.delete(id);
        if(null != map.getObject()){
            map.setStatus(260);
            //更新库存
            Rmap<Egmaterialcategory> gmaterialcategoryMap = egmaterialcategoryService.selectByPrimaryKey(map.getObject().getGmaterialcategoryid());
            if(null != gmaterialcategoryMap.getObject()){
                //减库存 负值
                ecmaterialcategoryService.updateStock(gmaterialcategoryMap.getObject().getId(),-map.getObject().getWeight());
            }
        }else {
            map.setStatus(240);
            if(null == map.getMessage()) {
                map.setMessage("删除失败");
            }
        }
        return map;
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @AuthToken
    @RequestMapping(value = "/deletes", method = RequestMethod.DELETE)
    public Rmap<Egmaterialin> deletes(@RequestBody Integer[] ids){
        String strIds =  ArrayUtils.toString(ids, ",").replace('{', '(').replace('}', ')');
        Rmap<Egmaterialin> map = egmaterialinService.deletes(strIds);
        if(null != map.getList()){
            map.setStatus(260);
            //更新类别库存
            for(Egmaterialin object : map.getList()){
                Rmap<Egmaterialcategory> gmaterialcategoryMap = egmaterialcategoryService.selectByPrimaryKey(object.getGmaterialcategoryid());
                if(null != gmaterialcategoryMap.getObject()){
                    //减库存 负值
                    ecmaterialcategoryService.updateStock(gmaterialcategoryMap.getObject().getId(),-object.getWeight());
                }
            }
        }else{
            map.setStatus(240);
            if(null == map.getMessage()){
                map.setMessage("删除失败");
            }
        }
        return map;
    }

    /**
     *  入库重量统计，按照类别分组
     * @param treatmentplantid
     * @param begintime 开始时间
     * @param endtime 结束时间
     * @return
     */
    @AuthToken
    @RequestMapping(value = "/sumWeightGroupByCategory", method = RequestMethod.GET)
    public Rmap<Map<String,Object>> sumWeightGroupByCategory(Integer treatmentplantid, Date begintime, Date endtime){
        Rmap<Map<String,Object>> map = new  Rmap<Map<String,Object>>();
        if(null == treatmentplantid){
            map.setStatus(240);
            map.setMessage("参数不全");
            return map;
        }
        StringBuilder sql =new StringBuilder();
        sql.append("t1.treatmentplantid = ").append(treatmentplantid);
        if (null != begintime && null != endtime) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            sql.append(" and t1.time >= '").append(format.format(begintime)).append("' and t1.time <= '").append(format.format(endtime)).append("'");
        }
        map = egmaterialinService.sumWeightGroupByCategory(sql.toString());
        if(null != map.getList()){
            map.setStatus(260);
        }else{
            map.setStatus(240);
        }
        return map;
    }


    /**
     *  入库重量统计，按照来源（破碎料类别）分组
     * @param treatmentplantid
     * @param begintime 开始时间
     * @param endtime 结束时间
     * @return
     */
    @AuthToken
    @RequestMapping(value = "/sumWeightGroupBySource", method = RequestMethod.GET)
    public Rmap<Map<String,Object>> sumWeightGroupBySource(Integer treatmentplantid, Date begintime, Date endtime){
        Rmap<Map<String,Object>> map = new  Rmap<Map<String,Object>>();
        if(null == treatmentplantid){
            map.setStatus(240);
            map.setMessage("参数不全");
            return map;
        }
        StringBuilder sql =new StringBuilder();
        sql.append(" t1.treatmentplantid = ").append(treatmentplantid);
        sql.append(" and t1.cmaterialcategoryid = t2.id ");
        if (null != begintime && null != endtime) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            sql.append(" and t1.time >= '").append(format.format(begintime)).append("' and t1.time <= '").append(format.format(endtime)).append("'");
        }
        map = egmaterialinService.sumWeightGroupBySource(sql.toString());
        if(null != map.getList()){
            map.setStatus(260);
        }else{
            map.setStatus(240);
        }
        return map;
    }

    /**
     * 入库重量, 按年按月分组
     * @param treatmentplantid
     * @param flag 0今年 1去年 2前年
     * @return
     */
    @AuthToken
    @RequestMapping(value = "/sumWeightGroupByMonth", method = RequestMethod.GET)
        public Map<String,Object> sumWeightGroupByMonth(Integer treatmentplantid, String flag){
        Map<String,Object> map = new HashMap<>();
        if(null == treatmentplantid || StringUtils.isBlank(flag)){
            map.put("status",240);
            map.put("message","参数不全");
            return map;
        }
        Rmap<Egmaterialcategory> gmaterialcategoryMap = egmaterialcategoryService.selectBySql("treatmentplantid = "+treatmentplantid);
        if(null != gmaterialcategoryMap.getList()){
            Map<String,Object> hashMap = new HashMap<>();
            for(Egmaterialcategory object : gmaterialcategoryMap.getList()){
                Rmap<Map<String,Object>> map1 = egmaterialinService.sumWeightGroupByMonth(treatmentplantid,object.getId(),Integer.valueOf(flag));
                if(null != map1.getList()){ //有数据
                    List<Map<String, Object>> list = map1.getList();
                    add(list);
                    sort(list);
                    hashMap.put(object.getName(), list);
                }else{ //无数据
                    List<Map<String, Object>> list = new ArrayList<>();
                    add(list);
                    sort(list);
                    hashMap.put(object.getName(), list);
                }
            }
            map.put("object",hashMap);
            map.put("categorylist",gmaterialcategoryMap.getList());
        }else{
            map.put("status",240);
            if(null == gmaterialcategoryMap.getMessage()){
                map.put("message","未查询到类别信息");
            }else{
                map.put("message","查询类别信息异常");
            }
        }
        if(!map.containsKey("status")){
            map.put("status",260);
        }
        return map;
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

    /**
     * 来源
     * @return
     */
    @AuthToken
    @RequestMapping(value = "/sourceList", method = RequestMethod.GET)
    public Rmap<Select> sourceList(){
        Rmap<Select> map = new Rmap<Select>();
        List<Select> list = new ArrayList<Select>();
        Select select = new Select();
        select.setText("自产");
        select.setValue("自产");
        list.add(select);
        select = new Select();
        select.setText("外购");
        select.setValue("外购");
        list.add(select);
        map.setStatus(260);
        map.setList(list);
        return map;
    }

}