<%@page import="com.ehealth.models.User"%>
<%@ page import = "java.util.ArrayList" %>

<%
    ArrayList<User> users = (ArrayList) request.getAttribute("users");
    if (users == null) {
        response.sendRedirect("login.jsp");
    }
%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Display Users</title>
<link rel="stylesheet" href="css/app.css" type="text/css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
</head>
<body>
	<div class="admin-container">
		<form id="menu" action="menu" method="post" class="navbar">
			<button class="menu-btn" id="logout" type="submit" name="menu" value="logout">Logout</button>
		</form>
	    <div class="admin_content">
		    <div class = "sub_container">
		    	<h1 style="text-align: center">List of all users</h1>
		    	<br>
	            <table class="styled-table">
	                <thead>
	                    <tr>
	                        <th width="7.9%">UserName</th>
	                        <th width= "7.9%">Email</th>
	                        <th style="width: 50px">Password</th>
	                        <th style="width: 7.9%">First Name</th>
	                        <th style="width: 7.9%">Last Name</th>
	                        <th style="width: 7.9%">Date Of Birth</th>
	                        <th style="width: 7.9%">Insurance Name</th>
	                        <th style="width: 7.9%">Insurance Type</th>
	                        <th style="width: 15.8%">Health Problem</th>
	                        <th style="width: 15.8%">Health Information</th>
	                        <th style="width: 5%;">Action</th>
	                    </tr>
	                </thead>
	                <tbody>
	                    <% for (int i = 0; i < users.size(); i++) { %>
	                    <tr>
	                        <% User user = (User) users.get(i);%>
	                        <td>
	                            <%= user.getUsername()%> 
	                        </td>
	                        <td>
	                            <%= user.getEmail()%> 
	                        </td>
	                        <td>
	                            <%= user.getPassword()%>
	                        </td>
	                        <td>
	                            <%= user.getFirstName()%> 
	                        </td>
	                        <td>
	                            <%= user.getLastName()%>
	                        </td>
	                        <td>
	                            <%= user.getDate_of_birth()%>
	                        </td>
	                        <td>
	                            <%= user.getInsuranceName()%> 
	                        </td>
	                        <td>
	                            <%= user.getInsuranceType()%> 
	                        </td>
	                        <td>
	                            <%= user.getHealth_problem()%> 
	                        </td>
	                        <td>
	                            <%= user.getHealth_information()%> 
	                        </td>
	                        <td>
	                            <a href="Editstore?edit_index=<%=(i)%>">Edit</a> 
	                            <a href="Delete?delete_index=<%=(i)%>">Delete</a>       
	                        </td>
	                    </tr>
	                    <% }%>
	                </tbody>
	            </table>
	        </div>
		</div>
	</div>
</body>
</html>
