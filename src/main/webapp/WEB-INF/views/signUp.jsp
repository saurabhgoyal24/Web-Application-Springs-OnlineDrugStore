<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

<!-- Latest compiled and minified JavaScript -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<link
	href='http://fonts.googleapis.com/css?family=Cuprum:700italic,400,400italic,700&subset=latin,latin-ext,cyrillic'
	rel='stylesheet' type='text/css'>
<style>
.margin {
	margin-top: 60px;
}

.marginbot {
	margin-bottom: 50px;
}
</style>
<script type="text/javascript">
	function ajaxFunction() {

		var xmlhttp;
		var username = document.getElementById("username").value;

		//document.getElementById("match").innerHTML = oldPass;

		if (window.XMLHttpRequest) {
			xmlhttp = new XMLHttpRequest(); //for IE7+, Firefox, Chrome, Opera, Safari
		} else {
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP"); //for IE6, IE5
		}

		xmlhttp.open("POST", "checkforusername.do", true);
		xmlhttp.setRequestHeader("username", username);

		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState == 4) {
				if (xmlhttp.status == 200) {
					if (xmlhttp.responseText == "false") {
						document.getElementById("match").innerHTML = "User name available";
						document.getElementById("submit").disabled = false;
					} else {
						document.getElementById("match").innerHTML = "Username already exists please try a different one.";
					}
					//document.getElementById("match").innerHTML = xmlhttp.responseText;
					//alert(xmlhttp.responseText);
				} else {
					alert('Something is wrong !!');
				}
			}
		}
		xmlhttp.send(null);
	}

	function validateForm() {
		alert("in");
		var firstName = document.forms["myform"]["firstName"].value;
		var lastName = document.forms["myform"]["lastName"].value;
		var address = document.forms["myform"]["address"].value;
		var dateOfBirth = document.forms["myform"]["dateOfBirth"].value;
		var email = document.forms["myform"]["email"].value;
		var phoneNo = document.forms["myform"]["phoneNo"].value;
		var username = document.forms["myform"]["username"].value;
		var password = document.forms["myform"]["password"].value;
		var confirmpassword = document.forms["myform"]["confirmPassword"].value;
		var patt1 = /^\d{10}$/g;
		var pattEmail = /^\w+@[a-zA-Z_]+?\.[a-zA-Z]{2,3}$/g;
		if (firstName.trim() == null || firstName.trim() == ""){
				/* || lastName.trim() == null || lastName.trim() == ""
				|| address.trim() == null || address.trim() == ""
				|| dateOfBirth.trim() == null || dateOfBirth.trim() == ""
				|| phoneNo.trim() == null || phoneNo.trim() == ""
				|| username.trim() == null || username.trim() == ""
				|| password.trim() == null || password.trim() == ""
				|| isNaN(phoneNo) || phoneNo < 0 || !phoneNo.match(patt1)
				|| confirmpassword == null || confirmpassword == ""
				|| password != confirmpassword || !email.match(pattEmail)) { */
			document.getElementById('errormsg').innerHTML = "Form is incomplete!";
			return false;
			alert("hi");

		} else {
			return true;
			alert("hiiiii");
		}
	}
</script>
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<h2>Sign Up</h2>
		<div class="row col-lg-6 col-lg-offset-3 margin">
			<form method="POST" commandName="customer" name="myform" id="myform"
				action="signup.do">
				<h3>Personal Details</h3>
				<h4 id="errormsg"></h4>
				<label>First Name* </label> <input type="text" name="firstName"
					id="firstName" class="form-control" /><br /> <label>Last
					Name* </label> <input type="text" name="lastName" id="lastName"
					class="form-control" /><br /> <label>Address* </label> <input
					type="text" id="address" name="address" class="form-control" /><br />
				<label>Date Of Birth* </label> <input type="text" id="dateOfBirth"
					name="dateOfBirth" class="form-control" /><br /> <label>Gender*
				</label> <input type="radio" name="gender" value="Male" /> <label>Male</label>
				<input type="radio" name="gender" value="Female" /> <label>Female</label><br>
				<label>Email* </label> <input type="text" id="email" name="email"
					class="form-control" /><br /> <label>Phone Number* </label> <input
					type="text" name="phoneNo" id="phoneNo" class="form-control" />

				<h3>User Account Details</h3>

				<label>Username* </label> <input type="text" id="username"
					name="username" onblur="ajaxFunction()" class="form-control" />
				<p id="match"></p>

				<label>Password* </label> <input type="password" name="password"
					class="form-control" /><br> <input type="submit"
					name="submit" id="submit" value="Register"
					class="form-control marginbot" onclick="return validateForm()"
					disabled />

			</form>
		</div>
	</div>
</body>
</html>