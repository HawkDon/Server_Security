/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import connectioninfo.ConnectionCredentials;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Mathias BJ
 */
public class DB {

    private Connection con;
    private static DB instance;
    private static PreparedStatement stmt;
    private static String driver = "com.mysql.jdbc.Driver";
    private static String URL = ConnectionCredentials.getURL();
    private static String id = ConnectionCredentials.getId();
    private static String pw = ConnectionCredentials.getPw();

    public Connection getConnection() {
        Connection con = null;
        try {
            Class.forName(driver).newInstance();
            con = DriverManager.getConnection(URL, id, pw);  // The connection will be released upon program
        } catch (Exception e) {
            System.out.println("\n*** Remember to insert your  ID and PW in the DBConnector class! ***\n");
            System.out.println("error in DBConnector.getConnection()");
            System.out.println(e);
        }
        return con;
    }

    public static void closeConnection(Connection con) {
        try {
            con.close();
        } catch (SQLException e) {
            System.out.println("Something happened when you tried to close the connection");
            System.out.println(e.getMessage());
        }
    }
}
