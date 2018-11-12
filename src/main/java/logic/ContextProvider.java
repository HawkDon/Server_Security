package logic;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.IOException;
import java.sql.SQLException;

@WebListener
public class ContextProvider implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("Starting up!");
        System.out.println("Get DB backup!");
        try {
            Restore.backupDB();
        } catch (ClassNotFoundException e) {
            System.out.println("Something went wrong upon startup!");
            System.out.println(e.getMessage());
        } catch (SQLException e) {
            System.out.println("Something went wrong upon startup!");
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println("Something went wrong upon startup!");
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("Shutting down!");
    }
}
