package com.chahan.blog.service;

import com.chahan.blog.model.entity.*;
import com.chahan.blog.repository.CommentRepository;
import com.chahan.blog.model.dto.CreateCommentDto;
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

    private final BloggerService bloggerService;
    private final PostService postService;
    private final CommentRepository commentRepository;
    private final Validator validator;

    public AbstractComment getById(Long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new BadRequestApiException(ERROR_INCORRECT_ID));
    }

    public void createComment(CreateCommentDto commentDto, Long postId) {
        validator.validatePostExists(postId);
        BloggerDetails blogger = AuthUtils.getCurrentBlogger();
        Comment comment = new Comment();
        comment.setPost(postService.getById(postId));
        comment.setAuthor(bloggerService.getBlogger(blogger.getId()));
        comment.setText(commentDto.getText());
        comment.setPublished(LocalDateTime.now());
        commentRepository.save(comment);
    }

    public void updateComment(CreateCommentDto request, Long commentId) {
        AbstractComment comment = getById(commentId);
        validator.validateCommentAccess(comment);
        comment.setText(request.getText());
        commentRepository.save(comment);
    }

    public void deleteComment(Long commentId) {
        AbstractComment comment = getById(commentId);
        validator.validateCommentAccess(comment);
        commentRepository.deleteById(commentId);
    }

    public void addLike(Long id) {
        BloggerDetails blogger = AuthUtils.getCurrentBlogger();
        Blogger currentBlogger = bloggerService.getBlogger(blogger.getId());
        AbstractComment comment = getById(id);
        comment.getBloggerLikes().add(currentBlogger);
        commentRepository.save(comment);
    }

    public void deleteLike(Long id) {
        BloggerDetails blogger = AuthUtils.getCurrentBlogger();
        Blogger currentBlogger = bloggerService.getBlogger(blogger.getId());
        AbstractComment comment = getById(id);
        comment.getBloggerLikes().remove(currentBlogger);
        commentRepository.save(comment);
    }

    public void createCommentReply(CreateCommentDto commentDto, Long commentId) {
        BloggerDetails blogger = AuthUtils.getCurrentBlogger();
        CommentReply commentReply = new CommentReply();
        AbstractComment comment = getById(commentId);
        commentReply.setPost(comment.getPost());
        commentReply.setAuthor(bloggerService.getBlogger(blogger.getId()));
        commentReply.setText(commentDto.getText());
        commentReply.setPublished(LocalDateTime.now());
        validator.validateReplyAccess(comment);
        commentReply.setComment((Comment) comment);
        commentRepository.save(commentReply);
    }
}
