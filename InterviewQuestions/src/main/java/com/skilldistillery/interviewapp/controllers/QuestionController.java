package com.skilldistillery.interviewapp.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.interviewapp.entities.Question;
import com.skilldistillery.interviewapp.services.QuestionService;

@RestController
//@RequestMapping("api")
@CrossOrigin({"*", "http://localhost/"})
public class QuestionController {

	@Autowired
	private QuestionService questionService;
	
//  GET questions
	@GetMapping("questions")
public List<Question> index(HttpServletRequest req, HttpServletResponse res) { 
		
	return questionService.questionList(); 
}

	
//	public List<Question> index(HttpServletRequest req, HttpServletResponse res, Principal principal) { 
//		//return todoService.index(username); 
//		return todoService.index(principal.getName()); 
//	}
	
	
}
