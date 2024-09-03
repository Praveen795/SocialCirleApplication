package com.socialCircle.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.socialCircle.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
