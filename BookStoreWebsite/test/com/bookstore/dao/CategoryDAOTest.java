package com.bookstore.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Category;

public class CategoryDAOTest {
	private static CategoryDAO categoryDao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		categoryDao = new CategoryDAO(); 
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		categoryDao.close();
	}

	@Test
	public void testCreateCategory() {
		Category category = new Category();
		category.setName("Others");
		categoryDao.create(category);
		
		assertTrue(category != null && category.getCategoryId() > 0);
	}
	
	@Test
	public void testUpdateCategory() {
		Category category = new Category();
		category.setName("PHP");
		category.setCategoryId(1);
		
		Category category2 = categoryDao.update(category);
		
		assertEquals(category.getName(), category2.getName());
	}

	@Test
	public void testGet() {
		int catId = 2;
		Category cat = categoryDao.get(catId);
		
		assertNotNull(cat);
	}

	@Test
	public void testDeleteCategory() {
		int catId = 17;
		categoryDao.delete(catId);
		
		Category cat = categoryDao.get(catId);
		assertNull(cat);
	}

	@Test
	public void testListAll() {
		List<Category> listCategory = categoryDao.listAll();
		
		assertTrue(listCategory.size() > 0);
	}

	@Test
	public void testCount() {
		long totalCategoties = categoryDao.count();
		
		assertEquals(5, totalCategoties);
	}
	
	@Test
	public void testfindByName() {
		String categoryName = "C/C++";
		Category category = categoryDao.findByName(categoryName);
		System.out.println(category.getName());
		
		assertNotNull(category);
	}
	
	@Test
	public void testfindByNameNotFound() {
		String categoryName = "FFF";
		Category category = categoryDao.findByName(categoryName);
		
		assertNull(category);
	}

}
