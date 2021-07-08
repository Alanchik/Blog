package com.chahan.blog.service;

import com.chahan.blog.dao.PostRepository;
import com.chahan.blog.dto.PostDto;
import com.chahan.blog.model.BloggerDetails;
import com.chahan.blog.model.Post;
import com.chahan.blog.util.AuthUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public void createPost(PostDto postDto) {
        BloggerDetails blogger = AuthUtils.getCurrentBlogger();
        Post post = new Post();
        post.setAuthorID(blogger.getId());
        post.setDescription(postDto.getDescription());
        post.setPublished(LocalDateTime.now());
        postRepository.save(post);
    }

    public List<Post> showBloggersPosts() {
        BloggerDetails blogger = AuthUtils.getCurrentBlogger();
        return postRepository.getByAuthorID(blogger.getId());
    }
}
