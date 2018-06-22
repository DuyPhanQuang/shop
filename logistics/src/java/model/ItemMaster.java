/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author QuangDuy
 */
public class ItemMaster {
    private int imID;
    private String imItemCode;
    private String imPC;
    private String imItemName;
    private String imDesc;
    private String imCreateAt;
    public ItemMaster() {}

    public void setImCreateAt(String imCreateAt) {
        this.imCreateAt = imCreateAt;
    }

    public String getImCreateAt() {
        return imCreateAt;
    }
    
    public ItemMaster(int imID, String imItemCode, String imPC, String imItemName, String imDesc) {
        this.imID = imID;
        this.imItemCode = imItemCode;
        this.imPC = imPC;
        this.imItemName = imItemName;
        this.imDesc = imDesc;
    }

    public int getImID() {
        return imID;
    }

    public void setImID(int imID) {
        this.imID = imID;
    }

    public String getImItemCode() {
        return imItemCode;
    }

    public void setImItemCode(String imItemCode) {
        this.imItemCode = imItemCode;
    }

    public String getImPC() {
        return imPC;
    }

    public void setImPC(String imPC) {
        this.imPC = imPC;
    }

    public String getImItemName() {
        return imItemName;
    }

    public void setImItemName(String imItemName) {
        this.imItemName = imItemName;
    }

    public String getImDesc() {
        return imDesc;
    }

    public void setImDesc(String imDesc) {
        this.imDesc = imDesc;
    }
    
    
    
    
}
