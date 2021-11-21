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
public class Etruckproductionplant implements Serializable {

    private Integer id;
    private Integer productionplantid;
    private Integer truckid;
    private Integer submituserid;
    private Integer confirmuserid;
    private Integer plasticnumber;
    private Double plasticweight;
    private Integer glassnumber;
    private Double glassweight;
    private Date time;
    private String status;
    private String note;

    private static final long serialVersionUID = 1L;

}
