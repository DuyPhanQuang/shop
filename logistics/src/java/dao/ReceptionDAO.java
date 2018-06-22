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
import java.util.Arrays;
import model.ItemMaster;
import model.ItemObject;
import model.Reception;
import model.User;
import tools.DateToString;

/**
 *
 * @author QuangDuy
 */
public class ReceptionDAO {

    public String addReception(Reception re) throws SQLException {
        try {
            if (this.isReceptionExist(re.getReCode())) {
                return "Cannot create reception, reception is exist in database!";
            }
            Connection connection = new DBConnect().getConnection();
            String sql = "INSERT INTO reception (RE_PC, RE_CODE, RE_CREATE_AT, RE_UPDATE_AT, RE_USER_UPDATE_ID, RE_STATUS, RE_NOTE) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, re.getReProjectCode());
            ps.setString(2, re.getReCode());
            ps.setString(3, DateToString.getCurrentTime());
            ps.setString(4, DateToString.getCurrentTime());
            ps.setInt(5, re.getReUserUpdateID());
            ps.setString(6, "CREATING");
            ps.setString(7, re.getReNote());

            int checkSuccess = ps.executeUpdate();

            if (checkSuccess <= 0) {
                connection.close();
                return "Add reception successfully!";
            }
            connection.close();
            return "Cannot add reception!";
        } catch (SQLException ex) {
            throw new SQLException(ex.getMessage());
        }

    }

    public String addReceptionDetail(Reception re) throws SQLException {
        try {
            Connection connection = new DBConnect().getConnection();
            for (ItemObject io : re.getItemObjs()) {
                String sql = "INSERT INTO reception_detail (RD_PC, RD_RE_ID, RD_RE_CODE, RD_IM_ID, RD_IM_CODE, RD_QTY) "
                        + "VALUES (?, ?, ?, ?, ?, ?)";
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setString(1, re.getReProjectCode());
                ps.setInt(2, re.getReId());
                ps.setString(3, re.getReCode());
                ps.setInt(4, io.getItemId());
                ps.setString(5, io.getItemCode());
                ps.setInt(6, io.getItemQty());

                int checkSuccess = ps.executeUpdate();

                if (checkSuccess <= 0) {
                    connection.close();
                    return "Cannot add reception!";
                }
            }
            //moi them
            ItemDAO itemDAO = new ItemDAO();
            itemDAO.insertItems(re.getReProjectCode(), re.getItemObjs());
            
            String sql = "UPDATE reception SET RE_STATUS = 'PUTTING' WHERE RE_CODE = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, re.getReCode());
            ps.execute();
            connection.close();
            return "Add reception successfully!";
        } catch (SQLException ex) {

            throw new SQLException(ex.getMessage());
        }
    }

