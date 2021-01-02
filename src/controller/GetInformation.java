package controller;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDAO;
import dao.UserDAOImpl;
import model.User;

/**
 * Servlet implementation class GetInformation
 */
@WebServlet("/GetInformation")
public class GetInformation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetInformation() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//get user interactions
				String firstName = request.getParameter("firstName");
				String lastName = request.getParameter("lastName");
				String bth_day = request.getParameter("day");
				String bth_month = request.getParameter("month");
				String bth_year = request.getParameter("year");
				String date_of_birth = bth_day.concat("/" + bth_month).concat("/" + bth_year);
				String insuranceName = request.getParameter("insuranceName");
				String insuranceType = request.getParameter("insuranceType");
				String healthProblem = request.getParameter("healthProblem");
				String healthInformation = request.getParameter("healthInformation");
				String maximum_search_distance = request.getParameter("maximum_search_distance");
				String submit = request.getParameter("confirm");
				
				//use session to save user information
				HttpSession session = request.getSession();
				User u = (User) (session.getAttribute("user"));
				//get user from database
				UserDAO userDAO = new UserDAOImpl();
				User user = userDAO.getUserFromDB(u.getUsername(), u.getPassword());
				
				//if user click on confirm => insert all info into database
				if (submit.equals("confirm")) {
					user.setFirstName(firstName);
					user.setLastName(lastName);
					user.setDate_of_birth(date_of_birth);
					user.setInsuranceName(insuranceName);
					user.setInsuranceType(insuranceType);
					user.setHealth_problem(healthProblem);
					user.setHealth_information(healthInformation);
					user.setMaximum_search_distance(maximum_search_distance);
					userDAO.insertUserInformationToDB(user);
					request.getRequestDispatcher("index.jsp").forward(request, response);
				} 
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
