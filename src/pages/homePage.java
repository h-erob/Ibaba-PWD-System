package pages;

import java.awt.*;
import java.io.Serial;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class homePage extends JFrame {
    private JButton selectedButton = null;
    private JPanel recordPanel;

    public static void launch() {
        try {
            homePage frame = new homePage();
            frame.setTitle("Barangay Ibaba PWD Sector");
            frame.setResizable(false);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public homePage() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1200, 700);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setBounds(200, 110, 1000, 590);
        mainPanel.setLayout(new CardLayout()); // Allows switching views easily
        contentPane.add(mainPanel);

        // Add the default background as the initial view
        ImageIcon background = new ImageIcon("imgs/background.png");
        Image scaled1 = background.getImage().getScaledInstance(1000, 590, Image.SCALE_SMOOTH);
        ImageIcon resized1 = new ImageIcon(scaled1);
        JLabel bgImg = new JLabel(resized1);
        mainPanel.add(bgImg, "home");

        // Start of top bar panel
        JPanel topBar = new JPanel();
        topBar.setBackground(new Color(249,241,20));
        topBar.setBounds(200, 0, 991, 112);
        contentPane.add(topBar);
        topBar.setLayout(null);

        ImageIcon adminLogo = new ImageIcon("imgs/adminLogo.png");
        Image scaled2 = adminLogo.getImage().getScaledInstance(93, 52, Image.SCALE_SMOOTH);
        ImageIcon resized2 = new ImageIcon(scaled2);
        JLabel adLogo = new JLabel(resized2);

        adLogo.setBounds(870, 30, 93, 52);
        topBar.add(adLogo);

        JLabel headerText = new JLabel("Magandang Araw, Ka Barangay");
        headerText.setFont(new Font("Trebuchet MS", Font.BOLD, 32));
        headerText.setBounds(30, 27, 479, 40);
        topBar.add(headerText);

        JLabel headerSubText = new JLabel("Isang barangay, Isang komunidad, Sama-sama sa kaunlaran");
        headerSubText.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
        headerSubText.setBounds(30, 60, 435, 27);
        topBar.add(headerSubText);

        JLabel adminText = new JLabel("Admin");
        adminText.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
        adminText.setBounds(842, 46, 58, 14);
        topBar.add(adminText);

        JLabel adminSubText = new JLabel("Admin");
        adminSubText.setFont(new Font("Trebuchet MS", Font.PLAIN, 11));
        adminSubText.setBounds(857, 57, 43, 14);
        topBar.add(adminSubText);

        // Start of side bar panel
        JPanel sideBar = new JPanel();
        sideBar.setBackground(new Color(37,37,37));
        sideBar.setBounds(0, 0, 200, 663);
        contentPane.add(sideBar);
        sideBar.setLayout(null);

        // Create recordPanel for sideBar
        recordPanel = new JPanel();
        recordPanel.setBackground(new Color(37,37,37)); // Darker shade for contrast
        recordPanel.setBounds(0, 230, 200, 85);
        recordPanel.setLayout(null);
        recordPanel.setVisible(false); // Initially hidden


        ImageIcon barangay = new ImageIcon("imgs/brgyLogo.png");
        Image scaled3 = barangay.getImage().getScaledInstance(180, 100, Image.SCALE_SMOOTH);
        ImageIcon resized3 = new ImageIcon(scaled3);
        JLabel brgyLogo = new JLabel(resized3);

        brgyLogo.setBounds(12, 5, 180, 100);
        sideBar.add(brgyLogo);

        JButton btnHome = createSidebarButton("🏠 Home", 110, mainPanel, "home");
        JButton btnAttendance = createSidebarButton("📋 Attendance", 150, mainPanel, "attendance");
        JButton btnTransaction = createSidebarButton("🔻 Records", 190, sideBar, "recordSub");
        JButton btnAttendRecord = createSidebarButton("📃 Past Attendance", 230, mainPanel, "attendance");
        JButton btnDemoRecord = createSidebarButton("🫂 Member List", 270, mainPanel, "attendance");
        JButton btnAccount = createSidebarButton("👤 Account", 350, mainPanel, "attendance");
        JButton btnSettings = createSidebarButton("⚙ Settings", 420, mainPanel, "attendance");
        JButton btnHelp = createSidebarButton("❓ Help", 545, mainPanel, "attendance");
        JButton btnLogOut = createSidebarButton("🚪 Log Out", 585, mainPanel, "attendance");

        sideBar.add(btnHome);
        sideBar.add(btnAttendance);
        sideBar.add(btnTransaction);
        sideBar.add(btnAccount);
        sideBar.add(btnSettings);
        sideBar.add(btnHelp);
        sideBar.add(btnLogOut);

        btnAttendRecord.setBounds(0, 0, 200, 40);
        btnDemoRecord.setBounds(0, 40, 200, 40);
        btnAttendRecord.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
        btnDemoRecord.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
        recordPanel.add(btnAttendRecord);
        recordPanel.add(btnDemoRecord);

        ArrayList<JButton> sidebarButtons = new ArrayList<>();
        sidebarButtons.add(btnHome);
        sidebarButtons.add(btnAttendance);
        sidebarButtons.add(btnTransaction);
        sidebarButtons.add(btnAccount);
        sidebarButtons.add(btnSettings);
        sidebarButtons.add(btnHelp);
        sidebarButtons.add(btnLogOut);
        sidebarButtons.add(btnAttendRecord);
        sidebarButtons.add(btnDemoRecord);

        selectButton(btnHome);

        JSeparator separator1 = new JSeparator();
        separator1.setBounds(40, 490, 120, 1);
        separator1.setForeground(new Color(150, 150, 150));
        sideBar.add(separator1);

        JSeparator separator2 = new JSeparator();
        separator2.setBounds(40, 330, 120, 1);
        separator2.setForeground(new Color(150, 150, 150));
        sideBar.add(separator2);

        sideBar.add(recordPanel, "recordSub");
        mainPanel.add(new attendancePage(), "attendance");
    }

    private JButton createSidebarButton(String text, int yPosition, JPanel mainPanel, String cardName) {
        JButton button = new JButton(text);
        button.setBounds(0, yPosition, 201, 40);

        button.setFont(new Font("", Font.PLAIN, 16));
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setOpaque(true);
        button.setBackground(new Color(37, 37, 37));
        button.setForeground(new Color(150, 150, 150));

        button.addActionListener(e -> {
            selectButton(button);
            if (cardName != null && !cardName.isEmpty()) {
                if ("recordSub".equals(cardName)) {
                    recordPanel.setVisible(!recordPanel.isVisible());
                } else if (cardName != null && !cardName.isEmpty()) {
                    CardLayout cl = (CardLayout)(mainPanel.getLayout());
                    cl.show(mainPanel, cardName);
                }
            }
        });

        return button;
    }



    private void selectButton(JButton buttonToSelect) {
        if (selectedButton != null && selectedButton != buttonToSelect) {
            selectedButton.setBackground(new Color(37,37,37));
            selectedButton.setForeground(new Color(150, 150, 150));
        }
        buttonToSelect.setBackground(new Color(249,241,20));
        buttonToSelect.setForeground(new Color(37,37,37));
        selectedButton = buttonToSelect;
    }
}