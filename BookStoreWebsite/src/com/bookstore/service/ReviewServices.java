package com.bookstore.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookstore.dao.BookDAO;
import com.bookstore.dao.ReviewDAO;
import com.bookstore.entity.Book;
import com.bookstore.entity.Customer;
import com.bookstore.entity.Review;

public class ReviewServices {
	private ReviewDAO reviewDAO;
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	public ReviewServices(HttpServletRequest request, HttpServletResponse response) {
		super();
		this.request = request;
		this.response = response;
		this.reviewDAO = new ReviewDAO();
	}
	
	public void listAllReview() throws ServletException, IOException {
		listAllReview(null);
	}
	
	public void listAllReview(String message) throws ServletException, IOException {
		List<Review> listReviews = reviewDAO.listAll();
		request.setAttribute("listReviews", listReviews);
		if(message != null) {
			request.setAttribute("message", message);
		}
		
		String listPage = "review_list.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(listPage);
		dispatcher.forward(request, response);
	}

	public void editReview() throws ServletException, IOException {
		int reviewId = Integer.parseInt(request.getParameter("id"));
		Review review = reviewDAO.get(reviewId);
		
		request.setAttribute("review", review);
		
		String editPage = "review_form.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(editPage);
		dispatcher.forward(request, response);
		
	}

	public void updateReview() throws ServletException, IOException {
		int reviewId = Integer.parseInt(request.getParameter("review-id"));
		Review review = reviewDAO.get(reviewId);
		
		String headline = request.getParameter("headline");
		String comment = request.getParameter("comment");
		
		review.setHeadline(headline);
		review.setComment(comment);
		
		reviewDAO.update(review);
		String message = "Updated Review Successfully!";
		listAllReview(message);
	}

	public void deleteReview() throws ServletException, IOException {
		Integer reviewId = Integer.parseInt(request.getParameter("id"));
		reviewDAO.delete(reviewId);
		String message = "Deleted Review Successfully!";
		listAllReview(message);
		
	}

	public void showReviewForm() throws ServletException, IOException {
		Integer bookId = Integer.parseInt(request.getParameter("book_id"));
		BookDAO bookDAO = new BookDAO();
		Book book = bookDAO.get(bookId);
		
		HttpSession session = request.getSession();
		session.setAttribute("book", book);
	
		Customer customer = (Customer) session.getAttribute("loggedCustomer");
		Review customerReview = reviewDAO.findByCustomerAndBook(customer.getCustomerId(), bookId);
		
		String page = "";
		
		if(customerReview != null) {
			request.setAttribute("customerReview", customerReview);
			page = "frontend/review_info.jsp";
		} else {
			page = "frontend/review_form.jsp";
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}

	public void submitReview() throws ServletException, IOException {
		Integer bookId = Integer.parseInt(request.getParameter("bookId"));
		Integer rating = Integer.parseInt(request.getParameter("rating"));
		
		String headline = request.getParameter("headline");
		String comment = request.getParameter("comment");
		
		Review review = new Review();
		review.setRating(rating);
		review.setHeadline(headline);
		review.setComment(comment);
		
		Book book = new Book();
		book.setBookId(bookId);
		review.setBook(book);
		
		Customer customer = (Customer) request.getSession().getAttribute("loggedCustomer");
		review.setCustomer(customer);
		
		review.setReviewTime(new Date());
		
		reviewDAO.create(review);
		
		String page = "frontend/review_done.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}
	
	
	
}
