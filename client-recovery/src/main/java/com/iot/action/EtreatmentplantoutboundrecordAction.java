package com.iot.action;

import com.iot.bean.Etreatmentplantoutboundrecord;
import com.iot.bean.Etreatmentplantoutboundrecordv;
import com.iot.bean.Rmap;
import com.iot.service.EtreatmentplantoutboundrecordService;
import com.iot.util.AuthToken;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/outboundrecord")
public class EtreatmentplantoutboundrecordAction extends BaseController{
    @Resource
    private EtreatmentplantoutboundrecordService etreatmentplantoutboundrecordService;

    /**
     * @Auther: Barry
     * @Date: 2020/6/1
     * @Param:
     * @Description: 查询所有
     */
    @AuthToken
    @RequestMapping(value = "/selectAll", method = RequestMethod.GET)
    public Rmap<Etreatmentplantoutboundrecord> selectAll(){
        Rmap<Etreatmentplantoutboundrecord> map = etreatmentplantoutboundrecordService.selectAll();
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
    public Rmap<Etreatmentplantoutboundrecord> selectByPage(Integer page, Integer rows, Integer categoryid, Integer treatmentplantid,Integer trucktreatmentplantreceiveid,Date begintime, Date endtime){
        Rmap<Etreatmentplantoutboundrecord> map = new Rmap<Etreatmentplantoutboundrecord>();
        if(null == treatmentplantid || null == trucktreatmentplantreceiveid){
            map.setStatus(240);
            map.setMessage("参数不全");
            return map;
        }
        StringBuilder sql =new StringBuilder();
        sql.append("treatmentplantid = "+treatmentplantid);
        sql.append(" and trucktreatmentplantreceiveid = "+trucktreatmentplantreceiveid);
        if(null != categoryid){
            sql.append(" and categoryid = "+categoryid);
        }
        if (null != begintime && null != endtime) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            sql.append(" and time >= '").append(format.format(begintime)).append("' and time <= '").append(format.format(endtime)).append("'");
        }
        map = etreatmentplantoutboundrecordService.selectByPage(page, rows, "id", "desc", sql.toString());
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
    public Rmap<Etreatmentplantoutboundrecord> selectByPrimaryKey(Integer id){
        Rmap<Etreatmentplantoutboundrecord> map = etreatmentplantoutboundrecordService.selectByPrimaryKey(id);
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
    public Rmap<Etreatmentplantoutboundrecordv> selectVAll(){
        Rmap<Etreatmentplantoutboundrecordv> map = etreatmentplantoutboundrecordService.selectVAll();
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
    public Rmap<Etreatmentplantoutboundrecordv> selectVByPage(Integer page, Integer rows,  Integer treatmentplantid, Integer trucktreatmentplantreceiveid, Integer categoryid,Date begintime, Date endtime){
        Rmap<Etreatmentplantoutboundrecordv> map = new Rmap<Etreatmentplantoutboundrecordv>();
        if(null == treatmentplantid || null == trucktreatmentplantreceiveid){
            map.setStatus(240);
            map.setMessage("参数不全");
            return map;
        }
        StringBuilder sql =new StringBuilder();
        sql.append("treatmentplantid = "+treatmentplantid);
        sql.append(" and trucktreatmentplantreceiveid = "+trucktreatmentplantreceiveid);
        if(null != categoryid){
            sql.append(" and categoryid = "+categoryid);
        }
        if (null != begintime && null != endtime) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            sql.append(" and time >= '").append(format.format(begintime)).append("' and time <= '").append(format.format(endtime)).append("'");
        }
        map = etreatmentplantoutboundrecordService.selectVByPage(page, rows, "id", "desc", sql.toString());
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
    public Rmap<Etreatmentplantoutboundrecordv> selectVByPrimaryKey(Integer id){
        Rmap<Etreatmentplantoutboundrecordv> map = etreatmentplantoutboundrecordService.selectVByPrimaryKey(id);
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
    public Rmap<Etreatmentplantoutboundrecord> insert(Etreatmentplantoutboundrecord record) {
        record.setTime(new Date());
        Rmap<Etreatmentplantoutboundrecord> map = etreatmentplantoutboundrecordService.insert(record);
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
    public Rmap<Etreatmentplantoutboundrecord> update(Etreatmentplantoutboundrecord record) {
        Rmap<Etreatmentplantoutboundrecord> map = etreatmentplantoutboundrecordService.update(record);
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
    public Rmap<Etreatmentplantoutboundrecord> delete(Integer id) {
        Rmap<Etreatmentplantoutboundrecord> map = etreatmentplantoutboundrecordService.delete(id);
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
    public Rmap<Etreatmentplantoutboundrecord> deletes(@RequestBody Integer[] ids){
        String strIds =  ArrayUtils.toString(ids, ",").replace('{', '(').replace('}', ')');
        Rmap<Etreatmentplantoutboundrecord> map = etreatmentplantoutboundrecordService.deletes(strIds);
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

}