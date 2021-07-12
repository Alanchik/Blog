package com.chahan.blog.controller;

import com.chahan.blog.service.BloggerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List<Long> getListOfSubscription() {
        return followService.getListOfSubscription();
    }

    @GetMapping("/subscribers")
    public List<Long> getListOfSubscribers() {
        return followService.getListOfSubscribers();
    }
}
