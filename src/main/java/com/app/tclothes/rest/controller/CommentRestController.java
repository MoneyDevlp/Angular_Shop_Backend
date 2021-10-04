package com.app.tclothes.rest.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.tclothes.entity.Comment;
import com.app.tclothes.request.CommentRequest;
import com.app.tclothes.service.CommentService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/")
public class CommentRestController {
	
	@Autowired
	CommentService commentService;
	
	@PostMapping("comment")
	public ResponseEntity<CommentRequest> addComment(@RequestBody CommentRequest commentRequest) {
		commentRequest.setCommentId(0L);
		return new ResponseEntity<>(commentService.save(commentRequest),HttpStatus.CREATED);
	}
	
	@GetMapping("comments")
	public ResponseEntity<List<Comment>> getCommentByProductId(@RequestParam("productId") Integer productId) {
		return ResponseEntity.ok(commentService.getCommentByProductId(productId));
	}
	
	@GetMapping("commentSize")
	public long orderSize(@RequestParam("productId") Integer productId) {
		return commentService.getCommentBySize(productId);
	}
}
