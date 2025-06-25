package dal.members;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class membersDAO {
    private Connection connection;

    public membersDAO(Connection connection) {
        this.connection = connection;
    }

    public boolean addMember(
            String fullName, String pwdIdNumber, String disabilityType, Date dateIssued, Date idValidUntil,
            Date birthdate, int age, String sex, String civilStatus, String placeOfBirth, String educationLevel,
            String occupation, String address, String mobileNumber, String email, String fbAccountName,
            String guardianName, String guardianRelation, String guardianMobile, boolean takesMedications,
            List<Map<String, Object>> householdMembers, Map<String, Boolean> medicalConditions, String otherConditions,
            List<String> medications) throws SQLException {
        boolean success = false;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            connection.setAutoCommit(false);

            String memberSql = "INSERT INTO members (full_name, pwd_id_number, disability_type, date_issued, id_valid_until, " +
                    "birthdate, age, sex, civil_status, place_of_birth, education_level, occupation, address, mobile_number, " +
                    "email, fb_account_name, guardian_name, guardian_relation, guardian_mobile, takes_medications, status) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 'Alive')";
            pstmt = connection.prepareStatement(memberSql, PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, fullName);
            pstmt.setString(2, pwdIdNumber);
            pstmt.setString(3, disabilityType);
            pstmt.setDate(4, dateIssued);
            pstmt.setDate(5, idValidUntil);
            pstmt.setDate(6, birthdate);
            pstmt.setInt(7, age);
            pstmt.setString(8, sex);
            pstmt.setString(9, civilStatus);
            pstmt.setString(10, placeOfBirth);
            pstmt.setString(11, educationLevel);
            pstmt.setString(12, occupation);
            pstmt.setString(13, address);
            pstmt.setString(14, mobileNumber);
            pstmt.setString(15, email != null ? email : "");
            pstmt.setString(16, fbAccountName != null ? fbAccountName : "");
            pstmt.setString(17, guardianName);
            pstmt.setString(18, guardianRelation);
            pstmt.setString(19, guardianMobile);
            pstmt.setBoolean(20, takesMedications);
            pstmt.executeUpdate();

            rs = pstmt.getGeneratedKeys();
            int memberId;
            if (rs.next()) {
                memberId = rs.getInt(1);
            } else {
                throw new SQLException("Failed to retrieve generated member_id.");
            }
            rs.close();
            pstmt.close();

            if (householdMembers != null && !householdMembers.isEmpty()) {
                String householdSql = "INSERT INTO members_household (member_id, name, relationship, age, birthdate, " +
                        "civil_status, education_level, occupation) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                pstmt = connection.prepareStatement(householdSql);
                for (Map<String, Object> household : householdMembers) {
                    pstmt.setInt(1, memberId);
                    pstmt.setString(2, (String) household.get("name"));
                    pstmt.setString(3, (String) household.get("relationship"));
                    Object ageObj = household.get("age");
                    if (ageObj != null && !((String) ageObj).isEmpty()) {
                        pstmt.setInt(4, Integer.parseInt((String) ageObj));
                    } else {
                        pstmt.setNull(4, java.sql.Types.INTEGER);
                    }
                    Object birthdateObj = household.get("birthdate");
                    if (birthdateObj != null && !((String) birthdateObj).isEmpty()) {
                        pstmt.setDate(5, Date.valueOf((String) birthdateObj));
                    } else {
                        pstmt.setNull(5, java.sql.Types.DATE);
                    }
                    pstmt.setString(6, (String) household.get("civilStatus"));
                    pstmt.setString(7, (String) household.get("education"));
                    pstmt.setString(8, (String) household.get("occupation"));
                    pstmt.executeUpdate();
                }
                pstmt.close();
            }

            String conditionsSql = "INSERT INTO members_conditions (member_id, diabetes, stroke, heart_problems, cancer, " +
                    "high_blood, lung_problems, arthritis, osteoporosis, epilepsy, kidney_problems, other_conditions) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            pstmt = connection.prepareStatement(conditionsSql);
            pstmt.setInt(1, memberId);
            pstmt.setBoolean(2, medicalConditions.getOrDefault("diabetes", false));
            pstmt.setBoolean(3, medicalConditions.getOrDefault("stroke", false));
            pstmt.setBoolean(4, medicalConditions.getOrDefault("heart_problems", false));
            pstmt.setBoolean(5, medicalConditions.getOrDefault("cancer", false));
            pstmt.setBoolean(6, medicalConditions.getOrDefault("high_blood", false));
            pstmt.setBoolean(7, medicalConditions.getOrDefault("lung_problems", false));
            pstmt.setBoolean(8, medicalConditions.getOrDefault("arthritis", false));
            pstmt.setBoolean(9, medicalConditions.getOrDefault("osteoporosis", false));
            pstmt.setBoolean(10, medicalConditions.getOrDefault("epilepsy", false));
            pstmt.setBoolean(11, medicalConditions.getOrDefault("kidney_problems", false));
            pstmt.setString(12, otherConditions != null ? otherConditions : "");
            pstmt.executeUpdate();
            pstmt.close();

            if (medications != null && !medications.isEmpty()) {
                String medicationsSql = "INSERT INTO members_medications (member_id, medication_name) VALUES (?, ?)";
                pstmt = connection.prepareStatement(medicationsSql);
                for (String medication : medications) {
                    if (!medication.trim().isEmpty()) {
                        pstmt.setInt(1, memberId);
                        pstmt.setString(2, medication);
                        pstmt.executeUpdate();
                    }
                }
            }

            connection.commit();
            success = true;
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
            throw e;
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (rs != null) try { rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            if (pstmt != null) try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
        return success;
    }

    public List<MemberData> getMembers() throws SQLException {
        List<MemberData> members = new ArrayList<>();
        PreparedStatement pstmtMembers = null;
        PreparedStatement pstmtHousehold = null;
        PreparedStatement pstmtConditions = null;
        PreparedStatement pstmtMedications = null;
        ResultSet rsMembers = null;
        ResultSet rsHousehold = null;
        ResultSet rsConditions = null;
        ResultSet rsMedications = null;

        try {
            String memberSql = "SELECT * FROM members";
            pstmtMembers = connection.prepareStatement(memberSql);
            rsMembers = pstmtMembers.executeQuery();

            while (rsMembers.next()) {
                MemberData member = new MemberData();
                member.memberId = rsMembers.getInt("member_id");
                member.fullName = rsMembers.getString("full_name");
                member.pwdIdNumber = rsMembers.getString("pwd_id_number");
                member.disabilityType = rsMembers.getString("disability_type");
                member.dateIssued = rsMembers.getDate("date_issued");
                member.idValidUntil = rsMembers.getDate("id_valid_until");
                member.birthdate = rsMembers.getDate("birthdate");
                member.age = rsMembers.getInt("age");
                member.sex = rsMembers.getString("sex");
                member.civilStatus = rsMembers.getString("civil_status");
                member.placeOfBirth = rsMembers.getString("place_of_birth");
                member.educationLevel = rsMembers.getString("education_level");
                member.occupation = rsMembers.getString("occupation");
                member.address = rsMembers.getString("address");
                member.mobileNumber = rsMembers.getString("mobile_number");
                member.email = rsMembers.getString("email");
                member.fbAccountName = rsMembers.getString("fb_account_name");
                member.guardianName = rsMembers.getString("guardian_name");
                member.guardianRelation = rsMembers.getString("guardian_relation");
                member.guardianMobile = rsMembers.getString("guardian_mobile");
                member.takesMedications = rsMembers.getBoolean("takes_medications");
                member.status = rsMembers.getString("status");

                String householdSql = "SELECT * FROM members_household WHERE member_id = ?";
                pstmtHousehold = connection.prepareStatement(householdSql);
                pstmtHousehold.setInt(1, member.memberId);
                rsHousehold = pstmtHousehold.executeQuery();
                member.householdMembers = new ArrayList<>();
                while (rsHousehold.next()) {
                    Map<String, Object> household = new HashMap<>();
                    household.put("name", rsHousehold.getString("name"));
                    household.put("relationship", rsHousehold.getString("relationship"));
                    household.put("age", rsHousehold.getInt("age"));
                    household.put("birthdate", rsHousehold.getDate("birthdate"));
                    household.put("civilStatus", rsHousehold.getString("civil_status"));
                    household.put("education", rsHousehold.getString("education_level"));
                    household.put("occupation", rsHousehold.getString("occupation"));
                    member.householdMembers.add(household);
                }
                rsHousehold.close();
                pstmtHousehold.close();

                String conditionsSql = "SELECT * FROM members_conditions WHERE member_id = ?";
                pstmtConditions = connection.prepareStatement(conditionsSql);
                pstmtConditions.setInt(1, member.memberId);
                rsConditions = pstmtConditions.executeQuery();
                member.medicalConditions = new HashMap<>();
                if (rsConditions.next()) {
                    member.medicalConditions.put("diabetes", rsConditions.getBoolean("diabetes"));
                    member.medicalConditions.put("stroke", rsConditions.getBoolean("stroke"));
                    member.medicalConditions.put("heart_problems", rsConditions.getBoolean("heart_problems"));
                    member.medicalConditions.put("cancer", rsConditions.getBoolean("cancer"));
                    member.medicalConditions.put("high_blood", rsConditions.getBoolean("high_blood"));
                    member.medicalConditions.put("lung_problems", rsConditions.getBoolean("lung_problems"));
                    member.medicalConditions.put("arthritis", rsConditions.getBoolean("arthritis"));
                    member.medicalConditions.put("osteoporosis", rsConditions.getBoolean("osteoporosis"));
                    member.medicalConditions.put("epilepsy", rsConditions.getBoolean("epilepsy"));
                    member.medicalConditions.put("kidney_problems", rsConditions.getBoolean("kidney_problems"));
                    member.otherConditions = rsConditions.getString("other_conditions");
                }
                rsConditions.close();
                pstmtConditions.close();

                String medicationsSql = "SELECT * FROM members_medications WHERE member_id = ?";
                pstmtMedications = connection.prepareStatement(medicationsSql);
                pstmtMedications.setInt(1, member.memberId);
                rsMedications = pstmtMedications.executeQuery();
                member.medications = new ArrayList<>();
                while (rsMedications.next()) {
                    member.medications.add(rsMedications.getString("medication_name"));
                }
                rsMedications.close();
                pstmtMedications.close();

                members.add(member);
            }
        } finally {
            if (rsMembers != null) try { rsMembers.close(); } catch (SQLException e) { e.printStackTrace(); }
            if (rsHousehold != null) try { rsHousehold.close(); } catch (SQLException e) { e.printStackTrace(); }
            if (rsConditions != null) try { rsConditions.close(); } catch (SQLException e) { e.printStackTrace(); }
            if (rsMedications != null) try { rsMedications.close(); } catch (SQLException e) { e.printStackTrace(); }
            if (pstmtMembers != null) try { pstmtMembers.close(); } catch (SQLException e) { e.printStackTrace(); }
            if (pstmtHousehold != null) try { pstmtHousehold.close(); } catch (SQLException e) { e.printStackTrace(); }
            if (pstmtConditions != null) try { pstmtConditions.close(); } catch (SQLException e) { e.printStackTrace(); }
            if (pstmtMedications != null) try { pstmtMedications.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
        return members;
    }

    public static class AttendanceEntry {
        public int memberId;
        public String status;

        public AttendanceEntry(int memberId, String status) {
            this.memberId = memberId;
            this.status = status;
        }
    }

    public boolean updateMember(
            int memberId, String fullName, String pwdIdNumber, String disabilityType, String status,
            Date dateIssued, Date idValidUntil, Date birthdate, int age, String sex, String civilStatus,
            String placeOfBirth, String educationLevel, String occupation, String address, String mobileNumber,
            String email, String fbAccountName, String guardianName, String guardianRelation, String guardianMobile,
            boolean takesMedications, List<Map<String, Object>> householdMembers, Map<String, Boolean> medicalConditions,
            String otherConditions, List<String> medications) throws SQLException {
        boolean success = false;
        PreparedStatement pstmt = null;

        try {
            connection.setAutoCommit(false);

            String memberSql = "UPDATE members SET full_name = ?, pwd_id_number = ?, disability_type = ?, status = ?, " +
                    "birthdate = ?, age = ?, sex = ?, civil_status = ?, place_of_birth = ?, education_level = ?, " +
                    "occupation = ?, address = ?, mobile_number = ?, email = ?, fb_account_name = ?, guardian_name = ?, " +
                    "guardian_relation = ?, guardian_mobile = ?, takes_medications = ? WHERE member_id = ?";
            pstmt = connection.prepareStatement(memberSql);
            pstmt.setString(1, fullName);
            pstmt.setString(2, pwdIdNumber);
            pstmt.setString(3, disabilityType);
            pstmt.setString(4, status);
            pstmt.setDate(5, birthdate);
            pstmt.setInt(6, age);
            pstmt.setString(7, sex);
            pstmt.setString(8, civilStatus);
            pstmt.setString(9, placeOfBirth);
            pstmt.setString(10, educationLevel);
            pstmt.setString(11, occupation);
            pstmt.setString(12, address);
            pstmt.setString(13, mobileNumber);
            pstmt.setString(14, email != null ? email : "");
            pstmt.setString(15, fbAccountName != null ? fbAccountName : "");
            pstmt.setString(16, guardianName);
            pstmt.setString(17, guardianRelation);
            pstmt.setString(18, guardianMobile);
            pstmt.setBoolean(19, takesMedications);
            pstmt.setInt(20, memberId);
            pstmt.executeUpdate();
            pstmt.close();

            String deleteHouseholdSql = "DELETE FROM members_household WHERE member_id = ?";
            pstmt = connection.prepareStatement(deleteHouseholdSql);
            pstmt.setInt(1, memberId);
            pstmt.executeUpdate();
            pstmt.close();

            if (householdMembers != null && !householdMembers.isEmpty()) {
                String householdSql = "INSERT INTO members_household (member_id, name, relationship, age, birthdate, " +
                        "civil_status, education_level, occupation) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                pstmt = connection.prepareStatement(householdSql);
                for (Map<String, Object> household : householdMembers) {
                    pstmt.setInt(1, memberId);
                    pstmt.setString(2, (String) household.get("name"));
                    pstmt.setString(3, (String) household.get("relationship"));
                    Object ageObj = household.get("age");
                    if (ageObj != null && !((String) ageObj).isEmpty()) {
                        pstmt.setInt(4, Integer.parseInt((String) ageObj));
                    } else {
                        pstmt.setNull(4, java.sql.Types.INTEGER);
                    }
                    Object birthdateObj = household.get("birthdate");
                    if (birthdateObj != null) {
                        pstmt.setDate(5, (java.sql.Date) birthdateObj);
                    } else {
                        pstmt.setNull(5, java.sql.Types.DATE);
                    }
                    pstmt.setString(6, (String) household.get("civilStatus"));
                    pstmt.setString(7, (String) household.get("education"));
                    pstmt.setString(8, (String) household.get("occupation"));
                    pstmt.executeUpdate();
                }
                pstmt.close();
            }

            String deleteConditionsSql = "DELETE FROM members_conditions WHERE member_id = ?";
            pstmt = connection.prepareStatement(deleteConditionsSql);
            pstmt.setInt(1, memberId);
            pstmt.executeUpdate();
            pstmt.close();

            String conditionsSql = "INSERT INTO members_conditions (member_id, diabetes, stroke, heart_problems, cancer, " +
                    "high_blood, lung_problems, arthritis, osteoporosis, epilepsy, kidney_problems, other_conditions) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            pstmt = connection.prepareStatement(conditionsSql);
            pstmt.setInt(1, memberId);
            pstmt.setBoolean(2, medicalConditions.getOrDefault("diabetes", false));
            pstmt.setBoolean(3, medicalConditions.getOrDefault("stroke", false));
            pstmt.setBoolean(4, medicalConditions.getOrDefault("heart_problems", false));
            pstmt.setBoolean(5, medicalConditions.getOrDefault("cancer", false));
            pstmt.setBoolean(6, medicalConditions.getOrDefault("high_blood", false));
            pstmt.setBoolean(7, medicalConditions.getOrDefault("lung_problems", false));
            pstmt.setBoolean(8, medicalConditions.getOrDefault("arthritis", false));
            pstmt.setBoolean(9, medicalConditions.getOrDefault("osteoporosis", false));
            pstmt.setBoolean(10, medicalConditions.getOrDefault("epilepsy", false));
            pstmt.setBoolean(11, medicalConditions.getOrDefault("kidney_problems", false));
            pstmt.setString(12, otherConditions != null ? otherConditions : "");
            pstmt.executeUpdate();
            pstmt.close();

            String deleteMedicationsSql = "DELETE FROM members_medications WHERE member_id = ?";
            pstmt = connection.prepareStatement(deleteMedicationsSql);
            pstmt.setInt(1, memberId);
            pstmt.executeUpdate();
            pstmt.close();

            if (medications != null && !medications.isEmpty()) {
                String medicationsSql = "INSERT INTO members_medications (member_id, medication_name) VALUES (?, ?)";
                pstmt = connection.prepareStatement(medicationsSql);
                for (String medication : medications) {
                    if (!medication.trim().isEmpty()) {
                        pstmt.setInt(1, memberId);
                        pstmt.setString(2, medication);
                        pstmt.executeUpdate();
                    }
                }
                pstmt.close();
            }

            connection.commit();
            success = true;
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
            throw e;
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (pstmt != null) try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
        return success;
    }

    public void addAttendance(java.sql.Date attendanceDate, List<AttendanceEntry> entries) throws SQLException {
        String sql = "INSERT INTO attendance (member_id, attendance_date, status) VALUES (?, ?, ?) " +
                "ON DUPLICATE KEY UPDATE status = VALUES(status)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            for (AttendanceEntry entry : entries) {
                pstmt.setInt(1, entry.memberId);
                pstmt.setDate(2, attendanceDate);
                pstmt.setString(3, entry.status.toLowerCase());
                pstmt.addBatch();
            }
            pstmt.executeBatch();
        }
    }

    public List<String> getAttendanceYears() throws SQLException {
        List<String> years = new ArrayList<>();
        String sql = "SELECT DISTINCT YEAR(attendance_date) AS year FROM attendance ORDER BY year DESC";
        try (PreparedStatement pstmt = connection.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                years.add(rs.getString("year"));
            }
        }
        return years;
    }

    public int getUniqueAttendanceDatesCount(String year) throws SQLException {
        String sql = "SELECT COUNT(DISTINCT attendance_date) AS count FROM attendance WHERE YEAR(attendance_date) = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, Integer.parseInt(year));
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("count");
                }
            }
        }
        return 0;
    }

    public List<AttendanceRecord> getAttendanceRecordsForYear(String year) throws SQLException {
        List<AttendanceRecord> records = new ArrayList<>();
        String sql = "SELECT m.member_id, m.full_name, m.pwd_id_number, m.status, " +
                "MAX(CASE WHEN a.status IN ('present', 'excused') THEN a.attendance_date ELSE NULL END) AS last_attendance_date, " +
                "SUM(CASE WHEN a.status IN ('present', 'excused') THEN 1 ELSE 0 END) AS total_attendance " +
                "FROM members m " +
                "LEFT JOIN attendance a ON m.member_id = a.member_id AND YEAR(a.attendance_date) = ? " +
                "GROUP BY m.member_id, m.full_name, m.pwd_id_number, m.status " +
                "ORDER BY m.full_name";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, Integer.parseInt(year));
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    AttendanceRecord record = new AttendanceRecord();
                    record.memberId = rs.getInt("member_id");
                    record.fullName = rs.getString("full_name");
                    record.pwdIdNumber = rs.getString("pwd_id_number");
                    record.status = rs.getString("status");
                    record.lastAttendanceDate = rs.getDate("last_attendance_date");
                    record.totalAttendance = rs.getInt("total_attendance");
                    records.add(record);
                }
            }
        }
        return records;
    }

    public static class AttendanceRecord {
        public int memberId;
        public String fullName;
        public String pwdIdNumber;
        public String status;
        public java.sql.Date lastAttendanceDate;
        public int totalAttendance;
    }

    public static class MemberData {
        public int memberId;
        public String fullName;
        public String pwdIdNumber;
        public String disabilityType;
        public Date dateIssued;
        public Date idValidUntil;
        public Date birthdate;
        public int age;
        public String sex;
        public String civilStatus;
        public String placeOfBirth;
        public String educationLevel;
        public String occupation;
        public String address;
        public String mobileNumber;
        public String email;
        public String fbAccountName;
        public String guardianName;
        public String guardianRelation;
        public String guardianMobile;
        public boolean takesMedications;
        public String status;
        public List<Map<String, Object>> householdMembers;
        public Map<String, Boolean> medicalConditions;
        public String otherConditions;
        public List<String> medications;
    }
}