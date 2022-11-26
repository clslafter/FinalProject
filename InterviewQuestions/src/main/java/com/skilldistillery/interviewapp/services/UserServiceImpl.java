package com.skilldistillery.interviewapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.skilldistillery.interviewapp.entities.User;
import com.skilldistillery.interviewapp.repositories.UserRepository;

public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;
	
	@Override
	public List<User> index(){
		return userRepo.findAll();
	}

	@Override
	public User show(int uid) {
		return userRepo.findById(uid);
	}

	@Override
	public User create(User user) {
		return userRepo.saveAndFlush(user);
	}

	@Override
	public User update(int uid, User user) {
		User managed = null;
		managed = userRepo.findById(uid);
		if (managed != null) {
			managed.setFirstName(user.getFirstName());
			managed.setLastName(user.getLastName());
			managed.setEnabled(user.getEnabled());
			managed.setUsername(user.getUsername());
			managed.setPassword(user.getPassword());
			managed.setEmail(user.getEmail());
			managed.setAboutMe(user.getAboutMe());
			managed.setDateCreated(user.getDateCreated());
			managed.setAvatarUrl(user.getAvatarUrl());
			managed.setAddress(user.getAddress());
			managed.setQuestions(user.getQuestions());
			managed.setAnswers(user.getAnswers());
			managed.setJobs(user.getJobs());

			return userRepo.save(managed);
		}
		return managed;
	}

	@Override
	public boolean delete(int uid) {
		User managed = null;
		managed = userRepo.findById(uid);
		if (managed != null) {

			managed.setEnabled(false);

			userRepo.save(managed);
			return managed.getEnabled();
		}
		return userRepo.findById(uid).getEnabled();
	}
}
