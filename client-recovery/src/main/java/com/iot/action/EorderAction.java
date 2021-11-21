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
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/order")
public class EorderAction extends BaseController{
    
    @Resource
    private EorderService eorderService;
    @Resource
    private EcmaterialcategoryService ecmaterialcategoryService;
    @Resource
    private EgmaterialcategoryService egmaterialcategoryService;
    @Resource
    private EcmaterialoutService ecmaterialoutService;
    @Resource
    private EgmaterialoutService egmaterialoutService;



    /**
     * 查询所有
     * @return
     */
    @AuthToken
    @RequestMapping(value = "/selectAll", method = RequestMethod.GET)
    public Rmap<Eorder> selectAll(){
        Rmap<Eorder> map = eorderService.selectAll();
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
    public Rmap<Eorder> selectByPrimaryKey(Integer id){
        Rmap<Eorder> map = eorderService.selectByPrimaryKey(id);
        if(null != map.getObject()){
            map.setStatus(260);
        }else{
            map.setStatus(240);
        }
        return map;
    }

    /**
     * 查询订单
     * @param page
     * @param rows
     * @param type
     * @param begintime
     * @param endtime
     * @return
     */
    @AuthToken
    @RequestMapping(value = "/selectByPage", method = RequestMethod.GET)
    public Rmap<Eorder> selectByPage(Integer page, Integer rows, Integer treatmentplantid, String type, Date begintime, Date endtime) {
        Rmap<Eorder> map = new Rmap<>();
        if(null == treatmentplantid){
            map.setStatus(240);
            map.setMessage("参数不全");
            return map;
        }
        StringBuilder sql =new StringBuilder();
        sql.append("treatmentplantid = ").append(treatmentplantid);
        if(StringUtils.isNotBlank(type)){
            sql.append(" and type = '").append(type).append("'");
        }
        if (null != begintime && null != endtime) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            sql.append(" and time >= '").append(format.format(begintime)).append("' and time <= '").append(format.format(endtime)).append("'");
        }
        map = eorderService.selectByPage(page, rows, "id", "desc", sql.toString());
        if (null != map.getList()) {
            map.setStatus(260);
        }else{
            map.setStatus(240);
        }
        return map;
    }

