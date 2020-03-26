package com.google.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {
    @RequestMapping("/")
    public ModelAndView Index(){
        ModelAndView mav=new ModelAndView();
        mav.setViewName("index");
        return mav;
    }
}
