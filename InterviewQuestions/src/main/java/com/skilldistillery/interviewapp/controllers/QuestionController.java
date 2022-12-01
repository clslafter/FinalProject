package com.skilldistillery.interviewapp.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.interviewapp.entities.Question;
import com.skilldistillery.interviewapp.services.QuestionService;

@RestController
//@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost/" })
public class QuestionController {

	@Autowired
	private QuestionService questionService;

	@GetMapping("questions")
	public List<Question> index(HttpServletRequest req, HttpServletResponse res) {

		return questionService.questionList();
	}

	@GetMapping("api/questions/{qid}")
	public Question show(HttpServletRequest req, HttpServletResponse res, @PathVariable int qid, Principal principal) {
		// Question questions = questionsService.show(principal.getName(), qid);
		Question questions = questionService.show(qid);
		if (questions == null) {
			res.setStatus(404);
		}
		return questions;
	}
	
	@PostMapping("api/questions")
	public Question create(HttpServletRequest req, HttpServletResponse res, @RequestBody Question question, Principal principal) {
		try {
			if (question == null) {
				res.setStatus(401);
				return question;
			}
			question = questionService.create(principal.getName(), question);
			//question = questionService.create(question);
			res.setStatus(201);
			StringBuffer url = req.getRequestURL();
			url.append("/").append(question.getId());
			res.setHeader("location", url.toString());
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			question = null;
		}
		return question;
	}
	
	@PutMapping("api/questions/{qid}")
	public Question update(HttpServletRequest req, HttpServletResponse res, @PathVariable int qid, @RequestBody Question question, Principal principal) {
		try {
			question = questionService.update(principal.getName(), qid, question);
			if (question == null) {
				res.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			question = null;
		}
		System.out.println("*************************");
		System.out.println(question.getCategories());
		System.out.println("*************************");
		return question;
	}
	
	@DeleteMapping("api/questions/{qid}")
	public void destroy(HttpServletRequest req, HttpServletResponse res, @PathVariable int qid, Principal principal) {
		try {
			if (questionService.delete(principal.getName(), qid)) {
			//if (questionService.delete(qid)) {
				res.setStatus(204);
			} else {
				res.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
	}
}

