package com.example.demo.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import com.example.demo.models.Student;
import com.example.demo.models.User;
import com.example.demo.repositories.StudentRepository;
import com.example.demo.repositories.UserRepository;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RequestMapping("/sign")
@RestController
public class SignUpController {
    private static final Logger logger = LoggerFactory.getLogger(SignUpController.class);

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("")
    public ModelAndView getPage() {
        logger.info("Navigating to signup/signin page");
        ModelAndView mav = new ModelAndView("Signup-in.html");
        return mav;
    }

  
    @PostMapping("signup")
    public ModelAndView saveUser(@Valid @ModelAttribute User user, BindingResult bindingResult, HttpSession session, @RequestParam("Confirmpass") String confirmPassword) {
        logger.info("Attempting to save new user: {}", user.getEmail());
        ModelAndView mav = new ModelAndView("Signup-in.html");

        if (!user.getPassword().equals(confirmPassword)) {
            bindingResult.rejectValue("password", "error.user", "Passwords do not match");
        }

        if (bindingResult.hasErrors()) {
            mav.addObject("signupErrors", bindingResult.getAllErrors());
            logger.error("Signup errors: {}", bindingResult.getAllErrors());
            return mav;
        }

        // Encrypt password
        String encodedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(12));
        user.setPassword(encodedPassword);
        user.setType("student");
        Student student = new Student();
        student.setUser(user);
        this.userRepository.save(user);
        this.studentRepository.save(student);
        session.setAttribute("email", user.getEmail());
        session.setAttribute("id", user.getUser_id());
        session.setAttribute("type", user.getType());
        logger.info("User {} signed up successfully", user.getEmail());
        return new ModelAndView("redirect:/");
    }

  

    @PostMapping("signin")
    public ModelAndView signinProcess(@ModelAttribute("user") User user, BindingResult bindingResult, HttpSession session) {
        logger.info("Attempting to sign in user: {}", user.getEmail());
        ModelAndView mav = new ModelAndView("Signup-in.html");
        User dbUser = this.userRepository.findByEmail(user.getEmail());

        if (dbUser == null) {
            bindingResult.rejectValue("email", "error.user", "Invalid email");
            logger.error("Signin failed: Invalid email {}", user.getEmail());
        } else {
            boolean isPasswordMatched = BCrypt.checkpw(user.getPassword(), dbUser.getPassword());
            if (!isPasswordMatched) {
                bindingResult.rejectValue("password", "error.user", "Incorrect password");
                logger.error("Signin failed: Incorrect password for email {}", user.getEmail());
            } else {
                session.setAttribute("id", user.getUser_id());
                session.setAttribute("email", dbUser.getEmail());
                session.setAttribute("type", dbUser.getType());
                logger.info("User {} signed in successfully", user.getEmail());
            }
        }

        if (bindingResult.hasErrors()) {
            mav.addObject("signinErrors", bindingResult.getAllErrors());
            return mav;
        }
        return new ModelAndView("redirect:/");
    }

    @GetMapping("signout")
    public RedirectView signout(HttpSession session) {
        logger.info("User {} signed out", session.getAttribute("email"));
        session.invalidate();
        return new RedirectView("/");
    }
}
