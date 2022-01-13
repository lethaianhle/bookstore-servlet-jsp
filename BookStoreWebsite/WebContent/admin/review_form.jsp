<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" href="../css/style.css" />
	<script type="text/javascript" src="../js/jquery-3.6.0.min.js"></script>
	<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
	<title>Edit Review</title>

</head>
<body>
	<jsp:directive.include file="header.jsp" />

	<div align="center">
		<h1 class="page-heading">Edit Review</h1>
		<hr width="60%">
	</div>

	<div style="margin: auto; padding: auto" align="right">
	<form action="update_review" method="post" id="review-form">
		<input type="hidden" name="review-id" value="${review.reviewId}">
		
		<table class="form">
			<tr>
				<td align="right">Book:</td>
				<td align="left"><input style="background-color: silver" type="text" name="book" size="20" id="book" value="${review.book.title}" readonly="readonly"></td>
			</tr>
			<tr>
				<td align="right">Rating:</td>
				<td align="left"><input style="background-color: silver" type="text" name="rating" size="20" id="rating" value="${review.rating}" readonly="readonly"></td>
			</tr>
			<tr>
				<td align="right">Customer:</td>
				<td align="left"><input style="background-color: silver" type="text" name="customer" size="20" id="customer" value="${review.customer.fullname}" readonly="readonly"></td>
			</tr>
			<tr>
				<td align="right">Headline:</td>
				<td align="left"><input type="text" name="headline" size="60" id="headline" value="${review.headline}"></td>
			</tr>			
			<tr>
				<td align="right">Comment:</td>
				<td align="left">
					<textarea rows="5" cols="70" name="comment">${review.comment}</textarea>
				</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<button type="submit">Save</button> &nbsp;&nbsp;
					<button type="button" id="button-cancel">Cancel</button>
				</td>
			</tr>
		</table>
	</form>
	</div>

	<jsp:directive.include file="footer.jsp" />
</body>

<script type="text/javascript">
	$(document).ready(function() {
		$("#review-form").validate({
			rules: {
				headline: "required",
				comment: "required"
			},
			messages: {
				headline: "Headline is required!",
				comment: "Comment is required!"
			}
		});
		
		$("#button-cancel").click(function() {
			history.go(-1);
		});
	});
</script>

</html>