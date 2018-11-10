/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamappers;

import java.sql.*;

import com.google.gson.Gson;
import entities.DB;
import entities.User;
import logic.BCryptValidation;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author Mathias BJ
 */
public class UserDataMapper {

    public static String addUser(User user) {

        Gson gson = new Gson();

        Connection con = new DB().getConnection();

        String result;
        try {
            con.setAutoCommit(false);
            PreparedStatement newUser = con.prepareStatement("insert into users (username, firstname, lastname, email, password) values (?,?,?,?,?)");
            newUser.setString(1, user.getUserName());
            newUser.setString(2, user.getFirstName());
            newUser.setString(3, user.getLastName());
            newUser.setString(4, user.getEmail());
            newUser.setString(5, BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(10)));
            newUser.executeUpdate();

            con.setAutoCommit(true);
            result = gson.toJson("User has been registered!");
        } catch (SQLException e) {
            System.out.println("Failed in datamapper - user");
            System.out.println(e.getMessage());
            result = gson.toJson("User did not register. Please try again.");
        } finally {
            DB.closeConnection(con);
        }
        return result;
    }


    public static boolean loginUser(User user) {

        boolean isIdentical = false;

        Connection con = new DB().getConnection();

        try {
            con.setAutoCommit(false);
            PreparedStatement getUser = con.prepareStatement("select password from users where username = ?");
            getUser.setString(1, user.getUserName());

            ResultSet rs = getUser.executeQuery();

            String password = "";

            while(rs.next()) {
                password = rs.getString("password");
            }

            isIdentical = BCryptValidation.validate(user.getPassword(), password);

        } catch (SQLException e) {
            System.out.println("Failed in datamapper - loginUser");
            e.getMessage();
        }

        return isIdentical;
    }

    public static User getUserCredentials(User user) {

        User newUser = new User();

        Connection con = new DB().getConnection();

        try {
            con.setAutoCommit(false);
            PreparedStatement getUser = con.prepareStatement("select id, username from users where username = ?");
            getUser.setString(1, user.getUserName());

            ResultSet rs = getUser.executeQuery();

            while(rs.next()) {
                newUser.setId(rs.getInt("id"));
                newUser.setUserName(rs.getString("username"));
            }

        } catch (SQLException e) {
            System.out.println("Failed in datamapper - getUserCredentials");
            e.getMessage();
        } finally {
            DB.closeConnection(con);
        }

        return newUser;
    }
}
