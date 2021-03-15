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
	public List<Doctor> getDoctorbyDistanceAndHealthProblem(double user_latitude, double user_longitude, double search_distance, String healthProblem) {
		List<Doctor> doctors = new ArrayList<>();
		try {
			conn = DBConnection.getConnection();
			String health_problem_database = null;
			
			switch (healthProblem) {
			case "flu fever", "headache", "abdominal pain", "body aches", "other": health_problem_database = "General medicine"; System.out.println("flu taken!"); break;
			case "eye pain": health_problem_database = "Ophthalmology"; break;
			case "can't breath": health_problem_database = "Pulmonology"; break;
			case "toothache": health_problem_database = "Odontology"; break;
			case "ear pain", "mouth lesion", "sore throat": health_problem_database = "Otorhinolaryngology"; break;
			case "arm pain", "neck pain","chest pain", "back pain", "leg pain", "joint pain": health_problem_database = "Surgery"; break;
			case "stomach-ache" : health_problem_database = "Endocrinology"; break;
			case "skin issue" : health_problem_database = "Dermatoloy"; break;
			case "psychological problem" : health_problem_database = "Psychology"; break;
			case "pregnancy" : health_problem_database = "Obstetrics"; break;
			case "gynecology" : health_problem_database = "Gynecology"; break;
			case "andrology" : health_problem_database = "Andrology"; break;
			case "neurology" : health_problem_database = "Neurology"; break;
			default:
				break;
			}
			ps = conn.prepareStatement("SELECT doctor_id ,firstName, lastName, doctor_address, specialization, SQRT(\n"
									 + "POW(69.1 * (latitude - ?), 2) +\n"
									 + "POW(69.1 * (? - longitude) * COS(latitude / 57.3), 2)) AS distance\n"
									 + "FROM doctors WHERE specialization = ? HAVING distance < ? ORDER BY distance;");
			ps.setDouble(1, user_latitude);
			ps.setDouble(2, user_longitude);
			ps.setString(3, health_problem_database);
			ps.setDouble(4, search_distance);
			ResultSet results = ps.executeQuery();
			while (results.next()) {
				Doctor doctor = new Doctor();
				doctor.setDoctorID(results.getInt(1));
				doctor.setFirstName(results.getString(2));
				doctor.setLastName(results.getString(3));
				doctor.setAddress((results.getString(4)));
				doctor.setSpecialization((results.getString(5)));
				doctor.setDistanceToUser((float) Math.round(results.getDouble(6)*100)/100);
				doctors.add(doctor);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return doctors;
	}
	
	@Override
	public List<Doctor> getAllDoctors(double user_latitude, double user_longitude, String healthProblem) {
		List<Doctor> doctors = new ArrayList<>();
		try {
			conn = DBConnection.getConnection();
			
			String health_problem_database = null;
			
			switch (healthProblem) {
			case "flu fever", "headache", "abdominal pain", "body aches", "other": health_problem_database = "General medicine"; System.out.println("flu taken!"); break;
			case "eye pain": health_problem_database = "Ophthalmology"; break;
			case "can't breath": health_problem_database = "Pulmonology"; break;
			case "toothache": health_problem_database = "Odontology"; break;
			case "ear pain", "mouth lesion", "sore throat": health_problem_database = "Otorhinolaryngology"; break;
			case "arm pain", "neck pain","chest pain", "back pain", "leg pain", "joint pain": health_problem_database = "Surgery"; break;
			case "stomach-ache" : health_problem_database = "Endocrinology"; break;
			case "skin issue" : health_problem_database = "Dermatoloy"; break;
			case "psychological problem" : health_problem_database = "Psychology"; break;
			case "pregnancy" : health_problem_database = "Obstetrics"; break;
			case "gynecology" : health_problem_database = "Gynecology"; break;
			case "andrology" : health_problem_database = "Andrology"; break;
			case "neurology" : health_problem_database = "Neurology"; break;
			default:
				break;
			}
			
			ps = conn.prepareStatement("SELECT doctor_id, firstName, lastName, doctor_address, specialization, SQRT(\n"
									 + "POW(69.1 * (latitude - ?), 2) +\n"
									 + "POW(69.1 * (? - longitude) * COS(latitude / 57.3), 2)) AS distance\n"
									 + "FROM doctors WHERE specialization = ? ORDER BY distance;");
			ps.setDouble(1, user_latitude);
			ps.setDouble(2, user_longitude);
			ps.setString(3, health_problem_database);
			ResultSet results = ps.executeQuery();
			while (results.next()) {
				Doctor doctor = new Doctor();
				doctor.setDoctorID(results.getInt(1));
				doctor.setFirstName(results.getString(2));
				doctor.setLastName(results.getString(3));
				doctor.setAddress((results.getString(4)));
				doctor.setSpecialization((results.getString(5)));
				doctor.setDistanceToUser((float) Math.round(results.getDouble(6)*100)/100);
				doctors.add(doctor);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return doctors;
	}
	public Doctor getDoctorByID(int doctor_id, double user_latitude, double user_longitude) {
		Doctor doctor = new Doctor();
		try {
			conn = DBConnection.getConnection();
			ps = conn.prepareStatement("SELECT doctor_id, firstName, lastName, doctor_address, specialization, SQRT(\n"
					 + "POW(69.1 * (latitude - ?), 2) +\n"
					 + "POW(69.1 * (? - longitude) * COS(latitude / 57.3), 2)) AS distance\n"
					 + "FROM doctors WHERE doctor_id = ?");
			ps.setDouble(1, user_latitude);
			ps.setDouble(2, user_longitude);
			ps.setInt(3, doctor_id);
			ResultSet results = ps.executeQuery();
			while (results.next()) {
				doctor.setDoctorID(results.getInt(1));
				doctor.setFirstName(results.getString(2));
				doctor.setLastName(results.getString(3));
				doctor.setAddress((results.getString(4)));
				doctor.setSpecialization((results.getString(5)));
				doctor.setDistanceToUser((float) Math.round(results.getDouble(6)*100)/100);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return doctor;
	}
	

}
