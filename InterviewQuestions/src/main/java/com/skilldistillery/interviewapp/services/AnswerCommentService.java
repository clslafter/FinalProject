package com.skilldistillery.interviewapp.services;

import java.util.List;

import com.skilldistillery.interviewapp.entities.AnswerComment;
import com.skilldistillery.interviewapp.entities.User;

public interface AnswerCommentService {
	
	List<AnswerComment> index();

	public AnswerComment show(int aid);

	public AnswerComment create(AnswerComment answerComment, User user);

	public AnswerComment update(int aid, AnswerComment answerComment);

	boolean delete(int aid);
}
