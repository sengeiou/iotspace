package com.iot.action;

import com.iot.bean.Eproductionplant;
import com.iot.bean.Rmap;
import com.iot.service.EproductionplantService;
import com.iot.util.AuthToken;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/productionplant")
public class EproductionplantAction extends BaseController{
    @Resource
    private EproductionplantService eproductionplantService;

    /**
     * @Auther: Barry
     * @Date: 2020/3/25
     * @Param:
     * @Description: 查询所有
     */
    @AuthToken
    @RequestMapping(value = "/selectAll", method = RequestMethod.GET)
    public Rmap<Eproductionplant> selectAll(){
        Rmap<Eproductionplant> map = eproductionplantService.selectAll();
        if(null != map.getList()){
            map.setStatus(260);
        }else{
            map.setStatus(240);
        }
        return map;
    }

    /**
     * @Auther: Barry
     * @Date: 2020/3/25
     * @Param: page
     * @Param: rows
     * @Param: name
     * @Param: address
     * @Param: leadername
     * @Param: leaderphone
     * @Description: 分页查询
     */
    @AuthToken
    @RequestMapping(value = "/selectByPage", method = RequestMethod.GET)
    public Rmap<Eproductionplant> selectByPage(Integer page, Integer rows, String name, String address, String leadername, String leaderphone){
        StringBuilder sql =new StringBuilder();
        if(StringUtils.isNotBlank(name)){
            sql.append("name like ").append("'%25").append(name).append("%25'");
        }
        if(StringUtils.isNotBlank(address)){
            if(sql.length() == 0){
                sql.append("address like ").append("'%25").append(address).append("%25'");
            }else{
                sql.append(" and address like ").append("'%25").append(address).append("%25'");
            }
        }
        if(StringUtils.isNotBlank(leadername)){
            if(sql.length() == 0){
                sql.append("leadername like ").append("'%25").append(leadername).append("%25'");
            }else{
                sql.append(" and leadername like ").append("'%25").append(leadername).append("%25'");
            }
        }
        if(StringUtils.isNotBlank(leaderphone)){
            if(sql.length() == 0){
                sql.append("leaderphone like ").append("'%25").append(leaderphone).append("%25'");
            }else{
                sql.append(" and leaderphone like ").append("'%25").append(leaderphone).append("%25'");
            }
        }
        Rmap<Eproductionplant> map = eproductionplantService.selectByPage(page, rows, "id", "desc", sql.toString());
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
    public Rmap<Eproductionplant> selectByPrimaryKey(Integer id){
        Rmap<Eproductionplant> map = eproductionplantService.selectByPrimaryKey(id);
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
    public Rmap<Eproductionplant> insert(Eproductionplant record) {
        Rmap<Eproductionplant> map = new Rmap<Eproductionplant>();
        if(isValidate(record.getName(), 0)){
            Rmap<Eproductionplant> rmap = eproductionplantService.insert(record);
            if(null != rmap.getObject()){
                map.setStatus(260);
            }else{
                map.setStatus(240);
                if(null == rmap.getMessage()){
                    map.setMessage("添加生产厂失败");
                }else{
                    map.setMessage(rmap.getMessage());
                }
            }
        }else{
            map.setStatus(240);
            map.setMessage("该生产厂已存在");
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
    public Rmap<Eproductionplant> update(Eproductionplant record) {
        Rmap<Eproductionplant> map = new Rmap<Eproductionplant>();
        if(isValidate(record.getName(), record.getId())){
            Rmap<Eproductionplant> rmap = eproductionplantService.update(record);
            if(null != rmap.getObject()){
                map.setStatus(260);
            }else{
                map.setStatus(240);
                if(null == rmap.getMessage()){
                    map.setMessage("修改生产厂失败");
                }else{
                    map.setMessage(rmap.getMessage());
                }
            }
        }else{
            map.setStatus(240);
            map.setMessage("该生产厂已存在");
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
    public Rmap<Eproductionplant> delete(Integer id) {
        Rmap<Eproductionplant> map = eproductionplantService.delete(id);
        if(null != map.getObject()){
            map.setStatus(260);
        }else {
            map.setStatus(240);
            if(null == map.getMessage()) {
                map.setMessage("删除生产厂失败");
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
    public Rmap<Eproductionplant> deletes(@RequestBody Integer[] ids){
        String strIds =  ArrayUtils.toString(ids, ",").replace('{', '(').replace('}', ')');
        Rmap<Eproductionplant> map = eproductionplantService.deletes(strIds);
        if(null != map.getList()){
            map.setStatus(260);
        }else{
            map.setStatus(240);
            if(null == map.getMessage()){
                map.setMessage("删除生产厂失败");
            }
        }
        return map;
    }

    /**
     * @Auther: Barry
     * @Date: 2020/3/25
     * @Param: name 生产厂名称
     * @Param: id
     * @Description: 唯一性校验
     */
    public boolean isValidate(String name, Integer id){
        Rmap<Eproductionplant> map = eproductionplantService.selectBySql("name = '" + name + "' and id != " + id);
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