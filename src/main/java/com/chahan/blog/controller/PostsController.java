package com.chahan.blog.controller;

import com.chahan.blog.dto.PostDto;
import com.chahan.blog.model.Post;
import com.chahan.blog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostsController {
    private final PostService postService;

    @PostMapping
    public void createPost(@Valid @RequestBody PostDto request) {
        postService.createPost(request);
    }

    @GetMapping
    public List<Post> showBloggersPosts() {
        List<Post> allPosts = postService.showBloggersPosts();
        return allPosts;
    }
}
