package com.chahan.blog.service;

import com.chahan.blog.dao.BloggerRepository;
import com.chahan.blog.model.Blogger;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.security.auth.login.LoginException;
import java.util.List;
import java.util.Optional;

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


    public List<Blogger> getAllBloggers() {
        return bloggerRepository.findAll();
    }

    public void saveBlogger(Blogger blogger) throws LoginException {
        bloggerRepository.save(blogger);

    }

    public Blogger getBlogger(long id) {
        Blogger blogger = null;
        Optional<Blogger> optionalBlogger = bloggerRepository.findById(id);
        if (optionalBlogger.isPresent()) {
            blogger = optionalBlogger.get();
        }
        return blogger;
    }

    public void deleteBloger(long id) {
        bloggerRepository.deleteById(id);
    }
}
