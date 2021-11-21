package com.iot.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @Auther: Barry
 * @Date: 2020/3/24
 * @Description:
 */
@Data
public class Etreatmentplant implements Serializable {

    private Integer id;
    private String name;
    private String address;
    private String leadername;
    private String leaderphone;
    private String note;
    private String img;
    private String code;
    private String socialcode;
    private String batch;
    private String province;
    private String city;
    private String plat;
    private static final long serialVersionUID = 1L;
}
