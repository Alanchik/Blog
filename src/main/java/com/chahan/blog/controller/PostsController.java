package com.chahan.blog.controller;

import com.chahan.blog.dto.CreatePostDto;
import com.chahan.blog.dto.PostDto;
import com.chahan.blog.model.Post;
import com.chahan.blog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostsController {
    private final PostService postService;

    @PostMapping
    public void createPost(@Valid @RequestBody CreatePostDto request) {
        postService.createPost(request);
    }

    @GetMapping("/current")
    public List<Post> getYourselfPosts() {
        return postService.getBloggersPosts();
    }

    @GetMapping
    public List<PostDto> getSubscriptionsPosts(Pageable pageable) {
        return postService.getSubscriptionsPosts(pageable);
    }
}
