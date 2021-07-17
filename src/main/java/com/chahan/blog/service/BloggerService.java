package com.chahan.blog.service;

import com.chahan.blog.dao.BloggerRepository;
import com.chahan.blog.exception.BadRequestApiException;
import com.chahan.blog.model.Blogger;
import com.chahan.blog.model.BloggerDetails;
import com.chahan.blog.util.AuthUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

import static com.chahan.blog.util.CommonUtils.ERROR_USER_FOLLOW_YOURSELF;
import static java.util.stream.Collectors.toSet;

@Service
@RequiredArgsConstructor
public class BloggerService {

    private final BloggerRepository bloggerRepository;

    public void saveNewBlogger(String username, String password) {
        Blogger blogger = new Blogger();
        blogger.setUsername(username);
        blogger.setPassword(password);
        bloggerRepository.save(blogger);
    }

    public Blogger getBlogger(String username) {
        return bloggerRepository.getByUsername(username);
    }
    public Blogger getBlogger (Long id){
        return bloggerRepository.getById(id);
    }

    public void follow(Long subscriptionBloggerId) {
        BloggerDetails blogger = AuthUtils.getCurrentBlogger();
        if (subscriptionBloggerId.equals(blogger.getId())) {
            throw new BadRequestApiException(ERROR_USER_FOLLOW_YOURSELF);
        }
        Blogger currentBlogger = bloggerRepository.getById(blogger.getId());
        Blogger subscribeBlogger = bloggerRepository.getById(subscriptionBloggerId);
        currentBlogger.getSubscriptions().add(subscribeBlogger);
        bloggerRepository.save(currentBlogger);

    }

    public void unfollow(Long id) {
        BloggerDetails blogger = AuthUtils.getCurrentBlogger();
        Blogger currentBlogger = bloggerRepository.getById(blogger.getId());
        currentBlogger.getSubscriptions().removeIf(subscription -> subscription.getId().equals(id));
        bloggerRepository.save(currentBlogger);
    }

    public Set<Long> getCurrentSubscriptions() {
        BloggerDetails blogger = AuthUtils.getCurrentBlogger();
        return getSubscriptions(blogger.getId());
    }

    public Set<Long> getSubscriptions(Long bloggerId) {
        Blogger currentBlogger = bloggerRepository.getById(bloggerId);
        return currentBlogger.getSubscriptions().stream()
                .map(Blogger::getId)
                .collect(toSet());
    }


    public Set<Long> getCurrentSubscribers() {
        BloggerDetails blogger = AuthUtils.getCurrentBlogger();
        Blogger currentBlogger = bloggerRepository.getById(blogger.getId());
        return currentBlogger.getSubscribers().stream()
                .map(Blogger::getId)
                .collect(Collectors.toSet());
    }

}
