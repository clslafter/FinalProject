package com.skilldistillery.interviewapp.services;

import java.util.List;

import com.skilldistillery.interviewapp.entities.Answer;

public interface AnswerService {
	
	List<Answer> answerList();
	
	Answer show(int aId);
	
	Answer create(Answer answer);
	
	Answer update(Answer answer , int aId);
	
	Answer delete(int aId);

}
