package com.chahan.blog.dao;

import com.chahan.blog.model.AbstractComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<AbstractComment, Long> {

    boolean existsById(Long id);

    Optional<Comment> findById(Long id);
}
