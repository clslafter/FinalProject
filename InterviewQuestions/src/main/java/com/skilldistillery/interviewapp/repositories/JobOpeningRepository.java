package com.skilldistillery.interviewapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.interviewapp.entities.JobOpening;

public interface JobOpeningRepository extends JpaRepository<JobOpening, Integer> {
	
	JobOpening findById(int cId);
	
	
}
