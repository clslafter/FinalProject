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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.interviewapp.entities.AnswerComment;
import com.skilldistillery.interviewapp.entities.Company;
import com.skilldistillery.interviewapp.services.AnswerCommentService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost/" })
public class AnswerCommentController {

	@Autowired
	private AnswerCommentService answerCommentService;

	@GetMapping("comments")
	public List<AnswerComment> index(HttpServletRequest req, HttpServletResponse res, Principal principal) {
		return answerCommentService.index();
		
	}

	@GetMapping("comments/{cid}")
	public AnswerComment show(HttpServletRequest req, HttpServletResponse res, @PathVariable int cid, Principal principal) {
		AnswerComment answerComment = answerCommentService.show(cid);
		if (answerComment == null) {
			res.setStatus(404);
		}
		return answerComment;
	}

	@PostMapping("comments")
	public AnswerComment create(HttpServletRequest req, HttpServletResponse res, @RequestBody AnswerComment comment,
			Principal principal) {
		try {
			if (comment == null) {
				res.setStatus(401);
				return comment;
			}
			comment = answerCommentService.create(comment);
			res.setStatus(201);
			StringBuffer url = req.getRequestURL();
			url.append("/").append(comment.getId());
			res.setHeader("location", url.toString());
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			comment = null;
		}
		return comment;
	}

	@PutMapping("comments/{cid}")
	public AnswerComment update(HttpServletRequest req, HttpServletResponse res, @PathVariable int cid,
			@RequestBody AnswerComment comment, Principal principal) {
		try {
			comment = answerCommentService.update(cid, comment);
			if (comment == null) {
				res.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			comment = null;
		}
		return comment;
	}

	@DeleteMapping("comments/{cid}")
	public void destroy(HttpServletRequest req, HttpServletResponse res, @PathVariable int cid, Principal principal) {
		try {
			if (answerCommentService.delete(cid)) {
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
