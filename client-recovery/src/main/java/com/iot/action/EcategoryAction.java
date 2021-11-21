package com.iot.action;

import com.iot.bean.Ecategory;
import com.iot.bean.Ecategoryv;
import com.iot.bean.Rmap;
import com.iot.service.EcategoryService;
import com.iot.util.AuthToken;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/category")
public class EcategoryAction extends BaseController{
    @Resource
    private EcategoryService ecategoryService;

    /**
     * @Auther: Barry
     * @Date: 2020/6/2
     * @Param: treatmentplantid
     * @Description: 某一处理厂 所有类别
     */
    @AuthToken
    @RequestMapping(value = "/selectByTreatmentplantid", method = RequestMethod.GET)
    public Rmap<Ecategory> selectByTreatmentplantid(Integer treatmentplantid){
        Rmap<Ecategory> map = new  Rmap<Ecategory>();
        if(null == treatmentplantid){
            map.setStatus(240);
            map.setMessage("参数不全");
            return map;
        }
        map = ecategoryService.selectBySql("treatmentplantid = "+treatmentplantid);
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
     * @Param:
     * @Description: 查询所有
     */
    @AuthToken
    @RequestMapping(value = "/selectAll", method = RequestMethod.GET)
    public Rmap<Ecategory> selectAll(){
        Rmap<Ecategory> map = ecategoryService.selectAll();
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
    public Rmap<Ecategory> selectByPage(Integer page, Integer rows, String name, Integer treatmentplantid){
        Rmap<Ecategory> map = new Rmap<Ecategory>();
        if(null == treatmentplantid){
            map.setStatus(240);
            map.setMessage("参数不全");
            return map;
        }
        StringBuilder sql =new StringBuilder();
        sql.append("treatmentplantid = "+treatmentplantid);
        if(StringUtils.isNotBlank(name)){
            sql.append(" and name like ").append("'%25").append(name).append("%25'");
        }

        map = ecategoryService.selectByPage(page, rows, "id", "desc", sql.toString());
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
    public Rmap<Ecategory> selectByPrimaryKey(Integer id){
        Rmap<Ecategory> map = ecategoryService.selectByPrimaryKey(id);
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
    public Rmap<Ecategoryv> selectVAll(){
        Rmap<Ecategoryv> map = ecategoryService.selectVAll();
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
    public Rmap<Ecategoryv> selectVByPage(Integer page, Integer rows, String name, Integer treatmentplantid){
        Rmap<Ecategoryv> map = new Rmap<Ecategoryv>();
        if(null == treatmentplantid){
            map.setStatus(240);
            map.setMessage("参数不全");
            return map;
        }
        StringBuilder sql =new StringBuilder();
        sql.append("treatmentplantid = "+treatmentplantid);
        if(StringUtils.isNotBlank(name)){
            sql.append(" and name like ").append("'%25").append(name).append("%25'");
        }

        map = ecategoryService.selectVByPage(page, rows, "id", "desc", sql.toString());
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
    public Rmap<Ecategoryv> selectVByPrimaryKey(Integer id){
        Rmap<Ecategoryv> map = ecategoryService.selectVByPrimaryKey(id);
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
    public Rmap<Ecategory> insert(Ecategory record) {
        Rmap<Ecategory> map = new Rmap<Ecategory>();
        if(isValidate(record.getName(), record.getTreatmentplantid(), 0)){
            map = ecategoryService.insert(record);
            if(null != map.getObject()){
                map.setStatus(260);
            }else{
                map.setStatus(240);
                if(null == map.getMessage()){
                    map.setMessage("添加失败");
                }
            }
        }else{
            map.setStatus(240);
            map.setMessage("该类别已存在");
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
    public Rmap<Ecategory> update(Ecategory record) {
        Rmap<Ecategory> map = new Rmap<Ecategory>();
        if(isValidate(record.getName(), record.getTreatmentplantid(), record.getId())){
            map = ecategoryService.update(record);
            if(null != map.getObject()){
                map.setStatus(260);
            }else{
                map.setStatus(240);
                if(null == map.getMessage()){
                    map.setMessage("修改失败");
                }
            }
        }else{
            map.setStatus(240);
            map.setMessage("该类别已存在");
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
    public Rmap<Ecategory> delete(Integer id) {
        Rmap<Ecategory> map = ecategoryService.delete(id);
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
    public Rmap<Ecategory> deletes(@RequestBody Integer[] ids){
        String strIds =  ArrayUtils.toString(ids, ",").replace('{', '(').replace('}', ')');
        Rmap<Ecategory> map = ecategoryService.deletes(strIds);
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

    /**
     * @Auther: Barry
     * @Date: 2020/6/1
     * @Param: name
     * @Param: treatmentplantid
     * @Param: id
     * @Description: 数据校验
     */
    public boolean isValidate(String name, Integer treatmentplantid, Integer id){
        Rmap<Ecategory> map = ecategoryService.selectBySql("name = '" + name + "' and treatmentplantid = " + treatmentplantid + " and id != " + id);
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