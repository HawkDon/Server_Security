/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author Mathias BJ
 */
public class UserFacade {

    private Connection con;

    public UserFacade() {
        con = new DB().getConnection();
    }

    public void addUser(User user) {
        PreparedStatement newUser = null;
        String SQLString = "insert into users (username, firstname, lastname, email, password) values (?,?,?,?,?)";
        try {
            con.setAutoCommit(false);
            newUser = con.prepareStatement(SQLString);
            newUser.setString(1, user.getUserName());
            newUser.setString(2, user.getFirstName());
            newUser.setString(3, user.getLastName());
            newUser.setString(4, user.getEmail());
            newUser.setString(5, BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(10)));
            newUser.executeUpdate();

            con.setAutoCommit(true);
        } catch (SQLException e) {
            System.out.println("Fail in DataMapper - addUser");
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        User user = new User("mjaay", "Mathias", "Jensenius", "mjaay@gmail.com", "test123");
        UserFacade uf = new UserFacade();
        uf.addUser(user);
    }
}
