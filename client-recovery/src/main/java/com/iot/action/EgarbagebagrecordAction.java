package com.iot.action;

import com.iot.bean.Egarbagebagrecord;
import com.iot.bean.Egarbagebagrecordv;
import com.iot.bean.Rmap;
import com.iot.bean.Select;
import com.iot.service.EgarbagebagrecordService;
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
@RequestMapping("/garbagebagrecord")
public class EgarbagebagrecordAction extends BaseController{
    @Resource
    private EgarbagebagrecordService egarbagebagrecordService;

    /**
     * @Auther: Barry
     * @Date: 2020/3/25
     * @Param:
     * @Description: 查询所有
     */
    @AuthToken
    @RequestMapping(value = "/selectAll", method = RequestMethod.GET)
    public Rmap<Egarbagebagrecord> selectAll(){
        Rmap<Egarbagebagrecord> map = egarbagebagrecordService.selectAll();
        if(null != map.getList()){
            map.setStatus(260);
        }else{
            map.setStatus(240);
        }
        return map;
    }

    /**
     * @Auther: Barry
     * @Date: 2020/4/7
     * @Param: page
     * @Param: rows
     * @Param: userid
     * @Param: garbagebagid
     * @Param: status
     * @Param: begintime
     * @Param: endtime
     * @Description: 查询
     */
    @AuthToken
    @RequestMapping(value = "/selectByPage", method = RequestMethod.GET)
    public Rmap<Egarbagebagrecord> selectByPage(Integer page, Integer rows, Integer userid, Integer garbagebagid, String status, Date begintime, Date endtime){
        StringBuilder sql =new StringBuilder();
        if (null != userid) {
            sql.append("userid = ").append(userid);
        }
        if(null != garbagebagid){
            if(sql.length() == 0){
                sql.append("garbagebagid = ").append(garbagebagid);
            }else{
                sql.append(" and garbagebagid = ").append(garbagebagid);
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
        Rmap<Egarbagebagrecord> map = egarbagebagrecordService.selectByPage(page, rows, "id", "desc", sql.toString());
        if (null != map.getList()) {
            map.setStatus(260);
        }else{
            map.setStatus(240);
        }
        return map;
    }

    /**
     * @Auther: Barry
     * @Date: 2020/4/8
     * @Param: garbagebagid
     * @Description: 查询某一垃圾袋记录
     */
    @AuthToken
    @RequestMapping(value = "/selectVByGarbagebagId", method = RequestMethod.GET)
    public Rmap<Egarbagebagrecordv> selectVByGarbagebagId(Integer garbagebagid){
        Rmap<Egarbagebagrecordv> map = egarbagebagrecordService.selectVBySql("garbagebagid = "+garbagebagid+" order by id desc");
        if(null != map.getList()){
            map.setStatus(260);
        }else{
            map.setStatus(240);
        }
        return map;
    }

    /**
     * @Auther: Barry
     * @Date: 2020/4/7
     * @Param: page
     * @Param: rows
     * @Param: userid
     * @Param: garbagebagid
     * @Param: status
     * @Param: begintime
     * @Param: endtime
     * @Description: V查询
     */
    @AuthToken
    @RequestMapping(value = "/selectVByPage", method = RequestMethod.GET)
    public Rmap<Egarbagebagrecordv> selectVByPage(Integer page, Integer rows, Integer userid, Integer garbagebagid, String status, Date begintime, Date endtime){
        StringBuilder sql =new StringBuilder();
        if (null != userid) {
            sql.append("userid = ").append(userid);
        }
        if(null != garbagebagid){
            if(sql.length() == 0){
                sql.append("garbagebagid = ").append(garbagebagid);
            }else{
                sql.append(" and garbagebagid = ").append(garbagebagid);
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
        Rmap<Egarbagebagrecordv> map = egarbagebagrecordService.selectVByPage(page, rows, "id", "desc", sql.toString());
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
    public Rmap<Egarbagebagrecord> selectByPrimaryKey(Integer id){
        Rmap<Egarbagebagrecord> map = egarbagebagrecordService.selectByPrimaryKey(id);
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
    public Rmap<Egarbagebagrecord> insert(Egarbagebagrecord record) {
        Rmap<Egarbagebagrecord> map = new Rmap<Egarbagebagrecord>();
        Rmap<Egarbagebagrecord> rmap = egarbagebagrecordService.insert(record);
        if (null != rmap.getObject()) {
            map.setStatus(260);
        } else {
            map.setStatus(240);
            if (null == rmap.getMessage()) {
                map.setMessage("添加垃圾袋记录失败");
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
    public Rmap<Egarbagebagrecord> update(Egarbagebagrecord record) {
        Rmap<Egarbagebagrecord> map = new Rmap<Egarbagebagrecord>();
        Rmap<Egarbagebagrecord> rmap = egarbagebagrecordService.update(record);
        if (null != rmap.getObject()) {
            map.setStatus(260);
        } else {
            map.setStatus(240);
            if (null == rmap.getMessage()) {
                map.setMessage("修改垃圾袋记录失败");
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
    public Rmap<Egarbagebagrecord> delete(Integer id) {
        Rmap<Egarbagebagrecord> map = egarbagebagrecordService.delete(id);
        if(null != map.getObject()){
            map.setStatus(260);
        }else {
            map.setStatus(240);
            if(null == map.getMessage()) {
                map.setMessage("删除垃圾袋记录失败");
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
    public Rmap<Egarbagebagrecord> deletes(@RequestBody Integer[] ids){
        String strIds =  ArrayUtils.toString(ids, ",").replace('{', '(').replace('}', ')');
        Rmap<Egarbagebagrecord> map = egarbagebagrecordService.deletes(strIds);
        if(null != map.getList()){
            map.setStatus(260);
        }else{
            map.setStatus(240);
            if(null == map.getMessage()){
                map.setMessage("删除垃圾袋记录失败");
            }
        }
        return map;
    }

    /**
     * @Auther: Barry
     * @Date: 2020/4/7
     * @Param:
     * @Description: 状态列表
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
}