package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;

@RequestMapping("/admin")
@RestController
public class adminController {
    private final UserRepository userRepository;

    @Autowired
    public adminController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("")
    public ModelAndView getUsersPage() {
        ModelAndView mav = new ModelAndView("Admin/index.html");
        return mav;
    }

    @GetMapping("/users")
    public ModelAndView getpage() {
        ModelAndView mav = new ModelAndView("Admin/users.html");
        List<User> users = userRepository.findAll();
        mav.addObject("users", users);
        return mav;
    }

}
