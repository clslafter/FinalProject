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

class QuestionTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Question question;

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
		question = em.find(Question.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		question = null;
	}

	@Test
	void test_Question_entity_mapping() {
		assertNotNull(question);
		assertEquals("How do I start my apprenticeship?", question.getQuestion());
	}
	
	@Test
	void test_Question_mapping_to_User() {
		assertNotNull(question);
		assertEquals("admin", question.getUser().getUsername());
	}
	
	@Test
	void test_Question_mapping_Answer() {
		assertNotNull(question);
		assertTrue(question.getAnswers().size() > 0);
	}
	
	@Test
	void test_Question_mapping_Category() {
		assertNotNull(question);
		assertTrue(question.getCategories().size() > 0);
	}
	
	@Test
	void test_Question_mapping_Company() {
		assertNotNull(question);
		assertTrue(question.getCompanies().size() > 0);
	}

}
