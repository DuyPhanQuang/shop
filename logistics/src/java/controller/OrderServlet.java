/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.OrderDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Order;
import model.User;

/**
 *
 * @author QuangDuy
 */
public class OrderServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Order order = new Order();
            User user = new User();
            OrderDAO orderDAO = new OrderDAO();
            HttpSession session = request.getSession();
            user = (User) session.getAttribute("user");
            String command = request.getParameter("command");
            
            switch (command) {
                case "insert":
                    order.setOrderPC(user.userPC);
                    order.setOrderCode(request.getParameter("order_code"));
                    order.setNote(request.getParameter("order_note"));
                    order.setUserUpdateId(user.userRole);
                    orderDAO.insertOrder(order);
                    
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/order.jsp");
                    rd.forward(request, response);
                break;
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
