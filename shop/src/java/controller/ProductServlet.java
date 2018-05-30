/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Product;

/**
 *
 * @author QuangDuy
 */
public class ProductServlet extends HttpServlet {
    
    ProductDAO productDAO = new ProductDAO();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String command = request.getParameter("command");
        String url = "";
        Product product = new Product();
        HttpSession session = request.getSession();
        switch (command) {
            case "insertProduct":
                product.setBrandID(0);
                product.setProductName(request.getParameter("name"));
                product.setProductImage(request.getParameter("image"));
                product.setProductDescription(request.getParameter("description"));
                product.setProductImportPrice(Integer.parseInt(request.getParameter("importPrice")));
                product.setProductSalePrice(Integer.parseInt(request.getParameter("salePrice")));
                productDAO.insertProduct(product);
                session.setAttribute("product", product);
                url="/register.jsp";
                break;
        }
        RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
        rd.forward(request, response);
    }


}
