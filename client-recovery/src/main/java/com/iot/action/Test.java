package com.iot.action;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: Barry
 * @Date: 2020/4/12
 * @Description:
 */
public class Test {


    public static void main(String[] args) {
        Map<String, String> param = new HashMap<>();
        param.put("appid", "wx224e2f522a401bb5");
        param.put("secret", "354e221d1c1d6ec89f2c6dbb5f859b25");
        param.put("js_code", "1234");
        param.put("grant_type", "authorization_code");
        // 发送请求
        String wxResult = HttpClientUtil.doGet("https://api.weixin.qq.com/sns/jscode2session", param);
        com.alibaba.fastjson.JSONObject jsonObject = com.alibaba.fastjson.JSONObject.parseObject(wxResult);
        // 获取参数返回的
        String session_key = jsonObject.get("session_key").toString();
        String open_id = jsonObject.get("openid").toString();
        boolean isFirst=false;

    }
}
