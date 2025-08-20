package com.josh.services;

import java.util.List;

import com.josh.model.Story;
import com.josh.model.User;

public interface Story2_Services {
	
public Story createStory(Story story, User user);
	
	public List<Story> findStoryByUserId(Integer userId) throws Exception;

}
