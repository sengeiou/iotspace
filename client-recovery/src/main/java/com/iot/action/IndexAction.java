package com.iot.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexAction {
    @RequestMapping("/")
    public String index() {
        return "forward:/index.html";
    }

    @RequestMapping("/admin")
    public String admin() {
        return "forward:/admin/index.html";
    }

    @RequestMapping("/v")
    public String v() {
        return "forward:/v/index.html";
    }

    @RequestMapping("/a")
    public String a() {
        return "forward:/a/index.html";
    }
}
