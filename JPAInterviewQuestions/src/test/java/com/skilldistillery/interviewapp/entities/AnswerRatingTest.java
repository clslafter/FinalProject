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

class AnswerRatingTest {

private static EntityManagerFactory emf;
	
	private EntityManager em;
	
	private AnswerRating answerRating;
	
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
		answerRating = em.find(AnswerRating.class, new AnswerRatingId(1,2));
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		answerRating = null;
	}

	@Test
	void test_answerRating_entity_mapping() {
		assertNotNull(answerRating);
		assertTrue(answerRating.getUpvote());
	}
	
	@Test
	void test_answerRating_mapping_user() {
		assertNotNull(answerRating);
		assertEquals("user", answerRating.getUser().getFirstName());
	}
	
	@Test
	void test_answerRating_mapping_answer() {
		assertNotNull(answerRating);
		assertEquals("You can start a tech school or get hired by an electrical company", answerRating.getAnswer().getAnswer());
	}
}
