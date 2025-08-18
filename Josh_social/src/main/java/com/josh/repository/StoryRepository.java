package com.josh.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.josh.model.Story;

public interface StoryRepository extends JpaRepository<Story, Integer> {

	public List<Story> findByUserId(Integer userId);
	
}
