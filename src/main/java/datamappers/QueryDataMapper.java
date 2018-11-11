package datamappers;

import entities.*;
import logic.Restore;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class QueryDataMapper {

    public static List getListOfItems(String query, String table) {

        Connection con = new DB().getConnection();
        List al = new ArrayList();
        try {
            con.setAutoCommit(false);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            switch (table) {
                case "users":
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
                    break;
                case "animals":
                    while(rs.next()) {
                        Animal animal = new Animal();
                        animal.setId(rs.getInt("id"));
                        animal.setGender(rs.getString("gender"));
                        animal.setLocation(rs.getString("location"));
                        animal.setName(rs.getString("name"));
                        animal.setType(rs.getString("type"));
                        al.add(animal);
                    }
                    break;
                case "cars":
                    while(rs.next()) {
                        Car car = new Car();
                        car.setId(rs.getInt("id"));
                        car.setMake(rs.getString("make"));
                        car.setModel(rs.getString("model"));
                        car.setYear(rs.getString("year"));
                        al.add(car);
                    }
                case "plants":
                    while(rs.next()) {
                        Plant plant = new Plant();
                        plant.setId(rs.getInt("id"));
                        plant.setFamily(rs.getString("family"));
                        plant.setLocation(rs.getString("location"));
                        plant.setName(rs.getString("name"));
                        al.add(plant);
                    }
                case "movies":
                    while(rs.next()) {
                        Movie movie = new Movie();
                        movie.setId(rs.getInt("id"));
                        movie.setGenre(rs.getString("genre"));
                        movie.setPublished(rs.getString("published"));
                        movie.setTitle(rs.getString("title"));
                    }
                default:
                    System.out.println("Query went through");
                    break;
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong in the datamapper - Query");
            System.out.println(e.getMessage());
        }
        return al;
    }
}
