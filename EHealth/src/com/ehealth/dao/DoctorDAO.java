package com.ehealth.dao;

import java.util.List;

import com.ehealth.models.Doctor;

public interface DoctorDAO {
	public List<Doctor> getDoctorbyDistanceAndHealthProblem(double user_latitude, double user_longitude, double search_distance, String healthProblem);
	public List<Doctor> getAllDoctors(double user_latitude, double user_longitude, String healthProblem);
	public Doctor getDoctorByID(int doctor_id, double user_latitude, double user_longitude);
}
