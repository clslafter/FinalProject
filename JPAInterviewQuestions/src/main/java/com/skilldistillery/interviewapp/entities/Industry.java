package com.skilldistillery.interviewapp.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Industry {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;

	private boolean enabled;

	@ManyToMany(mappedBy = "industries")
	private List<Company> companies;

	public Industry() {
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

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
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
			company.addIndustry(this);
		}
	}

	public void removeCompany(Company company) {
		if (companies != null && companies.contains(company)) {
			companies.remove(company);
			company.removeIndustry(this);
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
		Industry other = (Industry) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Industry [id=" + id + ", name=" + name + ", enabled=" + enabled + "]";
	}

}
