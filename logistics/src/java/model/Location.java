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
public class Location {
    private int locationID;
    private String locationPC;
    private String locationCode;
    private String locationName;
    private int rowOfLocation;
    private String createAt;
    private String updateAt;
    private int userUpdateId;

    public int getUserUpdateId() {
        return userUpdateId;
    }

    public void setUserUpdateId(int userUpdateId) {
        this.userUpdateId = userUpdateId;
    }

    public Location() {
    }
    
    

    public int getLocationID() {
        return locationID;
    }

    public void setLocationID(int locationID) {
        this.locationID = locationID;
    }

    public String getLocationPC() {
        return locationPC;
    }

    public void setLocationPC(String locationPC) {
        this.locationPC = locationPC;
    }

    public String getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public int getRowOfLocation() {
        return rowOfLocation;
    }

    public void setRowOfLocation(int rowOfLocation) {
        this.rowOfLocation = rowOfLocation;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public String getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(String updateAt) {
        this.updateAt = updateAt;
    }
    
    
}
