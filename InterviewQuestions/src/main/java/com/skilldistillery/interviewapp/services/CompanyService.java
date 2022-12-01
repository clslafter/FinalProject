package com.skilldistillery.interviewapp.services;

import java.util.List;

import com.skilldistillery.interviewapp.entities.Company;

public interface CompanyService {
	
	List<Company> index();

	public Company show(int cid);

	public Company create(Company company);

	public Company update(int uid, Company company);

	boolean delete(int cid);
	
	public void addCompanyToQuestion(int companyId, int questionId);

	
}
