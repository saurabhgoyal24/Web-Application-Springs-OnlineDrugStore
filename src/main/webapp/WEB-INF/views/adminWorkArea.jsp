<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	<script>
	
	</script>
<style>
.margin {
	margin-top: 60px;
}

.marginbot {
	margin-bottom: 50px;
}
</style>
<title>Admin Work Area</title>
</head>
<body>


	<div class="container">
		<h1>Admin Work Area</h1>
		<div id="menuButton" class="col-lg-1 col-lg-offset-9 margin">
			<form action="logMeOut.do" method="get">
				<input type="submit" value="Logout" class="form-control" />
			</form>
		</div>
		<div class="row col-lg-6 col-lg-offset-3 margin">
			<form action="goToAddSupplier.do" method="post">
				<input type="submit" name="addSupplier"
					value="Register a Drug Supplier" class="form-control" />
			</form>
			<br />

			<form action="goToAddDrugCategory.do" method="post">
				<input type="submit" name="addDrugCategory" value="Add DrugCategory"
					class="form-control" />
			</form>
		</div>
		
		<div class="col-lg-2 col-lg-offset-8">
			<form action="goToChangePassword.do" method="post">
				<input type="submit" value="Change Password" class="form-control" />
			</form>
		</div>
	</div>
	<!-- 
	<div id="menuButton">
		<form action="logMeOut.do" method="get">
			<input type="submit" value="Logout" class="form-control" />
		</form>
	</div>
 -->
</body>
</html>