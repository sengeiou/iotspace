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
public class Etruck implements Serializable {

    private Integer id;
    private Integer vehicleid;
    private Integer userid;
    private String status;
    private String type;
    private Date time;
    private String note;
    private Integer transportcompanyid;
    private static final long serialVersionUID = 1L;

}
