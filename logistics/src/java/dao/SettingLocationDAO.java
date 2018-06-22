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
import model.ItemLocation;
import tools.DateToString;

/**
 *
 * @author NguyenPH
 */
public class SettingLocationDAO {

    public SettingLocationDAO() {
    }
    
    public String InsertListItemLocation(ArrayList<ItemLocation> itemLocs)throws SQLException{
        try {
            Connection connection = new DBConnect().getConnection();
            for(ItemLocation il : itemLocs){
                String sql = "INSERT INTO item_location (IL_PC, IL_IT_ID, IL_IT_CODE, IL_LO_ID, IL_LO_CODE, IL_ROW, IL_CREATE_AT, IL_UPDATE_AT, IL_USER_UPDATE_ID, IL_RE_ID, IL_RE_CODE) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement ps =  connection.prepareStatement(sql);
                ps.setString(1, il.getProjectCode());
                ps.setInt(2, il.getItemId());
                ps.setString(3, il.getItemCode());
                ps.setInt(4, il.getLocationId());
                ps.setString(5, il.getLocationCode());
                ps.setInt(6, il.getRow());
                ps.setString(7, DateToString.getCurrentTime());
                ps.setString(8, DateToString.getCurrentTime());
                ps.setInt(9, il.getUserIdUpdate());
                ps.setInt(10, il.getReceptionId());
                ps.setString(11, il.getReceptionCode());

                int checkSuccess = ps.executeUpdate();
                
                if (checkSuccess <= 0){
                    connection.close();
                    return "Cannot add item location!";
                }
            }
            connection.close();
            return "Add item location successfully!";
        }
        catch(SQLException ex){
            throw new SQLException(ex.getMessage());
        }
    }
    
    public ArrayList<ItemLocation> getAllItemLocation() throws SQLException{
        try {
            Connection cnn = new DBConnect().getConnection();
            String sql = "SELECT * FROM item_location";
            PreparedStatement ps = cnn.prepareStatement(sql);
            ArrayList<ItemLocation> itemLocs = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                ItemLocation il = new ItemLocation();
                il.setId(rs.getInt("IL_ID"));
                il.setProjectCode(rs.getString("IL_PC"));
                il.setItemId(rs.getInt("IL_IT_ID"));
                il.setItemCode(rs.getString("IL_IT_CODE"));
                il.setLocationId(rs.getInt("IL_LO_ID"));
                il.setLocationCode(rs.getString("IL_LO_CODE"));
                il.setRow(rs.getShort("IL_ROW"));
                il.setCcreateAt(rs.getString("IL_CREATE_AT"));
                il.setUpdateAt(rs.getString("IL_UPDATE_AT"));
                il.setUserIdUpdate(rs.getInt("IL_USER_UPDATE_ID"));
                il.setReceptionId(rs.getInt("IL_RE_ID"));
                il.setReceptionCode(rs.getString("IL_RE_CODE"));
                itemLocs.add(il);
            }
            return itemLocs;
        }
        catch(SQLException ex){
            throw new SQLException(ex.getMessage());
        }
    }
    
    public ArrayList<ItemLocation> getItemLocationByLocation(String IL_RE_CODE) throws SQLException{
        try {
            Connection cnn = new DBConnect().getConnection();
            String sql = "SELECT * FROM item_location WHERE IL_RE_CODE = ?";
            PreparedStatement ps = cnn.prepareStatement(sql);
            ps.setString(1, IL_RE_CODE);
            ArrayList<ItemLocation> itemLocs = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                ItemLocation il = new ItemLocation();
                il.setId(rs.getInt("IL_ID"));
                il.setProjectCode(rs.getString("IL_PC"));
                il.setItemId(rs.getInt("IL_IT_ID"));
                il.setItemCode(rs.getString("IL_IT_CODE"));
                il.setLocationId(rs.getInt("IL_LO_ID"));
                il.setLocationCode(rs.getString("IL_LO_CODE"));
                il.setRow(rs.getShort("IL_ROW"));
                il.setCcreateAt(rs.getString("IL_CREATE_AT"));
                il.setUpdateAt(rs.getString("IL_UPDATE_AT"));
                il.setUserIdUpdate(rs.getInt("IL_USER_UPDATE_ID"));
                il.setReceptionId(rs.getInt("IL_RE_ID"));
                il.setReceptionCode(rs.getString("IL_RE_CODE"));
                itemLocs.add(il);
            }
            return itemLocs;

        }
        catch(SQLException ex){
            throw new SQLException(ex.getMessage());
        }
    }

}
