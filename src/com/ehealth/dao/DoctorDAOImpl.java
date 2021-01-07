package com.ehealth.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet; 
import java.util.ArrayList;
import java.util.List;

import com.ehealth.models.Doctor;

public class DoctorDAOImpl implements DoctorDAO {
	Connection conn;
	PreparedStatement ps;

	@Override
	public List<Doctor> getDoctorbyDistance(double user_latitude, double user_longitude, double search_distance) {
		List<Doctor> doctors = new ArrayList<>();
		try {
			conn = DBConnection.getConnection();
			ps = conn.prepareStatement("SELECT firstName, lastName, doctor_address, specialization, latitude, longitude, SQRT(\n"
									 + "POW(69.1 * (latitude - ?), 2) +\n"
									 + "POW(69.1 * (? - longitude) * COS(latitude / 57.3), 2)) AS distance\n"
									 + "FROM doctors HAVING distance < ? ORDER BY distance;");
			ps.setDouble(1, user_latitude);
			ps.setDouble(2, user_longitude);
			ps.setDouble(3, search_distance);
			
			ResultSet results = ps.executeQuery();
			while (results.next()) {
				Doctor doctor = new Doctor();
				doctor.setFirstName(results.getString(1));
				doctor.setLastName(results.getString(2));
				doctor.setAddress((results.getString(3)));
				doctor.setSpecialization((results.getString(4)));
				doctor.setDistanceToUser((float) Math.round(results.getFloat(7)*100)/100);
				doctors.add(doctor);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return doctors;
	}
	
	@Override
	public List<Doctor> getAllDoctors(double user_latitude, double user_longitude) {
		List<Doctor> doctors = new ArrayList<>();
		try {
			conn = DBConnection.getConnection();
			ps = conn.prepareStatement("SELECT firstName, lastName, doctor_address, specialization, latitude, longitude, SQRT(\n"
									 + "POW(69.1 * (latitude - ?), 2) +\n"
									 + "POW(69.1 * (? - longitude) * COS(latitude / 57.3), 2)) AS distance\n"
									 + "FROM doctors ORDER BY distance;");
			ps.setDouble(1, user_latitude);
			ps.setDouble(2, user_longitude);
			
			ResultSet results = ps.executeQuery();
			while (results.next()) {
				Doctor doctor = new Doctor();
				doctor.setFirstName(results.getString(1));
				doctor.setLastName(results.getString(2));
				doctor.setAddress((results.getString(3)));
				doctor.setSpecialization((results.getString(4)));
				doctor.setDistanceToUser((float) Math.round(results.getFloat(7)*100)/100);
				doctors.add(doctor);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return doctors;
	}

}
