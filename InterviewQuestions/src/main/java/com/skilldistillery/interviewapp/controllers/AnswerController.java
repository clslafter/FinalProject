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
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.interviewapp.entities.Answer;
import com.skilldistillery.interviewapp.services.AnswerService;

@RestController
@CrossOrigin({"*", "http://localhost/"})
public class AnswerController {
	
	@Autowired
	private AnswerService answerService;
	
	@GetMapping("answers")
	public List<Answer> index(HttpServletRequest req, HttpServletResponse res){
		return answerService.answerList();
	}
	
	@DeleteMapping("api/answers/{aid}")
	public void destroy(HttpServletRequest req, HttpServletResponse res, @PathVariable int aid, Principal principal) {
		try {
			if(answerService.delete(principal.getName(), aid)) {
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
