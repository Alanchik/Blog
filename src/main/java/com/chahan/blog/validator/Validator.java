package com.chahan.blog.validator;

import com.chahan.blog.exception.BadRequestApiException;
import com.chahan.blog.exception.ForbiddenApiException;
import com.chahan.blog.model.entity.AbstractComment;
import com.chahan.blog.model.entity.BloggerDetails;
import com.chahan.blog.model.entity.Comment;
import com.chahan.blog.model.entity.Post;
import com.chahan.blog.repository.BloggerRepository;
import com.chahan.blog.repository.PostRepository;
import com.chahan.blog.util.AuthUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.chahan.blog.util.CommonUtils.*;

@Component
@RequiredArgsConstructor
public class Validator {

    private final PostRepository postRepository;
    private final BloggerRepository bloggerRepository;

    public void validateCommentAccess(AbstractComment comment) {
        BloggerDetails blogger = AuthUtils.getCurrentBlogger();
        if (!(blogger.getId()).equals(comment.getAuthor().getId())) {
            throw new ForbiddenApiException(ERROR_ACTION_FORBIDDEN);
        }
    }

    public void validatePostAccess(Post post) {
        BloggerDetails blogger = AuthUtils.getCurrentBlogger();
        if (!blogger.getId().equals(post.getAuthor().getId())) {
            throw new BadRequestApiException(ERROR_POST_NOT_BELONG);
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

    public void validateReplyAccess(AbstractComment comment) {
        if (!(comment instanceof Comment)) {
            throw new BadRequestApiException(ERROR_REPLY_TO_REPLY_FORBIDDEN);
        }
    }
}


