package com.example.posthub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.posthub.model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

}
