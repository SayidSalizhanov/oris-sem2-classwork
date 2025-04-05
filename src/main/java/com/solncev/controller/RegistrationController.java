package com.solncev.controller;

import com.solncev.dto.RegistrationUserDto;
import com.solncev.service.RegistrationService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @GetMapping
    public String registerGet(@ModelAttribute("userDto") RegistrationUserDto userDto) {
        return "registration/registration.html";
    }

    @PostMapping
    public String registerPost(@ModelAttribute("userDto") RegistrationUserDto userDto) {
        registrationService.saveUser(userDto);
        return "redirect:index.html";
    }
}
