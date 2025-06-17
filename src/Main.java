import pages.loginPage;
import pages.mainPage;
import javax.swing.*;
import db.database;
import pages.records_membersbtn.addMembersPage;
import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(mainPage::launch);
        //SwingUtilities.invokeLater(loginPage::launch);
        //SwingUtilities.invokeLater(addMembersPage::launch);
    }
}