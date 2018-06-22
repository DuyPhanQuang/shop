/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ItemMasterDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.ItemMaster;
import model.User;

/**
 *
 * @author QuangDuy
 */
//@WebServlet("/tableitemmaster")
public class ItemMasterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            response.setContentType("text/html");
            request.setCharacterEncoding("UTF-8");
            ArrayList<ItemMaster> itemmasters = new ArrayList<ItemMaster>();
            ItemMasterDAO imDAO = new ItemMasterDAO();
            User user = new User();

            String command = request.getParameter("commandIM");
            switch (command) {
                case "getIM":
                    itemmasters = imDAO.getListItemMaster();
                    request.setAttribute("item_master", itemmasters);
                    Object data = "Some data, can be a String or a Javabean";
                    request.setAttribute("data", data);
                    RequestDispatcher rd = request.getRequestDispatcher("/tableitemmaster.jsp");
                    rd.forward(request, response);
                break;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ItemMasterServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            ItemMasterDAO imDAO = new ItemMasterDAO();
            ItemMaster im = new ItemMaster();
            User user = new User();
            HttpSession session = request.getSession();
            user = (User) session.getAttribute("user");
            String command = request.getParameter("command");
            switch (command) {
                case "insert":
                    im.setImPC(user.userPC);
                    im.setImItemCode(request.getParameter("im_item_code"));
                    im.setImItemName(request.getParameter("im_item_name"));
                    im.setImDesc(request.getParameter("im_item_desc"));
                    imDAO.addItemMaster(im, user.userRole);
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/itemmaster.jsp");
                    rd.forward(request, response);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ItemMasterServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
