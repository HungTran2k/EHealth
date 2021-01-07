package com.ehealth.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ehealth.dao.UserDAO;
import com.ehealth.dao.UserDAOImpl;
import com.ehealth.models.User;

/**
 * Servlet implementation class RegisterController
 */
@WebServlet("/register")
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//get user inputs
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String retypePassword = request.getParameter("passwordRetype");
		String email = request.getParameter("email");
		
		
		
		// create two cookies to query data from database to generate pdf file
		Cookie usernameCookie = new Cookie("username", username);
		Cookie passwordCookie = new Cookie("password", password);
		// get email cookie
		Cookie emailCookie = new Cookie("email", email);

		// add the cookies to the response header
		response.addCookie(usernameCookie);
		response.addCookie(passwordCookie);
		// add emailCookie
		response.addCookie(emailCookie);
		
		//get user from database with username and password
		UserDAO userDAO = new UserDAOImpl();
		User user = userDAO.getUserFromDB(username, password);
		
		//check username is existed or not
		if(!userDAO.checkUsernameExistedInDB(username)) {
			user.setUsername(username);
		//if existed, go to register page
		} else {
			request.setAttribute("usernameErrorMessage", "Username existed");
			//page redirection
			request.getRequestDispatcher("register.jsp").forward(request, response);
		}
		user.setEmail(email);
		//check retype password match
		if (password.equals(retypePassword)) {
			user.setPassword(password);
			userDAO.insertUserRegistrationToDB(user);
			request.setAttribute("successMessage", "Successful Registration. Login to continue!!!");
			//page redirection
			request.getRequestDispatcher("login.jsp").forward(request,  response);
		} else {
			request.setAttribute("passwordErrorMessage", "The re-type password didn't match");
			//page redirection
			request.getRequestDispatcher("register.jsp").forward(request, response);
		}  
	}

}
