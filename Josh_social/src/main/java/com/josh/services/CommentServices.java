package com.josh.services;

import com.josh.model.Comment;

public interface CommentServices {
	
	public Comment createComment(Comment comment, Integer postId, Integer userId) throws Exception;
	
	public Comment findCommentById(Integer commentId) throws Exception;
	
	public Comment likeComment(Integer commentId, Integer userId) throws Exception;
	

}
