package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/wishlist")
@RestController
public class wishlistController {
     @GetMapping("")
    public ModelAndView signup() {
       ModelAndView mav = new ModelAndView("wishlist.html");
       return mav;
    }
}
