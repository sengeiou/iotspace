package com.iot.action;

import com.iot.bean.Eoperate;
import com.iot.bean.Rmap;
import com.iot.bean.Select;
import com.iot.service.EoperateService;
import com.iot.util.AuthToken;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/operate")
public class EoperateAction extends  BaseController{
    @Resource
    private EoperateService eoperateService;

    /*
     * @Date 2020/7/20 18:30
     * @Description 操作手册最新一条
     * @Param
     * @Return
     */
    @RequestMapping(value = "/selectLimit", method = RequestMethod.GET)
    public Rmap<Eoperate> selectLimit(String type){
        Rmap<Eoperate> map = new Rmap<Eoperate>();
        if(StringUtils.isBlank(type)){
            map.setStatus(240);
            map.setMessage("参数不全");
            return map;
        }
        map = eoperateService.selectByPage(1, 4, "id", "desc", "type = '"+type+"'");
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
    public Rmap<Eoperate> selectAll(){
        Rmap<Eoperate> map = eoperateService.selectAll();
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
    public Rmap<Eoperate> selectByPage(Integer page, Integer rows, String item,String type){
        StringBuilder sql =new StringBuilder();
        if(StringUtils.isNotBlank(item)){
            sql.append("item like ").append("'%25").append(item).append("%25'");
        }
        if(StringUtils.isNotBlank(type)){
            if(sql.length() == 0){
                sql.append("type = '"+type+"'");
            }else{
                sql.append(" and type = '"+type+"'");
            }
        }
        Rmap<Eoperate> map = eoperateService.selectByPage(page, rows, "id", "desc", sql.toString());
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
    public Rmap<Eoperate> selectByPrimaryKey(Integer id){
        Rmap<Eoperate> map = eoperateService.selectByPrimaryKey(id);
        if(null != map.getObject()){
            map.setStatus(260);
        }else{
            map.setStatus(240);
        }
        return map;
    }

    /*
     * @Date 2020/7/20 18:19
     * @Description 添加操作手册
     * @Param
     * @Return
     */
    @AuthToken
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public Rmap<Eoperate> insert(Eoperate record) {
        record.setTime(new Date());
        Rmap<Eoperate> map = eoperateService.insert(record);
        if(null != map.getObject()){
            map.setStatus(260);
        }else{
            map.setStatus(240);
            if(null == map.getMessage()){
                map.setMessage("添加操作手册失败");
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
    public Rmap<Eoperate> update(Eoperate record) {
        Rmap<Eoperate> map = eoperateService.update(record);
        if(null != map.getObject()){
            map.setStatus(260);
        }else{
            map.setStatus(240);
            if(null == map.getMessage()){
                map.setMessage("修改操作手册失败");
            }
        }
        return map;
    }

    /*
     * @Date 2020/8/4 15:09
     * @Description 类型列表
     * @Param
     * @Return
     */
    @AuthToken
    @RequestMapping(value = "/typeList", method = RequestMethod.GET)
    public Rmap<Select> typeList(){
        Rmap<Select> map = new Rmap<Select>();
        List<Select> list = new ArrayList<Select>();
        Select select = new Select();
        select.setText("监管单位");
        select.setValue("监管单位");
        list.add(select);
        select = new Select();
        select.setText("医疗机构");
        select.setValue("医疗机构");
        list.add(select);
        select = new Select();
        select.setText("回收物流");
        select.setValue("回收物流");
        list.add(select);
        select = new Select();
        select.setText("回收利用企业");
        select.setValue("回收利用企业");
        list.add(select);
        select = new Select();
        select.setText("再利用企业");
        select.setValue("再利用企业");
        list.add(select);
        map.setStatus(260);
        map.setList(list);
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
    public Rmap<Eoperate> delete(Integer id) {
        Rmap<Eoperate> map = eoperateService.delete(id);
        if(null != map.getObject()){
            map.setStatus(260);
        }else {
            map.setStatus(240);
            if(null == map.getMessage()) {
                map.setMessage("删除操作手册失败");
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
    public Rmap<Eoperate> deletes(@RequestBody Integer[] ids){
        String strIds =  ArrayUtils.toString(ids, ",").replace('{', '(').replace('}', ')');
        Rmap<Eoperate> map = eoperateService.deletes(strIds);
        if(null != map.getList()){
            map.setStatus(260);
        }else{
            map.setStatus(240);
            if(null == map.getMessage()){
                map.setMessage("删除操作手册失败");
            }
        }
        return map;
    }

}