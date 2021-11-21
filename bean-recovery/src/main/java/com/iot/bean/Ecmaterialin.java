package com.iot.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Ecmaterialin implements Serializable {
    private Integer id;
    private Integer treatmentplantid;
    private Integer cmaterialcategoryid;
    private Integer number;
    private Double weight;
    private String source;
    private Date time;
    private String note;
    private static final long serialVersionUID = 1L;
}
