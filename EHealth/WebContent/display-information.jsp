<%@ page import = "com.ehealth.models.User" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <% 
 	HttpSession s = request.getSession();
	User user = (User)s.getAttribute("user");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Your Information</title>
<link rel="stylesheet" href="css/app.css" type="text/css">
</head>
<body>
	<div class="container">
		<form id="menu" action="menu" method="post" class="navbar">
			<button class="menu-btn" id="edit-information" type="submit" name="menu" value="edit">Edit Information</button>
			<button class="menu-btn" id="search" type="submit" name="menu" value="search">Search</button>
			<button class="menu-btn" id="export-information" type="submit" name="menu" value="export">Export Information</button>
			<button class="menu-btn" id="logout" type="submit" name="menu" value="logout">Logout</button>
		</form>
		<div class="appointment_content">
			<form class="sub_container" action="shift-cancel-appointment" method="post">
				<table class="styled-table" border = "1" align = "center"> 
		        	<thead>
		         		<tr>
				            <th style="text-align: center; font-size: 25px;" colspan="2">Your information</th>
		            	</tr>
			        </thead> 
			        <tr>
			         	<td style="width: 79px">First name</td>
			            <td><% out.print(user.getFirstName()); %></td>
			         </tr> 
			         <tr>
			         	<td>Last name</td>
			            <td><% out.print(user.getLastName()); %></td>
			         </tr> 
			         <tr>
			            <td>Birthday</td>
			            <td><% out.print(user.getDate_of_birth()); %></td>
			         </tr> 
			         <tr>
			            <td>Insurance type</td>
			            <td><% out.print(user.getInsuranceType()); %></td>
			         </tr> 
			          <tr>
			            <td>Insurance name</td>
			            <td><% out.print(user.getInsuranceName()); %></td>
			         </tr> 
			          <tr>
			            <td>Health information</td>
			            <td><% out.print(user.getHealth_information()); %></td>
			         </tr> 
		     	</table> 
			</form>
		</div>
	</div>
</body>
</html>