package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class databaseInitializer {
    public static void initialize() {
        try (Connection conn = database.getConnection();
             Statement stmt = conn.createStatement()) {

            // Tables
            stmt.executeUpdate("""
                CREATE TABLE IF NOT EXISTS admins (
                    id INT NOT NULL AUTO_INCREMENT,
                    username VARCHAR(250) NOT NULL,
                    password VARCHAR(250) NOT NULL,
                    PRIMARY KEY (id)
                );
            """);

            try (PreparedStatement check = conn.prepareStatement(
                    "SELECT * FROM admins WHERE username = ?")) {
                check.setString(1, "admin");
                ResultSet rs = check.executeQuery();

                if (!rs.next()) {
                    try (PreparedStatement insert = conn.prepareStatement(
                            "INSERT INTO admins (username, password) VALUES (?, ?)")) {
                        insert.setString(1, "admin");
                        insert.setString(2, "1234");
                        insert.executeUpdate();
                    }
                }
            }

            stmt.executeUpdate("""
                CREATE TABLE IF NOT EXISTS members (
                    member_id INT NOT NULL AUTO_INCREMENT,
                    full_name VARCHAR(100),
                    pwd_id_number VARCHAR(50),
                    disability_type VARCHAR(100),
                    fill_up_date DATE,
                    date_issued DATE,
                    id_valid_until DATE,
                    birthdate DATE,
                    age INT,
                    sex ENUM('Male','Female','Other'),
                    civil_status VARCHAR(50),
                    place_of_birth VARCHAR(100),
                    education_level VARCHAR(100),
                    occupation VARCHAR(100),
                    address TEXT,
                    mobile_number VARCHAR(20),
                    email VARCHAR(100),
                    fb_account_name VARCHAR(100),
                    guardian_name VARCHAR(100),
                    guardian_relation VARCHAR(50),
                    guardian_mobile VARCHAR(20),
                    takes_medications TINYINT(1) DEFAULT 0,
                    status ENUM('Alive','Deceased','Renewed','Expired') DEFAULT 'Alive',
                    PRIMARY KEY (member_id)
                );
            """);

            stmt.executeUpdate("""
                CREATE TABLE IF NOT EXISTS members_household (
                    household_id INT NOT NULL AUTO_INCREMENT,
                    member_id INT,
                    name VARCHAR(100),
                    relationship VARCHAR(50),
                    age INT,
                    birthdate DATE,
                    civil_status VARCHAR(50),
                    education_level VARCHAR(100),
                    occupation VARCHAR(100),
                    PRIMARY KEY (household_id),
                    FOREIGN KEY (member_id) REFERENCES members(member_id)
                );
            """);

            stmt.executeUpdate("""
                CREATE TABLE IF NOT EXISTS members_conditions (
                    member_id INT NOT NULL,
                    diabetes TINYINT(1) DEFAULT 0,
                    stroke TINYINT(1) DEFAULT 0,
                    heart_problems TINYINT(1) DEFAULT 0,
                    cancer TINYINT(1) DEFAULT 0,
                    high_blood TINYINT(1) DEFAULT 0,
                    lung_problems TINYINT(1) DEFAULT 0,
                    arthritis TINYINT(1) DEFAULT 0,
                    osteoporosis TINYINT(1) DEFAULT 0,
                    epilepsy TINYINT(1) DEFAULT 0,
                    kidney_problems TINYINT(1) DEFAULT 0,
                    other_conditions TEXT,
                    PRIMARY KEY (member_id),
                    FOREIGN KEY (member_id) REFERENCES members(member_id)
                );
            """);

            stmt.executeUpdate("""
                CREATE TABLE IF NOT EXISTS members_medications (
                    med_id INT NOT NULL AUTO_INCREMENT,
                    member_id INT,
                    medication_name VARCHAR(100),
                    PRIMARY KEY (med_id),
                    FOREIGN KEY (member_id) REFERENCES members(member_id)
                );
            """);

            stmt.executeUpdate("""
                CREATE TABLE IF NOT EXISTS attendance (
                    attendance_id INT NOT NULL AUTO_INCREMENT,
                    member_id INT,
                    attendance_date DATE,
                    status ENUM('present','absent','excused') NOT NULL,
                    PRIMARY KEY (attendance_id),
                    FOREIGN KEY (member_id) REFERENCES members(member_id)
                );
            """);

            stmt.executeUpdate("""
                CREATE OR REPLACE VIEW yearly_attendance AS
                SELECT member_id, YEAR(attendance_date) AS year, COUNT(*) AS total_present
                FROM attendance
                WHERE status = 'present'
                GROUP BY member_id, YEAR(attendance_date);
            """);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}