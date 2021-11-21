package com.iot.bean;

import lombok.Data;


@Data
public class Egmaterialinv extends Egmaterialin {

    private Etreatmentplant treatmentplant;
    private Ecmaterialcategory cmaterialcategory;
    private Egmaterialcategory gmaterialcategory;

}
