<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Insert Information</title>
<link rel="stylesheet" href="css/app.css" type="text/css">
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src="js/dobpicker.js"></script>

<script>
  $(document).ready(function() {
		$.dobPicker({
			daySelector: '#dobday', /* Required */
			monthSelector: '#dobmonth', /* Required */
			yearSelector: '#dobyear', /* Required */
			dayDefault: 'Day', /* Optional */
			monthDefault: 'Month', /* Optional */
			yearDefault: 'Year', /* Optional */
			minimumAge: 00, /* Optional */
			maximumAge: 120 /* Optional */
		});
	});
</script>
</head>
<body>
	<div class="container">
		<form id="menu" action="menu" method="post" class="navbar">
			<button class="menu-btn" id="search" type="submit" name="menu" value="search">Search</button>
			<button class="menu-btn" id="logout" type="submit" name="menu" value="logout">Logout</button>
		</form>
		<div class="update_content">
			<div class="sub_container">
				<form id="form" action="update" method="post">
					<div style="text-align:center;"><h2>Enter your information</h2></div>
					<br>
					<div class="form-group">
						<label for="firstName" id="firstName-label">First Name</label>
						<input type="text" id="firstName" name="firstName" class="form-control" placeholder="Enter your first name" required>
					</div>
					<div class="form-group">
						<label for="lastName" id="lastName-label">Last Name</label>
						<input type="text" id="firstName" name="lastName" class="form-control" placeholder="Enter your last name" required>
					</div>
					<div class="form-group" id="birthday">
						<label for="birthday">Birthday</label>
						<select id="dobday" name="day" class="form-control"></select>
						<select id="dobmonth" name="month" class="form-control"></select>
						<select id="dobyear" name="year" class="form-control"></select>
					</div>
					<div class="form-group">
						<label>Insurance type</label>
						<label><input type="radio" class="input-radio" value="public" name="insuranceType">Public</label>
						<label><input type="radio" class="input-radio" value="private" name="insuranceType">Private</label>
					</div>
					<div class="form-group">
						<label for="insuranceName" id="insuranceName">Insurance name</label>
						<input type="text" id="insuranceName" name="insuranceName" class="form-control" placeholder="Enter your insurance name" required>
					</div>
					<div class="form-group">
						<label>Health information</label>
						<textarea name="healthInformation" id="health-information" placeholder="Enter your health information here..."></textarea>
					</div>
					<div class="form-group">
						<button type="submit" id="submit" name="confirm" value="confirm" class="submit">Confirm</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>