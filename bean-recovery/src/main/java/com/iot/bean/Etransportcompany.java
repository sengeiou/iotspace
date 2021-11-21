package com.iot.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @Auther: Barry
 * @Date: 2020/3/24
 * @Description:
 */
@Data
public class Etransportcompany implements Serializable {

    private Integer id;
    private String name;
    private String address;
    private String leadername;
    private String leaderphone;
    private String note;
    private String img;
    private Integer treatmentplantid;
    private static final long serialVersionUID = 1L;
}
