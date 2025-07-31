package com.josh.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class User {

		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private Integer id;
		private String firstname;
		private String lastname;
		private String email;
		private String password;
		private String gender;
		
		private List<Integer> followers = new ArrayList<>();
		private List<Integer> followings = new ArrayList<>();
		
		
		@JsonIgnore
		@ManyToMany
		private List<Post> savedPost = new ArrayList<>();
		
		
		public User(){
			
		}

		public User(Integer id, String firstname, String lastname, String email, String password, String gender,
				List<Integer> followers, List<Integer> followings, List<Post> savedPost) {
			super();
			this.id = id;
			this.firstname = firstname;
			this.lastname = lastname;
			this.email = email;
			this.password = password;
			this.gender = gender;
			this.followers = followers;
			this.followings = followings;
			this.savedPost = savedPost;
		}


		public List<Post> getSavedPost() {
			return savedPost;
		}

		public void setSavedPost(List<Post> savedPost) {
			this.savedPost = savedPost;
		}

		public String getGender() {
			return gender;
		}

		public void setGender(String gender) {
			this.gender = gender;
		}

		public List<Integer> getFollowers() {
			return followers;
		}

		public void setFollowers(List<Integer> followers) {
			this.followers = followers;
		}

		public List<Integer> getFollowings() {
			return followings;
		}

		public void setFollowings(List<Integer> followings) {
			this.followings = followings;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}


		public Integer getId() {
			return id;
		}


		public void setId(Integer id) {
			this.id = id;
		}


		public String getFirstname() {
			return firstname;
		}


		public void setFirstname(String firstname) {
			this.firstname = firstname;
		}


		public String getLastname() {
			return lastname;
		}


		public void setLastname(String lastname) {
			this.lastname = lastname;
		}


		public String getEmail() {
			return email;
		}


		public void setEmail(String email) {
			this.email = email;
		}
		
}
