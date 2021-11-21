package com.iot.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @Auther: Barry
 * @Date: 2019/7/17
 * @Description:
 */
@Data
public class Evehicle implements Serializable {
    private Integer id;

    private String number;

    private Integer transportcompanyid;

    private String note;

    private static final long serialVersionUID = 1L;

}