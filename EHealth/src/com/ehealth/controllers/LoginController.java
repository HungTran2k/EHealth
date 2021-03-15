package com.ehealth.controllers;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ehealth.dao.AdminDAO;
import com.ehealth.dao.AdminDAOImpl;
import com.ehealth.dao.AppointmentDAO;
import com.ehealth.dao.AppointmentDAOImpl;
import com.ehealth.dao.DoctorDAO;
import com.ehealth.dao.DoctorDAOImpl;
import com.ehealth.dao.UserDAO;
import com.ehealth.dao.UserDAOImpl;
import com.ehealth.models.Appointment;
import com.ehealth.models.Doctor;
import com.ehealth.models.User;



@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 		HttpSession session = request.getSession();
 		//get user location
 		String lat = request.getParameter("lat");
 		String lon = request.getParameter("lon");
 		
		//get user inputs
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		//check Admin
        AdminDAO adminDAO = new AdminDAOImpl();
		
		//get user from database with user name and password
		UserDAO userDAO = new UserDAOImpl();
		User user = userDAO.getUserFromDB(username, password);
		//get appoinment to check if user already made appointment or not
		AppointmentDAO appointmentDAO = new AppointmentDAOImpl();
		Appointment appointment = appointmentDAO.getAppointmentInfo(username);
		System.out.println(appointment);
		
		//check Admin
		if (adminDAO.checkAdmin(username, password)==true){
            java.util.List<User>users = userDAO.getAllUsers();
            //save all users to session
            session.setAttribute("users",users);
            request.setAttribute("users", users);
            //page redirection
            request.getRequestDispatcher("admin.jsp").forward(request, response);
        }
		//Login 
		if (user!=null && user.getUsername()!=null) {
			//save data to session
			session.setAttribute("user",user);
			session.setAttribute("non_encrypt_password", password);
			session.setAttribute("lat", lat );
	 		session.setAttribute("lon", lon );
	 		System.out.println("lat:"+session.getAttribute("lat"));
			System.out.println("lon:"+session.getAttribute("lon"));
			//In case user haven't input information, go to page update.jsp. In case already input, go to page index.jsp
			if (user.getFirstName()==null && user.getLastName()==null && user.getDate_of_birth()==null && user.getInsuranceType()==null && user.getInsuranceName()==null && user.getHealth_information()==null ) {
				//page redirection
				response.sendRedirect("update.jsp");
			} else if (appointment!=null && appointment.getDate()!=null) {
				DoctorDAO doctorDAO = new DoctorDAOImpl();
				Doctor doctor = doctorDAO.getDoctorByID(appointment.getDoctorID(), Double.valueOf(lat), Double.valueOf(lon));
				session.setAttribute("selected-doctor", doctor);
				session.setAttribute("appointment", appointment);
				request.getRequestDispatcher("appointment.jsp").forward(request, response);
			} else {
				//page redirection
				request.getRequestDispatcher("search.jsp").forward(request, response);
			}
		//In case user name and password is not found in DB
		} else {
			request.setAttribute("message", "Data Not Found! Create an account to continue!");
			//page redirection
			request.getRequestDispatcher("login.jsp").forward(request,  response);
		}
		
	}
	

}
