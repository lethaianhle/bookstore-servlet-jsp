<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Shopping Cart</title>
	<link rel="stylesheet" href="css/style.css" />
	<script type="text/javascript" src="js/jquery-3.6.0.min.js"></script>
	<script type="text/javascript" src="js/jquery.validate.min.js"></script>
</head>
<body>
	<jsp:directive.include file="header.jsp" />
	<br>
	<br>

	<c:set var="cart" value="${sessionScope['cart']}" />
	<fmt:setLocale value = "en_US"/>

	<div align="center">
		<h2>Shopping Cart</h2>
		<c:if test="${message != null}">
			<div align="center">
				<h4 class="message">${message}</h4>
			</div>
		</c:if>
		
		<c:if test="${cart.totalItems == 0}">
			<div align="center">
				<h2 style="color: blue">There is no Item in your Cart...</h2>
			</div>
		</c:if>
		
		<c:if test="${cart.totalItems > 0}">
			<div align="center">
				<form action="update_cart" method="post" id="cart-form">
				<div>
					<table border="1px silver solid" cellpadding="5px">
						<tr>
							<th>No</th>
							<th>Thumbnail</th>
							<th>Book</th>
							<th>Quantity</th>
							<th>Price</th>
							<th>Subtotal</th>
							<th>
								<a href="clear_cart">Clear Cart</a>
							</th>
						</tr>

						<c:forEach items="${cart.items}" var="item" varStatus="status">
							<tr>
							<td>${status.index + 1}</td>
							<td>
								<img style="width: 50px; height: auto" class="book-large" src="data:image/jpg;base64,${item.key.base64Image }" />
							</td>
							<td>
								${item.key.title }
							</td>
							<td>
								<input type="hidden" value="${item.key.bookId}" name="bookId" />
								<input type="text" name="quantity${status.index + 1}"  size="2px" value="${item.value}" />
							</td>
							<td><fmt:formatNumber value = "${item.key.price }" type = "currency"/></td>
							<td><fmt:formatNumber value = "${item.value * item.key.price}" type = "currency"/></td>
							<td>
								<a href="remove_from_cart?book_id=${item.key.bookId}">Remove</a>
							</td>			
							</tr>			
						</c:forEach>
						
						<tr>
							<td colspan="3"></td>
							<td>${cart.totalQuantity } book(s)</td>
							<td>TOTAL: </td>
							<td><b><fmt:formatNumber value = "${cart.totalAmount }" type = "currency"/></b></td>
						</tr>
					</table>
					</div>
					<br>
					<div>
						<table style="border: 0">
							<tr>
								<td><button type="submit">Update</button></td>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
								<td><a href="${pageContext.request.contextPath}/">Continue Shopping</a></td>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
								<td><a href="checkout">Checkout</a></td>
							</tr>
						</table>
					</div>
				</form>
			</div>
		</c:if>
		
		
	</div>
	
	<br>
	<br>
	<jsp:directive.include file="footer.jsp" />
</body>

<script type="text/javascript">
	$(document).ready(function() {
		$("#cart-form").validate({
			rules: {
				<c:forEach items="${cart.items}" var="item" varStatus="status">
					quantity${status.index + 1}: {
						required: true,
						number: true,
						min: 1
					},
				</c:forEach>
			},
			messages: {
				<c:forEach items="${cart.items}" var="item" varStatus="status">
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