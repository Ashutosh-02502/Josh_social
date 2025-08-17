package com.josh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.josh.model.Reels;
import com.josh.model.User;
import com.josh.services.ReelsServices;
import com.josh.services.UserServices;

@RestController
public class ReelsController {
	
	@Autowired
	private ReelsServices reelsServices;
	
	@Autowired
	private UserServices userServices;
	
	@PostMapping("/api/createReels")
	public Reels createReels(@RequestBody Reels reels, @RequestHeader("Authorization") String jwt) {
		
		User reqUser = userServices.findUserByJwt(jwt);
		
		Reels createdReel = reelsServices.createReels(reels, reqUser);
		
		
		return createdReel;
	}
	
	@GetMapping("/api/allReels/user/{userId}")
	public List<Reels> findUsersReels(@PathVariable Integer userId) throws Exception {
		
		List<Reels> allReel = reelsServices.findUserReels(userId);
		
		return allReel;
	}

}
