package com.example.cfenv.web;

import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAspectJAutoProxy
public class web {
    
    @GetMapping("/web")
    public String helloString(){
        dummyMethod();
        return ("Spring boot web page from David. ");
    }

    public void dummyMethod(){
        System.out.println("dummyMethod called by helloString.");
    }

}
