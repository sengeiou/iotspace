package com.iot.action;

import com.iot.bean.Egarbagebag;
import com.iot.bean.Egarbagebagv;
import com.iot.bean.Rmap;
import com.iot.bean.Select;
import com.iot.service.EgarbagebagService;
import com.iot.util.AuthToken;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/garbagebag")
public class EgarbagebagAction extends BaseController{
    @Resource
    private EgarbagebagService egarbagebagService;

    /**
     * @Auther: Barry
     * @Date: 2020/3/25
     * @Param:
     * @Description: 查询所有
     */
    @AuthToken
    @RequestMapping(value = "/selectAll", method = RequestMethod.GET)
    public Rmap<Egarbagebag> selectAll(){
        Rmap<Egarbagebag> map = egarbagebagService.selectAll();
        if(null != map.getList()){
            map.setStatus(260);
        }else{
            map.setStatus(240);
        }
        return map;
    }

    /**
     * @Auther: Barry
     * @Date: 2020/4/13
     * @Param: page
     * @Param: rows
     * @Param: barcode
     * @Param: type
     * @Param: departmentid
     * @Param: hospitalid
     * @Param: status
     * @Param: truckid
     * @Param: begintime
     * @Param: endtime
     * @Description: 医院垃圾袋
     */
    @AuthToken
    @RequestMapping(value = "/selectByHospital", method = RequestMethod.GET)
    public Rmap<Egarbagebag> selectByHospital(Integer page, Integer rows, String barcode, String type, Integer  departmentid, Integer hospitalid, String status, Integer truckid, Date begintime, Date endtime){
        Rmap<Egarbagebag> map = new Rmap<Egarbagebag>();
        if (null == hospitalid) {
            map.setMessage("参数不全");
            map.setStatus(240);
            return map;
        }
        StringBuilder sql = new StringBuilder();
        sql.append("hospital.id = department.hospitalid and department.id = garbagebag.departmentid").append(" and hospital.id = ").append(hospitalid);;
        if (StringUtils.isNotBlank(barcode)) {
            sql.append(" and garbagebag.barcode like ").append("'%25").append(barcode).append("%25'");
        }
        if (null != departmentid) {
            sql.append(" and department.departmentid = ").append(departmentid);
        }
        if (StringUtils.isNotBlank(status)) {
            sql.append(" and garbagebag.status = '").append(status).append("'");
        }
        if (StringUtils.isNotBlank(type)) {
            sql.append(" and garbagebag.type = '").append(type).append("'");
        }
        if (null != truckid) {
            sql.append(" and garbagebag.truckid = ").append(truckid);

        }
        if (null != begintime && null != endtime) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            sql.append(" and garbagebag.time >= '").append(format.format(begintime)).append("' and garbagebag.time <= '").append(format.format(endtime)).append("'");
        }
        map = egarbagebagService.selectByHospitalAndPage(page, rows, "garbagebag.id", "desc", sql.toString());
        if (null != map.getList()) {
            map.setStatus(260);
        } else {
            map.setStatus(240);
        }
        return map;
    }


    /**
     * @Auther: Barry
     * @Date: 2020/4/13
     * @Param: page
     * @Param: rows
     * @Param: barcode
     * @Param: type
     * @Param: departmentid
     * @Param: hospitalid
     * @Param: status
     * @Param: truckid
     * @Param: begintime
     * @Param: endtime
     * @Description: 医院垃圾袋
     */
    @AuthToken
    @RequestMapping(value = "/selectVByHospital", method = RequestMethod.GET)
    public Rmap<Egarbagebagv> selectVByHospital(Integer page, Integer rows, String barcode, String type, Integer  departmentid, Integer hospitalid, String status, String belongtype,String belongname, Integer truckid, Date begintime, Date endtime){
        Rmap<Egarbagebagv> map = new Rmap<Egarbagebagv>();
        if (null == hospitalid) {
            map.setMessage("参数不全");
            map.setStatus(240);
            return map;
        }
        StringBuilder sql = new StringBuilder();
        sql.append("hospital.id = department.hospitalid and department.id = garbagebag.departmentid").append(" and hospital.id = ").append(hospitalid);
        if (StringUtils.isNotBlank(barcode)) {
            sql.append(" and garbagebag.barcode like ").append("'%25").append(barcode).append("%25'");
        }
        if (null != departmentid) {
            sql.append(" and department.departmentid = ").append(departmentid);
        }
        if (StringUtils.isNotBlank(status)) {
            sql.append(" and garbagebag.status = '").append(status).append("'");
        }
        if (StringUtils.isNotBlank(type)) {
            sql.append(" and garbagebag.type = '").append(type).append("'");
        }
        if (StringUtils.isNotBlank(belongtype)) {
            sql.append(" and garbagebag.belongtype = '").append(belongtype).append("'");
        }
        if (StringUtils.isNotBlank(belongname)) {
            sql.append(" and garbagebag.belongname like ").append("'%25").append(belongname).append("%25'");
        }
        if (null != truckid) {
            sql.append(" and garbagebag.truckid = ").append(truckid);

        }
        if (null != begintime && null != endtime) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            sql.append(" and garbagebag.time >= '").append(format.format(begintime)).append("' and garbagebag.time <= '").append(format.format(endtime)).append("'");
        }
        map = egarbagebagService.selectVByHospitalAndPage(page, rows, "garbagebag.id", "desc", sql.toString());
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
     * @Param: page
     * @Param: rows
     * @Param: barcode
     * @Param: departmentid
     * @Param: status
     * @Param: truckid
     * @Param: begintime
     * @Param: endtime
     * @Description: 分页查询
     */
    @AuthToken
    @RequestMapping(value = "/selectByPage", method = RequestMethod.GET)
    public Rmap<Egarbagebag> selectByPage(Integer page, Integer rows, String barcode, String type, Integer  departmentid, String status, Integer truckid, Date begintime, Date endtime){
        StringBuilder sql =new StringBuilder();
        if(StringUtils.isNotBlank(barcode)){
            sql.append("barcode like ").append("'%25").append(barcode).append("%25'");
        }
        if(null != departmentid){
            if(sql.length() == 0){
                sql.append("departmentid = ").append(departmentid);
            }else{
                sql.append(" and departmentid = ").append(departmentid);
            }
        }
        if(StringUtils.isNotBlank(status)){
            if(sql.length() == 0){
                sql.append("status = '").append(status).append("'");
            }else{
                sql.append(" and status = '").append(status).append("'");
            }
        }
        if(StringUtils.isNotBlank(type)){
            if(sql.length() == 0){
                sql.append("type = '").append(type).append("'");
            }else{
                sql.append(" and type = '").append(type).append("'");
            }
        }
        if(null != truckid){
            if(sql.length() == 0){
                sql.append("truckid = ").append(truckid);
            }else{
                sql.append(" and truckid = ").append(truckid);
            }
        }
        if(null != begintime && null != endtime){
            SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if(sql.length() == 0){
                sql.append("time >= '").append(format.format(begintime)).append("' and time <= '").append(format.format(endtime)).append("'");
            }else{
                sql.append(" and time >= '").append(format.format(begintime)).append("' and time <= '").append(format.format(endtime)).append("'");
            }
        }
        Rmap<Egarbagebag> map = egarbagebagService.selectByPage(page, rows, "id", "desc", sql.toString());
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
     * @Param: page
     * @Param: rows
     * @Param: barcode
     * @Param: departmentid
     * @Param: status
     * @Param: truckid
     * @Param: begintime
     * @Param: endtime
     * @Description: V分页查询
     */
    @AuthToken
    @RequestMapping(value = "/selectVByPage", method = RequestMethod.GET)
    public Rmap<Egarbagebagv> selectVByPage(Integer page, Integer rows, String barcode, String type, Integer  departmentid, String status, Integer truckid, Date begintime, Date endtime){
        StringBuilder sql =new StringBuilder();
        if(StringUtils.isNotBlank(barcode)){
            sql.append("barcode like ").append("'%25").append(barcode).append("%25'");
        }
        if(null != departmentid){
            if(sql.length() == 0){
                sql.append("departmentid = ").append(departmentid);
            }else{
                sql.append(" and departmentid = ").append(departmentid);
            }
        }
        if(StringUtils.isNotBlank(status)){
            if(sql.length() == 0){
                sql.append("status = '").append(status).append("'");
            }else{
                sql.append(" and status = '").append(status).append("'");
            }
        }
        if(StringUtils.isNotBlank(type)){
            if(sql.length() == 0){
                sql.append("type = '").append(type).append("'");
            }else{
                sql.append(" and type = '").append(type).append("'");
            }
        }
        if(null != truckid){
            if(sql.length() == 0){
                sql.append("truckid = ").append(truckid);
            }else{
                sql.append(" and truckid = ").append(truckid);
            }
        }
        if(null != begintime && null != endtime){
            SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if(sql.length() == 0){
                sql.append("time >= '").append(format.format(begintime)).append("' and time <= '").append(format.format(endtime)).append("'");
            }else{
                sql.append(" and time >= '").append(format.format(begintime)).append("' and time <= '").append(format.format(endtime)).append("'");
            }
        }
        Rmap<Egarbagebagv> map = egarbagebagService.selectVByPage(page, rows, "id", "desc", sql.toString());
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
    public Rmap<Egarbagebag> selectByPrimaryKey(Integer id){
        Rmap<Egarbagebag> map = egarbagebagService.selectByPrimaryKey(id);
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
    public Rmap<Egarbagebag> insert(Egarbagebag record) {
        Rmap<Egarbagebag> map = new Rmap<Egarbagebag>();
        if(isValidate(record.getBarcode(), 0)){
            record.setTime(new Date());
            Rmap<Egarbagebag> rmap = egarbagebagService.insert(record);
            if(null != rmap.getObject()){
                map.setStatus(260);
            }else{
                map.setStatus(240);
                if(null == rmap.getMessage()){
                    map.setMessage("添加垃圾袋失败");
                }else{
                    map.setMessage(rmap.getMessage());
                }
            }
        }else{
            map.setStatus(240);
            map.setMessage("该垃圾袋已存在");
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
    public Rmap<Egarbagebag> update(Egarbagebag record) {
        Rmap<Egarbagebag> map = new Rmap<Egarbagebag>();
        if(isValidate(record.getBarcode(), record.getId())){
            Rmap<Egarbagebag> rmap = egarbagebagService.update(record);
            if(null != rmap.getObject()){
                map.setStatus(260);
            }else{
                map.setStatus(240);
                if(null == rmap.getMessage()){
                    map.setMessage("修改垃圾袋失败");
                }else{
                    map.setMessage(rmap.getMessage());
                }
            }
        }else{
            map.setStatus(240);
            map.setMessage("该垃圾袋已存在");
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
    public Rmap<Egarbagebag> delete(Integer id) {
        Rmap<Egarbagebag> map = egarbagebagService.delete(id);
        if(null != map.getObject()){
            map.setStatus(260);
        }else {
            map.setStatus(240);
            if(null == map.getMessage()) {
                map.setMessage("删除垃圾袋失败");
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
    public Rmap<Egarbagebag> deletes(@RequestBody Integer[] ids){
        String strIds =  ArrayUtils.toString(ids, ",").replace('{', '(').replace('}', ')');
        Rmap<Egarbagebag> map = egarbagebagService.deletes(strIds);
        if(null != map.getList()){
            map.setStatus(260);
        }else{
            map.setStatus(240);
            if(null == map.getMessage()){
                map.setMessage("删除垃圾袋失败");
            }
        }
        return map;
    }

    /**
     * @Auther: Barry
     * @Date: 2020/4/16
     * @Param:
     * @Description: 垃圾袋状态列表
     */
    @AuthToken
    @RequestMapping(value = "/statusList", method = RequestMethod.GET)
    public Rmap<Select> statusList(){
        Rmap<Select> map = new Rmap<Select>();
        List<Select> list = new ArrayList<Select>();
        Select select = new Select();
        select.setText("使用中");
        select.setValue("使用中");
        list.add(select);
        select = new Select();
        select.setText("封袋");
        select.setValue("封袋");
        list.add(select);
        select = new Select();
        select.setText("运输");
        select.setValue("运输");
        list.add(select);
        select = new Select();
        select.setText("暂存");
        select.setValue("暂存");
        list.add(select);
        select = new Select();
        select.setText("已回收");
        select.setValue("已回收");
        list.add(select);
        map.setStatus(260);
        map.setList(list);
        return map;
    }

    /**
     * @Auther: Barry
     * @Date: 2020/4/8
     * @Param:
     * @Description: 垃圾袋类型列表
     */
    @AuthToken
    @RequestMapping(value = "/typeList", method = RequestMethod.GET)
    public Rmap<Select> typeList(){
        Rmap<Select> map = new Rmap<Select>();
        List<Select> list = new ArrayList<Select>();
        Select select = new Select();
        select.setText("塑料");
        select.setValue("塑料");
        list.add(select);
        select = new Select();
        select.setText("玻璃瓶");
        select.setValue("玻璃瓶");
        list.add(select);
        map.setStatus(260);
        map.setList(list);
        return map;
    }


    /**
     * @Auther: Barry
     * @Date: 2020/3/25
     * @Param: barcode
     * @Param: id
     * @Description: 唯一性
     */
    public boolean isValidate(String barcode, Integer id){
        Rmap<Egarbagebag> map = egarbagebagService.selectBySql("barcode = '" + barcode +"' and id != " + id);
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
     * @Date: 2020/4/27
     * @Param: hospitalid
     * @Description: 医院各科室垃圾袋数量
     */
    @AuthToken
    @RequestMapping(value = "/countGroupByDepartment", method = RequestMethod.GET)
    public Rmap<Map<String,Object>> countGroupByDepartment(Integer hospitalid){
        Rmap<Map<String,Object>> map = egarbagebagService.countGroupByDepartment(hospitalid);
        if(null != map.getList()){
            map.setStatus(260);
        }else{
            map.setStatus(240);
        }
        return map;
    }
}