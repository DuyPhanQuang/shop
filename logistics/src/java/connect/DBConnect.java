/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connect;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author QuangDuy
 */
public class DBConnect {

    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://us-cdbr-iron-east-04.cleardb.net:3306/heroku_18248a0589a6093", "bccff152863df3", "e273fa0c");
        } catch (Exception e) {
            e.printStackTrace();

        }
        return conn;
    }
    public static void main(String[] args) {
        System.out.println(getConnection());
    }
}
