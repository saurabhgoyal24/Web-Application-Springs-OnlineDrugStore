<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
<html>
<head>
<title>Home</title>
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
	margin-top: 100px;
}
</style>
</head>
<body>

	<div class="container">
		<div class="row col-lg-6 col-lg-offset-3 margin">
			<form:form method="POST" commandName="userAccount" action="login.do"
				class="form-horizontal h3" role="form" name="loginform">
				<div class="">
					<h2 class="">Please log in</h2>

					<form:input path="username" placeholder="Enter Username"
						class="form-control" size="300" />

					<h5>
						<form:errors path="username" />
					</h5>

					<form:input path="password" placeholder="Enter Password"
						class="form-control" size="300" />

					<h5>
						<form:errors path="password" />
					</h5>
					<br> <input type="checkbox" name="rememberMe"
						value="RememberMe" /> <label class="control-label"
						for="rememberme">Remember Me</label><br> <input type="submit"
						value="Login" name="login" />
				</div>

			</form:form>
			<p>Don't have an account?</p>
			<form:form action="goToSignUp.do">
				<input type="submit" value="Sign Up" name="signup" />
			</form:form>
		</div>
	</div>







	<%--  <div class="container">
			<div class="row">
				<div class="col-lg-2">
					<label>User name : </label>
				</div>
				<div class="col-lg-2">
					<form:input path="username" />
				</div>
				<div class="col-lg-2">
					<form:errors path="username" />
				</div>
			</div>
			<div class="row">
				<label>Password : </label>
				<form:input path="password" />
				<form:errors path="password" />
			</div>
			<div class="row">
				<input type="checkbox" name="rememberMe" value="rememberMe" />Remember
				me for 1 week
			</div>
			<div class="row">
				<input type="submit" name="logIn" value="Login" />
			</div>
		</div>


	</form:form>
	<form:form action="goToSignUp.do">
		<input type="submit" name="signUp" value="Sign Up" />
	</form:form>  --%>


</body>
</html>
