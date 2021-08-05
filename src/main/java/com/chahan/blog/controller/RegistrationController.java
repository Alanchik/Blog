package com.chahan.blog.controller;

import com.chahan.blog.model.dto.RegistrationDto;
import com.chahan.blog.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/registration")
public class RegistrationController {

    private final RegistrationService registrationService;

    @PostMapping("/signUp")
    public void signUp(@Valid @RequestBody RegistrationDto request) {
        registrationService.signUp(request);
    }
}
