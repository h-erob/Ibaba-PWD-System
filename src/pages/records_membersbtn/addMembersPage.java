package pages.records_membersbtn;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import javax.swing.plaf.basic.BasicTabbedPaneUI;

public class addMembersPage extends JFrame {
    private final JButton save;

    public static void launch() {
        try {
            addMembersPage frame = new addMembersPage();
            frame.setTitle("Membership Forms");
            frame.setResizable(false);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public addMembersPage() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(730, 590);
        setLayout(new BorderLayout());

        // Tabbed pane setup
        UIManager.put("TabbedPane.tabInsets", new Insets(3, 9, 3, 9));
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setUI(new BasicTabbedPaneUI() {

            @Override
            protected int calculateTabHeight(int tabPlacement, int tabIndex, int fontHeight) {
                return super.calculateTabHeight(tabPlacement, tabIndex, fontHeight) + 10;
            }

            @Override
            protected void installDefaults() {
                super.installDefaults();
                tabAreaInsets.top = 10; // Extra spacing inside the tab bar
            }

            @Override
            protected void paintTabArea(Graphics g, int tabPlacement, int selectedIndex) {
                g.setColor(new Color(37,37,37));
                g.fillRect(0, 0, tabPane.getWidth(), calculateTabAreaHeight(tabPlacement, runCount, maxTabHeight));
                super.paintTabArea(g, tabPlacement, selectedIndex);
            }

            @Override
            protected void paintTabBackground(Graphics g, int tabPlacement, int tabIndex,
                                              int x, int y, int w, int h, boolean isSelected) {
                if (isSelected) {
                    g.setColor(new Color(0, 202, 238));
                } else {
                    g.setColor(Color.WHITE);
                }
                g.fillRect(x, y, w, h);
            }

            @Override
            protected void paintFocusIndicator(Graphics g, int tabPlacement, Rectangle[] rects, int tabIndex,
                                               Rectangle iconRect, Rectangle textRect,
                                               boolean isSelected) {
            }

            @Override
            protected void paintContentBorderTopEdge(Graphics g, int tabPlacement, int selectedIndex,
                                                     int x, int y, int w, int h) {
            }
        });

        add(tabbedPane, BorderLayout.CENTER);

        // First Tab - Identification
        JPanel idPanel = new JPanel(null);
        tabbedPane.addTab(null, idPanel);
        tabbedPane.setTabComponentAt(0, new JLabel("Identification") {{
            setFont(new Font("Segoe UI", Font.BOLD, 16));
        }});
        idPanel.setBackground(new Color(240, 240, 240));

        // Header
        JPanel topBar = new JPanel();
        topBar.setBackground(new Color(220, 220, 220));
        topBar.setBounds(0, 0, getWidth(), 62);
        topBar.setLayout(null);
        idPanel.add(topBar);

        JLabel lblHeader = new JLabel("Enter Required Information for Barangay Membership");
        lblHeader.setFont(new Font("Trebuchet MS", Font.BOLD, 24));
        lblHeader.setBounds(15, 5, 860, 30);
        topBar.add(lblHeader);

        JLabel lblSubHeader = new JLabel("If certain details are unavailable, write 'N/A'. You can update them later.");
        lblSubHeader.setFont(new Font("Trebuchet MS", Font.ITALIC, 16));
        lblSubHeader.setBounds(15, 27, 850, 30);
        topBar.add(lblSubHeader);

        // Fonts and border
        Font labelFont = new Font("Trebuchet MS", Font.PLAIN, 16);
        Font fieldFont = new Font("Trebuchet MS", Font.PLAIN, 18);

        // Border for text fields
        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
        Border fieldBorder = BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(5, 5, 5, 5));

        // Form Fields
        JLabel lblDate = new JLabel("Date:");
        lblDate.setFont(labelFont);
        lblDate.setBounds(40, 70, 200, 30);
        idPanel.add(lblDate);

        JTextField date = new JTextField();
        date.setFont(fieldFont);
        date.setBounds(40, 100, 295, 38);
        date.setBorder(fieldBorder);
        idPanel.add(date);

        JLabel lblName = new JLabel("Full Name:");
        lblName.setFont(labelFont);
        lblName.setBounds(40, 140, 200, 30);
        idPanel.add(lblName);

        JTextField fullName = new JTextField();
        fullName.setFont(fieldFont);
        fullName.setBounds(40, 170, 295, 38);
        fullName.setBorder(fieldBorder);
        idPanel.add(fullName);

        JLabel lblBday = new JLabel("Birthday (MM/DD/YY):");
        lblBday.setFont(labelFont);
        lblBday.setBounds(40, 210, 250, 30);
        idPanel.add(lblBday);

        JTextField birthday = new JTextField();
        birthday.setFont(fieldFont);
        birthday.setBounds(40, 240, 295, 38);
        birthday.setBorder(fieldBorder);
        idPanel.add(birthday);

        JLabel lblHouseNum = new JLabel("House Number:");
        lblHouseNum.setFont(labelFont);
        lblHouseNum.setBounds(40, 280, 200, 30);
        idPanel.add(lblHouseNum);

        JTextArea houseNum = new JTextArea();
        houseNum.setFont(fieldFont);
        houseNum.setBounds(40, 310, 295, 108);
        houseNum.setLineWrap(true);
        houseNum.setWrapStyleWord(true);
        houseNum.setBorder(fieldBorder);
        idPanel.add(houseNum);

        JLabel lblSex = new JLabel("Sex:");
        lblSex.setFont(labelFont);
        lblSex.setBounds(380, 70, 100, 30);
        idPanel.add(lblSex);

        ButtonGroup btnSex = new ButtonGroup();

        JRadioButton f = new JRadioButton("Female");
        f.setFont(fieldFont);
        f.setBounds(380, 100, 100, 30);
        btnSex.add(f);
        idPanel.add(f);

        JRadioButton m = new JRadioButton("Male");
        m.setFont(fieldFont);
        m.setBounds(490, 100, 100, 30);
        btnSex.add(m);
        idPanel.add(m);

        JLabel lblPwdIDNo = new JLabel("PWD ID Number:");
        lblPwdIDNo.setFont(labelFont);
        lblPwdIDNo.setBounds(380, 140, 200, 30);
        idPanel.add(lblPwdIDNo);

        JTextField pwdIDNo = new JTextField();
        pwdIDNo.setFont(fieldFont);
        pwdIDNo.setBounds(380, 170, 295, 38);
        pwdIDNo.setBorder(fieldBorder);
        idPanel.add(pwdIDNo);

        JLabel lblPWDNo = new JLabel("PWD Number:");
        lblPWDNo.setFont(labelFont);
        lblPWDNo.setBounds(380, 210, 200, 30);
        idPanel.add(lblPWDNo);

        JTextField pwdNo = new JTextField();
        pwdNo.setFont(fieldFont);
        pwdNo.setBounds(380, 240, 295, 38);
        pwdNo.setBorder(fieldBorder);
        idPanel.add(pwdNo);

        JLabel lblBCGStatus = new JLabel("BCG Status:");
        lblBCGStatus.setFont(labelFont);
        lblBCGStatus.setBounds(380, 280, 200, 30);
        idPanel.add(lblBCGStatus);

        JTextField BCGStatus = new JTextField();
        BCGStatus.setFont(fieldFont);
        BCGStatus.setBounds(380, 310, 295, 38);
        BCGStatus.setBorder(fieldBorder);
        idPanel.add(BCGStatus);

        JLabel lblMemStart = new JLabel("Start of Membership (MM/DD/YY):");
        lblMemStart.setFont(labelFont);
        lblMemStart.setBounds(380, 350, 300, 30);
        idPanel.add(lblMemStart);

        JTextField MemStart = new JTextField();
        MemStart.setFont(fieldFont);
        MemStart.setBounds(380, 380, 295, 38);
        MemStart.setBorder(fieldBorder);
        idPanel.add(MemStart);

        save = new JButton("+ ADD");
        save.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
        save.setBounds(583, 443, 90, 35);
        save.setBackground(new Color(73, 230, 127));
        save.setForeground(Color.BLACK);
        save.setBorder(fieldBorder);
        idPanel.add(save);

        // Second tab placeholder
        JPanel demoPanel = new JPanel(new BorderLayout());
        tabbedPane.addTab(null, demoPanel);
        tabbedPane.setTabComponentAt(1, new JLabel("Demographic") {{
            setFont(new Font("Segoe UI", Font.BOLD, 16));
        }});

        addMembers_DemographicPage demographicPage = new addMembers_DemographicPage();
        demoPanel.add(demographicPage, BorderLayout.CENTER);


    }
}
