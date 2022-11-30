package com.skilldistillery.interviewapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.interviewapp.entities.Answer;
import com.skilldistillery.interviewapp.entities.AnswerRating;
import com.skilldistillery.interviewapp.entities.AnswerRatingId;
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
		AnswerRating ansRating = new AnswerRating();
		User user = userRepo.findByUsername(username);
		System.out.println(user);
		Answer answer = new Answer();
		answer.setId(answerId);
		System.out.println(answer);
		if (user == null) {
			ansRating = answerRatingRepo.findByAnswerAndUser(answer, user);
		}
		ansRating.setAnswer(answer);
		ansRating.setUpvote(up);
		ansRating.setUser(userRepo.findByUsername(username));
		System.out.println(ansRating);
		AnswerRatingId ansRatingId = new AnswerRatingId();
		ansRatingId.setAnswerId(answerId);
		ansRatingId.setUserId(user.getId());
		ansRating.setId(ansRatingId);
		return answerRatingRepo.saveAndFlush(ansRating);
	}

}
