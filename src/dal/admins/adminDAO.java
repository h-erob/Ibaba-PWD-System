package dal.admins;

import db.database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class adminDAO {

    public boolean checkIfAdminExists(String username, String password) {
        try (Connection connection = database.getConnection()) {
            // Use the correct table name: 'admins'
            String sql = "SELECT username, password FROM admins WHERE username = ? AND password = ?";
            PreparedStatement st = connection.prepareStatement(sql);

            st.setString(1, username);
            st.setString(2, password);

            ResultSet rs = st.executeQuery();
            return rs.next(); // True if admin found

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return false;
    }
}
