package com.ehealth.models;

public class Appointment {
	private int DoctorID;
	private String date;
	private String time;
	private String reminder_time;
	public int getDoctorID() {
		return DoctorID;
	}
	public void setDoctorID(int doctorID) {
		DoctorID = doctorID;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getReminder_time() {
		return reminder_time;
	}
	public void setReminder_time(String reminder_time) {
		this.reminder_time = reminder_time;
	}
}