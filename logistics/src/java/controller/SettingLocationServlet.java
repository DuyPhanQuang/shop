/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ItemMasterDAO;
import dao.LocationDAO;
import dao.ReceptionDAO;
import dao.SettingLocationDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.ItemLocation;
import model.ItemMaster;
import model.ItemObject;
import model.Location;
import model.Reception;
import model.User;

/**
 *
 * @author QuangDuy
 */
public class SettingLocationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            response.setContentType("text/html");
            request.setCharacterEncoding("UTF-8");
            HttpSession session = request.getSession();
            ReceptionDAO reDAO = new ReceptionDAO();
            LocationDAO loDAO = new LocationDAO();
            ItemMasterDAO imDAO = new ItemMasterDAO();
            ArrayList<Reception> reList = new ArrayList<Reception>();

            String command = request.getParameter("commandSL");
            switch (command) {
                case "getRecode":
                    reList = reDAO.getPuttingReception();
                    request.setAttribute("chooseReCode", reList);
                    RequestDispatcher rd = request.getRequestDispatcher("/settinglocation.jsp");
                    rd.forward(request, response);
                    break;
            }

        } catch (SQLException ex) {
            Logger.getLogger(SettingLocationServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            User user = new User();
            user = (User) session.getAttribute("user");
            ArrayList<ItemLocation> itemLocation = new ArrayList<ItemLocation>();
            ReceptionDAO reDAO = new ReceptionDAO();
            Location location = new Location();
            LocationDAO loDAO = new LocationDAO();
            SettingLocationDAO settingLocationDAO = new SettingLocationDAO();
            String command = request.getParameter("command");
            String data = request.getParameter("choose_location_code");
            location = loDAO.getLocationByCode(data);
            request.setAttribute("getLocationByCode", location);
            switch (command) {
                case "insert":
                    Location lo = new Location();
                    String[] selectedItems = request.getParameterValues("selectedItems");
                    for (String selectedItem : selectedItems) {
                        try {
                            Reception reByCode = new Reception();
                            reByCode = reDAO.getReceptionByCode(request.getParameter("choose_recode"));
                            
                            ItemLocation itl = new ItemLocation();
                            itl.setProjectCode(user.userPC);
                            itl.setItemId(Integer.parseInt(request.getParameter("item_id")));
                            itl.setItemCode(request.getParameter("item_code"));
                            itl.setLocationId(location.getLocationID());
                            itl.setLocationCode(request.getParameter("choose_location_code"));
                            itl.setRow(Integer.parseInt(request.getParameter("lo_row_bycode")));  
                            itl.setUserIdUpdate(user.userRole);
                            itl.setReceptionId(reByCode.getReId());  
                            itl.setReceptionCode(request.getParameter("choose_recode"));                                                                                                       
                            itemLocation.add(itl);
                            settingLocationDAO.InsertListItemLocation(itemLocation);
                            RequestDispatcher rd = getServletContext().getRequestDispatcher("/settinglocation.jsp");
                            rd.forward(request, response);
                        } catch (SQLException ex) {
                            Logger.getLogger(SettingLocationServlet.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    break;
            }
        } catch (SQLException ex) {
            Logger.getLogger(SettingLocationServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
