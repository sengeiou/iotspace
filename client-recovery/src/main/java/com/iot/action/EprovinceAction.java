package com.iot.action;

import com.iot.bean.Eprovince;
import com.iot.bean.Rmap;
import com.iot.service.EcityService;
import com.iot.service.EplatService;
import com.iot.service.EprovinceService;
import com.iot.util.AuthToken;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/province")
public class EprovinceAction extends BaseController{
    @Resource
    private EprovinceService eprovinceService;
    @Resource
    private EcityService ecityService;
    @Resource
    private EplatService eplatService;

    /**
     * @Auther: Barry
     * @Date: 2020/4/9
     * @Param:
     * @Description: 查询所有
     */
    @AuthToken
    @RequestMapping(value = "/selectAll", method = RequestMethod.GET)
    public Rmap<Eprovince> selectAll(){
        Rmap<Eprovince> map = eprovinceService.selectAll();
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
    public Rmap<Eprovince> selectByPrimaryKey(Integer id){
        Rmap<Eprovince> map = eprovinceService.selectByPrimaryKey(id);
        if(null != map.getObject()){
            map.setStatus(260);
        }else{
            map.setStatus(240);
        }
        return map;
    }

    @AuthToken
    @RequestMapping(value = "/selectByPage", method = RequestMethod.GET)
    public Rmap<Eprovince> selectByPage(Integer page, Integer rows, String item) {
        StringBuilder sql =new StringBuilder();
        if(StringUtils.isNotBlank(item)){
            sql.append("item like ").append("'%25").append(item).append("%25'");
        }
        Rmap<Eprovince> map = eprovinceService.selectByPage(page, rows, "id", "desc", sql.toString());
        if (null != map.getList()) {
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
     * @Description: 添加省份
     */
    @AuthToken
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public Rmap<Eprovince> insert(Eprovince record) {
        Rmap<Eprovince> map = new Rmap<Eprovince>();
        if(isValidate(record.getItem(), 0)){
            map = eprovinceService.insert(record);
            if(null != map.getObject()){
                map.setStatus(260);
            }else{
                map.setStatus(240);
                if(null == map.getMessage()){
                    map.setMessage("添加省份失败");
                }
            }
        }else{
            map.setStatus(240);
            map.setMessage("该省份已存在");
        }
        return map;
    }

    /**
     * @Auther: Barry
     * @Date: 2019/9/10
     * @Param: record
     * @Description: 更新省份
     */
    @AuthToken
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public Rmap<Eprovince> update(Eprovince record) {
        Rmap<Eprovince> map = new Rmap<Eprovince>();
        if(isValidate(record.getItem(), record.getId())){
            map = eprovinceService.update(record);
            if(null != map.getObject()){
                map.setStatus(260);
            }else{
                map.setStatus(240);
                if(null == map.getMessage()){
                    map.setMessage("修改省份失败");
                }
            }
        }else{
            map.setStatus(240);
            map.setMessage("该省份已存在");
        }
        return map;
    }

    /**
     * @Auther: Barry
     * @Date: 2019/9/9
     * @Param: id 省份主键id
     * @Description: 删除省份
     */
    @AuthToken
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public Rmap<Eprovince> delete(Integer id) {
        Rmap<Eprovince> map = eprovinceService.delete(id);
        if(null != map.getObject()){
            map.setStatus(260);
        }else {
            map.setStatus(240);
            if(null == map.getMessage()) {
                map.setMessage("删除省份失败");
            }
        }
        return map;
    }

    /**
     * @Auther: Barry
     * @Date: 2019/9/9
     * @Param: ids 省份主键id数组
     * @Description: 删除省份
     */
    @AuthToken
    @RequestMapping(value = "/deletes", method = RequestMethod.DELETE)
    public Rmap<Eprovince> deletes(@RequestBody Integer[] ids){
        String strIds =  ArrayUtils.toString(ids, ",").replace('{', '(').replace('}', ')');
        Rmap<Eprovince> map = eprovinceService.deletes(strIds);
        if(null != map.getList()){
            map.setStatus(260);
        }else{
            map.setStatus(240);
            if(null == map.getMessage()){
                map.setMessage("删除省份失败");
            }
        }
        return map;
    }

    /**
     * @Auther: Barry
     * @Date: 2019/9/10
     * @Param: item
     * @Param: projectid
     * @Param: id
     * @Description: 数据唯一性校验
     */
    public boolean isValidate(String item, Integer id){
        Rmap<Eprovince> map = eprovinceService.selectBySql("item = '" + item +"' and id != " + id);
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