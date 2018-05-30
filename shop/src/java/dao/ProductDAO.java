/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import connect.DBConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Product;

/**
 *
 * @author QuangDuy
 */
public class ProductDAO {
    
    //get list product by parameters category_id
    public ArrayList<Product> getListProductByCategory(int brand_id) throws SQLException {
        Connection connection = DBConnect.getConnection();
        String sql = "SELECT * FROM product WHERE brand_id = '"+brand_id+"' ";
        PreparedStatement ps =  connection.prepareCall(sql);
        ResultSet rs = ps.executeQuery();
        ArrayList<Product> listProduct = new ArrayList<>();
        while(rs.next()) {
            Product product = new Product();
            product.setProductID(rs.getInt("id"));
            product.setBrandID(rs.getInt("brand_id"));
            product.setProductQuantity(rs.getInt("quantity"));
            product.setProductName(rs.getString("name"));
            product.setProductImage(rs.getString("image"));
            product.setProductImportPrice(rs.getDouble("import_price"));
            product.setProductImportPrice(rs.getDouble("sale_price"));
            product.setProductDescription(rs.getString("description"));
            listProduct.add(product);
        }
        return listProduct;
    }
    
    //show detail products
    public Product getProduct(int productID) throws SQLException {
        Connection connection = DBConnect.getConnection();
        String sql = "SELECT * FROM product WHERE id = '"+productID+"' ";
        PreparedStatement ps = connection.prepareCall(sql);
        ResultSet rs = ps.executeQuery();
        Product product = new Product();
        while(rs.next()) {
            product.setProductID(rs.getInt("id"));
            product.setBrandID(rs.getInt("brand_id"));
            product.setProductQuantity(rs.getInt("quantity"));
            product.setProductName(rs.getString("name"));
            product.setProductImage(rs.getString("image"));
            product.setProductImportPrice(rs.getDouble("import_price"));
            product.setProductImportPrice(rs.getDouble("sale_price"));
            product.setProductDescription(rs.getString("description"));
        }
        return product;
    }
    
    //lam pjhan trang: lay danh sach san pham
    public ArrayList<Product> getListProductByNav(int brandID, int firstResult, int maxResult ) throws SQLException {
        Connection connection = DBConnect.getConnection();
        String sql = "SELECT * FROM product WHERE brand_id = '"+brandID+"' limit ?,? ";
        PreparedStatement ps = connection.prepareCall(sql);
        ps.setInt(1, firstResult);
        ps.setInt(2, maxResult);
        ResultSet rs = ps.executeQuery();
        ArrayList<Product> listProduct = new ArrayList<>();
        while (rs.next()) {
            Product product = new Product();
            product.setProductID(rs.getInt("id"));
            product.setBrandID(rs.getInt("brand_id"));
            product.setProductQuantity(rs.getInt("quantity"));
            product.setProductName(rs.getString("name"));
            product.setProductImage(rs.getString("image"));
            product.setProductImportPrice(rs.getDouble("import_price"));
            product.setProductImportPrice(rs.getDouble("sale_price"));
            product.setProductDescription(rs.getString("description"));
        }
        return listProduct;
    } 
    
    //phuong thuc tinh tong san pham cua danh muc de xac dinh duoc co bao nheiu trang
    public int countProductByCategory(int brandID) throws SQLException {
        Connection connection = DBConnect.getConnection();
        String sql = "SELECT count(id) FROM product WHERE brand_id = '"+brandID+"' ";
        PreparedStatement ps = connection.prepareCall(sql);
        ResultSet rs = ps.executeQuery();
        int count = 0;
        while (rs.next()) {
            count = rs.getInt(1);
        }
        return count;
    }
    
    //method them hang hoa tu trang .jsp
    public boolean insertProduct(Product u) {
        Connection connection = DBConnect.getConnection();
        String sql = "INSERT INTO product VALUES (?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareCall(sql);
            ps.setInt(1, u.getProductID());
            ps.setInt(2, u.getBrandID());
            ps.setString(3, u.getProductName());
            ps.setString(4, u.getProductDescription());
            ps.setString(5, u.getProductImage());
            ps.setDouble(6, u.getProductImportPrice());
            ps.setDouble(7, u.getProductSalePrice());
            ps.setInt(8, u.getProductQuantity());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public static void main(String[] args) throws SQLException{
        ProductDAO dao = new ProductDAO();
//        for (Product p : dao.getListProductByCategory(1)) {
//            System.out.println(p.getProductID() + " - " + p.getProductName());
//        }
//        System.out.println(dao.getProduct(1).getProductName());
        System.out.println(dao.countProductByCategory(1));
    }
}
