package pages.records_membersbtn;

import javax.swing.*;
import java.awt.*;

public class addMembersPage extends JFrame {

    public static void launch() {
        try {
            addMembersPage frame = new addMembersPage();
            frame.setTitle("Barangay Ibaba PWD Sector - Add Member");
            frame.setResizable(false);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public addMembersPage() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(900, 600);
        setLayout(new BorderLayout());

        // Tabbed pane setup
        UIManager.put("TabbedPane.tabInsets", new Insets(10, 10, 10, 10));
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
        add(tabbedPane, BorderLayout.CENTER);

        // First Tab - Identification
        JPanel idPanel = new JPanel(null);
        tabbedPane.addTab("Identification", idPanel);
        idPanel.setBackground(new Color(240, 240, 240));

        // Header
        JPanel topBar = new JPanel();
        topBar.setBackground(new Color(210, 210, 210));
        topBar.setBounds(0, 0, 900, 82);
        topBar.setLayout(null);
        idPanel.add(topBar);

        JLabel lblHeader = new JLabel("Enter Required Information for Barangay Membership");
        lblHeader.setFont(new Font("Trebuchet MS", Font.BOLD, 32));
        lblHeader.setBounds(20, 12, 860, 30);
        topBar.add(lblHeader);

        JLabel lblSubHeader = new JLabel("If certain details are unavailable, write 'N/A'. You can update them later.");
        lblSubHeader.setFont(new Font("Trebuchet MS", Font.PLAIN, 19));
        lblSubHeader.setBounds(20, 42, 850, 30);
        topBar.add(lblSubHeader);

        // Labels and Fields
        Font labelFont = new Font("Trebuchet MS", Font.BOLD, 20);
        Font fieldFont = new Font("Trebuchet MS", Font.BOLD, 18);

        JLabel lblPwdIdNo = new JLabel("PWD ID Number:");
        lblPwdIdNo.setFont(labelFont);
        lblPwdIdNo.setBounds(20, 90, 200, 30);
        idPanel.add(lblPwdIdNo);

        JTextField PwdIdNo = new JTextField();
        PwdIdNo.setFont(fieldFont);
        PwdIdNo.setBounds(210, 90, 205, 30);
        idPanel.add(PwdIdNo);

        JLabel lblName = new JLabel("Full Name:");
        lblName.setFont(labelFont);
        lblName.setBounds(20, 130, 200, 30);
        idPanel.add(lblName);

        JTextField fullName = new JTextField();
        fullName.setFont(fieldFont);
        fullName.setBounds(210, 130, 300, 30);
        idPanel.add(fullName);

        JLabel lblBday = new JLabel("Birthday (MM/DD/YY):");
        lblBday.setFont(labelFont);
        lblBday.setBounds(20, 170, 250, 30);
        idPanel.add(lblBday);

        JTextField birthday = new JTextField();
        birthday.setFont(fieldFont);
        birthday.setBounds(260, 170, 180, 30);
        idPanel.add(birthday);

        JLabel lblHouseNum = new JLabel("House Number:");
        lblHouseNum.setFont(labelFont);
        lblHouseNum.setBounds(20, 210, 200, 30);
        idPanel.add(lblHouseNum);

        JTextField houseNum = new JTextField();
        houseNum.setFont(fieldFont);
        houseNum.setBounds(210, 210, 400, 30);
        idPanel.add(houseNum);

        JLabel lblSex = new JLabel("Sex:");
        lblSex.setFont(labelFont);
        lblSex.setBounds(20, 250, 100, 30);
        idPanel.add(lblSex);

        ButtonGroup btnSex = new ButtonGroup();

        JRadioButton f = new JRadioButton("Female");
        f.setFont(fieldFont);
        f.setBounds(80, 250, 100, 30);
        btnSex.add(f);
        idPanel.add(f);

        JRadioButton m = new JRadioButton("Male");
        m.setFont(fieldFont);
        m.setBounds(190, 250, 100, 30);
        btnSex.add(m);
        idPanel.add(m);

        JLabel lblPWDNo = new JLabel("PWD Number:");
        lblPWDNo.setFont(labelFont);
        lblPWDNo.setBounds(20, 290, 200, 30);
        idPanel.add(lblPWDNo);

        JTextField PWDNo = new JTextField();
        PWDNo.setFont(fieldFont);
        PWDNo.setBounds(210, 290, 205, 30);
        idPanel.add(PWDNo);

        JLabel lblGuardianNo = new JLabel("Guardian Number:");
        lblGuardianNo.setFont(labelFont);
        lblGuardianNo.setBounds(20, 330, 200, 30);
        idPanel.add(lblGuardianNo);

        JTextField GuardianNo = new JTextField();
        GuardianNo.setFont(fieldFont);
        GuardianNo.setBounds(210, 330, 205, 30);
        idPanel.add(GuardianNo);

        JLabel lblBCGStatus = new JLabel("BCG Status:");
        lblBCGStatus.setFont(labelFont);
        lblBCGStatus.setBounds(20, 370, 200, 30);
        idPanel.add(lblBCGStatus);

        JTextField BCGStatus = new JTextField();
        BCGStatus.setFont(fieldFont);
        BCGStatus.setBounds(210, 370, 205, 30);
        idPanel.add(BCGStatus);

        JLabel lblMemStart = new JLabel("Start of Membership (MM/YYYY):");
        lblMemStart.setFont(labelFont);
        lblMemStart.setBounds(20, 410, 350, 30);
        idPanel.add(lblMemStart);

        JTextField MemStart = new JTextField();
        MemStart.setFont(fieldFont);
        MemStart.setBounds(350, 410, 200, 30);
        idPanel.add(MemStart);

        // Second tab placeholder
        JPanel demoPanel = new JPanel(null);
        tabbedPane.addTab("Demographic Sheet", demoPanel);
    }
}
