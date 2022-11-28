package com.skilldistillery.interviewapp.services;

import java.util.List;

import com.skilldistillery.interviewapp.entities.Address;

public interface AddressService {
	
	List<Address> addressList();
	
	Address show(int aId);
	
	Address create(Address address);
	
	Address update(Address address , int aId);
	
	boolean delete(int aId);
	
	

}
