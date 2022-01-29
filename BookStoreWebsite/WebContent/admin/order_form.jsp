<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Edit Order</title>
	<link rel="stylesheet" href="../css/style.css" />
	<script type="text/javascript" src="../js/jquery-3.6.0.min.js"></script>
	<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
</head>
<body>
	<jsp:directive.include file="header.jsp" />
	<fmt:setLocale value = "en_US"/>
	<br>
	<hr width="60%">

	<div align="center">
		<h1 class="page-heading">
			Edit Order Id: ${order.orderId}
		</h1>
	</div>

	<c:if test="${message != null}">
		<div align="center">
			<h4 class="message">${message}</h4>
		</div>
	</c:if>
	
	<form action="update_order" method="post" id="order-form">
	<div align="center">
		<h2>Order Overview:</h2>
		<table style="border: 0" border="0" cellpadding="5px">
			<tr>
				<td>Order Date: </td>
				<td>${order.orderDate }</td>
			</tr>
			<tr>
				<td>Ordered By: </td>
				<td>${order.customer.fullname }</td>
			</tr>
			<tr>
				<td>Recipient Name: </td>
				<td><input type="text" name="recipientName" value="${order.recipientName }" size="45"/></td>
			</tr>
			<tr>
				<td>Recipient Phone: </td>
				<td><input type="text" name="recipientPhone" value="${order.recipientPhone }" size="45" /></td>
			</tr>
			<tr>
				<td>Shipping Address: </td>
				<td><input type="text" name="shippingAddress" value="${order.shippingAddress }" size="45" /></td>
			</tr>
			<tr>
				<td>Order Status: </td>
				<td>
					<select name="orderStatus">
						<option value="Processing" <c:if test="${order.status eq 'Processing' }">selected='selected'</c:if> >Processing</option>
						<option value="Shipping" <c:if test="${order.status eq 'Shipping' }">selected='selected'</c:if>>Shipping</option>
						<option value="Delivered" <c:if test="${order.status eq 'Delivered' }">selected='selected'</c:if>>Delivered</option>
						<option value="Completed" <c:if test="${order.status eq 'Completed' }">selected='selected'</c:if>>Completed</option>
						<option value="Cancelled" <c:if test="${order.status eq 'Cancelled' }">selected='selected'</c:if>>Cancelled</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>Payment Method: </td>
				<td>
					<select name="paymentMethod">
						<option value="Cash On Delivery">Cash On Delivery</option>
					</select>
				</td>
			</tr>
		</table>
	</div>
	
	<br>
	
	<div align="center">
		<h2>Ordered Books:</h2>
		<table border="1px" cellpadding="5px">
				<tr>
					<th>Index </th>
					<th>Thumbnail</th>
					<th>Book Title</th>
					<th>Author</th>
					<th>Price</th>
					<th>Quantity</th>
					<th>Sub Total</th>
					<th><a href="javascript:showAddBookPopUp()">Add Books</a></th>
				</tr>
			<c:forEach items="${order.orderDetails}" var="item" varStatus="status">
				<tr>
					<td>${status.index + 1 } </td>
					<td><img style="width: 50px; height: auto" class="book-large" src="data:image/jpg;base64,${item.book.base64Image }" /></td>
					<td>${item.book.title }</td>
					<td>${item.book.author }</td>
					<td>
						<input type="hidden" value="${item.book.price }" name="price" />
						<fmt:formatNumber value="${item.book.price }" type="currency" />
					</td>
					<td>
						<input type="hidden" value="${item.book.bookId}" name="bookId" />
						<input type="text" value="${item.quantity }" name="quantity${status.index + 1}" size="3"/>
					</td>
					<td><fmt:formatNumber value="${item.subtotal }" type="currency" /></td>
					<td><a href="remove_book_from_order?id=${item.book.bookId }">Remove</a></td>
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

	<div align="center">
		<button type="submit">Save</button>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<button type="button" onclick="javascript:window.location.href='list_order'">Cancel</button>
	</div>
	</form>
	<br>
	<br>
	<jsp:directive.include file="footer.jsp" />
</body>

<script type="text/javascript">
	function showAddBookPopUp() {
		var width = 600;
		var height = 250;
		var left = (screen.width - width / 2);
		var top = (screen.width - height / 2);
		window.open('add_book_form', '_blank', 'width=' + width + ', height=' + height + ', top=' + top + ', left=' + left);
	}
	
	$(document).ready(function() {
		$('#order-form').validate({
			rules : {
				recipientName : "required",
				recipientPhone : "required",
				shippingAddress : "required",
				<c:forEach items="${order.orderDetails}" var="item" varStatus="status">
					quantity${status.index + 1}: {
						required: true,
						number: true,
						min: 1
					},
				</c:forEach>
			},
			messages : {
				recipientName : "required",
				recipientPhone : "required",
				shippingAddress : "required",
				<c:forEach items="${order.orderDetails}" var="item" varStatus="status">
					quantity${status.index + 1}: {
						required: "Please enter quantity",
						number: "Quantity must be number",
						min: "Quantity must be greater than 0"
					},
				</c:forEach>
			}
		});	
	});
</script>
</html>










