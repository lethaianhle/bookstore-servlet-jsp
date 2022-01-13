package com.bookstore.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Book;
import com.bookstore.entity.Customer;
import com.bookstore.entity.Review;

public class ReviewDAOTest {
	private static ReviewDAO reviewDao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		reviewDao = new ReviewDAO();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		reviewDao.close();
	}

	@Test
	public void testCreateReview() {
		Review review = new Review();
		Book book = new Book();
		book.setBookId(6);
		Customer customer = new Customer();
		customer.setCustomerId(11);
		
		review.setBook(book);
		review.setCustomer(customer);
		review.setHeadline("This is awesome book");
		review.setRating(5);
		review.setComment("I have just test this comment for book");
		
		Review newReview = reviewDao.create(review);
		assertNotNull(newReview);
	}

	@Test
	public void testGet() {
		Review review = reviewDao.get(1);
		System.out.println(review.getHeadline());
		assertNotNull(review);
	}
	
	@Test
	public void testUpdate() {
		int reviewId = 1;
		Review review = reviewDao.get(reviewId);
		review.setComment("I have just test update this comment for book");
		Review updatedReview = reviewDao.update(review);
		assertEquals(review.getComment(), updatedReview.getComment());
	}
	
	@Test
	public void testDeleteObject() {
		int reviewId = 2;
		reviewDao.delete(reviewId);
		
		Review review = reviewDao.get(reviewId);
		assertNull(review);
	}

	@Test
	public void testListAll() {
		List<Review> listReview = reviewDao.listAll();
		for (Review review : listReview) {
			System.out.println(review.getHeadline() + " - " + review.getBook().getTitle());
		}
		assertTrue(listReview.size() > 0);
	}

	@Test
	public void testCount() {
		long count;
		count = reviewDao.count();
		System.out.println(count);
		assertEquals(2, count);
	}

}
