package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.example.demo.models.CourseContent;
import com.example.demo.models.Courses;
import com.example.demo.repositories.CourseContentRepository;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import java.io.IOException;

@RequestMapping("/courses")
@RestController
public class courseContentController {

    @Autowired
    private CourseContentRepository courseContentRepository;

    @GetMapping("add-course-content")
    public ModelAndView courses() {
        ModelAndView mav = new ModelAndView("courses.html");
        Courses course = new Courses();
        mav.addObject("course", course);
        return mav;
    }

    @PostMapping("save-course-content") 
    public ModelAndView saveCourse(@ModelAttribute @Valid CourseContent courseContent, BindingResult bindingResult, HttpSession session) {
        ModelAndView mav = new ModelAndView("courses.html");

        if (bindingResult.hasErrors()) {
            mav.addObject("errors", bindingResult.getAllErrors());
            return mav;
        }

        if (session.getAttribute("email") != null) {
            
        courseContentRepository.save(courseContent);

               
        }
      
        return mav;
    }
}
