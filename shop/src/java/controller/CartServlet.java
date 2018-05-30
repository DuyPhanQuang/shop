/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ProductDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Cart;
import model.Item;
import model.Product;

/**
 *
 * @author QuangDuy
 */
public class CartServlet extends HttpServlet {

    private final ProductDAO productDAO = new ProductDAO();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String command = request.getParameter("command");
        String productID = request.getParameter("productID");
        Cart cart = (Cart) session.getAttribute("cart");
        try {
                Integer _id = Integer.parseInt(productID);
            Product product = productDAO.getProduct(_id);
            switch (command) {
                case "plus":
                    if (cart.getCartItems().containsKey(_id)) {
                        cart.plusToCart(_id, new Item(product, cart.getCartItems().get(_id).getQuantity()));
                    } else {
                        cart.plusToCart(_id, new Item(product, 1));
                    }
                    break;
                case "remove":
                    cart.removeToCart(_id);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("/DemoProjectJavaEE/index.jsp");
        }
        session.setAttribute("cart", cart);
        response.sendRedirect("/DemoProjectJavaEE/index.jsp");
    }

}
