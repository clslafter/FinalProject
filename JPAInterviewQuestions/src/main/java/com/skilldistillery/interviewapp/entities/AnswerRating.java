package com.skilldistillery.interviewapp.entities;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Table(name = "answer_rating")
@Entity
public class AnswerRating {

	@EmbeddedId
	private AnswerRatingId id;

	private Boolean upvote;
	
	@CreationTimestamp
	@Column(name = "rating_date")
	private LocalDateTime ratingDate;

	@ManyToOne
	@JoinColumn(name = "answer_id")
	@MapsId(value = "answerId")
	private Answer answer;

	@ManyToOne
	@JoinColumn(name = "user_id")
	@MapsId(value = "userId")
	private User user;

	public AnswerRating() {
		super();
	}

	public AnswerRatingId getId() {
		return id;
	}

	public void setId(AnswerRatingId id) {
		this.id = id;
	}

	public Boolean getUpvote() {
		return upvote;
	}

	public void setUpvote(Boolean upvote) {
		this.upvote = upvote;
	}

	public LocalDateTime getRatingDate() {
		return ratingDate;
	}

	public void setRatingDate(LocalDateTime ratingDate) {
		this.ratingDate = ratingDate;
	}

	public Answer getAnswer() {
		return answer;
	}

	public void setAnswer(Answer answer) {
		this.answer = answer;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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
		AnswerRating other = (AnswerRating) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "AnswerRating [id=" + id + ", upvote=" + upvote + ", ratingDate=" + ratingDate + "]";
	}

}
