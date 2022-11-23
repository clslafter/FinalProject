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

class JobOpeningTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private JobOpening job;

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
		job = em.find(JobOpening.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		job = null;
	}

	@Test
	void test_JobOpening_entity_mapping() {
		assertNotNull(job);
		assertTrue(job.getRoleFilled() == null);
	}
	
	@Test
	void test_JobOpening_mapping_User() {
		assertNotNull(job);
		assertEquals("admin", job.getUser().getFirstName());
	}
	
	@Test
	void test_JobOpening_mapping_Company() {
		assertNotNull(job);
		assertEquals("CEC", job.getCompany().getName());
	}
	
	@Test
	void test_JobOpening_mapping_address() {
		assertNotNull(job);
		assertEquals("Denver", job.getAddress().getCity());
	}

}
