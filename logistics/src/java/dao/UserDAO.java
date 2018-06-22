/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import connect.DBConnect;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.User;

/**
 *
 * @author QuangDuy
 */
public class UserDAO {
    public UserDAO(){}
    //check email existed in db
//    public boolean checkEmail(String _email) {
//        Connection connection =new DBConnect().getConnection();
//        String sql = "SELECT * FROM users WHERE user_email = '"+_email+"' ";
//        PreparedStatement ps;
//        try {
//            ps = connection.prepareCall(sql);
//            ResultSet rs = ps.executeQuery();
//            while(rs.next()) {
//                connection.close();
//                return true;
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(UsersDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        return false;
//    }
    
    //method add account
//    public boolean insertUser(Users u) {
//        Connection connection =new DBConnect().getConnection();
//        String sql = "INSERT INTO users VALUES(?,?,?,?)";
//        try {
//            PreparedStatement ps = connection.prepareCall(sql);
//            ps.setLong(1, u.getUserID());
//            ps.setString(2, u.getUserEmail());
//            ps.setString(3, u.getUserPass());
//            ps.setBoolean(4, u.isUserRole());
//            ps.executeUpdate();
//            return true;
//        } catch (SQLException ex) {
//            Logger.getLogger(UsersDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return false;
//    }
    
  
    
    // check login
    public User login(String userPC, String userAcc, String userPassword ) throws IOException {
        try {
            Connection connection = new DBConnect().getConnection();
            String sql = "SELECT * FROM user WHERE US_PC = ? and US_ACCOUNT = ? and US_PASSWORD = ?";
            PreparedStatement ps;

            ps = connection.prepareStatement(sql);
            ps.setString(1, userPC);
            ps.setString(2, userAcc);
            ps.setString(3, userPassword);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                User user = new User();
                user.userID = rs.getInt("US_ID");
                user.userName = rs.getString("US_NAME");
                user.userPC = rs.getString("US_PC");
                user.userRole = rs.getInt("US_RO_ID");
                connection.close();
                return user;
            }
            connection.close();
            return null;
        } catch (SQLException e) {
            throw new IOException(e.getMessage());
        }
    }
}