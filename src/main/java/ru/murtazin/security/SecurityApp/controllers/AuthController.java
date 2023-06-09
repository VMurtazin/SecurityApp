package ru.murtazin.security.SecurityApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.murtazin.security.SecurityApp.models.Person;
import ru.murtazin.security.SecurityApp.services.RegistrationService;
import ru.murtazin.security.SecurityApp.util.PersonValidator;

import javax.validation.Valid;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final RegistrationService registrationService;
    private final PersonValidator validator;

    @Autowired
    public AuthController(RegistrationService registrationService, PersonValidator validator) {
        this.registrationService = registrationService;
        this.validator = validator;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";
    }
    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("person") Person person){
        return "auth/registration";
    }
    @PostMapping("/registration")
    public String performRegistration(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult){
        validator.validate(person, bindingResult);
        if (bindingResult.hasErrors())
            return "auth/registration";

        registrationService.register(person);
        return "redirect:/auth/login";
    }
}
