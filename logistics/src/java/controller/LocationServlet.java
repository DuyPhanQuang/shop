/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ItemMasterDAO;
import dao.LocationDAO;
import dao.ReceptionDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Location;
import model.User;

/**
 *
 * @author QuangDuy
 */
public class LocationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Location lo = new Location();
        LocationDAO loDAO = new LocationDAO();
        User user = new User();
        HttpSession session = request.getSession();
        user = (User) session.getAttribute("user");
        String command = request.getParameter("command");
        switch (command) {
            case "insert":
                lo.setLocationPC(user.userPC);
                lo.setLocationCode(request.getParameter("location_code"));
                lo.setLocationName(request.getParameter("location_name"));
                lo.setRowOfLocation(Integer.parseInt(request.getParameter("location_row")));
                lo.setUserUpdateId(user.userRole);
                loDAO.insertLocation(lo);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/location.jsp");
                rd.forward(request, response);
            break;
        }
    }

}
