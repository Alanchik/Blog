package com.chahan.blog.controller;

import com.chahan.blog.service.BloggerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bloggers")
public class BloggerController {
    private final BloggerService followService;

    @PostMapping("/{id}/follow")
    public void follow(@PathVariable Long id) {
        followService.follow(id);
    }

    @DeleteMapping("/{id}/unfollow")
    public void unfollow(@PathVariable Long id) {
        followService.unfollow(id);
    }

    @GetMapping("/subscriptions")
    public Set<Long> getSubscription() {
        return followService.getListOfSubscription();
    }

    @GetMapping("/subscribers")
    public Set<Long> getSubscribers() {
        return followService.getListOfSubscribers();
    }
}
