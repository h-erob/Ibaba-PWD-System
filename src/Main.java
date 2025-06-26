import pages.loginPage;
import pages.mainPage;
import javax.swing.*;
import db.*;
import pages.records_membersbtn.addMembersPage;
import pages.records_membersbtn.viewMembers_InfoPage;
import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        databaseInitializer.initialize();
        //SwingUtilities.invokeLater(mainPage::launch);
        SwingUtilities.invokeLater(loginPage::launch);
    }
}