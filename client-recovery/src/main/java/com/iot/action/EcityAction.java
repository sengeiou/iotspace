package com.iot.action;

import com.iot.bean.Ecity;
import com.iot.bean.Ecityv;
import com.iot.bean.Rmap;
import com.iot.service.EcityService;
import com.iot.util.AuthToken;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/city")
public class EcityAction extends  BaseController{
    @Resource
    private EcityService ecityService;

    /**
     * @Auther: Barry
     * @Date: 2020/4/9
     * @Param:
     * @Description: 查询所有
     */
    @AuthToken
    @RequestMapping(value = "/selectAll", method = RequestMethod.GET)
    public Rmap<Ecity> selectAll(){
        Rmap<Ecity> map = ecityService.selectAll();
        if(null != map.getList()){
            map.setStatus(260);
        }else{
            map.setStatus(240);
        }
        return map;
    }

    /**
     * @Auther: Barry
     * @Date: 2020/4/9
     * @Param: page
     * @Param: rows
     * @Param: item
     * @Param: provinceid
     * @Description: 查询
     */
    @AuthToken
    @RequestMapping(value = "/selectByPage", method = RequestMethod.GET)
    public Rmap<Ecity> selectByPage(Integer page, Integer rows, String item, Integer provinceid){
        StringBuilder sql =new StringBuilder();
        if(StringUtils.isNotBlank(item)){
            sql.append("item like ").append("'%25").append(item).append("%25'");
        }
        if(null != provinceid){
            if(sql.length() == 0){
                sql.append("provinceid = ").append(provinceid);
            }else{
                sql.append(" and provinceid = ").append(provinceid);
            }
        }
        Rmap<Ecity> map = ecityService.selectByPage(page, rows, "id", "desc", sql.toString());
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
     * @Param: provinceid
     * @Description: 根据省份查询
     */
    @AuthToken
    @RequestMapping(value = "/selectByProvinceId", method = RequestMethod.GET)
    public Rmap<Ecity> selectByProvinceId(Integer provinceid){
        Rmap<Ecity> map = ecityService.selectBySql("provinceid = "+provinceid);
        if (null != map.getList()) {
            map.setStatus(260);
        }else{
            map.setStatus(240);
        }
        return map;
    }

    @AuthToken
    @RequestMapping(value = "/selectByPrimaryKey", method = RequestMethod.GET)
    public Rmap<Ecity> selectByPrimaryKey(Integer id){
        Rmap<Ecity> map = ecityService.selectByPrimaryKey(id);
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
     * @Description: 城市
     */
    @AuthToken
    @RequestMapping(value = "/selectVAll", method = RequestMethod.GET)
    public Rmap<Ecityv> selectVAll(){
        Rmap<Ecityv> map = ecityService.selectVAll();
        if(null != map.getList()){
            map.setStatus(260);
        }else{
            map.setStatus(240);
        }
        return map;
    }

    /**
     * @Auther: Barry
     * @Date: 2020/4/9
     * @Param: page
     * @Param: rows
     * @Param: item
     * @Param: provinceid
     * @Description: 城市v
     */
    @AuthToken
    @RequestMapping(value = "/selectVByPage", method = RequestMethod.GET)
    public Rmap<Ecityv> selectVByPage(Integer page, Integer rows, String item, Integer provinceid){
        StringBuilder sql =new StringBuilder();
        if(StringUtils.isNotBlank(item)){
            sql.append("item like ").append("'%25").append(item).append("%25'");
        }
        if(null != provinceid){
            if(sql.length() == 0){
                sql.append("provinceid = ").append(provinceid);
            }else{
                sql.append(" and provinceid = ").append(provinceid);
            }
        }
        Rmap<Ecityv> map = ecityService.selectVByPage(page, rows, "id", "desc", sql.toString());
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
    public Rmap<Ecityv> selectVByPrimaryKey(Integer id){
        Rmap<Ecityv> map = ecityService.selectVByPrimaryKey(id);
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
     * @Description: 添加城市
     */
    @AuthToken
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public Rmap<Ecityv> insert(Ecity record) {
        Rmap<Ecityv> map = new Rmap<Ecityv>();
        if(isValidate(record.getItem(), record.getProvinceid(), 0)){
            Rmap<Ecity> rmap = ecityService.insert(record);
            if(null != rmap.getObject()){
                map.setStatus(260);
                map.setObject(ecityService.selectVByPrimaryKey(rmap.getObject().getId()).getObject());
            }else{
                map.setStatus(240);
                if(null == map.getMessage()){
                    map.setMessage("添加城市失败");
                }
            }
        }else{
            map.setStatus(240);
            map.setMessage("该城市已存在");
        }
        return map;
    }

    /**
     * @Auther: Barry
     * @Date: 2019/9/10
     * @Param: record
     * @Description: 城市更新
     */
    @AuthToken
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public Rmap<Ecityv> update(Ecity record) {
        Rmap<Ecityv> map = new Rmap<Ecityv>();
        if(isValidate(record.getItem(), record.getProvinceid(), record.getId())){
            Rmap<Ecity> rmap = ecityService.update(record);
            if(null != rmap.getObject()){
                map.setStatus(260);
                map.setObject(ecityService.selectVByPrimaryKey(record.getId()).getObject());
            }else{
                map.setStatus(240);
                if(null == map.getMessage()){
                    map.setMessage("修改城市失败");
                }
            }
        }else{
            map.setStatus(240);
            map.setMessage("该城市已存在");
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
    public Rmap<Ecity> delete(Integer id) {
        Rmap<Ecity> map = ecityService.delete(id);
        if(null != map.getObject()){
            map.setStatus(260);
        }else {
            map.setStatus(240);
            if(null == map.getMessage()) {
                map.setMessage("删除城市失败");
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
    public Rmap<Ecity> deletes(@RequestBody Integer[] ids){
        String strIds =  ArrayUtils.toString(ids, ",").replace('{', '(').replace('}', ')');
        Rmap<Ecity> map = ecityService.deletes(strIds);
        if(null != map.getList()){
            map.setStatus(260);
        }else{
            map.setStatus(240);
            if(null == map.getMessage()){
                map.setMessage("删除城市失败");
            }
        }
        return map;
    }

    /**
     * @Auther: Barry
     * @Date: 2019/9/10
     * @Param: item 城市名
     * @Param: provinceid 省id
     * @Param: id
     * @Description: 数据唯一性校验
     */
    public boolean isValidate(String item, Integer provinceid, Integer id){
        Rmap<Ecity> map = ecityService.selectBySql("item = '" + item + "' and provinceid = " + provinceid + " and id != " + id);
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