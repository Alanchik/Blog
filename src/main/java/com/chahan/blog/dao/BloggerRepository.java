package com.chahan.blog.dao;

import com.chahan.blog.model.Blogger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface BloggerRepository extends JpaRepository<Blogger, Long> {
    Blogger getByUsername(String username);

}
