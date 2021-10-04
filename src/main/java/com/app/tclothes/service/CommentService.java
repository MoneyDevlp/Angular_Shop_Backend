package com.app.tclothes.service;

import java.util.List;

import com.app.tclothes.entity.Comment;
import com.app.tclothes.request.CommentRequest;

public interface CommentService {

	CommentRequest save(CommentRequest commentRequest);

	List<Comment> getCommentByProductId(Integer productId);

	long getCommentBySize(Integer productId);


	

}
