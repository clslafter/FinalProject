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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Answer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "date_created")
	private LocalDateTime dateCreated;

	@Column(name = "date_updated")
	private LocalDateTime dateUpdated;

	private boolean enabled;

	private String answer;

//	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@JsonIgnoreProperties({"answers", "categories", "companies", "user"})
	@ManyToOne
	@JoinColumn(name = "question_id")
	private Question question;
	
	@JsonIgnore
	@OneToMany(mappedBy = "answer")
	private List<AnswerRating> ratings;

	public Answer() {
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

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public List<AnswerRating> getRatings() {
		return ratings;
	}

	public void setRatings(List<AnswerRating> ratings) {
		this.ratings = ratings;
	}

	public void addRating(AnswerRating rating) {
		if (ratings == null) {
			ratings = new ArrayList<>();
		}
		if (!ratings.contains(rating)) {
			ratings.add(rating);
			if (rating.getAnswer() != null) {
				rating.getAnswer().getRatings().remove(rating);
			}
			rating.setAnswer(this);
		}
	}

	public void removeQuestion(AnswerRating rating) {
		rating.setAnswer(null);
		if (ratings != null) {
			ratings.remove(rating);
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
		Answer other = (Answer) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Answer [id=" + id + ", dateCreated=" + dateCreated + ", dateUpdated=" + dateUpdated + ", enabled="
				+ enabled + ", answer=" + answer + ", user=" + user + ", question=" + question + "]";
	}

}
