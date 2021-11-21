package com.iot.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Enterprise implements Serializable {
    private Integer id;
    private String name;
    private String code;
    private String username;
    private String type;
    private String dealtype;
    private String phone;
    private Date time;
    private String address;
    private String department;
    private String departmentphone;
    private String note;
    private String province;
    private String city;
    private String plat;

    private static final long serialVersionUID = 1L;


}
