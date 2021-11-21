package com.iot.action;

import com.iot.bean.Evehicle;
import com.iot.bean.Evehiclev;
import com.iot.bean.Rmap;
import com.iot.service.EvehicleService;
import com.iot.util.AuthToken;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
@RequestMapping("/vehicle")
public class EvehicleAction extends BaseController{
    @Resource
    private EvehicleService evehicleService;

    /**
     * @Auther: Barry
     * @Date: 2020/5/29
     * @Param:
     * @Description: 查询所有
     */
    @AuthToken
    @RequestMapping(value = "/selectAll", method = RequestMethod.GET)
    public Rmap<Evehicle> selectAll(){
        Rmap<Evehicle> map = evehicleService.selectAll();
        if(null != map.getList()){
            map.setStatus(260);
        }else{
            map.setStatus(240);
        }
        return map;
    }

    /**
     * @Auther: Barry
     * @Date: 2020/6/5
     * @Param: transportcompanyid
     * @Description: 根据运输公司id 查询车辆
     */
    @AuthToken
    @RequestMapping(value = "/selectByTransportcompanyId", method = RequestMethod.GET)
    public Rmap<Evehicle> selectByTransportcompanyId(Integer transportcompanyid){
        Rmap<Evehicle> map = new Rmap<Evehicle>();
        if(null == transportcompanyid){
            map.setStatus(240);
            map.setMessage("参数不全");
            return map;
        }
        map = evehicleService.selectBySql("transportcompanyid = "+transportcompanyid);
        if(null != map.getList()){
            map.setStatus(260);
        }else{
            map.setStatus(240);
        }
        return map;
    }

    /*
     * @Date 2020/9/7 12:13
     * @Description  回收利用企业车辆
     * @Param
     * @Return
     */
    @AuthToken
    @RequestMapping(value = "/selectByTreatmentplant", method = RequestMethod.GET)
    public Rmap<Evehicle> selectByTreatmentplant(Integer treatmentplantid,Integer transportcompanyid){
        Rmap<Evehicle> map = new Rmap<Evehicle>();
        if(null == treatmentplantid){
            map.setStatus(240);
            map.setMessage("参数不全");
            return map;
        }
        if(null != treatmentplantid){
            map = evehicleService.selectBySql("transportcompanyid in ( select id from etransportcompany where treatmentplantid = "+treatmentplantid+")+");
        }else{
            map = evehicleService.selectBySql("transportcompanyid = "+transportcompanyid);
        }
        if(null != map.getList()){
            map.setStatus(260);
        }else{
            map.setStatus(240);
        }
        return map;
    }

    /**
     * @Auther: Barry
     * @Date: 2020/5/29
     * @Param: page
     * @Param: rows
     * @Param: number
     * @Param: transportcompanyid
     * @Description: 查询
     */
    @AuthToken
    @RequestMapping(value = "/selectByPage", method = RequestMethod.GET)
    public Rmap<Evehicle> selectByPage(Integer page, Integer rows, String number, Integer  transportcompanyid){
        StringBuilder sql = new StringBuilder();
        if (StringUtils.isNotBlank(number)) {
            sql.append("number like ").append("'%25").append(number).append("%25'");
        }
        if (null != transportcompanyid) {
           if(sql.length() == 0){
               sql.append("transportcompanyid = ").append(transportcompanyid);
           }else{
               sql.append(" and transportcompanyid = ").append(transportcompanyid);
           }
        }
        Rmap<Evehicle> map = evehicleService.selectByPage(page, rows, "id", "desc", sql.toString());
        if (null != map.getList()) {
            map.setStatus(260);
        } else {
            map.setStatus(240);
        }
        return map;
    }

    /**
     * @Auther: Barry
     * @Date: 2020/5/29
     * @Param: page
     * @Param: rows
     * @Param: number
     * @Param: transportcompanyid
     * @Description: 查询
     */
    @AuthToken
    @RequestMapping(value = "/selectVByPage", method = RequestMethod.GET)
    public Rmap<Evehiclev> selectVByPage(Integer page, Integer rows, String number, Integer  transportcompanyid){
        StringBuilder sql = new StringBuilder();
        if (StringUtils.isNotBlank(number)) {
            sql.append("number like ").append("'%25").append(number).append("%25'");
        }
        if (null != transportcompanyid) {
            if(sql.length() == 0){
                sql.append("transportcompanyid = ").append(transportcompanyid);
            }else{
                sql.append(" and transportcompanyid = ").append(transportcompanyid);
            }
        }
        Rmap<Evehiclev> map = evehicleService.selectVByPage(page, rows, "id", "desc", sql.toString());
        if (null != map.getList()) {
            map.setStatus(260);
        } else {
            map.setStatus(240);
        }
        return map;
    }

    /**
     * @Auther: Barry
     * @Date: 2020/5/29
     * @Param: id
     * @Description: 主键
     */
    @AuthToken
    @RequestMapping(value = "/selectByPrimaryKey", method = RequestMethod.GET)
    public Rmap<Evehicle> selectByPrimaryKey(Integer id){
        Rmap<Evehicle> map = evehicleService.selectByPrimaryKey(id);
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
    public Rmap<Evehicle> insert(Evehicle record) {
        Rmap<Evehicle> map = new Rmap<Evehicle>();
        if(isValidate(record.getNumber(), record.getTransportcompanyid(), 0)){
            Rmap<Evehicle> rmap = evehicleService.insert(record);
            if(null != rmap.getObject()){
                map.setStatus(260);
            }else{
                map.setStatus(240);
                if(null == rmap.getMessage()){
                    map.setMessage("添加车辆失败");
                }else{
                    map.setMessage(rmap.getMessage());
                }
            }
        }else{
            map.setStatus(240);
            map.setMessage("该车辆已存在");
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
    public Rmap<Evehicle> update(Evehicle record) {
        Rmap<Evehicle> map = new Rmap<Evehicle>();
        if(isValidate(record.getNumber(), record.getTransportcompanyid(), record.getId())){
            Rmap<Evehicle> rmap = evehicleService.update(record);
            if(null != rmap.getObject()){
                map.setStatus(260);
            }else{
                map.setStatus(240);
                if(null == rmap.getMessage()){
                    map.setMessage("修改车辆失败");
                }else{
                    map.setMessage(rmap.getMessage());
                }
            }
        }else{
            map.setStatus(240);
            map.setMessage("该车辆已存在");
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
    public Rmap<Evehicle> delete(Integer id) {
        Rmap<Evehicle> map = evehicleService.delete(id);
        if(null != map.getObject()){
            map.setStatus(260);
        }else {
            map.setStatus(240);
            if(null == map.getMessage()) {
                map.setMessage("删除车辆失败");
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
    public Rmap<Evehicle> deletes(@RequestBody Integer[] ids){
        String strIds =  ArrayUtils.toString(ids, ",").replace('{', '(').replace('}', ')');
        Rmap<Evehicle> map = evehicleService.deletes(strIds);
        if(null != map.getList()){
            map.setStatus(260);
        }else{
            map.setStatus(240);
            if(null == map.getMessage()){
                map.setMessage("删除车辆失败");
            }
        }
        return map;
    }

    /**
     * @Auther: Barry
     * @Date: 2020/5/29
     * @Param: number
     * @Param: transportcompanyid
     * @Param: id
     * @Description: 唯一想校验
     */
    public boolean isValidate(String number, Integer transportcompanyid, Integer id){
        Rmap<Evehicle> map = evehicleService.selectBySql("number = '" + number +"' and transportcompanyid =  "+transportcompanyid +" and id != " + id);
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