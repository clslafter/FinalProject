package com.skilldistillery.interviewapp.services;

import java.util.List;

import com.skilldistillery.interviewapp.entities.Industry;

public interface IndustryService {

	List<Industry> industryList();

	Industry show(int iid);

}
