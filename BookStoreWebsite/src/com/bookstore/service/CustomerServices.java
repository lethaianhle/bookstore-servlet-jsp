package com.bookstore.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.dao.CustomerDAO;
import com.bookstore.entity.Customer;

public class CustomerServices {
	private CustomerDAO customerDAO;
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	public CustomerServices(HttpServletRequest request, HttpServletResponse response) {
		super();
		this.request = request;
		this.response = response;
		this.customerDAO = new CustomerDAO();
	}

	public void listCustomer(String message) throws ServletException, IOException {
		List<Customer> listCustomer = customerDAO.listAll();
		
		if(message != null) {
			request.setAttribute("message", message);
		}
		
		request.setAttribute("listCustomer", listCustomer);
		RequestDispatcher dispatcher = request.getRequestDispatcher("customer_list.jsp");
		dispatcher.forward(request, response);
		
	}
	
	public void listCustomer() throws ServletException, IOException {
		listCustomer(null);
	}

	public void createCustomer() throws ServletException, IOException {
		String email = request.getParameter("email");
		Customer existCustomer = customerDAO.findByEmail(email);
		
		if(existCustomer != null) {
			String message = "Could not create Customer: " + email + " already exist!";
			listCustomer(message);
		} else {
			String message = "Create Customer Successfully";
			
			String fullName = request.getParameter("fullName");
			String password = request.getParameter("password");
			String phone = request.getParameter("phone");
			String address = request.getParameter("address");
			String city = request.getParameter("city");
			String zipCode = request.getParameter("zipcode");
			String country = request.getParameter("country");
			
			Customer newCustomer = new Customer();
			newCustomer.setEmail(email);
			newCustomer.setFullname(fullName);;
			newCustomer.setPassword(password);
			newCustomer.setPhone(phone);
			newCustomer.setAddress(address);
			newCustomer.setCity(city);
			newCustomer.setCountry(country);
			newCustomer.setZipcode(zipCode);
			
			customerDAO.create(newCustomer);
			listCustomer(message);
		}
		
	}

	public void editCustomer() throws ServletException, IOException {
		int customerId = Integer.parseInt(request.getParameter("id"));
		
		Customer customer = customerDAO.get(customerId);
	
		request.setAttribute("customer", customer);
		RequestDispatcher dispatcher = request.getRequestDispatcher("customer_form.jsp");
		dispatcher.forward(request, response);
		
	}

	public void updateCustomer() throws ServletException, IOException {
		Integer customerId = Integer.parseInt(request.getParameter("customer-id"));
		String email = request.getParameter("email");
		
		Customer customer = customerDAO.get(customerId);
		Customer customerByEmail = customerDAO.findByEmail(email);

		if(customerByEmail != null && customerByEmail.getCustomerId() != customerId) {
			String message = "Could not update Customer. Customer " + email + " already exist!";
			listCustomer(message);
		} else {
			String fullName = request.getParameter("fullName");
			String password = request.getParameter("password");
			String phone = request.getParameter("phone");
			String address = request.getParameter("address");
			String city = request.getParameter("city");
			String zipCode = request.getParameter("zipcode");
			String country = request.getParameter("country");
			
			customer.setEmail(email);
			customer.setFullname(fullName);;
			customer.setPassword(password);
			customer.setPhone(phone);
			customer.setAddress(address);
			customer.setCity(city);
			customer.setCountry(country);
			customer.setZipcode(zipCode);
			
			customerDAO.update(customer);
			
			String message = "Customer Updated Successfully";
			listCustomer(message);
		}
		
	}

	public void deleteCustomer() throws ServletException, IOException {
		int customerId = Integer.parseInt(request.getParameter("id"));
		customerDAO.delete(customerId);
		String message = "Customer Deleted Successfully";
		listCustomer(message);

	}
}












