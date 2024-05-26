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
public ModelAndView saveCourseContent(@ModelAttribute @Valid CourseContent courseContent, BindingResult bindingResult, HttpSession session) {


    ModelAndView mav = new ModelAndView("teacher-coursespage.html");
    String email = session.getAttribute("email").toString();
    User user = userRepository.findByEmail(email);
    Instructor instructor = this.instructorRepository.findByUser(user);
    List<Courses> courses= instructor.getCourses();
    if (bindingResult.hasErrors()) {
        System.out.println("Validation errors:");
        bindingResult.getAllErrors().forEach(error -> System.out.println(error.toString()));
        mav.addObject("contenterrors", bindingResult.getAllErrors());
        mav.addObject("courses", courses);
        return mav;
    }
    


    int courseid = Integer.parseInt(courseContent.getVideo_playlist());
    Courses exsistingcourse = this.coursesRepository.findById(courseid);
    List<CourseContent> contentlist = exsistingcourse.getCoursecontent();
    contentlist.add(courseContent);
    exsistingcourse.setCoursecontent(contentlist);
    courseContent.setCourse(exsistingcourse);
    this.courseContentRepository.save(courseContent);
    this.coursesRepository.save(exsistingcourse);
    
    return new ModelAndView("redirect:/teacher/view-course");
  

 



   
     
 
    
}

@GetMapping("view-content")
public ModelAndView view_content(@RequestParam("contentid") int content_id , @RequestParam("courseid") int course_id ) {
    Courses course= this.coursesRepository.findById(course_id);
    ModelAndView mav = new ModelAndView("view-content.html");
    CourseContent specific_content = null ; 
    List<CourseContent> content = course.getCoursecontent();
    for (CourseContent c : content) {
        if (c.getVideo_id() == content_id) {
            specific_content = c;
            break;
        }
    }
         mav.addObject("specific_content" , specific_content );
       
   
   
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