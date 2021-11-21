package com.iot.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @Auther: Barry
 * @Date: 2020/6/1
 * @Description: 
 */
@Data
public class Ecategory implements Serializable {
    private Integer id;

    private String name;

    private Integer treatmentplantid;

    private String note;

    private static final long serialVersionUID = 1L;

}