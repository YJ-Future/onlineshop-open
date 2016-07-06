package com.gloryofme.onlineshop.manage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by YU on 2016/7/3.
 */
@RequestMapping("page")
@Controller
public class PageController {
    @RequestMapping("{pageName}")
    public String toPage(@PathVariable("pageName") String pageName){
        return pageName;
    }
}
