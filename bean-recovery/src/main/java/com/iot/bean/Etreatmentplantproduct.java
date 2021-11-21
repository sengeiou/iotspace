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
public class Etreatmentplantproduct implements Serializable {

    private Integer id;

    private Double weight;

    private Integer number;

    private Integer categoryid;

    private Date time;

    private Integer treatmentplantid;

    private String note;

    private static final long serialVersionUID = 1L;

}