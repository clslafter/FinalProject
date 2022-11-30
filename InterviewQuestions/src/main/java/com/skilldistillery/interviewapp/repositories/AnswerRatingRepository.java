package com.skilldistillery.interviewapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.interviewapp.entities.Answer;
import com.skilldistillery.interviewapp.entities.AnswerRating;
import com.skilldistillery.interviewapp.entities.User;

public interface AnswerRatingRepository extends JpaRepository<AnswerRating, Integer> {
	List <AnswerRating> findByAnswerId(int answerId);
	
	AnswerRating findByAnswerAndUser(Answer answer, User user);
	
}
