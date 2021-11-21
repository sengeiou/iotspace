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
public class Etrucktransportcompany implements Serializable {
    private Integer id;
    private Integer truckid;
    private Integer transportcompanyid;
    private Integer submituserid;
    private Integer confirmuserid;
    private Integer plasticnumber;
    private Double plasticweight;
    private Integer glassnumber;
    private Double glassweight;
    private Date time;
    private String status;
    private String note;
    private Integer treatmentplantid;
    private static final long serialVersionUID = 1L;
}
