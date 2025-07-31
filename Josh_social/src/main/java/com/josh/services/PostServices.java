package com.josh.services;

import java.util.List;

import com.josh.model.Post;

public interface PostServices {
	
	Post createNewPost(Post post, Integer userId) throws Exception;
	
	String deletePost(Integer postId,Integer userId) throws Exception;
	
	List<Post> findPostByUserId(Integer Id) throws Exception;
	
	Post findPostById(Integer Id) throws Exception;
	
	List<Post> findAllPost();
	
	Post savedPost(Integer postId, Integer userId) throws Exception;
	
	Post likePost(Integer postId,Integer userId) throws Exception;
	
	

}
