<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/app.css" type="text/css">
<title>Login Page</title>
</head>
<body>
	<div class="container">
		<div class=loginRegisterContent>
			<form id="form" action = "LoginRegister" method = "post" >
				<div style="text-align:center;"><h2>Login</h2></div>
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
				<div class="flex-container">
					<a href="register.jsp">Create an account</a>
					<button type="submit" id="submit" name="submit" value="login" class="submit spec-btn">Login</button>
				</div>
			</form>
		</div>
	</div>
</body>
</html>