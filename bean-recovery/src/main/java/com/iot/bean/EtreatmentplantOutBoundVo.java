package com.iot.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Auther: Barry
 * @Date: 2020/6/2
 * @Description: 处理厂出库 车次信息 运输人信息 重量/数量信息  生产厂信息
 */
@Data
public class EtreatmentplantOutBoundVo implements Serializable {

    //车次收货对接id
    private Integer trucktreatmentplantreceiveid;
    //运走运输员姓名
    private String transportname;
    //运走车牌号
    private String carnumber;
    //运输公司名字
    private String transportcompanyname;
    //运走审核状态
    private String status;
    //运走时间
    private Date time;
    //运走塑料重量
    private Double plasticweight;
    //运走塑料数量
    private Integer plasticnumber;
    //运走玻璃重量
    private Double glassweight;
    //运走玻璃数量
    private Integer glassnumber;

    //生产厂接收信息
    //生产厂名字
    private String productionplantname;
    //生产厂接收塑料重量
    private Double productionplantplasticweight;
    //生产厂接收塑料数量
    private Integer productionplantplasticnumber;
    //生产厂接收玻璃重量
    private Double productionplantglassweight;
    //生产厂接收玻璃数量
    private Integer productionplantglassnumber;


}
