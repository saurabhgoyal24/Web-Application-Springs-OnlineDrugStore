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
<style>
.margin {
	margin-top: 60px;
}

.marginbot {
	margin-bottom: 50px;
}
</style>
<title>Add Drug Cartegory</title>
</head>
<body>
	<div class="container">
		<div class="row col-lg-6 col-lg-offset-3 margin">
			<h1>Add Drug Category</h1>
			<form action="addDrugCategory.do" method="post">
				Enter Drug Category <input type="text" name="newDrugCategory"
					class="form-control"> <input type="submit" name="submit"
					class="form-control margin">
			</form>
		</div>
	</div>
	<br/>
	<div id="menuButton" class="col-lg-1 col-lg-offset-9 margin">
			<form action="logMeOut.do" method="get">
				<input type="submit" value="Logout" class="form-control" />
			</form>
	</div>
	<br/>
	<br/>
	<a href="goToAdminHome.do">Click here to go back</a>
	
</body>
</html>