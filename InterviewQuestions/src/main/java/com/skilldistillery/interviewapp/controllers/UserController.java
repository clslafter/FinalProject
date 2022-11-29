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

import com.skilldistillery.interviewapp.entities.Address;
import com.skilldistillery.interviewapp.entities.User;
import com.skilldistillery.interviewapp.services.UserService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost/" })
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("users")
	public List<User> index(HttpServletRequest req, HttpServletResponse res, Principal principal) {
		return userService.index();
		// return userService.index(principal.getName());
	}

	@GetMapping("users/{uid}")
	public User show(HttpServletRequest req, HttpServletResponse res, @PathVariable int uid, Principal principal) {
		// User user = userService.show(principal.getName(), uid);
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
			// user = userService.create(principal.getName(), user);
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

	//change to principle rather than user id. Also change the user service impl
	@PutMapping("users")
	public User update(HttpServletRequest req, HttpServletResponse res, @RequestBody User user,
			Principal principal) {
		try {
			// user = userService.update(principal.getName(), uid, user);
			user = userService.update(principal.getName(), user);
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
	
	
	@PutMapping("users/addresses/{aid}")
	public User addAddress(HttpServletRequest req, HttpServletResponse res, @PathVariable int aid,
			Principal principal) {
		User user = new User();
		try {
			// user = userService.update(principal.getName(), uid, user);
			user = userService.addAddress(principal.getName(), aid);
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

	@DeleteMapping("users/{uid}")
	public void destroy(HttpServletRequest req, HttpServletResponse res, @PathVariable int uid, Principal principal) {
		try {
			// if (userService.delete(principal.getName(), uid)) {
			if (userService.delete(uid)) {
				res.setStatus(204);
			} else {
				res.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
	}
	
	@DeleteMapping("users/disable/{uid}")
	public void disable(HttpServletRequest req, HttpServletResponse res, @PathVariable int uid, Principal principal) {
		try {
			// if (userService.delete(principal.getName(), uid)) {
			if (userService.delete(uid)) {
				res.setStatus(204);
			} else {
				res.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
	}
	
	
	@PutMapping("users/enable/{uid}")
	public void enableUser(HttpServletRequest req, HttpServletResponse res, @PathVariable int uid, Principal principal) {
		try {
			// if (userService.delete(principal.getName(), uid)) {
			if (userService.enable(uid)) {
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
