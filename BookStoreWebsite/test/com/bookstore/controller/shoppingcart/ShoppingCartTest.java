package com.bookstore.controller.shoppingcart;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Book;

public class ShoppingCartTest {
	private static ShoppingCart cart;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		cart = new ShoppingCart();
		Book book = new Book(1);
		book.setPrice(10);
		
		cart.addItem(book);
		cart.addItem(book);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testAddItem() {
		Map<Book, Integer> items = cart.getItems();
		int quantity = items.get(new Book(1));
		
		assertEquals(2, quantity);
		
	}
	
	@Test
	public void testRemoveItem() {
		cart.removeItem(new Book(1));
		
		assertTrue(cart.getItems().isEmpty());
		
	}
	
	@Test
	public void testRemoveItem2() {
		cart.addItem(new Book(2));
		cart.removeItem(new Book(2));
		
		assertNull(cart.getItems().get(new Book(2)));
		
	}
	
	@Test
	public void testGetTotalQuantity() {
		cart.addItem(new Book(3));
		cart.addItem(new Book(3));
		cart.addItem(new Book(3));
		
		assertEquals(5, cart.getTotalQuantity());
		
	}
	
	
	@Test
	public void testGetTotalAmount1() {

		assertEquals(0.0f, cart.getTotalAmount(), 0.0f);
		
	}
	
	@Test
	public void testGetTotalAmount2() {
		System.out.println(cart.getTotalAmount());

		assertEquals(50.0f, cart.getTotalAmount(), 10.0f);
		
	}
	
	@Test
	public void testClear() {
		cart.clear();
		System.out.println(cart.getTotalQuantity());

		assertEquals(0.0f, cart.getTotalAmount(), 0.0f);
		
	}
	
	@Test
	public void testUpdateCart() {
		cart = new ShoppingCart();
		cart.addItem(new Book(1));
		cart.addItem(new Book(2));
		
		int[] bookIds = {1, 2};
		int[] quantities = {3, 4};
		
		cart.updateCart(bookIds, quantities);
		
		assertEquals(cart.getTotalQuantity(), 7);
		
	}

}
