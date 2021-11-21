package com.iot.action;

import com.iot.bean.Ecategory;
import com.iot.bean.Etreatmentplantproduct;
import com.iot.bean.Etreatmentplantproductv;
import com.iot.bean.Rmap;
import com.iot.service.EcategoryService;
import com.iot.service.EtreatmentplantproductService;
import com.iot.util.AuthToken;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/treatmentplantproduct")
public class EtreatmentplantproductAction extends BaseController{
    @Resource
    private EtreatmentplantproductService etreatmentplantproductService;
    @Resource
    private EcategoryService ecategoryService;

    /**
     * @Auther: Barry
     * @Date: 2020/6/5
     * @Param: treatmentplantid
     * @Param: categoryid
     * @Param: begintime
     * @Param: endtime
     * @Description: 各类别总量
     */
    @AuthToken
    @RequestMapping(value = "/sumWeightGroupByCategory", method = RequestMethod.GET)
    public Rmap<Map<String, Object>> sumWeightGroupByCategory(Integer treatmentplantid, Integer categoryid,Date begintime, Date endtime){
        Rmap<Map<String, Object>> map = new  Rmap<Map<String, Object>>();
        if(null == treatmentplantid){
            map.setStatus(240);
            map.setMessage("参数不全");
            return map;
        }
        StringBuilder sql = new StringBuilder();
        sql.append("treatmentplantid = "+treatmentplantid);
        if(null != categoryid){
           sql.append(" and id = "+categoryid);
        }
        Rmap<Ecategory> mapCategory = ecategoryService.selectBySql(sql.toString());
        if(null == mapCategory.getList()){
            map.setStatus(240);
            if(null == mapCategory.getMessage()){ //没有分类
                map.setMessage("没有分类");
            }else{
                map.setMessage(mapCategory.getMessage());
            }
            return map;
        }
        sql = new StringBuilder();
        sql.append("t1.treatmentplantid = t2.treatmentplantid");
        sql.append(" and t1.id = t2.categoryid");
        sql.append(" and t1.treatmentplantid = "+treatmentplantid);
        if(null != categoryid){
            sql.append(" and t1.id = "+categoryid);
        }
        if (null != begintime && null != endtime) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            sql.append(" and t2.time >= '").append(format.format(begintime)).append("' and t2.time <= '").append(format.format(endtime)).append("'");
        }
        sql.append(" group by t1.id");
        map = etreatmentplantproductService.sumWeightGroupByCategory(sql.toString());
        if(null != map.getList()){
            List<Map<String, Object>> list = map.getList();
            add(mapCategory.getList(),map.getList());
            map.setStatus(260);
            map.setList(list);
        }else{
            map.setStatus(240);
        }
        return map;
    }

    /**
     * @Auther: Barry
     * @Date: 2020/6/1
     * @Param:
     * @Description: 查询所有
     */
    @AuthToken
    @RequestMapping(value = "/selectAll", method = RequestMethod.GET)
    public Rmap<Etreatmentplantproduct> selectAll(){
        Rmap<Etreatmentplantproduct> map = etreatmentplantproductService.selectAll();
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
     * @Param: page
     * @Param: rows
     * @Param: name
     * @Param: treatmentplantid
     * @Description: 查询
     */
    @AuthToken
    @RequestMapping(value = "/selectByPage", method = RequestMethod.GET)
    public Rmap<Etreatmentplantproduct> selectByPage(Integer page, Integer rows, Integer categoryid, Integer treatmentplantid,Date begintime, Date endtime){
        Rmap<Etreatmentplantproduct> map = new Rmap<Etreatmentplantproduct>();
        if(null == treatmentplantid){
            map.setStatus(240);
            map.setMessage("参数不全");
            return map;
        }
        StringBuilder sql =new StringBuilder();
        sql.append("treatmentplantid = "+treatmentplantid);
        if(null != categoryid){
            sql.append(" and categoryid = "+categoryid);
        }
        if (null != begintime && null != endtime) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            sql.append(" and time >= '").append(format.format(begintime)).append("' and time <= '").append(format.format(endtime)).append("'");
        }
        map = etreatmentplantproductService.selectByPage(page, rows, "id", "desc", sql.toString());
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
     * @Param: id
     * @Description: 主键查询
     */
    @AuthToken
    @RequestMapping(value = "/selectByPrimaryKey", method = RequestMethod.GET)
    public Rmap<Etreatmentplantproduct> selectByPrimaryKey(Integer id){
        Rmap<Etreatmentplantproduct> map = etreatmentplantproductService.selectByPrimaryKey(id);
        if(null != map.getObject()){
            map.setStatus(260);
        }else{
            map.setStatus(240);
        }
        return map;
    }

    /**
     * @Auther: Barry
     * @Date: 2020/4/9
     * @Param:
     * @Description: V所有
     */
    @AuthToken
    @RequestMapping(value = "/selectVAll", method = RequestMethod.GET)
    public Rmap<Etreatmentplantproductv> selectVAll(){
        Rmap<Etreatmentplantproductv> map = etreatmentplantproductService.selectVAll();
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
     * @Param: page
     * @Param: rows
     * @Param: name
     * @Param: treatmentplantid
     * @Description: V查询
     */
    @AuthToken
    @RequestMapping(value = "/selectVByPage", method = RequestMethod.GET)
    public Rmap<Etreatmentplantproductv> selectVByPage(Integer page, Integer rows, Integer treatmentplantid, Integer categoryid,Date begintime, Date endtime){
        Rmap<Etreatmentplantproductv> map = new Rmap<Etreatmentplantproductv>();
        if(null == treatmentplantid){
            map.setStatus(240);
            map.setMessage("参数不全");
            return map;
        }
        StringBuilder sql =new StringBuilder();
        sql.append("treatmentplantid = "+treatmentplantid);
        if(null != categoryid){
            sql.append(" and categoryid = "+categoryid);
        }
        if (null != begintime && null != endtime) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            sql.append(" and time >= '").append(format.format(begintime)).append("' and time <= '").append(format.format(endtime)).append("'");
        }
        map = etreatmentplantproductService.selectVByPage(page, rows, "id", "desc", sql.toString());
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
     * @Description: v主键查询
     */
    @AuthToken
    @RequestMapping(value = "/selectVByPrimaryKey", method = RequestMethod.GET)
    public Rmap<Etreatmentplantproductv> selectVByPrimaryKey(Integer id){
        Rmap<Etreatmentplantproductv> map = etreatmentplantproductService.selectVByPrimaryKey(id);
        if(null != map.getObject()){
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
     * @Description: 添加
     */
    @AuthToken
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public Rmap<Etreatmentplantproduct> insert(Etreatmentplantproduct record) {
        record.setTime(new Date());
        Rmap<Etreatmentplantproduct> map = etreatmentplantproductService.insert(record);
        if (null != map.getObject()) {
            map.setStatus(260);
        } else {
            map.setStatus(240);
            if (null == map.getMessage()) {
                map.setMessage("添加失败");
            }
        }
        return map;
    }

    /**
     * @Auther: Barry
     * @Date: 2019/9/10
     * @Param: record
     * @Description: 更新
     */
    @AuthToken
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public Rmap<Etreatmentplantproduct> update(Etreatmentplantproduct record) {
        Rmap<Etreatmentplantproduct> map = etreatmentplantproductService.update(record);
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
     * @Date: 2019/9/9
     * @Param: id
     * @Description: 删除
     */
    @AuthToken
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public Rmap<Etreatmentplantproduct> delete(Integer id) {
        Rmap<Etreatmentplantproduct> map = etreatmentplantproductService.delete(id);
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
     * @Auther: Barry
     * @Date: 2019/9/9
     * @Param: ids
     * @Description: 删除
     */
    @AuthToken
    @RequestMapping(value = "/deletes", method = RequestMethod.DELETE)
    public Rmap<Etreatmentplantproduct> deletes(@RequestBody Integer[] ids){
        String strIds =  ArrayUtils.toString(ids, ",").replace('{', '(').replace('}', ')');
        Rmap<Etreatmentplantproduct> map = etreatmentplantproductService.deletes(strIds);
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
     * @Auther: Barry
     * @Date: 2020/6/8
     * @Param: categoryList
     * @Param: list
     * @Description: 补全数据
     */
    public List<Map<String, Object>> add(List<Ecategory> categoryList, List<Map<String, Object>> list) {
        /* System.out.println(list.toString());*/
        for (Ecategory category : categoryList) {
            if (!list.toString().contains("name="+category.getName())) {
                Map<String, Object> map = new HashMap<>();
                map.put("name", category.getName());
                map.put("value", 0);
                list.add(map);
            }
        }
        return list;
    }

}