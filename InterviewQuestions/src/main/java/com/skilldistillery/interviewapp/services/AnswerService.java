package com.skilldistillery.interviewapp.services;

import java.util.List;

import com.skilldistillery.interviewapp.entities.Answer;

public interface AnswerService {
	
	List<Answer> answerList();
	
	Answer show(int aId);
	
	Answer create(String username, Answer answer);
	
	Answer update(Answer answer , int aId, String username);
	
	public boolean delete(String username, int aId);
	
	List<Answer> questionAnswers(int id);

}
