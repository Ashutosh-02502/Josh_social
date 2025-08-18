package com.josh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.josh.model.Story;
import com.josh.model.User;
import com.josh.services.StoryServices;
import com.josh.services.UserServices;

@RestController
public class StoryController {

	@Autowired
	private StoryServices storyServices;
	
	@Autowired
	private UserServices userServices;
	
	
	@PostMapping("/api/story")
	public Story createStory(@RequestBody Story story,@RequestHeader("Authorization") String jwt) {
		
		User user = userServices.findUserByJwt(jwt);
		
		Story createdStory = storyServices.createStory(story, user);
		
		return createdStory;
	}
	
	@PostMapping("/api/story/user/{userId}")
	public List<Story> findUserStory(@PathVariable Integer userId,@RequestHeader("Authorization") String jwt) throws Exception {
		
		User user = userServices.findUserByJwt(jwt);
		
		List<Story> stories = storyServices.findStoryByUserId(userId);
		
		return stories;
	}
	
}
