package com.skilldistillery.interviewapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.interviewapp.entities.Industry;

public interface IndustryRepository extends JpaRepository<Industry, Integer>{
	
	Industry findById (int idd);

}
