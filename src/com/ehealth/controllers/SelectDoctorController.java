package com.ehealth.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

import com.ehealth.models.Doctor;


/**
 * Servlet implementation class MakeAppointment
 */
@WebServlet("/select-doctor")
public class SelectDoctorController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectDoctorController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   
	   
	} 
		
		
	 public void sendPlainTextEmail(String host, String port,
	            final String userName, final String password, String toAddress,
	            String subject, String message) throws AddressException,
	            MessagingException {
	 
	        // sets SMTP server properties
	        Properties properties = new Properties();
	        properties.put("mail.smtp.host", host);
	        properties.put("mail.smtp.port", port);
	        properties.put("mail.smtp.auth", "true");
	        properties.put("mail.smtp.starttls.enable", "true");
	 
	        // creates a new session with an authenticator
	        Authenticator auth = new Authenticator() {
	            public PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication(userName, password);
	            }
	        };
	 
	        Session session = Session.getInstance(properties, auth);
	 
	        // creates a new e-mail message
	        Message msg = new MimeMessage(session);
	 
	        msg.setFrom(new InternetAddress(userName));
	        InternetAddress[] toAddresses = { new InternetAddress(toAddress) };
	        msg.setRecipients(Message.RecipientType.TO, toAddresses);
	        msg.setSubject(subject);
	        msg.setSentDate(new Date());
	        // set plain text message
	        msg.setText(message);
	 
	        // sends the e-mail
	        Transport.send(msg);
	 
	 }	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		//get doctors list from session
		ArrayList<Doctor> doctors = (ArrayList<Doctor>) session.getAttribute("doctors");
		
		//Handle which doctor user selected to make appointment
		String doctor_index = request.getParameter("appointment");
		Integer x = Integer.valueOf(doctor_index);
		Doctor doctor = doctors.get(x);
		
		//demo display selected doctor
		PrintWriter w = response.getWriter();
		w.println("Doctor name: " + doctor.getFirstName() + " " + doctor.getLastName());
		w.println("Doctor address: " + doctor.getAddress());
		w.println("Doctor specialization: " + doctor.getSpecialization());
		w.println("Distance to you:  " + doctor.getDistanceToUser() + " km");
	
		////Send confirmation email to user
		
		
		 // sets SMTP server properties
		String host = "smtp.gmail.com";
        String port = "587";
        String mailFrom = "yourEmailAddress"; //your email address
        String password = "yourEmailPassword"; //your password
 
        // outgoing message information
        Cookie c[] = request.getCookies();
        String mailTo = c[2].getValue(); // this is the email from cookies
        String subject = "Appointment Confirmation";
        String message = "This is a confirmation email that you have made an appointment with doctor: " + doctor.getFirstName() + doctor.getLastName() + " in: " + doctor.getAddress();
       
 
        SelectDoctorController mailer = new SelectDoctorController();
 
        try {
            mailer.sendPlainTextEmail(host, port, mailFrom, password, mailTo, subject, message);
            System.out.println("Email sent.");
        } catch (Exception ex) {
            System.out.println("Failed to sent email.");
            ex.printStackTrace();
        }
		 
		
		
		
		
	}

}
