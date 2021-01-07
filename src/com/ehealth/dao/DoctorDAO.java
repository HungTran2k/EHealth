package com.ehealth.dao;

import java.util.List;

import com.ehealth.models.Doctor;

public interface DoctorDAO {
	public List<Doctor> getDoctorbyDistance(double user_latitude, double user_longitude, double search_distance);
	public List<Doctor> getAllDoctors(double user_latitude, double user_longitude);
}
