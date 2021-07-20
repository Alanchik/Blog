package com.chahan.blog.service;

import com.chahan.blog.dao.PostRepository;
import com.chahan.blog.dto.CreatePostDto;
import com.chahan.blog.dto.PostDto;
import com.chahan.blog.mapper.PostMapper;
import com.chahan.blog.model.BloggerDetails;
import com.chahan.blog.model.Post;
import com.chahan.blog.util.AuthUtils;
import com.chahan.blog.validator.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class PostService {

    private final BloggerService bloggerService;
    private final PostRepository postRepository;
    private final PostMapper postMapper;
    private final Validator validator;

    public void createPost(CreatePostDto postDto) {
        BloggerDetails blogger = AuthUtils.getCurrentBlogger();
        Post post = new Post();
        post.setAuthor(bloggerService.getBlogger(blogger.getId()));
        post.setDescription(postDto.getDescription());
        post.setPublished(LocalDateTime.now());
        postRepository.save(post);
    }

    public void updatePost(CreatePostDto request, Long postId) {
        Post post = postRepository.getById(postId);
        validator.validatePostAccess(post);
        post.setDescription(request.getDescription());
        postRepository.save(post);
    }

    public void deletePost(Long postId) {
        Post post = postRepository.getById(postId);
        validator.validatePostAccess(post);
        postRepository.deleteById(postId);
    }


    public List<PostDto> getBloggersPosts() {
        BloggerDetails blogger = AuthUtils.getCurrentBlogger();
        return postMapper.map(postRepository.getByAuthorId(blogger.getId()));
    }

    public List<PostDto> getSubscriptionsPosts(Pageable pageable) {
        Set<Long> subscriptions = bloggerService.getCurrentSubscriptions();
        List<Post> posts = postRepository.getByAuthorIdInOrderByPublishedDesc(subscriptions, pageable);
        return postMapper.map(posts);
    }
}
