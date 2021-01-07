<%@ page import = "java.util.ArrayList" %>
<%@ page import = "com.ehealth.models.Doctor" %>
<% 
	ArrayList<Doctor> doctors = (ArrayList)request.getAttribute("doctors");
	if (doctors == null) {
		response.sendRedirect("login.jsp");
	}
%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Display doctors</title>
<link rel="stylesheet" href="css/app.css" type="text/css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
</head>
<body>
	<div class="container">
		<div class="doctor-list_content">
			<form class="sub_container_no_border_radius_bottom" action="search" method="post">
				<div class="form-group">
					<label>Distance of search</label>
					<select name="search_distance" id="search_distance" class="form-control">
						<option disabled selected>--All--</option>
						<option value= "3">3 km</option>
						<option value= "5">5 km</option>
						<option value= "10">10 km</option>
						<option value= "20">20 km</option>
						<option value= "30">30 km</option>
					</select>
				</div>
				<div class="form-group">
					<button id="distance_seach_btn" id="submit" name="submit" value="research_confirm" class="submit">Submit</button>
				</div>
			</form>
			<form class="sub_container_no_border_radius_top" action="select-doctor" method="post">
				<table class="styled-table">
					<thead>
						<tr>
							<th style="width: 200px">First Name</th>
							<th style="width: 200px">Last Name</th>
							<th style="width: 240px">Address</th>
							<th style="width: 260px">Specialization</th>
							<th>Distance</th>
							<th>Make appointment</th>
						</tr>
					</thead>
					<tbody>
					<% for(int i = 0; i < doctors.size(); i++) { %>
						<tr>
							<% Doctor doctor = (Doctor)doctors.get(i); %>
							<td>
								<%= doctor.getFirstName() %> 
							</td>
							<td>
								<%= doctor.getLastName() %>
							</td>
							<td>
								<%= doctor.getAddress() %>
							</td>
							<td>
								<%= doctor.getSpecialization() %>
							</td>
							<td>
								<%= doctor.getDistanceToUser() %> km
							</td>
							<td>
								<button id="appointment_btn" name="appointment" value=<%=(i)%>>Appointment</button>
							</td>
						</tr>
					<% } %>
					</tbody>
				</table>
			</form>
		</div>
	</div>
</html>