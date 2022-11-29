package com.skilldistillery.interviewapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.interviewapp.entities.Address;
import com.skilldistillery.interviewapp.entities.User;
import com.skilldistillery.interviewapp.repositories.AddressRepository;
import com.skilldistillery.interviewapp.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private AddressRepository addressRepo;
	
	@Autowired
	private AddressService addressSvc;
	
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
		
			if (user.getAddress() != null) {
			
			user.setAddress(addressRepo.saveAndFlush(user.getAddress()));
		}
		return userRepo.saveAndFlush(user);
	}

	@Override
	public User update(String username, User user) {
		User managed = null;
		managed = userRepo.findByUsername(username);
		if (managed != null) {
			managed.setFirstName(user.getFirstName());
			managed.setLastName(user.getLastName());
//			managed.setEnabled(user.getEnabled());
//			managed.setUsername(user.getUsername());
//			managed.setPassword(user.getPassword());
			managed.setEmail(user.getEmail());
			managed.setAboutMe(user.getAboutMe());
//			managed.setDateCreated(user.getDateCreated());
			managed.setAvatarUrl(user.getAvatarUrl());
//			managed.setAddress(user.getAddress());
			if (user.getAddress() != null) {
				System.out.println(user.getAddress());
				Address managedAddress = addressSvc.update(user.getAddress(), user.getAddress().getId());
				managed.setAddress(managedAddress);
			}
//			managed.setQuestions(user.getQuestions());
//			managed.setAnswers(user.getAnswers());
//			managed.setJobs(user.getJobs());

			return userRepo.saveAndFlush(managed);
		}
		return managed;
	}
	
	@Override
	public User addAddress(String username, int aid) {
		User managed = null;
		managed = userRepo.findByUsername(username);
		if (managed != null) {
			Address newAddress = addressRepo.findById(aid);
			if(managed.getAddress() == null) {
				managed.setAddress(newAddress);
			}

			
			return userRepo.saveAndFlush(managed);
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
			//returns true if user was disabled
			return !managed.getEnabled();
		}
		//returns false if user is enabled
		return !userRepo.findById(uid).getEnabled();
	}
	
	@Override
	public boolean enable(int uid) {
		User managed = null;
		managed = userRepo.findById(uid);
		if (managed != null) {
			managed.setEnabled(true);
			
			userRepo.save(managed);
			//returns true if user was disabled
			return managed.getEnabled();
		}
		//returns false if user is enabled
		return userRepo.findById(uid).getEnabled();
	}
	
//
}
