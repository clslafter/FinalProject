package com.skilldistillery.interviewapp.controllers;

import java.security.Principal;
import java.util.List;

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

import com.skilldistillery.interviewapp.entities.User;
import com.skilldistillery.interviewapp.services.UserService;

@RestController
@RequestMapping("api")
@CrossOrigin({"*", "http://localhost/"})
public class UserController {
	
	@Autowired
	private UserService userService;
	
	
	@GetMapping("users")
	public List<User> index(HttpServletRequest req, HttpServletResponse res, Principal principal) {
		return userService.index();
		//return userService.index(principal.getName());
	}

	@GetMapping("users/{uid}")
	public User show(HttpServletRequest req, HttpServletResponse res, @PathVariable int uid, Principal principal) {
		//User user = userService.show(principal.getName(), uid);
		User user = userService.show(uid);
		if (user == null) {
			res.setStatus(404);
		}
		return user;
	}
	
	@PostMapping("users")
	public User create(HttpServletRequest req, HttpServletResponse res, @RequestBody User user, Principal principal) { 
		try {
			if (user == null) {
				res.setStatus(401);
				return user;
			}
			//user = userService.create(principal.getName(), user);
			user = userService.create(user);
			res.setStatus(201);
			StringBuffer url = req.getRequestURL();
			url.append("/").append(user.getId());
			res.setHeader("location", url.toString());
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			user = null;
		}
		return user;
	}
	
	@PutMapping("users/{tid}")
	public User update(HttpServletRequest req, HttpServletResponse res, @PathVariable int uid, @RequestBody User user, Principal principal) {
		try {
			//user = userService.update(principal.getName(), uid, user);
			user = userService.update(uid, user);
			if (user == null) {
				res.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			user = null;
		}
		return user;
	}
	
}
