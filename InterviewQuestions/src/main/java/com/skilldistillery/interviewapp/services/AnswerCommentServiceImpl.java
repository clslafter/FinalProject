package com.skilldistillery.interviewapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.interviewapp.entities.Answer;
import com.skilldistillery.interviewapp.entities.AnswerComment;
import com.skilldistillery.interviewapp.entities.User;
import com.skilldistillery.interviewapp.repositories.AnswerCommentRepository;
import com.skilldistillery.interviewapp.repositories.AnswerRepository;
import com.skilldistillery.interviewapp.repositories.UserRepository;

@Service
public class AnswerCommentServiceImpl implements AnswerCommentService {

	@Autowired
	private AnswerCommentRepository answerCommentRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private AnswerRepository answerRepo;

	@Override
	public List<AnswerComment> index() {
		return answerCommentRepo.findAll();
	}

	@Override
	public AnswerComment show(int aid) {
		return answerCommentRepo.findById(aid);
	}

	@Override
	public AnswerComment create(String username, AnswerComment answerComment, int answerId) {
		User user = userRepo.findByUsername(username);
		if (user != null) {
			Answer answer = answerRepo.findById(answerId);
			answerComment.setAnswer(answer);
			answer.addAnswerComment(answerComment);
			user.addAnswerComment(answerComment);
			answerComment.setUser(user);
			return answerCommentRepo.saveAndFlush(answerComment);
		}
		return null;
	}

	@Override
	public AnswerComment update(int aid, AnswerComment answerComment) {
		AnswerComment managed = null;
		managed = answerCommentRepo.findById(aid);
		if (managed != null) {
			managed.setCommentText(answerComment.getCommentText());

			return answerCommentRepo.saveAndFlush(managed);
		}
		return managed;
	}

	@Override
	public boolean delete(int aid) {
		AnswerComment managed = null;
		managed = answerCommentRepo.findById(aid);
		if (managed != null) {

			managed.setEnabled(false);

			 answerCommentRepo.save(managed);
			// returns true if company was disabled
			return !managed.getEnabled();
		}
		// returns false if company is enabled
		return !answerCommentRepo.findById(aid).getEnabled();
	}

}
