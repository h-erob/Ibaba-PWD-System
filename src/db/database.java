package db;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class database {
    private static final String CONFIG_FILE = "db_config.properties";
    private static String DB_NAME;
    private static String URL_WITHOUT_DB;
    private static String URL_WITH_DB;
    private static String USERNAME;
    private static String PASSWORD;
    public static boolean LOAD_MOCK_DATA;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Properties props = new Properties();
            File configFile = new File(CONFIG_FILE);
            if (!configFile.exists()) {
                throw new IOException("Configuration file not found: " + CONFIG_FILE);
            }
            try (FileInputStream in = new FileInputStream(configFile)) {
                props.load(in);
            }

            DB_NAME = props.getProperty("db.name", "members_system");
            USERNAME = props.getProperty("db.username", "root");
            PASSWORD = props.getProperty("db.password", "1234");
            URL_WITHOUT_DB = props.getProperty("db.url_without_db", "jdbc:mysql://localhost:3306/");
            URL_WITH_DB = URL_WITHOUT_DB + DB_NAME;
            LOAD_MOCK_DATA = Boolean.parseBoolean(props.getProperty("load_mock_data", "true"));

            try (Connection conn = DriverManager.getConnection(URL_WITHOUT_DB, USERNAME, PASSWORD);
                 Statement stmt = conn.createStatement()) {
                stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS " + DB_NAME);
            }

        } catch (IOException e) {
            throw new RuntimeException("Could not read config file: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize database: " + e.getMessage(), e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL_WITH_DB, USERNAME, PASSWORD);
    }
}