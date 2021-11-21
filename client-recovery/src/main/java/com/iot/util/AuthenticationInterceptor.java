package com.iot.util;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.iot.bean.Etradeuser;
import com.iot.bean.Euser;
import com.iot.bean.Rmap;
import com.iot.service.EtradeuserService;
import com.iot.service.EuserService;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AuthenticationInterceptor implements HandlerInterceptor {
    @Resource
    EuserService userService;
    @Resource
    EtradeuserService tradeuserService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object){
        String token = request.getHeader("token");
        if (!(object instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) object;
        Method method = handlerMethod.getMethod();
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }
        if (method.isAnnotationPresent(AuthToken.class)) {
            AuthToken authToken = method.getAnnotation(AuthToken.class);
            if (authToken.required()) {
                response.setContentType("text/html;charset=utf-8");
                response.setCharacterEncoding("UTF-8");
                if (token == null) {
                    Map<String,Object> map = new HashMap<>();
                    map.put("code", 401);
                    map.put("message", "无token，请重新登录");
                    try {
                        response.getWriter().write(JSON.toJSONString(map));
                        return false;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    if(null == JWT.decode(token).getAudience()){
                        Map<String,Object> map = new HashMap<>();
                        map.put("code", 402);
                        map.put("message", "token错误");
                        try {
                            response.getWriter().write(JSON.toJSONString(map));
                            return false;
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("size = ="+JWT.decode(token).getAudience().size());
                    if(JWT.decode(token).getAudience().size() ==1){ //euser
                        String username = JWT.decode(token).getAudience().get(0);
                        Rmap<Euser> usermap =userService.selectBySql("username = '" + username + "'");
                        List<Euser> list = usermap.getList();
                        if (null!= list && list.size() > 0) {
                            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(list.get(0).getPassword())).build();
                            try {
                                jwtVerifier.verify(token);
                            } catch (JWTVerificationException j) {
                                Map<String,Object> map = new HashMap<>();
                                map.put("status", 401);
                                map.put("message", "token无效，请重新登录");
                                try {
                                    response.getWriter().write(JSON.toJSONString(map));
                                    return false;
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        } else {
                            Map<String,Object> map = new HashMap<>();
                            map.put("status", 401);
                            map.put("message", "用户不存在，请重新登录");
                            try {
                                response.getWriter().write(JSON.toJSONString(map));
                                return false;
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }else if(JWT.decode(token).getAudience().size() ==2){ //etradeuser
                        String username = JWT.decode(token).getAudience().get(0);
                        Rmap<Etradeuser> usermap =tradeuserService.selectBySql("username = '" + username + "'");
                        List<Etradeuser> list = usermap.getList();
                        if (null!= list && list.size() > 0) {
                            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(list.get(0).getPassword())).build();
                            try {
                                jwtVerifier.verify(token);
                            } catch (JWTVerificationException j) {
                                Map<String,Object> map = new HashMap<>();
                                map.put("status", 401);
                                map.put("message", "token无效，请重新登录");
                                try {
                                    response.getWriter().write(JSON.toJSONString(map));
                                    return false;
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        } else {
                            Map<String,Object> map = new HashMap<>();
                            map.put("status", 401);
                            map.put("message", "用户不存在，请重新登录");
                            try {
                                response.getWriter().write(JSON.toJSONString(map));
                                return false;
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }else{
                        Map<String,Object> map = new HashMap<>();
                        map.put("code", 402);
                        map.put("message", "token错误");
                        try {
                            response.getWriter().write(JSON.toJSONString(map));
                            return false;
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (JWTDecodeException j) {
                    Map<String,Object> map = new HashMap<>();
                    map.put("status", 401);
                    map.put("message", "token无效，请重新登录");
                    try {
                        response.getWriter().write(JSON.toJSONString(map));
                        return false;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse,
                           Object o, ModelAndView modelAndView){

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse,
                                Object o, Exception e){
    }
}