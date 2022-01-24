<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Write Review</title>
	<link rel="stylesheet" href="css/style.css" />
</head>
<body>
	<jsp:directive.include file="header.jsp" />
	<br>
	<br>

	<div align="center">
		<form id="review-form" action="submit_review" method="post">
			<table style="border: 0; width: 60%">
				<tr>
					<td><h2>Your Reviews</h2></td>
					<td>&nbsp;</td>
					<td><h2>${loggedCustomer.fullname}</h2></td>
				</tr>
				<tr>
					<td colspan="3"><hr/></td>
				</tr>
				<tr>
					<td>
						<h3>${book.title}</h3>
						<img style="width: 250px; height: auto" class="book-large" src="data:image/jpg;base64,${book.base64Image }" />
					</td>
					<td colspan="2">
						<h3>Thank for your Review</h3>
					</td>
				</tr>
			</table>
		</form>
	</div>
	
	<br>
	<br>
	<jsp:directive.include file="footer.jsp" />
</body>

</html>