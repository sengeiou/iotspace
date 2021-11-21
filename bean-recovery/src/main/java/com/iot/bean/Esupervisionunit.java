package com.iot.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @Auther: Barry
 * @Date: 2020/4/7
 * @Description:
 */
@Data
public class Esupervisionunit implements Serializable {
    private Integer id;
    private String name;
    private String address;
    private String level;
    private Integer pid;
    private String note;
    private static final long serialVersionUID = 1L;
}
