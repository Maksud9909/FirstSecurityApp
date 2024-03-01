package com.maksudrustamov.springboot.firstsecurityapp.controllers;


import com.maksudrustamov.springboot.firstsecurityapp.entity.Person;
import com.maksudrustamov.springboot.firstsecurityapp.util.PersonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/auth")
public class AuthController {
    PersonValidator personValidator;

    @Autowired
    public AuthController(PersonValidator personValidator) {
        this.personValidator = personValidator;
    }

    @GetMapping("/login")
    public String loginPage(){
        return "auth/login";
    }

    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("person")Person person){ // в модель положит пустого человека для фронта

        return "auth/registration";
    }

    @PostMapping("/registration")
    public String performRegistration(@ModelAttribute ("person") @Valid Person person
    , BindingResult bindingResult){ // мы здесь получаем нашу модель, также валидируем у нас есть правила в entity class
        personValidator.validate(person,bindingResult);

        return "";
    }

}
