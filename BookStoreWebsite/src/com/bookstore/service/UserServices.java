package com.bookstore.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.dao.UserDAO;
import com.bookstore.entity.Users;

public class UserServices{
	private UserDAO userDAO;
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	public UserServices(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		userDAO = new UserDAO();
	}
	
	public void listUser() 
			throws ServletException, IOException {
		listUser(null);
	}
	
	public void listUser(String message) 
			throws ServletException, IOException {
		List<Users> listUsers = userDAO.listAll();
		request.setAttribute("listUsers", listUsers);
		if(message != null) {
			request.setAttribute("message", message);
		}
		
		String listPage = "user_list.jsp";
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(listPage);
		dispatcher.forward(request, response);
	}
	
	public void createUser() throws ServletException, IOException {
		String email = request.getParameter("email");
		String fullname = request.getParameter("fullname");
		String password = request.getParameter("password");
		
		Users existUser = userDAO.findByEmail(email);
		if(existUser != null) {
			String message = "User " + email + " aldready exist!";
			request.setAttribute("message", message);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("message.jsp");
			requestDispatcher.forward(request, response);
		} else {
			Users user = new Users(email, fullname, password);
			userDAO.create(user);
			String message = "New User created successfully";
			listUser(message);
		}
	}

	public void editUser() throws ServletException, IOException {
		int userId = Integer.parseInt(request.getParameter("id"));
		Users user = userDAO.get(userId);
		
		String editPage = "user_form.jsp";
		request.setAttribute("user", user);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(editPage);
		dispatcher.forward(request, response);
	}

	public void updateUser() throws ServletException, IOException {
		int userId = Integer.parseInt(request.getParameter("user-id"));
		String email = request.getParameter("email");
		String fullName = request.getParameter("fullname");
		String password = request.getParameter("password");
		
		Users userById = userDAO.get(userId);
		Users userByEmail = userDAO.findByEmail(email);
		
		if(userByEmail != null && userByEmail.getUserId() != userById.getUserId()) {
			String message = "Updated User Fail! Email " + email + " already exist";
			request.setAttribute("message", message);
			RequestDispatcher dispatcher = request.getRequestDispatcher("message.jsp");
			dispatcher.forward(request, response);
			
		} else {
			Users user = new Users(userId, email, fullName, password);
			userDAO.update(user);
			
			String message = "Updated User Successfully!";
			listUser(message);
		}

	}

	public void deleteUser() throws ServletException, IOException {
		int userId =Integer.parseInt(request.getParameter("id"));
		userDAO.delete(userId);
		String message = "User deleted successfully!";
		listUser(message);
	}
	
	public void login() throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		boolean result = userDAO.checkLogin(email, password);
		if(result == true) {
			request.getSession().setAttribute("useremail", email);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/admin");
			requestDispatcher.forward(request, response);
		} else {
			String message = "Login failed!";
			request.setAttribute("message", message);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.jsp");
			requestDispatcher.forward(request, response);
		}
	}
	
}
