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

class CompanyTest {

private static EntityManagerFactory emf;
	
	private EntityManager em;
	
	private Company company;
	
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
		company = em.find(Company.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		company = null;
	}

	@Test
	void test_Company_entity_mapping() {
		assertNotNull(company);
		assertEquals("CEC",company.getName());
	}
	
	@Test
	void test_Company_mapping_Address() {
		assertNotNull(company);
		assertEquals("Denver", company.getAddress().getCity());
	}
	
	@Test
	void test_Company_mapping_Question() {
		assertNotNull(company);
		assertTrue(company.getQuestions().size() > 0);
	}
	
	@Test
	void test_Company_mapping_Industry() {
		assertNotNull(company);
		assertTrue(company.getIndustries().size() > 0);
	}
	
	@Test
	void test_Company_mapping_job() {
		assertNotNull(company);
		assertTrue(company.getJobs().size() > 0);
	}

}
