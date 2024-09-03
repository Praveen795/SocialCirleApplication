package com.socialCircle.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.socialCircle.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

}
