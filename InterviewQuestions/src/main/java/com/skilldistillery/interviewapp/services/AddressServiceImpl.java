package com.skilldistillery.interviewapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.interviewapp.entities.Address;
import com.skilldistillery.interviewapp.repositories.AddressRepository;

@Service
public class AddressServiceImpl implements AddressService {
	
	@Autowired
	private AddressRepository addressRepo;

	@Override
	public List<Address> addressList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Address show(int aId) {
		
		return addressRepo.findById(aId);
	}

	@Override
	public Address create(Address address) {
		return addressRepo.saveAndFlush(address);
	}

	@Override
	public Address update(Address address, int aId) {
		Address managed = show(aId);
		managed.setStreet(address.getStreet());
		managed.setStreet2(address.getStreet2());
		managed.setCity(address.getCity());
		managed.setState(address.getState());
		managed.setZip(address.getZip());
		managed.setEnabled(true);
		managed.setCompany(address.getCompany());
		managed.setJob(address.getJob());
		
		return addressRepo.save(managed);
	}

	@Override
	public boolean delete(int aId) {
		// TODO Auto-generated method stub
		return false;
	}

}
