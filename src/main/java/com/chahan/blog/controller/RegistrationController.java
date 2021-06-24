package com.chahan.blog.controller;

import com.chahan.blog.dto.RegistrationDto;
import com.chahan.blog.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.ValidationException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/registration")
public class RegistrationController {

    private final RegistrationService registrationService;

    @PostMapping("/signUp")
    public void signUp(@Valid @RequestBody RegistrationDto request
            , BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException();
        } else
            registrationService.signUp(request);
    }
}
