package com.chahan.blog.service;

import com.chahan.blog.dao.BloggerRepository;
import com.chahan.blog.entity.Blogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.security.auth.login.LoginException;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;

@Service
public class BloggerService {
    @Autowired
    private BloggerRepository bloggerRepository;

    public List<Blogger> getAllBloggers() {
        return bloggerRepository.findAll();
    }

    public void saveBlogger(Blogger blogger)  {
            bloggerRepository.save(blogger);

    }

    public Blogger getBlogger(int id) {
        Blogger blogger = null;
        Optional<Blogger> optionalBlogger = bloggerRepository.findById(id);
        if (optionalBlogger.isPresent()) {
            blogger = optionalBlogger.get();
        }
        return blogger;
    }

    public void deleteBloger(int id) {
        bloggerRepository.deleteById(id);
    }
}
