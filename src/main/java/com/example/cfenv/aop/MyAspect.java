package com.example.cfenv.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyAspect {

    @Before("execution(* com.example.cfenv.web.*.*(..))")
    public void before(JoinPoint point){
        System.out.println("before aspect");
        System.out.println(point.getSignature());
    }    
 
}