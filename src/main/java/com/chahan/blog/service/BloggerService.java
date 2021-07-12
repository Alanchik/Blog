package com.chahan.blog.service;

import com.chahan.blog.dao.BloggerRepository;
import com.chahan.blog.model.Blogger;
import com.chahan.blog.model.BloggerDetails;
import com.chahan.blog.util.AuthUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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

    public void follow(Long subscriptionBloggerId) {
        BloggerDetails blogger = AuthUtils.getCurrentBlogger();
        Blogger currentBlogger = bloggerRepository.getById(blogger.getId());
        Blogger subscriptionBlogger = bloggerRepository.getById(subscriptionBloggerId);
        currentBlogger.getSubscriptions().add(subscriptionBlogger);
        bloggerRepository.save(currentBlogger);
    }

    public void unfollow(Long id) {
        BloggerDetails blogger = AuthUtils.getCurrentBlogger();
        Blogger currentBlogger = bloggerRepository.getById(blogger.getId());
        currentBlogger.getSubscriptions().removeIf(subscription -> subscription.getId().equals(id));
        bloggerRepository.save(currentBlogger);
    }

    public List<Long> getListOfSubscription() {
        BloggerDetails blogger = AuthUtils.getCurrentBlogger();
        Blogger currentBlogger = bloggerRepository.getById(blogger.getId());
        Iterator<Blogger> iterator = currentBlogger.getSubscriptions().iterator();
        List<Long> listId = new ArrayList<>();
        while (iterator.hasNext()) {
            listId.add(iterator.next().getId());
        }
        return listId;
    }


    public List<Long> getListOfSubscribers() {
        BloggerDetails blogger = AuthUtils.getCurrentBlogger();
        Blogger currentBlogger = bloggerRepository.getById(blogger.getId());
        System.out.println(currentBlogger.getSubscribers());
        Iterator<Blogger> iterator = currentBlogger.getSubscribers().iterator();
        List<Long> listId = new ArrayList<>();
        while (iterator.hasNext()) {
            listId.add(iterator.next().getId());
        }
        return listId;
    }

}
