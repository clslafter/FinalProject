package com.skilldistillery.interviewapp.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class AnswerRatingId implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Column(name = "answer_id")
	private int answerId;
	
	@Column(name = "user_id")
	private int userId;

	public AnswerRatingId(int answerId, int userId) {
		super();
		this.answerId = answerId;
		this.userId = userId;
	}

	public AnswerRatingId() {
		super();
	}

	@Override
	public int hashCode() {
		return Objects.hash(answerId, userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AnswerRatingId other = (AnswerRatingId) obj;
		return answerId == other.answerId && userId == other.userId;
	}
	
	
	
}
