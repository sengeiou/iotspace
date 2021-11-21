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
public class Ebarcode implements Serializable {
    private Integer id;
    private Integer hospitalid;
    private Integer number;
    private Date time;
    private String note;
    private static final long serialVersionUID = 1L;
}
