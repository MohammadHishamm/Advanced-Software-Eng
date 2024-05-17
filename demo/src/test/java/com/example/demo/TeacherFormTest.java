package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

import com.example.demo.controller.InstructorController;
import com.example.demo.models.Cart;
import com.example.demo.models.Courses;
import com.example.demo.models.Instructor;
import com.example.demo.models.Student;
import com.example.demo.models.User;
import com.example.demo.repositories.InstructorRepository;
import com.example.demo.repositories.UserRepository;

public class TeacherFormTest {

    @InjectMocks
    private InstructorController instructorController;

    @Mock
    private UserRepository userRepository;

    @Mock
    private InstructorRepository instructorRepository;

    

    @BeforeEach
    public void setUp() {
        
        MockitoAnnotations.openMocks(this);

        
        User user = new User();
        user.setUser_id(13);
        user.setEmail("xplayer222xz@gmail.com");
        when(userRepository.findByEmail("xplayer222xz@gmail.com")).thenReturn(user);

       Instructor instructor = new Instructor();
       instructor.setUser(user);
       instructor.setStatus("Observing");
       when(instructorRepository.findByUser(user)).thenReturn(instructor);
    }

    @Test
    public void testSaveInstructor_ValidData() {
        Instructor instructor = new Instructor();
        String email = "xplayer222xz@gmail.com";
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("email", email);
      BindingResult bindingResult = new BeanPropertyBindingResult(instructor, "instructor");

        
        ModelAndView mav = instructorController.saveInstructor(instructor, bindingResult, session);

        verify(userRepository).findByEmail("xplayer222xz@gmail.com");

        
        assertEquals("Observing", instructor.getStatus());
    
        
        verify(instructorRepository).save(instructor);
        
    }

    
}
