package com.iot.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Auther: Barry
 * @Date: 2020/3/24
 * @Description:
 */
@Data
public class Ehospital implements Serializable {

    private Integer id;
    private String name;
    private String address;
    private String leadername;
    private String leaderphone;
    private Integer supervisionunitid;
    private String level;
    private String type;
    private String code;
    private String note;
    private String img;
    private String belonglevel;
    private Integer beds;
    private Integer transportcompanyid;
    private Date time;
    private String province;
    private String city;
    private String plat;
    private String longitude;
    private String latitude;

    private static final long serialVersionUID = 1L;
}
