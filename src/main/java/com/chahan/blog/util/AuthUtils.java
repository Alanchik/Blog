package com.chahan.blog.util;

import com.chahan.blog.model.BloggerDetails;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthUtils {
    public static BloggerDetails getCurrentBlogger() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        return (BloggerDetails) securityContext.getAuthentication().getPrincipal();
    }
}
