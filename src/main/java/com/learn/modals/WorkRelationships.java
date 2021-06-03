package com.learn.modals;

public class WorkRelationships {
	private String workType;
	private String gradeCode;
	private String locationId;

	public String getWorkType() {
		return workType;
	}

	public WorkRelationships(String workType, String gradeCode, String locationId) {
		this.workType = workType;
		this.gradeCode = gradeCode;
		this.locationId = locationId;
	}

	public void setWorkType(String workType) {
		this.workType = workType;
	}

	public String getGradeCode() {
		return gradeCode;
	}

	public void setGradeCode(String gradeCode) {
		this.gradeCode = gradeCode;
	}

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	
}
