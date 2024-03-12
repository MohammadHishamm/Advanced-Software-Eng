package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;

import jakarta.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;




@RestController
@RequestMapping("/user")
public class UserController {

      @Autowired  
    private UserRepository userRepository;

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


}
