package com.example.demo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;
import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;

import com.example.demo.controller.UserController;
import com.example.demo.repositories.CartRepository;
import com.example.demo.repositories.CoursesRepository;
import com.example.demo.repositories.StudentRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.repositories.WishlistRepository;
import com.example.demo.models.Cart;
import com.example.demo.models.Courses;
import com.example.demo.models.Student;
import com.example.demo.models.User;

public class AddCartTest {

    @InjectMocks
    private UserController CartController;

    @Mock
    private UserRepository userRepository;

    @Mock
    private CoursesRepository coursesRepository;

    @Mock
    private WishlistRepository wishlistRepository;

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private CartRepository cartRepository;

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

        // Mock CartRepository behavior
        Cart cart = new Cart();
        cart.setStudent(student);
        when(cartRepository.findByStudent(student)).thenReturn(cart);
    }

   @Test
    public void testAddCourseToCartSuccessfully() throws IOException {
        // Setup test data
        int courseId = 1;
        String email = "xplayer222xz@gmail.com";
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("email", email);
        MockHttpServletResponse response = new MockHttpServletResponse();

        // Execute the method
        CartController.addcart(courseId, session, response);

        // Verify and assert
        assertEquals("Course added to cart successfully.", response.getContentAsString());
        verify(cartRepository).save(any(Cart.class));
    }



    @Test
    public void testUserNotLoggedIn() throws IOException {
        // Setup test data
        int courseId = 1;
        MockHttpSession session = new MockHttpSession();
        MockHttpServletResponse response = new MockHttpServletResponse();

        // Execute the method
        CartController.addcart(courseId, session, response);

        // Verify and assert
        assertEquals("User not logged in.", response.getContentAsString());
    }

}