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

class UserTest {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private User user;
	private User user2;

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
		user = em.find(User.class, 1);
		
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		user = null;
	}

	@Test
	void test_User_entity_mapping() {
		assertNotNull(user);
		assertEquals("admin", user.getUsername());
		assertEquals("admin", user.getFirstName());
	}
	
	@Test
	void test_User_mapping_to_Address() {
		assertNotNull(user);
		assertEquals("Denver", user.getAddress().getCity());
	}
	
	@Test
	void test_user_mapping_to_Question() {
		assertNotNull(user);
		assertTrue(user.getQuestions().size() > 0);
	}
	
	@Test
	void test_user_mapping_answer() {
		user2 = em.find(User.class, 2);
		assertNotNull(user2);
		assertTrue(user2.getAnswers().size() > 0);
	}
	
	@Test
	void test_user_mapping_job() {
		assertNotNull(user);
		assertTrue(user.getJobs().size() > 0);
	}

}
