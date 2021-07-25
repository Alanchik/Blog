package com.chahan.blog.dao;

import com.chahan.blog.model.AbstractComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<AbstractComment, Long> {
}
