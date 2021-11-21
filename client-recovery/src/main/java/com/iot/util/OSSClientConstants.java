package com.iot.util;

/**
 * @Auther: Barry
 * @Date: 2019/8/1
 * @Description:   oss常量配置
 */
public class OSSClientConstants {  
    //阿里云API的endpoint
    public static final String ENDPOINT = "oss-cn-beijing.aliyuncs.com";
    //阿里云API的密钥Access Key ID   
    public static final String ACCESS_KEY_ID = "LTAI4GKY24DxsV5i9ishdDHo";
    //阿里云API的密钥Access Key Secret  
    public static final String ACCESS_KEY_SECRET = "mvqVvxznylFsBRBZn3flhvPyfjoBtL";
    //阿里云API的bucket名称  
    public static final String BACKET_NAME = "rcv";
    //host的格式为 bucketname.endpoint
//	public final static String HOST = "http://" + BACKET_NAME + "." + ENDPOINT;
    public final static String HOST = "http://rcv.oss-cn-beijing.aliyuncs.com/";
}  
