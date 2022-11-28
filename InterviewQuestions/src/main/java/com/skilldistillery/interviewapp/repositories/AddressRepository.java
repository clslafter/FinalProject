package com.skilldistillery.interviewapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.interviewapp.entities.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {
	
	Address findById(int aId);
	
	Address findByCompanyId(int cId);
	
	Address findByUserId(int uId);
	
	Address findByJobId(int jId);
	
}
