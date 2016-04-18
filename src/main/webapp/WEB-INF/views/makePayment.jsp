<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html>
<head>
<!-- Latest compiled and minified CSS -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

<!-- Latest compiled and minified JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<script
	src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.13.1/jquery.validate.min.js"></script>
<script src="<c:url value="/js/makePayment.js"/>"></script>
<style>

</style>
<title>Enter Payment Details</title>

</head>
<body>
	
	<div class="container">
		<h1 class="">
			Online <span>Drug Store	</span>
		</h1>
		<div id="" class="col-lg-5">
			<form name="makePayment" id="makePayment" action="makepayment.htm" method = "post">
				<fieldset>
				<input type = "hidden" name="orderId" value = "${orderId}">
					<!-- Form Name -->
					<legend>Make Payment</legend>
					<div class="form-group" id="errors"></div>

					<div class="form-group">
						<label class=" control-label" for="name">Name on Card</label>
						<div class="">
							<input id="name" name="name" class="form-control input-md"
								type="text" maxlength="100">
						</div>
					</div>

					<div class="form-group">
						<label class=" control-label" for="cardNumber">Card Number</label>
						<div class="">
							<input id="cardNumber1" name="cardNumber"
								class="form-control input-md" type="text" maxlength="4" onkeyup="jump(this)">
							<input id="cardNumber2" name="cardNumber"
								class="form-control input-md" type="text" maxlength="4" onkeyup="jump(this)">
							<input id="cardNumber3" name="cardNumber"
								class="form-control input-md" type="text" maxlength="4" onkeyup="jump(this)">
							<input id="cardNumber4" name="cardNumber"
								class="form-control input-md" type="text"maxlength="4" onkeyup="jump(this)">
						</div>
					</div>


					<!-- Select Basic -->
					<div class="form-group">
						<div id="info">
							<p id="errormsg" class="text-danger"></p>
						</div>
						<label class=" control-label" for="selectbasic">Expiration
							Date</label>
						<div class="exp">
							<select id="month" name="month" class="form-control">
								<option value="1">01</option>
								<option value="2">02</option>
								<option value="3">03</option>
								<option value="4">04</option>
								<option value="5">05</option>
								<option value="6">06</option>
								<option value="7">07</option>
								<option value="8">08</option>
								<option value="9">09</option>
								<option value="10">10</option>
								<option value="11">11</option>
								<option value="12">12</option>

							</select> <select id="year" name="year" class="form-control">
								<option value="13">2017</option>
								<option value="14">2018</option>
								<option value="15">2019</option>
								<option value="16">2020</option>
								<option value="17">2021</option>
								<option value="18">2022</option>

							</select>
						</div>
					</div>

					<!-- Password input-->
					<div class="form-group">
						<label class=" control-label" for="cvv">CVV</label>
						
						<div class="">
							<input id="cvv" name="cvv" class="form-control input-md"
								type="text" maxlength="3">
						</div>
					</div>

					<!-- Button -->
					<div class="form-group">
						<div class="">
							<input type="submit" class="btn btn-info" id="singlebutton"
								name="pay" value="Pay" />
						</div>
					</div>
				</fieldset>
			</form>
			
			<div class="col-lg-4 col-lg-offset-8">
				<form action="logMeOut.do" method="get">
				<input type="submit" value="Logout" class=" btn btn-info form-control" />
				</form>
			</div>

		</div>
		<div class="clearfix"></div>
	</div>
	
	<br/>
	<a href = "gotohomepage.htm">Click here to go home.</a>
</body>
</html>