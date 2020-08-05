package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @description:
 * @author: yanghaojie 31648
 * @date: 2020/8/5 15:25
 */
@RequestMapping("/index")
@RestController
public class IndexController {
    @RequestMapping("index")
    public ModelAndView index(ModelAndView modelAndView){
        modelAndView.setViewName("index/index");
        return modelAndView;
    }
}
