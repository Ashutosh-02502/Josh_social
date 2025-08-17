package com.josh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.josh.model.Comment;
import com.josh.model.User;
import com.josh.services.CommentServices;
import com.josh.services.UserServices;

@RestController
public class CommentController {
	
	@Autowired
	private CommentServices commentServices;
	
	@Autowired
	private UserServices userServices;
	
	
	@PostMapping("/api/createComment/post/{postId}")
	public Comment createComment(@RequestBody Comment comment,@PathVariable Integer postId, @RequestHeader("Authorization") String jwt) throws Exception {
		
		User user = userServices.findUserByJwt(jwt);
		
		Comment createdComment = commentServices.createComment(comment, postId, user.getId());
				
		return createdComment;
	}

	
	@PutMapping("/api/comment/like/{commentId}")
	public Comment likeComment(@PathVariable Integer commentId, @RequestHeader("Authorization") String jwt) throws Exception {
		
		User user = userServices.findUserByJwt(jwt);
		
		Comment likedComment = commentServices.likeComment(commentId, user.getId());
				
		return likedComment;
	}
	
	
	

}
