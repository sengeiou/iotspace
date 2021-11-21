package com.iot.action;

import com.iot.bean.Etruckproductionplant;
import com.iot.bean.Etruckproductionplantv;
import com.iot.bean.Rmap;
import com.iot.bean.Select;
import com.iot.service.EtruckproductionplantService;
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

@RestController
@RequestMapping("/truckproductionplant")
public class EtruckproductionplantAction extends BaseController{
    @Resource
    private EtruckproductionplantService etruckproductionplantService;

    /**
     * @Auther: Barry
     * @Date: 2020/3/25
     * @Param:
     * @Description: 查询所有
     */
    @AuthToken
    @RequestMapping(value = "/selectAll", method = RequestMethod.GET)
    public Rmap<Etruckproductionplant> selectAll(){
        Rmap<Etruckproductionplant> map = etruckproductionplantService.selectAll();
        if(null != map.getList()){
            map.setStatus(260);
        }else{
            map.setStatus(240);
        }
        return map;
    }

    /**
     * @Auther: Barry
     * @Date: 2020/4/28
     * @Param: page
     * @Param: rows
     * @Param: productionplantid
     * @Param: truckid
     * @Param: submituserid
     * @Param: confirmuserid
     * @Param: status
     * @Param: begintime
     * @Param: endtime
     * @Description:
     */
    @AuthToken
    @RequestMapping(value = "/selectByPage", method = RequestMethod.GET)
    public Rmap<Etruckproductionplant> selectByPage(Integer page, Integer rows, Integer productionplantid, Integer truckid, Integer submituserid, Integer confirmuserid, String status, Date begintime, Date endtime){
        StringBuilder sql =new StringBuilder();
        if(null != productionplantid){
            sql.append("productionplantid = ").append(productionplantid);
        }
        if(null != submituserid){
            if(sql.length() == 0){
                sql.append("submituserid = ").append(submituserid);
            }else{
                sql.append(" and submituserid = ").append(submituserid);
            }
        }
        if(null != truckid){
            if(sql.length() == 0){
                sql.append("truckid = ").append(truckid);
            }else{
                sql.append(" and truckid = ").append(truckid);
            }
        }
        if(null != confirmuserid){
            if(sql.length() == 0){
                sql.append("confirmuserid = ").append(confirmuserid);
            }else{
                sql.append(" and confirmuserid = ").append(confirmuserid);
            }
        }
        if(StringUtils.isNotBlank(status)){
            if(sql.length() == 0){
                sql.append("status = '").append(status).append("'");
            }else{
                sql.append(" and status = '").append(status).append("'");
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
        Rmap<Etruckproductionplant> map = etruckproductionplantService.selectByPage(page, rows, "id", "desc", sql.toString());
        if (null != map.getList()) {
            map.setStatus(260);
        }else{
            map.setStatus(240);
        }
        return map;
    }

    /**
     * @Auther: Barry
     * @Date: 2020/4/28
     * @Param: page
     * @Param: rows
     * @Param: productionplantid
     * @Param: truckid
     * @Param: submituserid
     * @Param: confirmuserid
     * @Param: status
     * @Param: begintime
     * @Param: endtime
     * @Description: v
     */
    @AuthToken
    @RequestMapping(value = "/selectVByPage", method = RequestMethod.GET)
    public Rmap<Etruckproductionplantv> selectVByPage(Integer page, Integer rows, Integer productionplantid, Integer submituserid, Integer confirmuserid, Integer truckid, String status, Date begintime, Date endtime){
        StringBuilder sql =new StringBuilder();
        if(null != productionplantid){
            sql.append("productionplantid = ").append(productionplantid);
        }
        if(null != submituserid){
            if(sql.length() == 0){
                sql.append("submituserid = ").append(submituserid);
            }else{
                sql.append(" and submituserid = ").append(submituserid);
            }
        }
        if(null != confirmuserid){
            if(sql.length() == 0){
                sql.append("confirmuserid = ").append(confirmuserid);
            }else{
                sql.append(" and confirmuserid = ").append(confirmuserid);
            }
        }
        if(null != truckid){
            if(sql.length() == 0){
                sql.append("truckid = ").append(truckid);
            }else{
                sql.append(" and truckid = ").append(truckid);
            }
        }
        if(StringUtils.isNotBlank(status)){
            if(sql.length() == 0){
                sql.append("status = '").append(status).append("'");
            }else{
                sql.append(" and status = '").append(status).append("'");
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
        Rmap<Etruckproductionplantv> map = etruckproductionplantService.selectVByPage(page, rows, "id", "desc", sql.toString());
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
    public Rmap<Etruckproductionplant> selectByPrimaryKey(Integer id){
        Rmap<Etruckproductionplant> map = etruckproductionplantService.selectByPrimaryKey(id);
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
    public  Rmap<Etruckproductionplantv> selectByTruckid(Integer truckid){
        Rmap<Etruckproductionplantv> map = etruckproductionplantService.selectVBySql("truckid = "+truckid);
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
     * @Description: 添加
     */
    @AuthToken
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public Rmap<Etruckproductionplant> insert(Etruckproductionplant record) {
        Rmap<Etruckproductionplant> map = new Rmap<Etruckproductionplant>();
        Rmap<Etruckproductionplant> rmap = etruckproductionplantService.insert(record);
        if (null != rmap.getObject()) {
            map.setStatus(260);
        } else {
            map.setStatus(240);
            if (null == rmap.getMessage()) {
                map.setMessage("添加车次生产厂失败");
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
     * @Description: 更新
     */
    @AuthToken
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public Rmap<Etruckproductionplant> update(Etruckproductionplant record) {
        Rmap<Etruckproductionplant> map = new Rmap<Etruckproductionplant>();
        Rmap<Etruckproductionplant> rmap = etruckproductionplantService.update(record);
        if (null != rmap.getObject()) {
            map.setStatus(260);
        } else {
            map.setStatus(240);
            if (null == rmap.getMessage()) {
                map.setMessage("修改车次生产厂失败");
            } else {
                map.setMessage(rmap.getMessage());
            }
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
    public Rmap<Etruckproductionplant> delete(Integer id) {
        Rmap<Etruckproductionplant> map = etruckproductionplantService.delete(id);
        if(null != map.getObject()){
            map.setStatus(260);
        }else {
            map.setStatus(240);
            if(null == map.getMessage()) {
                map.setMessage("删除车次生产厂失败");
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
    public Rmap<Etruckproductionplant> deletes(@RequestBody Integer[] ids){
        String strIds =  ArrayUtils.toString(ids, ",").replace('{', '(').replace('}', ')');
        Rmap<Etruckproductionplant> map = etruckproductionplantService.deletes(strIds);
        if(null != map.getList()){
            map.setStatus(260);
        }else{
            map.setStatus(240);
            if(null == map.getMessage()){
                map.setMessage("删除车次生产厂失败");
            }
        }
        return map;
    }

    /**
     * @Auther: Barry
     * @Date: 2020/4/10
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
        select = new Select();
        select.setText("审核不通过");
        select.setValue("审核不通过");
        list.add(select);
        map.setStatus(260);
        map.setList(list);
        return map;
    }

}