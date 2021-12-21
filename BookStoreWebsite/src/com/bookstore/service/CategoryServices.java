package com.bookstore.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.dao.BookDAO;
import com.bookstore.dao.CategoryDAO;
import com.bookstore.entity.Category;

public class CategoryServices{
	private CategoryDAO categoryDAO;
	private HttpServletRequest request;
	private HttpServletResponse response;

	public CategoryServices(HttpServletRequest request, HttpServletResponse response) {
		super();
		this.request = request;
		this.response = response;
		categoryDAO = new CategoryDAO();
	}
	
	public void listCategory(String message) throws ServletException, IOException {
		List<Category> listCategory = categoryDAO.listAll();
		request.setAttribute("listCategory", listCategory);
	
		if(message != null) {
			request.setAttribute("message", message);
		}
		
		String listPage = "category_list.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(listPage);
		
		dispatcher.forward(request, response);
	}
	
	public void listCategory() throws ServletException, IOException {
		listCategory(null);
	}

	public void createCategory() throws ServletException, IOException {
		String name = request.getParameter("name");
		Category existCategory = categoryDAO.findByName(name);
		
		if(existCategory != null) {
			String message = "Category name already exist!";
			request.setAttribute("message", message);
			RequestDispatcher dispatcher = request.getRequestDispatcher("message.jsp");
			dispatcher.forward(request, response);
		} else {
			Category newCategory = new Category(name);
			categoryDAO.create(newCategory);
			listCategory("New Category created successfully!");
		}
		
	}
	
	public void editCategory() throws ServletException, IOException {
		int categoryId = Integer.parseInt(request.getParameter("id"));
		
		Category category = categoryDAO.get(categoryId);
		request.setAttribute("category", category);
		RequestDispatcher dispatcher = request.getRequestDispatcher("category_form.jsp");
		dispatcher.forward(request, response);
	}

	public void updateCategory() throws ServletException, IOException {
		int categoryId = Integer.parseInt(request.getParameter("category-id"));
		String categoryName = request.getParameter("name");
		
		Category categoryById = categoryDAO.get(categoryId);
		Category categoryByName = categoryDAO.findByName(categoryName);
		
		if(categoryByName != null && categoryById.getCategoryId() != categoryByName.getCategoryId()) {
			String message = "Category name " + categoryName + " already exist!";
			request.setAttribute("message", message);
			RequestDispatcher dispatcher = request.getRequestDispatcher("message.jsp");
			dispatcher.forward(request, response);
		} else {
			categoryById.setName(categoryName);
			categoryDAO.update(categoryById);
			listCategory("Updated Category successfully!");
		}
	
	}

	public void deleteCategory() throws ServletException, IOException {
		int categoryId =Integer.parseInt(request.getParameter("id"));
		BookDAO bookDAO = new BookDAO();
		long numOfBooks = bookDAO.countByCategory(categoryId);
		
		if(numOfBooks > 0) {
			String message = "Could not delete category because it still contains some books!";
			listCategory(message);
		} else {
			categoryDAO.delete(categoryId);
			String message = "Category deleted successfully!";
			listCategory(message);
		}
	}

}
