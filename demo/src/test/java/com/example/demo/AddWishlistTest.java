package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.never;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;

import com.example.demo.controller.UserController;
import com.example.demo.models.Courses;
import com.example.demo.models.Student;
import com.example.demo.models.User;
import com.example.demo.models.Wishlist;
import com.example.demo.repositories.CoursesRepository;
import com.example.demo.repositories.StudentRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.repositories.WishlistRepository;

public class AddWishlistTest {

    @InjectMocks
    private UserController wishController;

    @Mock
    private UserRepository userRepository;

    @Mock
    private CoursesRepository coursesRepository;

    @Mock
    private WishlistRepository wishlistRepository;

    @Mock
    private StudentRepository studentRepository;

    @BeforeEach
    public void setUp() {
        // Initialize mocks
        MockitoAnnotations.openMocks(this);

        // Mock UserRepository behavior
        User user = new User();
        user.setUser_id(13);
        user.setEmail("xplayer222xz@gmail.com");
        when(userRepository.findByEmail("xplayer222xz@gmail.com")).thenReturn(user);

        // Mock CoursesRepository behavior
        Courses course = new Courses();
        course.setCourse_id(1);
        when(coursesRepository.findById(1)).thenReturn(course);

        // Mock StudentRepository behavior
        Student student = new Student();
        student.setUser(user);
        when(studentRepository.findByUser(user)).thenReturn(student);

        // Mock WishlistRepository behavior
        Wishlist wishlist = new Wishlist();
        wishlist.setStudent(student);
        when(wishlistRepository.findByStudent(student)).thenReturn(wishlist);
    }

    @Test
    public void testAddCourseToWishlistSuccessfully() throws IOException {
        // Setup test data
        int courseId = 1;
        String email = "xplayer222xz@gmail.com";
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("email", email);
        MockHttpServletResponse response = new MockHttpServletResponse();

        // Execute the method
        wishController.addwishlist(courseId, session, response);

        // Verify and assert
        assertEquals("Course added to Wishlist successfully.", response.getContentAsString());
        verify(wishlistRepository).save(any(Wishlist.class));
    }

    @Test
    public void testAddWishlistCourseAlreadyExists() throws IOException {
        // Setup test data
        int courseId = 1;
        String email = "xplayer222xz@gmail.com";
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("email", email);
        MockHttpServletResponse response = new MockHttpServletResponse();

        // Mock WishlistRepository behavior with existing course
        User user = userRepository.findByEmail(email);
        Student student = studentRepository.findByUser(user);
        Wishlist wishlist = new Wishlist();
        wishlist.setStudent(student);
        List<Courses> coursesList = new ArrayList<>();
        Courses course = new Courses();
        course.setCourse_id(courseId);
        coursesList.add(course);
        wishlist.setCourses(coursesList);
        when(wishlistRepository.findByStudent(student)).thenReturn(wishlist);

        // Execute the method
        wishController.addwishlist(courseId, session, response);

        // Verify and assert
        assertEquals("Course already exists in the Wishlist.", response.getContentAsString());
        verify(wishlistRepository, never()).save(any(Wishlist.class));
    }

    @Test
    public void testAddWishlistUserNotLoggedIn() throws IOException {
        // Setup test data
        int courseId = 1;
        MockHttpSession session = new MockHttpSession();
        MockHttpServletResponse response = new MockHttpServletResponse();

        // Execute the method
        wishController.addwishlist(courseId, session, response);

        // Verify and assert
        assertEquals("User not logged in.", response.getContentAsString());
        verify(wishlistRepository, never()).save(any(Wishlist.class));
    }
}
