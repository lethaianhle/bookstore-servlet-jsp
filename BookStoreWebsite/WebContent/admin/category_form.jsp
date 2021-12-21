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
<c:if test="${category != null}">
	<title>Edit Category</title>
</c:if>
<c:if test="${category == null}">
	<title>Create New Category</title>
</c:if>
</head>
<body>
	<jsp:directive.include file="header.jsp" />

	<div align="center">
		<c:if test="${category != null}">
			<h1 class="page-heading">Edit Category</h1>
		</c:if>
		<c:if test="${category == null}">
			<h1 class="page-heading">Create New Category</h1>
		</c:if>
		<hr width="60%">
	</div>

	<div align="center">
		<c:if test="${category != null}">
			<form action="update_category" method="post" id="category-form">
				<input type="hidden" name="category-id"
					value="${category.categoryId}">
		</c:if>
		<c:if test="${category == null}">
			<form action="create_category" method="post" id="category-form">
		</c:if>
		<table class="form">
			<tr>
				<td align="right">Name:</td>
				<td><input type="text" name="name" size="20" id="name"
					value="${category.name}"></td>
			</tr>
			<tr>
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
		$("#category-form").validate({
			rules: {
				name: "required"
			},
			messages: {
				name: "Name is required!"
			}
		});
		
		$("#button-cancel").click(function() {
			history.go(-1);
		});
	});
</script>

</html>