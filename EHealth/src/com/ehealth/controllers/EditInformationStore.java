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
import com.ehealth.models.User;

/**
 *
 * @author dohai
 */
@WebServlet("/Editstore")
public class EditInformationStore extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public EditInformationStore() {
        super();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	//get all users from session
    	HttpSession session = request.getSession();
        ArrayList<User> users = (ArrayList<User>) session.getAttribute("users");
        //get admin click
        String user_edit_index_String = request.getParameter("edit_index");
        Integer user_edit_index = Integer.valueOf(user_edit_index_String);
        User user = users.get(user_edit_index);
        session.removeAttribute("users");
        session.setAttribute("username", user.getUsername());
        request.setAttribute("username", user.getUsername());
        //page redirection
        request.getRequestDispatcher("admin-edit-user.jsp").forward(request, response);
    
	}

}
