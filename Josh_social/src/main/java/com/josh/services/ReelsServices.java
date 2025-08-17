package com.josh.services;

import java.util.List;


import com.josh.model.Reels;
import com.josh.model.User;


public interface ReelsServices {
	
	public Reels createReels(Reels reel,User user);
	
	public List<Reels> findAllReels();
	
	public List<Reels> findUserReels(Integer userId) throws Exception;
	

}
