package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.models.Instructor;
import com.example.demo.repositories.InstructorRepository;

@RequestMapping("/teacher")
@RestController
public class InstructorController 
{

    @Autowired  
    private InstructorRepository instructorRepository;
    


    @GetMapping("teacherform")
    public ModelAndView viewInstructor() {
        ModelAndView mav= new ModelAndView("Teacher-Form.html");
        Instructor newInstructor= new Instructor();
        mav.addObject("instructor", newInstructor);
        return mav;
    }

 
    @PostMapping("teacherform")
    public String saveInstructor(@ModelAttribute Instructor instructor) {

      
            this.instructorRepository.save(instructor);
            return "Added";

    }
    
    
}
