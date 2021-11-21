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
public class Egarbagebag implements Serializable {
    private Integer id;
    private String barcode;
    private Integer departmentid;
    private String status;
    private String type;
    private Integer belongid;
    private String belongtype;
    private String belongname;
    private Date time;
    private String note;
    private static final long serialVersionUID = 1L;
}
