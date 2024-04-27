package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.models.CourseContent;
import com.example.demo.models.Courses;
import com.example.demo.repositories.CourseContentRepository;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@RequestMapping("/courses")
@RestController
public class courseContentController {
    @Autowired
    private CourseContentRepository courseContentRepository;

@GetMapping("add-course-content")
    public ModelAndView courses() {
       ModelAndView mav = new ModelAndView("courses.html");
       Courses course= new Courses();
       mav.addObject("course", course);
       return mav;
    }

    @PostMapping("save-course-content") 
    public ModelAndView saveCourse(@ModelAttribute @Valid CourseContent coursecontent, BindingResult bindingResult ,HttpSession session) {
        ModelAndView mav = new ModelAndView("courses.html");
        
        if (bindingResult.hasErrors()) {
            mav.addObject("errors", bindingResult.getAllErrors());
            return mav;
        }

        if (session.getAttribute("email") != null)
        {
            // int id  = (int)session.getAttribute("id");
            // Instructor instructor = instructorRepository.findbyUser_id(id);

            // if (instructor != null) {
              
            //     List<Courses> instructorCourses = instructor.getCourses();
            //     instructorCourses.add(course);
            //     instructor.setCourses(instructorCourses);
    
              
            //     instructorRepository.save(instructor);
                
               
            //     return new ModelAndView("redirect:/courses/add-course");
            // } else {
              
            //   //  mav.addObject("error", "Instructor not found");
                return mav;
            // }
        } else {
          
         //   mav.addObject("error", "User session not found");
            return mav;
        }
      
    }



}
