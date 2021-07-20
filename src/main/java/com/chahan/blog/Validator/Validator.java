package com.chahan.blog.Validator;

import com.chahan.blog.exception.BadRequestApiException;
import com.chahan.blog.model.BloggerDetails;
import com.chahan.blog.model.Post;
import com.chahan.blog.util.AuthUtils;
import org.springframework.stereotype.Component;

@Component
public class Validator {
    public void validatePostAccess(Post post) {
        BloggerDetails blogger = AuthUtils.getCurrentBlogger();
        if (!blogger.getId().equals(post.getAuthor().getId())) {
            throw new BadRequestApiException("Post doesn't belong to current user");
        }
    }
}
