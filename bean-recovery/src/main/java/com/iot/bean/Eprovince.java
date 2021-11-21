package com.iot.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @Auther: Barry
 * @Date: 2019/7/17
 * @Description:
 */
@Data
public class Eprovince implements Serializable {
    private Integer id;

    private String item;

    private String note;

    private static final long serialVersionUID = 1L;

}