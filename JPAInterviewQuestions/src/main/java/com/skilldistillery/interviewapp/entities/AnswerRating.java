package com.skilldistillery.interviewapp.entities;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "answer_rating")
@Entity
public class AnswerRating {

	@EmbeddedId
	private AnswerRatingId id;
	
	private Boolean upvote;
	
	@Column(name="rating_date")
	private LocalDateTime ratingDate;

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
