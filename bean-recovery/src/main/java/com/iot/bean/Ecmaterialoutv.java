package com.iot.bean;

import lombok.Data;


@Data
public class Ecmaterialoutv extends Ecmaterialout {

    private Etreatmentplant treatmentplant;
    private Ecmaterialcategory cmaterialcategory;

}
