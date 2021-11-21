package com.iot.action;

import com.iot.bean.Enotice;
import com.iot.bean.Enoticev;
import com.iot.bean.Rmap;
import com.iot.bean.Select;
import com.iot.service.EnoticeService;
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
@RequestMapping("/notice")
public class EnoticeAction extends  BaseController{
    @Resource
    private EnoticeService enoticeService;

    /**
     * 查询三条 图片不为null, 重要类型 重要, 限制三条  首页轮播图
     * @return
     */
    @RequestMapping(value = "/selectLimit3", method = RequestMethod.GET)
    public Rmap<Enotice> selectLimit3(){
        StringBuilder sql =new StringBuilder();
        sql.append("img is not null ").append(" and importance = '重要' ").append(" order by id desc limit 3");
        Rmap<Enotice> map = enoticeService.selectBySql(sql.toString());
        if(null != map.getList()){
            map.setStatus(260);
        }else{
            map.setStatus(240);
        }
        return map;
    }

    /**
     * 首页 通知公告 政策文件
     * @return
     */
    @RequestMapping(value = "/homePageNotice", method = RequestMethod.GET)
    public Rmap<Enotice> homePageNotice(Integer page, Integer rows,String type){
        Rmap<Enotice> map = new Rmap<Enotice>();
        if(StringUtils.isBlank(type)){
            map.setStatus(240);
            map.setMessage("参数不全");
            return map;
        }
        StringBuilder sql =new StringBuilder();
        sql.append("type = '"+type+"'");
        map = enoticeService.selectByPage(page, rows, "id", "desc", sql.toString());
        if (null != map.getList()) {
            map.setStatus(260);
        }else{
            map.setStatus(240);
        }
        return map;
    }

    /**
     * 首页更多详情
     * @param id
     * @return
     */
    @RequestMapping(value = "/selectById", method = RequestMethod.GET)
    public Rmap<Enotice> selectById(Integer id){
        Rmap<Enotice> map = enoticeService.selectByPrimaryKey(id);
        if(null != map.getObject()){
            map.setStatus(260);
        }else{
            map.setStatus(240);
        }
        return map;
    }

    /*
     * @Date 2020/7/20 18:30
     * @Description 通知最新一条
     * @Param
     * @Return
     */
    @RequestMapping(value = "/selectLimit", method = RequestMethod.GET)
    public Rmap<Enotice> selectLimit(){
        Rmap<Enotice> map = enoticeService.selectByPage(1, 1, "id", "desc", null);
        if(null != map.getList()){
            map.setStatus(260);
        }else{
            map.setStatus(240);
        }
        return map;
    }

   /*
    * @Date 2020/7/20 18:15
    * @Description 查询所有
    * @Param
    * @Return
    */
    @AuthToken
    @RequestMapping(value = "/selectAll", method = RequestMethod.GET)
    public Rmap<Enotice> selectAll(){
        Rmap<Enotice> map = enoticeService.selectAll();
        if(null != map.getList()){
            map.setStatus(260);
        }else{
            map.setStatus(240);
        }
        return map;
    }

    /**
     * v查询所有
     * @return
     */
    @AuthToken
    @RequestMapping(value = "/selectVAll", method = RequestMethod.GET)
    public Rmap<Enoticev> selectVAll(){
        Rmap<Enoticev> map = enoticeService.selectVAll();
        if(null != map.getList()){
            map.setStatus(260);
        }else{
            map.setStatus(240);
        }
        return map;
    }

    /*
     * @Date 2020/7/20 18:15
     * @Description 分页查询
     * @Param
     * @Return
     */
    @AuthToken
    @RequestMapping(value = "/selectByPage", method = RequestMethod.GET)
    public Rmap<Enotice> selectByPage(Integer page, Integer rows, String item,String importance, String type){
        StringBuilder sql =new StringBuilder();
        if(StringUtils.isNotBlank(item)){
            sql.append("item like ").append("'%25").append(item).append("%25'");
        }
        if(StringUtils.isNotBlank(importance)){
            if(sql.length() == 0){
                sql.append("importance = '"+importance+"'");
            }else{
                sql.append(" and importance = '"+importance+"'");
            }
        }
        if(StringUtils.isNotBlank(type)){
            if(sql.length() == 0){
                sql.append("type = '"+type+"'");
            }else{
                sql.append(" and type = '"+type+"'");
            }
        }
        Rmap<Enotice> map = enoticeService.selectByPage(page, rows, "id", "desc", sql.toString());
        if (null != map.getList()) {
            map.setStatus(260);
        }else{
            map.setStatus(240);
        }
        return map;
    }

    /**
     * v 分页查询
     * @param page
     * @param rows
     * @param item
     * @param importance
     * @param type
     * @return
     */
    @AuthToken
    @RequestMapping(value = "/selectVByPage", method = RequestMethod.GET)
    public Rmap<Enoticev> selectVByPage(Integer page, Integer rows, String item,String importance, String type){
        StringBuilder sql =new StringBuilder();
        if(StringUtils.isNotBlank(item)){
            sql.append("item like ").append("'%25").append(item).append("%25'");
        }
        if(StringUtils.isNotBlank(importance)){
            if(sql.length() == 0){
                sql.append("importance = '"+importance+"'");
            }else{
                sql.append(" and importance = '"+importance+"'");
            }
        }
        if(StringUtils.isNotBlank(type)){
            if(sql.length() == 0){
                sql.append("type = '"+type+"'");
            }else{
                sql.append(" and type = '"+type+"'");
            }
        }
        Rmap<Enoticev> map = enoticeService.selectVByPage(page, rows, "id", "desc", sql.toString());
        if (null != map.getList()) {
            map.setStatus(260);
        }else{
            map.setStatus(240);
        }
        return map;
    }

