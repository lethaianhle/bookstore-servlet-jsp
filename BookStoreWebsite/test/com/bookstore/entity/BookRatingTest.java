package com.bookstore.entity;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class BookRatingTest {

	@Test
	public void testAverageRating() {
		Book book = new Book();
		
		Set<Review> reviews = new HashSet<>();
		Review review1 = new Review();
		review1.setRating(5);
		
		reviews.add(review1);
		
		book.setReviews(reviews);
		
		float bookRating = book.getAverageRating();
		System.out.println(bookRating);
		
		assertEquals(5.0, bookRating, 0.0);
	}
	
	@Test
	public void testAverageRating2() {
		Book book = new Book();
		
		float bookRating = book.getAverageRating();
		System.out.println(bookRating);
		
		assertEquals(0.0, bookRating, 0.0);
	}
	
	@Test
	public void testAverageRating3() {
		Book book = new Book();
		
		Set<Review> reviews = new HashSet<>();
		Review review1 = new Review();
		review1.setRating(5);
		
		Review review2 = new Review();
		review2.setRating(4);
		
		Review review3 = new Review();
		review3.setRating(3);
		
		reviews.add(review1);
		reviews.add(review2);
		reviews.add(review3);
		
		book.setReviews(reviews);
		
		float bookRating = book.getAverageRating();
		System.out.println(bookRating);
		
		assertEquals(4.0, bookRating, 0.0);
	}
	
	@Test
	public void testRatingString1() {
		Book book = new Book();
		float averageRating = 0.0f;
		
		String ratingString = book.getRatingString(averageRating);
		
		System.out.println(ratingString);
		String expect = "off,off,off,off,off";
		assertEquals(expect, ratingString);
	}
	
	@Test
	public void testRatingString2() {
		Book book = new Book();
//		Set<Review> reviews = new HashSet<>();
//		Review review1 = new Review();
//		review1.setRating(5);
//		reviews.add(review1);
//		
//		book.setReviews(reviews);
		
		float averageRating = 5.0f;
		
		String ratingString = book.getRatingString(averageRating);
		
		System.out.println(ratingString);
		String expect = "on,on,on,on,on";
		assertEquals(expect, ratingString);
	}
	
	@Test
	public void testRatingString3() {
		Book book = new Book();
		float averageRating = 3.6f;
		
		String ratingString = book.getRatingString(averageRating);
		
		System.out.println(ratingString);
		String expect = "on,on,on,half,off";
		assertEquals(expect, ratingString);
	}

}
