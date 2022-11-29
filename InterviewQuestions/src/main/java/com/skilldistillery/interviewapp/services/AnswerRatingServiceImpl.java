package com.skilldistillery.interviewapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.interviewapp.entities.Answer;
import com.skilldistillery.interviewapp.entities.AnswerRating;
import com.skilldistillery.interviewapp.repositories.AnswerRatingRepository;
import com.skilldistillery.interviewapp.repositories.AnswerRepository;
import com.skilldistillery.interviewapp.repositories.UserRepository;

@Service
public class AnswerRatingServiceImpl implements AnswerRatingService {
	
	@Autowired
	private AnswerRatingRepository answerRatingRepo;
	
	@Autowired
	private AnswerRepository answerRepo;
	
	@Autowired
	private UserRepository userRepo;

	@Override
	public List<AnswerRating> answerRatingsList(int answerId) {
		return answerRatingRepo.findByAnswerId(answerId);
	}

	@Override
	public AnswerRating answerRatingUpVote(boolean up, int answerId) {
		List <AnswerRating> answerToUpVote = answerRatingRepo.findByAnswerId(answerId);
		
		return null;
	}

	@Override
	public AnswerRating answerRatingDownVote(boolean down, int answerId) {
		List <AnswerRating> answerToDownVote = answerRatingRepo.findByAnswerId(answerId);
		return null;
	}

	

}
