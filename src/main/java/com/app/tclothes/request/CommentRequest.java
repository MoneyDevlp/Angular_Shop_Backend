package com.app.tclothes.request;

import java.time.LocalDateTime;

import com.app.tclothes.entity.Comment;
import com.app.tclothes.entity.Custommer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentRequest {
	
	Long commentId;
	String content;
	LocalDateTime dateCreated;
	LocalDateTime lastUpdated;
	int deleteFlag;
	Integer productId;
	Custommer custommer;
	
	public CommentRequest(Comment comment) {
		this.commentId = comment.getCommentId();
		this.content = comment.getContent();
		this.dateCreated = comment.getDateCreated();
		this.lastUpdated = comment.getLastUpdated();
		this.deleteFlag = comment.getDeleteFlag();
		this.productId = comment.getProduct().getProductId();
		this.custommer = comment.getCustommer();
	}
}
