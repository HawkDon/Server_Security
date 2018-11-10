package datamappers;

import entities.DB;
import entities.ErrorMessage;
import entities.User;

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
            switch (table) {
                case "users":
                    ResultSet rs = st.executeQuery(query);
                    while (rs.next()){
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

                default:
                    con.setAutoCommit(false);
                    st.executeQuery(query);
                    break;
            }
        } catch(SQLException e) {
            System.out.println("Something went wrong in the datamapper - Query");
            System.out.println(e.getMessage());
        }
        return al;
    }
}
