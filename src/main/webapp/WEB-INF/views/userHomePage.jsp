<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
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

#myForm input, #myForm a{
margin-bottom:10px;
}
</style>

<script type="text/javascript">
	function ajaxFunction() {

		var xmlhttp;
		var productName = document.getElementById("drugName").value;
		var category = document.getElementById("selectbasic").value;

		//document.getElementById("match").innerHTML = oldPass;

		if (window.XMLHttpRequest) {
			xmlhttp = new XMLHttpRequest(); //for IE7+, Firefox, Chrome, Opera, Safari
		} else {
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP"); //for IE6, IE5
		}

		xmlhttp.open("POST", "checkforproduct.doasap", true);
		xmlhttp.setRequestHeader("productName", productName);
		xmlhttp.setRequestHeader("category", category);

		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState == 4) {
				if (xmlhttp.status == 200) {
					/* if(xmlhttp.responseText == "false"){
						document.getElementById("match").innerHTML = "Quantity invalid";
						document.getElementById("submit").disabled = true;
					}else{
						document.getElementById("match").innerHTML = "Quantity available";
						document.getElementById("submit").disabled = false;
					} */
					//document.getElementById("match").innerHTML = xmlhttp.responseText;
					//alert(xmlhttp.responseText);
					document.getElementById("match").innerHTML = xmlhttp.responseText;
				} else {
					alert('Something is wrong !!');
				}
			}
		}
		xmlhttp.send(null);
	}
</script>


<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Customer Home Page</title>
</head>
<body>
	<div class="container" id="myForm">
		<h3>Welcome ${user.username}</h3>
		<div class="col-lg-2 col-lg-offset-8">
			<form action="logMeOut.do" method="get" >
				<input type="submit" value="Logout" class="form-control btn btn-info" />
			</form>
		</div>
		<div class="col-lg-2 col-lg-offset-8">
			<a href="viewmycart.htm" class="form-control text-center btn btn-info">View
				Cart</a>
		</div>
		<div class="col-lg-2 col-lg-offset-8">
			<form method="POST" action="getOrderHistory.htm" role="form">
				<input type="hidden" name="customerID" value="${customerID}" />
				<div>
					<div>
						<input type="submit" name="orderHistory" value="My History"
							class="form-control btn btn-info" />
					</div>
				</div>
			</form>
		</div>
		<div class="col-lg-2 col-lg-offset-8">
			<form action="goToChangePassword.do" method="post">
				<input type="submit" value="Change Password" class="form-control btn btn-info" />
			</form>
		</div>
		<div class="row col-lg-6 col-lg-offset-3">
			<h3>Search</h3>

			<!-- Select Basic -->
			<form name="myform" action="getProducts.htm" method="post">

				<div id="info"></div>
				<label>Select Category</label> <select id="selectbasic"
					name="category" class="form-control">
					<c:forEach var="item" items="${listDrugCategory}">
						<option value="${item}">${item}</option>
					</c:forEach>
				</select>

				<!-- Text input-->

				<label for="productName">Product Name</label> <input id="drugName"
					name="productName" type="text" maxlength="100"
					onkeyup="ajaxFunction()" class="form-control"> <input
					type="submit" class="btn btn-info form-control margin" /> <br /> <br />
				Suggestions:
				<div id="match"></div>
			</form>
			<br /> <br /> <br />


			<div>
				<h3>Quick Search</h3>
				<form action="quickSearch.do" method="post">
					<div>
						<label>Drug Name:</label><input type="text" name="drugName"
							class="form-control" />
					</div>
					<div>
						<label>Company Name:</label> <select id="selectbasic"
							name="company" class="form-control">
							<c:forEach var="item" items="${companyList}">
								<option value="${item}">${item}</option>
							</c:forEach>
						</select>
					</div>
					<div>
						<label>Price from:</label><input type="text" name="priceFrom"
							class="form-control"> <label>Price To:</label><input
							type="text" name="priceTo" class="form-control">
					</div>
					<div>
						<label>Category:</label><select id="selectbasic" name="category"
							class="form-control marginbot">
							<c:forEach var="item" items="${listDrugCategory}">
								<option value="${item}">${item}</option>
							</c:forEach>
						</select>
					</div>
					<div>
						<input type="submit" name="submit" value="Search"
							class="form-control btn btn-info marginbot">
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>