    /**
     * 创建订单
     * @param record
     * @return
     */
    @AuthToken
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public Rmap<Eorder> insert(Eorder record) {
        Rmap<Eorder> map = new Rmap<Eorder>();
        if(StringUtils.isBlank(record.getType()) || null == record.getCategoryid() || null == record.getWeight()){
            map.setStatus(240);
            map.setMessage("参数不全");
            return map;
        }
        Date date = new Date();
        record.setTime(date);
        //订单编号
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        record.setNo(format.format(date));
        map = eorderService.insert(record);
        if (null != map.getObject()) {
            map.setStatus(260);
            //更新库存及生成出库记录
            if(record.getType().equals("破碎料")){
                //更新破碎料库存
                Rmap<Ecmaterialcategory> cmaterialcategoryMap = ecmaterialcategoryService.selectByPrimaryKey(record.getCategoryid());
                if(null != cmaterialcategoryMap.getObject()){
                    ecmaterialcategoryService.updateStock(cmaterialcategoryMap.getObject().getId(),-record.getWeight());
                }
                //生成破碎料出库
                Ecmaterialout cmaterialout = new Ecmaterialout();
                cmaterialout.setTime(date);
                cmaterialout.setDataid(map.getObject().getId());
                cmaterialout.setTreatmentplantid(record.getTreatmentplantid());
                cmaterialout.setCmaterialcategoryid(record.getCategoryid());
                cmaterialout.setNumber(record.getNumber());
                cmaterialout.setWeight(record.getWeight());
                cmaterialout.setSource("销售");
                cmaterialout.setNote(null);
                ecmaterialoutService.insert(cmaterialout);
            }else if(record.getType().equals("颗粒料")){
                //更新破碎料库存
                Rmap<Egmaterialcategory> gmaterialcategoryMap = egmaterialcategoryService.selectByPrimaryKey(record.getCategoryid());
                if(null != gmaterialcategoryMap.getObject()){
                    egmaterialcategoryService.updateStock(gmaterialcategoryMap.getObject().getId(),-record.getWeight());
                }
                //生成破碎料出库
                Egmaterialout gmaterialout = new Egmaterialout();
                gmaterialout.setTime(date);
                gmaterialout.setDataid(map.getObject().getId());
                gmaterialout.setTreatmentplantid(record.getTreatmentplantid());
                gmaterialout.setGmaterialcategoryid(record.getCategoryid());
                gmaterialout.setNumber(record.getNumber());
                gmaterialout.setWeight(record.getWeight());
                gmaterialout.setSource("销售");
                gmaterialout.setNote(null);
                egmaterialoutService.insert(gmaterialout);
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
    public Rmap<Eorder> update(Eorder record) {
        Rmap<Eorder> map = new Rmap<Eorder>();
        map = eorderService.update(record);
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
     * 删除
     * @param id
     * @return
     */
    @AuthToken
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public Rmap<Eorder> delete(Integer id) {
        Rmap<Eorder> map = eorderService.delete(id);
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
     * 删除
     * @param ids
     * @return
     */
    @AuthToken
    @RequestMapping(value = "/deletes", method = RequestMethod.DELETE)
    public Rmap<Eorder> deletes(@RequestBody Integer[] ids){
        String strIds =  ArrayUtils.toString(ids, ",").replace('{', '(').replace('}', ')');
        Rmap<Eorder> map = eorderService.deletes(strIds);
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
     * 查询销售 破碎料/颗粒料类别
     * @param type
     * @param categoryid
     * @return
     */
    @AuthToken
    @RequestMapping(value = "/selectByCategory", method = RequestMethod.GET)
    public Map<String, Object> selectByCategory(String type,Integer categoryid){
        Map<String, Object> map = new HashMap<>();
        if(StringUtils.isBlank(type) || null == categoryid){
            map.put("status","240");
            map.put("message","参数不全");
            return map;
        }
        if(type.equals("破碎料")){
            Rmap<Ecmaterialcategory> cmaterialcategoryMap = ecmaterialcategoryService.selectByPrimaryKey(categoryid);
            if(null != cmaterialcategoryMap.getObject()){
                map.put("status","260");
                map.put("object", cmaterialcategoryMap.getObject());
            }else{
                map.put("status","240");
                if(null == cmaterialcategoryMap.getMessage()){
                    map.put("message","未查询到数据");
                }else{
                    map.put("message","查询数据异常");
                }
            }
        }else if(type.equals("颗粒料")){
            Rmap<Egmaterialcategory> gmaterialcategoryMap = egmaterialcategoryService.selectByPrimaryKey(categoryid);
            if(null != gmaterialcategoryMap.getObject()){
                map.put("status","260");
                map.put("object", gmaterialcategoryMap.getObject());
            }else{
                map.put("status","240");
                if(null == gmaterialcategoryMap.getMessage()){
                    map.put("message","未查询到数据");
                }else{
                    map.put("message","查询数据异常");
                }
            }
        }else{
            map.put("status","240");
            map.put("message","参数错误");
        }
        return map;
    }

    /**
     * 销售类型
     * @return
     */
    @AuthToken
    @RequestMapping(value = "/typeList", method = RequestMethod.GET)
    public Rmap<Select> sourceList(){
        Rmap<Select> map = new Rmap<Select>();
        List<Select> list = new ArrayList<Select>();
        Select select = new Select();
        select.setText("破碎料");
        select.setValue("破碎料");
        list.add(select);
        select = new Select();
        select.setText("颗粒料");
        select.setValue("颗粒料");
        list.add(select);
        map.setStatus(260);
        map.setList(list);
        return map;
    }

}