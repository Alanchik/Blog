package com.chahan.blog.validator;

import com.chahan.blog.dao.BloggerRepository;
import com.chahan.blog.dao.CommentRepository;
import com.chahan.blog.dao.PostRepository;
import com.chahan.blog.exception.BadRequestApiException;
import com.chahan.blog.exception.ForbiddenApiException;
import com.chahan.blog.model.BloggerDetails;
import com.chahan.blog.model.AbstractComment;
import com.chahan.blog.model.Post;
import com.chahan.blog.util.AuthUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.chahan.blog.util.CommonUtils.*;

@Component
@RequiredArgsConstructor
public class Validator {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final BloggerRepository bloggerRepository;
    public void validateCommentAccess(AbstractComment comment) {
        BloggerDetails blogger = AuthUtils.getCurrentBlogger();
        if (!(blogger.getId()).equals(comment.getAuthor().getId())) {
            throw new ForbiddenApiException(ERROR_ACTION_FORBIDDEN);
        }
    }

    public void validateCommentBelongToPost(Post post, Long commentId) {
        boolean postContainsComment = post.getComments().stream()
                .anyMatch(comment -> comment.getId().equals(commentId));
        if (!postContainsComment) {
            throw new BadRequestApiException(ERROR_COMMENT_NOT_FOUND);
        }
    }

    public void validatePostAccess(Post post) {
        BloggerDetails blogger = AuthUtils.getCurrentBlogger();
        if (!blogger.getId().equals(post.getAuthor().getId())) {
            throw new BadRequestApiException(ERROR_POST_NOT_BELONG);
        }
    }

    public void validateCommentExists(Long id) {
        if (!commentRepository.existsById(id)) {
            throw new BadRequestApiException(ERROR_INCORRECT_ID);
        }
    }

    public void validatePostExists(Long id) {
        if (!postRepository.existsById(id)) {
            throw new BadRequestApiException(ERROR_INCORRECT_ID);
        }
    }

    public void validateBloggerExists(Long id) {
        if (!bloggerRepository.existsById(id)) {
            throw new BadRequestApiException(ERROR_INCORRECT_ID);
        }
    }
}


