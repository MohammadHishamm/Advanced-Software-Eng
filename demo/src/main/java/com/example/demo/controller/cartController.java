package com.example.demo.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.models.Cart;
import com.example.demo.models.Courses;
import com.example.demo.models.Student;
import com.example.demo.models.User;
import com.example.demo.repositories.CartRepository;
import com.example.demo.repositories.CoursesRepository;
import com.example.demo.repositories.StudentRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.repositories.WishlistRepository;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@RequestMapping("/cart")
@RestController
public class cartController {
    
    

 @Autowired  
    private UserRepository userRepository;
    @Autowired  
    private CoursesRepository coursesRepository;
    @Autowired  
    private WishlistRepository wishlistRepository;
    @Autowired  
    private StudentRepository studentRepository;
    @Autowired  
    private CartRepository cartRepository;
    

@GetMapping("add-cart")
public void addcart(@RequestParam("courseid") int courseid, HttpSession session, HttpServletResponse response) {
    String email = (String) session.getAttribute("email");
    if(email != null) {
        Courses course = this.coursesRepository.findById(courseid);
        User user = this.userRepository.findByEmail(email);
        Student student = this.studentRepository.findByUser(user);

        Cart cart = this.cartRepository.findByStudent(student);

        if (cart == null) { 
            cart = new Cart(); 
            cart.setStudent(student);
        }

        List<Courses> courseslist = cart.getCourses();
        boolean course_found = false;

        for (Courses course_loop : courseslist) {
            if (course_loop.getCourse_id() == courseid) {
                course_found = true;
                break;
            }
        }

        if(!course_found) {
            courseslist.add(course);
            cart.setCourses(courseslist);
            this.cartRepository.save(cart);
            // Write response message
            try {
                response.getWriter().write("Course added to cart successfully.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // Write response message
            try {
                response.getWriter().write("Course already exists in the cart.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    } else {
        // Write response message
        try {
            response.getWriter().write("User not logged in.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

 

@GetMapping("view-cart")
public ModelAndView viewcart( HttpSession session){
        ModelAndView mav = new ModelAndView("cart.html");
        String email = (String) session.getAttribute("email");
        User user = this.userRepository.findByEmail(email);
        Student student = this.studentRepository.findByUser(user);
        Cart cart = this.cartRepository.findByStudent(student);

        mav.addObject("courses", cart.getCourses());
        return mav;
     

}

@GetMapping("remove-cart")
public ModelAndView removecart(@RequestParam("courseid") int courseId, HttpSession session) 
{
    ModelAndView mav = new ModelAndView("cart.html");

    String email = (String) session.getAttribute("email");
    if(email != null)
    {
        User user = this.userRepository.findByEmail(email);
        Student student = this.studentRepository.findByUser(user);
    
        Cart cart = this.cartRepository.findByStudent(student);
        if (cart != null) {
            List<Courses> courses = cart.getCourses();
            Courses courseToRemove = null;
            for (Courses course : courses) {
                if (course.getCourse_id() == courseId) {
                    courseToRemove = course;
                    break;
                }
            }
            if (courseToRemove != null)
             {
                courses.remove(courseToRemove);
                this.cartRepository.save(cart);
                mav.addObject("message", "course removed from cart");
            }
        }
    
        // Fetch wishlist again after removal in case it's updated
         cart = this.cartRepository.findByStudent(student);
    
        mav.addObject("courses", cart.getCourses());
    }
    else
    {
         mav = new ModelAndView("Signup-in.html");
    }
   
    return mav;
}



}
