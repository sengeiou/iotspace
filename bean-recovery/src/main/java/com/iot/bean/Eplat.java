package com.iot.bean;

import lombok.Data;

import java.io.Serializable;

@Data
public class Eplat implements Serializable {
    private Integer id;

    private String item;

    private Integer cityid;

    private String longitude;

    private String latitude;

    private String note;

    private static final long serialVersionUID = 1L;

}