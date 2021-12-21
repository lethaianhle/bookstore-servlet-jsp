<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Evergreen Bookstore Administrator</title>
<link rel="stylesheet" href="../css/style.css" />
</head>
<body>
	<jsp:directive.include file="header.jsp" />

	<div align="center">
		<h1 class="page-heading">
			Administrator Dashboard
			</h2>
			<hr width="60%">
	</div>

	<div align="center">
		<h2 class="page-heading">Quick Actions:</h2>
		<a href="create_book">New Book</a> &nbsp; <a href="create_user">New
			User</a> &nbsp; <a href="create_catelogy">New Category</a> &nbsp; <a
			href="create_customer">New Customer</a> &nbsp; <br> <br>
		<hr width="60%">
	</div>

	<div align="center">
		<h2 class="page-heading">Recent Sales:</h2>
		<hr width="60%">
	</div>

	<div align="center">
		<h2 class="page-heading">Recent Reviews:</h2>
		<hr width="60%">
	</div>

	<div align="center">
		<h2 class="page-heading">Statistics:</h2>

	</div>

	<jsp:directive.include file="footer.jsp" />
</body>
</html>