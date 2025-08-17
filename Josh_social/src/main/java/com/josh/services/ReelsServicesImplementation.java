package com.josh.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.josh.model.Reels;
import com.josh.model.User;
import com.josh.repository.ReelsRepository;


@Service
public class ReelsServicesImplementation implements ReelsServices{
	
	@Autowired
	private ReelsRepository reelsRepository;
	
	@Autowired
	private UserServices userServices;
	
	@Override
	public Reels createReels(Reels reel, User user) {

		Reels createReels = new Reels();
		createReels.setTitle(reel.getTitle());
		createReels.setVideo(reel.getVideo());
		createReels.setUser(user);
		
		return reelsRepository.save(createReels);
	}

	@Override
	public List<Reels> findAllReels() {
		
		return reelsRepository.findAll();
	}

	@Override
	public List<Reels> findUserReels(Integer userId) throws Exception {
		User user = userServices.findUserById(userId);
		return reelsRepository.findByUserId(user.getId());
	}

}
