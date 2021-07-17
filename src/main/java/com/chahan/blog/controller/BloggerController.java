package com.chahan.blog.controller;

import com.chahan.blog.service.BloggerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bloggers")
public class BloggerController {
    private final BloggerService bloggerService;

    @PostMapping("/{id}/follow")
    public void follow(@PathVariable Long id) {
        bloggerService.follow(id);
    }

    @DeleteMapping("/{id}/unfollow")
    public void unfollow(@PathVariable Long id) {
        bloggerService.unfollow(id);
    }

    @GetMapping("/subscriptions")
    public Set<Long> getSubscription() {
        return bloggerService.getCurrentSubscriptions();
    }

    @GetMapping("/subscribers")
    public Set<Long> getSubscribers() {
        return bloggerService.getListOfSubscribers();
    }
}
