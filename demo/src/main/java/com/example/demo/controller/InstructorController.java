package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.Services.InstructorService;
import com.example.demo.models.Courses;
import com.example.demo.models.Instructor;
import com.example.demo.models.User;

import com.example.demo.repositories.InstructorRepository;
import com.example.demo.repositories.UserRepository;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;





@RequestMapping("/teacher")
@RestController
public class InstructorController 
{
    @Autowired  
    private InstructorRepository instructorRepository;
    @Autowired  
    private UserRepository userRepository;

    @Autowired
    private InstructorService instructorService;

    

    @GetMapping("teacherform")
    public ModelAndView viewInstructor(HttpSession session) {
        if (session.getAttribute("email") != null) {
            ModelAndView mav = new ModelAndView("Teacher-Form.html");
            Instructor newInstructor = new Instructor();
            mav.addObject("instructor", newInstructor);
            return mav;
        } else {
            return new ModelAndView("redirect:/sign");
        }
    }

    @PostMapping("teacherform")
    public ModelAndView saveInstructor(@ModelAttribute @Valid Instructor instructor, BindingResult bindingResult, HttpSession session) {
        if (bindingResult.hasErrors()) {
            ModelAndView mav = new ModelAndView("Teacher-Form.html");
            mav.addObject("errors", bindingResult.getAllErrors());
            return mav;
        } else {
            String email = (String) session.getAttribute("email");
            User user = this.userRepository.findByEmail(email);
            user.setType("instructor");
            instructor.setUser(user);
            instructor.setStatus("Observing");

            this.instructorService.save(instructor);

            return new ModelAndView("redirect:/");
        }
    }
        
     
   
    
    @GetMapping("view-course")
   public ModelAndView view_course(HttpSession session) {
       ModelAndView mav = new ModelAndView("teacher-coursespage.html");
       String email= session.getAttribute("email") .toString();
       User user = userRepository.findByEmail(email);
       Instructor instructor= this.instructorRepository.findByUser(user);


       List<Courses> courses = instructor.getCourses();
       if(courses != null) 
       {
        
            mav.addObject("courses" , courses );
      
       }
       else
       {
        return new ModelAndView("redirect:/user/profile");
       }

       return mav;
    }
}
