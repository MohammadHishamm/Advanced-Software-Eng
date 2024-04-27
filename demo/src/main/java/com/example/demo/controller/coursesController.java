package com.example.demo.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.servlet.ModelAndView;

import com.example.demo.models.Courses;
import com.example.demo.models.Instructor;
import com.example.demo.models.Student;
import com.example.demo.models.User;
import com.example.demo.repositories.CoursesRepository;
import com.example.demo.repositories.InstructorRepository;
<<<<<<< HEAD
=======
import com.example.demo.repositories.StudentRepository;
>>>>>>> b69c209add577b2010f27c5a0779ccab5271a678
import com.example.demo.repositories.UserRepository;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@RequestMapping("/courses")
@RestController
public class coursesController {

    @Autowired
    private CoursesRepository coursesRepository;
    @Autowired
    private InstructorRepository instructorRepository;
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StudentRepository studentRepository;
    
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
    public ModelAndView saveCourse(@ModelAttribute @Valid Courses course, BindingResult bindingResult ,HttpSession session) {
        ModelAndView mav = new ModelAndView("courses.html");
        
        if (bindingResult.hasErrors()) {
            mav.addObject("errors", bindingResult.getAllErrors());
            return mav;
        }

        if (session.getAttribute("email") != null)
        {
            String email= session.getAttribute("email") .toString();
            User user = userRepository.findByEmail(email);
            Instructor instructor= this.instructorRepository.findByUser(user);

            if (instructor != null) {
              
                List<Courses> instructorCourses = instructor.getCourses();
                instructorCourses.add(course);
                instructor.setCourses(instructorCourses);
                course.setInstructor(instructor);
    
              
                this.instructorRepository.save(instructor);
                this.coursesRepository.save(course);
                
               
                return new ModelAndView("redirect:/courses/add-course");
            } else {
              
              //  mav.addObject("error", "Instructor not found");
                return mav;
            }
        } else {
          
         //   mav.addObject("error", "User session not found");
            return mav;
        }
      
    }



    @GetMapping("view-course")
    public ModelAndView view_course(@RequestParam("courseid") int id ,HttpSession session ) {
       ModelAndView mav = new ModelAndView("view-course.html");
       Courses course =  this.coursesRepository.findById(id);
       if(course != null) 
       {
            if(session != null)
            {   
                //get student from user id to see if the student is already enrolled in the course or not
                String useremail = (String) session.getAttribute("email");
                User user1 = userRepository.findByEmail(useremail);
                Student student = studentRepository.findByUser(user1);
                //all the courses that this student enrolled in 
                List<Courses> courses = student.getCourses();

                boolean enrolled_in = false;
                for (Courses c : courses) {
                    if (c.getCourse_id() == id) {
                        enrolled_in = true;
                        break;
                    }
                }

                Instructor instructor =  course.getInstructor();
                User user2 = instructor.getUser();
                mav.addObject("course" , course );
                mav.addObject("User" , user2 );
                mav.addObject("enroll" , enrolled_in );
            }
            else
            {
                mav = new ModelAndView("Signup-in.html");
            }

       }
       else
       {
            mav = new ModelAndView("index.html");
       }

       return mav;
    }



    




}
