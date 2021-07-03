package com.chahan.blog.service;

import com.chahan.blog.dao.BloggerRepository;
import com.chahan.blog.model.Blogger;
import com.chahan.blog.model.BloggerDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {

    private final BloggerRepository bloggerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Blogger blogger = bloggerRepository.getByUsername(username);
        if (blogger == null){
            throw new UsernameNotFoundException("User not found");
        }

    return new BloggerDetails (blogger.getUsername(),blogger.getPassword());
    }
}
