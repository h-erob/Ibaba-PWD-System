import pages.mainPage;
import javax.swing.*;
import db.database;
import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(mainPage::launch);
    }
}