package com.chahan.blog.service;

import com.chahan.blog.repo.BloggerRepository;
import com.chahan.blog.model.Blogger;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BloggerService {

    private final BloggerRepository bloggerRepository;

    public Blogger getBlogger(String username) {
        return bloggerRepository.getByUsername(username);
    }

    public void saveNewBlogger(String username, String password) {
        Blogger blogger = new Blogger();
        blogger.setUsername(username);
        blogger.setPassword(password); //password should be encoded
        bloggerRepository.save(blogger);
    }
}
