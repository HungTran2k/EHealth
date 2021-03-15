<%@ page import = "java.util.ArrayList" %>
<%@ page import = "com.ehealth.models.Doctor" %>
<% 
	ArrayList<Doctor> doctors = (ArrayList)request.getAttribute("doctors");
	if (doctors == null) {
		response.sendRedirect("login.jsp");
	}
	HttpSession s = request.getSession();
	String search_distance = (String)s.getAttribute("search_distance");
	if (search_distance==null) {
		search_distance = "All";
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
<script src="https://kit.fontawesome.com/6ad0ff1f04.js" crossorigin="anonymous"></script>
</head>
<body>
	<div class="container">
		<form id="menu" action="menu" method="post" class="navbar">
			<button class="menu-btn" id="search" type="submit" name="menu" value="search">Search</button>
			<button class="menu-btn" id="edit-information" type="submit" name="menu" value="information">Your Information</button>
			<button class="menu-btn" id="logout" type="submit" name="menu" value="logout">Logout</button>
		</form>
		<div class="doctor-list_content">
			<form class="sub_container_no_border_radius_bottom" action="search" method="post">
				<div>
					<input type="hidden" name="healthProblem" value="<%= s.getAttribute("healthProblem") %>">
				</div>
				<div class="">
					<label>Distance of search</label>
					<div class="flex-container">
					<select name="search_distance" id="search_distance" class="_66-width form-control">
						<option disabled selected>--All--</option>
						<option value= "3">3 km</option>
						<option value= "5">5 km</option>
						<option value= "10">10 km</option>
						<option value= "20">20 km</option>
						<option value= "30">30 km</option>
					</select>
					<button id="distance_seach_btn" id="submit" name="submit" value="research_confirm" class="_33-width submit"><i class="fas fa-search"></i></button>
					</div>
				</div>
			</form>
			<form class="sub_container_no_border_radius_top" action="select-doctor" method="post">
				<table class="styled-table">
					<caption><% 
					if (search_distance.equals("All")) {
						out.print("Search distance filter for: " + search_distance); 
					} else {
						out.print("Search distance filter for: " + search_distance + " km"); 
					}
					%></caption>
					<thead>
						<tr>
							<th style="max-width: 185px">First Name</th>
							<th style="max-width: 185px">Last Name</th>
							<th style="max-width: 230px">Address</th>
							<th style="max-width: 190px">Specialization</th>
							<th style="max-width: 115px">Distance</th>
							<th style="width: 90px"></th>
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
								<button style="padding: 8px; border-radius: 3px;" id="appointment_btn" name="appointment" value=<%=(i)%>>Appointment</button>
							</td>
						</tr>
					<% } %>
					</tbody>
					<tfoot>
						<tr>
					<% if (doctors.size() == 0) { %>
						<td colspan="6" style="text-align: center">
							<%= "No doctors available!" %>
						</td>
					<% } %>
						</tr>
					</tfoot>
				</table>
			</form>
		</div>
	</div>
</body>
</html>