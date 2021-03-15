package com.ehealth.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.ehealth.dao.DoctorDAO;
import com.ehealth.dao.DoctorDAOImpl;
import com.ehealth.dao.UserDAO;
import com.ehealth.dao.UserDAOImpl;
import com.ehealth.models.Doctor;
import com.ehealth.models.User;

/**
 * Servlet implementation class IndexController
 */
@WebServlet ("/search")
public class SearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchController() {
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
		HttpSession session = request.getSession();
		//get user inputs
		String healthProblem = request.getParameter("healthProblem");
		String search_distance_string = request.getParameter("search_distance");
		System.out.println(search_distance_string);
		//get user from session
		
		User u = (User) (session.getAttribute("user"));
		session.setAttribute("healthProblem", healthProblem);
		System.out.println(healthProblem);
		//get user from database
		UserDAO userDAO = new UserDAOImpl();
		User user = userDAO.getUserFromDB(u.getUsername(), u.getPassword());
		//insert health_problem into data base
		user.setHealth_problem(healthProblem);
		userDAO.insertUserHealthProblemToDB(healthProblem, u.getUsername());
		
		DoctorDAO doctorDAO = new DoctorDAOImpl();
		//get user longitude from session
		double latitide = Double.valueOf((String) session.getAttribute("lat"));
		double longitude = Double.valueOf((String) session.getAttribute("lon"));
		//when user doesn't select distance of search, it means all doctors are being taken from database
		if (search_distance_string==null) {
			session.setAttribute("search_distance", search_distance_string );
			//get all doctors from database 
			java.util.List<Doctor> doctors = doctorDAO.getAllDoctors(latitide, longitude, healthProblem);
			//save doctors into session
			session.setAttribute("doctors", doctors);
			//save doctors into attribute "doctors" to access doctors data in "doctors-list.jsp"
			request.setAttribute("doctors", doctors);
			//page redirection
			request.getRequestDispatcher("doctors-list.jsp").forward(request, response);	
		//when user select the distance of search
		} else {
			double search_distance = Double.valueOf(search_distance_string);
			session.setAttribute("search_distance", search_distance_string);
			//get doctors base on latitude and longitude
			java.util.List<Doctor> doctors = doctorDAO.getDoctorbyDistanceAndHealthProblem(latitide, longitude, search_distance, healthProblem);
			//save doctors into session
			session.setAttribute("doctors", doctors);
			//save doctors into attribute "doctors" to access doctors data in "doctors-list.jsp"
			request.setAttribute("doctors", doctors);
			//page redirection
			request.getRequestDispatcher("doctors-list.jsp").forward(request, response);
		}
	}

}
