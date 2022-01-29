<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Book to Order</title>
</head>
<body>
	<div align="center">
		<h2>Add Book to Order Id: ${order.orderId }</h2>
		<form action="add_book_to_order" method="post">
			<table>
				<tr>
					<td>Select a book: </td>
					<td>
						<select name="bookId" >
							<c:forEach items="${listBook}" var="book" varStatus="status">
								<option value="${book.bookId}">${book.title} - ${book.author }</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr><td>&nbsp;</td></tr>
				<tr>
					<td>Numbers of copies: </td>
					<td>
						<select name="quantity" >
							<option value="1">1</option>
							<option value="2">2</option>
							<option value="3">3</option>
							<option value="4">4</option>
							<option value="5">5</option>
						</select>
					</td>
				</tr>
				<tr><td>&nbsp;</td></tr>
				<tr>
					<td colspan="2" align="center">
						<button type="submit">Add</button>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" onclick="javascript:self.close()">Cancel</button>
					 </td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>