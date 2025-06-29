package db;

import java.sql.*;

public class database {
    private static final String DB_NAME = "members_system";
    private static final String URL_WITHOUT_DB = "jdbc:mysql://localhost:3306/";
    private static final String URL_WITH_DB = "jdbc:mysql://localhost:3306/" + DB_NAME;
    private static final String USERNAME = "root";
    private static final String PASSWORD = "1234"; // <-- change this if needed

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection conn = DriverManager.getConnection(URL_WITHOUT_DB, USERNAME, PASSWORD);
                 Statement stmt = conn.createStatement()) {
                // Creates a database if it doesn't exist
                stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS " + DB_NAME);
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("⚠️ Failed to initialize database.");
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL_WITH_DB, USERNAME, PASSWORD);
    }
}