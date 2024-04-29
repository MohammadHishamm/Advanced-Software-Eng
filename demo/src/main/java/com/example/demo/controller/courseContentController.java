package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.example.demo.models.CourseContent;
import com.example.demo.models.Courses;
import com.example.demo.models.Instructor;
import com.example.demo.models.User;
import com.example.demo.repositories.CourseContentRepository;
import com.example.demo.repositories.CoursesRepository;
import com.example.demo.repositories.InstructorRepository;
import com.example.demo.repositories.UserRepository;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import java.io.IOException;
import java.util.List;

@RequestMapping("/coursescontent")
@RestController
public class courseContentController {

    @Autowired
    private CourseContentRepository courseContentRepository;

    @Autowired
private CoursesRepository coursesRepository;

@Autowired
private UserRepository userRepository;

@Autowired
private InstructorRepository instructorRepository;

   @GetMapping("add-course-content")
public ModelAndView courseContent(@RequestParam("course_id") int courseId) {
    ModelAndView mav = new ModelAndView("courses.html");
    CourseContent content = new CourseContent(); 
    mav.addObject("courseContent", content);
    Courses course = this.coursesRepository.findById(courseId);
    mav.addObject("course", course);

    
    // Fetch all courses from the database
    List<Courses> allCourses = this.coursesRepository.findAll();
    mav.addObject("courses", allCourses);
    
    return mav;
}
@PostMapping("save-course-content")
public ModelAndView saveCourseContent(@ModelAttribute @Valid CourseContent courseContent,
                                      BindingResult bindingResult) {
    ModelAndView mav = new ModelAndView("courses.html");
    int courseid = Integer.parseInt(courseContent.getVideo_playlist());
    Courses exsistingcourse = this.coursesRepository.findById(courseid);
    List<CourseContent> contentlist = exsistingcourse.getCoursecontent();
    contentlist.add(courseContent);
    exsistingcourse.setCoursecontent(contentlist);
    courseContent.setCourse(exsistingcourse);




    this.courseContentRepository.save(courseContent);
    this.coursesRepository.save(exsistingcourse);
    
    return new ModelAndView("redirect:/teacher/view-course");





    // if (bindingResult.hasErrors()) {
        
    //      mav.addObject("contenterrors", bindingResult.getAllErrors());
    //     return mav;
    
    // }
        

     

    
}

@GetMapping("view-content")
public ModelAndView view_content(@RequestParam("courseid") int courseId) {
    Courses course= this.coursesRepository.findById(courseId);
    ModelAndView mav = new ModelAndView("view-content.html");
   
    List<CourseContent> content = course.getCoursecontent();
   
         mav.addObject("courseContents" , content );
       
   
   
    return mav;



}

@GetMapping("/delete-content")
public ModelAndView deleteContent(@RequestParam("courseid") int courseId) {
    ModelAndView mav = new ModelAndView();

    CourseContent contentToDelete = courseContentRepository.findById(courseId);

    if (contentToDelete != null) {
        courseContentRepository.delete(contentToDelete);
        mav.setViewName("redirect:/user/profile");
    } else {
        mav.setViewName("error-page");
    }

    return mav;
}




}