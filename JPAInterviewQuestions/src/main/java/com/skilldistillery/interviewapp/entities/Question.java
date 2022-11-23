package com.skilldistillery.interviewapp.entities;

import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
public class Question {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "date_created")
	@CreationTimestamp
	private LocalDateTime dateCreated;

	@Column(name = "date_updated")
	@UpdateTimestamp
	private LocalDateTime dateUpdated;

	private String question;

	private Boolean enabled;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@OneToMany(mappedBy = "question")
	private List<Answer> answers;

	@ManyToMany(mappedBy = "questions")
	private List<Category> categories;

	@ManyToMany(mappedBy = "questions")
	private List<Company> companies;

	// METHODS

	public Question() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(LocalDateTime dateCreated) {
		this.dateCreated = dateCreated;
	}

	public LocalDateTime getDateUpdated() {
		return dateUpdated;
	}

	public void setDateUpdated(LocalDateTime dateUpdated) {
		this.dateUpdated = dateUpdated;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	public void addAnswer(Answer answer) {
		if (answers == null) {
			answers = new ArrayList<>();
		}
		if (!answers.contains(answer)) {
			answers.add(answer);
			if (answer.getQuestion() != null) {
				answer.getQuestion().getAnswers().remove(answer);
			}
			answer.setQuestion(this);
		}
	}

	public void removeAnswer(Answer answer) {
		answer.setQuestion(null);
		if (answers != null) {
			answers.remove(answer);
		}
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public void addCategory(Category category) {
		if (categories == null) {
			categories = new ArrayList<>();
		}
		if (!categories.contains(category)) {
			categories.add(category);
			category.addQuestion(this);
		}
	}

	public void removeCategory(Category category) {
		if (categories != null && categories.contains(category)) {
			categories.remove(category);
			category.removeQuestion(this);
		}
	}

	public List<Company> getCompanies() {
		return companies;
	}

	public void setCompanies(List<Company> companies) {
		this.companies = companies;
	}

	public void addCompany(Company company) {
		if (companies == null) {
			companies = new ArrayList<>();
		}
		if (!companies.contains(company)) {
			companies.add(company);
			company.addQuestion(this);
		}
	}

	public void removeCompany(Company company) {
		if (companies != null && companies.contains(company)) {
			companies.remove(company);
			company.removeQuestion(this);
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
		Question other = (Question) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Question [id=" + id + ", dateCreated=" + dateCreated + ", dateUpdated=" + dateUpdated + ", question="
				+ question + ", enabled=" + enabled + ", user=" + user + "]";
	}

}
