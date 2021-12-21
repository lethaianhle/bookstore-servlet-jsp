<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Manage Customers - Evergreen Bookstore Administrator</title>
	<link rel="stylesheet" href="../css/style.css" />
	<script type="text/javascript" src="../js/jquery-3.6.0.min.js"></script>
</head>
<body>
	<jsp:directive.include file="header.jsp" />
	<br>
	<hr width="60%">

	<div align="center">
		<h1 class="page-heading">
			Customers Management
			</h2>
	</div>

	<c:if test="${message != null}">
		<div align="center">
			<h4 class="message">${message}</h4>
		</div>
	</c:if>
	<div align="center">
		<table border="1px" cellpadding="5px">
			<caption style="margin-left: 60%; margin-bottom: 10px">
				<a href="customer_form.jsp">Create new Customer</a>
			</caption>
			<tr>
				<th>Index</th>
				<th>Id</th>
				<th>E-mail</th>
				<th>Full Name</th>
				<th>City</th>
				<th>Country</th>
				<th>Registered Date</th>
				<th>Actions</th>
			</tr>
			<c:forEach var="customer" items="${listCustomer}" varStatus="status">
				<tr>
					<td>${status.index + 1}</td>
					<td>${customer.customerId}</td>
					<td>${customer.email }</td>
					<td>${customer.fullname}</td>
					<td>${customer.city}</td>
					<td>${customer.country}</td>
					<td>${customer.registerDate}</td>
					<td><a href="edit_customer?id=${customer.customerId}">Edit</a> &nbsp; <a
						href="javascript:void(0)" class="delete-link" id="${customer.customerId}">Delete</a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>

	<br>
	<br>
	<jsp:directive.include file="footer.jsp" />
</body>

<script type="text/javascript">
$(document).ready(function() {
	$(".delete-link").each(function() {
		$(this).on("click", function() {
			customerId = $(this).attr("id");
			if(confirm("Are u sure want to delete customer with id " + customerId + " ?")) {
				window.location = "delete_customer?id=" + customerId;
			}
		});
	});
});
</script>
</html>