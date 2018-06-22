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
import java.util.ArrayList;
import model.Location;
import tools.DateToString;

/**
 *
 * @author NguyenPH
 */
public class LocationDAO {

    public LocationDAO() {
    }
    
    public String insertLocation(Location lo)throws IOException{
        try {
            if(this.isExistLocation(lo.getLocationCode())){
                return "Location is exist!";
            }
            Connection cnn = new DBConnect().getConnection();
            String sql = "INSERT INTO location (LO_PC, LO_CODE, LO_NAME, LO_ROW_NUM, LO_CREATE_AT, LO_UPDATE_AT, LO_USER_UPDATE) "+
                        "VALUES (?, ?, ?, ?, ?, ?,?)";
            PreparedStatement ps = cnn.prepareStatement(sql);
            ps.setString(1, lo.getLocationPC());
            ps.setString(2, lo.getLocationCode());
            ps.setString(3, lo.getLocationName());
            ps.setInt(4, lo.getRowOfLocation());
            ps.setString(5, DateToString.getCurrentTime());
            ps.setString(6, DateToString.getCurrentTime());
            ps.setInt(7, lo.getUserUpdateId());
            
            
            int checkSuccess = ps.executeUpdate();
            
            if (checkSuccess <= 0){
                cnn.close();
                return "Add location successfully!";
            }
            cnn.close();
            return "Cannot add location!";
        }
        catch(SQLException ex){
            throw new IOException(ex.getMessage());
        }
    }
    
    public ArrayList<Location> getAllLocation()throws SQLException{
        try {
            Connection connection = new DBConnect().getConnection();
            String sql = "SELECT * FROM location";
            PreparedStatement ps =  connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            ArrayList<Location> listLocation = new ArrayList<>();
            while(rs.next()) {
                Location lo = new Location();
                lo.setLocationID(rs.getInt("LO_ID"));
                lo.setLocationPC(rs.getString("LO_PC"));
                lo.setLocationCode(rs.getString("LO_CODE"));
                lo.setLocationName(rs.getString("LO_NAME"));
                lo.setRowOfLocation(rs.getInt("LO_ROW_NUM"));
                lo.setCreateAt(rs.getString("LO_CREATE_AT"));
                lo.setUpdateAt(rs.getString("LO_UPDATE_AT"));
                lo.setUserUpdateId(rs.getInt("LO_USER_UPDATE"));
                listLocation.add(lo);
            }
            connection.close();
            return listLocation;
        }
        catch(SQLException ex){
            throw new SQLException(ex.getMessage());
        }
    }
    
    public Location getLocationByCode(String locationCode)throws SQLException{
        try {
            Connection cnn = new DBConnect().getConnection();
            String sql = "SELECT * FROM location WHERE LO_CODE = ?";
            PreparedStatement ps = cnn.prepareStatement(sql);
            ps.setString(1, locationCode);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                Location lo = new Location();
                lo.setLocationID(rs.getInt("LO_ID"));
                lo.setLocationPC(rs.getString("LO_PC"));
                lo.setLocationCode(rs.getString("LO_CODE"));
                lo.setLocationName(rs.getString("LO_NAME"));
                lo.setRowOfLocation(rs.getInt("LO_ROW_NUM"));
                lo.setCreateAt(rs.getString("LO_CREATE_AT"));
                lo.setUpdateAt(rs.getString("LO_UPDATE_AT"));
                lo.setUserUpdateId(rs.getInt("LO_USER_UPDATE"));
                cnn.close();
                return lo;
            }
            cnn.close();
            return null;
        }
        catch(SQLException ex){
            throw new SQLException(ex.getMessage());
        }
    }
    
    
    public boolean isExistLocation(String locationCode)throws SQLException{
        try {
            Connection cnn = new DBConnect().getConnection();
            String sql = "SELECT LO_ID FROM location WHERE LO_CODE = ?";
            PreparedStatement ps = cnn.prepareStatement(sql);
            ps.setString(1, locationCode);
            
            ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    //int id = rs.getInt("IM_ID");
                    cnn.close();
                    return true;
                }
            return false;
            
        }
        catch(SQLException ex){
            throw new SQLException(ex.getMessage());
        }
    }
    
    public static void main(String[] args) throws SQLException {
        LocationDAO dao = new LocationDAO();
           
            System.out.println(dao.getLocationByCode("LO101"));
        

    }
    
}
