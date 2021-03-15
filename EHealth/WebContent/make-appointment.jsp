<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Make Appointment</title>
<link rel="stylesheet" href="css/app.css" type="text/css">
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
 <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.min.css">
 <script src="//cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.min.js"></script>
</head>
<body>
	<div class="container">
		<form id="menu" action="menu" method="post" class="navbar">
			<button class="menu-btn" id="edit-information" type="submit" name="menu" value="information">Your Information</button>
			<button class="menu-btn" id="logout" type="submit" name="menu" value="logout">Logout</button>
		</form>
		<div class="appointment_content">
			<form class="sub_container" action="make-appointment" method="post">
				<div class="form-group">
					<label>Date & Time</label>
					<input type="text" id="datepicker" name="datepicker" class="form-control">
					<input type="text" id="timepicker" name="timepicker" class="form-control">
				</div>
				<div class="form-group">
					<label>Reminder time </label>
					<select name ='reminder_time' id='reminder_time'class="form-control">
						<option value = "None">None</option>
						<option value ='604800'>1 week</option>
						<option value ='259200'>3 days</option>
						<option value ='3600'>1 hour</option>
						<option value ='600'>10 minutes</option>
						<option value ='60'>(Test) 1 minute</option>
					</select>
					<br>
					<i>Please select your reminder time before the appointment</i>
					<i>We will send you a reminder to your email address</i>
				</div>
				<div class="form-group">
					<button type="submit" id="submit" name="confirm" value="confirm" class="submit">Confirm</button>
				</div>
			</form>
		</div>
	</div>
	
 <script>
 //jQuery date and time picker
 $(function() {
   $( "#datepicker" ).datepicker({
   	changeMonth: true,
       changeYear: true,
       showOtherMonths: true,
       selectOtherMonths: true,
       showButtonPanel: true
   });
   $( "#datepicker" ).datepicker( "option", "showAnim", "slideDown" );
   $( "#datepicker" ).datepicker( "option", "dateFormat", "dd/mm/yy" );
   $('#timepicker').timepicker({
       timeFormat: 'HH:mm',
       interval: 10,
       minTime: '8',
       maxTime: '16:00',
       defaultTime: '8',
       startTime: '08:00',
       dynamic: false,
       dropdown: true,
       scrollbar: true
   });
 } );
  </script>
</body>
</html>