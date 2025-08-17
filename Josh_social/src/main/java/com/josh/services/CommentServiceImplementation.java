package com.josh.services;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.josh.model.Comment;
import com.josh.model.Post;
import com.josh.model.User;
import com.josh.repository.CommentRepository;
import com.josh.repository.PostRepository;


@Service
public class CommentServiceImplementation implements CommentServices{
	
	@Autowired
	private UserServices userServices;
	
	@Autowired
	private PostServices postServices;
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Override
	public Comment createComment(Comment comment, Integer postId, Integer userId) throws Exception{
		
		User user = userServices.findUserById(userId);
		
		Post post = postServices.findPostById(userId);
		
		Comment newComment = new Comment();
		
		newComment.setContent(comment.getContent());
		newComment.setCreatedAt(LocalDateTime.now());
		newComment.setUser(user);
		
		Comment savedComment =  commentRepository.save(newComment);
		
		post.getComments().add(savedComment);
		
		postRepository.save(post);
		
		return savedComment ;
	}

	@Override
	public Comment findCommentById(Integer commentId) throws Exception {
		
		Optional<Comment> comment = commentRepository.findById(commentId);
		if(comment.isEmpty()) throw new Exception("No Coment Fpund with CommentId: "+commentId);
		
		
		return comment.get();
	}

	@Override
	public Comment likeComment(Integer commentId, Integer userId) throws Exception {
		
		Comment existingComment = findCommentById(commentId);
		
		User existingUser = userServices.findUserById(userId);
		
		if(existingComment.getLiked().contains(existingUser)) existingComment.getLiked().remove(existingUser);
		else existingComment.getLiked().add(existingUser);
		
		return commentRepository.save(existingComment);
	}

}
