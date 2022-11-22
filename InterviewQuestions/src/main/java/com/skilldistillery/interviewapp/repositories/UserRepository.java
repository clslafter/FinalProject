package com.skilldistillery.interviewapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.interviewapp.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	User findByUsername(String username);
}
