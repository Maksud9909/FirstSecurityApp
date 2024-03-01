package com.maksudrustamov.springboot.firstsecurityapp.controllers;


import com.maksudrustamov.springboot.firstsecurityapp.security.PersonDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;




@Controller
public class HelloController {
    @GetMapping("/hello")
    public String sayHello(){
        return "hello";
    }


    @GetMapping("/showUserInfo")
    public String showUserInfo(){
        // через SecurityContextHolder мы получаем доступ к обертеке Person, а через обертку уже получаем доступ к самой сущности
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal(); // закастили до человека, который регался
        System.out.println(personDetails.getPerson());



        return "hello";
    }
}
