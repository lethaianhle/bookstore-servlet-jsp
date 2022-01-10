<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" href="css/style.css" />
	<link rel="stylesheet" href="//netdna.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

	<script type="text/javascript" src="js/jquery-3.6.0.min.js"></script>
	<script type="text/javascript" src="js/jquery.validate.min.js"></script>
	<title>Register New Customer</title>
</head>
<body>
	<jsp:directive.include file="header.jsp" />

	<div align="center">
			<h1 class="page-heading">Register New Customer</h1>
		<hr width="60%">
	</div>

	<div align="center">
	<form action="register_customer" method="post" id="customer-form">
		<table class="form" style="width: 70%">
			<tr>
				<td align="right">Email:</td>
				<td><input type="text" name="email" id="email" size="45"></td>
			</tr>
			<tr>
				<td align="right">Full Name:</td>
				<td><input type="text" name="fullName" id="fullName" size="45"></td>
			</tr>
			<tr>
				<td align="right">Password:</td>
				<td><input type="password" name="password" size="45" id="password"></td>
			</tr>
			<tr>
				<td align="right">Confirm Password:</td>
				<td><input type="password" name="confirmPassword" size="45" id="confirmPassword"></td>
			</tr>
			<tr>
				<td align="right">Phone Number:</td>
				<td><input type="text" name="phone" size="45" id="phone"/></td>
			</tr>
			<tr>
				<td align="right">Address:</td>
				<td><input type="text" name="address" size="45" id="address"></td>
			</tr>
			<tr>
				<td align="right">City:</td>
				<td><input type="text" name="city" size="45" id="city"></td>
			</tr>
			<tr>
				<td align="right">Zip Code:</td>
				<td><input type="text" name="zipcode" size="45" id="zipcode"></td>
			</tr>
			<tr>
				<td align="right">Country:</td>
				<td><input type="text" name="country" size="45" id="country"></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<button type="submit">Save</button> &nbsp;&nbsp;
					<button type="button" id="button-cancel">Cancel</button>
				</td>
			</tr>
		</table>
		</form>
	</div>



	<jsp:directive.include file="footer.jsp" />
</body>

<script type="text/javascript">
	$(document).ready(function() {
		$('#customer-form').validate({
			rules : {
				email: {
					required: true,
					email: true
				},
				fullName : "required",
				password : "required",
				confirmPassword : {
					required: true,
					equalTo: "#password"
				},
				country : "required",
				city : "required",
				phone : "required",
				zipcode : "required",
				address: "required"
			},
			messages : {
				email: {
					required: "Email is required",
					email: "Email is must availid",
				},
				fullName : "Fullname is required",
				password : "Password is required",
				confirmPassword : {
					required: "Confirm password is required",
					equalTo: "Confirm password must same same"
				},
				country : "Country is required",
				city : "City is required",
				phone : "Phone is required",
				zipcode : "Zipcode is required",
				address: "Address is required"
			}
		});

		$('#button-cancel').click(function() {
			history.go(-1);
		});
		
	});
</script>

</html>