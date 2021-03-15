package com.ehealth.models;

public class User {
	private String username;
	private String email;
	private String password;
	private String firstName;
	private String lastName;
	private String date_of_birth;
	private String insuranceName;
	private String insuranceType; // true: public, false: private
	private String health_problem;
	private String health_information;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	public String getDate_of_birth() {
		return date_of_birth;
	}
	public void setDate_of_birth(String date_of_birth) {
		this.date_of_birth = date_of_birth;
	}
	public String getInsuranceName() {
		return insuranceName;
	}
	public void setInsuranceName(String insuranceName) {
		this.insuranceName = insuranceName;
	}
	public String getInsuranceType() {
		return insuranceType;
	}
	public void setInsuranceType(String insuranceType) {
		this.insuranceType = insuranceType;
	}
	public String getHealth_problem() {
		return health_problem;
	}
	public void setHealth_problem(String health_problem) {
		this.health_problem = health_problem;
	}
	public String getHealth_information() {
		return health_information;
	}
	public void setHealth_information(String health_information) {
		this.health_information = health_information;
	}
}
