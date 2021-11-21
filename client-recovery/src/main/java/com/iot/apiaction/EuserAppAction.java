package com.iot.apiaction;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.iot.action.BaseController;
import com.iot.bean.*;
import com.iot.service.*;
import com.iot.util.AuthToken;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: Barry
 * @Date: 2020/4/13
 * @Description:
 */
@RestController
@RequestMapping("/api/user")
public class EuserAppAction extends BaseController {

    @Resource
    private EuserService euserService;
    @Resource
    private EhospitalService ehospitalService;
    @Resource
    private EtreatmentplantService etreatmentplantService;
    @Resource
    private EtransportcompanyService etransportcompanyService;

    private static final String userRole = "|医疗机构回收员|审核员|用户|运输员|";

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public Map<String, Object> login(String username, String password){
        Map<String, Object> map = new HashMap<>();
        Rmap<Euser> rmap = euserService.selectBySql("username = '" + username + "'");
        if(null != rmap.getList()){
            Euser user = rmap.getList().get(0);
            if(!user.getPassword().equals(password)){
                map.put("status", 240);
                map.put("message", "密码输入错误");
                return map;
            }
            if(!userRole.contains("|"+user.getRole()+"|")){
                map.put("status", 240);
                map.put("message", "用户无权限");
                return map;
            }
            map.put("status", 260);
            map.put("user", user);
            map.put("token", this.getToken(username, password));
            if(user.getType().equals("医疗机构") && user.getRole().equals("医疗机构回收员")){
                Rmap<Ehospital> hospitalMap = ehospitalService.selectByPrimaryKey(user.getBelongid());
                if(null != hospitalMap.getObject()){
                    map.put("supervisionunit", hospitalMap.getObject().getName());
                }else{
                    map.put("supervisionunit", null);
                }
            }else if(user.getType().equals("回收利用企业")){
                Rmap<Etreatmentplant> etreatmentplantMap =  etreatmentplantService.selectByPrimaryKey(user.getBelongid());
                if(null != etreatmentplantMap.getObject()){
                    map.put("supervisionunit", etreatmentplantMap.getObject().getName());
                }else{
                    map.put("supervisionunit", null);
                }
            }else if(user.getType().equals("回收物流")){
                Rmap<Etransportcompany> etransportcompanyMap =  etransportcompanyService.selectByPrimaryKey(user.getBelongid());
                if(null != etransportcompanyMap.getObject()){
                    map.put("supervisionunit", etransportcompanyMap.getObject().getName());
                }else{
                    map.put("supervisionunit", null);
                }
            }
        }else{
            map.put("status", 240);
            if(null == rmap.getMessage()){
                map.put("message", "未查询到用户信息");
            }else{
                map.put("message", rmap.getMessage());
            }
        }
        return map;
    }

    /**
     * @Auther: Barry
     * @Date: 2020/4/13
     * @Param: username
     * @Param: password
     * @Description: 生成token
     */
    public String getToken(String username, String password) {
        String token = "";
        token = JWT.create().withAudience(username)
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60*24))
                .sign(Algorithm.HMAC256(password));
        return token;
    }

    /**
     * @Auther: Barry
     * @Date: 2020/4/14
     * @Param: token
     * @Description: token有效验证
     */
    @RequestMapping(value = "/tokenVerify", method = RequestMethod.GET)
    public Map<String, Object> tokenVerify(String token){
        Map<String, Object> map = new HashMap<>();
        if (StringUtils.isBlank(token)) {
            map.put("status", 240);
            map.put("message", "无token，请重新登录");
        }else{
            try {
                if(null == JWT.decode(token).getAudience() || JWT.decode(token).getAudience().size() !=1){
                    map.put("status", 240);
                    map.put("message", "token错误");
                    return map;
                }
                String username = JWT.decode(token).getAudience().get(0);
                Rmap<Euser> usermap =euserService.selectBySql("username = '" + username + "'");
                List<Euser> list = usermap.getList();
                if (null!= list && list.size() > 0) {
                    JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(list.get(0).getPassword())).build();
                    try {
                        jwtVerifier.verify(token);
                    } catch (JWTVerificationException j) {
                        map.put("status", 240);
                        map.put("message", "token无效，请重新登录");
                    }
                } else {
                    map.put("status", 240);
                    map.put("message", "用户不存在，请重新登录");
                }
            } catch (JWTDecodeException j) {
                map.put("status", 240);
                map.put("message", "token无效，请重新登录");
            }
        }
        if(!map.containsKey("status")){
            map.put("status", 260);
        }
        return map;
    }

    /**
     * @Auther: Barry
     * @Date: 2020/4/15
     * @Param: belongid
     * @Param: role
     * @Description:  所属单位 角色筛选用户
     */
    @AuthToken
    @RequestMapping(value = "/selectByRoleAndBelongId", method = RequestMethod.GET)
    public Rmap<Euser> selectByRoleAndBelongId(Integer belongid, String role,String type){
        StringBuilder sql = new StringBuilder();
        sql.append("belongid = ").append(belongid);
        sql.append(" and role = '").append(role).append("'");
        sql.append(" and type = '").append(type).append("'");
        Rmap<Euser> map = euserService.selectBySql(sql.toString());
        if(null != map.getList()){
            map.setStatus(260);
        }else{
            map.setStatus(240);
        }
        return map;
    }


}
