package db;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class mockDataLoader {
    public static void loadMockData(Connection conn) {
        try {
            File file = new File("pwdCares_MockData.sql");
            if (!file.exists()) {
                System.out.println("No mock SQL file found. Skipping mock data.");
                return;
            }

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM members");
            rs.next();
            int memberCount = rs.getInt(1);
            if (memberCount > 0) {
                System.out.println("Members table already contains data. Skipping mock data to prevent conflicts.");
                return;
            }

            rs = stmt.executeQuery("SELECT COUNT(*) FROM admins");
            rs.next();
            int adminCount = rs.getInt(1);
            boolean skipAdmins = adminCount > 0;

            Scanner scanner = new Scanner(file);
            scanner.useDelimiter(";");
            while (scanner.hasNext()) {
                String sql = scanner.next().trim();

                if (sql.isEmpty() || sql.startsWith("--") || sql.startsWith("/*") || !sql.toUpperCase().startsWith("INSERT")) {
                    if (!sql.isEmpty() && !sql.startsWith("--") && !sql.startsWith("/*")) {
                        System.out.println("Skipping statement: " + sql);
                    }
                    continue;
                }

                if (skipAdmins && sql.toUpperCase().contains("INSERT INTO `admins`")) {
                    System.out.println("Skipping admin insert due to existing data: " + sql);
                    continue;
                }

                try {
                    String modifiedSql = sql.replace("INSERT INTO", "INSERT IGNORE INTO");
                    stmt.execute(modifiedSql + ";");
                } catch (Exception ex) {
                    System.out.println("Error executing SQL: " + sql);
                    ex.printStackTrace();
                }
            }

            scanner.close();
            System.out.println("Mock data loaded successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to load mock data.");
        }
    }
}