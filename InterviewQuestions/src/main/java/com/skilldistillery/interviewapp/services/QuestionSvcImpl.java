package com.skilldistillery.interviewapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.interviewapp.entities.Category;
import com.skilldistillery.interviewapp.entities.Question;
import com.skilldistillery.interviewapp.entities.User;
import com.skilldistillery.interviewapp.repositories.CategoryRepository;
import com.skilldistillery.interviewapp.repositories.QuestionRepository;
import com.skilldistillery.interviewapp.repositories.UserRepository;

@Service
public class QuestionSvcImpl implements QuestionService {
	
	@Autowired
	private QuestionRepository questionRepo;
	
	@Autowired
	
	private UserRepository userRepo;
	@Autowired
	private CategoryRepository categoryRepo;
	
	@Override
	public List<Question> questionList() {
		//may need to sort by enabled into new list
		return questionRepo.findAll();
	}

	@Override
	public Question show(int qid) {
		return questionRepo.findById(qid);
	}

	@Override
	//public Question create(Question question) {
		public Question create(String username, Question question) {
		User user = userRepo.findByUsername(username);
		if (user != null) {
			question.setUser(user);
			questionRepo.saveAndFlush(question);
			for (Category category: question.getCategories()) {
				Category newCat = categoryRepo.queryById(category.getId());
				newCat.addQuestion(question);
				categoryRepo.saveAndFlush(newCat);
			}
			return question;
		}
		return null;
	}
	

	@Override
	public Question update(String username, int qid, Question question) {
		User user = userRepo.findByUsername(username);
		Question managed = questionRepo.findById(qid);
		if(managed.getUser().getUsername().equals(username)) {
			if (managed != null) {
				managed.setDateCreated(question.getDateCreated());
				managed.setDateUpdated(question.getDateUpdated());
				managed.setEnabled(question.getEnabled());
				managed.setQuestion(question.getQuestion());
				managed.setEnabled(question.getEnabled());
				managed.setUser(userRepo.findByUsername(username));
				managed.setAnswers(question.getAnswers());
				managed.setCategories(question.getCategories());
				managed.setCompanies(question.getCompanies());
				System.out.println(managed.getCategories());
				
				
				List<Category> allCats = categoryRepo.findAll();
				for (Category category : allCats) {
					if(!managed.getCategories().contains(category) && category.getQuestions().contains(managed)) {
						category.removeQuestion(managed);
					}
				}
				
				for (int i = 0; i < managed.getCategories().size(); i++) {
					Category managedCat = categoryRepo.queryById(managed.getCategories().get(i).getId());
					managedCat.addQuestion(managed);
					managed.getCategories().set(i, managedCat);
				}
				
				
				
				Question savedQuestion = questionRepo.save(managed);
				
				
			return savedQuestion;
			}
		}
		return managed;
	}

	@Override
	public boolean delete(String username, int qid) {
		Question managed = questionRepo.findByIdAndUser_Username(qid, username);
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
