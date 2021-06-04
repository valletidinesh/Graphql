package com.learn.modals;

import java.util.List;

public class Worker {
	private String personNumber;
	private String dateOfBirth;
	private List<WorkRelationships> workRelationships;

	
	public Worker(String personNumber, String dateOfBirth, List<WorkRelationships> workRelationships) {
		
		this.personNumber = personNumber;
		this.dateOfBirth = dateOfBirth;
		this.setWorkRelationships(workRelationships);
	}

	public String getPersonNumber() {
		return personNumber;
	}

	public void setPersonNumber(String personNumber) {
		this.personNumber = personNumber;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public List<WorkRelationships> getWorkRelationships() {
		return workRelationships;
	}

	public void setWorkRelationships(List<WorkRelationships> workRelationships) {
		this.workRelationships = workRelationships;
	}

	
}
