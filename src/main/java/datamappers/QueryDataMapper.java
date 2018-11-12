package datamappers;

import entities.*;
import logic.QueryFacade;
import logic.Restore;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class QueryDataMapper {

    public static List getListOfItems(String query) {

        Connection con = new DB().getConnection();
        List al = new ArrayList();
        String queryLowercased = query.toLowerCase();
        try {
            con.setAutoCommit(false);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(queryLowercased);
            System.out.println(query.toLowerCase());
            if(queryLowercased.contains("users")) {
                while (rs.next()) {
                    User user = new User();
                    user.setUserName(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                    user.setId(rs.getInt("id"));
                    user.setEmail(rs.getString("email"));
                    user.setFirstName(rs.getString("firstname"));
                    user.setLastName(rs.getString("lastname"));
                    al.add(user);
                }
            } else if(queryLowercased.contains("animals")) {
                while(rs.next()) {
                    Animal animal = new Animal();
                    animal.setId(rs.getInt("id"));
                    animal.setGender(rs.getString("gender"));
                    animal.setLocation(rs.getString("location"));
                    animal.setName(rs.getString("name"));
                    animal.setType(rs.getString("type"));
                    al.add(animal);
                }
            } else if(queryLowercased.contains("cars")) {
                while(rs.next()) {
                    Car car = new Car();
                    car.setId(rs.getInt("id"));
                    car.setMake(rs.getString("make"));
                    car.setModel(rs.getString("model"));
                    car.setYear(rs.getString("year"));
                    al.add(car);
                }
            } else if(queryLowercased.contains("plants")) {
                while(rs.next()) {
                    Plant plant = new Plant();
                    plant.setId(rs.getInt("id"));
                    plant.setFamily(rs.getString("family"));
                    plant.setLocation(rs.getString("location"));
                    plant.setName(rs.getString("name"));
                    al.add(plant);
                }
            } else if(queryLowercased.contains("movies")) {
                while(rs.next()) {
                    Movie movie = new Movie();
                    movie.setId(rs.getInt("id"));
                    movie.setGenre(rs.getString("genre"));
                    movie.setPublished(rs.getString("published"));
                    movie.setTitle(rs.getString("title"));
                    al.add(movie);
                }
            } else {
                System.out.println("Query went through. No errors happened.");
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong in the datamapper - Query");
            System.out.println(e.getMessage());
        }
        return al;
    }

    public static List notSafeSelectStatement(String userId) {
        Connection con = new DB().getConnection();
        List al = new ArrayList();
        String sqlStatement = "SELECT * FROM users WHERE id=" + userId;

        try {
            con.setAutoCommit(false);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sqlStatement);

            while(rs.next()) {
                User user = new User();
                user.setPassword(rs.getString("password"));
                user.setId(rs.getInt("id"));
                user.setEmail(rs.getString("email"));
                user.setFirstName(rs.getString("firstname"));
                user.setLastName(rs.getString("lastname"));
                al.add(user);
            }
        } catch(SQLException e) {
           System.out.println("Something went wrong in the datamapper - Query");
            System.out.println(e.getMessage());
        }
        return al;
    }

    public static List safeSelectStatement(String userId) {
        Connection con = new DB().getConnection();
        List al = new ArrayList();

        try {
            con.setAutoCommit(false);
            PreparedStatement getUser = con.prepareStatement("select * from users where id = ?");
            getUser.setString(1, userId);

            ResultSet rs = getUser.executeQuery();

            while(rs.next()) {
                User user = new User();
                user.setPassword(rs.getString("password"));
                user.setId(rs.getInt("id"));
                user.setEmail(rs.getString("email"));
                user.setFirstName(rs.getString("firstname"));
                user.setLastName(rs.getString("lastname"));
                al.add(user);
            }
        } catch(SQLException e) {
            System.out.println("Something went wrong in the datamapper - Query");
            System.out.println(e.getMessage());
        }
        return al;
    }

    /*public static List notSafeSelectBatchStatement(String id) {
        Connection con = new DB().getConnection();
        List al = new ArrayList();

        try {
            con.setAutoCommit(false);
            Statement stmt = con.createStatement();

            stmt.addBatch("SELECT * FROM users WHERE id=" + id + "; DROP TABLE plants;");

            stmt.executeBatch();

            ResultSet rs = stmt.executeQuery("SELECT * FROM users WHERE id=" + id);

            while(rs.next()) {
                User user = new User();
                user.setPassword(rs.getString("password"));
                user.setId(rs.getInt("id"));
                user.setEmail(rs.getString("email"));
                user.setFirstName(rs.getString("firstname"));
                user.setLastName(rs.getString("lastname"));
                al.add(user);
            }
        } catch(SQLException e) {
            System.out.println("Something went wrong in the datamapper - Query");
            System.out.println(e.getMessage());
        }
        return al;
    }*/

    /*public static List safeSelectBatchStatement(String userId) {
        Connection con = new DB().getConnection();
        List al = new ArrayList();

        try {
            con.setAutoCommit(false);
            PreparedStatement getUser = con.prepareStatement("select * from users where id = ?");
            getUser.setString(1, userId);

            ResultSet rs = getUser.executeQuery();

            while(rs.next()) {
                User user = new User();
                user.setPassword(rs.getString("password"));
                user.setId(rs.getInt("id"));
                user.setEmail(rs.getString("email"));
                user.setFirstName(rs.getString("firstname"));
                user.setLastName(rs.getString("lastname"));
                al.add(user);
            }
        } catch(SQLException e) {
            System.out.println("Something went wrong in the datamapper - Query");
            System.out.println(e.getMessage());
        }
        return al;
    } */

}
