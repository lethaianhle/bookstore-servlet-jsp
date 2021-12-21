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
<c:if test="${user != null}">
	<title>Edit User</title>
</c:if>
<c:if test="${user == null}">
	<title>Create New User</title>
</c:if>
</head>
<body>
	<jsp:directive.include file="header.jsp" />

	<div align="center">
		<c:if test="${user != null}">
			<h1 class="page-heading">Edit User</h1>
		</c:if>
		<c:if test="${user == null}">
			<h1 class="page-heading">Create New User</h1>
		</c:if>
		<hr width="60%">
	</div>

	<div align="center">
		<c:if test="${user != null}">
			<form action="update_user" method="post" id="user-form">
				<input type="hidden" name="user-id" value="${user.userId}">
		</c:if>
		<c:if test="${user == null}">
			<form action="create_user" method="post" id="user-form">
		</c:if>
		<table class="form">
			<tr>
				<td align="right">Email:</td>
				<td><input type="text" name="email" id="email" size="20"
					value="${user.email}"></td>
			</tr>
			<tr>
				<td align="right">Full name:</td>
				<td><input type="text" name="fullname" size="20" id="fullname"
					value="${user.fullName}"></td>
			</tr>
			<tr>
				<td align="right">Password:</td>
				<td><input type="password" name="password" size="20"
					id="password" value="${user.password}"></td>
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
		$("#user-form").validate({
			rules: {
				email: {
					required: true,
					email: true
				},
				fullname : "required",
				password: "required"
			},
			messages: {
				email: {
					required: "Email is required",
					email: "Not an valid email!"
				},
				fullname : "Fullname is required!",
				password: "Password is required!"
			}
		});
		
		$("#button-cancel").click(function() {
			history.go(-1);
		});
	});

</script>

</html>