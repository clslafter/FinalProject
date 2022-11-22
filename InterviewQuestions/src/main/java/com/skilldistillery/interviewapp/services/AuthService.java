package com.skilldistillery.interviewapp.services;

import com.skilldistillery.interviewapp.entities.User;

public interface AuthService {

	public User register(User user);
	public User getUserByUsername(String username);
	
}
