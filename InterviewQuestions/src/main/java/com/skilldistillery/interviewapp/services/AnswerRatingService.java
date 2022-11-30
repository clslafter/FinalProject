package com.skilldistillery.interviewapp.services;

import java.util.List;

import com.skilldistillery.interviewapp.entities.AnswerRating;

public interface AnswerRatingService {

	List <AnswerRating> answerRatingsList(int answerId);
	
	AnswerRating answerRatingUpVote(boolean up, int answerId, String username);
	
	
	
	
}
