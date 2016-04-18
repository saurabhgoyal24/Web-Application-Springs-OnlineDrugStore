<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">

<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">

<!-- Latest compiled and minified JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>

<script type="text/javascript">
	
		function ajaxFunction(){
		
		var xmlhttp;
		var productId = document.getElementById("productId").value;
		var quantity = document.getElementById("quantity").value;
		
		//document.getElementById("match").innerHTML = oldPass;
		if(quantity == ""){
			alert("Enter valid quantity.");
			document.getElementById("submit").disabled = true;
			return;
		}
		if (window.XMLHttpRequest){
	        xmlhttp = new XMLHttpRequest(); //for IE7+, Firefox, Chrome, Opera, Safari
	    } else {
	      xmlhttp = new ActiveXObject("Microsoft.XMLHTTP"); //for IE6, IE5
	    }
		
		
		xmlhttp.open("POST", "checkforquantity.do", true);
		xmlhttp.setRequestHeader("productId", productId);
		xmlhttp.setRequestHeader("quantity", quantity);
		
		xmlhttp.onreadystatechange = function(){
			if(xmlhttp.readyState == 4){
				if (xmlhttp.status == 200)
	            {
					if(xmlhttp.responseText == "false"){
						document.getElementById("match").innerHTML = "Quantity invalid";
						document.getElementById("submit").disabled = true;
					}else{
						document.getElementById("match").innerHTML = "Quantity available";
						document.getElementById("submit").disabled = false;
					}
	                //document.getElementById("match").innerHTML = xmlhttp.responseText;
	                //alert(xmlhttp.responseText);
	            }
				else
	            {
	                alert('Something is wrong !!');
	                document.getElementById("submit").disabled = true;
	            }
			}		
		}
		xmlhttp.send(null);
	}
</script>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Product Details </title>
</head>
<body>
<h2>Product Details</h2>
<div class="row">
	<form action="addToCart.htm" method="post" class="col-lg-offset-5">

		<input type="hidden" name="productID" id = "productId" value="${requestScope.product.drugId}"/>
		<table>
			<tr>
				<td>Details for Product :-</td>
				<td>${requestScope.product.name}</td>
			</tr>			
			<tr>
				<td>Price :-</td>
				<td>${requestScope.product.price}</td>
			</tr>			
			<tr>
				<td>Quantity :-</td>
				<td>${requestScope.product.quantity}</td>
			</tr>			
			<tr>
				<td>Description :-</td>
				<td>${requestScope.product.description}</td>
			</tr>
			<tr>
				<td>Quantity:</td>
				<td><input type="text" id="quantity" name="quantity" onkeyup="ajaxFunction()"/></td>
				<td><p id="match"></p></td>
			</tr>
			
			<tr>
				<td>Image:</td>
				<td><img alt="product" src="resources/images/${requestScope.product.path}"/></td>
				<td><p id="match"></p></td>
			</tr>
		</table>
		
		<br/>
		<br/>
		<input type="submit" id = "submit" value="Add to cart" disabled/>

	</form>
	</div>
	
	<div class="col-lg-2 col-lg-offset-8">
		<form action="logMeOut.do" method="get">
			<input type="submit" value="Logout" class="btn btn-info form-control" />
		</form>
	</div>
	
	<div class="col-lg-2 col-lg-offset-8">
			<a href="viewmycart.htm" class="btn btn-info form-control text-center">View
				Cart</a>
	</div>
	
	<br/>
	<a href = "gotohomepage.htm">Click here to go home.</a>
</body>
</html>