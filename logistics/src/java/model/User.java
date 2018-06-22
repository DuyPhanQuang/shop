
package model;

/**
 *
 * @author QuangDuy
 */
public class User {
    
    public int userID;
    public String userName;
    public String userAcc;
    public String userPassword;
    public String userPC;
    public int userRole;

    public User() {
    }

    public User(int userID, String userPC, int userRole) {
        this.userID = userID;
        this.userPC = userPC;
        this.userRole = userRole;
    }
    public User(int userID, String userName, String userAcc, String userPassword, int userRole, String userPC) {
        this.userID = userID;
        this.userAcc = userAcc;
        this.userName = userName;
        this.userPC = userPC;
        this.userPassword = userPassword;
        this.userRole = userRole;
    }
}