<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">

<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">

<!-- Latest compiled and minified JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<title>Add Drug Product</title>
<style>
.margin {
	margin-top: 60px;
}

.marginbot {
	margin-bottom: 50px;
}
</style>
<script>
function myUploadFunction() {
    var x = document.getElementById("image").value;
    var filename = x.replace(/^.*\\/, "");
    
    if(filename=="")
    	{
    	alert("Invalid upload. Please try again");
    	return;
    	}
/*     document.getElementById("putImage").innerHTML = x;
    document.getElementById("putSample").innerHTML = y;
 */    
    document.getElementById('putImage').value = filename;
    document.getElementById("submit").disabled=false;
}
</script>

</head>
<body>
	<div class="container">
		<div id="menuButton" class="col-lg-1 col-lg-offset-9 margin">
			<form action="logMeOut.do" method="get">
				<input type="submit" value="Logout" class="form-control" />
			</form>
		</div>
		<div class="row col-lg-6 col-lg-offset-3">
			<form name="addProduct" action="addDrugProductToCategory.do"
				method="post">
				<h2>Enter New Product!</h2>
				<label class="col-sm-5" for="category">Category* </label> <select
					name="selectedDrugCategory" class="form-control">
					<c:forEach var="item" items="${listDrugCategory}">

						<option value="${item}">${item}</option>
						<br>
						<br>
						<br>
					</c:forEach>
				</select> <label>Product Name* </label> <input type="text" name="name"
					class="form-control" /><br /> <label>Product Description*
				</label> <input type="text" name="description" class="form-control" /><br />
				<label>Product Price* </label> <input type="text" name="price"
					class="form-control" /><br /> <label>Quantity* </label> <input
					type="text" name="quantity" class="form-control" /><br /> <label>Company
					Name</label> <input type="text" name="companyName" class="form-control" />
				<label>Upload Picture*</label> <input type="file" name="image" id="image"
					class="form-control" /><br /> 
					<input type="button" name="upload" value="Upload" onClick="myUploadFunction()"/>
					
					<!-- <input type="text"  name="uploadText" id="uploadText" /> -->
					
					<div class="form-group"> 
				<div class="input-group input-group-lg center-block">
					
					<input type="text" id="putImage" name="putImage" placeHolder="File not uploaded yet."/><br/>
					<p id="uploadError" class="text-danger col-sm-12"></p>
				</div>
				<input type="submit"
					value="Add Product" id= "submit" class="btn btn-primary btn-block btn-lg"
					tabindex="4" disabled />
					
			</div>
			</form>
		</div>
		<br /> <br />

	</div>
	<br/>
	<br/>
	<div class="col-lg-2 col-lg-offset-8">
			<form action="goToChangePassword.do" method="post">
				<input type="submit" value="Change Password" class="form-control" />
			</form>
		</div>
</body>
</html>