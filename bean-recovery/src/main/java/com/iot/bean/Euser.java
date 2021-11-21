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
public class Euser implements Serializable {
    private Integer id;
    private String username;
    private String password;
    private String phone;
    private String type;
    private String role;
    private String realname;
    private String openid;
    private Integer belongid;
    private Date time;
    private String note;
    private static final long serialVersionUID = 1L;
}
