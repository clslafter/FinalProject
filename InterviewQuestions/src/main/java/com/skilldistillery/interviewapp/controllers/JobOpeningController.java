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
import com.skilldistillery.interviewapp.entities.JobOpening;
import com.skilldistillery.interviewapp.services.JobOpeningService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost/" })
public class JobOpeningController {

	@Autowired
	private JobOpeningService jobService;

	@GetMapping("jobOpenings")
	public List<JobOpening> index(HttpServletRequest req, HttpServletResponse res, Principal principal) {
		return jobService.index();
		// return jobService.index(principal.getName());
	}

	@GetMapping("jobOpenings/{jid}")
	public JobOpening show(HttpServletRequest req, HttpServletResponse res, @PathVariable int jid, Principal principal) {
		// JobOpening jobOpening = jobService.show(principal.getName(), jid);
		JobOpening jobOpening = jobService.show(jid);
		if (jobOpening == null) {
			res.setStatus(404);
		}
		return jobOpening;
	}

	@PostMapping("jobOpenings")
	public JobOpening create(HttpServletRequest req, HttpServletResponse res, @RequestBody JobOpening jobOpening, Principal principal) {
		try {
			if (jobOpening == null) {
				res.setStatus(401);
				return jobOpening;
			}
			// jobOpening = jobOpeningService.create(principal.getName(), jobOpening);
			jobOpening = jobService.create(jobOpening);
			res.setStatus(201);
			StringBuffer url = req.getRequestURL();
			url.append("/").append(jobOpening.getId());
			res.setHeader("location", url.toString());
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			jobOpening = null;
		}
		return jobOpening;
	}

	@PutMapping("jobOpenings/{jid}")
	public JobOpening update(HttpServletRequest req, HttpServletResponse res, @PathVariable int jid, @RequestBody JobOpening jobOpening,
			Principal principal) {
		try {
			// jobOpening = jobOpeningService.update(principal.getName(), jid, jobOpening);
			jobOpening = jobService.update(jid, jobOpening);
			if (jobOpening == null) {
				res.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			jobOpening = null;
		}
		return jobOpening;
	}

	@DeleteMapping("jobOpenings/{jid}")
	public void destroy(HttpServletRequest req, HttpServletResponse res, @PathVariable int jid, Principal principal) {
		try {
			// if (jobService.delete(principal.getName(), jid)) {
			if (jobService.delete(jid)) {
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
