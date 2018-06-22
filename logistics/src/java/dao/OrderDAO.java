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
import model.Order;
import tools.DateToString;

/**
 *
 * @author NguyenPH
 */
public class OrderDAO {
    
    public int insertOrder(Order or) throws SQLException{
        try {
            if(this.isOrderExist(or.getOrderCode())){
                return 0;
            }
            Connection connection = new DBConnect().getConnection();
            String sql = "INSERT INTO ordering (OR_CODE, OR_PC, OR_CREATE_AT, OR_DELIVERY_AT, OR_UPDATE_AT, OR_USER_UPDATE, OR_STATUS, OR_NOTE) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps =  connection.prepareStatement(sql);
            ps.setString(1, or.getOrderCode());
            ps.setString(2, or.getOrderPC());
            ps.setString(3, DateToString.getCurrentTime());
            ps.setString(4, DateToString.getCurrentTime());
            ps.setString(5, DateToString.getCurrentTime());
            ps.setInt(6, or.getUserUpdateId());
            ps.setString(7, "CREATING");
            ps.setString(8, or.getNote());
            
            int checkSuccess = ps.executeUpdate();
            
            if (checkSuccess <= 0){
                connection.close();
                return 0;
            }
            connection.close();
            return 1;
        } 
        catch(SQLException ex){
            throw new SQLException(ex.getMessage());
        }
        
    }
    
    public int insertOrderDetail(Order or)throws SQLException{
        try {
            Connection connection = new DBConnect().getConnection();
            for(ItemObject io : or.getItemObjs()){
                String sql = "INSERT INTO order_detail (OD_PC, OD_QTY, OD_IM_ID, OD_IM_CODE, OD_OR_CODE, OD_OR_ID) " +
                            "VALUES (?, ?, ?, ?, ?, ?)";
                PreparedStatement ps =  connection.prepareStatement(sql);
                ps.setString(1, or.getOrderPC());
                ps.setInt(2, io.getItemQty());
                ps.setInt(3, io.getItemId());
                ps.setString(4, io.getItemCode());
                ps.setString(5, or.getOrderCode());
                ps.setInt(6, or.getOrderId());

                int checkSuccess = ps.executeUpdate();

                if (checkSuccess <= 0){
                    connection.close();
                    return 0;
                }
            }
            
            ItemDAO itemDAO = new ItemDAO();
            itemDAO.getItems(or.getOrderPC(), or.getItemObjs());
            
            String sql = "UPDATE ordering SET OR_STATUS = 'COMPLETED' WHERE OR_CODE = ?";
            PreparedStatement ps =  connection.prepareStatement(sql);
            ps.setString(1, or.getOrderCode());
            if(ps.executeUpdate() <=0){
                connection.close();
                return 1;
            }
            else{
                connection.close();
                return 0;
            }
            
        } 
        catch(SQLException ex){
            
            throw new SQLException(ex.getMessage());
        }
    }
    
