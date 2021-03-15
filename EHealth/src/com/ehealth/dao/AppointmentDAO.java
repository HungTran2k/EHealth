package com.ehealth.dao;

import com.ehealth.models.Appointment;

public interface AppointmentDAO {
	public int insertAppointmentToDB(String username, int doctor_id, Appointment appointment);
	public Appointment getAppointmentInfo(String username);
	public int deleteAppointment(String username);
	public int updateAppointment(String username, Appointment appointment);
	public boolean checkAppointmentExisted(String username);
}
