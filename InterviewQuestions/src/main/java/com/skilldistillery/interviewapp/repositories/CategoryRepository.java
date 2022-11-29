package com.skilldistillery.interviewapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.interviewapp.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{
	
	Category queryById(int catId);

}
