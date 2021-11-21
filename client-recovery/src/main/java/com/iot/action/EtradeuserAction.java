package com.iot.action;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.iot.bean.Etradeuser;
import com.iot.bean.Rmap;
import com.iot.service.EtradeuserService;
import com.iot.util.AuthToken;
import com.iot.util.Md5;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/tradeuser")
public class EtradeuserAction extends BaseController{

    @Resource
    private EtradeuserService etradeuserService;

    /**
     * 商务部用户登录
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public Map<String, Object> login(String username, String password) {
        Map<String, Object> map = new HashMap<>();
        if(StringUtils.isBlank(username) || StringUtils.isBlank(password)){
            map.put("status", 240);
            map.put("message", "参数不全");
            return map;
        }
        Rmap<Etradeuser> rmap = etradeuserService.selectBySql("username='" + username + "'");
        if(null != rmap.getList()){
            Etradeuser tradeuser = rmap.getList().get(0);
            if (tradeuser.getPassword().equals(password)) {
                map.put("status", 260);
                map.put("user", tradeuser);
                map.put("token", getToken(username, password));
            }else{
                map.put("status", 240);
                map.put("message", "密码错误");
            }
        }else{
            map.put("status", 240);
            if (null == rmap.getMessage()) {
                map.put("message", "用户不存在");
            } else { //服务异常
                map.put("message", rmap.getMessage());
            }
        }
        return map;
    }


    /**
     * 查询所有
     * @return
     */
    @AuthToken
    @RequestMapping(value = "/selectAll", method = RequestMethod.GET)
    public Rmap<Etradeuser> selectAll(){
        Rmap<Etradeuser> map = etradeuserService.selectAll();
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
    public Rmap<Etradeuser> selectByPrimaryKey(Integer id){
        Rmap<Etradeuser> map = etradeuserService.selectByPrimaryKey(id);
        if(null != map.getObject()){
            map.setStatus(260);
        }else{
            map.setStatus(240);
        }
        return map;
    }

    /**
     * 分页查询用户
     * @param page
     * @param rows
     * @param phone
     * @param username
     * @param realname
     * @param type
     * @param role
     * @param begintime
     * @param endtime
     * @return
     */
    @AuthToken
    @RequestMapping(value = "/selectByPage", method = RequestMethod.GET)
    public Rmap<Etradeuser> selectByPage(Integer page, Integer rows, String phone, String username, String realname, String type, String role, Date begintime, Date endtime){
        Rmap<Etradeuser> map = new Rmap<Etradeuser>();
        StringBuilder sql = new StringBuilder();
        if (StringUtils.isNotBlank(phone)) {
            sql.append("phone like ").append("'%25").append(phone).append("%25'");
        }
        if (StringUtils.isNotBlank(username)) {
            if(sql.length() == 0){
                sql.append("username like ").append("'%25").append(username).append("%25'");
            }else{
                sql.append(" and username like ").append("'%25").append(username).append("%25'");
            }
        }
        if (StringUtils.isNotBlank(realname)) {
            if(sql.length() == 0){
                sql.append("realname like ").append("'%25").append(realname).append("%25'");
            }else{
                sql.append(" and realname like ").append("'%25").append(realname).append("%25'");
            }
        }
        if (StringUtils.isNotBlank(type)) {
            if(sql.length() == 0){
                sql.append("type = '").append(type).append("'");
            }else{
                sql.append(" and type = '").append(type).append("'");
            }
        }
        if (StringUtils.isNotBlank(role)) {
            if(sql.length() == 0){
                sql.append("role = '").append(role).append("'");
            }else{
                sql.append(" and role = '").append(role).append("'");
            }
        }
        if (null != begintime && null != endtime) {
            if(sql.length() == 0){
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                sql.append("time >= '").append(format.format(begintime)).append("' and time <= '").append(format.format(endtime)).append("'");
            }else{
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                sql.append(" and time >= '").append(format.format(begintime)).append("' and time <= '").append(format.format(endtime)).append("'");
            }
        }
        map = etradeuserService.selectByPage(page, rows, "id", "desc", sql.toString());
        if (null != map.getList()) {
            map.setStatus(260);
        } else {
            map.setStatus(240);
        }
        return map;
    }

    /**
     * 创建商务部用户
     * @param record
     * @return
     */
    @AuthToken
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public Map<String, Object> insert(Etradeuser record) {
        Map<String, Object> map = new HashMap<>();
        if (!isValidate(0, record.getUsername())) {
            map.put("status", 240);
            map.put("message", "该用户名已注册");
            return map;
        }
        record.setTime(new Date()); //注册时间
        Md5 md5 = new Md5();
        md5.md5s("123456"); //初始密码设置为123456
        record.setPassword(md5.str);
        Rmap<Etradeuser> rmap = etradeuserService.insert(record);
        if (null != rmap.getObject()) {
            map.put("status", 260);
        } else {
            map.put("status", 240);
            if (null == rmap.getMessage()) {
                map.put("message", "创建失败");
            } else {
                map.put("message", rmap.getMessage());
            }
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
    public Rmap<Etradeuser> update(Etradeuser record) {
        Rmap<Etradeuser> map = new Rmap<Etradeuser>();
        if(isValidate(record.getId(),record.getUsername())){
            map = etradeuserService.update(record);
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
            map.setMessage("该用户名已存在");
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
    public Rmap<Etradeuser> delete(Integer id) {
        Rmap<Etradeuser> map = etradeuserService.delete(id);
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
    public Rmap<Etradeuser> deletes(@RequestBody Integer[] ids){
        String strIds =  ArrayUtils.toString(ids, ",").replace('{', '(').replace('}', ')');
        Rmap<Etradeuser> map = etradeuserService.deletes(strIds);
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

    public String getToken(String username, String password) {
        String token = "";
        token = JWT.create().withAudience(username,"tradeuser")
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60*24))
                .sign(Algorithm.HMAC256(password));
        return token;
    }

    /**
     * 唯一性校验
     * @param id
     * @param username
     * @return
     */
    public boolean isValidate(Integer id, String username){
        Rmap<Etradeuser> map = etradeuserService.selectBySql("username = '" + username + "' and id != " + id);
        if(null != map.getList()){ //有数据
            return false;
        }else{
            if(null != map.getMessage()){//服务异常
                return false;
            }
            return true;
        }
    }


}