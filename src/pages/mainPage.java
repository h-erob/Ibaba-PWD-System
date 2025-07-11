package pages;

import com.sun.tools.javac.Main;
import pages.recordsPanel.*;
import pages.records_membersbtn.viewMembers_InfoPage;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.List;
import java.util.ArrayList;
import javax.swing.*;

public class mainPage extends JFrame {
    private JButton selectedButton = null;
    private JPanel recordPanel;
    private JPanel contentPane;
    private JPanel cardPanel;
    private JPanel dimPanel;
    public static mainPage instance;
    private homePage homePagePanel;
    private records_members recordsMembersPanel;
    private attendancePage attendancePanel;
    private records_attendancePage recordsAttendancePanel;
    private JButton btnTransaction;
    private JButton btnDemoRecord;
    private List<viewMembers_InfoPage> openMemberPages = new ArrayList<>();

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
        attendancePanel = new attendancePage();
        recordsAttendancePanel = new records_attendancePage();

        setUndecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1190, 690);

        ImageIcon logo = new ImageIcon("imgs/bipdaLogo.png");
        setIconImage(logo.getImage());

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
        topBar.setBackground(new Color(255, 228, 113));
        topBar.setBounds(200, 30, 991, 115);
        contentPane.add(topBar);
        topBar.setLayout(null);

        JLabel headerText = new JLabel("Magandang Araw, Ka Baranggay");
        headerText.setFont(new Font("Trebuchet MS", Font.BOLD, 32));
        headerText.setBounds(30, 27, 479, 40);
        topBar.add(headerText);

        JLabel headerSubText = new JLabel("Isang baranggay, Isang komunidad, Sama-sama sa kaunlaran");
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
        recordPanel.setBounds(0, 267, 200, 85);
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
        JButton btnSupport = createSidebarButton("\u2699 Support", 535, "support");
        JButton btnLogOut = createSidebarButton("\ud83d\udeaa Log Out", 585, "");

        sideBar.add(btnHome);
        sideBar.add(btnAttendance);
        sideBar.add(btnTransaction);
        sideBar.add(btnSupport);
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
        sidebarButtons.add(btnSupport);
        sidebarButtons.add(btnLogOut);
        sidebarButtons.add(btnAttendRecord);
        sidebarButtons.add(btnDemoRecord);

        sideBar.add(recordPanel);

        selectButton(btnHome);

        JSeparator separator = new JSeparator();
        separator.setBounds(40, 533, 122, 2);
        separator.setForeground(new Color(150, 150, 150));
        sideBar.add(separator);

        homePagePanel = new homePage();
        recordsMembersPanel = new records_members();
        cardPanel.add(homePagePanel, "home");
        cardPanel.add(attendancePanel, "attendance");
        cardPanel.add(recordsAttendancePanel, "recordAttendance");
        cardPanel.add(recordsMembersPanel, "recordMembers");
        cardPanel.add(new supportPage(), "support");

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
        button.setBackground(new Color(255, 228, 113));
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
        recordPanel.setVisible(true);
        if (selectedButton != null) {
            selectedButton.setBackground(new Color(37, 37, 37));
            selectedButton.setForeground(new Color(150, 150, 150));
        }
        btnDemoRecord.setBackground(new Color(255, 228, 113));
        btnDemoRecord.setForeground(new Color(37, 37, 37));

        selectedButton = btnDemoRecord;

        btnTransaction.setBackground(new Color(37, 37, 37));
        btnTransaction.setForeground(new Color(150, 150, 150));

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

    public void refreshAttendancePage() {
        if (attendancePanel != null) {
            attendancePanel.reloadMembers();
        }
    }

    public void refreshRecordsAttendancePage() {
        if (recordsAttendancePanel != null) {
            recordsAttendancePanel.reloadAttendanceData();
        }
    }

    public void refreshHomePage() {
        if (homePagePanel != null) {
            homePagePanel.reloadData();
        }
    }

    public void setRecordsMembersPanel(records_members panel) {
        this.recordsMembersPanel = panel;
    }

    public void refreshRecordsMembers() {
        if (recordsMembersPanel != null) {
            recordsMembersPanel.loadMembers();
            recordsMembersPanel.clearSearchField();
        }
    }

    public void addOpenMemberPage(viewMembers_InfoPage page) {
        openMemberPages.add(page);
    }

    public void removeOpenMemberPage(viewMembers_InfoPage page) {
        openMemberPages.remove(page);
    }

    public void refreshOpenMemberPages(int memberId) {
        for (viewMembers_InfoPage page : openMemberPages) {
            if (page.getMemberId() == memberId) {
                page.reloadData();
            }
        }
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
        buttonToSelect.setBackground(new Color(255, 228, 113));
        buttonToSelect.setForeground(new Color(37, 37, 37));
        selectedButton = buttonToSelect;
    }
}