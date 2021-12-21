<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Manage Users - Evergreen Bookstore Administrator</title>
<link rel="stylesheet" href="../css/style.css" />
<script type="text/javascript" src="../js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
</head>
<body>
	<jsp:directive.include file="header.jsp" />
	<br>
	<hr width="60%">

	<div align="center">
		<h1 class="page-heading">
			Users Management
			</h2>
	</div>

	<c:if test="${message != null}">
		<div align="center">
			<h4 class="message">${message}</h4>
		</div>
	</c:if>
	<div align="center">
		<table border="1px" cellpadding="5px">
			<caption style="margin-left: 60%; margin-bottom: 10px">
				<a href="user_form.jsp">Create new User</a>
			</caption>
			<tr>
				<th>Index</th>
				<th>Id</th>
				<th>Email</th>
				<th>Full Name</th>
				<th>Password</th>
				<th>Actions</th>
			</tr>
			<c:forEach var="user" items="${listUsers}" varStatus="status">
				<tr>
					<td>${status.index + 1}</td>
					<td>${user.userId}</td>
					<td>${user.email}</td>
					<td>${user.fullName}</td>
					<td>${user.password}</td>
					<td><a href="edit_user?id=${user.userId}">Edit</a> &nbsp; <a
						href="javascript:void(0)" class="delete-link" id="${user.userId}">Delete</a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>

	<br>
	<br>
	<jsp:directive.include file="footer.jsp" />
</body>

<script type="text/javascript">
$(document).ready(function() {
	$(".delete-link").each(function() {
		$(this).on("click", function() {
			userId = $(this).attr("id");
			if(confirm("Are u sure want to delete user with id " + userId + " ?")) {
				window.location = "delete_user?id=" + userId;
			}
		});
	});
});
</script>
</html>