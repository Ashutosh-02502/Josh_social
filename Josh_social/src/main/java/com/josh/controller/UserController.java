package com.josh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.josh.model.User;
import com.josh.repository.UserRepository;
import com.josh.services.UserServices;

@RestController
public class UserController {

	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserServices userServices;
  
	
	//CREATE-NEW-USER
	@PostMapping("/user/insert")
	public User createUser(@RequestBody User user) {
		
		User savedUser = userServices.resisterUser(user);		
		return savedUser;
	}
	
	//GET-ALL-USERS
	@GetMapping("/api/users")
	public List<User> getUsers(){
		
		List<User> users = userRepository.findAll();
		
		return users;
	}
	
	//FIND-USER-WITH-ID
	@GetMapping("/api/user/{userid}")
	public User getUserById(@PathVariable("userid") Integer id) throws Exception {
		
		User existingUser = userServices.findUserById(id);
		return existingUser;
	}
	
	//UPDATE-USER-WITH-ID
	@PutMapping("/updateuser/{userid}")
	public User updateUserById(@RequestBody User user, @PathVariable("userid") Integer id) throws Exception {
		
		User savedUser = userServices.updateUser(user,id);
		return savedUser;
	}
	
//	@DeleteMapping("/deleteuser/{userid}")
//	public User deleteUserById(@PathVariable("userid") Integer id) throws Exception {
//		
//		Optional<User> existingUser = userRepository.findById(id);
//		if(existingUser.isEmpty()) {
//			throw new Exception("User with userId: "+id+" does not present");
//		}
//		userRepository.delete(existingUser.get());
//		return existingUser.get();
//	}
//	
	
	@PutMapping("/users/follow/{userId1}/{userId2}")
	public User followUserHandler(@PathVariable Integer userId1,@PathVariable Integer userId2) throws Exception {
		
		User user = userServices.followUser(userId1, userId2);
		
		return user;
	}
	
	@PutMapping("/users/unfollow/{userId1}/{userId2}")
	public User unfollowUser(@PathVariable Integer userId1, @PathVariable Integer userId2) throws Exception {
	
		User user = userServices.unfollowUser(userId1, userId2);
		
		return user;
	}
	
	@GetMapping("users/search")
	public List<User> searchUser(@RequestParam("query") String query){
		
		List<User> users = userServices.searchUser(query);
		return users;
	}
	
}
