package com.skilldistillery.interviewapp.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.interviewapp.entities.Company;
import com.skilldistillery.interviewapp.services.CompanyService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost/" })
public class CompanyController {

	@Autowired
	private CompanyService companyService;

	@GetMapping("companies")
	public List<Company> index(HttpServletRequest req, HttpServletResponse res, Principal principal) {
		return companyService.index();
		// return companyService.index(principal.getName());
	}

	@GetMapping("companies/{cid}")
	public Company show(HttpServletRequest req, HttpServletResponse res, @PathVariable int cid, Principal principal) {
		// Company company = companyService.show(principal.getName(), cid);
		Company company = companyService.show(cid);
		if (company == null) {
			res.setStatus(404);
		}
		return company;
	}

	@PostMapping("companies")
	public Company create(HttpServletRequest req, HttpServletResponse res, @RequestBody Company company, Principal principal) {
		try {
			if (company == null) {
				res.setStatus(401);
				return company;
			}
			// company = companyService.create(principal.getName(), company);
			company = companyService.create(company);
			res.setStatus(201);
			StringBuffer url = req.getRequestURL();
			url.append("/").append(company.getId());
			res.setHeader("location", url.toString());
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			company = null;
		}
		return company;
	}

	@PutMapping("companies/{cid}")
	public Company update(HttpServletRequest req, HttpServletResponse res, @PathVariable int cid, @RequestBody Company company,
			Principal principal) {
		try {
			// company = companyService.update(principal.getName(), cid, company);
			company = companyService.update(cid, company);
			if (company == null) {
				res.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			company = null;
		}
		return company;
	}

	@DeleteMapping("companies/{cid}")
	public void destroy(HttpServletRequest req, HttpServletResponse res, @PathVariable int cid, Principal principal) {
		try {
			// if (companyService.delete(principal.getName(), cid)) {
			if (companyService.delete(cid)) {
				res.setStatus(204);
			} else {
				res.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
	}
}
