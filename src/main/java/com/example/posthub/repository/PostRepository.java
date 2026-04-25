package com.example.posthub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.posthub.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    Post findById(long id);
}
