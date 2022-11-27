package com.skilldistillery.interviewapp.services;

import java.util.List;

import com.skilldistillery.interviewapp.entities.Question;

public interface QuestionService {

	List<Question> questionList();

	Question show(int qid);

	public Question create(String username, Question question);

	public Question update(String username, int qid, Question question);

	boolean delete(int qid);

}
