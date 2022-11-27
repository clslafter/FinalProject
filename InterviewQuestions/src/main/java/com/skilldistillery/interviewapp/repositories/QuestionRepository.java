package com.skilldistillery.interviewapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.interviewapp.entities.Question;
import com.skilldistillery.interviewapp.entities.User;



public interface QuestionRepository extends JpaRepository<Question, Integer>{
	
	Question findById(int qid);
	
	Question findByIdAndUser_Username(int qid, String username);

}
