package com.skilldistillery.interviewapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.interviewapp.entities.Answer;
import com.skilldistillery.interviewapp.entities.AnswerRating;
import com.skilldistillery.interviewapp.entities.User;
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
	public AnswerRating answerRatingUpVote(boolean up, int answerId, String username) {
		User user = userRepo.findByUsername(username);
		System.out.println(user);
		Answer answer = answerRepo.findById(answerId);
		System.out.println(answer);
		AnswerRating answerRating = answerRatingRepo.findByAnswerAndUser(answer, user);
//		answerRating.setId(new AnswerRatingId(1,1));
		answerRating.setAnswer(answerRepo.findById(2));
		answerRating.setUpvote(up);
		answerRating.setUser(userRepo.findByUsername(username));
		System.out.println(answerRating);
		return answerRatingRepo.saveAndFlush(answerRating);
	}

}
