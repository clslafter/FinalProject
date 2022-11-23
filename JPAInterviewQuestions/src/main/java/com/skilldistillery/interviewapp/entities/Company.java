package com.skilldistillery.interviewapp.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Company {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;

	private String description;

	@Column(name = "logo_url")
	private String logoURL;

	@OneToOne
	@JoinColumn(name = "address_id")
	private Address address;

	private boolean enabled;

	@ManyToMany
	@JoinTable(name = "company_has_question", joinColumns = @JoinColumn(name = "company_id"), inverseJoinColumns = @JoinColumn(name = "question_id"))
	private List<Question> questions;

	@ManyToMany
	@JoinTable(name = "industry_has_company", joinColumns = @JoinColumn(name = "industry_id"), inverseJoinColumns = @JoinColumn(name = "company_id"))
	private List<Industry> industries;

	@OneToMany(mappedBy = "user")
	private List<JobOpening> jobs;

	public Company() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLogoURL() {
		return logoURL;
	}

	public void setLogoURL(String logoURL) {
		this.logoURL = logoURL;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public void addQuestion(Question question) {
		if (questions == null) {
			questions = new ArrayList<>();
		}
		if (!questions.contains(question)) {
			questions.add(question);
			question.addCompany(this);
		}
	}

	public void removeQuestion(Question question) {
		if (questions != null && questions.contains(question)) {
			questions.remove(question);
			question.removeCompany(this);
		}
	}

	public List<Industry> getIndustries() {
		return industries;
	}

	public void setIndustries(List<Industry> industries) {
		this.industries = industries;
	}

	public void addIndustry(Industry industry) {
		if (industries == null) {
			industries = new ArrayList<>();
		}
		if (!industries.contains(industry)) {
			industries.add(industry);
			industry.addCompany(this);
		}
	}

	public void removeIndustry(Industry industry) {
		if (industries != null && industries.contains(industry)) {
			industries.remove(industry);
			industry.removeCompany(this);
		}
	}

	public List<JobOpening> getJobs() {
		return jobs;
	}

	public void setJobs(List<JobOpening> jobs) {
		this.jobs = jobs;
	}

	public void addJob(JobOpening job) {
		if (jobs == null) {
			jobs = new ArrayList<>();
		}
		if (!jobs.contains(job)) {
			jobs.add(job);
			if (job.getCompany() != null) {
				job.getCompany().getJobs().remove(job);
			}
			job.setCompany(this);
		}
	}

	public void removeJob(JobOpening job) {
		job.setCompany(null);
		if (jobs != null) {
			jobs.remove(job);
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Company other = (Company) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Company [id=" + id + ", name=" + name + ", description=" + description + ", logoURL=" + logoURL
				+ ", enabled=" + enabled + "]";
	}

}
