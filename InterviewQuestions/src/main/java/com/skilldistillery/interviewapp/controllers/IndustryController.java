package com.skilldistillery.interviewapp.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.interviewapp.entities.Industry;
import com.skilldistillery.interviewapp.services.IndustryService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost/" })
public class IndustryController {
	
	@Autowired
	private IndustryService industryService;

	@GetMapping("industries")
	public List<Industry> index(HttpServletRequest req, HttpServletResponse res) {

		return industryService.industryList();
	}

}
