package com.chahan.blog.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthUtils {
    public static Authentication getAuth() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        return securityContext.getAuthentication();
    }
}
