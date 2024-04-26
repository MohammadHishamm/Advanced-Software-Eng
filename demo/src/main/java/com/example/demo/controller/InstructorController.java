package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.models.Instructor;
import com.example.demo.models.User;
import com.example.demo.repositories.InstructorRepository;
import com.example.demo.repositories.UserRepository;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/teacher")
@RestController
public class InstructorController 
{

    @Autowired  
    private InstructorRepository instructorRepository;
    @Autowired  
    private UserRepository userRepository;
    


    @GetMapping("teacherform")
    public ModelAndView viewInstructor(HttpSession session) {
        if (session.getAttribute("email") != null)
        {
        ModelAndView mav= new ModelAndView("Teacher-Form.html");
        Instructor newInstructor= new Instructor();
        mav.addObject("instructor", newInstructor);
        return mav;
        }
        else
        {
            return new ModelAndView("redirect:/sign");      
    }}

 
    @PostMapping("teacherform")
    public ModelAndView saveInstructor(@ModelAttribute Instructor instructor, HttpSession session) {
       
       
           
            String email = (String) session.getAttribute("email");
    
         
            User user = this.userRepository.findByEmail(email);
    
         
            user.setType("instructor");
    
         
            instructor.setUser(user);
    
          
            this.instructorRepository.save(instructor);
    
            
            return new ModelAndView("redirect:/");
        } 
            
        
     
    
    
    
    
}
