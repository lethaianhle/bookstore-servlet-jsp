<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>My Order History - Evergreen Bookstore Administrator</title>
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
			My Order History
		</h1>
	</div>
	
	<c:if test="${fn:length(listOrders) == 0 }">
		<h3 align="center" style="color: blue">You have not place any order yet :(( </h3>
	</c:if>
	
	<c:if test="${fn:length(listOrders) > 0 }">
		<div align="center">
			<table border="1px" cellpadding="5px">
				<tr>
					<th>Index</th>
					<th>Order Id</th>
					<th>Quantity</th>
					<th>Total Amount</th>
					<th>Order Date</th>
					<th>Status</th>
					<th>Actions</th>
				</tr>
				<c:forEach var="order" items="${listOrders}" varStatus="status">
					<tr>
						<td>${status.index + 1}</td>
						<td>${order.orderId}</td>
						<td>${order.totalCopies}</td>
						<td><fmt:formatNumber value="${order.total}" type="currency" /></td>
						<td>${order.orderDate}</td>
						<td>${order.status}</td>
						<td>
							<a href="show_order_detail?id=${order.orderId}">View Details</a>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</c:if>

	<br>
	<br>
	<jsp:directive.include file="footer.jsp" />
</body>

</html>