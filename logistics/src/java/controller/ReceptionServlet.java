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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.ItemMaster;
import model.Reception;
import model.User;

/**
 *
 * @author QuangDuy
 */
public class ReceptionServlet extends HttpServlet {

    private ReceptionDAO reDAO;
    private Reception re;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            reDAO = new ReceptionDAO();
            re = new Reception();
            ItemMaster im = new ItemMaster();
            User user = new User();
            HttpSession session = request.getSession();
            user = (User) session.getAttribute("user");
            String command = request.getParameter("command");
            switch (command) {
                case "insert":
                    re.setReProjectCode(user.userPC);
                    re.setReCode(request.getParameter("re_code"));
                    re.setReNote(request.getParameter("re_node"));
                    re.setReUserUpdateID(user.userRole);
                    reDAO.addReception(re);
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/reception.jsp");
                    rd.forward(request, response);
                break;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReceptionServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
