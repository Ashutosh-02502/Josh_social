package com.josh.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.josh.model.User;
import com.josh.repository.UserRepository;


@Service
public class UserServicesImplementation implements UserServices{
	
	@Autowired
	UserRepository userRepository;
	
	
	@Override
	public User resisterUser(User user) {
		User user1 = new User();
		if(user.getId()!=null) user1.setId(user.getId());
		if(user.getFirstname()!=null) user1.setFirstname(user.getFirstname());
		if(user.getLastname()!=null) user1.setLastname(user.getLastname());
		if(user.getEmail()!=null) user1.setEmail(user.getEmail());
		if(user.getPassword()!=null) user1.setPassword(user.getPassword());
 		
		
		User savedUser = userRepository.save(user1);
		return savedUser;
	}

	@Override
	public User findUserById(Integer id) throws Exception {
		Optional<User> user = userRepository.findById(id);
		
		if(user.isPresent()) return user.get();
		else {
			throw new Exception("User with userId: "+id+" does not exist");
		}
	}

	@Override
	public User findUserByEmail(String email) {
		User user = userRepository.findByEmail(email);
		return user;
	}

	@Override
	public User followUser(Integer userId1, Integer userId2) throws Exception {
		User user1 = findUserById(userId1);
		User user2 = findUserById(userId2);
		
		user2.getFollowers().add(user1.getId());
		user1.getFollowings().add(user2.getId());
		
		userRepository.save(user1);
		userRepository.save(user2);
		
		
		return user1;
	}
	
	@Override
	public User unfollowUser(Integer userId1, Integer userId2) throws Exception {
		User user1 = findUserById(userId1);
		User user2 = findUserById(userId2);
		
		user1.getFollowings().remove(user2.getId());
		user2.getFollowers().remove(user1.getId());
		
		userRepository.save(user1);
		userRepository.save(user2);
		
		return user1;
	}
	
	

	@Override
	public User updateUser(User user, Integer id) throws Exception {
		Optional<User> oldUser = userRepository.findById(id);
		if(oldUser.isEmpty()) {
			throw new Exception("User with userId: "+id+" does not present");
		}
		
		User existingUser = oldUser.get();
		
		if(user.getId()!=null) existingUser.setId(user.getId());
		if(user.getFirstname()!=null) existingUser.setFirstname(user.getFirstname());
		if(user.getLastname()!=null) existingUser.setLastname(user.getLastname());
		if(user.getEmail()!=null) existingUser.setEmail(user.getEmail());
		if(user.getPassword()!=null) existingUser.setPassword(user.getPassword());
		
		User savedUser = userRepository.save(existingUser);
	
		return savedUser;
	}

	@Override
	public List<User> searchUser(String query) {
		
		return userRepository.searchUser(query);
	}
	
	
	
}
