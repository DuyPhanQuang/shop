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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Order;

/**
 *
 * @author QuangDuy
 */
public class TableOrderDetailServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            response.setContentType("text/html");
            request.setCharacterEncoding("UTF-8");
            OrderDAO orderDAO = new OrderDAO();
            ArrayList<Order> orderList = new ArrayList<Order>();
            String command = request.getParameter("commandTOD");
            
            switch (command) {
                case "getOrCode":
                    orderList = orderDAO.getCreatingOrder();
                    request.setAttribute("chooseOrCode", orderList);
                break;
            }
        } catch (SQLException ex) {
            Logger.getLogger(TableOrderDetailServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
