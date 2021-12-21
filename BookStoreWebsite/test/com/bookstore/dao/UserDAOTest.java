package com.bookstore.dao;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Users;

public class UserDAOTest {
	private static UserDAO userDAO;
	
	@BeforeClass
	public static void setUpClass() throws Exception {
		userDAO = new UserDAO();
	}
	
	@AfterClass
	public static void tearDownClass() throws Exception {
		userDAO.close();
	}

	@Test
	public void testCreateUsers() {
		Users user1 = new Users();
		user1.setEmail("nguyentuan123132@gmail.com");
		user1.setFullName("Nguyen Manh Tuan");
		user1.setPassword("123");
		
		user1 = userDAO.create(user1);
		
		assertTrue(user1.getUserId() > 0);
		
		System.out.println("test them");
	}
	
	@Test(expected = PersistenceException.class)
	public void testCreateUsersFieldsNotSet() {
		Users user1 = new Users();
		
		user1 = userDAO.create(user1);
	}
	
	@Test
	public void testUpdateUser() {
		Users user = new Users();
		user.setUserId(1);
		user.setEmail("thai@gmail.com");
		user.setFullName("Thai Anh");
		user.setPassword("111");
		
		user = userDAO.update(user);
		
		String expected = "111";
		String actual = user.getPassword();
		assertEquals(expected, actual);
	}
	
	@Test
	public void testGetUsersFound() {
		Integer userId = 1;
		Users user = userDAO.get(userId);
		
		System.out.println(user.getEmail());
		
		assertNotNull(user);
	}
	
	@Test
	public void testGetUsersNotFound() {
		Integer userId = 99;
		Users user = userDAO.get(userId);
		
		assertNull(user);
	}
	
	@Test
	public void testDeleteUsers() {
		Integer userId = 10;
		userDAO.delete(userId);
		
		Users user = userDAO.get(userId);
		
		assertNull(user);
	}
	
	@Test(expected = EntityNotFoundException.class)
	public void testDeleteNonExistUsers() {
		Integer userId = 10;
		userDAO.delete(userId);
	}
	
	@Test
	public void testListAll() {
		List<Users> listUsers = userDAO.listAll();
		
		for (Users user : listUsers) {
			System.out.println(user.getEmail());
		}
		
		assertTrue(listUsers.size() > 0);
	}
	
	@Test
	public void testCount() {
		long totalUsers = userDAO.count();
		assertTrue(totalUsers == 11);
	}

	@Test
	public void testFindByEmail() {
		UserDAO userDAO = new UserDAO();
		String email = "thai@gmail.com";
		Users user = userDAO.findByEmail(email);
		
		assertNotNull(user);
	}
	
	@Test
	public void testcheckLoginSuccess() {
		UserDAO userDAO = new UserDAO();
		String email = "thai@gmail.com";
		String password = "111";
		boolean result = userDAO.checkLogin(email, password);
		assertTrue(result);
	}
	
	@Test
	public void testcheckLoginFailedSuccess() {
		UserDAO userDAO = new UserDAO();
		String email = "thai@gmail.com";
		String password = "1113";
		boolean result = userDAO.checkLogin(email, password);
		assertFalse(result);
	}
	
}
