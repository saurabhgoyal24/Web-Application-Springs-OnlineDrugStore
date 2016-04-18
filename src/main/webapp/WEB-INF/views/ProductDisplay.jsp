<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
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
<title>List of Products</title>
</head>
<body>
	<h2>Product List</h2>
	<div class="row">
		<table class="col-lg-offset-4">
			<tr>
				<td class="col-lg-3">Product Name</td>
				<td class="col-lg-4">Show Details</td>
				
			</tr>
			<c:forEach var="product" items="${productList}">
				<tr>
					<td class="col-lg-3"><c:out value="${product.name}" /></td>
					<td class="col-lg-4"><a
						href="showProductDetails.htm?productID=${product.drugId}">
							Show Product Details</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>

	<div class="col-lg-2 col-lg-offset-8">
		<form action="logMeOut.do" method="get">
			<input type="submit" value="Logout" class="btn btn-info form-control" />
		</form>
	</div>
	<br />
	<br />
	<div class="col-lg-2 col-lg-offset-8">
		<a href="viewmycart.htm" class="btn btn-info form-control text-center">View
			Cart</a>
	</div>
	<br />
	<br />
	<a href="gotohomepage.htm">Click here to go home.</a>
</body>
</html>