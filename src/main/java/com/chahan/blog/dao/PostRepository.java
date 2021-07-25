package com.chahan.blog.dao;

import com.chahan.blog.model.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    @Override
    Optional<Post> findById(Long id);

    List<Post> getByAuthorId(Long id);

    List<Post> getByAuthorIdInOrderByPublishedDesc(Set<Long> ids, Pageable pageable);
}
