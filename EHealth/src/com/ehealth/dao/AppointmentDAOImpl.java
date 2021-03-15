package com.ehealth.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.ehealth.models.Appointment;

public class AppointmentDAOImpl implements AppointmentDAO {
	Connection conn;
	PreparedStatement ps;

	@Override
	public int insertAppointmentToDB(String username, int doctor_id, Appointment appointment) {
		int status = 0;
		try {
			conn = DBConnection.getConnection();
			ps = conn.prepareStatement("INSERT INTO appointments VALUES(?,?,?,?,?)");
			ps.setString(1, username);
			ps.setInt(2, doctor_id);
			ps.setString(3, appointment.getDate());
			ps.setString(4, appointment.getTime());
			ps.setString(5, appointment.getReminder_time());
			status = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}
		return status;
	}

	@Override
	public Appointment getAppointmentInfo(String username) {
		Appointment appointment = new Appointment();
		try {
			conn = DBConnection.getConnection();
			ps = conn.prepareStatement("SELECT * FROM appointments WHERE username = ?");
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				appointment.setDoctorID(rs.getInt(2));
				appointment.setDate(rs.getString(3));
				appointment.setTime(rs.getString(4));
				appointment.setReminder_time(rs.getString(5));
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return appointment;
	}
	@Override
	public int deleteAppointment(String username) {
		int status = 0;
		try {
			conn = DBConnection.getConnection();
			ps = conn.prepareStatement("DELETE FROM appointments WHERE username = ?");
			ps.setString(1, username);
			status = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}
		return status;
	}
	@Override
	public int updateAppointment(String username, Appointment appointment) {
		int status = 0;
		try {
			conn = DBConnection.getConnection();
			ps = conn.prepareStatement("UPDATE appointments SET date = ?, time = ?, reminder_time = ? WHERE username = ?");
			ps.setString(1, appointment.getDate());
			ps.setString(2, appointment.getTime());
			ps.setString(3, appointment.getReminder_time());
			ps.setString(4, username);
			status = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}
		return status;
	}

	@Override
	public boolean checkAppointmentExisted(String username) {
		boolean appointmentExisted = false;
		try {
			conn = DBConnection.getConnection();
			ps = conn.prepareStatement("SELECT * FROM appointments WHERE username = ?");
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				appointmentExisted = true;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return appointmentExisted;
	}
}
