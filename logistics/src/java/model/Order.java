/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author NguyenPH
 */
public class Order {
    private int orderId;
    private String orderCode;
    private String orderPC;
    private String createAt;
    private String receivedAt;
    private String updateAt;
    private int userUpdateId;
    private String status;
    private String note;

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
    private ArrayList<ItemObject> itemObjs;

    public Order() {
        this.itemObjs = new ArrayList<>();
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getOrderPC() {
        return orderPC;
    }

    public void setOrderPC(String orderPC) {
        this.orderPC = orderPC;
    }

    public String getCreateAt() {
        return createAt;
    }

    public ArrayList<ItemObject> getItemObjs() {
        return itemObjs;
    }

    public void setItemObjs(ArrayList<ItemObject> itemObjs) {
        this.itemObjs = itemObjs;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public String getReceivedAt() {
        return receivedAt;
    }

    public void setReceivedAt(String receivedAt) {
        this.receivedAt = receivedAt;
    }

    public String getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(String updateAt) {
        this.updateAt = updateAt;
    }

    public int getUserUpdateId() {
        return userUpdateId;
    }

    public void setUserUpdateId(int userUpdateId) {
        this.userUpdateId = userUpdateId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
}
