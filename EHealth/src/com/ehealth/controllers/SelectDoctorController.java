package com.ehealth.controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		//get doctors list from session
		@SuppressWarnings("unchecked")
		ArrayList<Doctor> doctors = (ArrayList<Doctor>) session.getAttribute("doctors");
		
		//Handle which doctor user selected to make appointment
		String doctor_index_String = request.getParameter("appointment");
		Integer doctor_index = Integer.valueOf(doctor_index_String);
		Doctor doctor = doctors.get(doctor_index);
		session.setAttribute("selected-doctor", doctor);
		//when already selected, redirect to page make-appointment
		request.getRequestDispatcher("make-appointment.jsp").forward(request, response);
	}

}
