package com.iot.action;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.beans.PropertyEditorSupport;
import java.util.Date;

/**
 * @Auther: Barry
 * @Date: 2019/9/2
 * @Description:
 */
@Controller
public abstract class BaseController {

    /**
     * @Auther: Barry
     * @Date: 2019/9/2
     * @Param: binder
     * @Description: 前端日期转换
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String value) {
                try {
                    if (StringUtils.isNotBlank(value)) {
                        Date date = new Date(Long.valueOf(value));
                        setValue(date);
                    } else {
                        setValue(null);
                    }
                } catch (Exception e) {
                    setValue(null);
                }
            }
        });
        binder.registerCustomEditor(String.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String value) {
                if (StringUtils.isBlank(value) || "null".equals(value) || "undefined".equals(value)) {
                    setValue(null);
                } else{
                    setValue(value);
                }
            }
        });
        binder.registerCustomEditor(Integer.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String value) {
                if (StringUtils.isBlank(value) || "null".equals(value) || "undefined".equals(value)) {
                    setValue(null);
                } else{
                    setValue(value);
                }
            }
        });
    }


}
