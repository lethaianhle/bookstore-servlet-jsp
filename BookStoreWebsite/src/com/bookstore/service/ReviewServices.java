package com.bookstore.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.dao.ReviewDAO;
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
	
	
	
}
