package com.example.demo.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.servlet.ModelAndView;

import com.example.demo.models.Courses;
import com.example.demo.repositories.CoursesRepository;

@RequestMapping("/courses")
@RestController
public class coursesController {

    @Autowired
    private CoursesRepository coursesRepository;
    @GetMapping("")
    public ModelAndView getcourses() {
        ModelAndView mav = new ModelAndView("viewCourses.html");
        List<Courses> courses = this.coursesRepository.findAll();
        mav.addObject("courses", courses);
         return mav;

    }






    @GetMapping("add-course")
    public ModelAndView courses() {
       ModelAndView mav = new ModelAndView("courses.html");
       Courses course= new Courses();
       mav.addObject("course", course);
       return mav;
    }

    @PostMapping("save-course") 
    public String savecourse(@ModelAttribute Courses course) {
        this.coursesRepository.save(course);
        return "Added";

    }

    @GetMapping("view-course")
    public ModelAndView view_course(@RequestParam("courseid") int id) {
       ModelAndView mav = new ModelAndView("view-course.html");
       Courses course =  this.coursesRepository.findById(id);
       if(course != null) 
       {
            mav.addObject("course", course);
       }
       else
       {
            mav = new ModelAndView("index.html");
       }

       return mav;
    }
    
    


    




}
