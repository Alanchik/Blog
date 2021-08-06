package com.chahan.blog.service;

import com.chahan.blog.model.entity.Blogger;
import com.chahan.blog.model.entity.BloggerDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import static com.chahan.blog.util.CommonUtils.ERROR_USER_NOT_FOUND;

@Component
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {

    private final BloggerService bloggerService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Blogger blogger = bloggerService.getBlogger(username);
        if (blogger == null) {
            throw new UsernameNotFoundException(ERROR_USER_NOT_FOUND);
        }

        return new BloggerDetails(blogger.getId(), blogger.getUsername(), blogger.getPassword());
    }
}
