package com.skilldistillery.interviewapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.interviewapp.entities.Company;
import com.skilldistillery.interviewapp.entities.JobOpening;
import com.skilldistillery.interviewapp.repositories.AddressRepository;
import com.skilldistillery.interviewapp.repositories.CompanyRepository;
import com.skilldistillery.interviewapp.repositories.JobOpeningRepository;
import com.skilldistillery.interviewapp.repositories.UserRepository;

@Service
public class JobOpeningServiceImpl implements JobOpeningService {
	
	@Autowired
	private JobOpeningRepository jobOpeningRepo;
	
	@Autowired
	private AddressRepository addressRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private CompanyRepository companyRepo;


	@Override
	public List<JobOpening> index() {
		return jobOpeningRepo.findAll();
		}

	@Override
	public JobOpening show(int jid) {
	return jobOpeningRepo.findById(jid);
	}

	@Override
	public JobOpening create(JobOpening jobOpening) {
		if (jobOpening.getAddress() != null) {
			jobOpening.setAddress(addressRepo.saveAndFlush(jobOpening.getAddress()));
		}
		if (jobOpening.getUser() != null) {
			jobOpening.setUser(userRepo.saveAndFlush(jobOpening.getUser()));
		}
		if (jobOpening.getCompany() != null) {
			jobOpening.setCompany(companyRepo.saveAndFlush(jobOpening.getCompany()));
		}
		return jobOpeningRepo.saveAndFlush(jobOpening);
	}

	@Override
	public JobOpening update(int jid, JobOpening jobOpening) {
		JobOpening managed = null;
		managed = jobOpeningRepo.findById(jid);
		if (managed != null) {
			managed.setDescription(jobOpening.getDescription());
			managed.setUrlPost(jobOpening.getUrlPost());
			managed.setRoleFilled(jobOpening.getRoleFilled());
			return jobOpeningRepo.saveAndFlush(managed);
		}
		return managed;
	}

	@Override
	public boolean delete(int jid) {
		JobOpening managed = null;
		managed = jobOpeningRepo.findById(jid);
		if (managed != null) {

			managed.setEnabled(false);

			jobOpeningRepo.save(managed);
			//returns true if job was disabled
			return !managed.getEnabled();
		}
		//returns false if job is enabled
		return !jobOpeningRepo.findById(jid).getEnabled();
	}

}
