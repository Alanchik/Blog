package com.chahan.blog.dao;

import com.chahan.blog.model.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface PostRepository extends PagingAndSortingRepository<Post, Long> {
    List<Post> getByAuthorId(Long id);
    List<Post> getByAuthorIdInOrderByPublishedDesc(Set<Long> ids, Pageable pageable);
}
