package com.chahan.blog.service;

import com.chahan.blog.dao.BloggerRepository;
import com.chahan.blog.dao.CommentRepository;
import com.chahan.blog.dao.PostRepository;
import com.chahan.blog.dto.CreateCommentDto;
import com.chahan.blog.exception.BadRequestApiException;
import com.chahan.blog.model.BloggerDetails;
import com.chahan.blog.model.Comment;
import com.chahan.blog.model.Post;
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

    public void updateComment(CreateCommentDto request, Long postId, Long commentId) {
        Comment comment = getById(commentId);
        validator.validateCommentAccess(comment);
        Post post = postRepository.getById(postId);
        validator.validateCommentBelongToPost(post, commentId);
        comment.setText(request.getText());
        commentRepository.save(comment);
    }

    public void deleteComment(Long commentId) {
        Comment comment = getById(commentId);
        validator.validateCommentAccess(comment);
        commentRepository.deleteById(commentId);
    }
}
