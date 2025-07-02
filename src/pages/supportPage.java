package pages;

import dal.admins.adminDAO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class supportPage extends JPanel {
    private final adminDAO adminDao = new adminDAO();
    private final JCheckBox showPass;
    private final JCheckBox showNewPass;
    private final JCheckBox showConfPass;
    private final JPasswordField password;
    private final JPasswordField newPass;
    private final JPasswordField confNewPass;

    public supportPage() {
        setLayout(null);
        setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel supportHeader = new JLabel("⚙ SUPPORT");
        supportHeader.setBounds(18, 10, 300, 25);
        supportHeader.setFont(new Font(" ", Font.BOLD, 24));
        add(supportHeader);

        JLabel supportSubHeader = new JLabel("Use this section to update your password and contact support.");
        supportSubHeader.setBounds(45, 32, 500, 18);
        supportSubHeader.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
        add(supportSubHeader);

        JSeparator separator = new JSeparator();
        separator.setForeground(new Color(100, 100, 100));
        separator.setBounds(15, 59, 930, 2);
        add(separator);

        JPanel passwordPanel = createRoundedPanel();
        passwordPanel.setBackground(new Color(249,245,245));
        passwordPanel.setBounds(24, 72, 410, 460);
        passwordPanel.setLayout(null);
        add(passwordPanel);

        ImageIcon passIcon = new ImageIcon("imgs/lockIcon.png");
        JLabel passImg = new JLabel(passIcon);
        passImg.setBounds(180, 30, 40, 40);
        passwordPanel.add(passImg);

        JLabel passwordHeader = new JLabel("UPDATE PASSWORD");
        passwordHeader.setBounds(110, 75, 500, 22);
        passwordHeader.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
        passwordPanel.add(passwordHeader);

        JLabel passworSubdHeader = new JLabel("Update your password to keep your account secure");
        passworSubdHeader.setBounds(42, 95, 500, 22);
        passworSubdHeader.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
        passwordPanel.add(passworSubdHeader);

        JLabel currPasslbl = new JLabel("CURRENT PASSWORD");
        currPasslbl.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
        currPasslbl.setBounds(50, 146, 400, 15);
        passwordPanel.add(currPasslbl);

        password = new JPasswordField();
        password.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
        password.setBounds(0, 0, 300, 33);
        password.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK, 1), BorderFactory.createEmptyBorder(5, 5, 5, 5)));

        JLayeredPane currPassPane = new JLayeredPane();
        currPassPane.setBounds(50, 160, 300, 33);
        currPassPane.add(password, JLayeredPane.DEFAULT_LAYER);

        ImageIcon rawIcon = new ImageIcon("imgs/supShow.png");
        Image scaledImage = rawIcon.getImage().getScaledInstance(18, 12, Image.SCALE_SMOOTH);
        ImageIcon resizedShowIcon = new ImageIcon(scaledImage);

        ImageIcon rawSelectedIcon = new ImageIcon("imgs/supHide.png");
        Image scaledSelectedImage = rawSelectedIcon.getImage().getScaledInstance(18, 12, Image.SCALE_SMOOTH);
        ImageIcon resizedHideIcon = new ImageIcon(scaledSelectedImage);

        showPass = new JCheckBox();
        showPass.setIcon(resizedShowIcon);
        showPass.setSelectedIcon(resizedHideIcon);
        showPass.setBorderPainted(false);
        showPass.setFocusPainted(false);
        showPass.setContentAreaFilled(false);
        showPass.setBounds(270, 6, 25, 20);
        showPass.addActionListener(e -> showPassword());
        currPassPane.add(showPass, JLayeredPane.PALETTE_LAYER);
        passwordPanel.add(currPassPane);

        JLabel newPasslbl = new JLabel("NEW PASSWORD");
        newPasslbl.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
        newPasslbl.setBounds(50, 207, 400, 15);
        passwordPanel.add(newPasslbl);

        newPass = new JPasswordField();
        newPass.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
        newPass.setBounds(0, 0, 300, 33);
        newPass.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK, 1), BorderFactory.createEmptyBorder(5, 5, 5, 5)));

        JLayeredPane newPassPane = new JLayeredPane();
        newPassPane.setBounds(50, 221, 300, 33);
        newPassPane.add(newPass, JLayeredPane.DEFAULT_LAYER);

        showNewPass = new JCheckBox();
        showNewPass.setIcon(resizedShowIcon);
        showNewPass.setSelectedIcon(resizedHideIcon);
        showNewPass.setBorderPainted(false);
        showNewPass.setFocusPainted(false);
        showNewPass.setContentAreaFilled(false);
        showNewPass.setBounds(270, 6, 25, 20);
        showNewPass.addActionListener(e -> showPassword());
        newPassPane.add(showNewPass, JLayeredPane.PALETTE_LAYER);
        passwordPanel.add(newPassPane);

        JLabel confNewPasslbl = new JLabel("CONFIRM NEW PASSWORD");
        confNewPasslbl.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
        confNewPasslbl.setBounds(50, 267, 400, 15);
        passwordPanel.add(confNewPasslbl);

        confNewPass = new JPasswordField();
        confNewPass.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
        confNewPass.setBounds(0, 0, 300, 33);
        confNewPass.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK, 1), BorderFactory.createEmptyBorder(5, 5, 5, 5)));

        JLayeredPane confPassPane = new JLayeredPane();
        confPassPane.setBounds(50, 281, 300, 33);
        confPassPane.add(confNewPass, JLayeredPane.DEFAULT_LAYER);

        showConfPass = new JCheckBox();
        showConfPass.setIcon(resizedShowIcon);
        showConfPass.setSelectedIcon(resizedHideIcon);
        showConfPass.setBorderPainted(false);
        showConfPass.setFocusPainted(false);
        showConfPass.setContentAreaFilled(false);
        showConfPass.setBounds(270, 6, 25, 20);
        showConfPass.addActionListener(e -> showPassword());
        confPassPane.add(showConfPass, JLayeredPane.PALETTE_LAYER);
        passwordPanel.add(confPassPane);

        RoundedButton setPass = new RoundedButton("SET NEW PASSWORD", new Color(40,40,40));
        setPass.setBounds(50, 375, 300, 33);
        setPass.setBorder(null);
        passwordPanel.add(setPass);
        setPass.addActionListener(e -> {
            String currentPass = new String(password.getPassword()).trim();
            String newPassword = new String(newPass.getPassword()).trim();
            String confirmPass = new String(confNewPass.getPassword()).trim();
            String currentUsername = "admin";

            if (currentPass.isEmpty() || newPassword.isEmpty() || confirmPass.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all password fields.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!newPassword.equals(confirmPass)) {
                JOptionPane.showMessageDialog(this, "New passwords do not match.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (newPassword.length() < 8) {
                JOptionPane.showMessageDialog(this, "New password must be at least 8 characters long.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!newPassword.matches(".*[A-Z].*")) {
                JOptionPane.showMessageDialog(this, "New password must contain at least one uppercase letter.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!newPassword.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};:'\",.<>?].*")) {
                JOptionPane.showMessageDialog(this, "New password must contain at least one special character.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (newPassword.equals(currentPass)) {
                JOptionPane.showMessageDialog(this, "New password cannot be the same as the current password.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (newPassword.equals(currentUsername)) {
                JOptionPane.showMessageDialog(this, "New password cannot be the same as the username.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            boolean isValid = adminDao.checkIfAdminExists(currentUsername, currentPass);
            if (!isValid) {
                JOptionPane.showMessageDialog(this, "Current password is incorrect.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            boolean updated = adminDao.updatePassword(currentUsername, newPassword);
            if (updated) {
                JOptionPane.showMessageDialog(this, "Password updated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                password.setText("");
                newPass.setText("");
                confNewPass.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Failed to update password. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        JPanel faqPanel = createRoundedPanel();
        faqPanel.setBackground(new Color(249,245,245));
        faqPanel.setBounds(450, 72, 510, 460);
        faqPanel.setLayout(null);
        add(faqPanel);

        ImageIcon faqIcon = new ImageIcon("imgs/faqIcon.png");
        JLabel faqImg = new JLabel(faqIcon);
        faqImg.setBounds(21, 13, 40, 40);
        faqPanel.add(faqImg);

        JLabel faqHeader = new JLabel("Frequently Asked Questions");
        faqHeader.setBounds(70, 15, 500, 22);
        faqHeader.setFont(new Font("Arial", Font.BOLD, 19));
        faqPanel.add(faqHeader);

        JLabel faqSubHeader = new JLabel("Helping you understand the CARES system better");
        faqSubHeader.setBounds(70, 36, 500, 14);
            faqSubHeader.setFont(new Font("Arial", Font.PLAIN, 13));
        faqPanel.add(faqSubHeader);

        JPanel innerFaq = createRoundedPanel();
        innerFaq.setBackground(new Color(249,245,245));
        innerFaq.setBounds(10, 60, 490, 390);
        innerFaq.setLayout(null);
        faqPanel.add(innerFaq);

        JLabel faq1 = new JLabel("Q1: The member I added isn’t showing up in the list.");
        faq1.setBounds(17, 23, 500, 20);
        faq1.setFont(new Font("Arial", Font.BOLD, 16));
        innerFaq.add(faq1);

        JTextArea ans1 = new JTextArea("A: Try restarting the program or exiting and reopening it. This will refresh\n     the member list display.");
        ans1.setBounds(18, 44, 500, 32);
        ans1.setEditable(false);
        ans1.setOpaque(false);
        ans1.setFont(new Font("Arial", Font.PLAIN, 14));
        innerFaq.add(ans1);

        JSeparator faqseparator1 = new JSeparator();
        faqseparator1.setForeground(new Color(100, 100, 100));
        faqseparator1.setBounds(18, 85, 450, 2);
        innerFaq.add(faqseparator1);

        JLabel faq2 = new JLabel("Q2: I can’t find a specific member.");
        faq2.setBounds(14, 94, 500, 20);
        faq2.setFont(new Font("Arial", Font.BOLD, 16));
        innerFaq.add(faq2);

        JTextArea ans2 = new JTextArea("A: Use the search bar and double-check for spelling errors or typos in the\n    name. Partial name and ID number searches are supported.");
        ans2.setBounds(15, 115, 500, 32);
        ans2.setEditable(false);
        ans2.setOpaque(false);
        ans2.setFont(new Font("Arial", Font.PLAIN, 14));
        innerFaq.add(ans2);

        JSeparator faqseparator2 = new JSeparator();
        faqseparator2.setForeground(new Color(100, 100, 100));
        faqseparator2.setBounds(18, 158, 450, 2);
        innerFaq.add(faqseparator2);

        JLabel faq3 = new JLabel("Q3: Some member information is incorrect.");
        faq3.setBounds(14, 168, 500, 20);
        faq3.setFont(new Font("Arial", Font.BOLD, 16));
        innerFaq.add(faq3);

        JTextArea ans3 = new JTextArea("A: You can edit member details from the Members List. Click the Edit button\n      on the top right of the member’s profile, make the changes, then click\n      Save in the same location.");
        ans3.setBounds(15, 189, 500, 48);
        ans3.setEditable(false);
        ans3.setOpaque(false);
        ans3.setFont(new Font("Arial", Font.PLAIN, 14));
        innerFaq.add(ans3);

        JSeparator faqseparator3 = new JSeparator();
        faqseparator3.setForeground(new Color(100, 100, 100));
        faqseparator3.setBounds(18, 250, 450, 2);
        innerFaq.add(faqseparator3);

        JLabel faq4 = new JLabel("Q4: I can’t click the “Save” button in the Attendance page.");
        faq4.setBounds(14, 263, 500, 19);
        faq4.setFont(new Font("Arial", Font.BOLD, 16));
        innerFaq.add(faq4);

        JTextArea ans4 = new JTextArea("A: You may have already submitted attendance for today. Each member’s \n     attendance can only be saved once per day.");
        ans4.setBounds(16, 284, 500, 32);
        ans4.setEditable(false);
        ans4.setOpaque(false);
        ans4.setFont(new Font("Arial", Font.PLAIN, 14));
        innerFaq.add(ans4);

        JSeparator faqseparator4 = new JSeparator();
        faqseparator4.setForeground(new Color(100, 100, 100));
        faqseparator4.setBounds(18, 332, 450, 2);
        innerFaq.add(faqseparator4);

        JLabel contlbl = new JLabel("If problems persist, please contact the development team ");
        contlbl.setForeground(new Color(80,80,80));
        contlbl.setBounds(37, 341, 500, 19);
        contlbl.setFont(new Font(" ", Font.BOLD, 15));
        innerFaq.add(contlbl);

        JLabel contlbl1 = new JLabel("using the contact information provided");
        contlbl1.setForeground(new Color(80,80,80));
        contlbl1.setBounds(70, 359, 500, 19);
        contlbl1.setFont(new Font(" ", Font.BOLD, 15));
        innerFaq.add(contlbl1);

        JButton contactBtn = new JButton("<html><u>here.</u></html>");
        contactBtn.setBounds(346, 353, 65, 30);
        contactBtn.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
        contactBtn.setForeground(new Color(56, 117, 186));
        contactBtn.setBackground(new Color(249,245,245));
        contactBtn.setBorderPainted(false);
        contactBtn.setContentAreaFilled(false);
        contactBtn.setFocusPainted(false);
        contactBtn.setOpaque(false);
        innerFaq.add(contactBtn);

        JDialog contactDialog = new JDialog(SwingUtilities.getWindowAncestor(this), "Contact List", Dialog.ModalityType.APPLICATION_MODAL);
        contactDialog.setSize(545, 510);
        contactDialog.setLayout(null);
        contactDialog.setLocationRelativeTo(this);
        contactDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        contactDialog.getContentPane().setBackground(new Color(238, 235, 235));

        contactBtn.addActionListener(e -> {
            mainPage.instance.showDim();
            contactDialog.setVisible(true);
            mainPage.instance.hideDim();
        });

        JPanel contactPanel = createRoundedPanel();
        contactPanel.setBackground(new Color(249,245,245));
        contactPanel.setBounds(10, 5, 505, 460);
        contactPanel.setLayout(null);
        contactDialog.add(contactPanel);

        ImageIcon contactIcon = new ImageIcon("imgs/contactIcon.png");
        JLabel contactImg = new JLabel(contactIcon);
        contactImg.setBounds(18, 13, 40, 40);
        contactPanel.add(contactImg);

        JLabel contactHeader = new JLabel("CONTACT THE TEAM");
        contactHeader.setBounds(73, 16, 500, 22);
        contactHeader.setFont(new Font("Trebuchet MS", Font.BOLD, 19));
        contactPanel.add(contactHeader);

        JLabel contactSubHeader = new JLabel("Reach out to the development team for assistance");
        contactSubHeader.setBounds(74, 35, 500, 14);
        contactSubHeader.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
        contactPanel.add(contactSubHeader);

        JSeparator separator1 = new JSeparator();
        separator1.setForeground(new Color(100, 100, 100));
        separator1.setBounds(18, 62, 470, 2);
        contactPanel.add(separator1);

        JPanel dev1 = createRoundedPanel();
        dev1.setBackground(new Color(249,245,245));
        dev1.setBounds(20, 70, 470, 72);
        dev1.setLayout(null);
        contactPanel.add(dev1);

        ImageIcon dev1Icon = new ImageIcon("imgs/devimg.png");
        JLabel dev1Img = new JLabel(dev1Icon);
        dev1Img.setBounds(19, 6, 62, 60);
        dev1.add(dev1Img);

        JLabel dev1Name = new JLabel("AGUSTIN, JANA BEATRICE");
        dev1Name.setBounds(90, 18, 500, 22);
        dev1Name.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
        dev1.add(dev1Name);

        JLabel dev1Num = new JLabel("0995 364 9868");
        dev1Num.setBounds(91, 37, 500, 14);
        dev1Num.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
        dev1.add(dev1Num);

        JPanel dev2 = createRoundedPanel();
        dev2.setBackground(new Color(249,245,245));
        dev2.setBounds(20, 148, 470, 72);
        dev2.setLayout(null);
        contactPanel.add(dev2);

        ImageIcon dev2Icon = new ImageIcon("imgs/dev2img.png");
        JLabel dev2Img = new JLabel(dev2Icon);
        dev2Img.setBounds(19, 6, 62, 60);
        dev2.add(dev2Img);

        JLabel dev2Name = new JLabel("CAPACIO, PEAVEY IYA");
        dev2Name.setBounds(90, 18, 500, 22);
        dev2Name.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
        dev2.add(dev2Name);

        JLabel dev2Num = new JLabel("0975 891 6089");
        dev2Num.setBounds(91, 37, 500, 14);
        dev2Num.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
        dev2.add(dev2Num);

        JPanel dev3 = createRoundedPanel();
        dev3.setBackground(new Color(249,245,245));
        dev3.setBounds(20, 224, 470, 72);
        dev3.setLayout(null);
        contactPanel.add(dev3);

        ImageIcon dev3Icon = new ImageIcon("imgs/dev3img.png");
        JLabel dev3Img = new JLabel(dev3Icon);
        dev3Img.setBounds(19, 6, 62, 60);
        dev3.add(dev3Img);

        JLabel dev3Name = new JLabel("DESALIT, JOSEPH ISMAEL");
        dev3Name.setBounds(90, 18, 500, 22);
        dev3Name.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
        dev3.add(dev3Name);

        JLabel dev3Num = new JLabel("0992 621 0697");
        dev3Num.setBounds(91, 37, 500, 14);
        dev3Num.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
        dev3.add(dev3Num);

        JPanel dev4 = createRoundedPanel();
        dev4.setBackground(new Color(249,245,245));
        dev4.setBounds(20, 302, 470, 72);
        dev4.setLayout(null);
        contactPanel.add(dev4);

        ImageIcon dev4Icon = new ImageIcon("imgs/dev4img.png");
        JLabel dev4Img = new JLabel(dev4Icon);
        dev4Img.setBounds(19, 6, 62, 60);
        dev4.add(dev4Img);

        JLabel dev4Name = new JLabel("PENUS, ELIAND JOHN");
        dev4Name.setBounds(90, 18, 500, 22);
        dev4Name.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
        dev4.add(dev4Name);

        JLabel dev4Num = new JLabel("0960 412 3893");
        dev4Num.setBounds(91, 37, 500, 14);
        dev4Num.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
        dev4.add(dev4Num);

        JPanel dev5 = createRoundedPanel();
        dev5.setBackground(new Color(249,245,245));
        dev5.setBounds(20, 380, 470, 72);
        dev5.setLayout(null);
        contactPanel.add(dev5);

        ImageIcon dev5Icon = new ImageIcon("imgs/dev5img.png");
        JLabel dev5Img = new JLabel(dev5Icon);
        dev5Img.setBounds(19, 6, 62, 60);
        dev5.add(dev5Img);

        JLabel dev5Name = new JLabel("PERUCHO, EARL MICHAEL");
        dev5Name.setBounds(90, 18, 500, 22);
        dev5Name.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
        dev5.add(dev5Name);

        JLabel dev5Num = new JLabel("0921 436 5488");
        dev5Num.setBounds(91, 37, 500, 14);
        dev5Num.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
        dev5.add(dev5Num);

    }

    private void showPassword() {
        // Handle current password field
        if (showPass.isSelected()) {
            password.setEchoChar((char) 0);
        } else {
            password.setEchoChar('•');
        }

        // Handle new password field
        if (showNewPass.isSelected()) {
            newPass.setEchoChar((char) 0);
        } else {
            newPass.setEchoChar('•');
        }

        // Handle confirm new password field
        if (showConfPass.isSelected()) {
            confNewPass.setEchoChar((char) 0);
        } else {
            confNewPass.setEchoChar('•');
        }
    }

    private JPanel createRoundedPanel() {
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
                g2.setColor(new Color(58, 58, 58));
                g2.setStroke(new BasicStroke(1));
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 30, 30);
                g2.dispose();
            }
        };
        panel.setOpaque(false);
        panel.setBackground(new Color(238, 235, 235));
        panel.setLayout(null);
        return panel;
    }

    private static class RoundedButton extends JButton {
        private final int radius = 10;
        private final Color bgColor;

        public RoundedButton(String text, Color bgColor) {
            super(text);
            this.bgColor = bgColor;
            setFont(new Font("Trebuchet MS", Font.BOLD, 14));
            setForeground(new Color(255, 228, 113));
            setFocusPainted(false);
            setContentAreaFilled(false);
            setOpaque(false);
            setBorderPainted(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(bgColor);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
            FontMetrics fm = g2.getFontMetrics();
            int x = (getWidth() - fm.stringWidth(getText())) / 2;
            int y = (getHeight() - fm.getHeight()) / 2 + fm.getAscent();
            g2.setColor(getForeground());
            g2.setFont(getFont());
            g2.drawString(getText(), x, y);
            g2.dispose();
        }

        @Override
        protected void paintBorder(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(Color.BLACK);
            g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);
            g2.dispose();
        }
    }
}