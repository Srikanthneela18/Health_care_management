package com.dev.healthcare_management.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Doctor {
    private int doctorId;
    private String name;
    private String specialization;
    private String contactNumber;
    private String email;

	public int getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}
	public String getSpecialization() {
		return specialization;
	}
	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name2) {
		this.name = name2;
	}
}
