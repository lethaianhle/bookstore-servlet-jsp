package com.bookstore.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
			
			Customer newCustomer = new Customer();
			updateCustomerFieldsFromForm(newCustomer);
			
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
			updateCustomerFieldsFromForm(customer);
			
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
	
	public void registerCustomer() throws ServletException, IOException {
		String email = request.getParameter("email");
		Customer existCustomer = customerDAO.findByEmail(email);
		String message = "";
		
		if(existCustomer != null) {
			message = "Could not register! Customer: " + email + " already exist!";
		} else {
			message = "Register Customer Successfully! "
					+ "<br>"
					+ "<a href='login'>Click here to sign in</a>";
			
			Customer newCustomer = new Customer();
			updateCustomerFieldsFromForm(newCustomer);
			
			customerDAO.create(newCustomer);
		}
		
		request.setAttribute("message", message);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("frontend/message.jsp");
		dispatcher.forward(request, response);
		
	}
	
	private void updateCustomerFieldsFromForm(Customer customer) {
		String email = request.getParameter("email");
		String fullName = request.getParameter("fullName");
		String password = request.getParameter("password");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		String city = request.getParameter("city");
		String zipCode = request.getParameter("zipcode");
		String country = request.getParameter("country");
		
		if(email != null && !email.equals("")) {
			customer.setEmail(email);
		}
		
		if(password != null && !password.equals("")) {
			customer.setPassword(password);
		}
		
		customer.setFullname(fullName);;
		customer.setPhone(phone);
		customer.setAddress(address);
		customer.setCity(city);
		customer.setCountry(country);
		customer.setZipcode(zipCode);
	}

	public void showLogin() throws ServletException, IOException {
		String loginPage = "frontend/login.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(loginPage);
		requestDispatcher.forward(request, response);
		
	}

	public void doLogin() throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		Customer customer = customerDAO.checkLogin(email, password);
		
		if(customer == null) {
			String message = "Login failed. Please check your email and password."; 
			request.setAttribute("message", message);
			showLogin();

		} else {
			HttpSession session = request.getSession();
			session.setAttribute("loggedCustomer", customer);
			Object objRedirectURL = session.getAttribute("redirectURL");
			
			if(objRedirectURL != null) {
				String url =  (String) objRedirectURL;
				session.removeAttribute("redirectURL");
				response.sendRedirect(url);
			} else {
				showCustomerProfile();
			}
		}
		
	}
	
	public void showCustomerProfile() throws ServletException, IOException {
		String customerProfilePage = "frontend/customer_profile.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(customerProfilePage);
		dispatcher.forward(request, response);
	}

	public void showCustomerProfileEditForm() throws ServletException, IOException {
		String editPage = "frontend/edit_profile.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(editPage);
		dispatcher.forward(request, response);
		
	}

	public void updateCustomerProfile() throws ServletException, IOException {
		Customer customer = (Customer) request.getSession().getAttribute("loggedCustomer");
		updateCustomerFieldsFromForm(customer);
		customerDAO.update(customer);
		showCustomerProfile();
	}

	
	
}












