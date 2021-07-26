package com.chahan.blog.dao;

import com.chahan.blog.model.Blogger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BloggerRepository extends JpaRepository<Blogger, Long> {

    boolean existsById(Long id);

    Optional<Blogger> findByUsername(String username);

    Optional<Blogger> findById(Long id);

    Blogger getByUsername(String username);
}
