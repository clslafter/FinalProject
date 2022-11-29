package com.skilldistillery.interviewapp.services;

import java.util.List;

import com.skilldistillery.interviewapp.entities.JobOpening;

public interface JobOpeningService {
	
	List<JobOpening> index();

	public JobOpening show(int jid);

	public JobOpening create(JobOpening jobOpening);

	public JobOpening update(int uid, JobOpening jobOpening);

	boolean delete(int jid);

	
}
