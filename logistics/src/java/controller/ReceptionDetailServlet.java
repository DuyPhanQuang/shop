/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ItemMasterDAO;
import dao.ReceptionDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
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
import model.ItemObject;
import model.Reception;
import model.User;

/**
 *
 * @author QuangDuy
 */
public class ReceptionDetailServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {

            HttpSession session = request.getSession();
            ItemMasterDAO imDAO = new ItemMasterDAO();
            ReceptionDAO reDAO = new ReceptionDAO();
            ArrayList<Reception> reList = new ArrayList<Reception>();
            ArrayList<ItemMaster> im = new ArrayList<ItemMaster>();
            String command = request.getParameter("commandRC");

            switch (command) {
                case "getReCode":
                    reList = reDAO.getCreatingReception();
                    request.setAttribute("chooseReCode", reList);
                    im = imDAO.getListItemMaster();
                    request.setAttribute("item_master", im);
                    RequestDispatcher rd = request.getRequestDispatcher("/receptiondetail.jsp");
                    rd.forward(request, response);
                    break;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReceptionDetailServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = new User();
        user = (User) session.getAttribute("user");
        Reception re = new Reception();
        String command = request.getParameter("command");
        ArrayList<ItemObject> ios = new ArrayList<ItemObject>();
        ReceptionDAO reDAO = new ReceptionDAO();

        switch (command) {
            case "insert":
                String[] selectedItems = request.getParameterValues("selectedItems");
                for (String selectedItem : selectedItems) {
                    try {
                        System.out.println("check" + selectedItem);
//                        Reception res = new Reception();
//                        res = reDAO.getReceptionByCode(request.getParameter("choose_recode"));
                        ItemObject io = new ItemObject();

                        io.setItemId(Integer.parseInt(request.getParameter("io_id")));
                        io.setItemCode(request.getParameter("io_code"));
                        io.setItemQty(Integer.parseInt(request.getParameter("item_qty")));
                        ios.add(io);
                        re.setItemObjs(ios);
                        re.setReProjectCode(user.userPC);
                        re.setReCode(request.getParameter("choose_recode"));
                        re.setReId(1);
                        reDAO.addReceptionDetail(re);
                        RequestDispatcher rd = getServletContext().getRequestDispatcher("/receptiondetail.jsp");
                        rd.forward(request, response);
                    } catch (SQLException ex) {
                        Logger.getLogger(ReceptionDetailServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;

        }

    }

}
