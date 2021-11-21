package com.iot.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Etradeuser implements Serializable {

    private Integer id;
    private String username;
    private String realname;
    private String password;
    private String phone;
    private String type;
    private String role;
    private Date time;
    private String openid;
    private String province;
    private String note;
    private static final long serialVersionUID = 1L;

}
