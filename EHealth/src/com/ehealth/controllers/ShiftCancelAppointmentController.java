package com.ehealth.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ehealth.dao.AppointmentDAO;
import com.ehealth.dao.AppointmentDAOImpl;
import com.ehealth.models.Doctor;
import com.ehealth.models.EmailSender;
import com.ehealth.models.User;

/**
 * Servlet implementation class ShiftCancelAppointmentController
 */
@WebServlet("/shift-cancel-appointment")
public class ShiftCancelAppointmentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShiftCancelAppointmentController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String btnType = request.getParameter("shift-cancel-btn");
		HttpSession session = request.getSession();
		User u = (User)session.getAttribute("user");
		Doctor doctor = (Doctor)session.getAttribute("selected-doctor");
		
		if (btnType.equals("shift")) {
			request.getRequestDispatcher("make-appointment.jsp").forward(request, response);
			
		} else {
			AppointmentDAO appointmentDAO = new AppointmentDAOImpl();
			appointmentDAO.deleteAppointment(u.getUsername());
			
			//Send email
			String newline = System.getProperty("line.separator");
			String host = "smtp.gmail.com";
	        String port = "587";
	        String mailFrom = "ehealthjava@gmail.com";
	        String password = "abc123--";
	        String mailTo = u.getEmail();
	        String subject = "Appointment Cancellation";
	        String message = "Dear Mrs/Mr " + u.getLastName() + "," + newline + newline +
	        		"This is a confirmation email that you have canceled the appointment with:" + newline + newline +
	        		"Doctor: " + doctor.getFirstName() + " " + doctor.getLastName() + newline +
	        		"Address: " + doctor.getAddress() + newline + newline +
	        		"Best regards" + newline +
	        		"EHealth Team";
	       
	        EmailSender mailer = new EmailSender();
	        try {
	            mailer.sendEmail(host, port, mailFrom, password, mailTo, subject, message);
	            System.out.println("Appointment Cancellation Email sent.");
	        } catch (Exception ex) {
	            System.out.println("Failed to sent Appointment Cancellation Email.");
	            ex.printStackTrace();
	        }
			session.removeAttribute("appointment");
			request.getRequestDispatcher("cancel-success.jsp").forward(request, response);
		}
	}

}
