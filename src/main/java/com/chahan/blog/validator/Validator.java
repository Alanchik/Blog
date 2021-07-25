package com.chahan.blog.validator;

import com.chahan.blog.exception.BadRequestApiException;
import com.chahan.blog.exception.ForbiddenApiException;
import com.chahan.blog.model.BloggerDetails;
import com.chahan.blog.model.Comment;
import com.chahan.blog.model.Post;
import com.chahan.blog.util.AuthUtils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static com.chahan.blog.util.CommonUtils.*;

@Component
public class Validator {

    public void validateCommentAccess(Comment comment) {
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

    public void validateObjectIsNull(JpaRepository repository, Long id) {
        Optional<Object> object = repository.findById(id);
        if (object.isEmpty()) {
            throw new BadRequestApiException(ERROR_INCORRECT_ID);
        }
    }
}


