package com.iot.util;

public interface Constant {
    String msgId = "LTAIXpoDTXW3Cvat";
    String msgSecret = "0nsk97XkQwGAe8DZLg4rLp5qtBvN8Z";
    String msgSignName = "物联天地";
    String registTemplateCode = "SMS_158492592";                // 注册模板
    String lostTemplateCode = "SMS_158492599";                  // 忘记密码模板
    String msgTemplateCode = "SMS_158547302";                   // 告警模板
    String wxAppId = "wxf46055e9cac275af";                      //微信appid
    String wxAppSecret = "b16b6e6c48f8e6e32521c6aeeb9664fe";    //微信appsecret
    String wxTestAppId = "wxa871ef5ac8ed0299";
    String wxTestAppSecret = "6cf7e0760c78d87afd9bd6653e24fa5d";
    String loginTemplateCode = "SMS_171354752";   //短信验证码登录
    String orderConfirmTemplateCode = "SMS_171354981"; //工单确认
    String bindCardTemplateCode = "SMS_171750122"; //添加银行卡
    String auditFail = "SMS_172595345";  //用户审核失败
    String auditSuccess = "SMS_172595341";  //用户审核成功

    String orderAuditSuccessTemplateCode ="SMS_174028942"; //工单审核通过
    String orderAuditFailTemplateCode ="SMS_174028947"; //工单审核不通过
}
