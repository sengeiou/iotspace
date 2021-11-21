package com.iot.bean;


import lombok.Data;

import java.io.Serializable;

@Data
public class Ecmaterialcategory implements Serializable {
    private Integer id;
    private String name;
    private Double stock;
    private Integer treatmentplantid;
    private String note;
    private static final long serialVersionUID = 1L;

}