    public Reception getReception(String reCode) throws SQLException {
        try {
            Connection connection = new DBConnect().getConnection();
            String sql = "SELECT * FROM reception";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Reception re = new Reception();
                re.setReCode(rs.getString("RE_CODE"));
                re.setReCreateAt(rs.getString("RE_CREATE_AT"));
                re.setReId(rs.getInt("RE_ID"));
                re.setReNote(rs.getString("RE_NOTE"));
                re.setReProjectCode(rs.getString("RE_PC"));
                re.setReStatus(rs.getString("RE_STATUS"));
                re.setReUpdateAt(rs.getString("RE_UPDATE_AT"));
                re.setReUserUpdateID(rs.getInt("RE_USER_UPDATE_ID"));
                connection.close();
                return re;
            }
            connection.close();
            return null;
        } catch (SQLException ex) {
            throw new SQLException(ex.getMessage());
        }
    }

    public Reception getReceptionByCode(String RE_CODE) throws SQLException {
        try {
            Connection cnn = new DBConnect().getConnection();
            String sql = "SELECT * FROM reception WHERE RE_CODE = ?";
            PreparedStatement ps = cnn.prepareStatement(sql);
            ps.setString(1, RE_CODE);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Reception re = new Reception();
                re.setReId(rs.getInt("RE_ID"));
                re.setReProjectCode(rs.getString("RE_PC"));
                re.setReCode(rs.getString("RE_CODE"));
                re.setReCreateAt(rs.getString("RE_CREATE_AT"));
                re.setReUpdateAt(rs.getString("RE_UPDATE_AT"));
                re.setReUserUpdateID(rs.getInt("RE_USER_UPDATE_ID"));
                re.setReStatus(rs.getString("RE_STATUS"));
                re.setReNote(rs.getString("RE_NOTE"));
                cnn.close();
                return re;
            }
            cnn.close();
            return null;
        } catch (SQLException ex) {
            throw new SQLException(ex.getMessage());
        }
    }

    public ArrayList<ItemObject> getReceptionDetail(String reCode) throws SQLException{
        try {
            Connection cnn = new DBConnect().getConnection();
            String sql = "SELECT * FROM reception_detail INNER JOIN item_master ON RD_IM_CODE = IM_ITEM_CODE WHERE RD_RE_CODE = ?";
            PreparedStatement ps = cnn.prepareStatement(sql);
            ps.setString(1, reCode);
            ArrayList<ItemObject> listItemObject = new ArrayList<ItemObject>();
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                ItemObject item = new ItemObject();
                item.setItemCode(rs.getString("RD_IM_CODE"));
                item.setItemDesc(rs.getString("IM_DESCRIPTION"));
                item.setItemId(rs.getInt("RD_IM_ID"));
                item.setItemName(rs.getString("IM_NAME"));
                item.setItemQty(rs.getInt("RD_QTY"));
                listItemObject.add(item);
            }
            cnn.close();
            return listItemObject;

        }
        catch(SQLException ex){
            throw new SQLException(ex.getMessage());
        }
    }

    public boolean isReceptionExist(String receptionCode) throws SQLException {
        try {
            Connection cnn = new DBConnect().getConnection();
            String sql = "SELECT RE_ID FROM reception WHERE RE_CODE = ?";
            PreparedStatement ps = cnn.prepareStatement(sql);
            ps.setString(1, receptionCode);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                cnn.close();
                return true;
            }
            return false;

        } catch (SQLException ex) {
            throw new SQLException(ex.getMessage());
        }
    }
    
    public ArrayList<Reception> getPuttingReception() throws SQLException {
        try {
            Connection connection = new DBConnect().getConnection();
            String sql = "SELECT * FROM reception WHERE RE_STATUS = 'PUTTING'";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            ArrayList<Reception> listReception = new ArrayList<>();
            while (rs.next()) {
                Reception re = new Reception();
                re.setReCode(rs.getString("RE_CODE"));
                re.setReCreateAt(rs.getString("RE_CREATE_AT"));
                re.setReId(rs.getInt("RE_ID"));
                re.setReNote(rs.getString("RE_NOTE"));
                re.setReProjectCode(rs.getString("RE_PC"));
                re.setReStatus(rs.getString("RE_STATUS"));
                re.setReUpdateAt(rs.getString("RE_UPDATE_AT"));
                re.setReUserUpdateID(rs.getInt("RE_USER_UPDATE_ID"));
                listReception.add(re);
            }
            connection.close();
            return listReception;
        } catch (SQLException ex) {
            throw new SQLException(ex.getMessage());
        }
    }
    

    public ArrayList<Reception> getCreatingReception() throws SQLException {
        try {
            Connection connection = new DBConnect().getConnection();
            String sql = "SELECT * FROM reception WHERE RE_STATUS = 'CREATING'";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            ArrayList<Reception> listReception = new ArrayList<>();
            while (rs.next()) {
                Reception re = new Reception();
                re.setReCode(rs.getString("RE_CODE"));
                re.setReCreateAt(rs.getString("RE_CREATE_AT"));
                re.setReId(rs.getInt("RE_ID"));
                re.setReNote(rs.getString("RE_NOTE"));
                re.setReProjectCode(rs.getString("RE_PC"));
                re.setReStatus(rs.getString("RE_STATUS"));
                re.setReUpdateAt(rs.getString("RE_UPDATE_AT"));
                re.setReUserUpdateID(rs.getInt("RE_USER_UPDATE_ID"));
                listReception.add(re);
            }
            connection.close();
            return listReception;
        } catch (SQLException ex) {
            throw new SQLException(ex.getMessage());
        }
    }

    public static void main(String[] args) throws SQLException {
//        ReceptionDAO dao = new ReceptionDAO();
//        for (ItemObject io : dao.getReceptionDetail("RE1111")) {
//            System.out.println(io.getItemName());
//        }
        
//        System.out.println(dao.getProduct(1).getProductName());

    }
}
