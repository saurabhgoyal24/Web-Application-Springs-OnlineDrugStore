<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">

<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">

<!-- Latest compiled and minified JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<title>Change Password</title>
<script type="text/javascript">
	function changePwd() {

		var xmlhttpObj;
		var oldPwd = document.getElementById("oldPwdTF").value;

		if (window.XMLHttpRequest) {
			xmlhttpObj = new XMLHttpRequest(); //for IE, Firefox, Chrome, Opera, Safari
		} else {
			xmlhttpObj = new ActiveXObject("Microsoft.XMLHTTP"); //for IE6, IE5
		}

		xmlhttpObj.open("POST", "checkPassword.do", true);
		xmlhttpObj.setRequestHeader("oldPassword", oldPwd);

		xmlhttpObj.onreadystatechange = function() {
			if (xmlhttpObj.readyState == 4) {
				if (xmlhttpObj.status == 200) {
					if (xmlhttpObj.responseText == "true") {
						document.getElementById("textIfMatch").innerHTML = "correct password";
						document.getElementById("newPwdTF").disabled = false;
						document.getElementById("retypeNewPwd").disabled = false;
					} else {
						document.getElementById("textIfMatch").innerHTML = "Incorrect password";
						document.getElementById("newPwdTF").disabled = true;
						document.getElementById("retypeNewPwd").disabled = true;
					}
				} else {
					alert("XMLHTTP status is not 200");
				}
			}
		}
		xmlhttpObj.send(null);
	}
</script>
</head>
<body>
	<div class="container">
		<div class="row" align="center">
		<h2>Change your Password</h2>
			<form action="changePassword.do"  method="post">
				<table border='0'>
					

					<tr>
						<td>Enter old password:</td>
						<td><input type="text" id="oldPwdTF" onkeyup="changePwd()" /></td>
						<td><div id="textIfMatch"></div></td>
					</tr>

					<tr>
						<td>Enter NEW password:</td>
						<td><input type="text" id="newPwdTF" name="newPwdTF" disabled /></td>
					</tr>

					<tr>
						<td>Confirm New Password:</td>
						<td><input type="text" id="retypeNewPwd" disabled /></td>
					</tr>
					<tr>
						<td><input type="submit" class="btn btn-info"value="Change Password"></td>
					</tr>
				</table>
			</form>
			</div>
		</div>
</body>
</html>