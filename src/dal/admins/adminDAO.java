package dal.admins;

import db.database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class adminDAO {

    public boolean checkIfAdminExists(String username, String password) {
        try (Connection connection = database.getConnection()) {
            String sql = "SELECT username, password FROM admins WHERE username = ? AND password = ?";
            PreparedStatement st = connection.prepareStatement(sql);

            // Trim inputs to avoid whitespace issues
            String trimmedUsername = username != null ? username.trim() : "";
            String trimmedPassword = password != null ? password.trim() : "";

            st.setString(1, trimmedUsername);
            st.setString(2, trimmedPassword);

            // Debug logging
            System.out.println("Executing query with username: " + trimmedUsername);
            System.out.println("Executing query with password: " + trimmedPassword);

            ResultSet rs = st.executeQuery();
            boolean exists = rs.next();
            System.out.println("Admin exists: " + exists);
            return exists;

        } catch (SQLException sqlException) {
            System.err.println("SQL Exception in checkIfAdminExists: " + sqlException.getMessage());
            sqlException.printStackTrace();
            return false;
        }
    }

    public boolean updatePassword(String username, String newPassword) {
        try (Connection connection = database.getConnection()) {
            String sql = "UPDATE admins SET password = ? WHERE username = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, newPassword.trim());
            st.setString(2, username.trim());

            int rowsAffected = st.executeUpdate();
            System.out.println("Password update rows affected: " + rowsAffected);
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("SQL Exception in updatePassword: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}