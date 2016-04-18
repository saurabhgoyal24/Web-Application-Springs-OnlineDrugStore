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
<title>Shopping Cart</title>
<style>
table tr td{
margin:10px;
padding:10px;

}
</style>
</head>
<body>
<div class="container">
	<div class="row">
	<h2>Following order Items added to your cart:</h2>
	
	<table class="col-lg-offset-3">
		<tr>
			<td><b>Product Name</b></td>
			<td><b>Product Quantity</b></td>
			<td><b>Price</b></td>
			<td><b>Delete OrderItem</b></td>
		</tr>
		<c:forEach var="orderItem" items="${cart.orderItem}">
		
		<tr>
	    	<td><c:out value="${orderItem.drugProduct.name}"/></td>
	        <td>
	        	<form method="get" action = "updatequantity.htm">
	        		<input type="hidden" name = "orderItemId" value = "${orderItem.orderItemId}"/>
	        		<input type="hidden" name = "orderId" value = "${cart.orderId}"/>
	        		<input type = "text"  name = "${orderItem.orderItemId}" value = "${orderItem.quantity}"/>
	        		<input type="submit" value = "update quantity"/>
	        	</form>
	        </td>
	        <td><c:out value="${orderItem.totalCost}"></c:out></td>
	        <td><a href="deleteOrderItem.htm?orderID=${cart.orderId}&orderItemid=${orderItem.orderItemId}"> Delete Order Item </a> </td>
			<!-- <td><input type="submit" value = "update quantity"/></td> -->
			<%-- <td><a href="updatequantity.htm?orderItemId=${orderItem.orderItemId}">Update</a></td> --%>
		</tr>
		
	</c:forEach>
		<tr>
			<td> </td>
			<td> </td>
			<td><b>Order Total: <c:out value="${cart.orderTotal}"/></b></td>
			<td></td>
		</tr>
	</table>
	</div>

	<a href = "proceedforpayment.htm?orderId=${cart.orderId}">Proceed For Checkout</a>
	<br/>
	<br/>
	<div class="col-lg-2 col-lg-offset-8">
		<form action="logMeOut.do" method="get">
			<input type="submit" value="Logout" class="btn btn-info form-control" />
		</form>
	</div>
	<br/>
	<a href = "gotohomepage.htm">Click here to go home.</a>
	</div>
</body>
</html>