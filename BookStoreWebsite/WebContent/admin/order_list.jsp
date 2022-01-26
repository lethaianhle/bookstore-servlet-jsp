<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Manage Orders - Evergreen Bookstore Administrator</title>
<link rel="stylesheet" href="../css/style.css" />
<script type="text/javascript" src="../js/jquery-3.6.0.min.js"></script>
</head>
<body>
	<jsp:directive.include file="header.jsp" />
	<fmt:setLocale value = "en_US"/>
	<br>
	<hr width="60%">

	<div align="center">
		<h1 class="page-heading">
			Order Management
			</h2>
	</div>

	<c:if test="${message != null}">
		<div align="center">
			<h4 class="message">${message}</h4>
		</div>
	</c:if>
	
	<div align="center">
		<table border="1px" cellpadding="5px">
			<tr>
				<th>Index</th>
				<th>Order Id</th>
				<th>Ordered By</th>
				<th>Book Copies</th>
				<th>Total</th>
				<th>Payment Method</th>
				<th>Status</th>
				<th>Order Date</th>
				<th>Actions</th>
			</tr>
			<c:forEach var="order" items="${listOrder}" varStatus="status">
				<tr>
					<td>${status.index + 1}</td>
					<td>${order.orderId}</td>
					<td>${order.customer.fullname}</td>
					<td>${order.totalCopies}</td>
					<td><fmt:formatNumber value="${order.total}" type="currency" /></td>
					<td>${order.paymentMethod}</td>
					<td>${order.status}</td>
					<td>${order.orderDate}</td>
					<td>
						<a href="view_order?id=${order.orderId}">Details</a>
						&nbsp;
						<a href="edit_order?id=${order.orderId}">Edit</a>
						&nbsp; 
						<a href="javascript:void(0)" class="delete-link" id="${order.orderId}">Delete</a>
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
				orderId = $(this).attr("id");
				if(confirm("Are u sure want to delete order with id " + orderId + " ?")) {
					window.location = "delete_order?id=" + orderId;
				}
			});
		});
	});
</script>
</html>