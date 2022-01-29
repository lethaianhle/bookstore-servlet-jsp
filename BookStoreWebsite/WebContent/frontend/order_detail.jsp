<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>My Order Detail - Evergreen Bookstore Administrator</title>
<link rel="stylesheet" href="../css/style.css" />
</head>
<body>
	<jsp:directive.include file="header.jsp" />
	<fmt:setLocale value = "en_US"/>
	<br>
	<hr width="60%">

	<div align="center">
		<h1 class="page-heading">
			Your OrderId: ${order.orderId}
		</h1>
	</div>
	
	<div align="center">
		<table border="1px" cellpadding="5px">
			<tr>
				<td>Order Status: </td>
				<td>${order.status }</td>
			</tr>
			<tr>
				<td>Order Date: </td>
				<td>${order.orderDate }</td>
			</tr>
			<tr>
				<td>Quantity: </td>
				<td>${order.totalCopies }</td>
			</tr>
			<tr>
				<td>Total Amount: </td>
				<td><fmt:formatNumber value="${order.total}" type="currency" /></td>
			</tr>
			<tr>
				<td>Recipient Name: </td>
				<td>${order.recipientName }</td>
			</tr>
			<tr>
				<td>Recipient Phone: </td>
				<td>${order.recipientPhone }</td>
			</tr>
			<tr>
				<td>Payment Method: </td>
				<td>${order.paymentMethod}</td>
			</tr>
			<tr>
				<td>Shipping Address: </td>
				<td>${order.shippingAddress }</td>
			</tr>
		</table>
	</div>
	
	<br>
	
	<div align="center">
		<h2>Ordered Books:</h2>
		<table border="1px" cellpadding="5px">
				<tr>
					<th>No </th>
					<th>Thumbnail</th>
					<th>Book Title</th>
					<th>Author</th>
					<th>Price</th>
					<th>Quantity</th>
					<th>Sub Total</th>
				</tr>
			<c:forEach items="${order.orderDetails}" var="item" varStatus="status">
				<tr>
					<td>${status.index + 1 } </td>
					<td><img style="width: 50px; height: auto" class="book-large" src="data:image/jpg;base64,${item.book.base64Image }" /></td>
					<td>${item.book.title }</td>
					<td>${item.book.author }</td>
					<td><fmt:formatNumber value="${item.book.price }" type="currency" /></td>
					<td>${item.quantity }</td>
					<td><fmt:formatNumber value="${item.subtotal }" type="currency" /></td>
				</tr>
			</c:forEach>
				<tr>
					<td colspan="5" align="right"><b>TOTAL: </b></td>
					<td>${order.totalCopies}</td>
					<td><fmt:formatNumber value="${order.total}" type="currency" /></td>
				</tr>
		</table>
	</div>
	
	<br>
	<br>
	<jsp:directive.include file="footer.jsp" />
</body>

</html>