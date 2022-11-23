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

class IndustryTest {

private static EntityManagerFactory emf;
	
	private EntityManager em;
	
	private Industry industry;
	
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
		industry = em.find(Industry.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		industry = null;
	}

	@Test
	void test_Industry_entity_mapping() {
		assertNotNull(industry);
		assertEquals("Construction",industry.getName());
	}
	
	@Test
	void test_Industry_mapping_Company() {
		assertNotNull(industry);
		assertTrue(industry.getCompanies().size() > 0);
	}
}
