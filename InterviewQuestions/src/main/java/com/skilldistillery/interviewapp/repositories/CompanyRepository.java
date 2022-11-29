package com.skilldistillery.interviewapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.interviewapp.entities.Company;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
	
	Company findById(int cId);
	
	//Company findByJobId(int jId);
	
	//List <Company> findByQuestionId(int qId);
	
}
