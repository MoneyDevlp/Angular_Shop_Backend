package com.app.tclothes.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.tclothes.dao.CommentDao;
import com.app.tclothes.dao.CustommerDao;
import com.app.tclothes.dao.ProductDao;
import com.app.tclothes.entity.Comment;
import com.app.tclothes.request.CommentRequest;
import com.app.tclothes.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService{
	
	@Autowired
	CommentDao commentDao;
	
	@Autowired
	CustommerDao custommerDao;
	
	@Autowired
	ProductDao productDao;
	
	@Override
	public CommentRequest save(CommentRequest commentRequest) {
		Optional<Comment> optional = commentDao.findById(commentRequest.getCommentId());
		Comment comment = new Comment();
		if(optional.isPresent()) {
			comment = optional.get();
		}
		convert(comment, commentRequest);
		commentDao.save(comment);
		return commentRequest;
	}
	
	private void convert(Comment comment, CommentRequest commentRequest) {
		comment.setCommentId(commentRequest.getCommentId());
		comment.setContent(commentRequest.getContent());
		comment.setStar(commentRequest.getStar());
		comment.setDeleteFlag(0);
		comment.setProduct(productDao.getById(commentRequest.getProductId()));
		comment.setCustommer(custommerDao.findUsername(commentRequest.getCustommer().getUsername()));
	}

	@Override
	public List<Comment> getCommentByProductId(Integer productId) {
		return commentDao.getCommentByProductId(productId);
	}

	@Override
	public long getCommentBySize(Integer productId) {
		return commentDao.getCommentBySize(productId);
	}

	
	
	
}
