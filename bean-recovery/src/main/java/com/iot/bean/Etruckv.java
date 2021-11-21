package com.iot.bean;

import lombok.Data;


/**
 * @Auther: Barry
 * @Date: 2020/3/24
 * @Description:
 */
@Data
public class Etruckv extends Etruck {

    private Euser user;
    private Etransportcompanyv transportcompany;
    private Evehicle vehicle;

}
