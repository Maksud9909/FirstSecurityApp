package com.maksudrustamov.springboot.firstsecurityapp.controllers;


import com.maksudrustamov.springboot.firstsecurityapp.entity.Person;
import com.maksudrustamov.springboot.firstsecurityapp.service.PeopleService;
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
    private final PersonValidator personValidator; // проверка
    private final PeopleService peopleService;

    @Autowired
    public AuthController(PersonValidator personValidator, PeopleService peopleService) {
        this.personValidator = personValidator;
        this.peopleService = peopleService;
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
    public String performRegistration(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {
        personValidator.validate(person, bindingResult);

        if (bindingResult.hasErrors()) {
            // Если есть ошибки валидации, возвращаем обратно на страницу регистрации
            return "auth/registration";
        }

        // Если ошибок нет, регистрируем пользователя
        peopleService.register(person);

        // Перенаправляем на страницу входа
        return "redirect:/auth/login";
    }
}
