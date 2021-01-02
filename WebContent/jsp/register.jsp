<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/app.css" type="text/css">
<title>Registration Page</title>
</head>
<body>
	<div class="container">
		<div class="loginRegisterContent">
			<form id= "form" action = "LoginRegister" method = "post">
				<div style="text-align:center;"><h2>Create your account</h2></div>
				<br>
				<div class="form-group">
					<label for="firstName" id="username-label">UserName</label>
					<input type="text" id="username" class="form-control" name="username" required>
					<i style="color:red">${usernameErrorMessage}</i>
				</div>
				<div class="form-group">
					<label for="email" id="email">Email</label>
					<input type="email" id="email" class="form-control" name = "email" required>
				</div>
				<div class="form-group">
					<label for="password" id="password-label">Password</label>
					<input type="password" id="password" class="form-control" name="password" required>
					<i style="color:red">${passwordErrorMessage}</i>
				</div>
				<div class="form-group">
					<label for="password" id="password-label">Re-type Password</label>
					<input type="password" id="password" class="form-control" name="passwordRetype" required>
				</div>
				<div class="flex-container">
					<a href="login.jsp">Login</a>
					<button type="submit" id="submit" name="submit" class="submit spec-btn" value="register">Register</button>
				</div>
			</form>
		</div>
	</div>
</body>
</html>