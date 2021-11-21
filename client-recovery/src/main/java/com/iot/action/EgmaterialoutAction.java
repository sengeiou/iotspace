package com.iot.action;

import com.iot.bean.*;
;
import com.iot.service.EgmaterialcategoryService;
import com.iot.service.EgmaterialoutService;
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
@RequestMapping("/gmaterialout")
public class EgmaterialoutAction extends BaseController{
    @Resource
    private EgmaterialoutService egmaterialoutService;
    @Resource
    private EgmaterialcategoryService egmaterialcategoryService;

    //数据月份补全
    private static final String montharray[] = {"01","02","03","04","05","06","07","08","09","10","11","12"};

    /**
     * 某一回收企业所有出库记录
     * @param treatmentplantid
     * @return
     */
    @AuthToken
    @RequestMapping(value = "/selectByTreatmentplantid", method = RequestMethod.GET)
    public Rmap<Egmaterialout> selectByTreatmentplantid(Integer treatmentplantid){
        Rmap<Egmaterialout> map = new  Rmap<Egmaterialout>();
        if(null == treatmentplantid){
            map.setStatus(240);
            map.setMessage("参数不全");
            return map;
        }
        map = egmaterialoutService.selectBySql("treatmentplantid = "+treatmentplantid);
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
    public Rmap<Egmaterialout> selectAll(){
        Rmap<Egmaterialout> map = egmaterialoutService.selectAll();
        if(null != map.getList()){
            map.setStatus(260);
        }else{
            map.setStatus(240);
        }
        return map;
    }

    /**
     * 某一回收企业出库分页查询
     * @param page
     * @param rows
     * @param source
     * @param treatmentplantid
     * @param gmaterialcategoryid
     * @param begintime
     * @param endtime
     * @return
     */
    @AuthToken
    @RequestMapping(value = "/selectByPage", method = RequestMethod.GET)
    public Rmap<Egmaterialout> selectByPage(Integer page, Integer rows, String source, Integer treatmentplantid, Integer gmaterialcategoryid, Date begintime, Date endtime){
        Rmap<Egmaterialout> map = new Rmap<Egmaterialout>();
        if(null == treatmentplantid){
            map.setStatus(240);
            map.setMessage("参数不全");
            return map;
        }
        StringBuilder sql =new StringBuilder();
        sql.append("treatmentplantid = "+treatmentplantid);
        if(null != gmaterialcategoryid){
            sql.append(" and gmaterialcategoryid = "+gmaterialcategoryid);
        }
        if(StringUtils.isNotBlank(source)){
            sql.append(" and source = '"+source+"'");
        }
        if (null != begintime && null != endtime) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            sql.append(" and time >= '").append(format.format(begintime)).append("' and time <= '").append(format.format(endtime)).append("'");
        }
        map = egmaterialoutService.selectByPage(page, rows, "id", "desc", sql.toString());
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
    public Rmap<Egmaterialout> selectByPrimaryKey(Integer id){
        Rmap<Egmaterialout> map = egmaterialoutService.selectByPrimaryKey(id);
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
    public Rmap<Egmaterialoutv> selectVAll(){
        Rmap<Egmaterialoutv> map = egmaterialoutService.selectVAll();
        if(null != map.getList()){
            map.setStatus(260);
        }else{
            map.setStatus(240);
        }
        return map;
    }

    /**
     * 某一回收企业出库记录
     * @param page
     * @param rows
     * @param source
     * @param treatmentplantid
     * @param gmaterialcategoryid
     * @param begintime
     * @param endtime
     * @return
     */
    @AuthToken
    @RequestMapping(value = "/selectVByPage", method = RequestMethod.GET)
    public Rmap<Egmaterialoutv> selectVByPage(Integer page, Integer rows, String source, Integer treatmentplantid, Integer gmaterialcategoryid, Date begintime, Date endtime){
        Rmap<Egmaterialoutv> map = new Rmap<Egmaterialoutv>();
        if(null == treatmentplantid){
            map.setStatus(240);
            map.setMessage("参数不全");
            return map;
        }
        StringBuilder sql =new StringBuilder();
        sql.append("treatmentplantid = "+treatmentplantid);
        if(null != gmaterialcategoryid){
            sql.append(" and gmaterialcategoryid = "+gmaterialcategoryid);
        }
        if(StringUtils.isNotBlank(source)){
            sql.append(" and source = '"+source+"'");
        }
        if (null != begintime && null != endtime) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            sql.append(" and time >= '").append(format.format(begintime)).append("' and time <= '").append(format.format(endtime)).append("'");
        }
        map = egmaterialoutService.selectVByPage(page, rows, "id", "desc", sql.toString());
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
    public Rmap<Egmaterialoutv> selectVByPrimaryKey(Integer id){
        Rmap<Egmaterialoutv> map = egmaterialoutService.selectVByPrimaryKey(id);
        if(null != map.getObject()){
            map.setStatus(260);
        }else{
            map.setStatus(240);
        }
        return map;
    }

    /**
     * 创建 自用
     * @param record
     * @return
     */
    @AuthToken
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public Rmap<Egmaterialout> insert(Egmaterialout record) {
        Rmap<Egmaterialout> map = new Rmap<Egmaterialout>();
        if(null == record.getTreatmentplantid() || null == record.getGmaterialcategoryid() || null == record.getWeight()){
            map.setStatus(240);
            map.setMessage("参数不全");
            return map;
        }
        //出库时间
        record.setTime(new Date());
        record.setSource("自用");
        map = egmaterialoutService.insert(record);
        if (null != map.getObject()) {
            map.setStatus(260);
            //减库存
            Rmap<Egmaterialcategory> gmaterialcategoryMap = egmaterialcategoryService.selectByPrimaryKey(record.getGmaterialcategoryid());
            if(null != gmaterialcategoryMap.getObject()){
                egmaterialcategoryService.updateStock(gmaterialcategoryMap.getObject().getId(),-record.getWeight());
            }
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
    public Rmap<Egmaterialout> update(Egmaterialout record) {
        Rmap<Egmaterialout> map = egmaterialoutService.update(record);
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
    public Rmap<Egmaterialout> delete(Integer id) {
        Rmap<Egmaterialout> map = egmaterialoutService.delete(id);
        if(null != map.getObject()){
            map.setStatus(260);
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
    public Rmap<Egmaterialout> deletes(@RequestBody Integer[] ids){
        String strIds =  ArrayUtils.toString(ids, ",").replace('{', '(').replace('}', ')');
        Rmap<Egmaterialout> map = egmaterialoutService.deletes(strIds);
        if(null != map.getList()){
            map.setStatus(260);
        }else{
            map.setStatus(240);
            if(null == map.getMessage()){
                map.setMessage("删除失败");
            }
        }
        return map;
    }

    /**
     *  出库重量统计，按照类别分组
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
        map = egmaterialoutService.sumWeightGroupByCategory(sql.toString());
        if(null != map.getList()){
            map.setStatus(260);
        }else{
            map.setStatus(240);
        }
        return map;
    }


    /**
     *  出库重量统计，按照去向分组
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
        sql.append("treatmentplantid = ").append(treatmentplantid);
        if (null != begintime && null != endtime) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            sql.append(" and time >= '").append(format.format(begintime)).append("' and time <= '").append(format.format(endtime)).append("'");
        }
        map = egmaterialoutService.sumWeightGroupBySource(sql.toString());
        if(null != map.getList()){
            map.setStatus(260);
        }else{
            map.setStatus(240);
        }
        return map;
    }

    /**
     * 出库重量, 按年按月分组
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
                Rmap<Map<String,Object>> map1 = egmaterialoutService.sumWeightGroupByMonth(treatmentplantid,object.getId(),Integer.valueOf(flag));
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
     * 去向
     * @return
     */
    @AuthToken
    @RequestMapping(value = "/sourceList", method = RequestMethod.GET)
    public Rmap<Select> sourceList(){
        Rmap<Select> map = new Rmap<Select>();
        List<Select> list = new ArrayList<Select>();
        Select select = new Select();
        select.setText("销售");
        select.setValue("销售");
        list.add(select);
        select = new Select();
        select.setText("自用");
        select.setValue("自用");
        list.add(select);
        map.setStatus(260);
        map.setList(list);
        return map;
    }

}