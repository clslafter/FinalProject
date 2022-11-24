package com.skilldistillery.interviewapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.interviewapp.entities.Question;
import com.skilldistillery.interviewapp.repositories.QuestionRepository;

@Service
public class QuestionSvcImpl implements QuestionService {
	
	@Autowired
	private QuestionRepository questionRepo;
	
	@Override
	public List<Question> questionList() {
		return questionRepo.findAll();
	}

}
