<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" href="../css/style.css" />
	<link rel="stylesheet" href="../css/jquery-ui.min.css" />
	<link rel="stylesheet" href="//netdna.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" href="../css/richtext.min.css">
	
	
	<script type="text/javascript" src="../js/jquery-3.6.0.min.js"></script>
	<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
	<script type="text/javascript" src="../js/jquery-ui.min.js"></script>
	<script type="text/javascript" src="../js/jquery.richtext.min.js"></script>
<c:if test="${book != null}">
	<title>Edit Book</title>
</c:if>
<c:if test="${book == null}">
	<title>Create New Book</title>
</c:if>
</head>
<body>
	<jsp:directive.include file="header.jsp" />

	<div align="center">
		<c:if test="${book != null}">
			<h1 class="page-heading">Edit Book</h1>
		</c:if>
		<c:if test="${book == null}">
			<h1 class="page-heading">Create New Book</h1>
		</c:if>
		<hr width="60%">
	</div>

	<div align="center">
		<c:if test="${book != null}">
			<form action="update_book" method="post" id="book-form"
				enctype="multipart/form-data">
				<input type="hidden" name="book-id" value="${book.bookId}">
		</c:if>
		<c:if test="${book == null}">
			<form action="create_book" method="post" id="book-form"
				enctype="multipart/form-data">
		</c:if>
		<table class="form" style="width: 70%">
			<tr>
				<td align="right">Category:</td>
				<td><select name="category" id="category">
						<c:forEach items="${listCategory}" var="category">
							<c:if test="${category.categoryId eq book.category.categoryId }">
								<option value="${category.categoryId}" selected="selected">${category.name}</option>
							</c:if>
							<c:if test="${category.categoryId ne book.category.categoryId }">
								<option value="${category.categoryId}">${category.name}</option>
							</c:if>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td align="right">Title:</td>
				<td><input type="text" name="title" id="title" size="20"
					value="${book.title}"></td>
			</tr>
			<tr>
				<td align="right">Author:</td>
				<td><input type="text" name="author" size="20" id="author"
					value="${book.author}"></td>
			</tr>
			<tr>
				<td align="right">ISBN:</td>
				<td><input type="text" name="isbn" size="20" id="isbn"
					value="${book.isbn}"></td>
			</tr>
			<tr>
				<td align="right">Publish Date:</td>
				<td><input type="text" name="publishDate" size="20"
					id="publishDate"
					value="<fmt:formatDate pattern='MM/dd/yyyy' value='${book.publishDate}'/>" />
				</td>
			</tr>
			<tr>
				<td align="right">Book Image:</td>
				<td><input type="file" name="bookImage" size="20"
					id="bookImage"> <br> <img alt="Image Preview"
					id="thumbnail" style="width: 20%; margin-top: 10px"
					src="data:image/jpg;base64,${book.base64Image}" /></td>
			</tr>
			<tr>
				<td align="right">Price:</td>
				<td><input type="text" name="price" size="20" id="price"
					value="${book.price}"></td>
			</tr>
			<tr>
				<td align="right">Description:</td>
				<td><textarea rows="5" cols="50" name="description"
						id="description">${book.description}</textarea></td>
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
		$('#publishDate').datepicker();
		
		$('#description').richText();
		
		$('#bookImage').change(function() {
			showImageThumbnail(this);
		})
		
		$('#book-form').validate({
			rules : {
				category: "required",
				title : "required",
				author : "required",
				isbn : "required",
				publishDate : "required",
				<c:if test="${book == null}">
					bookImage : "required",
				</c:if>
				price : "required",
				description : "required"
			},
			messages : {
				category: "Category is required",
				title : "Title is required",
				author : "Author is required",
				isbn : "ISBN is required",
				publishDate : "Publish date is required",
				bookImage : "Book image is required",
				price : "price is required",
				description : "Description is required"
			}
		});

		$('#button-cancel').click(function() {
			history.go(-1);
		});
		
	});
	
	function showImageThumbnail(fileInput) {
		var file = fileInput.files[0];
		
		var reader = new FileReader();
		reader.onload = function(e) {
			$('#thumbnail').attr('src', e.target.result);
		};
		
		reader.readAsDataURL(file);
	}
</script>

</html>