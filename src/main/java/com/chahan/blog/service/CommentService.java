package com.chahan.blog.service;

import com.chahan.blog.dao.BloggerRepository;
import com.chahan.blog.dao.CommentRepository;
import com.chahan.blog.dao.PostRepository;
import com.chahan.blog.dto.CreateCommentDto;
import com.chahan.blog.model.Blogger;
import com.chahan.blog.model.BloggerDetails;
import com.chahan.blog.model.Comment;
import com.chahan.blog.model.Post;
import com.chahan.blog.util.AuthUtils;
import com.chahan.blog.validator.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final BloggerRepository bloggerRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final Validator validator;

    public void createComment(CreateCommentDto commentDto, Long postId) {
        BloggerDetails blogger = AuthUtils.getCurrentBlogger();
        Comment comment = new Comment();
        comment.setPost(postRepository.getById(postId));
        comment.setAuthor(bloggerRepository.getById(blogger.getId()));
        comment.setText(commentDto.getText());
        comment.setPublished(LocalDateTime.now());
        commentRepository.save(comment);
    }

    public void updateComment(CreateCommentDto request, Long postId, Long commentId) {
        Comment comment = commentRepository.getById(commentId);
        validator.validateCommentAccess(comment);
        Post post = postRepository.getById(postId);
        validator.validateCommentBelongToPost(post, commentId);
        comment.setText(request.getText());
        commentRepository.save(comment);
    }

    public void deleteComment(Long commentId) {
        Comment comment = commentRepository.getById(commentId);
        validator.validateCommentAccess(comment);
        commentRepository.deleteById(commentId);
    }

    public void addLike(Long id) {
        BloggerDetails blogger = AuthUtils.getCurrentBlogger();
        Blogger currentBlogger = bloggerRepository.getById(blogger.getId());
        currentBlogger.getCommentLikes().add(commentRepository.getById(id));
        bloggerRepository.save(currentBlogger);
    }

    public void deleteLike(Long id) {
        BloggerDetails blogger = AuthUtils.getCurrentBlogger();
        Blogger currentBlogger = bloggerRepository.getById(blogger.getId());
        currentBlogger.getCommentLikes().removeIf(like -> like.getId().equals(id));
        bloggerRepository.save(currentBlogger);
    }
}
