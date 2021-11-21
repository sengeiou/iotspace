package com.iot.bean;

import lombok.Data;

/**
 * @Auther: Barry
 * @Date: 2019/7/17
 * @Description:
 */
@Data
public class Egarbagebagrecordv extends Egarbagebagrecord{

    private Egarbagebagv garbagebag;
    private Euser user;
}