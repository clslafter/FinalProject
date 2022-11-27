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

	@Override
	public Question show(int qid) {
		return questionRepo.findById(qid);
	}

	@Override
	public Question create(Question question) {
		return questionRepo.saveAndFlush(question);
	}

	@Override
	public Question update(int qid, Question question) {
		Question managed = null;
		managed = questionRepo.findById(qid);
		if (managed != null) {
			managed.setDateCreated(question.getDateCreated());
			managed.setDateUpdated(question.getDateUpdated());
			managed.setEnabled(question.getEnabled());
			managed.setQuestion(question.getQuestion());
			managed.setEnabled(question.getEnabled());
			managed.setUser(question.getUser());
			managed.setAnswers(question.getAnswers());
			managed.setCategories(question.getCategories());
			managed.setCompanies(question.getCompanies());

			return questionRepo.save(managed);
		}
		return managed;
	}

	@Override
	public boolean delete(int qid) {
		Question managed = null;
		managed = questionRepo.findById(qid);
		if (managed != null) {

			managed.setEnabled(false);

			questionRepo.save(managed);
			//returns true if question was disabled
			return !managed.getEnabled();
		}
		//returns false if question is enabled
		return !questionRepo.findById(qid).getEnabled();
	}
	
}
