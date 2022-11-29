package com.skilldistillery.interviewapp.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	private Boolean enabled;

	private String role;

	private String username;

	private String password;

	private String email;

	@Column(name = "about_me")
	private String aboutMe;

	@CreationTimestamp
	@Column(name = "date_created")
	private LocalDate dateCreated;

	@Column(name = "avatar_url")
	private String avatarUrl;

	@OneToOne
	@JoinColumn(name = "address_id")
	private Address address;

	@JsonIgnoreProperties({"user"})
	@OneToMany(mappedBy = "user")
	private List<Question> questions;

	@JsonIgnoreProperties({"user"})
	@OneToMany(mappedBy = "user")
	private List<Answer> answers;

	@JsonIgnore
	@OneToMany(mappedBy = "user")
	private List<JobOpening> jobs;

	public User() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAboutMe() {
		return aboutMe;
	}

	public void setAboutMe(String aboutMe) {
		this.aboutMe = aboutMe;
	}

	public LocalDate getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(LocalDate dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
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
			if (answer.getUser() != null) {
				answer.getUser().getAnswers().remove(answer);
			}
			answer.setUser(this);
		}
	}

	public void removeAnswer(Answer answer) {
		answer.setUser(null);
		if (answers != null) {
			answers.remove(answer);
		}
	}

	public void addQuestion(Question question) {
		if (questions == null) {
			questions = new ArrayList<>();
		}
		if (!questions.contains(question)) {
			questions.add(question);
			if (question.getUser() != null) {
				question.getUser().getQuestions().remove(question);
			}
			question.setUser(this);
		}
	}

	public void removeQuestion(Question question) {
		question.setUser(null);
		if (questions != null) {
			questions.remove(question);
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
			if (job.getUser() != null) {
				job.getUser().getJobs().remove(job);
			}
			job.setUser(this);
		}
	}

	public void removeJob(JobOpening job) {
		job.setUser(null);
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
		User other = (User) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", enabled=" + enabled
				+ ", role=" + role + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", aboutMe=" + aboutMe + ", dateCreated=" + dateCreated + ", avatarUrl=" + avatarUrl + ", address="
				+ address + "]";
	}

}
