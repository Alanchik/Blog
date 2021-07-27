package com.chahan.blog.service;

import com.chahan.blog.dao.BloggerRepository;
import com.chahan.blog.dao.CommentRepository;
import com.chahan.blog.dao.PostRepository;
import com.chahan.blog.dto.CreateCommentDto;
import com.chahan.blog.model.*;
import com.chahan.blog.exception.BadRequestApiException;
import com.chahan.blog.util.AuthUtils;
import com.chahan.blog.validator.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.chahan.blog.util.CommonUtils.ERROR_INCORRECT_ID;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final BloggerRepository bloggerRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final Validator validator;

    public Comment getById(Long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new BadRequestApiException(ERROR_INCORRECT_ID));
    }

    public void createComment(CreateCommentDto commentDto, Long postId) {
        validator.validatePostExists(postId);
        BloggerDetails blogger = AuthUtils.getCurrentBlogger();
        Comment comment = new Comment();
        comment.setPost(postRepository.getById(postId));
        comment.setAuthor(bloggerRepository.getById(blogger.getId()));
        comment.setText(commentDto.getText());
        comment.setPublished(LocalDateTime.now());
        commentRepository.save(comment);
    }

    public void updateComment(CreateCommentDto request, Long commentId) {
        AbstractComment comment = commentRepository.getById(commentId);
        validator.validateCommentAccess(comment);
        comment.setText(request.getText());
        commentRepository.save(comment);
    }

    public void deleteComment(Long commentId) {
        AbstractComment comment = commentRepository.getById(commentId);
        validator.validateCommentAccess(comment);
        commentRepository.deleteById(commentId);
    }

    public void createCommentReply(CreateCommentDto commentDto, Long commentId) {
        BloggerDetails blogger = AuthUtils.getCurrentBlogger();
        CommentReply commentReply = new CommentReply();
        AbstractComment comment = commentRepository.getById(commentId);
        commentReply.setPost(comment.getPost());
        commentReply.setAuthor(bloggerRepository.getById(blogger.getId()));
        commentReply.setText(commentDto.getText());
        commentReply.setPublished(LocalDateTime.now());
        commentReply.setComment(comment);
        commentRepository.save(commentReply);
    }
}
