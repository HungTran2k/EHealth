package com.ehealth.controllers;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ehealth.dao.UserDAO;
import com.ehealth.dao.UserDAOImpl;
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
		
		
		
		//get user from database with user name and password
		UserDAO userDAO = new UserDAOImpl();
		User user = userDAO.getUserFromDB(username, password);
		
		//Login 
		if (user!=null && user.getUsername()!=null) {
			//save data to session
			session.setAttribute("user",user);
			session.setAttribute("lat", lat );
	 		session.setAttribute("lon", lon );
	 		System.out.println(session.getAttribute("lat"));
			System.out.println(session.getAttribute("lon"));
			//In case user haven't input information, go to page update.jsp. In case already input, go to page index.jsp
			if (user.getFirstName()!=null && user.getLastName()!=null && user.getDate_of_birth()!=null && user.getInsuranceType()!=null && user.getInsuranceName()!=null && user.getHealth_information()!=null ) {
				//page redirection
				response.sendRedirect("search.jsp");
			} else {
				//page redirection
				response.sendRedirect("update.jsp");
			}
		//In case user name and password is not found in DB
		} else {
			request.setAttribute("message", "Data Not Found! Create an account to continue!");
			//page redirection
			request.getRequestDispatcher("login.jsp").forward(request,  response);
		}
		
	}
	

}
