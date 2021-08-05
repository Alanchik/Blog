package com.chahan.blog.repository;

import com.chahan.blog.model.entity.AbstractComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<AbstractComment, Long> {

    boolean existsById(Long id);

    Optional<AbstractComment> findById(Long id);
}
