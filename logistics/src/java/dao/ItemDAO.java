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
import model.ItemObject;
import tools.DateToString;

/**
 *
 * @author NguyenPH
 */
public class ItemDAO {
    public int updateItems(){return 0;}
    
    public boolean checkItemExist(String itemCode) throws SQLException{
    
        try {
            Connection cnn = new DBConnect().getConnection();
            String sql = "SELECT IT_ID FROM item WHERE IT_IM_CODE = ?";
            PreparedStatement ps = cnn.prepareStatement(sql);
            ps.setString(1, itemCode);
            
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
    
    public int insertItems(String projectCode, ArrayList<ItemObject> ios)throws SQLException {
        try {
            
            Connection cnn = new DBConnect().getConnection();
            for(ItemObject io:ios){
                if(this.checkItemExist(io.getItemCode())) {
                    String sql = "UPDATE item SET IT_STOCK_QTY = (IT_STOCK_QTY + ?) WHERE IT_IM_CODE = ?";
                    PreparedStatement ps = cnn.prepareStatement(sql);
                    ps.setInt(1, io.getItemQty());
                    ps.setString(2, io.getItemCode());
                    
                    int checkSuccess = ps.executeUpdate();

                    if (checkSuccess <= 0){
                        cnn.close();
                        return 0;
                    }
                }
                else {
                    String sql = "INSERT INTO item (IT_PC, IT_IM_ID, IT_IM_CODE, IT_STOCK_QTY, IT_UPDATE_AT) "+
                            "VALUES (?, ?, ?, ?, ?)";
                    PreparedStatement ps = cnn.prepareStatement(sql);
                    ps.setString(1, projectCode);
                    ps.setInt(2, io.getItemId());
                    ps.setString(3, io.getItemCode());
                    ps.setInt(4, io.getItemQty());
                    ps.setString(5, DateToString.getCurrentTime());

                    int checkSuccess = ps.executeUpdate();

                    if (checkSuccess <= 0){
                        cnn.close();
                        return 0;
                    }
                }
            }
            cnn.close();
            return 1;
            
        }
        catch(SQLException ex){
            throw new SQLException(ex.getMessage());
        }
    }
    
    
    public int getItems(String projectCode, ArrayList<ItemObject> ios)throws SQLException {
        try {
            
            Connection cnn = new DBConnect().getConnection();
            for(ItemObject io:ios){
                if(this.checkItemExist(io.getItemCode())) {
                    String sql = "UPDATE item SET IT_STOCK_QTY = (IT_STOCK_QTY - ?) WHERE IT_IM_CODE = ?";
                    PreparedStatement ps = cnn.prepareStatement(sql);
                    ps.setInt(1, io.getItemQty());
                    ps.setString(2, io.getItemCode());
                    
                    int checkSuccess = ps.executeUpdate();

                    if (checkSuccess <= 0){
                        cnn.close();
                        return 0;
                    }
                }
                
            }
            cnn.close();
            return 1;
        }
        catch(SQLException ex){
            throw new SQLException(ex.getMessage());
        }
    }
    
    public int insertItems(String projectCode, ItemObject io)throws SQLException {
        try {
            
            Connection cnn = new DBConnect().getConnection();
            String sql = "INSERT INTO item (IT_PC, IT_IM_ID, IT_IM_CODE, IT_STOCK_QTY, IT_UPDATE_AT) "+
                        "VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = cnn.prepareStatement(sql);
            ps.setString(1, projectCode);
            ps.setInt(2, io.getItemId());
            ps.setString(3, io.getItemCode());
            ps.setInt(4, io.getItemQty());
            ps.setString(5, DateToString.getCurrentTime());
            
            int checkSuccess = ps.executeUpdate();
            
            if (checkSuccess <= 0){
                cnn.close();
                return 0;
            }
            cnn.close();
            return 1;
            
        }
        catch(SQLException ex){
            throw new SQLException(ex.getMessage());
        }
    }
    
    
    
    public ArrayList<ItemObject>getAllItems()throws SQLException {
        try {
            Connection connection = new DBConnect().getConnection();
            String sql = "SELECT * FROM item INNER JOIN item_master ON IT_IM_CODE = IM_ITEM_CODE";
            PreparedStatement ps =  connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            ArrayList<ItemObject> listItemObject = new ArrayList<>();
            while(rs.next()) {
                ItemObject item = new ItemObject();
                item.setItemId(rs.getInt("IT_ID"));
                item.setItemCode(rs.getString("IT_IM_CODE"));
                item.setItemQty(rs.getInt("IT_STOCK_QTY"));
                item.setItemName(rs.getString("IM_NAME"));
                item.setItemDesc(rs.getString("IM_DESCRIPTION"));
                
                listItemObject.add(item);
            }
            connection.close();
            return listItemObject;
        }
        catch(SQLException ex) {
            throw new SQLException(ex.getMessage());
        }
        
    }
    
    
}
