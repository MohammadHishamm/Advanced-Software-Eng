package com.example.demo.controller;

import java.io.IOException;
import java.util.List;

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
import com.example.demo.models.Student;
import com.example.demo.models.User;
import com.example.demo.repositories.CartRepository;
import com.example.demo.repositories.CoursesRepository;
import com.example.demo.repositories.InstructorRepository;

import com.example.demo.repositories.StudentRepository;

import com.example.demo.repositories.UserRepository;
import com.example.demo.repositories.WishlistRepository;
import com.example.demo.Services.ImageService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@RequestMapping("/courses")
@RestController
public class coursesController {

    @Autowired
    ImageService imageservice;

    @Autowired
    private CoursesRepository coursesRepository;

    @Autowired
    private InstructorRepository instructorRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private WishlistRepository wishlistRepository;
    @Autowired
    private CartRepository cartRepository;

    @GetMapping("")
    public ModelAndView getcourses() {
        ModelAndView mav = new ModelAndView("viewCourses.html");
        List<Courses> courses = this.coursesRepository.findAll();
        mav.addObject("courses", courses);
        return mav;

    }

    @GetMapping("add-course")
    public ModelAndView courses() {
        ModelAndView mav = new ModelAndView("teacher-coursespage.html");
        Courses course = new Courses();
        mav.addObject("course", course);
        return mav;
    }

    @PostMapping("save-course")
    public ModelAndView saveCourse(@ModelAttribute @Valid Courses course, BindingResult bindingResult,
            HttpSession session ,  @RequestParam("coursethumbnail") MultipartFile courseThumbnail) throws IOException {

        ModelAndView mav = new ModelAndView("teacher-coursespage.html");

      
        
        if (bindingResult.hasErrors()) {
            mav.addObject("courseerrors", bindingResult.getAllErrors());
            return mav;
        }

        if (session.getAttribute("email") != null) {
            String email = session.getAttribute("email").toString();
            User user = userRepository.findByEmail(email);
            Instructor instructor = this.instructorRepository.findByUser(user);

            if (instructor != null) {

                List<Courses> instructorCourses = instructor.getCourses();
                
                String filePath = imageservice.uploadImage(courseThumbnail);
                course.setImage(filePath);

        
                instructorCourses.add(course);
                instructor.setCourses(instructorCourses);
                course.setInstructor(instructor);

                this.instructorRepository.save(instructor);
                this.coursesRepository.save(course);

                return new ModelAndView("redirect:/user/profile");
            } else {

           
                return mav;
            }
        } else { 

        
            return mav;
        }

    }

    @GetMapping("view-course")
    public ModelAndView view_course(@RequestParam("courseid") int id, HttpSession session) {
        ModelAndView mav = new ModelAndView("view-course.html");
        Courses course = this.coursesRepository.findById(id);
        if (course != null) {
            if (session != null) {
                // get student from user id to see if the student is already enrolled in the
                // course or not
                String useremail = (String) session.getAttribute("email");
                User user1 = this.userRepository.findByEmail(useremail);
                Student student = this.studentRepository.findByUser(user1);
          
                // all the courses that this student enrolled in
                List<Courses> courses = student.getCourses();

                boolean enrolled_in = false;
                for (Courses c : courses) {
                    if (c.getCourse_id() == id) {
                        enrolled_in = true;
                        break;
                    }
                }
                System.out.println(enrolled_in);
                Instructor instructor = course.getInstructor();
                User user2 = instructor.getUser();
                mav.addObject("course", course);
                mav.addObject("User", user2);
                mav.addObject("enroll", enrolled_in);
           
              
            }
            else 
            {
                mav = new ModelAndView("Signup-in.html");
            }

        } else {
            mav = new ModelAndView("index.html");
        }

        return mav;
    }

    @GetMapping("enroll-course")
    public ModelAndView enroll_course(@RequestParam("courseid") int id, HttpSession session) {
        ModelAndView mav = new ModelAndView("view-course.html");
        Courses course = this.coursesRepository.findById(id);
        if (course != null) {
            if (session != null) {
                // get student from user id to see if the student is already enrolled in the
                // course or not
                String useremail = (String) session.getAttribute("email");
                User user1 = this.userRepository.findByEmail(useremail);
                Student student = this.studentRepository.findByUser(user1);

                List<Courses> courses = student.getCourses();

                boolean enrolled_in = false;
                for (Courses c : courses) {
                    if (c.getCourse_id() == id) {
                        enrolled_in = true;
                        break;
                    }
                }

                if (enrolled_in == false) {
                    courses.add(course);
                    student.setCourses(courses);

                    this.studentRepository.save(student);
                } else {

                }

                mav = new ModelAndView("Profile.html");
            } else {
                mav = new ModelAndView("Signup-in.html");
            }

        } else {
            mav = new ModelAndView("index.html");
        }

        return mav;
    }

    @GetMapping("/edit-course")
    public ModelAndView editCourse(@RequestParam("courseid") int courseId) {
        Courses existingCourse = coursesRepository.findById(courseId);
        ModelAndView mav = new ModelAndView("edit-course.html");
        mav.addObject("existingCourse", existingCourse);
        return mav;
    }

    @PostMapping("/update-course")
    public ModelAndView updateCourse(@ModelAttribute("existingCourse") @Valid Courses updatedCourse,
            BindingResult bindingResult,
            HttpSession session) {
        ModelAndView mav = new ModelAndView("edit-course.html");

        int courseId = updatedCourse.getCourse_id(); 

        if (bindingResult.hasErrors()) {
            mav.addObject("courseerrors", bindingResult.getAllErrors());
            return mav;
        }

        Courses existingCourse = coursesRepository.findById(courseId);

        if (existingCourse != null) {
            // Update the existing course with the new data
            existingCourse.setCourse_title(updatedCourse.getCourse_title());
            existingCourse.setCourse_status(updatedCourse.getCourse_status());
            existingCourse.setCourse_description(updatedCourse.getCourse_description());
            existingCourse.setCourse_requirements(updatedCourse.getCourse_requirements());
            existingCourse.setCourse_price(updatedCourse.getCourse_price());

            // Save the updated course
            coursesRepository.save(existingCourse);

            // Redirect to the view page for the updated course
            mav.setViewName("redirect:/teacher/view-course");
        } else {
            mav.addObject("errorMessage", "Course not found");
        }

        return mav;
    }

    @GetMapping("/delete-course")
    public ModelAndView deleteCourse(@RequestParam("courseid") int courseId) {
        ModelAndView mav = new ModelAndView();

        Courses courseToDelete = coursesRepository.findById(courseId);
        

        if (courseToDelete != null) {
            coursesRepository.delete(courseToDelete);
            mav.setViewName("redirect:/user/profile");
        } else {
            mav.setViewName("error-page");
        }

        return mav;
    }


 





}
