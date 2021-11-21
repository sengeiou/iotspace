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
public class Einfusionbag implements Serializable {
    private Integer id;
    private String barcode;
    private String name;
    private Integer garbagebagid;
    private Date time;
    private String status;
    private String note;
    private static final long serialVersionUID = 1L;

}
