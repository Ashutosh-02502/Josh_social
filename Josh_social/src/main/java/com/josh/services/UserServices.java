package com.josh.services;

import java.util.List;

import com.josh.model.User;

public interface UserServices {
	
	public User resisterUser(User user);
	
	public User findUserById(Integer userId) throws Exception;
	
	public User findUserByEmail(String email);
	
	public User followUser(Integer userId1, Integer userId2) throws Exception;
	
	public User unfollowUser(Integer userId1, Integer userId2) throws Exception;
	
	public User updateUser(User user, Integer id) throws Exception;
	
	public List<User> searchUser(String query);
	
	public User findUserByJwt(String jwt);
	
}
