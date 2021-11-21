package com.iot.action;

import com.iot.bean.Ecmaterialcategory;
import com.iot.bean.Ecmaterialcategoryv;
import com.iot.bean.Rmap;
import com.iot.service.EcmaterialcategoryService;
import com.iot.util.AuthToken;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/cmaterialcategory")
public class EcmaterialcategoryAction extends BaseController{
    @Resource
    private EcmaterialcategoryService ecmaterialcategoryService;

    /**
     * 某一回收企业所有破碎料类别
     * @param treatmentplantid
     * @return
     */
    @AuthToken
    @RequestMapping(value = "/selectByTreatmentplantid", method = RequestMethod.GET)
    public Rmap<Ecmaterialcategory> selectByTreatmentplantid(Integer treatmentplantid){
        Rmap<Ecmaterialcategory> map = new  Rmap<Ecmaterialcategory>();
        if(null == treatmentplantid){
            map.setStatus(240);
            map.setMessage("参数不全");
            return map;
        }
        map = ecmaterialcategoryService.selectBySql("treatmentplantid = "+treatmentplantid);
        if(null != map.getList()){
            map.setStatus(260);
        }else{
            map.setStatus(240);
        }
        return map;
    }

    /**
     * 查询所有
     * @return
     */
    @AuthToken
    @RequestMapping(value = "/selectAll", method = RequestMethod.GET)
    public Rmap<Ecmaterialcategory> selectAll(){
        Rmap<Ecmaterialcategory> map = ecmaterialcategoryService.selectAll();
        if(null != map.getList()){
            map.setStatus(260);
        }else{
            map.setStatus(240);
        }
        return map;
    }

    /**
     * 某一回收企业 分页查询自己下的破碎料类别
     * @param page
     * @param rows
     * @param name
     * @param treatmentplantid
     * @return
     */
    @AuthToken
    @RequestMapping(value = "/selectByPage", method = RequestMethod.GET)
    public Rmap<Ecmaterialcategory> selectByPage(Integer page, Integer rows, String name, Integer treatmentplantid){
        Rmap<Ecmaterialcategory> map = new Rmap<Ecmaterialcategory>();
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
        map = ecmaterialcategoryService.selectByPage(page, rows, "id", "desc", sql.toString());
        if (null != map.getList()) {
            map.setStatus(260);
        }else{
            map.setStatus(240);
        }
        return map;
    }


    /**
     * 主键查询
     * @param id
     * @return
     */
    @AuthToken
    @RequestMapping(value = "/selectByPrimaryKey", method = RequestMethod.GET)
    public Rmap<Ecmaterialcategory> selectByPrimaryKey(Integer id){
        Rmap<Ecmaterialcategory> map = ecmaterialcategoryService.selectByPrimaryKey(id);
        if(null != map.getObject()){
            map.setStatus(260);
        }else{
            map.setStatus(240);
        }
        return map;
    }

    /**
     * v主键查询所有
     * @return
     */
    @AuthToken
    @RequestMapping(value = "/selectVAll", method = RequestMethod.GET)
    public Rmap<Ecmaterialcategoryv> selectVAll(){
        Rmap<Ecmaterialcategoryv> map = ecmaterialcategoryService.selectVAll();
        if(null != map.getList()){
            map.setStatus(260);
        }else{
            map.setStatus(240);
        }
        return map;
    }

    /**
     * 某一回收企业 分页查询自己下的破碎料类别
     * @param page
     * @param rows
     * @param name
     * @param treatmentplantid
     * @return V
     */
    @AuthToken
    @RequestMapping(value = "/selectVByPage", method = RequestMethod.GET)
    public Rmap<Ecmaterialcategoryv> selectVByPage(Integer page, Integer rows, String name, Integer treatmentplantid){
        Rmap<Ecmaterialcategoryv> map = new Rmap<Ecmaterialcategoryv>();
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

        map = ecmaterialcategoryService.selectVByPage(page, rows, "id", "desc", sql.toString());
        if (null != map.getList()) {
            map.setStatus(260);
        }else{
            map.setStatus(240);
        }
        return map;
    }

    /**
     * 主键查询
     * @param id
     * @return
     */
    @AuthToken
    @RequestMapping(value = "/selectVByPrimaryKey", method = RequestMethod.GET)
    public Rmap<Ecmaterialcategoryv> selectVByPrimaryKey(Integer id){
        Rmap<Ecmaterialcategoryv> map = ecmaterialcategoryService.selectVByPrimaryKey(id);
        if(null != map.getObject()){
            map.setStatus(260);
        }else{
            map.setStatus(240);
        }
        return map;
    }

    /**
     * 创建
     * @param record
     * @return
     */
    @AuthToken
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public Rmap<Ecmaterialcategory> insert(Ecmaterialcategory record) {
        Rmap<Ecmaterialcategory> map = new Rmap<Ecmaterialcategory>();
        if(null == record.getTreatmentplantid() || StringUtils.isBlank(record.getName())){
            map.setStatus(240);
            map.setMessage("参数不全");
            return map;
        }
        if(isValidate(record.getName(), record.getTreatmentplantid(), 0)){
            //创建库存默认0
            if(null == record.getStock()){
                record.setStock(0.00);
            }
            map = ecmaterialcategoryService.insert(record);
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
     * 编辑
     * @param record
     * @return
     */
    @AuthToken
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public Rmap<Ecmaterialcategory> update(Ecmaterialcategory record) {
        Rmap<Ecmaterialcategory> map = new Rmap<Ecmaterialcategory>();
        if(isValidate(record.getName(), record.getTreatmentplantid(), record.getId())){
            map = ecmaterialcategoryService.update(record);
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
     * 删除
     * @param id
     * @return
     */
    @AuthToken
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public Rmap<Ecmaterialcategory> delete(Integer id) {
        Rmap<Ecmaterialcategory> map = ecmaterialcategoryService.delete(id);
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
     * 批量删除
     * @param ids
     * @return
     */
    @AuthToken
    @RequestMapping(value = "/deletes", method = RequestMethod.DELETE)
    public Rmap<Ecmaterialcategory> deletes(@RequestBody Integer[] ids){
        String strIds =  ArrayUtils.toString(ids, ",").replace('{', '(').replace('}', ')');
        Rmap<Ecmaterialcategory> map = ecmaterialcategoryService.deletes(strIds);
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
     * 校验
     * @param name
     * @param treatmentplantid
     * @param id
     * @return
     */
    public boolean isValidate(String name, Integer treatmentplantid, Integer id){
        Rmap<Ecmaterialcategory> map = ecmaterialcategoryService.selectBySql("name = '" + name + "' and treatmentplantid = " + treatmentplantid + " and id != " + id);
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