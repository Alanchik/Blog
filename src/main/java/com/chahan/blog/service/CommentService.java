package com.chahan.blog.service;

import com.chahan.blog.dao.BloggerRepository;
import com.chahan.blog.dao.CommentRepository;
import com.chahan.blog.dao.PostRepository;
import com.chahan.blog.dto.CreateCommentDto;
import com.chahan.blog.model.*;
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
        AbstractComment comment = commentRepository.getById(commentId);
        validator.validateCommentAccess(comment);
        Post post = postRepository.getById(postId);
        validator.validateCommentBelongToPost(post, commentId);
        comment.setText(request.getText());
        commentRepository.save(comment);
    }

    public void deleteComment(Long commentId) {
        AbstractComment comment = commentRepository.getById(commentId);
        validator.validateCommentAccess(comment);
        commentRepository.deleteById(commentId);
    }

    //**replies**

    public void createCommentReply(CreateCommentDto commentDto, Long postId, Long commentId) {
        BloggerDetails blogger = AuthUtils.getCurrentBlogger();
        CommentReply commentReply = new CommentReply();

        commentReply.setPost(postRepository.getById(postId));
        commentReply.setAuthor(bloggerRepository.getById(blogger.getId()));
        commentReply.setText(commentDto.getText());
        commentReply.setPublished(LocalDateTime.now());
        commentReply.setComment(commentRepository.getById(commentId));
        commentRepository.save(commentReply);
    }

    public void updateCommentReply(CreateCommentDto request, Long postId, Long commentId) {
        AbstractComment comment = commentRepository.getById(commentId);
        validator.validateCommentAccess(comment);
        Post post = postRepository.getById(postId);
        validator.validateCommentBelongToPost(post, commentId);
        comment.setText(request.getText());
        commentRepository.save(comment);
    }

    public void deleteCommentReply(Long replyId) {
        AbstractComment comment = commentRepository.getById(replyId);
        validator.validateCommentAccess(comment);
        commentRepository.deleteById(replyId);
    }
}
