package com.skilldistillery.interviewapp.entities;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AnswerCommentTest {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private AnswerComment answerComment;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("JPAInterviewQuestions");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		emf.close();
	}

	@BeforeEach
	void setUp() throws Exception {
		em = emf.createEntityManager();
		answerComment = em.find(AnswerComment.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		answerComment = null;
	}

	@Test
	void test_AnswerComment_entity_mapping() {
		assertNotNull(answerComment);
		assertEquals("thanks!", answerComment.getCommentText());
	}
	
	@Test
	void test_AnswerComment_to_user_mapping() {
		assertNotNull(answerComment);
		assertEquals("admin", answerComment.getUser().getFirstName());
	}
	
	@Test
	void test_AnswerComment_to_answer_mapping() {
		assertNotNull(answerComment);
		assertEquals("You can start a tech school or get hired by an electrical company", answerComment.getAnswer().getAnswer());
	}
}
