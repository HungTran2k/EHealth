<%@ page import = "com.ehealth.models.Doctor" %>
<%@ page import = "com.ehealth.models.Appointment" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <% 
 	HttpSession s = request.getSession();
	Doctor doctor = (Doctor)s.getAttribute("selected-doctor");
	Appointment appointment = (Appointment)s.getAttribute("appointment"); 
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Your Appointment</title>
<link rel="stylesheet" href="css/app.css" type="text/css">
</head>
<body>
	<div class="container">
		<form id="menu" action="menu" method="post" class="navbar">
			<button class="menu-btn" id="edit-information" type="submit" name="menu" value="information">Your Information</button>
			<button class="menu-btn" id="logout" type="submit" name="menu" value="logout">Logout</button>
		</form>
		<div class="appointment_content">
			<form class="sub_container" action="shift-cancel-appointment" method="post">
				<table class="styled-table" border = "1"> 
		        	<thead>
		         		<tr>
				            <th style="text-align: center; width: 150px; font-size: 25px;" colspan="2">Your appointment</th>
		            	</tr>
			        </thead> 
			        <tr>
			         	<td>Doctor</td>
			            <td><% out.print(doctor.getFirstName() + " " + doctor.getLastName()); %></td>
			         </tr> 
			         <tr>
			            <td>Address</td>
			            <td><% out.print(doctor.getAddress()); %></td>
			         </tr> 
			         <tr>
			            <td>Time</td>
			            <td><% out.print(appointment.getDate() + " at " + appointment.getTime()); %></td>
			         </tr> 
			         <tr>
			            <td>Distance</td>
			            <td><% out.print(doctor.getDistanceToUser() + " km"); %></td>
			         </tr> 
		     	</table> 
		    	<br>
		      	<div class="flex-container">
					<button type="submit" id="submit" name="shift-cancel-btn" value="shift" class="submit _46-width">Shift</button>
					<button type="submit" id="submit" name="shift-cancel-btn" value="cancel" class="submit _46-width">Cancel</button>
				</div>
			</form>
		</div>
	</div>
</body>
</html>