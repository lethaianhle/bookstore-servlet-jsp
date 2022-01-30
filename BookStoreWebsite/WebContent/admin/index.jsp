<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Evergreen Bookstore Administrator</title>
<link rel="stylesheet" href="../css/style.css" />
</head>
<body>
	<jsp:directive.include file="header.jsp" />

	<fmt:setLocale value = "en_US"/>
	<div align="center">
		<h1 class="page-heading">
			Administrator Dashboard
		</h1>
		<hr width="60%">
	</div>

	<div align="center">
		<h2 class="page-heading">Quick Actions:</h2>
		<a href="book_form.jsp">New Book</a> &nbsp; 
		<a href="user_form.jsp">New User</a> &nbsp; 
		<a href="category_form.jsp">New Category</a> &nbsp; 
		<a href="customer_form.jsp">New Customer</a> &nbsp; 
		
		<br> <br>
		<hr width="60%">
	</div>

	<div align="center">
		<h2 class="page-heading">Recent Sales:</h2>	
		<table border="1">
			<tr>
				<th>Order Id</th>
				<th>Ordered By</th>
				<th>BookCopies</th>
				<th>Total</th>
				<th>Payment Method</th>
				<th>Status</th>
				<th>Order Date</th>
			</tr>
			<c:forEach items="${listMostRecentSales }" var="order" varStatus="status">
				<tr>
					<td><a href="view_order?id=${order.orderId}">${order.orderId }</a></td>
					<td>${order.customer.fullname }</td>
					<td>${order.totalCopies }</td>
					<td><fmt:formatNumber value="${order.total}" type="currency" /></td>
					<td>${order.paymentMethod }</td>
					<td>${order.status }</td>
					<td>${order.orderDate }</td>
				</tr>
			</c:forEach>
		</table>
		<br>
		<br>
		<hr width="60%">
	</div>

	<div align="center">
		<h2 class="page-heading">Recent Reviews:</h2>
		<table border="1">
			<tr>
				<th>Book</th>
				<th>Rating</th>
				<th>HeadLine</th>
				<th>Customer</th>
				<th>Review On</th>
			</tr>
			<c:forEach items="${listMostRecentReviews }" var="review" varStatus="status">
				<tr>
					<td>${review.book.title }</td>
					<td>${review.rating}</td>
					<td><a href="edit_review?id=${review.reviewId}">${review.headline}</a></td>
					<td>${review.customer.fullname }</td>
					<td>${review.reviewTime }</td>
				</tr>
			</c:forEach>
		</table>
		<br>
		<br>	
		<hr width="60%">
	</div>

	<div align="center">
		<h2 class="page-heading">Statistics:</h2>
		Total Users: ${totalUsers} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		Total Books: ${totalBooks } &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		Total Customers: ${totalCustomers } &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		Total Reviews: ${totalReviews } &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		Total Orders: ${totalOrders } &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		
		<br>
		<br>	
		<hr width="60%">
	</div>

	<jsp:directive.include file="footer.jsp" />
</body>
</html>