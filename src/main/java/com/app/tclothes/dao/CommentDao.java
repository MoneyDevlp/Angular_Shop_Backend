package com.app.tclothes.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.tclothes.entity.Comment;

public interface CommentDao extends JpaRepository<Comment, Long>{

}
