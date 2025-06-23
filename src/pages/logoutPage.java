package pages;

import javax.swing.*;

public class logoutPage {
    public logoutPage(JFrame mainFrame){
        int choice = JOptionPane.showConfirmDialog(null, "Do you wish to log out?", "Confirmation", JOptionPane.YES_NO_OPTION);

        if (choice == JOptionPane.YES_OPTION){
            mainFrame.dispose();
            loginPage.launch();
        } else if (choice == JOptionPane.NO_OPTION) {

        }
    }
}
