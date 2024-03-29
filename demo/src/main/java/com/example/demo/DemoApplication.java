package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
	@GetMapping("")
	    public ModelAndView index() {
       ModelAndView mav = new ModelAndView("index.html");
       return mav;
    }

	@GetMapping("/error")
	    public ModelAndView error() {
       ModelAndView mav = new ModelAndView("error.html");
       return mav;
    }

}
