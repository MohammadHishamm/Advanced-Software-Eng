package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


@RequestMapping("/checkout")
@RestController
public class checkoutController {
 @GetMapping("")
    public ModelAndView checkout() {
       ModelAndView mav = new ModelAndView("checkout.html");
       return mav;
    }

}
