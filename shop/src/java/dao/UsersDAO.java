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
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Users;

/**
 *
 * @author QuangDuy
 */
public class UsersDAO {
    //check email existed in db
    public boolean checkEmail(String _email) {
        Connection connection = DBConnect.getConnection();
        String sql = "SELECT * FROM users WHERE user_email = '"+_email+"' ";
        PreparedStatement ps;
        try {
            ps = connection.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                connection.close();
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsersDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    //method add account
    public boolean insertUser(Users u) {
        Connection connection = DBConnect.getConnection();
        String sql = "INSERT INTO users VALUES(?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareCall(sql);
            ps.setLong(1, u.getUserID());
            ps.setString(2, u.getUserEmail());
            ps.setString(3, u.getUserPass());
            ps.setBoolean(4, u.isUserRole());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UsersDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
  
    
    // check login
    public Users login(String email, String password ) {
        Connection connection = DBConnect.getConnection();
        String sql = "SELECT * FROM users WHERE user_email = '" + email + "'and user_password = '" + password + "' ";
        PreparedStatement ps;
        try {
            ps = (PreparedStatement) connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Users users = new Users();
                users.setUserID(rs.getLong("_id"));
                users.setUserEmail(rs.getString("user_email"));
                users.setUserPass(rs.getString("user_password"));
                users.setUserRole(rs.getBoolean("user_role"));
                connection.close();
                return users;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
