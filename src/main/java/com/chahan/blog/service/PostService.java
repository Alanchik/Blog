package com.chahan.blog.service;

import com.chahan.blog.dao.PostRepository;
import com.chahan.blog.dto.CreatePostDto;
import com.chahan.blog.dto.PostDto;
import com.chahan.blog.mapper.PostMapper;
import com.chahan.blog.model.BloggerDetails;
import com.chahan.blog.model.Post;
import com.chahan.blog.util.AuthUtils;
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

    public void createPost(CreatePostDto postDto) {
        BloggerDetails blogger = AuthUtils.getCurrentBlogger();
        Post post = new Post();
        post.setAuthor(bloggerService.getBlogger(blogger.getUsername()));
        post.setDescription(postDto.getDescription());
        post.setPublished(LocalDateTime.now());
        postRepository.save(post);
    }

    public List<Post> showBloggersPosts() {
        BloggerDetails blogger = AuthUtils.getCurrentBlogger();
        return postRepository.getByAuthorId(blogger.getId());
    }

    public List<PostDto> getSubscriptionsPosts(Pageable pageable) {
        Set<Long> subscriptions = bloggerService.getCurrentSubscriptions();
        List<Post> posts = postRepository.getByAuthorIdInOrderByPublishedDesc(subscriptions, pageable);
        return postMapper.map(posts);
    }
}
