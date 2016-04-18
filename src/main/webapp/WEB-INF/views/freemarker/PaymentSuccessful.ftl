<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Order Ready</title>
</head>
<body>
<h1>Payment successful</h1>
<br/>
<br/>
<form method="get" action="viewPdf.do">
<input type="hidden" name = "orderId" value="${orderId}" />
<input type = "hidden" name = "paymentId" value="${paymentId}"/>

<input type="submit" name = "submit" value="Generate Invoice PDF" />

</form>
<br/>
<br/>
<h3>Your Order will be on your way shortly</h3>

<br/>
<br/>
<a href="gotohomepage.htm">Go to home page</a>
<br/>
<br/>
	<div class="col-lg-2 col-lg-offset-8">
		<form action="logMeOut.do" method="get">
			<input type="submit" value="Logout" class="form-control" />
		</form>
	</div>
	
	<br/>
	<a href = "gotohomepage.htm">Click here to go home.</a>
</body>
</html>