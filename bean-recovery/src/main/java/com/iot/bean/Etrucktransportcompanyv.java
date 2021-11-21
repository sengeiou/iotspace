package com.iot.bean;

import lombok.Data;


/**
 * @Auther: Barry
 * @Date: 2020/3/24
 * @Description:
 */
@Data
public class Etrucktransportcompanyv extends Etrucktransportcompany {
    private Etruckv truck;
    private Euser submitUser;
    private Euser confirmUser;
    private Etransportcompany transportcompany;

}
