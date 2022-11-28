package com.skilldistillery.interviewapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.interviewapp.entities.Answer;

public interface AnswerRepository extends JpaRepository<Answer, Integer> {
	
	Answer findById(int aId);
	
	List<Answer> findByQuestionId(int aId);
	
}
