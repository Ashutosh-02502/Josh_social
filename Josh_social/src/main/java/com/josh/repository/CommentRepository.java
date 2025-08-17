package com.josh.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.josh.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

}
