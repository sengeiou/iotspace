package com.iot.bean;

import lombok.Data;


@Data
public class Ecmaterialinv extends Ecmaterialin {

    private Etreatmentplant treatmentplant;
    private Ecmaterialcategory cmaterialcategory;

}
