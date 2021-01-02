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
 * Servlet implementation class LoginRegister
 */
@WebServlet("/LoginRegister")
public class LoginRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginRegister() {
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
		HttpSession session = request.getSession();
		
		//get user interactions
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String submitType = request.getParameter("submit");
		String retypePassword = request.getParameter("passwordRetype");
		String email = request.getParameter("email");
		
		//get user from database with username and password
		UserDAO userDAO = new UserDAOImpl();
		User user = userDAO.getUserFromDB(username, password);
		
		//Login. In case user haven't input information, go to page update.jsp. In case already input, go to page index.jsp
		if (submitType.equals("login") && user!=null && user.getUsername()!=null) {
			session.setAttribute("user",user);
			if (user.getFirstName()!=null && user.getLastName()!=null && user.getDate_of_birth()!=null && user.getInsuranceType()!=null && user.getInsuranceName()!=null && user.getHealth_problem()!=null && user.getHealth_information()!=null && user.getMaximum_search_distance()!= null) {
				request.getRequestDispatcher("index.jsp").forward(request, response);
			} else {
				getServletContext().getRequestDispatcher("update.jsp").forward(request, response);
			}
			
		//Register
		} else if (submitType.equals("register")) {
			if(!userDAO.checkUsernameExistedInDB(username)) {
				user.setUsername(username);
			} else {
				request.setAttribute("usernameErrorMessage", "Username existed");
				request.getRequestDispatcher("register.jsp").forward(request, response);
			}
			user.setEmail(email);
			//check retype password match
			if (password.equals(retypePassword)) {
				user.setPassword(password);
				userDAO.insertUserRegistrationToDB(user);
				request.setAttribute("successMessage", "Successful Registration. Login to continue!!!");
				request.getRequestDispatcher("login.jsp").forward(request,  response);
			} else {
				request.setAttribute("passwordErrorMessage", "The re-type password didn't match");
				request.getRequestDispatcher("register.jsp").forward(request, response);
			}  
				
		} else {
			request.setAttribute("message", "Data Not Found! Create an account to continue!");
			request.getRequestDispatcher("login.jsp").forward(request,  response);
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
