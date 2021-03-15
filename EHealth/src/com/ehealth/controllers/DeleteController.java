package com.ehealth.controllers;

import com.ehealth.dao.*;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.ehealth.models.User;

@WebServlet(name = "DeleteController", urlPatterns = {"/Delete"})
public class DeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public DeleteController() {
        super();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	//get all users from session
    	HttpSession session = request.getSession();
        ArrayList<User> users = (ArrayList<User>) session.getAttribute("users");
        //get admin click
        String user_delete_index_String = request.getParameter("delete_index");
        Integer user_delete_index = Integer.valueOf(user_delete_index_String);
        User user = users.get(user_delete_index);
        //delete user
        UserDAO userDAO = new UserDAOImpl();
        userDAO.deleteUserInList(user);
        session.removeAttribute("users");
        users = (ArrayList<User>) userDAO.getAllUsers();
        session.setAttribute("users", users);
        request.setAttribute("users", users);
        //page redirection
        request.getRequestDispatcher("admin.jsp").forward(request, response);
	}

  
}
