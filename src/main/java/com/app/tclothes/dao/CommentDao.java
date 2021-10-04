package com.app.tclothes.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.tclothes.entity.Comment;

public interface CommentDao extends JpaRepository<Comment, Long>{
	
	@Query("SELECT c FROM Comment c WHERE c.deleteFlag=0 AND c.product.productId=?1 ORDER BY c.commentId DESC")
	List<Comment> getCommentByProductId(Integer productId);
	
	@Query("SELECT COUNT(c) FROM Comment c WHERE c.deleteFlag=0 AND c.product.productId=?1")
	long getCommentBySize(Integer productId);
}
