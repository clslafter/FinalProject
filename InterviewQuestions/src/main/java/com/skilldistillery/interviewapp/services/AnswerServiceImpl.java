package com.skilldistillery.interviewapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.interviewapp.entities.Answer;
import com.skilldistillery.interviewapp.entities.Question;
import com.skilldistillery.interviewapp.entities.User;
import com.skilldistillery.interviewapp.repositories.AnswerRepository;
import com.skilldistillery.interviewapp.repositories.QuestionRepository;
import com.skilldistillery.interviewapp.repositories.UserRepository;

@Service
public class AnswerServiceImpl implements AnswerService {

	@Autowired
	private AnswerRepository answerRepo;
	
	@Autowired
	private QuestionRepository questionRepo;

	@Autowired
	private UserRepository userRepo;

	@Override
	public List<Answer> answerList() {
		return answerRepo.findAll();
	}

	@Override
	public Answer show(int aId) {
		return answerRepo.findById(aId);
	}

	@Override
	public Answer create(String username, Answer answer, int questionId) {
		User user = userRepo.findByUsername(username);
		if (user != null) {
			Question question = questionRepo.findById(questionId);
			answer.setQuestion(question);
			question.addAnswer(answer);
			user.addAnswer(answer);
			answer.setUser(user);
			return answerRepo.saveAndFlush(answer);
		}
		return null;
	}

	@Override
	public Answer update(Answer answer, int aId, String username) {
		User user = userRepo.findByUsername(username);
		Answer updatedAnswer = answerRepo.findById(aId);
		if (updatedAnswer.getUser().getUsername().equals(username)) {
			if (updatedAnswer != null) {
				updatedAnswer.setAnswer(answer.getAnswer());
				updatedAnswer.setEnabled(answer.isEnabled());
				updatedAnswer.setDateUpdated(answer.getDateUpdated());
				updatedAnswer.setDateCreated(answer.getDateCreated());
				updatedAnswer.setUser(user);
				updatedAnswer.setRatings(answer.getRatings());

				return answerRepo.save(updatedAnswer);
			}
		}
		return updatedAnswer;
	}

	@Override
	public boolean delete(String username, int aId) {
		Answer answerToDelete = answerRepo.findById(aId);
		if (answerToDelete != null) {

			answerToDelete.setEnabled(false);

			answerRepo.save(answerToDelete);

			return !answerToDelete.isEnabled();
		}
		return !answerRepo.findById(aId).isEnabled();
	}

	@Override
	public List<Answer> questionAnswers(int id) {
		return answerRepo.findByQuestionId(id);
	}

}
