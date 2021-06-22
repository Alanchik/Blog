package com.chahan.blog.dao;

import com.chahan.blog.entity.Blogger;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BloggerRepository extends JpaRepository<Blogger,Integer> {
}
