package com.ehealth.models;

public class Doctor {
	private int DoctorID;
	private String firstName;
	private String lastName;
	private String address;
	private String specialization;
	private float distanceToUser;
	public int getDoctorID() {
		return DoctorID;
	}
	public void setDoctorID(int doctorID) {
		DoctorID = doctorID;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getSpecialization() {
		return specialization;
	}
	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}
	public float getDistanceToUser() {
		return distanceToUser;
	}
	public void setDistanceToUser(float distanceToUser) {
		this.distanceToUser = distanceToUser;
	}
}
