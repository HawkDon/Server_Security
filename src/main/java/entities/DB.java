/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 *
 * @author Mathias BJ
 */
public class DB {
      private Connection con;
    private static DB instance;
    private static PreparedStatement stmt;
    private static String driver = "com.mysql.jdbc.Driver";
    private static String URL = "jdbc:mysql://localhost:3306/security";
    private static String id = "root";
    private static String pw = "m4th145bj";

    public Connection getConnection() {
        Connection con = null;
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(URL, id, pw);  // The connection will be released upon program 

        } catch (Exception e) {
            System.out.println("\n*** Remember to insert your  ID and PW in the DBConnector class! ***\n");
            System.out.println("error in DBConnector.getConnection()");
            System.out.println(e);
        }

        return con;
    }
}
