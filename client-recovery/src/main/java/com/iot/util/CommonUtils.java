package com.iot.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;

public class CommonUtils {
	public static String json2sql(String jsonString){

		String sql = "";
		if(jsonString != null && !jsonString.equals("null")) {
			JSONArray jsonArray = JSONArray.parseArray(jsonString);
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				String field = jsonObject.getString("field");
				String op = jsonObject.getString("op");
				String value = jsonObject.getString("value");
				if (!sql.isEmpty())
					sql += " and ";
				sql += field;
				switch (op) {
					case "contains":
						sql += " like '%" + value + "%'";
						break;
					case "same":
						sql += "='" + value + "'";
						break;
					case "equal":
						sql += "=" + Double.parseDouble(value);
						break;
					case "notequal":
						sql += "!=" + Double.parseDouble(value);
						break;
					case "beginwith":
						sql += ">='" + value + "'";
						break;
					case "endwith":
						sql += "<='" + value + "'";
						break;
					case "less":
						sql += "<" + Double.parseDouble(value);
						break;
					case "lessorequal":
						sql += "<=" + Double.parseDouble(value);
						break;
					case "greater":
						sql += ">" + Double.parseDouble(value);
						break;
					case "greaterorequal":
						sql += ">=" + Double.parseDouble(value);
						break;
					default:
						sql = "";
				}
			}
		}
		return sql;
	}

	/**
	 * @Auther: davis
	 * @Date: 2019/07/11
	 * @Param: phone
	 * @Param: content 格式为 "{\"key1\":\"value1\", \"key2\":\"value2\", \"key3\":\"value3\"}"
		注册和忘记密码短信 content "{\"code\":\"" + code + "\"}"
		告警短信 "{\"item\":\"" + item + "\", \"level\": " + level + ", \"time\":\"" + time + "\"}"
		msgTemplateCode 为 Constant.registTemplateCode 或 Constant.lostTemplateCode 或 Constant.msgTemplateCode
	 * @Param: msgTemplateCode
	 * @Description:
	 */
	public static boolean sendMsg(String phone, String content, String msgTemplateCode){
		DefaultProfile profile = DefaultProfile.getProfile("default", Constant.msgId, Constant.msgSecret);
		IAcsClient client = new DefaultAcsClient(profile);
		CommonRequest request = new CommonRequest();
		request.setMethod(MethodType.POST);
		request.setDomain("dysmsapi.aliyuncs.com");
		request.setVersion("2017-05-25");
		request.setAction("SendSms");
		request.putQueryParameter("PhoneNumbers", phone);
		request.putQueryParameter("SignName", Constant.msgSignName);
		request.putQueryParameter("TemplateCode", msgTemplateCode);
		request.putQueryParameter("TemplateParam", content);
		try {
			CommonResponse response = client.getCommonResponse(request);
			JSONObject data = JSONObject.parseObject(response.getData());
			if(data.getString("Code").equals("OK")){
				return true;
			}else{
				return false;
			}
		} catch (ServerException e) {
			e.printStackTrace();
			return false;
		} catch (ClientException e) {
			e.printStackTrace();
			return false;
		}
	}

}
