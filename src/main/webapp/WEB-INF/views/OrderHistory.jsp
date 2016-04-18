<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
</head>
<body>
Your Order Details:-
<table>
			<tr>
	              		<th> orderID</th>
	              		<th> Order Total</th>
	                    <th> Order Status </th>
	                    <th> View Details </th>
            </tr>
          
	<c:forEach var="order" items="${orderList}">
	
		
			<tr>
				<td>${order.orderId}</td>
				<td>${order.orderTotal}</td>
				<td>${order.orderStatus }</td>
				<td><a href="viewPdf.do?orderId=${order.orderId}">View Details</td>
			</tr>
		<%-- <c:forEach var="orderItem" items="${order.orderItem}">
                    
                    
                    <tr>
                    	<td><c:out value="${order.orderId}"/></td> 
	                    <td><c:out value="${orderItem.drugProduct.name}"/></td>
	                    <td><c:out value="${orderItem.quantity}"/></td>
	                   		 
	                </tr>
	                <br/>
		
		</c:forEach> --%>
	</c:forEach>
</table>
</body>
</html>
<body>

</body>
</html>