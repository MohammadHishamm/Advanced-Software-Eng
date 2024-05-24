// package com.example.demo;

// import static org.junit.jupiter.api.Assertions.assertEquals;

// import static org.mockito.ArgumentMatchers.any;

// import static org.mockito.Mockito.verify;
// import static org.mockito.Mockito.when;

// import org.junit.jupiter.api.BeforeEach;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.MockitoAnnotations;

// import org.springframework.validation.BeanPropertyBindingResult;
// import org.springframework.validation.BindingResult;
// import org.springframework.web.servlet.ModelAndView;
// import org.junit.jupiter.api.Test;
// import com.example.demo.controller.courseContentController;
// import com.example.demo.models.Courses;
// import com.example.demo.models.CourseContent;

// import com.example.demo.repositories.CourseContentRepository;
// import com.example.demo.repositories.CoursesRepository;


// public class AddContentTest {
    
// @InjectMocks
//     private courseContentController CourseContentController;

//     @Mock
//     private CoursesRepository coursesRepository;

//      @Mock
//     private CourseContentRepository courseContentRepository;


//     @BeforeEach
//     public void setUp() {
//         // Initialize mocks
//         MockitoAnnotations.openMocks(this);

       
//         Courses course = new Courses();
//         course.setCourse_id(1);
//         when(coursesRepository.findById(1)).thenReturn(course);

//         CourseContent courseContent = new CourseContent();
//         courseContent.setCourse(course);
//         when(courseContentRepository.findById(1)).thenReturn(courseContent);

//     }

//    @Test
//     public void testSaveCourseContent() {
        
//         CourseContent courseContent = new CourseContent();
//         courseContent.setVideo_playlist("1");
//         courseContent.setVideo_title("Science");
//         courseContent.setVideo_description("Learn Science");
//         courseContent.setVideo_thumbnail("Video image");
//         courseContent.setVideo_play("Video");
//         BindingResult bindingResult = new BeanPropertyBindingResult(courseContent, "courseContent");

        
//         ModelAndView mav = CourseContentController.saveCourseContent(courseContent, bindingResult);

        
//         assertEquals("redirect:/teacher/view-course", mav.getViewName());
//         verify(courseContentRepository).save(any(CourseContent.class));
//         verify(coursesRepository).save(any(Courses.class));
//     }

    



// }
