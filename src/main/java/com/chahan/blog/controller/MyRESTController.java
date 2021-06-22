package com.chahan.blog.controller;

import com.chahan.blog.entity.Blogger;
import com.chahan.blog.service.BloggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.LoginException;
import java.util.List;

@RestController
public class MyRESTController {
    @Autowired
    private BloggerService bloggersService;

    @GetMapping("/bloggers")
    public List<Blogger> showAllBloggers() {
        List<Blogger> allBloggers = bloggersService.getAllBloggers();
        return allBloggers;
    }

    @GetMapping("/bloggers/{id}")
    public Blogger getBlogger(@PathVariable int id) {
        Blogger blogger = bloggersService.getBlogger(id);
        return blogger;
    }

    @PostMapping("/bloggers")
    public Blogger addNewBlogger(@RequestBody Blogger blogger) {
        bloggersService.saveBlogger(blogger);
        return blogger;
    }

    @PutMapping("/bloggers")
    public Blogger updateBlogger(@RequestBody Blogger blogger)  {
        bloggersService.saveBlogger(blogger);
        return blogger;
    }

    @DeleteMapping("/bloggers/{id}")
    public String deleteBlogger(@PathVariable int id) {
        bloggersService.deleteBloger(id);
        return "Blogger with ID = " + id + " was deleted";
    }
}
