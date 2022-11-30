package com.skilldistillery.interviewapp.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.interviewapp.entities.AnswerRating;
import com.skilldistillery.interviewapp.services.AnswerRatingService;

@RestController
@CrossOrigin({ "*", "http://localhost/" })
public class AnswerRatingController {
	
	@Autowired
	private AnswerRatingService answerRatingService;
	
	@GetMapping("api/answerrating/{answerId}")
	public List <AnswerRating> findRatingsOnQuestion(@PathVariable int answerId, Principal principal, 
			HttpServletRequest req, HttpServletResponse res){
		return answerRatingService.answerRatingsList(answerId);
	}
	
	@PutMapping("api/answerrating/{answerId}")
	public AnswerRating upvoteOrDownvoteQuestion(Principal principal, @PathVariable int answerId, @RequestBody AnswerRating answerRating, 
			HttpServletResponse res, HttpServletRequest req ) {
			answerRating = answerRatingService.answerRatingUpVote(answerRating.getUpvote(), answerId, principal.getName());
			try {
				if(answerRating == null) {
					res.setStatus(404);
				}
			} catch (Exception e) {
				e.printStackTrace();
				res.setStatus(400);
				answerRating = null;
			}
			
		return answerRating;
		
	}
}
