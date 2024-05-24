package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.example.demo.models.Instructor;
import com.example.demo.models.User;
import com.example.demo.repositories.InstructorRepository;
import com.example.demo.repositories.UserRepository;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/admin")
@RestController
public class adminController {

    @Autowired
    private InstructorRepository instructorRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("")
    public ModelAndView getpage() {
        ModelAndView mav = new ModelAndView("Admin/index.html");
        List<User> users = userRepository.findAll();
        mav.addObject("users", users);
        return mav;
    }

    @GetMapping("/view-tutor")
    public ModelAndView view_tutor(HttpSession session) {
        ModelAndView mav = new ModelAndView("Admin/tutor.html");
        List<Instructor> instructorList = this.instructorRepository.findAll();
        if (session != null) {
            List<User> userlist = this.userRepository.findAll();
            mav.addObject("users", userlist);
            mav.addObject("instructors", instructorList);
        } else {
            mav = new ModelAndView("Signup-in.html");
        }

        return mav;
    }

    @DeleteMapping("/delete-user/{id}")
    public String deleteUser(@PathVariable int id) {
        userRepository.deleteById(id);
        return "User deleted successfully";
    }

}
