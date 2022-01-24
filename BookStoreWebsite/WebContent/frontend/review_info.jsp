<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Write Review</title>
	<link rel="stylesheet" href="css/style.css" />
	<script type="text/javascript" src="js/jquery-3.6.0.min.js"></script>
	<script type="text/javascript" src="js/jquery.validate.min.js"></script>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/rateYo/2.3.2/jquery.rateyo.min.css">
	<script src="https://cdnjs.cloudflare.com/ajax/libs/rateYo/2.3.2/jquery.rateyo.min.js"></script>
</head>
<body>
	<jsp:directive.include file="header.jsp" />
	<br>
	<br>

	<div align="center">
		<form>
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
						<div id="rateYo"></div>
						<br/>
						<input type="text" name="headline" size="60" value="${customerReview.headline}" disabled="disabled"/>
						<br/>
						<br/>
						<textarea name="comment" rows="10" cols="70" disabled="disabled">${customerReview.comment}</textarea>
					</td>
				</tr>
				<tr>
					<td colspan="3" align="center">
						<h3 style="color: blue;">You can only write one review for each book and cannot fix it! You are already wrote one here :l</h3>
					</td>
				</tr>
			</table>
		</form>
	</div>
	
	<br>
	<br>
	<jsp:directive.include file="footer.jsp" />
</body>

<script type="text/javascript">
	$(document).ready(function() {
		$("#rateYo").rateYo({
			starWidth: "40px",
			fullStar: true,
			readOnly: true,
			rating: ${customerReview.rating}
		});
	});
</script>

</html>