<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Manage Books - Evergreen Bookstore Administrator</title>
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
			Books Management
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
				<a href="new_book">Create new Book</a>
			</caption>
			<tr>
				<th>Index</th>
				<th>Id</th>
				<th>Image</th>
				<th>Title</th>
				<th>Author</th>
				<th>Category</th>
				<th>Price</th>
				<th>Last Updated</th>
				<th>Actions</th>
			</tr>
			<c:forEach var="book" items="${listBooks}" varStatus="status">
				<tr>
					<td>${status.index + 1}</td>
					<td>${book.bookId}</td>
					<td><img src="data:image/jpg;base64,${book.base64Image}"
						width="84" height="100" /></td>
					<td>${book.title}</td>
					<td>${book.author}</td>
					<td>${book.category.name}</td>
					<td>$${book.price}</td>
					<td><fmt:formatDate pattern='MM/dd/yyyy'
							value='${book.lastUpdateTime}' /></td>
					<td><a href="edit_book?id=${book.bookId}">Edit</a> &nbsp; <a
						href="javascript:void(0)" class="delete-link" id="${book.bookId}">Delete</a>
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
			bookId = $(this).attr("id");
			if(confirm("Are u sure want to delete book with id " + bookId + " ?")) {
				window.location = "delete_book?id=" + bookId;
			}
		});
	});
});
</script>
</html>