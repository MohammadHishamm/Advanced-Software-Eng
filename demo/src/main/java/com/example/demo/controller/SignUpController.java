package com.example.demo.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;

import jakarta.validation.Valid;


import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.validation.Errors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/sign")
@RestController
public class SignUpController {
    @Autowired  
    private UserRepository userRepository;

    @GetMapping("")
    public ModelAndView getpage() {
        ModelAndView mav = new ModelAndView("Signup-in.html");
        return mav;
    }
    



    
    @GetMapping("signup")
    public ModelAndView signup() {
        ModelAndView mav= new ModelAndView("Signup-in.html");
        User newuser= new User();
        mav.addObject("user", newuser);
        return mav;
    }

    @PostMapping("signup")
    public ModelAndView saveUser(@Valid @ModelAttribute User user, Errors errors) {
        ModelAndView mav = new ModelAndView("Signup-in.html");
   
           
            if (errors.hasErrors()) {
                mav.addObject("errors", errors.getAllErrors());
                return mav;
            }
        
    
       
        String encodePassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(12));
        user.setPassword(encodePassword);
        this.userRepository.save(user);
        return new ModelAndView("redirect:/");
    }
    


@GetMapping("signin")
public ModelAndView signin() {
    ModelAndView mav = new ModelAndView("Signup-in.html");
    User newuser = new User();
    mav.addObject("user", newuser);
    return mav;
}
@PostMapping("signin")
public RedirectView signinProcess(@RequestParam("email") String email,@RequestParam("password") String password) {
    User dbUser=  this.userRepository.findByEmail(email);
    Boolean ispasswordmatched= BCrypt.checkpw(password, dbUser.getPassword());

    if(ispasswordmatched)
    return new RedirectView("/");
    else
    return new RedirectView("/user/signin");

}



    


    
}
