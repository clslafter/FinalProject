package com.skilldistillery.interviewapp.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AnswerTest {

	private static EntityManagerFactory emf;
	
	private EntityManager em;
	
	private Answer answer;
	
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
		answer = em.find(Answer.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		answer = null;
	}

	@Test
	void test_Answer_entity_mapping() {
		assertNotNull(answer);
		assertEquals("You can start a tech school or get hired by an electrical company",answer.getAnswer());
		assertTrue(answer.isEnabled());
	}
	
	@Test
	void test_Answer_mapping_Question() {
		assertNotNull(answer);
		assertEquals("How do I start my apprenticeship?", answer.getQuestion().getQuestion());
	}
	
	@Test
	void test_Answer_mapping_User() {
		assertNotNull(answer);
		assertEquals("user", answer.getUser().getFirstName());
	}
	
	@Test
	void test_Answer_mapping_AnswerRating() {
		assertNotNull(answer);
		assertTrue(answer.getRatings().size() > 0);
	}


}
