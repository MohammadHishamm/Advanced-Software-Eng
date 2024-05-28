package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import org.junit.jupiter.api.Test;

import com.example.demo.Services.FileService;
import com.example.demo.controller.courseContentController;
import com.example.demo.models.Courses;
import com.example.demo.models.Instructor;
import com.example.demo.models.User;
import com.example.demo.models.CourseContent;

import com.example.demo.repositories.CourseContentRepository;
import com.example.demo.repositories.CoursesRepository;
import com.example.demo.repositories.InstructorRepository;
import com.example.demo.repositories.UserRepository;


public class AddContentTest {
    
@InjectMocks
    private courseContentController courseContentController;

    @Mock
    private UserRepository userRepository;

    @Mock
    private InstructorRepository instructorRepository;

    @Mock
    private CoursesRepository coursesRepository;

    @Mock
    private CourseContentRepository courseContentRepository;

    @Mock
    private FileService fileService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        User user = new User();
        user.setUser_id(13);
        user.setEmail("xplayer222xz@gmail.com");
        when(userRepository.findByEmail("xplayer222xz@gmail.com")).thenReturn(user);

        Instructor instructor = new Instructor();
        instructor.setUser(user);
        instructor.setCourses(new ArrayList<>());
        when(instructorRepository.findByUser(user)).thenReturn(instructor);
    }

    @Test
    public void testSaveCourseContent_ValidData() throws IOException {
        CourseContent courseContent = new CourseContent();
        courseContent.setVideo_playlist("1");
        courseContent.setVideo_title("Science");
        courseContent.setVideo_description("Learn Science");
        courseContent.setVideo_thumbnail("Video image");
        courseContent.setVideo_play("Video");
        BindingResult bindingResult = new BeanPropertyBindingResult(courseContent, "courseContent");

        String email = "xplayer222xz@gmail.com";
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("email", email);

        MockMultipartFile courseThumbnail = new MockMultipartFile("courseThumbnail", "thumb.jpg", "image/jpeg", new byte[]{});
        MockMultipartFile courseVideo = new MockMultipartFile("courseVideo", "video.mp4", "video/mp4", new byte[]{});

        when(fileService.uploadImage(courseThumbnail, "demo/src/main/resources/static/Images/courses/thumbs/")).thenReturn("uploaded/thumb.jpg");
        when(fileService.uploadImage(courseVideo, "demo/src/main/resources/static/Images/courses/videos/")).thenReturn("uploaded/video.mp4");

        Courses course = new Courses();
        course.setCourse_id(1);
        course.setCoursecontent(new ArrayList<>());
        when(coursesRepository.findById(1)).thenReturn(course);

        ModelAndView mav = courseContentController.saveCourseContent(courseContent, bindingResult, session, courseThumbnail, courseVideo);

        assertEquals("redirect:/teacher/view-course", mav.getViewName());

        verify(userRepository).findByEmail(email);
        verify(fileService).uploadImage(courseThumbnail, "demo/src/main/resources/static/Images/courses/thumbs/");
        verify(fileService).uploadImage(courseVideo, "demo/src/main/resources/static/Images/courses/videos/");
        verify(courseContentRepository).save(any(CourseContent.class));
        verify(coursesRepository).save(any(Courses.class));
    }


     @Test
    public void testSaveCourseContent_InvalidData() throws IOException {
        CourseContent courseContent = new CourseContent();
        courseContent.setVideo_playlist("1");
        courseContent.setVideo_title("Science");
        courseContent.setVideo_description("Learn Science");
        courseContent.setVideo_thumbnail("Video image");
        courseContent.setVideo_play("Video");
        BindingResult bindingResult = new BeanPropertyBindingResult(courseContent, "courseContent");
        bindingResult.reject("error", "Some error");

        String email = "xplayer222xz@gmail.com";
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("email", email);

        MockMultipartFile courseThumbnail = new MockMultipartFile("courseThumbnail", "thumb.jpg", "image/jpeg", new byte[]{});
        MockMultipartFile courseVideo = new MockMultipartFile("courseVideo", "video.mp4", "video/mp4", new byte[]{});

        when(fileService.uploadImage(courseThumbnail, "demo/src/main/resources/static/Images/courses/thumbs/")).thenReturn("uploaded/thumb.jpg");
        when(fileService.uploadImage(courseVideo, "demo/src/main/resources/static/Images/courses/videos/")).thenReturn("uploaded/video.mp4");

        Courses course = new Courses();
        course.setCourse_id(1);
        List<CourseContent> contentList = new ArrayList<>();
        course.setCoursecontent(contentList);
        when(coursesRepository.findById(1)).thenReturn(course);

        ModelAndView mav = courseContentController.saveCourseContent(courseContent, bindingResult, session, courseThumbnail, courseVideo);

        assertEquals("teacher-coursespage.html", mav.getViewName());
        assertEquals(1, bindingResult.getErrorCount());

        verify(courseContentRepository, never()).save(any(CourseContent.class));
        verify(coursesRepository, never()).save(any(Courses.class));
    }

    



}
