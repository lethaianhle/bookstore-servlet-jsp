<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Evergreen Bookstore - Your Profile</title>
</head>
<body>
	<jsp:directive.include file="header.jsp" />

	<div align="center">
		<h2>
			Customer Profile
		</h2>
		<hr width="60%">
	</div>

	<div align="center">
		<h2>
			Welcome, ${loggedCustomer.fullname}
		</h2>
	</div>
	
	<br>
	
	<div align="center">
	<table border="0">
		<tr>
			<td><b>Email Address: </b></td>
			<td>${loggedCustomer.email}</td>
		</tr>
		<tr>
			<td><b>Full Name: </b></td>
			<td>${loggedCustomer.fullname}</td>
		</tr>
		<tr>
			<td><b>Phone Number: </b></td>
			<td>${loggedCustomer.phone}</td>
		</tr>
		<tr>
			<td><b>Address: </b></td>
			<td>${loggedCustomer.address}</td>
		</tr>
		<tr>
			<td><b>City: </b></td>
			<td>${loggedCustomer.city}</td>
		</tr>
		<tr>
			<td><b>Zip Code: </b></td>
			<td>${loggedCustomer.zipcode}</td>
		</tr>
		<tr>
			<td><b>Country: </b></td>
			<td>${loggedCustomer.country}</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td colspan="2" align="center"><a href="edit_profile"><b>Edit My Profile</b></a></td>
		</tr>
	</table>
	</div>


	<jsp:directive.include file="footer.jsp" />
</body>
</html>















