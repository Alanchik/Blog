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

    @PutMapping("/{id}")
    public void updatePost(@Valid @RequestBody CreatePostDto request, @PathVariable Long id) {
        postService.updatePost(request, id);
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Long id) {
        postService.deletePost(id);
    }

    @GetMapping("/current")
    public List<Post> showYourselfPosts() {
        return postService.showBloggersPosts();
    }

    @GetMapping
    public List<PostDto> getSubscriptionsPosts(Pageable pageable) {
        return postService.getSubscriptionsPosts(pageable);
    }

}
