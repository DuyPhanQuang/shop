/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ReceptionDAO;
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
import model.Reception;

/**
 *
 * @author QuangDuy
 */
public class TableReceptionDetailServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            response.setContentType("text/html");
            request.setCharacterEncoding("UTF-8");
            ReceptionDAO reDAO = new ReceptionDAO();
            ArrayList<Reception> reList = new ArrayList<Reception>();
            String command = request.getParameter("commandTRD");
            switch (command) {
                case "getReCode":
                    reList = reDAO.getPuttingReception();
                    request.setAttribute("chooseReCode", reList);
                    RequestDispatcher rd = request.getRequestDispatcher("/tablereceptiondetail.jsp");
                    rd.forward(request, response);
                    break;
                       
            }
        } catch (SQLException ex) {
            Logger.getLogger(TableReceptionDetailServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
