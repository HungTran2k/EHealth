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

</head>
<body>
	<div class="container">
		<div class="search_content">
			<form class="sub_container" action="search" method="post">
				<div class="form-group">
					<label>Health problem</label>
					<select name="healthProblem" id="healthProblem" class="form-control">
						<option disabled selected value>Your problem is about</option>
						<option value="certain infectious and parasitic">Certain infectious and parasitic</option>
						<option value="endocrine, nutritional and metabolic">Endocrine, nutritional and metabolic </option>
						<option value="mental and behavioural disorders">Mental and behavioural disorders</option>
						<option value="the nervous system">The nervous system</option>
						<option value="the eye and adnexa">The eye and adnexa</option>
						<option value="the ear and mastoid process">The ear and mastoid process</option>
						<option value="the circulatory system">The circulatory system</option>
						<option value="the respiratory system">The respiratory system</option>
						<option value="the digestive system">The digestive system</option>
						<option value="the skin and subcutaneous tissue">The skin and subcutaneous tissue</option>
						<option value="the musculoskeletal system and connective tissue">The musculoskeletal system and connective tissue</option>
						<option value="the genitourinary system">The genitourinary system</option>
						<option value="pregnancy, childbirth and the puerperium">Pregnancy, childbirth and the puerperium</option>
						<option value="certain conditions originating in the perinatal period">Certain conditions originating in the perinatal period</option>
						<option value="congenital malformations, deformations and chromosomal abnormalities">Congenital malformations, deformations and chromosomal abnormalities</option>
						<option value="injury, poisoning and certain other consequences of external causes">Injury, poisoning and certain other consequences of external causes</option>
						<option value="other">Other</option>
					</select>
				</div>
				<div class="form-group">
					<label>Distance of search</label>
					<select name="search_distance" id="search_distance" class="form-control">
						<option disabled selected> --All--</option>
						<option value= "3">3 km</option>
						<option value= "5">5 km</option>
						<option value= "10">10 km</option>
						<option value= "15">15 km</option>
						<option value= "25">25 km</option>
					</select>
				</div>

				<div class="form-group">
					<a href="generatePdf.jsp">Export pdf file</a>
					<button type="submit" id="submit" name="confirm" value="confirm" class="submit">Submit</button>
				</div>
			</form>
		</div>
	</div>
</body>
</html>