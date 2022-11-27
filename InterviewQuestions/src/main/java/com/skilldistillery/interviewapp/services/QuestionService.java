package com.skilldistillery.interviewapp.services;

import java.util.List;

import com.skilldistillery.interviewapp.entities.Question;

public interface QuestionService {

	List<Question> questionList();

	Question show(int qid);

	//Question create(Question question);
	
	public Question create(String username, Question question);

	Question update(int qid, Question question);

	boolean delete(int qid);

}
