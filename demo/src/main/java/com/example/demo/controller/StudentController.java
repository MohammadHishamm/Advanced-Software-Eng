package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.models.Courses;
import com.example.demo.models.Instructor;
import com.example.demo.models.Student;
import com.example.demo.models.User;
import com.example.demo.repositories.StudentRepository;
import com.example.demo.repositories.UserRepository;

import jakarta.servlet.http.HttpSession;



@RequestMapping("/student")
@RestController
public class StudentController {


    @Autowired  
    private StudentRepository studentRepository;
    @Autowired  
    private UserRepository userRepository;



     @GetMapping("view-course")
   public ModelAndView view_course(HttpSession session) {
       ModelAndView mav = new ModelAndView("student-coursespage.html");
       String email= session.getAttribute("email") .toString();
       User user = userRepository.findByEmail(email);
       Student student= this.studentRepository.findByUser(user);


       List<Courses> courses = student.getCourses();
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
