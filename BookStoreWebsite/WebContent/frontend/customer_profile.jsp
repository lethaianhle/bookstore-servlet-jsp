<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Evergreen Bookstore - Your Profile</title>
</head>
<body>
	<jsp:directive.include file="header.jsp" />

	<div align="center">
		<h1>
			Customer Profile
			</h2>
			<hr width="60%">
	</div>

	<div align="center" style="color: red">
		<h3>
			<i>${message}</i>
		</h3>
	</div>

	<div align="center">
		<br> <input type="button" value="Return"
			onclick="javascript:history.go(-1)">
	</div>

	<jsp:directive.include file="footer.jsp" />
</body>
</html>