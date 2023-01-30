package com.management.hotel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/policy")
public class PolicyController {

    @GetMapping
    public ModelAndView findAll() throws Exception {
        ModelAndView mav = new ModelAndView("chinh-sach.html");
        return mav;
    }

}
