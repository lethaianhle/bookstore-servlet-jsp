package com.bookstore.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Customer;

public class CustomerDAOTest {
	private static CustomerDAO customerDAO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		customerDAO = new CustomerDAO();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		customerDAO.close();
	}

	@Test
	public void testCreateCustomer() {
		Customer customer = new Customer();
		customer.setEmail("tom2@gmail.com");
		customer.setFullname("Tom Eager");
		customer.setCity("New York");
		customer.setCountry("USA");
		customer.setAddress("100 North Avenus");
		customer.setPassword("123");
		customer.setPhone("09135245233");
		customer.setZipcode("10000");
		
		Customer newCustomer = customerDAO.create(customer);
		
		assertTrue(newCustomer != null && newCustomer.getCustomerId() > 0);
	}
	
	@Test
	public void testGet() {
		int customerId = 1;
		Customer customer = customerDAO.get(customerId);
		
		assertNotNull(customer);
	}
	
	@Test
	public void testDeleteCustomer() {
		int customerId = 1;
		customerDAO.delete(customerId);
		
		Customer customer = customerDAO.get(customerId);
		
		assertNull(customer);
	}
	
	@Test
	public void testUpdateCustomer() {
		int customerId = 2;
		Customer customer = customerDAO.get(customerId);
		customer.setFullname("Tom Updated");
		
		customerDAO.update(customer);
		
		
		assertEquals(customer.getFullname(), "Tom Updated");
	}
	
	@Test
	public void testListAll() {
		List<Customer> listCustomer = customerDAO.listAll();
		assertTrue(listCustomer.size() > 0);
	}
	
	@Test
	public void testCount() {
		long totalCustomer = customerDAO.count();
		
		assertEquals(2, totalCustomer);
	}
	
	@Test
	public void testFindById() {
		String email = "tomfqe@gmail.com";
		
		Customer result = customerDAO.findByEmail(email);
		System.out.println(result.getEmail());
		
		assertNotNull(result);
	}
	
	@Test
	public void testCheckLoginSuccess() {
		String email = "tt@gmail.com";
		String password = "123";
		
		Customer customer = customerDAO.checkLogin(email, password);
		
		assertNotNull(customer);
	}
	
	@Test
	public void testCheckLoginFail() {
		String email = "ttgwwgwrwgwrg@gmail.com";
		String password = "123";
		
		Customer customer = customerDAO.checkLogin(email, password);
		
		assertNull(customer);
	}

}
