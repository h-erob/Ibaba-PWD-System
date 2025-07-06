import pages.loginPage;
import pages.mainPage;
import javax.swing.*;
import db.*;

public class Main {
    public static void main(String[] args) {
        try {
            databaseInitializer.initialize();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Failed to initialize database: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }

        SwingUtilities.invokeLater(loginPage::launch);
    }
}