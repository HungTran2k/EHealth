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
            <div class="update_content">
                <div class="sub_container">
                    <form id= "form" action = "EditAdmin" method = "post">
                        <div style="text-align:center;"><h2>Edit your account</h2></div>
                        <br>
                        <div class="form-group">
                            <label>UserName:${sessionScope.username}</label>		
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
                            <button type="submit" id="submit" name="confirm" value="confirm" class="submit">Submit</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
