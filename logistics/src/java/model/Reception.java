/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author QuangDuy
 */
public class Reception {
    private int reId;
    private String reProjectCode;
    private String reCode;
    private String reCreateAt;
    private String reUpdateAt;
    private int reUserUpdateID;
    private String reStatus;
    private String reNote;
    private ArrayList<ItemObject> itemObjs;

    public Reception() {
        this.itemObjs = new ArrayList<>();
    }

    public int getReId() {
        return reId;
    }

    public void setReId(int reId) {
        this.reId = reId;
    }

    public String getReProjectCode() {
        return reProjectCode;
    }

    public void setReProjectCode(String reProjectCode) {
        this.reProjectCode = reProjectCode;
    }

    public String getReCode() {
        return reCode;
    }

    public void setReCode(String reCode) {
        this.reCode = reCode;
    }

    public String getReCreateAt() {
        return reCreateAt;
    }

    public void setReCreateAt(String reCreateAt) {
        this.reCreateAt = reCreateAt;
    }

    public String getReUpdateAt() {
        return reUpdateAt;
    }

    public void setReUpdateAt(String reUpdateAt) {
        this.reUpdateAt = reUpdateAt;
    }

    public int getReUserUpdateID() {
        return reUserUpdateID;
    }

    public void setReUserUpdateID(int reUserUpdateID) {
        this.reUserUpdateID = reUserUpdateID;
    }

    public String getReStatus() {
        return reStatus;
    }

    public void setReStatus(String reStatus) {
        this.reStatus = reStatus;
    }

    public String getReNote() {
        return reNote;
    }

    public void setReNote(String reNote) {
        this.reNote = reNote;
    }

    public ArrayList<ItemObject> getItemObjs() {
        return itemObjs;
    }

    public void setItemObjs(ArrayList<ItemObject> itemObjs) {
        this.itemObjs = itemObjs;
    }
    
    
    
    
}
