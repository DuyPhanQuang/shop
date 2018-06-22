/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author NguyenPH
 */
public class ItemLocation {
    private int id;
    private String projectCode;
    private int itemId;
    private String itemCode;
    private int locationId;
    private String locationCode;
    private int row;
    private String CcreateAt;
    private String updateAt;
    private int userIdUpdate;
    private int receptionId;
    private String receptionCode;

    public int getReceptionId() {
        return receptionId;
    }

    public void setReceptionId(int receptionId) {
        this.receptionId = receptionId;
    }

    public String getReceptionCode() {
        return receptionCode;
    }

    public void setReceptionCode(String receptionCode) {
        this.receptionCode = receptionCode;
    }

    public int getId() {
        return id;
    }

    public ItemLocation() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public String getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public String getCcreateAt() {
        return CcreateAt;
    }

    public void setCcreateAt(String CcreateAt) {
        this.CcreateAt = CcreateAt;
    }

    public String getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(String updateAt) {
        this.updateAt = updateAt;
    }

    public int getUserIdUpdate() {
        return userIdUpdate;
    }

    public void setUserIdUpdate(int userIdUpdate) {
        this.userIdUpdate = userIdUpdate;
    }
    
}