    /*
     * @Date 2020/7/20 18:16
     * @Description 主键查询
     * @Param
     * @Return
     */
    @AuthToken
    @RequestMapping(value = "/selectByPrimaryKey", method = RequestMethod.GET)
    public Rmap<Enotice> selectByPrimaryKey(Integer id){
        Rmap<Enotice> map = enoticeService.selectByPrimaryKey(id);
        if(null != map.getObject()){
            map.setStatus(260);
        }else{
            map.setStatus(240);
        }
        return map;
    }

    /**
     * V 主键查询
     * @param id
     * @return
     */
    @AuthToken
    @RequestMapping(value = "/selectVByPrimaryKey", method = RequestMethod.GET)
    public Rmap<Enoticev> selectVByPrimaryKey(Integer id){
        Rmap<Enoticev> map = enoticeService.selectVByPrimaryKey(id);
        if(null != map.getObject()){
            map.setStatus(260);
        }else{
            map.setStatus(240);
        }
        return map;
    }

    /*
     * @Date 2020/7/20 18:19
     * @Description 添加通知
     * @Param
     * @Return
     */
    @AuthToken
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public Rmap<Enotice> insert(Enotice record) {
        Rmap<Enotice> map = new  Rmap<Enotice>();
        if(null == record.getUserid()){
            map.setStatus(240);
            map.setMessage("参数不全");
            return map;
        }
        record.setTime(new Date());
        map = enoticeService.insert(record);
        if(null != map.getObject()){
            map.setStatus(260);
        }else{
            map.setStatus(240);
            if(null == map.getMessage()){
                map.setMessage("添加通知失败");
            }
        }
        return map;
    }

    /*
     * @Date 2020/7/20 18:19
     * @Description 更新
     * @Param
     * @Return
     */
    @AuthToken
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public Rmap<Enotice> update(Enotice record) {
        Rmap<Enotice> map = new Rmap<Enotice>();
        if(null == record.getUserid()){
            map.setStatus(240);
            map.setMessage("参数不全");
            return map;
        }
        map = enoticeService.update(record);
        if(null != map.getObject()){
            map.setStatus(260);
        }else{
            map.setStatus(240);
            if(null == map.getMessage()){
                map.setMessage("修改通知失败");
            }
        }
        return map;
    }

    /**
     * @Auther: Barry
     * @Date: 2019/9/9
     * @Param: id 城市主键id
     * @Description: 删除城市
     */
    @AuthToken
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public Rmap<Enotice> delete(Integer id) {
        Rmap<Enotice> map = enoticeService.delete(id);
        if(null != map.getObject()){
            map.setStatus(260);
        }else {
            map.setStatus(240);
            if(null == map.getMessage()) {
                map.setMessage("删除通知失败");
            }
        }
        return map;
    }

    /**
     * @Auther: Barry
     * @Date: 2019/9/9
     * @Param: ids 城市主键Id
     * @Description: 删除城市
     */
    @AuthToken
    @RequestMapping(value = "/deletes", method = RequestMethod.DELETE)
    public Rmap<Enotice> deletes(@RequestBody Integer[] ids){
        String strIds =  ArrayUtils.toString(ids, ",").replace('{', '(').replace('}', ')');
        Rmap<Enotice> map = enoticeService.deletes(strIds);
        if(null != map.getList()){
            map.setStatus(260);
        }else{
            map.setStatus(240);
            if(null == map.getMessage()){
                map.setMessage("删除通知失败");
            }
        }
        return map;
    }

    /**
     * 公告类型  一般公告、政策文件
     * @return
     */
    @AuthToken
    @RequestMapping(value = "/typeList", method = RequestMethod.GET)
    public Rmap<Select> typeList(){
        Rmap<Select> map = new Rmap<Select>();
        List<Select> list = new ArrayList<Select>();
        Select select = new Select();
        select.setText("一般公告");
        select.setValue("一般公告");
        list.add(select);
        select = new Select();
        select.setText("政策文件");
        select.setValue("政策文件");
        list.add(select);
        map.setStatus(260);
        map.setList(list);
        return map;
    }

    /**
     * 重要程度  普通、重要
     * @return
     */
    @AuthToken
    @RequestMapping(value = "/importanceList", method = RequestMethod.GET)
    public Rmap<Select> importanceList(){
        Rmap<Select> map = new Rmap<Select>();
        List<Select> list = new ArrayList<Select>();
        Select select = new Select();
        select.setText("普通");
        select.setValue("普通");
        list.add(select);
        select = new Select();
        select.setText("重要");
        select.setValue("重要");
        list.add(select);
        map.setStatus(260);
        map.setList(list);
        return map;
    }


}