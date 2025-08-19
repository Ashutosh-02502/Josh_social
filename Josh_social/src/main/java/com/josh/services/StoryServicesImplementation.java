package com.josh.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.josh.model.Story;
import com.josh.model.User;
import com.josh.repository.StoryRepository;


@Service
public class StoryServicesImplementation implements StoryServices {

	
	@Autowired
	private StoryRepository storyRepository;
	
	@Autowired
	private UserServices userServices;
	
	@Override
	public Story createStory(Story story, User user) {
		
		Story createdStory = new Story();
		
		createdStory.setCaptions(story.getCaptions());
		createdStory.setImage(story.getImage());
		createdStory.setUser(user);
		createdStory.setTimestamp(LocalDateTime.now());
		
		Story savedStory = storyRepository.save(createdStory);
	
		return savedStory;
	}

	@Override
	public List<Story> findStoryByUserId(Integer userId) throws Exception {
		
		User user = userServices.findUserById(userId);
		
		return storyRepository.findByUserId(userId);
	}

}
