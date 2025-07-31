package com.josh.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.josh.model.Post;
import com.josh.response.ApiResponse;
import com.josh.services.PostServices;

@RestController
public class PostController {
	
	@Autowired
	PostServices postServices;
	
	
	@PostMapping("/posts/newpost/{userId}")
	public ResponseEntity<Post> createPost(@RequestBody Post post,@PathVariable Integer userId) throws Exception{
		
		Post createdPost = postServices.createNewPost(post, userId);
		
		return new ResponseEntity<Post>(createdPost,HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/posts/{postId}/{userId}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId,@PathVariable Integer userId) throws Exception{
		
		String message = postServices.deletePost(postId, userId);
		ApiResponse res = new ApiResponse(message,true);
		return new ResponseEntity<ApiResponse>(res,HttpStatus.OK);
	}
	
	@GetMapping("/posts/{postId}")
	public ResponseEntity<Post> findPostByIdHandler(@PathVariable Integer postId) throws Exception{
		
		Post post = postServices.findPostById(postId);

		return new ResponseEntity<Post>(post,HttpStatus.ACCEPTED);
	}
	
	
	@GetMapping("/posts/user/{userId}")
	public ResponseEntity<List<Post>> findUsersPost(@PathVariable Integer userId) throws Exception{
		
		List<Post> posts = postServices.findPostByUserId(userId);
		
		return new ResponseEntity<List<Post>>(posts,HttpStatus.OK);
	}
	
	@GetMapping("/posts")
	public ResponseEntity<List<Post>> findAllPosts() {
		
		List<Post> posts = postServices.findAllPost();
		
		return new ResponseEntity<List<Post>>(posts,HttpStatus.OK);
	}
	
	@PutMapping("/posts/{postId}/user/{userId}")
	public ResponseEntity<Post> savePostHandler(@PathVariable Integer postId, @PathVariable Integer userId) throws Exception{
		
		Post post = postServices.savedPost(postId,userId);

		return new ResponseEntity<Post>(post,HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/posts/like/{postId}/user/{userId}")
	public ResponseEntity<Post> likePostHandler(@PathVariable Integer postId, @PathVariable Integer userId) throws Exception{
		
		Post post = postServices.likePost(postId,userId);

		return new ResponseEntity<Post>(post,HttpStatus.ACCEPTED);
	}
	
	

}
