/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

/**
 *
 * @author QuangDuy
 */
public class Reception {
    private int ID;
    private String RECEPTION_CODE;
    private Date DATE_TIME;
    private int USER_ID;
    private String NOTE;

    public Reception() {
    }

    public Reception(int ID, String RECEPTION_CODE, Date DATE_TIME, int USER_ID, String NOTE) {
        this.ID = ID;
        this.RECEPTION_CODE = RECEPTION_CODE;
        this.DATE_TIME = DATE_TIME;
        this.USER_ID = USER_ID;
        this.NOTE = NOTE;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getRECEPTION_CODE() {
        return RECEPTION_CODE;
    }

    public void setRECEPTION_CODE(String RECEPTION_CODE) {
        this.RECEPTION_CODE = RECEPTION_CODE;
    }

    public Date getDATE_TIME() {
        return DATE_TIME;
    }

    public void setDATE_TIME(Date DATE_TIME) {
        this.DATE_TIME = DATE_TIME;
    }

    public int getUSER_ID() {
        return USER_ID;
    }

    public void setUSER_ID(int USER_ID) {
        this.USER_ID = USER_ID;
    }

    public String getNOTE() {
        return NOTE;
    }

    public void setNOTE(String NOTE) {
        this.NOTE = NOTE;
    }
    
}
