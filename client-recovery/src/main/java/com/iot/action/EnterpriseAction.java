package com.iot.action;

import com.iot.bean.Enterprise;
import com.iot.bean.Rmap;
import com.iot.service.EnterpriseService;
import com.iot.util.AuthToken;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/nterprise")
public class EnterpriseAction extends BaseController{

    @Resource
    private EnterpriseService enterpriseService;

    /**
     * 首页 商务部企业公示
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping(value = "/homeSelectByPage", method = RequestMethod.GET)
    public Rmap<Enterprise> homeSelectByPage(Integer page, Integer rows,String department) {
        StringBuilder sql =new StringBuilder();
        if(StringUtils.isNotBlank(department)){
            sql.append("department like ").append("'%25").append(department).append("%25'");
        }
        Rmap<Enterprise> map = enterpriseService.selectByPage(page, rows, "department", "desc", sql.toString());
        if (null != map.getList()) {
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
    public Rmap<Enterprise> selectAll(){
        Rmap<Enterprise> map = enterpriseService.selectAll();
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
    public Rmap<Enterprise> selectByPrimaryKey(Integer id){
        Rmap<Enterprise> map = enterpriseService.selectByPrimaryKey(id);
        if(null != map.getObject()){
            map.setStatus(260);
        }else{
            map.setStatus(240);
        }
        return map;
    }

    /**
     * 分页查询
     * @param page
     * @param rows
     * @param name
     * @return
     */
    @AuthToken
    @RequestMapping(value = "/selectByPage", method = RequestMethod.GET)
    public Rmap<Enterprise> selectByPage(Integer page, Integer rows, String name) {
        StringBuilder sql =new StringBuilder();
        if(StringUtils.isNotBlank(name)){
            sql.append("name like ").append("'%25").append(name).append("%25'");
        }
        Rmap<Enterprise> map = enterpriseService.selectByPage(page, rows, "id", "desc", sql.toString());
        if (null != map.getList()) {
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
    public Rmap<Enterprise> insert(Enterprise record) {
        Rmap<Enterprise> map = new Rmap<Enterprise>();
        if(isValidate(record.getName(),record.getCode(), 0)){
            map = enterpriseService.insert(record);
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
            map.setMessage("该企业已存在");
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
    public Rmap<Enterprise> update(Enterprise record) {
        Rmap<Enterprise> map = new Rmap<Enterprise>();
        if(isValidate(record.getName(),record.getCode(), record.getId())){
            map = enterpriseService.update(record);
            if(null != map.getObject()){
                map.setStatus(260);
            }else{
                map.setStatus(240);
                if(null == map.getMessage()){
                    map.setMessage("编辑失败");
                }
            }
        }else{
            map.setStatus(240);
            map.setMessage("该企业已存在");
        }
        return map;
    }

    /**
     * 主键删除
     * @param id
     * @return
     */
    @AuthToken
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public Rmap<Enterprise> delete(Integer id) {
        Rmap<Enterprise> map = enterpriseService.delete(id);
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
    public Rmap<Enterprise> deletes(@RequestBody Integer[] ids){
        String strIds =  ArrayUtils.toString(ids, ",").replace('{', '(').replace('}', ')');
        Rmap<Enterprise> map = enterpriseService.deletes(strIds);
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
     * 唯一性校验
     * @param name
     * @param code
     * @param id
     * @return
     */
    public boolean isValidate(String name,String code, Integer id){
        Rmap<Enterprise> map = enterpriseService.selectBySql("( name = '"+name+"'" + " or code = '"+code+"' ) and id != "+id);
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