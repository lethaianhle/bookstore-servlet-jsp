package com.bookstore.controller.shoppingcart;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.dao.BookDAO;
import com.bookstore.entity.Book;

@WebServlet("/view_cart")
public class ViewCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ViewCartServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Object cartObject = request.getSession().getAttribute("cart");
		
		if(cartObject == null) {
			ShoppingCart cart = new ShoppingCart();
			request.getSession().setAttribute("cart", cart);
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("frontend/shopping_cart.jsp");
		dispatcher.forward(request, response);
	}


}
