package com.bookstore.service;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.bookstore.dao.BookDAO;
import com.bookstore.dao.CategoryDAO;
import com.bookstore.entity.Book;
import com.bookstore.entity.Category;

public class BookServices {
	private BookDAO bookDAO;
	private CategoryDAO categoryDAO;
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	public BookServices(HttpServletRequest request, HttpServletResponse response) {
		super();
		this.request = request;
		this.response = response;
		bookDAO = new BookDAO();
		categoryDAO = new CategoryDAO();
	}
	
	public void listBooks() throws ServletException, IOException {
		listBooks(null);
	}

	public void listBooks(String message) throws ServletException, IOException {
		List<Book> listBooks = bookDAO.listAll();
		request.setAttribute("listBooks", listBooks);
		
		if(message != null) {
			request.setAttribute("message", message);
		}
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("book_list.jsp");
		requestDispatcher.forward(request, response);
	}
	
	public void showBookNewForm() throws ServletException, IOException {
		List<Category> listCategory = categoryDAO.listAll();
		request.setAttribute("listCategory", listCategory);
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("book_form.jsp");
		requestDispatcher.forward(request, response);
	}

	public void createBook() throws ServletException, IOException {
//		int categoryId = Integer.parseInt(request.getParameter("category"));
//		Category category = categoryDAO.get(categoryId);
		String title = request.getParameter("title");
		
		Book existedBook = 	bookDAO.findByTitle(title);
		if(existedBook != null) {
			String message = "Book Title already exist!";
			listBooks(message);
			return;
		}
		
//		String author = request.getParameter("author");
//		String isbn = request.getParameter("isbn");
//		String description = request.getParameter("description");
//		float price = Float.parseFloat(request.getParameter("price"));
//		Date publishDate = null;
//		
//		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yy");
//		try {
//			publishDate = dateFormat.parse(request.getParameter("publishDate"));
//		} catch (ParseException e) {
//			throw new ServletException("Error parsing Publish date!");
//		}
//		
//		Book book = new Book();
//		book.setTitle(title);
//		book.setAuthor(author);
//		book.setDescription(description);
//		book.setIsbn(isbn);
//		book.setPrice(price);
//		book.setPublishDate(publishDate);
//		book.setCategory(category);
//		
//		Part part = request.getPart("bookImage");
//		if(part != null && part.getSize() > 0) {
//			long size = part.getSize();
//			byte[] imageBytes = new byte[(int) size];
//			InputStream inputStream = part.getInputStream();
//			inputStream.read(imageBytes);
//			inputStream.close();
//			
//			book.setImage(imageBytes);
//		}
		
		Book newBook = new Book();
		readBookFields(newBook);
		
		Book createdBook = bookDAO.create(newBook);
		if(createdBook.getBookId() > 0) {
			String message = "Created New Book Successfully!";
			listBooks(message);
		}

		
	}

	public void editBook() throws ServletException, IOException {
		int bookId = Integer.parseInt(request.getParameter("id"));
		Book book = bookDAO.get(bookId);
		List<Category> listCategory = categoryDAO.listAll();
		
		request.setAttribute("book", book);
		request.setAttribute("listCategory", listCategory);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("book_form.jsp");
		requestDispatcher.forward(request, response);
		
	}
	
	public void readBookFields(Book book) throws IOException, ServletException {
		int categoryId = Integer.parseInt(request.getParameter("category"));
		Category category = categoryDAO.get(categoryId);
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String isbn = request.getParameter("isbn");
		String description = request.getParameter("description");
		float price = Float.parseFloat(request.getParameter("price"));
		Date publishDate = null;
		
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yy");
		try {
			publishDate = dateFormat.parse(request.getParameter("publishDate"));
		} catch (ParseException e) {
			throw new ServletException("Error parsing Publish date!");
		}
		
		book.setTitle(title);
		book.setAuthor(author);
		book.setDescription(description);
		book.setIsbn(isbn);
		book.setPrice(price);
		book.setPublishDate(publishDate);
		book.setCategory(category);
		
		Part part = request.getPart("bookImage");
		if(part != null && part.getSize() > 0) {
			long size = part.getSize();
			byte[] imageBytes = new byte[(int) size];
			InputStream inputStream = part.getInputStream();
			inputStream.read(imageBytes);
			inputStream.close();
			
			book.setImage(imageBytes);
		}
	}

	public void updateBook() throws IOException, ServletException {
		int bookId = Integer.parseInt(request.getParameter("book-id"));
		String title = request.getParameter("title");
		
		Book bookByTitle = bookDAO.findByTitle(title);
		Book existedBook = bookDAO.get(bookId);
		
		if(bookByTitle != null && !existedBook.equals(bookByTitle)) {
			String message = "Update Fail! Book title " + title + " already exist!";
			listBooks(message);
			return;
		}
		
		readBookFields(existedBook);
		bookDAO.update(existedBook);
		String message = "Updated Book Successfully!";
		request.setAttribute("message", message);
		listBooks(message);
	}

	public void deleteBook() throws ServletException, IOException {
		int bookId = Integer.parseInt(request.getParameter("id"));
		bookDAO.delete(bookId);
		listBooks("Delete Book Successfully!");
	}

	public void listBooksByCategory() throws ServletException, IOException {
		int categoryId = Integer.parseInt(request.getParameter("id"));
		Category category = categoryDAO.get(categoryId);
		List<Book> listBooks = bookDAO.listByCategory(categoryId);
		
		request.setAttribute("listBooks", listBooks);
		request.setAttribute("category", category);
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/frontend/books_list_by_category.jsp");
		requestDispatcher.forward(request, response);
	}

	public void viewBookDetail() throws ServletException, IOException {
		int bookId = Integer.parseInt(request.getParameter("id"));
		Book book = bookDAO.get(bookId);
		
		request.setAttribute("book", book);
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/frontend/book_detail.jsp");
		requestDispatcher.forward(request, response);
		
	}

	public void search() throws ServletException, IOException {
		String keyword = request.getParameter("keyword");
		List<Book> result = null;
		
		if(keyword.equals("")) {
			result = bookDAO.listAll();
		} else {
			result = bookDAO.search(keyword);
		}
		
		request.setAttribute("result", result);
		request.setAttribute("keyword", keyword);
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/frontend/search_result.jsp");
		requestDispatcher.forward(request, response);
	}
	
}
