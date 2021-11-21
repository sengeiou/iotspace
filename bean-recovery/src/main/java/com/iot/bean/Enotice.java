package com.iot.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Auther: Barry
 * @Date: 2020/4/17
 * @Description:
 */
@Data
public class Enotice implements Serializable {
    private Integer id;
    private String item;
    private String file;
    private Date time;
    private String note;
    private String title;
    private String img;
    private String importance;
    private String type;
    private Integer userid;
    private static final long serialVersionUID = 1L;
}
