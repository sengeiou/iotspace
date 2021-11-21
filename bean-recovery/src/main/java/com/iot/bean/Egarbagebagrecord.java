package com.iot.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Auther: Barry
 * @Date: 2019/7/17
 * @Description: 
 */
@Data
public class Egarbagebagrecord implements Serializable {
    private Integer id;
    private Integer userid;
    private Integer garbagebagid;
    private Integer belongid;
    private String belongtype;
    private String belongname;
    private String status;
    private Date time;
    private String note;

    private static final long serialVersionUID = 1L;

}