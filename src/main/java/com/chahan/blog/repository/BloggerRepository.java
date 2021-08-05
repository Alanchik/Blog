package com.chahan.blog.repository;

import com.chahan.blog.model.entity.Blogger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BloggerRepository extends JpaRepository<Blogger, Long> {

    boolean existsById(Long id);

    Optional<Blogger> findById(Long id);

    Blogger getByUsername(String username);
}
