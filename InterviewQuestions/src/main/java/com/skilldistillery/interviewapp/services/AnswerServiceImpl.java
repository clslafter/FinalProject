package com.skilldistillery.interviewapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.interviewapp.entities.Answer;
import com.skilldistillery.interviewapp.repositories.AnswerRepository;

@Service
public class AnswerServiceImpl implements AnswerService {
	
	@Autowired
	private AnswerRepository answerRepo;

	@Override
	public List<Answer> answerList() {
		return answerRepo.findAll();
	}

	@Override
	public Answer show(int aId) {
		return answerRepo.findById(aId);
	}

	@Override
	public Answer create(Answer answer) {
		
		return null;
	}

	@Override
	public Answer update(Answer answer, int aId) {
		Answer updatedAnswer = answerRepo.findById(aId);
		if(updatedAnswer != null) {
			updatedAnswer.setAnswer(answer.getAnswer());
			updatedAnswer.setEnabled(true);
			updatedAnswer.setDateUpdated(answer.getDateUpdated());
			updatedAnswer.setQuestion(answer.getQuestion());
			updatedAnswer.setDateCreated(answer.getDateCreated());
			updatedAnswer.setUser(answer.getUser());
			return answerRepo.save(updatedAnswer);
		}
		
		return updatedAnswer;
	}

	@Override
	public Answer delete(int aId) {
		// TODO Auto-generated method stub
		return null;
	}

}
