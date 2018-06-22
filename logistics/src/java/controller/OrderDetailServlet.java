/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ItemDAO;
import dao.OrderDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.ItemObject;
import model.Order;
import model.User;

/**
 *
 * @author QuangDuy
 */
public class OrderDetailServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            User user = new User();
            user = (User) request.getAttribute("user");
            OrderDAO orderDAO = new OrderDAO();
            ItemDAO itemDAO = new ItemDAO();
            ArrayList<Order> orderList = new ArrayList<Order>();
            ArrayList<ItemObject> ioList = new ArrayList<ItemObject>();
            String command = request.getParameter("commandOD");
            
            switch (command) {
                case "getOrCode":
                    orderList = orderDAO.getCreatingOrder();
                    request.setAttribute("chooseOrderCode", orderList);
                    ioList = itemDAO.getAllItems();
                    request.setAttribute("itemAll", ioList);
                    RequestDispatcher rd = request.getRequestDispatcher("/orderdetail.jsp");
                    rd.forward(request, response);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDetailServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = new User();
        Order order = new Order();
        OrderDAO orderDAO = new OrderDAO();
        user = (User) session.getAttribute("user");
        ArrayList<ItemObject> ioList = new ArrayList<ItemObject>();
        String command = request.getParameter("command");
        
        switch (command) {
            case "insert":                
                String[] selectedItems = request.getParameterValues("selectedItems");
                for (String selectedItem : selectedItems) {
                    try {
                        
//                        Order or = new Order();
//                        or = orderDAO.getOrderByCode(request.getParameter("choose_order_code"));
                        ItemObject io = new ItemObject();
                        
                        order.setOrderPC(user.userPC);
                        io.setItemId(Integer.parseInt(request.getParameter("io_id")));
                        io.setItemCode(request.getParameter("io_code"));
                        io.setItemQty(Integer.parseInt(request.getParameter("item_qty")));
                        ioList.add(io);
                        order.setItemObjs(ioList);
                        order.setOrderId(1);
                        order.setOrderCode(request.getParameter("choose_order_code"));
                        orderDAO.insertOrderDetail(order);
                        
                        RequestDispatcher rd = getServletContext().getRequestDispatcher("/orderdetail.jsp");
                        rd.forward(request, response);
                    } catch (SQLException ex) {
                        Logger.getLogger(OrderDetailServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
                break;
        }
        
    }
    
}
