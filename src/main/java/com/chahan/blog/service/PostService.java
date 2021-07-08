package com.chahan.blog.service;

import com.chahan.blog.dao.PostRepository;
import com.chahan.blog.dto.PostDto;
import com.chahan.blog.model.BloggerDetails;
import com.chahan.blog.model.Post;
import com.chahan.blog.util.AuthUtils;
import lombok.RequiredArgsConstructor;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public void createPost(PostDto postDto) {
        BloggerDetails blogger = (BloggerDetails) AuthUtils.getAuth().getPrincipal();
        Post post = new Post();
        post.setAuthorID(blogger.getId());
        post.setDescription(postDto.getText());
        post.setPublished(LocalDateTime.now());
        postRepository.save(post);
    }

    public List<Post> showBloggersPosts() {
        BloggerDetails blogger = (BloggerDetails) AuthUtils.getAuth().getPrincipal();
        return postRepository.getByAuthorID(blogger.getId());
    }
}
