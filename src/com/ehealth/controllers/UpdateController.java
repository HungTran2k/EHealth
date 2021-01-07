package com.ehealth.controllers;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ehealth.dao.UserDAO;
import com.ehealth.dao.UserDAOImpl;
import com.ehealth.models.User;


@WebServlet("/update")

public class UpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public UpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		//get user inputs
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String bth_day = request.getParameter("day");
		String bth_month = request.getParameter("month");
		String bth_year = request.getParameter("year");
		String date_of_birth = bth_day.concat("/" + bth_month).concat("/" + bth_year);
		String insuranceName = request.getParameter("insuranceName");
		String insuranceType = request.getParameter("insuranceType");
		String healthInformation = request.getParameter("healthInformation");
		
		//save user information into session
		HttpSession session = request.getSession();
		User u = (User) (session.getAttribute("user"));
		//get user from database
		UserDAO userDAO = new UserDAOImpl();
		User user = userDAO.getUserFromDB(u.getUsername(), u.getPassword());
		
		//insert all user info into database
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setDate_of_birth(date_of_birth);
		user.setInsuranceName(insuranceName);
		user.setInsuranceType(insuranceType);
		user.setHealth_information(healthInformation);
		userDAO.insertUserInformationToDB(user);
		//page redirection
		response.sendRedirect("search.jsp");
	}


}
	
