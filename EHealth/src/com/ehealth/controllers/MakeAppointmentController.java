package com.ehealth.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

import com.ehealth.dao.AppointmentDAO;
import com.ehealth.dao.AppointmentDAOImpl;
import com.ehealth.models.Appointment;
import com.ehealth.models.Doctor;
import com.ehealth.models.EmailSender;
import com.ehealth.models.User;

/**
 * Servlet implementation class MakeAppointmentController
 */
@WebServlet("/make-appointment")
public class MakeAppointmentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MakeAppointmentController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//get user inputs
		String date = request.getParameter("datepicker");
		String time = request.getParameter("timepicker");
		String reminder_time = request.getParameter("reminder_time");
		System.out.println("this is date: " + date);
		System.out.println("this is reminder time: " + reminder_time);
		
		if (date.equals("")) {
			request.getRequestDispatcher("make-appointment.jsp").forward(request, response);
		} else {
			HttpSession session = request.getSession();
			//ger user and doctor info from session
			User u = (User) (session.getAttribute("user"));
			Doctor doctor = (Doctor)session.getAttribute("selected-doctor");
			//save appointment info to appointment
			Appointment appointment = new Appointment();
			appointment.setDate(date);
			appointment.setTime(time);
			appointment.setReminder_time(reminder_time);
			//insert appointment with user name and doctor id into to database
			AppointmentDAO appointmentDAO = new AppointmentDAOImpl();
			//insert appointment with user name and doctor id into to database
			if (!appointmentDAO.checkAppointmentExisted(u.getUsername())) {
				appointmentDAO.insertAppointmentToDB(u.getUsername(), doctor.getDoctorID(), appointment);
				Appointment a = appointmentDAO.getAppointmentInfo(u.getUsername());
				session.setAttribute("appointment", a);
				request.getRequestDispatcher("appointment.jsp").forward(request, response);
				//Send email
				String newline = System.getProperty("line.separator");
				String host = "smtp.gmail.com";
		        String port = "587";
		        String mailFrom = "ehealthjava@gmail.com";
		        String password = "abc123--";
		        String mailTo = u.getEmail();
		        String subject = "Appointment Confirmation";
		        String message = "Dear Mrs/Mr " + u.getLastName() + "," + newline + newline +
		        		"This is a confirmation email that you have made an appointment with:" + newline + newline +
		        		"Doctor: " + doctor.getFirstName() + " " + doctor.getLastName() + newline +
		        		"Address: " + doctor.getAddress() + newline +
		        		"Appointment day: " + appointment.getDate() + ", at " + appointment.getTime() + newline + newline +
		        		"Best regards" + newline +
		        		"EHealth Team";
		       
		        EmailSender mailer = new EmailSender();
		        try {
		            mailer.sendEmail(host, port, mailFrom, password, mailTo, subject, message);
		            System.out.println("Appointment Confirmation Email sent.");
		        } catch (Exception ex) {
		            System.out.println("Failed to sent Appointment Confirmation Email.");
		            ex.printStackTrace();
		        }
				
			//update appointment when user want to shift the appointment
			} else {
				Appointment previous_appointment = appointmentDAO.getAppointmentInfo(u.getUsername());
				appointmentDAO.updateAppointment(u.getUsername(), appointment);
				Appointment current_appointment = appointmentDAO.getAppointmentInfo(u.getUsername());
				session.setAttribute("appointment", current_appointment);
				request.getRequestDispatcher("appointment.jsp").forward(request, response);
				//Send email
				String newline = System.getProperty("line.separator");
				String host = "smtp.gmail.com";
		        String port = "587";
		        String mailFrom = "ehealthjava@gmail.com";
		        String password = "abc123--";
		        String mailTo = u.getEmail();
		        String subject = "Appointment Shifting";
		        String message = "Dear Mrs/Mr " + u.getLastName() + "," + newline + newline +
		        		"This is a confirmation email that you have changed the appointment with:" + newline + newline +
		        		"Doctor: " + doctor.getFirstName() + " " + doctor.getLastName() + newline +
		        		"Address: " + doctor.getAddress() + newline +
		        		"From appointment day: " + previous_appointment.getDate() + ", at " + previous_appointment.getTime() + newline +
		        		"To appointment day: " + current_appointment.getDate() + ", at " + current_appointment.getTime() + newline + newline +
		        		"Best regards" + newline +
		        		"EHealth Team";
		       
		        EmailSender mailer = new EmailSender();
		        try {
		            mailer.sendEmail(host, port, mailFrom, password, mailTo, subject, message);
		            System.out.println("Appointment Shifting Email sent.");
		        } catch (Exception ex) {
		            System.out.println("Failed to sent Appointment Shifting Email.");
		            ex.printStackTrace();
		        }
			}
			
			if (reminder_time.equals("None")) {
				
			} else {
			
			 	String appointment_date_string = date.concat(" " + time.concat(":00"));
			 	int reminder_time_in_seconds = Integer.valueOf(reminder_time);
			 	System.out.println(appointment_date_string);
		        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		        LocalDateTime appointment_date_LocalDateTime = LocalDateTime.parse(appointment_date_string, formatter);
		        
		        LocalDateTime reminder_date = appointment_date_LocalDateTime.minusSeconds(reminder_time_in_seconds);
		        System.out.println(reminder_date.format(formatter));
				
				Instant instant = reminder_date.atZone(ZoneId.systemDefault()).toInstant();
				Date reminder_date_Date = Date.from(instant);
				System.out.println(reminder_date_Date);
		        
		        Timer timer = new Timer();
		        TimerTask task = new TimerTask() {
					
					@Override
					public void run() {
						//Send email
						String newline = System.getProperty("line.separator");
						String host = "smtp.gmail.com";
				        String port = "587";
				        String mailFrom = "ehealthjava@gmail.com";
				        String password = "abc123--";
				        String mailTo = u.getEmail();
				        String subject = "Appointment Reminder";
				        String message = "Dear Mrs/Mr " + u.getLastName() + "," + newline + newline +
				        		"Don't forget that you will have an appointment with:" + newline + newline +
				        		"Doctor: " + doctor.getFirstName() + " " + doctor.getLastName() + newline +
				        		"Address: " + doctor.getAddress() + newline +
				        		"Appointment day: " + appointment.getDate() + ", at " + appointment.getTime() + newline + newline +
				        		"Best regards" + newline +
				        		"EHealth Team";
				       
				        EmailSender mailer = new EmailSender();
				        try {
				            mailer.sendEmail(host, port, mailFrom, password, mailTo, subject, message);
				            System.out.println("Appointment Reminder Email sent.");
				        } catch (Exception ex) {
				            System.out.println("Failed to sent Appointment Reminder Email.");
				            ex.printStackTrace();
				        }
					}
				};
		        
		        timer.schedule(task, reminder_date_Date);
			}
		}
	}
}
