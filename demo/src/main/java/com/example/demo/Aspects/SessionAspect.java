package com.example.demo.Aspects;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;

import jakarta.servlet.http.HttpSession;


@Aspect
@Component
public class SessionAspect {

    

    @Autowired
    private HttpSession httpSession;

    @Before("execution(* com.example.demo.controller.UserController.*(..)) && within(com.example.demo.controller.UserController)")
    public void beforeControllerMethods() throws ModelAndViewDefiningException {
        String userEmail = (String) httpSession.getAttribute("email");
        if (userEmail == null) {
          

            
            ModelAndView mav = new ModelAndView("/index");
            mav.addObject("sessionerror", "User session is empty. Please sign in.");
            throw new ModelAndViewDefiningException(mav);
        }
    }
}
