package com.skilldistillery.interviewapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.interviewapp.entities.AnswerComment;

public interface AnswerCommentRepository extends JpaRepository<AnswerComment, Integer> {
	
	AnswerComment findById(int aId);

	
}
