package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/admin")
@RestController
public class adminController {
    @GetMapping("")
    public ModelAndView getpage() {
        ModelAndView mav = new ModelAndView("fragments/side-nav.html");
        return mav;
    }
}
