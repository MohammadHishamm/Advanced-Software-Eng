package com.example.demo.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class SignUpController {
    @GetMapping("")
    public ModelAndView signup() {
       ModelAndView mav = new ModelAndView("Signup-in.html");
       return mav;
    }
    


    
}
