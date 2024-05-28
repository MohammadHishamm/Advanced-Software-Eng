// package com.example.demo;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.mock.web.MockHttpSession;
// import org.springframework.validation.BeanPropertyBindingResult;
// import org.springframework.validation.BindingResult;
// import org.springframework.web.multipart.MultipartFile;
// import org.springframework.web.servlet.ModelAndView;

// import com.example.demo.Services.ImageService;
// import com.example.demo.controller.coursesController;
// import com.example.demo.models.Cart;
// import com.example.demo.models.Courses;
// import com.example.demo.models.Instructor;
// import com.example.demo.models.Student;
// import com.example.demo.models.User;
// import com.example.demo.repositories.CoursesRepository;
// import com.example.demo.repositories.InstructorRepository;
// import com.example.demo.repositories.StudentRepository;
// import com.example.demo.repositories.UserRepository;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertNotNull;
// import static org.mockito.ArgumentMatchers.any;
// import static org.mockito.Mockito.never;
// import static org.mockito.Mockito.verify;
// import static org.mockito.Mockito.when;

// import java.io.IOException;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.MockitoAnnotations;
// public class AddCourseTest {

//     @InjectMocks
//     private coursesController CoursesController;


//     @Mock
//     private CoursesRepository coursesRepository;

//     @Mock
//     private InstructorRepository instructorRepository;

//     @Mock
//     private UserRepository userRepository;

//     @Mock
//     private StudentRepository studentRepository;

//     @Mock
//     private ImageService imageService;


// @BeforeEach
//     public void setUp() {
//         // Initialize mocks
//         MockitoAnnotations.openMocks(this);

//         // Mock UserRepository behavior
//         User user = new User();
//         user.setUser_id(13);
//         user.setEmail("xplayer222xz@gmail.com");
//         when(userRepository.findByEmail("xplayer222xz@gmail.com")).thenReturn(user);

//         // Mock CoursesRepository behavior
//         Courses course = new Courses();
//         course.setCourse_id(1);
//         when(coursesRepository.findById(1)).thenReturn(course);

//         // Mock StudentRepository behavior
//         Student student = new Student();
//         student.setUser(user);
//         when(studentRepository.findByUser(user)).thenReturn(student);

//        Instructor instructor = new Instructor();
//        instructor.setUser(user);
//        instructor.setStatus("Observing");
//        when(instructorRepository.findByUser(user)).thenReturn(instructor);
        
//     }

//   @Test
//     public void testSaveCourse_ValidData() throws IOException {
        
//         Courses course = new Courses();
//         course.setCourse_id(1);
//         course.setCourse_title("Java");
//         course.setCourse_status("Active");
//         course.setCourse_description("Java is a programming language");
//         course.setCourse_requirements("Java is a programming language");
//         course.setCourse_price(100.00);
//         String email = "xplayer222xz@gmail.com";
//         MockHttpSession session = new MockHttpSession();
//         session.setAttribute("email", email);
//         BindingResult bindingResult = new BeanPropertyBindingResult(course, "course");
//         MultipartFile courseThumbnail = null; 

        
//         when(imageService.uploadImage(courseThumbnail)).thenReturn("../images/image.png");

        
//         ModelAndView mav = CoursesController.saveCourse(course, bindingResult, session, courseThumbnail);

        
//         assertEquals("redirect:/user/profile", mav.getViewName());
//         verify(instructorRepository).save(any(Instructor.class));
//         verify(coursesRepository).save(any(Courses.class));
//     }

//     @Test
//     public void testSaveCourse_WithBindingErrors() throws IOException {
        
//         Courses course = new Courses();
//         MockHttpSession session = new MockHttpSession();
//         BindingResult bindingResult = new BeanPropertyBindingResult(course, "course");
//         bindingResult.reject("errorCode", "errorMessage");
//         MultipartFile courseThumbnail = null; 

        
//         when(imageService.uploadImage(courseThumbnail)).thenReturn("../images/image.png");

        
//         ModelAndView mav = CoursesController.saveCourse(course, bindingResult, session, courseThumbnail);

        
//         assertEquals("teacher-coursespage.html", mav.getViewName());
//         assertNotNull(mav.getModel().get("errors"));
//         verify(instructorRepository, never()).save(any(Instructor.class));
//         verify(coursesRepository, never()).save(any(Courses.class));
//     }

//     @Test
//     public void testSaveCourse_UserNotLoggedIn() throws IOException {
       
//         Courses course = new Courses();
//         MockHttpSession session = new MockHttpSession();
//         BindingResult bindingResult = new BeanPropertyBindingResult(course, "course");
//         MultipartFile courseThumbnail = null; 

        
//         when(imageService.uploadImage(courseThumbnail)).thenReturn("../images/image.png");

        
//         ModelAndView mav = CoursesController.saveCourse(course, bindingResult, session, courseThumbnail);

        
//         assertEquals("teacher-coursespage.html", mav.getViewName());
//         verify(instructorRepository, never()).save(any(Instructor.class));
//         verify(coursesRepository, never()).save(any(Courses.class));
//     }

//     @Test
//     public void testSaveCourse_InstructorNotFound() throws IOException {
        
//         Courses course = new Courses();
//         String email = "xplayer222xz@gmail.com";
//         MockHttpSession session = new MockHttpSession();
//         session.setAttribute("email", email);
//         BindingResult bindingResult = new BeanPropertyBindingResult(course, "course");

        
//         User user = new User();
//         user.setUser_id(13);
//         user.setEmail(email);
//         when(userRepository.findByEmail(email)).thenReturn(user);
//         when(instructorRepository.findByUser(user)).thenReturn(null);
//         MultipartFile courseThumbnail = null; 

        
//         when(imageService.uploadImage(courseThumbnail)).thenReturn("../images/image.png");

        
//         ModelAndView mav = CoursesController.saveCourse(course, bindingResult, session, courseThumbnail);

        
//         assertEquals("teacher-coursespage.html", mav.getViewName());
//         verify(instructorRepository, never()).save(any(Instructor.class));
//         verify(coursesRepository, never()).save(any(Courses.class));
//     }

// }
