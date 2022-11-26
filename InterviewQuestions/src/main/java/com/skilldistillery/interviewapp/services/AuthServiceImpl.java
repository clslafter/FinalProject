package com.skilldistillery.interviewapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.skilldistillery.interviewapp.entities.User;
import com.skilldistillery.interviewapp.repositories.UserRepository;




@Service
public class AuthServiceImpl implements AuthService {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private PasswordEncoder encoder;

	@Override
	public User register(User user) {
		System.out.println("********Entered AuthService register method********");
		String ecryptedPassword = encoder.encode(user.getPassword());
		user.setPassword(ecryptedPassword);
		user.setEnabled(true);
		user.setRole("USER");
		userRepo.saveAndFlush(user);
		return user;
	}

	@Override
	public User getUserByUsername(String username) {
		return userRepo.findByUsername(username);
	}

}
