package com.skilldistillery.interviewapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.interviewapp.entities.Question;



public interface QuestionRepository extends JpaRepository<Question, Integer>{
	
	

}
