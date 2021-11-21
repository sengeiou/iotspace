package com.iot.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @Auther: Barry
 * @Date: 2019/7/17
 * @Description: 
 */
@Data
public class Ecity implements Serializable {
    private Integer id;

    private String item;

    private Integer provinceid;

    private String note;

    private static final long serialVersionUID = 1L;

}