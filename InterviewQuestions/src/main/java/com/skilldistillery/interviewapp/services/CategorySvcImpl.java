package com.skilldistillery.interviewapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.interviewapp.entities.Category;
import com.skilldistillery.interviewapp.repositories.CategoryRepository;

@Service
public class CategorySvcImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepo;
	
	@Override
	public List<Category> categoryList() {
		return categoryRepo.findAll();
	}
}
