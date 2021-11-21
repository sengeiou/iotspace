package com.iot.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Eorder implements Serializable {

    private Integer id;
    private String name;
    private String type;
    private Integer number;
    private Double unitprice;
    private Date time;
    private String address;
    private String contract;
    private String no;
    private Integer categoryid;
    private Double weight;
    private Double totalprice;
    private String car;
    private String phone;
    private String invoice;
    private Integer treatmentplantid;
    private String username;
    private String note;

    private static final long serialVersionUID = 1L;
}
