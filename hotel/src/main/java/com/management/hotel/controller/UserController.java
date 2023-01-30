package com.management.hotel.controller;

import com.management.hotel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/changepass")
    public ModelAndView getAll() throws Exception {
        ModelAndView mav = new ModelAndView("change-password.html");

        return mav;
    }

    @GetMapping("/profile")
    public ModelAndView getProfile() throws Exception {
        ModelAndView mav = new ModelAndView("profile.html");
        mav.addObject("username", userService.getUsername());
        mav.addObject("info", userService.getUserByUsername(userService.getUsername()).getData());
        return mav;
    }

}
