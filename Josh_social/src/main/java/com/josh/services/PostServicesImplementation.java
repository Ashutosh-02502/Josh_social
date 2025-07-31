package com.josh.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.josh.model.Post;
import com.josh.model.User;
import com.josh.repository.PostRepository;
import com.josh.repository.UserRepository;

@Service
public class PostServicesImplementation implements PostServices {
	
	@Autowired
	PostRepository postRepository;
	
	@Autowired
	UserServices userServices;
	
	@Autowired
	UserRepository userRepository;

	@Override
	public Post createNewPost(Post post, Integer userId) throws Exception {

		 System.out.println("Received userId: " + userId);

		    Post newPost = new Post();

		    User user = userServices.findUserById(userId);

		    newPost.setUser(user);
		    newPost.setCaption(post.getCaption());
		    newPost.setImage(post.getImage());
		    newPost.setVideo(post.getVideo());
		    newPost.setCreatedAt(LocalDateTime.now());

		    postRepository.save(newPost);
		
		return newPost;
	}

	@Override
	public String deletePost(Integer postId, Integer userId) throws Exception {
		
		Post post = findPostById(postId);
		User user = userServices.findUserById(userId);
		
		if(post.getUser().getId()!=user.getId()) throw new Exception("Can't Delete Post Of Another User");
		
		postRepository.delete(post);
		
		return "Post Deleted Successfully";
	}

	@Override
	public List<Post> findPostByUserId(Integer Id){
		
		return postRepository.findPostByUserId(Id);
	}

	@Override
	public Post findPostById(Integer Id) throws Exception {
		
		Optional<Post> post = postRepository.findById(Id);
		if(post.isEmpty()) {
			throw new Exception("Post with postId: "+Id+" is not present");
		}
		
		return post.get();
	}

	@Override
	public List<Post> findAllPost() {
		return postRepository.findAll();
	}

	@Override
	public Post savedPost(Integer postId, Integer userId) throws Exception {
		
		Post post = findPostById(postId);
		User user = userServices.findUserById(userId);
		
		if(user.getSavedPost().contains(post)) user.getSavedPost().remove(post);
		else user.getSavedPost().add(post);
		
		userRepository.save(user);
		
		return post;
	}

	@Override
	public Post likePost(Integer postId, Integer userId) throws Exception {
		
		Post post = findPostById(postId);
		User user = userServices.findUserById(userId);
		
		if(post.getLiked().contains(user)) post.getLiked().remove(user);
		else post.getLiked().add(user);
		
		return postRepository.save(post);
	}
	
	

}
