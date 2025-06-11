package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class database {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/members_system";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "123";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Load the JDBC driver
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL JDBC driver not found", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}