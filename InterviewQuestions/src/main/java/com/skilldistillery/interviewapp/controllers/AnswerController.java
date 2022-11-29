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

import com.skilldistillery.interviewapp.entities.Answer;
import com.skilldistillery.interviewapp.entities.Question;
import com.skilldistillery.interviewapp.services.AnswerService;
import com.skilldistillery.interviewapp.services.QuestionService;

@RestController
@CrossOrigin({ "*", "http://localhost/" })
public class AnswerController {

	@Autowired
	private AnswerService answerService;

	@Autowired
	private QuestionService questionService;

	@GetMapping("api/answers")
	public List<Answer> index(HttpServletRequest req, HttpServletResponse res) {
		return answerService.answerList();
	}

	@PostMapping("api/answers")
	public Answer create(HttpServletRequest req, HttpServletResponse res, @RequestBody Answer answer, Principal principal) {
		try {
			if (answer == null) {
				res.setStatus(401);
				return answer;
			}
			answer = answerService.create(principal.getName(), answer);
			res.setStatus(201);
			StringBuffer url = req.getRequestURL();
			url.append("/").append(answer.getId());
			res.setHeader("location", url.toString());
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			answer = null;
		}
		return answer;
	}

	@PutMapping("api/answers/{aid}")
	public Answer update(HttpServletResponse res, HttpServletRequest req, @PathVariable int aid,
			@RequestBody Answer answer, Principal principal) {
		answer = answerService.update(answer, aid, principal.getName());
		try {
			if (answer == null) {
				res.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			answer = null;
		}

		return answer;
	}

	@DeleteMapping("api/answers/{aid}")
	public void destroy(HttpServletRequest req, HttpServletResponse res, @PathVariable int aid, Principal principal) {
		try {
			if (answerService.delete(principal.getName(), aid)) {
				res.setStatus(204);
			} else {
				res.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
	}

	@GetMapping("api/answers/{aid}/questions/{qid}")
	public List<Answer> questionsAnswer(HttpServletRequest req, HttpServletResponse res, @PathVariable int aid,
			@PathVariable int qid, Principal principal) {
		Question question = questionService.show(qid);
		List<Answer> answers = answerService.questionAnswers(aid);
		if (question == null | answers == null) {
			res.setStatus(404);
		}
		return answers;
	}

}
