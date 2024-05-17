package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;

import com.example.demo.models.Courses;
import com.example.demo.repositories.CoursesRepository;

@SpringBootApplication
@Controller
@EnableAspectJAutoProxy 
public class DemoApplication{

    @Autowired
    private CoursesRepository coursesRepository;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

	@GetMapping("")
	public ModelAndView index() {
		List<Courses> course = this.coursesRepository.findAll();
		ModelAndView mav = new ModelAndView("index.html");
		boolean success = false;
		
		if (!course.isEmpty()) {
			success = true;
			mav.addObject("courses", course);
		} 
		
		mav.addObject("success", success);
		return mav;
	}
	

	@GetMapping("/error")
	    public ModelAndView error() {
       ModelAndView mav = new ModelAndView("error.html");
       return mav;
    }

	@ControllerAdvice
	public class GlobalExceptionHandler {

    @ExceptionHandler(ModelAndViewDefiningException.class)
    public ModelAndView handleModelAndViewDefiningException(ModelAndViewDefiningException ex) {
        return ex.getModelAndView();
	}

}
}
