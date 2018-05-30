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
import model.Category;

/**
 *
 * @author QuangDuy
 */
public class CategoryDAO {
    public ArrayList<Category> getListCategory() {
        Connection connection = DBConnect.getConnection();
        String sql = "SELECT * FROM category";
        ArrayList<Category> listCategory  = new ArrayList<>();
        try {
        PreparedStatement ps = connection.prepareCall(sql);
        ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Category category = new Category(1, "");
                category.setCategoryID(rs.getLong("_id"));
                category.setCategoryName(rs.getString("category_name"));
                listCategory.add(category);
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listCategory;
    }
    
    public static void main(String[] args) throws SQLException {
        CategoryDAO dao = new CategoryDAO();
        for (Category ds : dao.getListCategory()) {
            System.out.println(ds.getCategoryID() + " - " + ds.getCategoryName());
        }
    }

}
