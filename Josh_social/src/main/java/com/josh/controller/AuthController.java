package com.josh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.josh.config.JwtProvider;
import com.josh.model.User;
import com.josh.repository.UserRepository;
import com.josh.request.LoginRequest;
import com.josh.responce.AuthResponce;
import com.josh.services.CustomeUserDetailService;


@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private CustomeUserDetailService customeUserDetailService;
	
	//CREATE-NEW-USER
	@PostMapping("/user/signup")
	public AuthResponce createUser(@RequestBody User user) throws Exception {
		
		User isExist = userRepository.findByEmail(user.getEmail());
		if(isExist!=null) throw new Exception("User with Email: "+ isExist.getEmail()+" already resistered");
		
		User user1 = new User();
		
		if(user.getFirstname()!=null) user1.setFirstname(user.getFirstname());
		if(user.getLastname()!=null) user1.setLastname(user.getLastname());
		if(user.getEmail()!=null) user1.setEmail(user.getEmail());
		if(user.getPassword()!=null) user1.setPassword(passwordEncoder.encode(user.getPassword()));
 		
		
		User savedUser = userRepository.save(user1);
		
		Authentication authentication = new UsernamePasswordAuthenticationToken(savedUser.getEmail(), savedUser.getPassword());
		
		String token = JwtProvider.generateToken(authentication);
		
		AuthResponce res = new AuthResponce(token,"Register Successfull");
		
		return res;
	}
	
	
	@PostMapping("/user/signin")
	public AuthResponce signin(@RequestBody LoginRequest loginRequest) {
		
		Authentication authentication = authenticate(loginRequest.getEmail(),loginRequest.getPassword());
		
		String token = JwtProvider.generateToken(authentication);
		
		AuthResponce res = new AuthResponce(token,"Login Successfull");
		
		return res;
	}



	private Authentication authenticate(String email, String password) {
		
		UserDetails userDetails = customeUserDetailService.loadUserByUsername(email);
		
		if(userDetails==null) throw new BadCredentialsException("Invalid User");
		
		if(!passwordEncoder.matches(password, userDetails.getPassword())) {
			throw new BadCredentialsException("password not matched");
		}
		
		
		return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
	}

}
