package com.bookstore.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Book;
import com.bookstore.entity.BookOrder;
import com.bookstore.entity.Customer;
import com.bookstore.entity.OrderDetail;

public class OrderDAOTest {
	
	private static OrderDAO orderDAO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		orderDAO = new OrderDAO();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		orderDAO.close();
	}

	@Test
	public void testCreateBookOrder() {
		BookOrder bookOrder = new BookOrder();
		Customer customer = new Customer();
		customer.setCustomerId(13);
		bookOrder.setCustomer(customer);
		
		bookOrder.setRecipientName("Le Thai Anh");
		bookOrder.setRecipientPhone("0913813513");
		bookOrder.setShippingAddress("C3 Thach That");
		
		Set<OrderDetail> orderDetails = new HashSet<>();
		OrderDetail orderDetail1 = new OrderDetail();	
		OrderDetail orderDetail2 = new OrderDetail();
		
		Book book1 = new Book(10);
		orderDetail1.setBook(book1);
		orderDetail1.setQuantity(2);
		orderDetail1.setSubtotal((float) (33.99 * 2));
		orderDetail1.setBookOrder(bookOrder);
		
		Book book2 = new Book(9);
		orderDetail2.setBook(book2);
		orderDetail2.setQuantity(2);
		orderDetail2.setSubtotal((float) (28.79 * 2));
		orderDetail2.setBookOrder(bookOrder);
		
		orderDetails.add(orderDetail1);
		orderDetails.add(orderDetail2);
		bookOrder.setOrderDetails(orderDetails);
		
		orderDAO.create(bookOrder);
		
		assertTrue(bookOrder.getOrderId() > 0);
		
	}

	@Test
	public void testGet() {
		Integer orderId = 2;
		BookOrder order = orderDAO.get(orderId);
		System.out.println(order.getCustomer().getFullname());
		
		assertNotNull(order);
	}

	@Test
	public void testDeleteObject() {
		orderDAO.delete(3);
		
		assertNull(orderDAO.get(3));
	}

	@Test
	public void testListAll() {
		List<BookOrder> order = orderDAO.listAll();
		
		assertTrue(order.size() > 0);
	}

	@Test
	public void testCount() {
		long count = orderDAO.count();
		
		assertEquals(2, count);
	}
	
	@Test
	public void testUpdateBook() {
		Integer orderId = 3;
		BookOrder order = orderDAO.get(orderId);
		order.setShippingAddress("Van la Cong truong C3");
		orderDAO.update(order);
		BookOrder updatedOrder = orderDAO.get(orderId);
		assertEquals(order.getShippingAddress(), updatedOrder.getShippingAddress());
	}
	
	@Test
	public void testListByCustomerNoOrders() {
		Integer customerId = 13531531;
		List<BookOrder> list = orderDAO.listByCustomer(customerId);
		
		assertTrue(list.isEmpty());
	}
	
	@Test
	public void testListByCustomer() {
		Integer customerId = 2;
		List<BookOrder> list = orderDAO.listByCustomer(customerId);
		
		for (BookOrder bookOrder : list) {
			System.out.println(bookOrder.getCustomer().getFullname() + " - " + bookOrder.getTotalCopies());
		}
		
		assertTrue(list.size() > 0);
	}
	
	@Test
	public void testFindByIdAndCustomer() {
		Integer customerId = 2;
		Integer orderId = 2;
		BookOrder order = orderDAO.get(orderId, customerId);

		assertNotNull(order);
	}

}
