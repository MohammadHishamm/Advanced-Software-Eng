package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/courses")
@RestController
public class coursesController {
     @GetMapping("")
    public ModelAndView courses() {
       ModelAndView mav = new ModelAndView("courses.html");
       return mav;
    }

}
