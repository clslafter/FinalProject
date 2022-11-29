package com.skilldistillery.interviewapp.services;

import java.util.List;


import com.skilldistillery.interviewapp.entities.User;

public interface UserService {
	
	List<User> index();

	public User show(int uid);

	public User create(User user);

	public User update(String username, User user);

	boolean delete(int uid);

	User addAddress(String username, int aid);

	
}
