<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/app.css" type="text/css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<title>Login Page</title>
</head>
<body>
	<div class="container">
		<div class=loginRegister_content>
			<form class="sub_container" action="login" method = "post" >
				<div style="text-align:center;"><h2>E-Health Login</h2></div>
				<br>
				<div><i style="color:light">${message}</i></div>
				<div><i style="color:light">${successMessage}</i></div>
				<div class="form-group">
					<label for="firstName" id="username-label">UserName</label>
					<input type="text" id="username" class="form-control" name="username" placeholder="Enter your username" required>
				</div>
				<div class="form-group">
					<label for="password" id="password-label">Password</label>
					<input type="password" id="password" class="form-control" name="password" placeholder="Enter your password" required>
				</div>
				<div>
					<input type="hidden" id="lat" name="lat">
					<input type="hidden" id="lon" name="lon">
				</div>
				<div class="flex-container">
					<a href="register.jsp">Create an account</a>
					<button type="submit" id="submit" name="submit" value="login" class="submit _38-width">Login</button>
				</div>
			</form>
		</div>
		
	</div>
<script>
//use IPinfo.io API to get user location
function getLocationByDefault() {
    $.getJSON("https://ipinfo.io?token=b0244f6f14547e", onLocationGot);
  }
 
function onLocationGot(info) {
    let position = info.loc.split(",");
	document.getElementById("lat").value = position[0];
	document.getElementById("lon").value = position[1];
 }
 
 getLocationByDefault()
</script>
</body>
</html>