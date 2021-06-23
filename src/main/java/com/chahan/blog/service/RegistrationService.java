package com.chahan.blog.service;

import com.chahan.blog.dto.RegistrationDto;
import com.chahan.blog.exception.BadRequestApiException;
import com.chahan.blog.model.Blogger;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.chahan.blog.util.Constants.USER_ALREADY_EXISTS;

@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final BloggerService bloggerService;

    public void signUp(RegistrationDto request) {
        Blogger blogger = bloggerService.getBlogger(request.getUsername());
        if (blogger != null) {
            throw new BadRequestApiException(USER_ALREADY_EXISTS);
        }
        bloggerService.saveNewBlogger(request.getUsername(), request.getPassword());
    }
}
