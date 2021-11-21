package com.iot.action;

import com.iot.bean.Eplat;
import com.iot.bean.Eplatv;
import com.iot.bean.Rmap;
import com.iot.service.EplatService;
import com.iot.util.AuthToken;
import com.iot.util.CommonUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/plat")
public class EplatAction extends BaseController{
    @Resource
    private EplatService eplatService;

    @AuthToken
    @RequestMapping(value = "/selectAll", method = RequestMethod.GET)
    public Rmap<Eplat> selectAll(){
        Rmap<Eplat> map = eplatService.selectAll();
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
     * @Param: cityid
     * @Description: 查询
     */
    @AuthToken
    @RequestMapping(value = "/selectByPage", method = RequestMethod.GET)
    public Rmap<Eplat> selectByPage(Integer page, Integer rows, String item, Integer cityid){
        StringBuilder sql =new StringBuilder();
        if(StringUtils.isNotBlank(item)){
            sql.append("item like ").append("'%25").append(item).append("%25'");
        }
        if(null != cityid){
            if(sql.length() == 0){
                sql.append("cityid = ").append(cityid);
            }else{
                sql.append(" and cityid = ").append(cityid);
            }
        }
        Rmap<Eplat> map = eplatService.selectByPage(page, rows, "id", "desc", sql.toString());
        if (null != map.getList()) {
            map.setStatus(260);
        }else{
            map.setStatus(240);
        }
        return map;
    }

    /**
     * @Auther: Barry
     * @Date: 2019/9/11
     * @Param: cityid 城市id
     * @Description: 根据城市id查询区
     */
    @AuthToken
    @RequestMapping(value = "/selectByCityId", method = RequestMethod.GET)
    public Rmap<Eplat> selectByCityId(Integer cityid){
        Rmap<Eplat> map = eplatService.selectBySql("cityid = "+cityid);
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
     * @Description: 主键查询
     */
    @AuthToken
    @RequestMapping(value = "/selectByPrimaryKey", method = RequestMethod.GET)
    public Rmap<Eplat> selectByPrimaryKey(Integer id){
        Rmap<Eplat> map = eplatService.selectByPrimaryKey(id);
        if(null != map.getObject()){
            map.setStatus(260);
        }else{
            map.setStatus(240);
        }
        return map;
    }

    @AuthToken
    @RequestMapping(value = "/selectVAll", method = RequestMethod.GET)
    public Rmap<Eplatv> selectVAll(){
        Rmap<Eplatv> map = eplatService.selectVAll();
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
     * @Param: cityid
     * @Description: v查询
     */
    @AuthToken
    @RequestMapping(value = "/selectVByPage", method = RequestMethod.GET)
    public Rmap<Eplatv> selectVByPage(Integer page, Integer rows, String item, Integer cityid){
        StringBuilder sql =new StringBuilder();
        if(StringUtils.isNotBlank(item)){
            sql.append("item like ").append("'%25").append(item).append("%25'");
        }
        if(null != cityid){
            if(sql.length() == 0){
                sql.append("cityid = ").append(cityid);
            }else{
                sql.append(" and cityid = ").append(cityid);
            }
        }
        Rmap<Eplatv> map = eplatService.selectVByPage(page, rows, "id", "desc", sql.toString());
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
     * @Description: v主键
     */
    @AuthToken
    @RequestMapping(value = "/selectVByPrimaryKey", method = RequestMethod.GET)
    public Rmap<Eplatv> selectVByPrimaryKey(Integer id){
        Rmap<Eplatv> map = eplatService.selectVByPrimaryKey(id);
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
     * @Description: 添加区域
     */
    @AuthToken
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public Rmap<Eplatv> insert(Eplat record) {
        Rmap<Eplatv> map = new Rmap<Eplatv>();
        if(isValidate(record.getItem(), record.getCityid(), 0)){
            Rmap<Eplat> rmap = eplatService.insert(record);
            if(null != rmap.getObject()){
                map.setStatus(260);
                map.setObject(eplatService.selectVByPrimaryKey(rmap.getObject().getId()).getObject());
            }else{
                map.setStatus(240);
                if(null == map.getMessage()){
                    map.setMessage("添加区域失败");
                }
            }
        }else{
            map.setStatus(240);
            map.setMessage("该区域已存在");
        }
        return map;
    }

    /**
     * @Auther: Barry
     * @Date: 2019/9/10
     * @Param: record
     * @Description: 更新区
     */
    @AuthToken
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public Rmap<Eplatv> update(Eplat record) {
        Rmap<Eplatv> map = new Rmap<Eplatv>();
        if(isValidate(record.getItem(), record.getCityid(), record.getId())){
            Rmap<Eplat> rmap = eplatService.update(record);
            if(null != rmap.getObject()){
                map.setStatus(260);
                map.setObject(eplatService.selectVByPrimaryKey(record.getId()).getObject());
            }else{
                map.setStatus(240);
                if(null == map.getMessage()){
                    map.setMessage("修改区域失败");
                }
            }
        }else{
            map.setStatus(240);
            map.setMessage("该区域已存在");
        }
        return map;
    }

    /**
     * @Auther: Barry
     * @Date: 2019/9/9
     * @Param: id 区域主键id
     * @Description: 删除区域
     */
    @AuthToken
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public Rmap<Eplat> delete(Integer id) {
        Rmap<Eplat> map = eplatService.delete(id);
        if(null != map.getObject()){
            map.setStatus(260);
        }else {
            map.setStatus(240);
            if(null == map.getMessage()) {
                map.setMessage("删除区域失败");
            }
        }
        return map;
    }

    /**
     * @Auther: Barry
     * @Date: 2019/9/9
     * @Param: ids 区域主键id
     * @Description: 删除区域
     */
    @AuthToken
    @RequestMapping(value = "/deletes", method = RequestMethod.DELETE)
    public Rmap<Eplat> deletes(@RequestBody Integer[] ids){
        String strIds =  ArrayUtils.toString(ids, ",").replace('{', '(').replace('}', ')');
        Rmap<Eplat> map = eplatService.deletes(strIds);
        if(null != map.getList()){
            map.setStatus(260);
        }else{
            map.setStatus(240);
            if(null == map.getMessage()){
                map.setMessage("删除区域失败");
            }
        }
        return map;
    }

    /**
     * @Auther: Barry
     * @Date: 2019/9/10
     * @Param: item 区名
     * @Param: cityid 城市id
     * @Param: id
     * @Description: 数据唯一性校验
     */
    public boolean isValidate(String item, Integer cityid, Integer id){
        Rmap<Eplat> map = eplatService.selectBySql("item = '" + item + "' and cityid = "+ cityid + " and id != " + id);
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