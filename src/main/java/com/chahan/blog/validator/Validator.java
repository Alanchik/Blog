package com.chahan.blog.validator;

import com.chahan.blog.exception.BadRequestApiException;
import com.chahan.blog.exception.ForbiddenApiException;
import com.chahan.blog.model.BloggerDetails;
import com.chahan.blog.model.Comment;
import com.chahan.blog.model.Post;
import com.chahan.blog.util.AuthUtils;
import org.springframework.stereotype.Component;

@Component
public class Validator {

    public void validateCommentAccess(Comment comment) {
        BloggerDetails blogger = AuthUtils.getCurrentBlogger();
        if (!(blogger.getId()).equals(comment.getAuthor().getId())) {
            throw new ForbiddenApiException("Action with comment forbidden");
        }
    }

    public void validateCommentBelongToPost(Post post, Long commentId) {
        boolean postContainsComment = post.getComments().stream()
                .anyMatch(comment -> comment.getId().equals(commentId));
        if (!postContainsComment) {
            throw new BadRequestApiException("Post doesn't contains comment");
        }
    }

    public void validatePostAccess(Post post) {
        BloggerDetails blogger = AuthUtils.getCurrentBlogger();
        if (!blogger.getId().equals(post.getAuthor().getId())) {
            throw new BadRequestApiException("Post doesn't belong to current user");
        }
    }
}
