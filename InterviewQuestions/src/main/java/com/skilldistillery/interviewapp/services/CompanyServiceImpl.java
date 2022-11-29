package com.skilldistillery.interviewapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.interviewapp.entities.Company;
import com.skilldistillery.interviewapp.repositories.AddressRepository;
import com.skilldistillery.interviewapp.repositories.CompanyRepository;

@Service
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	private CompanyRepository companyRepo;

	@Autowired
	private AddressRepository addressRepo;

	@Override
	public List<Company> index() {
		return companyRepo.findAll();
	}

	@Override
	public Company show(int cid) {
		return companyRepo.findById(cid);
	}

	@Override
	public Company create(Company company) {

		if (company.getAddress() != null) {

			company.setAddress(addressRepo.saveAndFlush(company.getAddress()));
		}
		return companyRepo.saveAndFlush(company);
	}

	@Override
	public Company update(int cid, Company company) {
		Company managed = null;
		managed = companyRepo.findById(cid);
		if (managed != null) {
			managed.setName(company.getName());
			managed.setDescription(company.getDescription());
			managed.setLogoURL(company.getLogoURL());
			return companyRepo.saveAndFlush(managed);
		}
		return managed;
	}

	@Override
	public boolean delete(int cid) {
		Company managed = null;
		managed = companyRepo.findById(cid);
		if (managed != null) {

			managed.setEnabled(false);

			companyRepo.save(managed);
			//returns true if user was disabled
			return !managed.isEnabled();
		}
		//returns false if user is enabled
		return !companyRepo.findById(cid).isEnabled();
	}
}
