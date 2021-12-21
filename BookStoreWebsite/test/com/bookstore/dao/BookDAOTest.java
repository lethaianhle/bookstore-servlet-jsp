package com.bookstore.dao;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Book;
import com.bookstore.entity.Category;

public class BookDAOTest{
	private static BookDAO bookDAO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		bookDAO = new BookDAO();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		bookDAO.close();
	}

	@Test
	public void testCreateBook() throws ParseException, IOException {
		Book newBook = new Book();
		Category category = new Category("JavaCore");
		category.setCategoryId(16);
		newBook.setCategory(category);
		newBook.setTitle("Java 8 in Action: Lambdas, Streams, and functional-style programming");
		newBook.setAuthor("Raoul-Gabriel Urma, Mario Fusco, Alan Mycroft");
		newBook.setDescription("Purchase of the print book includes a free eBook in PDF, Kindle, and ePub formats from Manning Publications.");
		newBook.setPrice(36.72f);
		newBook.setIsbn("1617291994");
		
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yy");
		Date publishDate = dateFormat.parse("08/28/2014");
		newBook.setPublishDate(publishDate);
		
		String imagePath = "D:\\WorkSpace\\books\\Java 8 in Action.JPG";
		byte[] imageBytes = Files.readAllBytes(Paths.get(imagePath));
		newBook.setImage(imageBytes);
		
		Book createdBook = bookDAO.create(newBook);
		
		assertTrue(createdBook.getBookId() > 0);
	}
	
	@Test
	public void testUpdateBook() throws ParseException, IOException {
		Book newBook = new Book();
		newBook.setBookId(1);
		
		Category category = new Category("JDBC");
		category.setCategoryId(15);
		newBook.setCategory(category);
		newBook.setTitle("Effective Java (3rd Edition)");
		newBook.setAuthor("Joshua Bloch");
		newBook.setDescription("Each chapter in the book consists of several “items” presented in the form of a short, standalone essay that provides specific advice, insight into Java platform subtleties, and outstanding code examples. The comprehensive descriptions and explanations for each item illuminate what to do, what not to do, and why.");
		newBook.setPrice(40f);
		newBook.setIsbn("0321356683");
		
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yy");
		Date publishDate = dateFormat.parse("05/08/2008");
		newBook.setPublishDate(publishDate);
		
		String imagePath = "D:\\WorkSpace\\books\\Effective Java.JPG";
		byte[] imageBytes = Files.readAllBytes(Paths.get(imagePath));
		newBook.setImage(imageBytes);
		
		Book updatedBook = bookDAO.update(newBook);
		
		assertEquals(newBook.getTitle(), updatedBook.getTitle());
	}
	
	@Test(expected = EntityNotFoundException.class)
	public void testDeleteBookFail() {
		int bookId = 100;
		bookDAO.delete(bookId);
		
		assertTrue(true);
	}
	
	@Test
	public void testDeleteBookSuccess() {
		int bookId = 1;
		bookDAO.delete(bookId);
		
		assertTrue(true);
	}
	
	@Test
	public void testGetBookSuccess() {
		int bookId = 2;
		Book book = bookDAO.get(bookId);
		
		assertNotNull(book);
	}
	
	@Test
	public void testGetBookFail() {
		int bookId = 123;
		Book book = bookDAO.get(bookId);
		
		assertNull(book);
	}
	
	@Test
	public void testListAll() {
		List<Book> listBooks = bookDAO.listAll();
		
		for (Book book : listBooks) {
			System.out.println(book.getTitle() + " - " + book.getAuthor());
		}
		
		assertFalse(listBooks.isEmpty());
	}
	
	@Test
	public void testFindByTitleNotExist(){
		String title = "Java LacaBA";
		Book book = bookDAO.findByTitle(title);
		
		assertNull(book);
	}
	
	@Test
	public void testCount(){
		long totalBooks = bookDAO.count();
		
		assertEquals(2, totalBooks);;
	}
	
	@Test
	public void testFindByTitleExist(){
		String title = "Effective Java (2nd Edition)";
		Book book = bookDAO.findByTitle(title);
		
		assertNotNull(book);
	}
	
	@Test
	public void testFindByCategory(){
		int categoryId = 4;
		List<Book> listBooks = bookDAO.listByCategory(categoryId);
		assertTrue(listBooks.size() > 0);
	}
	
	@Test
	public void testListNewBooks(){
		List<Book> listBooks = bookDAO.listNewBooks();
		for (Book book : listBooks) {
			System.out.println(book.getTitle());
			System.out.println(book.getAuthor());
			System.out.println(book.getPublishDate());
			System.out.println("---");
		}
		
		assertTrue(listBooks.size() == 4);
	}
	
	@Test
	public void testSearchBookInTitle() {
		String keyword = "java";
		List<Book> listBooks = bookDAO.search(keyword);
		for (Book book : listBooks) {
			System.out.println(book.getTitle());
		}
		
		assertTrue(listBooks.size() > 0);
	}
	
	@Test
	public void testSearchBookInAuthor() {
		String keyword = "Kathy";
		List<Book> listBooks = bookDAO.search(keyword);
		for (Book book : listBooks) {
			System.out.println(book.getTitle() + " - " + book.getAuthor());
		}
		
		assertTrue(listBooks.size() == 1);
	}
	
	@Test
	public void testSearchBookInDescription() {
		String keyword = "The Big Picture";
		List<Book> listBooks = bookDAO.search(keyword);
		for (Book book : listBooks) {
			System.out.println(book.getTitle() + " - " + book.getDescription());
		}
		
		assertTrue(listBooks.size() > 0);
	}
	
	@Test
	public void testCountByCategory() {
		int categoryId = 11;
		long count = bookDAO.countByCategory(categoryId);
		System.out.println("Count----------------" + count);
		
		assertTrue(count > 0);
	}

}
