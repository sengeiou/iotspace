package com.iot.bean;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @Author: Davis
 * @Date: 2019/07/14
 * @Description:
 */
@Data
public class Rmap<clazz> {
    private Integer status;
    private String message;
    private List<clazz> list;
    private clazz object;
    private Map<Object, Object> info;
}
