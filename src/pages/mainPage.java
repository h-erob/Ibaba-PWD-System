package pages;

import pages.recordsPanel.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import javax.swing.*;

public class mainPage extends JFrame {
    private JButton selectedButton = null;
    private JPanel recordPanel;
    private JPanel contentPane;
    private JPanel cardPanel;
    private JPanel dimPanel;
    public static mainPage instance;
    private homePage homePagePanel; // Add field for homePage
    private records_members recordsMembersPanel; // Add field for records_members
    private JButton btnTransaction;
    private JButton btnDemoRecord;

    public static void launch() {
        try {
            mainPage frame = new mainPage();
            frame.setResizable(false);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public mainPage() {
        instance = this;

        setUndecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1190, 690);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, 1200, 700);
        setContentPane(layeredPane);

        contentPane = new JPanel();
        contentPane.setLayout(null);
        contentPane.setBounds(0, 0, 1200, 700);
        layeredPane.add(contentPane, JLayeredPane.DEFAULT_LAYER);

        JPanel titleBar = new JPanel();
        titleBar.setBackground(new Color(37, 37, 37));
        titleBar.setBounds(0, 0, 1190, 30);
        titleBar.setLayout(null);
        contentPane.add(titleBar);

        JLabel titleLabel = new JLabel("Barangay Ibaba Persons With Disability Association");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
        titleLabel.setBounds(10, 0, 350, 30);
        titleBar.add(titleLabel);

        JButton closeButton = new JButton("X");
        closeButton.setBounds(1150, 0, 40, 30);
        closeButton.setBackground(new Color(200, 50, 50));
        closeButton.setForeground(Color.WHITE);
        closeButton.setFont(new Font("Arial", Font.BOLD, 14));
        closeButton.setFocusPainted(false);
        closeButton.setMargin(new Insets(0, 0, 0, 0));
        closeButton.setBorderPainted(false);
        closeButton.setContentAreaFilled(true);
        closeButton.addActionListener(e -> dispose());
        titleBar.add(closeButton);

        final Point[] mousePoint = {null};
        titleBar.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mousePoint[0] = e.getPoint();
            }
        });
        titleBar.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                Point currPoint = e.getLocationOnScreen();
                setLocation(currPoint.x - mousePoint[0].x, currPoint.y - mousePoint[0].y);
            }
        });

        JPanel topBar = new JPanel();
        topBar.setBackground(new Color(254, 239, 25));
        topBar.setBounds(200, 30, 991, 115);
        contentPane.add(topBar);
        topBar.setLayout(null);

        JLabel headerText = new JLabel("Magandang Araw, Ka Barangay");
        headerText.setFont(new Font("Trebuchet MS", Font.BOLD, 32));
        headerText.setBounds(30, 27, 479, 40);
        topBar.add(headerText);

        JLabel headerSubText = new JLabel("Isang barangay, Isang komunidad, Sama-sama sa kaunlaran");
        headerSubText.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
        headerSubText.setBounds(30, 60, 435, 27);
        topBar.add(headerSubText);

        JPanel sideBar = new JPanel();
        sideBar.setBackground(new Color(37, 37, 37));
        sideBar.setBounds(0, 30, 200, 663);
        contentPane.add(sideBar);
        sideBar.setLayout(null);

        recordPanel = new JPanel();
        recordPanel.setBackground(new Color(37, 37, 37));
        recordPanel.setBounds(0, 265, 200, 85);
        recordPanel.setLayout(null);
        recordPanel.setVisible(false);

        ImageIcon barangay = new ImageIcon("imgs/homeLogo.png");
        JLabel brgyLogo = new JLabel(barangay);
        brgyLogo.setBounds(29, 39, 142, 40);
        sideBar.add(brgyLogo);

        cardPanel = new JPanel(new CardLayout());
        cardPanel.setBounds(200, 144, 1000, 590);
        contentPane.add(cardPanel);

        JButton btnHome = createSidebarButton("\ud83c\udfe0 Home", 115, "home");
        JButton btnAttendance = createSidebarButton("\ud83d\udccb Attendance", 165, "attendance");
        btnTransaction = createSidebarButton("\ud83d\udcc2 Records", 215, "recordSub");
        JButton btnAttendRecord = createSidebarButton("- Past Attendance", 255, "recordAttendance");
        btnDemoRecord = createSidebarButton("- Member List", 295, "recordMembers");
        JButton btnSettings = createSidebarButton("\u2699 Settings", 485, "home");
        JButton btnHelp = createSidebarButton("\u2753 Help", 535, "home");
        JButton btnLogOut = createSidebarButton("\ud83d\udeaa Log Out", 585, "");

        sideBar.add(btnHome);
        sideBar.add(btnAttendance);
        sideBar.add(btnTransaction);
        sideBar.add(btnSettings);
        sideBar.add(btnHelp);
        sideBar.add(btnLogOut);

        btnAttendRecord.setBounds(0, 0, 200, 40);
        btnDemoRecord.setBounds(0, 40, 200, 40);
        btnAttendRecord.setFont(new Font("", Font.BOLD, 14));
        btnDemoRecord.setFont(new Font("", Font.BOLD, 14));
        recordPanel.add(btnAttendRecord);
        recordPanel.add(btnDemoRecord);

        ArrayList<JButton> sidebarButtons = new ArrayList<>();
        sidebarButtons.add(btnHome);
        sidebarButtons.add(btnAttendance);
        sidebarButtons.add(btnTransaction);
        sidebarButtons.add(btnSettings);
        sidebarButtons.add(btnHelp);
        sidebarButtons.add(btnLogOut);
        sidebarButtons.add(btnAttendRecord);
        sidebarButtons.add(btnDemoRecord);

        sideBar.add(recordPanel);

        selectButton(btnHome);

        JSeparator separator = new JSeparator();
        separator.setBounds(45, 482, 110, 2);
        separator.setForeground(new Color(150, 150, 150));
        sideBar.add(separator);


        // Initialize and add pages to cardPanel
        homePagePanel = new homePage();
        recordsMembersPanel = new records_members();
        cardPanel.add(homePagePanel, "home");
        cardPanel.add(new attendancePage(), "attendance");
        cardPanel.add(new pages.recordsPanel.records_attendancePage(), "recordAttendance");
        cardPanel.add(recordsMembersPanel, "recordMembers");

        btnLogOut.addActionListener(e -> new logoutPage(this));
        CardLayout cl = (CardLayout)(cardPanel.getLayout());
        cl.show(cardPanel, "home");

        dimPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(new Color(0, 0, 0, 130));
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        dimPanel.setOpaque(false);
        dimPanel.setBounds(0, 0, 1200, 700);
        dimPanel.setVisible(false);

        layeredPane.add(dimPanel, JLayeredPane.PALETTE_LAYER);
    }

    public JPanel getCardPanel() {
        return cardPanel;
    }

    public void selectSidebarButton(JButton button, String cardName, boolean isSubmenu) {
        if (isSubmenu) {
            recordPanel.setVisible(true);
        }
        if (selectedButton != null) {
            selectedButton.setBackground(new Color(37, 37, 37));
            selectedButton.setForeground(new Color(150, 150, 150));
        }
        button.setBackground(new Color(254, 239, 25));
        button.setForeground(new Color(37, 37, 37));
        selectedButton = button;
        if (isSubmenu) {
            btnTransaction.setBackground(new Color(37, 37, 37));
            btnTransaction.setForeground(new Color(150, 150, 150));
        }
        CardLayout cl = (CardLayout) (cardPanel.getLayout());
        cl.show(cardPanel, cardName);
    }

    public void selectMemberListButton() {
        // Show the records submenu
        recordPanel.setVisible(true);
        // Reset the previously selected button
        if (selectedButton != null) {
            selectedButton.setBackground(new Color(37, 37, 37));
            selectedButton.setForeground(new Color(150, 150, 150));
        }
        // Highlight only the "Member List" button
        btnDemoRecord.setBackground(new Color(254, 239, 25));
        btnDemoRecord.setForeground(new Color(37, 37, 37));
        selectedButton = btnDemoRecord;
        // Ensure "Records" button is not highlighted
        btnTransaction.setBackground(new Color(37, 37, 37));
        btnTransaction.setForeground(new Color(150, 150, 150));
        // Switch to records_members panel
        CardLayout cl = (CardLayout) (cardPanel.getLayout());
        cl.show(cardPanel, "recordMembers");
    }

    public homePage getHomePagePanel() {
        return homePagePanel;
    }

    public records_members getRecordsMembersPanel() {
        return recordsMembersPanel;
    }

    public void showDim() {
        dimPanel.setVisible(true);
    }

    public void hideDim() {
        dimPanel.setVisible(false);
    }

    private JButton createSidebarButton(String text, int yPosition, String cardName) {
        JButton button = new JButton(text);
        button.setBounds(0, yPosition, 201, 50);
        button.setFont(new Font("", Font.BOLD, 16));
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
                } else {
                    CardLayout cl = (CardLayout)(cardPanel.getLayout());
                    cl.show(cardPanel, cardName);
                }
            }
        });
        return button;
    }

    private void selectButton(JButton buttonToSelect) {
        if (selectedButton != null && selectedButton != buttonToSelect) {
            selectedButton.setBackground(new Color(37, 37, 37));
            selectedButton.setForeground(new Color(150, 150, 150));
        }
        buttonToSelect.setBackground(new Color(254, 239, 25));
        buttonToSelect.setForeground(new Color(37, 37, 37));
        selectedButton = buttonToSelect;
    }
}