    public boolean isOrderExist(String orderCode) throws SQLException{
        try {
            Connection cnn = new DBConnect().getConnection();
            String sql = "SELECT OR_ID FROM ordering WHERE OR_CODE = ?";
            PreparedStatement ps = cnn.prepareStatement(sql);
            ps.setString(1, orderCode);
            
            ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    cnn.close();
                    return true;
                }
            return false;
            
        }
        catch(SQLException ex){
            throw new SQLException(ex.getMessage());
        }
    }
    
    
    public ArrayList<Order> getAllOrder()throws SQLException{
        try {
            Connection connection = new DBConnect().getConnection();
            String sql = "SELECT * FROM ordering";
            PreparedStatement ps =  connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            ArrayList<Order> listOrders = new ArrayList();
            while(rs.next()){
                Order or = new Order();
                or.setOrderId(rs.getInt("OR_ID"));
                or.setOrderCode(rs.getString("OR_CODE"));
                or.setOrderPC(rs.getString("OR_PC"));
                or.setCreateAt(rs.getString("OR_CREATE_AT"));
                or.setReceivedAt(rs.getString("OR_DELIVERY_AT"));
                or.setUpdateAt(rs.getString("OR_UPDATE_AT"));
                or.setUserUpdateId(rs.getInt("OR_USER_UPDATE"));
                or.setStatus(rs.getString("OR_STATUS"));
                or.setNote(rs.getString("OR_NOTE"));
                listOrders.add(or);
            }
            connection.close();
            return listOrders;
        }
        catch(SQLException ex){
            throw new SQLException(ex.getMessage());
        }
    }
    
    public ArrayList<Order> getCreatingOrder()throws SQLException{
        try {
            Connection connection = new DBConnect().getConnection();
            String sql = "SELECT * FROM ordering WHERE OR_STATUS = 'CREATING'";
            PreparedStatement ps =  connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            ArrayList<Order> listOrders = new ArrayList();
            while(rs.next()){
                Order or = new Order();
                or.setOrderId(rs.getInt("OR_ID"));
                or.setOrderCode(rs.getString("OR_CODE"));
                or.setOrderPC(rs.getString("OR_PC"));
                or.setCreateAt(rs.getString("OR_CREATE_AT"));
                or.setReceivedAt(rs.getString("OR_DELIVERY_AT"));
                or.setUpdateAt(rs.getString("OR_UPDATE_AT"));
                or.setUserUpdateId(rs.getInt("OR_USER_UPDATE"));
                or.setStatus(rs.getString("OR_STATUS"));
                or.setNote(rs.getString("OR_NOTE"));
                listOrders.add(or);
            }
            connection.close();
            return listOrders;
        }
        catch(SQLException ex){
            throw new SQLException(ex.getMessage());
        }
    }
    
    
    public ArrayList<Order> getCompletedOrder()throws SQLException{
        try {
            Connection connection = new DBConnect().getConnection();
            String sql = "SELECT * FROM ordering WHERE OR_STATUS = 'COMPLETED'";
            PreparedStatement ps =  connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            ArrayList<Order> listOrders = new ArrayList();
            while(rs.next()){
                Order or = new Order();
                or.setOrderId(rs.getInt("OR_ID"));
                or.setOrderCode(rs.getString("OR_CODE"));
                or.setOrderPC(rs.getString("OR_PC"));
                or.setCreateAt(rs.getString("OR_CREATE_AT"));
                or.setReceivedAt(rs.getString("OR_DELIVERY_AT"));
                or.setUpdateAt(rs.getString("OR_UPDATE_AT"));
                or.setUserUpdateId(rs.getInt("OR_USER_UPDATE"));
                or.setStatus(rs.getString("OR_STATUS"));
                or.setNote(rs.getString("OR_NOTE"));
                listOrders.add(or);
            }
            connection.close();
            return listOrders;
        }
        catch(SQLException ex){
            throw new SQLException(ex.getMessage());
        }
    }
    
    // lay reception detail bang reception code
    public ArrayList<ItemObject> getOrderDetail(String orCode) throws SQLException{
        try {
            Connection cnn = new DBConnect().getConnection();
            String sql = "SELECT * FROM order_detail INNER JOIN item_master ON OD_IM_CODE = IM_ITEM_CODE WHERE OD_OR_CODE = ?";
            PreparedStatement ps = cnn.prepareStatement(sql);
            ps.setString(1, orCode);
            ArrayList<ItemObject> listItemObject = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                ItemObject item = new ItemObject();
                item.setItemQty(rs.getInt("OD_QTY"));
                item.setItemId(rs.getInt("OD_IM_ID"));
                item.setItemCode(rs.getString("OD_IM_CODE"));
                item.setItemName(rs.getString("IM_NAME"));
                item.setItemDesc(rs.getString("IM_DESCRIPTION"));
                listItemObject.add(item);
            }
            cnn.close();
            return listItemObject;

        }
        catch(SQLException ex){
            throw new SQLException(ex.getMessage());
        }
    }
    
    public Order getOrderByCode(String OR_CODE) throws SQLException{
        try {
            Connection cnn = new DBConnect().getConnection();
            String sql = "SELECT * FROM ordering WHERE OR_CODE = ?";
            PreparedStatement ps = cnn.prepareStatement(sql);
            ps.setString(1, OR_CODE);
            
            ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    Order or = new Order();
                    or.setOrderId(rs.getInt("OR_ID"));
                    or.setOrderCode(rs.getString("OR_CODE"));
                    or.setOrderPC(rs.getString("OR_PC"));
                    or.setCreateAt(rs.getString("OR_CREATE_AT"));
                    or.setReceivedAt(rs.getString("OR_DELIVERY_AT"));
                    or.setUpdateAt(rs.getString("OR_UPDATE_AT"));
                    or.setUserUpdateId(rs.getInt("OR_USER_UPDATE"));
                    or.setStatus(rs.getString("OR_STATUS"));
                    or.setNote(rs.getString("OR_NOTE"));
                    cnn.close();
                    return or;
                }
                cnn.close();
                return null;
        }
        catch(SQLException ex){
            throw new SQLException(ex.getMessage());
        }
    }
    
}
