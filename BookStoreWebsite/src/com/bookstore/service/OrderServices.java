package com.bookstore.service;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookstore.controller.shoppingcart.ShoppingCart;
import com.bookstore.dao.OrderDAO;
import com.bookstore.entity.Book;
import com.bookstore.entity.BookOrder;
import com.bookstore.entity.Customer;
import com.bookstore.entity.OrderDetail;

public class OrderServices {
	private OrderDAO orderDAO;
	private HttpServletRequest request;
	private HttpServletResponse response;

	public OrderServices(HttpServletRequest request, HttpServletResponse response) {
		this.orderDAO = new OrderDAO();
		this.request = request;
		this.response = response;
	}

	public void listAllOrder(String message) throws ServletException, IOException {
		List<BookOrder> listOrder = orderDAO.listAll();
		
		if(message != null) {
			request.setAttribute("message", message);
		}

		request.setAttribute("listOrder", listOrder);

		String orderListPage = "order_list.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(orderListPage);
		dispatcher.forward(request, response);

	}
	
	public void listAllOrder() throws ServletException, IOException {
		listAllOrder(null);
	}

	public void viewOrderDetailForAdmin() throws ServletException, IOException {
		Integer orderId = Integer.parseInt(request.getParameter("id"));

		BookOrder order = orderDAO.get(orderId);

		request.setAttribute("order", order);

		RequestDispatcher dispatcher = request.getRequestDispatcher("order_detail.jsp");
		dispatcher.forward(request, response);

	}

	public void showCheckOutForm() throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("frontend/checkout.jsp");
		dispatcher.forward(request, response);
	}

	public void placeOrder() throws ServletException, IOException {
		String recipientName = request.getParameter("recipientName");
		String recipientPhone = request.getParameter("recipientPhone");
		String address = request.getParameter("address");
		String city = request.getParameter("city");
		String zipcode = request.getParameter("zipcode");
		String country = request.getParameter("country");
		String paymentMethod = request.getParameter("paymentMethod");
		
		StringBuilder shippingAddress = new StringBuilder();
		shippingAddress.append(address);
		shippingAddress.append(", ");
		shippingAddress.append(city);
		shippingAddress.append(", ");
		shippingAddress.append(zipcode);
		shippingAddress.append(", ");
		shippingAddress.append(country);
		
		
		BookOrder order = new BookOrder();
		order.setRecipientName(recipientName);
		order.setRecipientPhone(recipientPhone);
		order.setShippingAddress(shippingAddress.toString());
		order.setPaymentMethod(paymentMethod);
		
		HttpSession session = request.getSession();
		Customer customer = (Customer) session.getAttribute("loggedCustomer");
		
		order.setCustomer(customer);
		
		ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
		Map<Book, Integer> items = cart.getItems();
		
		Iterator<Book> iterator = items.keySet().iterator();
		Set<OrderDetail> orderDetails = new HashSet<>();
		
		while(iterator.hasNext()) {
			Book book = iterator.next();
			Integer quantity = items.get(book);
			float subtotal = quantity * book.getPrice();
			
			OrderDetail orderDetail = new OrderDetail();
			orderDetail.setBook(book);
			orderDetail.setBookOrder(order);
			orderDetail.setQuantity(quantity);
			orderDetail.setSubtotal(subtotal);
			
			orderDetails.add(orderDetail);
		}
		
		order.setOrderDetails(orderDetails);
		order.setTotal(cart.getTotalAmount());
		
		orderDAO.create(order);
		
		cart.clear();
		
		String message = "Thank you for order! We'll deliver your books now <3";
		request.setAttribute("message", message);
		
		String messagePage = "frontend/message.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(messagePage);
		dispatcher.forward(request, response);
	}

	public void listOrderByCustomer() throws ServletException, IOException {
		HttpSession session = request.getSession();
		Customer customer = (Customer) session.getAttribute("loggedCustomer");
		List<BookOrder> listOrders = orderDAO.listByCustomer(customer.getCustomerId());
		
		request.setAttribute("listOrders", listOrders);
		String historyPage = "frontend/order_list.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(historyPage);
		dispatcher.forward(request, response);
	}

	public void showOrderDetailForCustomer() throws ServletException, IOException {
		Integer orderId = Integer.parseInt(request.getParameter("id"));
		Customer customer = (Customer) request.getSession().getAttribute("loggedCustomer");
		Integer customerId = customer.getCustomerId();
		
		BookOrder order = orderDAO.get(orderId, customerId);

		request.setAttribute("order", order);

		RequestDispatcher dispatcher = request.getRequestDispatcher("frontend/order_detail.jsp");
		dispatcher.forward(request, response);
		
	}

	public void showEditOrderForm() throws ServletException, IOException {
		Integer orderId = Integer.parseInt(request.getParameter("id"));
		HttpSession session = request.getSession();
		Object isPendingBook = session.getAttribute("NewBookPendingToAddToOrder");
		
		if (isPendingBook == null) {
			BookOrder order = orderDAO.get(orderId);
			session.setAttribute("order", order);
		} else {
			session.removeAttribute("NewBookPendingToAddToOrder");
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("order_form.jsp");
		dispatcher.forward(request, response);
		
	}

	public void updateOrder() throws ServletException, IOException {
		HttpSession session = request.getSession();
		BookOrder order = (BookOrder) session.getAttribute("order");
		
		String recipientName = request.getParameter("recipientName");
		String recipientPhone = request.getParameter("recipientPhone");
		String shippingAddress = request.getParameter("shippingAddress");
		String orderStatus = request.getParameter("orderStatus");
		String paymentMethod = request.getParameter("paymentMethod");
		
		order.setRecipientName(recipientName);
		order.setRecipientPhone(recipientPhone);
		order.setShippingAddress(shippingAddress);
		order.setStatus(orderStatus);
		order.setPaymentMethod(paymentMethod);
		
		String[] arrayBookId = request.getParameterValues("bookId");
		String[] arrayPrice = request.getParameterValues("price");
		String[] arrayQuantity = new String[arrayBookId.length];
		
		for(int i = 1; i <= arrayQuantity.length; i++) {
			arrayQuantity[i - 1] = request.getParameter("quantity" + i);
		}
		
		Set<OrderDetail> orderDetails = order.getOrderDetails();
		orderDetails.clear();
		
		float totalAmount = 0.0f;
		for(int i = 0; i < arrayBookId.length; i++) {
			int bookId = Integer.parseInt(arrayBookId[i]);
			int quantity = Integer.parseInt(arrayQuantity[i]);
			float price = Float.parseFloat(arrayPrice[i]);
			float subtotal = price * quantity;
			
			OrderDetail orderDetail = new OrderDetail();
			orderDetail.setQuantity(quantity);
			orderDetail.setSubtotal(subtotal);
			orderDetail.setBook(new Book(bookId));
			orderDetail.setBookOrder(order);
			
			orderDetails.add(orderDetail);
			totalAmount += subtotal;
			
		}
		
		order.setTotal(totalAmount);
		orderDAO.update(order);
		
		String message = "Updated Order Successfully!";
		listAllOrder(message);
		
	}

	public void deleteOrder() throws ServletException, IOException {
		Integer orderId = Integer.parseInt(request.getParameter("id"));
		orderDAO.delete(orderId);
		
		String message = "Delete Order Successfully!";
		listAllOrder(message);
		
	}

}




















