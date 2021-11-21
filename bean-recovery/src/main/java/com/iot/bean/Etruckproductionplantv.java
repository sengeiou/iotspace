package com.iot.bean;

import lombok.Data;



/**
 * @Auther: Barry
 * @Date: 2020/3/24
 * @Description:
 */
@Data
public class Etruckproductionplantv extends Etruckproductionplant {

    private Eproductionplant productionplant;
    private Euser submitUser;
    private Euser confirmUser;
    private Etruckv truck;


}
