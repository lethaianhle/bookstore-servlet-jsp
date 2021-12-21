<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Result for ${keyword}</title>
<link rel="stylesheet" href="css/style.css" />
</head>
<body>
	<jsp:directive.include file="header.jsp" />

	<c:if test="${fn:length(result) == 0 }">
		<h2 align="center" style="color: red">No Result for "${keyword}"</h2>
	</c:if>

	<c:if test="${fn:length(result) > 0 }">
		<div align="center">
			<h2>Result for "${keyword}":</h2>
			<div align="center" style="width: 80%; margin: 0 auto;">
				<c:forEach items="${result}" var="book">
					<div>
						<div style="display: inline-block; margin: 20px; width: 10%">
							<div align="left">
								<a href="view_book?id=${book.bookId}"> <img
									src="data:image/jpg;base64,${book.base64Image}" width="128"
									height="164" />
								</a>
							</div>
						</div>
						<div
							style="display: inline-block; margin: 20px; vertical-align: top; width: 60%"
							align="left">
							<div>
								<h2>
									<a href="view_book?id=${book.bookId}"><b>${book.title}</b></a>
								</h2>
							</div>
							<div>Rating *****</div>
							<div>
								<i>by ${book.author}</i>
							</div>
							<div>
								<p>${fn:substring(book.description, 0, 100)}...</p>
							</div>
						</div>
						<div
							style="display: inline-block; margin: 10px; vertical-align: top;">
							<h3>
								<b>$${book.price}</b>
							</h3>
							<h3>
								<a href="">Add to cart</a>
							</h3>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</c:if>


	<br>
	<br>
	<jsp:directive.include file="footer.jsp" />
</body>
</html>