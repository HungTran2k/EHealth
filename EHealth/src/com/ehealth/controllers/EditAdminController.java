/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import com.ehealth.models.*;

@WebServlet(name = "EditAdminController", urlPatterns = {"/EditAdmin"})
public class EditAdminController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public EditAdminController() {
        super();
        // TODO Auto-generated constructor stub
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
	}
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   	 //get user interactions
        String password = request.getParameter("password");
        String retypePassword = request.getParameter("passwordRetype");
        String email = request.getParameter("email");
        String insuranceName = request.getParameter("insuranceName");
        String insuranceType = request.getParameter("insuranceType");
        String healthInformation = request.getParameter("healthInformation");
        String submit = request.getParameter("confirm");

        //use session to save user information
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        //get user from database
        UserDAO userDAO = new UserDAOImpl();
        if (submit.equals("confirm")) {
            if (retypePassword.equals(password)) {
                userDAO.EditUser(email, password, insuranceType, insuranceName, username, healthInformation);
                session.removeAttribute("username");
                ArrayList<User> users = (ArrayList<User>) session.getAttribute("users");
                users = (ArrayList<User>) userDAO.getAllUsers();
                session.setAttribute("users", users);
                request.setAttribute("users", users);
                request.getRequestDispatcher("admin.jsp").forward(request, response);
            } else {
                request.setAttribute("passwordErrorMessage", "The re-type password didn't match");
                //page redirection
                request.getRequestDispatcher("admin-edit-user.jsp").forward(request, response);
            }

        }
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

       
    }

}
