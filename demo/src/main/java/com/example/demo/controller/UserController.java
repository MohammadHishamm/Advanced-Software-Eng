package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.example.demo.models.Cart;
import com.example.demo.models.Courses;
import com.example.demo.models.Student;
import com.example.demo.models.User;
import com.example.demo.models.Wishlist;
import com.example.demo.repositories.CartRepository;
import com.example.demo.repositories.CoursesRepository;
import com.example.demo.repositories.StudentRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.repositories.WishlistRepository;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;





@RestController
@RequestMapping("/user")
public class UserController {

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

    @GetMapping("/profile")
    public ModelAndView viewProfile(HttpSession session) {
        ModelAndView mav = new ModelAndView("Profile.html");
        String userEmail = (String) session.getAttribute("email");
        if (userEmail == null) {
     
            return new ModelAndView("redirect:/sign");
        }
        
        User user = this.userRepository.findByEmail(userEmail);
        if (user != null) {
       
            mav.addObject("name", user.getName());
        } else {
           
            mav.addObject("errorMessage", "User not found");
        }
        
        return mav;
    }
    
    @GetMapping("edit")
    public ModelAndView editprifle(HttpSession session) {
        ModelAndView mav = new ModelAndView("edit-profile.html");
        String userEmail = (String) session.getAttribute("email");
        if (userEmail == null) {
     
            return new ModelAndView("redirect:/sign");
        }

        User user = this.userRepository.findByEmail(userEmail);
        if (user != null) {
       
            mav.addObject("name", user.getName());
            mav.addObject("email", user.getEmail());
            mav.addObject("password", user.getPassword());
        } else {
           
            mav.addObject("errorMessage", "User not found");
        }
        
    return mav;
    
    }

    @GetMapping("update")
    public ModelAndView updateuser(@RequestParam("email") String email) {
        User userToUpdate = userRepository.findByEmail(email);
        ModelAndView mav = new ModelAndView("edit-profile.html");

        mav.addObject("user", userToUpdate);
        return mav;
    }
    
    @PostMapping("update")
    public RedirectView updateUserProcess(@ModelAttribute User user) {
     
        User existingUser = userRepository.findByEmail(user.getEmail());
    
     
        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(12)));
   
    
        userRepository.save(existingUser);
    
        return new RedirectView("/user/profile");
    }
    
    @GetMapping("delete")
    public RedirectView deleteUser(HttpSession session)  {
        String Email = (String) session.getAttribute("email");
        User userToDelete = userRepository.findByEmail(Email);
        userRepository.delete(userToDelete);
        return new RedirectView("/");
    }



    @GetMapping("add-wishlist")
    public void addwishlist(@RequestParam("courseid") int courseid, HttpSession session ,  HttpServletResponse response) {
        Courses course = this.coursesRepository.findById(courseid);
        String email = (String) session.getAttribute("email");
        
        if(email != null) {
        User user = this.userRepository.findByEmail(email);
        Student student = this.studentRepository.findByUser(user);
    
        Wishlist wishlist = this.wishlistRepository.findByStudent(student);
        
        if (wishlist == null) {
         
            wishlist = new Wishlist();
            wishlist.setStudent(student);
        }
        List<Courses> courseslist = wishlist.getCourses();

        boolean course_found = false;

        for (Courses course_loop : courseslist) {
            if (course_loop.getCourse_id() == courseid) {
                course_found = true;
                break;
            }
        }

        if(!course_found) {
            courseslist.add(course);
            wishlist.setCourses(courseslist);
            this.wishlistRepository.save(wishlist);
            // Write response message
            try {
                response.getWriter().write("Course added to Wishlist successfully.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // Write response message
            try {
                response.getWriter().write("Course already exists in the Wishlist.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        }
        else
        {
            try {
                response.getWriter().write("User not logged in.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    
    }
    
    
 

@GetMapping("wishlist")
public ModelAndView viewwishlist( HttpSession session){
        ModelAndView mav = new ModelAndView("wishlist.html");
        String email = (String) session.getAttribute("email");
        User user = this.userRepository.findByEmail(email);
        Student student = this.studentRepository.findByUser(user);
        Wishlist wishlist = this.wishlistRepository.findByStudent(student);

        mav.addObject("courses", wishlist.getCourses());
        return mav;
     

}

@GetMapping("remove-wishlist")
public ModelAndView removeWishlist(@RequestParam("courseid") int courseId, HttpSession session) {
    ModelAndView mav = new ModelAndView("wishlist.html");

    String email = (String) session.getAttribute("email");
    User user = this.userRepository.findByEmail(email);
    Student student = this.studentRepository.findByUser(user);

    Wishlist wishlist = this.wishlistRepository.findByStudent(student);
    if (wishlist != null) {
        List<Courses> courses = wishlist.getCourses();
        Courses courseToRemove = null;
        for (Courses course : courses) {
            if (course.getCourse_id() == courseId) {
                courseToRemove = course;
                break;
            }
        }
        if (courseToRemove != null) {
            courses.remove(courseToRemove);
            this.wishlistRepository.save(wishlist);
            mav.addObject("message", "course removed from wishlist");
        }
    }

  
    wishlist = this.wishlistRepository.findByStudent(student);

    mav.addObject("courses", wishlist.getCourses());
    return mav;
}



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
