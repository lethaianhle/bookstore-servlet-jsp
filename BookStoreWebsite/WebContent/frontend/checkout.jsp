<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Check Out - Online Book Store</title>
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
		<h1>Checkout</h1>
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
				<div>
					<h2>Review Your Order Details&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="view_cart">Edit</a></h2>
					<table border="1px silver solid" cellpadding="5px">
						<tr>
							<th>No</th>
							<th>Thumbnail</th>
							<th>Book</th>
							<th>Author</th>
							<th>Price</th>
							<th>Quantity</th>
							<th>Subtotal</th>
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
							<td>${item.key.author }</td>
							<td><fmt:formatNumber value = "${item.key.price }" type = "currency"/></td>
							<td>
								${item.value}
							</td>
							<td><fmt:formatNumber value = "${item.value * item.key.price}" type = "currency"/></td>			
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
					
					<div align="center">
					<h2 style="color: blue">Your Shipping Information</h2>
						<form id="order-form" action="place_order" method="post">
							<table style="border: 0">
								<tr>
									<td>Recipient Name: </td>
									<td>
										<input type="text" name="recipientName" value="${loggedCustomer.fullname }" />
									</td>
								</tr>
								<tr>
									<td>Recipient Phone: </td>
									<td>
										<input type="text" name="recipientPhone" value="${loggedCustomer.phone }" />
									</td>
								</tr>
								<tr>
									<td>Shipping Address: </td>
									<td>
										<input type="text" name="address" value="${loggedCustomer.address }" />
									</td>
								</tr>
								<tr>
									<td>City: </td>
									<td>
										<input type="text" name="city" value="${loggedCustomer.city }" />
									</td>
								</tr>
								<tr>
									<td>Zip Code: </td>
									<td>
										<input type="text" name="zipcode" value="${loggedCustomer.zipcode }" />
									</td>
								</tr>
								<tr>
									<td>Country: </td>
									<td>
										<input type="text" name="country" value="${loggedCustomer.country }" />
									</td>
								</tr>
								<tr>
									<td>Payment method: </td>
									<td>
										<select name="paymentMethod">
											<option value="Cast On Delivery">Cast On Delivery</option>
										</select>
									</td>
								</tr>
							</table>
							
							<br>
							<br>
							
							<div>
								<button type="submit">Place Order</button>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<a href="${pageContext.request.contextPath}/">Continue Shopping</a>
							</div>
						</form>
					</div>
					
			</div>
		</c:if>
		
		
	</div>
	
	<br>
	<br>
	<jsp:directive.include file="footer.jsp" />
</body>

<script type="text/javascript">
	$(document).ready(function() {
		$("#order-form").validate({
			rules: {
				recipientName: "required",
				recipientPhone: "required",
				address: "required",
				city: "required",
				zipcode: "required",
				country: "required"
			},
			message: {
				recipientName: "required",
				recipientPhone: "required",
				address: "required",
				city: "required",
				zipcode: "required",
				country: "required"
			}
		});
	});

</script>

</html>