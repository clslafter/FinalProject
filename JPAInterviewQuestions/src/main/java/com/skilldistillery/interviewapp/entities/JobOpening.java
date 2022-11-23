package com.skilldistillery.interviewapp.entities;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "job_opening")
public class JobOpening {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String description;

	@Column(name = "url_post")
	private String urlPost;

	private Boolean enabled;

	@Column(name = "role_filled")
	private Boolean roleFilled;

	private LocalDateTime posted;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "company_id")
	private Company company;
	
	@OneToOne
	@JoinColumn(name = "address_id")
	private Address address;

	// METHODS
	public JobOpening() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrlPost() {
		return urlPost;
	}

	public void setUrlPost(String urlPost) {
		this.urlPost = urlPost;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Boolean getRoleFilled() {
		return roleFilled;
	}

	public void setRoleFilled(Boolean roleFilled) {
		this.roleFilled = roleFilled;
	}

	public LocalDateTime getPosted() {
		return posted;
	}

	public void setPosted(LocalDateTime posted) {
		this.posted = posted;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
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
		JobOpening other = (JobOpening) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "JobOpening [id=" + id + ", description=" + description + ", urlPost=" + urlPost + ", enabled=" + enabled
				+ ", roleFilled=" + roleFilled + ", posted=" + posted + "]";
	}

}
