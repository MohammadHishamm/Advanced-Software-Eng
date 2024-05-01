package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/cart")
@RestController
public class cartController {
    
    
    @GetMapping("view-cart")
    public ModelAndView cart() {
       ModelAndView mav = new ModelAndView("cart.html");
       return mav;
    }
}
