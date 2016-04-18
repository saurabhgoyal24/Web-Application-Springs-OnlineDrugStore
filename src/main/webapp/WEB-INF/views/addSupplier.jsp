<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">

<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">

<!-- Latest compiled and minified JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<script
	src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.13.1/jquery.validate.min.js"></script>
<title>Add Drug Product</title>
<style>
.margin {
	margin-top: 60px;
}

.marginbot {
	margin-bottom: 50px;
}
</style>

<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<div class="row col-lg-6 col-lg-offset-3 margin">
			<h2>Enter supplier details</h2>
			<form action="addSupplier.do" id="register-form" method="POST" name="register-form">
			<div id="errors1"></div>
				<label>First Name</label> <input type="text" name="firstname"
					class="form-control"><label>Last Name</label> <input
					type="text" name="lastname" class="form-control"> <label>Username</label>
				<input type="text" name="username" id="username"
					onblur="ajaxFunction()" class="form-control">
				<p id="match"></p>

				<label>Password</label> <input type="password" name="password"
					class="form-control"> <input type="submit" name="submit"
					id="submit" class="form-control margin" disabled>

			</form>
		</div>
	</div>
	<br />
	<div id="menuButton" class="col-lg-1 col-lg-offset-9 margin">
		<form action="logMeOut.do" method="get">
			<input type="submit" value="Logout" class="form-control" />
		</form>
	</div>
	<br />
	<br />
	<a href="goToAdminHome.do">Click here to go back</a>
	<script type="text/javascript">
$(document).ready(function(){
$("#register-form").validate({
    
	 onfocusout: false,
	 errorElement: "div",
	 errorPlacement: function(error, element) {
        
		 error.appendTo("div#errors1");
    }, 
       // Specify the validation rules
       rules: {
       	firstname: "required",
           lastname: "required",
          
           username:"required",
           password:"required"
    
       },
       messages: {
       	firstName: "Please enter your first name",
           lastName: "Please enter your last name",
          
           username:"Please set a username",
           password:"Please set a password"
           
       },
       
       submitHandler: function(form) {
           form.submit();
       }
});
});

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
						document.getElementById("submit").disabled = true;
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
</script>
</body>
</html>