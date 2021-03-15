<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Searching Page</title>
<link rel="stylesheet" href="css/app.css" type="text/css">
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src="https://kit.fontawesome.com/6ad0ff1f04.js" crossorigin="anonymous"></script>
</head>
<body>
	<div class="container">
		<form id="menu" action="menu" method="post" class="navbar">
			<button class="menu-btn" id="edit-information" type="submit" name="menu" value="information">Your Information</button>
			<button class="menu-btn" id="logout" type="submit" name="menu" value="logout">Logout</button>
		</form>
		<div class="search_content">
			<form class="sub_container" action="search" method="post">
				<div class="form-group">
					<label>Health problem</label>
					<select name="healthProblem" id="health-problem" class="form-control" required>
						<option disabled selected value="">Your problem</option>
						<option value="flu fever">I have flu/ fever</option>
						<option value="headache">I have headache</option>
						<option value="eye pain">I have eye pain</option>
						<option value="can't breath">I can't breath</option>
						<option value="ear pain">I have ear pain</option>
						<option value="mouth lesion">I have mouth lesions</option>
						<option value="toothache">I have toothache</option>
						<option value="sore throat">I have sore throat</option>
						<option value="neck pain">I have neck pain</option>
						<option value="arm pain">I have arm pain</option>
						<option value="chest pain">I have chest pain</option>
						<option value="abdominal pain">I have abdominal pain</option>
						<option value="stomach-ache">I have stomach-ache</option>
						<option value="back pain">I have back pain</option>
						<option value="leg pain">I have leg pain</option>
						<option value="joint pain">I have joint pain</option>
						<option value="body aches">I have body aches</option>
						<option value="skin issue">I have skin issue</option>
						<option value="psychological problem">I have psychological problem</option>
						<option value="pregnancy">Pregnancy check</option>
						<option value="gynecology">Gynecology</option>
						<option value="andrology">Andrology</option>
						<option value="neurology">Neurology</option>
						<option value="other">Other</option>
					</select>
				</div>
				<div class="form-group">
					<label>Distance of search</label>
					<select name="search_distance" id="search_distance" class="form-control">
						<option disabled selected>--All--</option>
						<option value= "3">3 km</option>
						<option value= "5">5 km</option>
						<option value= "10">10 km</option>
						<option value= "15">15 km</option>
						<option value= "25">25 km</option>
					</select>
				</div>
				<div class="form-group">
					<button type="submit" id="submit" name="confirm" value="confirm" class="submit"><i class="fas fa-search"></i></button>
				</div>
			</form>
		</div>
	</div>
</body>
</html>