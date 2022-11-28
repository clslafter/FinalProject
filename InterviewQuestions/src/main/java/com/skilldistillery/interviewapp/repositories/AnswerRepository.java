package com.skilldistillery.interviewapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.interviewapp.entities.Answer;

public interface AnswerRepository extends JpaRepository<Answer, Integer> {
	
	Answer findById(int aId);
	
}
