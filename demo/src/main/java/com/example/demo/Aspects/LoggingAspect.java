package com.example.demo.Aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
     private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Before("execution(* com.example.demo.controller.SignUpController.signup(..))")
    public void beforeSignup(JoinPoint joinPoint) {
        logger.info("Before signing up: " + joinPoint.getSignature());
    }

    @After("execution(* com.example.demo.controller.SignUpController.signup(..))")
    public void afterSignup(JoinPoint joinPoint) {
        logger.info("After signing up: " + joinPoint.getSignature());
    }

    @AfterReturning(pointcut = "execution(* com.example.demo.controller.SignUpController.saveUser(..))", returning = "result")
    public void afterSignupReturning(JoinPoint joinPoint, Object result) {
        logger.info("After saving user (signup): " + joinPoint.getSignature() + ", result=" + result);
    }

    @Before("execution(* com.example.demo.controller.SignUpController.signin(..))")
    public void beforeSignin(JoinPoint joinPoint) {
        logger.info("Before signing in: " + joinPoint.getSignature());
    }

    @After("execution(* com.example.demo.controller.SignUpController.signin(..))")
    public void afterSignin(JoinPoint joinPoint) {
        logger.info("After signing in: " + joinPoint.getSignature());
    }

    @AfterReturning(pointcut = "execution(* com.example.demo.controller.SignUpController.signinProcess(..))", returning = "result")
    public void afterSigninReturning(JoinPoint joinPoint, Object result) {
        logger.info("After processing signin: " + joinPoint.getSignature() + ", result=" + result);
    }

    @Around("within(com.example.demo.controller.*)")
    public Object timeTracker(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        long timeTakenInMs = endTime - startTime;
        logger.info("Time taken by " + joinPoint.getSignature() + " is " + timeTakenInMs + " ms");
        return result;
    }
    
}
