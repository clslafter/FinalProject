package com.skilldistillery.interviewapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.interviewapp.entities.Company;
import com.skilldistillery.interviewapp.entities.Industry;
import com.skilldistillery.interviewapp.repositories.IndustryRepository;

@Service
public class IndustryServiceImpl implements IndustryService {
	
	@Autowired
	private IndustryRepository industryRepo;

	@Override
	public List<Industry> industryList() {
		return industryRepo.findAll();
	}
	
	@Override
	public Industry show(int iid) {
		return industryRepo.findById(iid);
	}

}
