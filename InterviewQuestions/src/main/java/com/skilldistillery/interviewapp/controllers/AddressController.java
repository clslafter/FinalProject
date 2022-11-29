package com.skilldistillery.interviewapp.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.interviewapp.entities.Address;
import com.skilldistillery.interviewapp.services.AddressService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost/" })
public class AddressController {
	
	@Autowired
	private AddressService addressService;
	
	@GetMapping("addresses/{id}")
	public Address getAddress (@PathVariable int id, HttpServletResponse res) {
		Address address = addressService.show(id);
		if(address == null) {
			res.setStatus(404);
		}
		
		return address;
	}
	
	@PostMapping("addresses")
	public Address createNewAddress (@RequestBody Address address, HttpServletResponse res, HttpServletRequest req) {
		try {
			addressService.create(address);
			res.setStatus(201);
			StringBuffer url = req.getRequestURL();
			url.append("/").append(address.getId());
			res.setHeader("Location", url.toString());
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
		return address;
	}
	
	@PutMapping ("addresses/{id}")
	public Address updateAddress (@PathVariable int id, @RequestBody Address address, HttpServletResponse res) {
		try {
			address = addressService.update(address, id);
			if (address == null) {
				res.setStatus(404);
			}
		} catch (Exception e) {

			e.printStackTrace();
			res.setStatus(404);
			address = null;
		}		
		return address;
	}

}
