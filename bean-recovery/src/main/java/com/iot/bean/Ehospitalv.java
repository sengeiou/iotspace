package com.iot.bean;

import lombok.Data;


/**
 * @Auther: Barry
 * @Date: 2020/3/24
 * @Description:
 */
@Data
public class Ehospitalv extends Ehospital {

    private Esupervisionunitv supervisionunit;
    private Etransportcompanyv transportcompany;
}
