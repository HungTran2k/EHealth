<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cancel successfully</title>
<link rel="stylesheet" href="css/app.css" type="text/css">
</head>
<body>
	<div class="container">
		<form id="menu" action="menu" method="post" class="navbar">
			<button class="menu-btn" id="edit-information" type="submit" name="menu" value="information">Your Information</button>
			<button class="menu-btn" id="logout" type="submit" name="menu" value="logout">Logout</button>
		</form>
		<div class="appointment_content">
			<div class="sub_container">
				Successful cancellation!
				<a href="search.jsp">New appointment</a>
			</div>
		</div>
	</div>
</body>
</html>