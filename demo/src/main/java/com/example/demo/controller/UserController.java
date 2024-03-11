package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import ch.qos.logback.core.model.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("")
    public ModelAndView viewprofile() {
        ModelAndView mav = new ModelAndView("Profile.html");
        return mav;
    }

    @GetMapping("edit")
    public ModelAndView editprifle() {
        ModelAndView mav = new ModelAndView("edit-profile.html");
        return mav;
    
    
    }
}
