import pages.homePage;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(homePage::launch);
    }
}