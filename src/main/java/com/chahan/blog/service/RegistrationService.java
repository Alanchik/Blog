package com.chahan.blog.service;

import com.chahan.blog.dto.RegistrationDto;
import com.chahan.blog.exception.BadRequestApiException;
import com.chahan.blog.model.Blogger;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.chahan.blog.util.Constants.USER_ALREADY_EXISTS;

@Service
@RequiredArgsConstructor
public class RegistrationService {

    private  final PasswordEncoder passwordEncoder;
    private final BloggerService bloggerService;

    public void signUp(RegistrationDto registrationDto) {
        Blogger blogger = bloggerService.getBlogger(registrationDto.getUsername());
        if (blogger != null) {
            throw new BadRequestApiException(USER_ALREADY_EXISTS);
        }
        bloggerService.saveNewBlogger(registrationDto.getUsername()
                , passwordEncoder.encode(registrationDto.getPassword()));
    }
}
