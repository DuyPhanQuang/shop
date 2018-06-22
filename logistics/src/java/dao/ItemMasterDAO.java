/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import connect.DBConnect;
import tools.DateToString;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.ItemMaster;
import model.Reception;

/**
 *
 * @author QuangDuy
 */
public class ItemMasterDAO {
    
    //get list product by parameters category_id
    public ArrayList<ItemMaster> getListItemMaster() throws SQLException {
        try {
            Connection connection = new DBConnect().getConnection();
            String sql = "SELECT * FROM item_master";
            PreparedStatement ps =  connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            ArrayList<ItemMaster> listItemMaster = new ArrayList<>();
            while(rs.next()) {
                ItemMaster item = new ItemMaster();
                item.setImDesc(rs.getString("IM_DESCRIPTION"));
                item.setImID(rs.getInt("IM_ID"));
                item.setImItemCode(rs.getString("IM_ITEM_CODE"));
                item.setImPC(rs.getString("IM_PC"));
                item.setImCreateAt(rs.getString("IM_CREATE_AT"));
                item.setImItemName(rs.getString("IM_NAME"));
                listItemMaster.add(item);
            }
            connection.close();
            return listItemMaster;
        }
        catch(SQLException ex) {
            throw new SQLException(ex.getMessage());
        }
        
    }
    
    
    public String addItemMaster(ItemMaster itemMaster, int userID) throws SQLException {
        try {
            if(this.isItemMasterExist(itemMaster.getImItemCode())){
                return "Item master is exist!";
            }
            Connection cnn = new DBConnect().getConnection();
            String sql = "INSERT INTO item_master (IM_PC, IM_ITEM_CODE, IM_NAME, IM_DESCRIPTION, IM_CREATE_AT, IM_UPDATE_AT, IM_ID_USER_UPDATE) "+
                        "VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = cnn.prepareStatement(sql);
            ps.setString(1, itemMaster.getImPC());
            ps.setString(2, itemMaster.getImItemCode());
            ps.setString(3, itemMaster.getImItemName());
            ps.setString(4, itemMaster.getImDesc());
            ps.setString(5, DateToString.getCurrentTime());
            ps.setString(6, DateToString.getCurrentTime());
            ps.setInt(7, userID);
            
            int checkSuccess = ps.executeUpdate();
            
            if (checkSuccess <= 0){
                cnn.close();
                return "Add item master successfully!";
            }
            cnn.close();
            return "Cannot add item master!";
            
        }
        catch(SQLException ex){
            throw new SQLException(ex.getMessage());
        }
    }
    
    public boolean isItemMasterExist(String itemMasterCode) throws SQLException{
        try {
            Connection cnn = new DBConnect().getConnection();
            String sql = "SELECT IM_ID FROM item_master WHERE IM_ITEM_CODE = ?";
            PreparedStatement ps = cnn.prepareStatement(sql);
            ps.setString(1, itemMasterCode);
            
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
    
    
    public static void main(String[] args) throws SQLException{
        ItemMasterDAO imDAO = new ItemMasterDAO();
        for (ItemMaster im : imDAO.getListItemMaster()) {
            System.out.println(im.getImItemName());
        }
//        System.out.println(dao.getProduct(1).getProductName());
        
    }
}
