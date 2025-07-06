package pages.records_membersbtn;

import dal.members.membersDAO;
import pages.homePage;
import pages.mainPage;
import db.database;
import pages.recordsPanel.records_members;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class viewMembers_InfoPage extends JFrame {
    private JPanel topPanel;
    private static viewMembers_InfoPage instance;
    private DefaultTableModel tableModel;
    private JTextField fullName, pwdIdNum, disabilityType, idValidity, birthday, age, placeOfBirth, educationLevel, occupation, address, mobileNum,
            fbName, email, guardianName, guardianRelation, guardianMobile;
    private JComboBox<String> statusCombo, civilStatusCombo, sexCombo;
    private JCheckBox[] medicalCheckboxes;
    private JTextField otherMedHis;
    private JCheckBox takingMeds, notTakingMeds;
    private JTextField[] medFields;
    private JPanel medsPane;
    private RoundedButton editSaveBtn;
    private boolean isEditing = false;
    private membersDAO.MemberData memberData;
    private JScrollPane scrollPane;
    private int memberId;
    private records_members recordsMembers;
    private homePage homePagePanel;

    public static void launch(membersDAO.MemberData memberData, records_members recordsMembers, homePage homePage) {
        try {
            if (instance == null || !instance.isDisplayable()) {
                instance = new viewMembers_InfoPage(memberData, recordsMembers, homePage);
                instance.setTitle("Membership Forms");
                instance.setResizable(false);
                instance.setLocationRelativeTo(null);
                instance.setVisible(true);
            } else {
                instance.toFront();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public viewMembers_InfoPage(membersDAO.MemberData memberData, records_members recordsMembers, homePage homePage) {
        this.memberId = memberData.memberId;
        this.memberData = memberData;
        this.recordsMembers = recordsMembers;
        this.homePagePanel = homePage;
        setUndecorated(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(750, 670);
        setLayout(new BorderLayout());

        topPanel = new JPanel(null);
        topPanel.setPreferredSize(new Dimension(750, 120));
        add(topPanel, BorderLayout.NORTH);

        JPanel titleBar = new JPanel();
        titleBar.setBackground(new Color(37, 37, 37));
        titleBar.setBounds(0, 0, 750, 30);
        titleBar.setLayout(null);
        topPanel.add(titleBar);

        JLabel titleLabel = new JLabel("Membership Forms");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
        titleLabel.setBounds(10, 0, 200, 30);
        titleBar.add(titleLabel);

        JButton closeButton = new JButton("X");
        closeButton.setBounds(710, 0, 40, 30);
        closeButton.setBackground(new Color(200, 50, 50));
        closeButton.setForeground(Color.WHITE);
        closeButton.setFont(new Font("Arial", Font.BOLD, 14));
        closeButton.setFocusPainted(false);
        closeButton.setMargin(new Insets(0, 0, 0, 0));
        closeButton.setBorderPainted(false);
        closeButton.setContentAreaFilled(true);
        closeButton.addActionListener(e -> {
            dispose();
            if (mainPage.instance != null) {
                mainPage.instance.hideDim();
            }
        });
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
                if (mousePoint[0] != null) {
                    Point currPoint = e.getLocationOnScreen();
                    setLocation(currPoint.x - mousePoint[0].x, currPoint.y - mousePoint[0].y);
                }
            }
        });

        Font headerFont = new Font("Arial", Font.BOLD, 20);
        Font labelFont = new Font("Trebuchet MS", Font.PLAIN, 15);
        Font fieldFont = new Font("Trebuchet MS", Font.PLAIN, 15);
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

        ImageIcon incImg = new ImageIcon("imgs/memberProf.png");
        JLabel incompleteImg = new JLabel(incImg);
        incompleteImg.setBounds(20, 38, 65, 65);
        topPanel.add(incompleteImg);

        fullName = new JTextField(memberData.fullName != null ? memberData.fullName : "");
        fullName.setFont(headerFont);
        fullName.setBorder(null);
        fullName.setEditable(false);
        fullName.setFocusable(false);
        fullName.setOpaque(false);
        fullName.setBounds(105, 38, 400, 26);
        topPanel.add(fullName);

        pwdIdNum = new JTextField(memberData.pwdIdNumber != null ? memberData.pwdIdNumber : "");
        pwdIdNum.setFont(fieldFont);
        pwdIdNum.setBorder(null);
        pwdIdNum.setEditable(false);
        pwdIdNum.setFocusable(false);
        pwdIdNum.setOpaque(false);
        pwdIdNum.setBounds(105, 63, 150, 20);
        topPanel.add(pwdIdNum);

        disabilityType = new JTextField(memberData.disabilityType != null ? memberData.disabilityType : "");
        disabilityType.setFont(fieldFont);
        disabilityType.setBorder(null);
        disabilityType.setEditable(false);
        disabilityType.setFocusable(false);
        disabilityType.setOpaque(false);
        disabilityType.setBounds(105, 82, 150, 20);
        topPanel.add(disabilityType);

        JLabel validDatelbl = new JLabel("ID validity:");
        validDatelbl.setForeground(new Color(100, 100, 100));
        validDatelbl.setFont(labelFont);
        validDatelbl.setBounds(263, 60, 400, 30);
        topPanel.add(validDatelbl);

        idValidity = new JTextField(memberData.idValidUntil != null ? sdf.format(memberData.idValidUntil) : "");
        idValidity.setFont(fieldFont);
        idValidity.setBorder(null);
        idValidity.setEditable(false);
        idValidity.setFocusable(false);
        idValidity.setOpaque(false);
        idValidity.setBounds(263, 77, 150, 33);
        topPanel.add(idValidity);

        JLabel statuslbl = new JLabel("Status:");
        statuslbl.setForeground(new Color(100, 100, 100));
        statuslbl.setFont(labelFont);
        statuslbl.setBounds(358, 60, 400, 30);
        topPanel.add(statuslbl);

        statusCombo = new JComboBox<>(new String[]{"Alive", "Deceased", "Expired", "Renewed"});
        statusCombo.setSelectedItem(memberData.status != null ? memberData.status : "Pending");
        statusCombo.setFont(fieldFont);
        statusCombo.setBounds(356, 84, 95, 18);
        statusCombo.setFocusable(false);
        statusCombo.setEnabled(false);
        statusCombo.setForeground(Color.BLACK);
        statusCombo.setOpaque(true);
        statusCombo.setBorder(null);
        statusCombo.setUI(new CustomComboBoxUI());
        statusCombo.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                          boolean isSelected, boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, false, false);
                label.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
                label.setForeground(Color.BLACK);
                label.setBackground(isSelected ? list.getSelectionBackground() : Color.WHITE);
                return label;
            }
        });
        topPanel.add(statusCombo);

        JLabel expiredLabel = new JLabel();
        expiredLabel.setFont(labelFont);
        expiredLabel.setForeground(Color.RED);
        expiredLabel.setBounds(460, 84, 150, 18);
        topPanel.add(expiredLabel);

        editSaveBtn = new RoundedButton("EDIT 🖊", new Color(180, 180, 180));
        editSaveBtn.setFont(new Font(" ", Font.BOLD, 11));
        editSaveBtn.setBounds(655, 47, 67, 20);
        editSaveBtn.addActionListener(e -> toggleEditSave());
        topPanel.add(editSaveBtn);

        JSeparator separator = new JSeparator();
        separator.setForeground(new Color(100, 100, 100));
        separator.setBounds(15, 115, 710, 2);
        topPanel.add(separator);

        JPanel panel = new JPanel(null);
        panel.setOpaque(true);

        scrollPane = new JScrollPane(panel);
        scrollPane.setBounds(0, 0, 750, 550);
        styleScrollBar(scrollPane);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(null);
        add(scrollPane, BorderLayout.CENTER);

        JLabel basicInformationlbl = new JLabel("\uD83D\uDEC8  BASIC INFORMATION");
        basicInformationlbl.setFont(new Font(" ", Font.BOLD, 20));
        basicInformationlbl.setBounds(25, 0, 400, 30);
        panel.add(basicInformationlbl);

        JLabel birthdaylbl = new JLabel("Birthday");
        birthdaylbl.setForeground(new Color(100, 100, 100));
        birthdaylbl.setFont(labelFont);
        birthdaylbl.setBounds(33, 30, 400, 30);
        panel.add(birthdaylbl);

        birthday = new JTextField(memberData.birthdate != null ? sdf.format(memberData.birthdate) : "");
        birthday.setFont(fieldFont);
        birthday.setBorder(null);
        birthday.setEditable(false);
        birthday.setFocusable(false);
        birthday.setOpaque(false);
        birthday.setBounds(34, 53, 100, 18);
        panel.add(birthday);

        JLabel agelbl = new JLabel("Age");
        agelbl.setForeground(new Color(100, 100, 100));
        agelbl.setFont(labelFont);
        agelbl.setBounds(33, 70, 400, 30);
        panel.add(agelbl);

        age = new JTextField(String.valueOf(memberData.age));
        age.setFont(fieldFont);
        age.setBorder(null);
        age.setEditable(false);
        age.setFocusable(false);
        age.setOpaque(false);
        age.setBounds(34, 94, 100, 18);
        panel.add(age);

        JLabel sexlbl = new JLabel("Sex");
        sexlbl.setForeground(new Color(100, 100, 100));
        sexlbl.setFont(labelFont);
        sexlbl.setBounds(220, 30, 400, 30);
        panel.add(sexlbl);

        sexCombo = new JComboBox<>(new String[]{"Male", "Female", "Other"});
        sexCombo.setSelectedItem(memberData.sex != null ? memberData.sex : "Other");
        sexCombo.setFont(fieldFont);
        sexCombo.setBounds(215, 53, 80, 17);
        sexCombo.setFocusable(false);
        sexCombo.setEnabled(false);
        sexCombo.setForeground(Color.BLACK);
        sexCombo.setOpaque(true);
        sexCombo.setBorder(null);
        sexCombo.setUI(new CustomComboBoxUI());
        sexCombo.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                          boolean isSelected, boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, false, false);
                label.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
                label.setForeground(Color.BLACK);
                label.setBackground(isSelected ? list.getSelectionBackground() : Color.WHITE);
                return label;
            }
        });
        panel.add(sexCombo);

        JLabel civilStatuslbl = new JLabel("Civil Status");
        civilStatuslbl.setForeground(new Color(100, 100, 100));
        civilStatuslbl.setFont(labelFont);
        civilStatuslbl.setBounds(220, 70, 400, 30);
        panel.add(civilStatuslbl);

        civilStatusCombo = new JComboBox<>(new String[]{"Single", "Married", "Widowed", "Separated", "Divorced"});
        civilStatusCombo.setSelectedItem(memberData.civilStatus != null ? memberData.civilStatus : "Single");
        civilStatusCombo.setFont(fieldFont);
        civilStatusCombo.setBounds(215, 93, 100, 18);
        civilStatusCombo.setFocusable(false);
        civilStatusCombo.setEnabled(false);
        civilStatusCombo.setForeground(Color.BLACK);
        civilStatusCombo.setOpaque(true);
        civilStatusCombo.setBorder(null);
        civilStatusCombo.setUI(new CustomComboBoxUI());
        civilStatusCombo.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                          boolean isSelected, boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, false, false);
                label.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
                label.setForeground(Color.BLACK);
                label.setBackground(isSelected ? list.getSelectionBackground() : Color.WHITE);
                return label;
            }
        });
        panel.add(civilStatusCombo);

        JLabel placeOfBirthlbl = new JLabel("Place of Birth");
        placeOfBirthlbl.setForeground(new Color(100, 100, 100));
        placeOfBirthlbl.setFont(labelFont);
        placeOfBirthlbl.setBounds(400, 30, 400, 30);
        panel.add(placeOfBirthlbl);

        placeOfBirth = new JTextField(memberData.placeOfBirth != null ? memberData.placeOfBirth : "");
        placeOfBirth.setFont(fieldFont);
        placeOfBirth.setBorder(null);
        placeOfBirth.setEditable(false);
        placeOfBirth.setFocusable(false);
        placeOfBirth.setOpaque(false);
        placeOfBirth.setBounds(400, 52, 180, 18);
        panel.add(placeOfBirth);

        JLabel educationLevellbl = new JLabel("Education Level");
        educationLevellbl.setForeground(new Color(100, 100, 100));
        educationLevellbl.setFont(labelFont);
        educationLevellbl.setBounds(400, 70, 400, 30);
        panel.add(educationLevellbl);

        educationLevel = new JTextField(memberData.educationLevel != null ? memberData.educationLevel : "");
        educationLevel.setFont(fieldFont);
        educationLevel.setBorder(null);
        educationLevel.setEditable(false);
        educationLevel.setFocusable(false);
        educationLevel.setOpaque(false);
        educationLevel.setBounds(400, 94, 180, 19);
        panel.add(educationLevel);

        JLabel occupationlbl = new JLabel("Occupation");
        occupationlbl.setForeground(new Color(100, 100, 100));
        occupationlbl.setFont(labelFont);
        occupationlbl.setBounds(600, 30, 400, 30);
        panel.add(occupationlbl);

        occupation = new JTextField(memberData.occupation != null ? memberData.occupation : "");
        occupation.setFont(fieldFont);
        occupation.setBorder(null);
        occupation.setEditable(false);
        occupation.setFocusable(false);
        occupation.setOpaque(false);
        occupation.setBounds(600, 52, 120, 18);
        panel.add(occupation);

        JLabel addresslbl = new JLabel("Address");
        addresslbl.setForeground(new Color(100, 100, 100));
        addresslbl.setFont(labelFont);
        addresslbl.setBounds(33, 114, 150, 30);
        panel.add(addresslbl);

        address = new JTextField(memberData.address != null ? memberData.address : "");
        address.setFont(fieldFont);
        address.setBorder(null);
        address.setEditable(false);
        address.setFocusable(false);
        address.setOpaque(false);
        address.setBounds(34, 136, 500, 19);
        panel.add(address);

        JSeparator separator1 = new JSeparator();
        separator1.setForeground(new Color(110, 100, 100));
        separator1.setBounds(15, 167, 710, 2);
        panel.add(separator1);

        JLabel contactInformationlbl = new JLabel("📞 CONTACT INFORMATION");
        contactInformationlbl.setFont(new Font(" ", Font.BOLD, 20));
        contactInformationlbl.setBounds(25, 173, 400, 30);
        panel.add(contactInformationlbl);

        JLabel mobileNumlbl = new JLabel("Mobile Number");
        mobileNumlbl.setForeground(new Color(100, 100, 100));
        mobileNumlbl.setFont(labelFont);
        mobileNumlbl.setBounds(33, 198, 400, 30);
        panel.add(mobileNumlbl);

        mobileNum = new JTextField(memberData.mobileNumber != null ? memberData.mobileNumber : "");
        mobileNum.setFont(fieldFont);
        mobileNum.setBorder(null);
        mobileNum.setEditable(false);
        mobileNum.setFocusable(false);
        mobileNum.setOpaque(false);
        mobileNum.setBounds(34, 220, 290, 19);
        panel.add(mobileNum);

        JLabel fbNamelbl = new JLabel("Facebook Name");
        fbNamelbl.setForeground(new Color(100, 100, 100));
        fbNamelbl.setFont(labelFont);
        fbNamelbl.setBounds(33, 240, 400, 30);
        panel.add(fbNamelbl);

        fbName = new JTextField(memberData.fbAccountName != null ? memberData.fbAccountName : "");
        fbName.setFont(fieldFont);
        fbName.setBorder(null);
        fbName.setEditable(false);
        fbName.setFocusable(false);
        fbName.setOpaque(false);
        fbName.setBounds(33, 262, 290, 19);
        panel.add(fbName);

        JLabel emaillbl = new JLabel("Email");
        emaillbl.setForeground(new Color(100, 100, 100));
        emaillbl.setFont(labelFont);
        emaillbl.setBounds(33, 282, 400, 30);
        panel.add(emaillbl);

        email = new JTextField(memberData.email != null ? memberData.email : "");
        email.setFont(fieldFont);
        email.setBorder(null);
        email.setEditable(false);
        email.setFocusable(false);
        email.setOpaque(false);
        email.setBounds(33, 304, 290, 19);
        panel.add(email);

        JSeparator verticalSeparator = new JSeparator(SwingConstants.VERTICAL);
        verticalSeparator.setForeground(new Color(110, 100, 100));
        verticalSeparator.setBounds(350, 185, 3, 148);
        panel.add(verticalSeparator);

        JLabel emergencyContactlbl = new JLabel("🤳 EMERGENCY CONTACT");
        emergencyContactlbl.setFont(new Font(" ", Font.BOLD, 20));
        emergencyContactlbl.setBounds(375, 173, 400, 30);
        panel.add(emergencyContactlbl);

        JLabel guardianNamelbl = new JLabel("Guardian Name");
        guardianNamelbl.setForeground(new Color(100, 100, 100));
        guardianNamelbl.setFont(labelFont);
        guardianNamelbl.setBounds(376, 198, 400, 30);
        panel.add(guardianNamelbl);

        guardianName = new JTextField(memberData.guardianName != null ? memberData.guardianName : "");
        guardianName.setFont(fieldFont);
        guardianName.setBorder(null);
        guardianName.setEditable(false);
        guardianName.setFocusable(false);
        guardianName.setOpaque(false);
        guardianName.setBounds(376, 220, 300, 19);
        panel.add(guardianName);

        JLabel guardianRelationlbl = new JLabel("Guardian Relation");
        guardianRelationlbl.setForeground(new Color(100, 100, 100));
        guardianRelationlbl.setFont(labelFont);
        guardianRelationlbl.setBounds(376, 240, 400, 30);
        panel.add(guardianRelationlbl);

        guardianRelation = new JTextField(memberData.guardianRelation != null ? memberData.guardianRelation : "");
        guardianRelation.setFont(fieldFont);
        guardianRelation.setBorder(null);
        guardianRelation.setEditable(false);
        guardianRelation.setFocusable(false);
        guardianRelation.setOpaque(false);
        guardianRelation.setBounds(376, 262, 300, 19);
        panel.add(guardianRelation);

        JLabel guardianMobilelbl = new JLabel("Guardian Mobile");
        guardianMobilelbl.setForeground(new Color(100, 100, 100));
        guardianMobilelbl.setFont(labelFont);
        guardianMobilelbl.setBounds(376, 282, 400, 30);
        panel.add(guardianMobilelbl);

        guardianMobile = new JTextField(memberData.guardianMobile != null ? memberData.guardianMobile : "");
        guardianMobile.setFont(fieldFont);
        guardianMobile.setBorder(null);
        guardianMobile.setEditable(false);
        guardianMobile.setFocusable(false);
        guardianMobile.setOpaque(false);
        guardianMobile.setBounds(376, 304, 300, 19);
        panel.add(guardianMobile);

        JSeparator separator2 = new JSeparator();
        separator2.setForeground(new Color(110, 100, 100));
        separator2.setBounds(15, 343, 710, 2);
        panel.add(separator2);

        JLabel householdlbl = new JLabel("🏠 HOUSEHOLD MEMBERS");
        householdlbl.setFont(new Font(" ", Font.BOLD, 20));
        householdlbl.setBounds(25, 350, 400, 30);
        panel.add(householdlbl);

        String[] columnNames = {"Name", "Relationship", "Age", "Birthdate", "Civil Status", "Education", "Occupation"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return isEditing;
            }
        };
        JTable displayTable = new JTable(tableModel);
        displayTable.setFont(new Font("Roboto Mono", Font.PLAIN, 14));
        displayTable.setRowHeight(41);
        displayTable.setShowGrid(true);
        displayTable.setGridColor(new Color(80, 80, 80));
        displayTable.setIntercellSpacing(new Dimension(0, 0));
        displayTable.setRowMargin(0);
        displayTable.setEnabled(false);

        if (memberData.householdMembers != null && !memberData.householdMembers.isEmpty()) {
            for (Map<String, Object> household : memberData.householdMembers) {
                tableModel.addRow(new Object[]{
                        household.get("name") != null ? household.get("name") : "",
                        household.get("relationship") != null ? household.get("relationship") : "",
                        household.get("age") != null ? household.get("age") : "",
                        household.get("birthdate") != null ? sdf.format(household.get("birthdate")) : "",
                        household.get("civilStatus") != null ? household.get("civilStatus") : "",
                        household.get("education") != null ? household.get("education") : "",
                        household.get("occupation") != null ? household.get("occupation") : ""
                });
            }
        }

        JTableHeader header = displayTable.getTableHeader();
        header.setOpaque(false);
        header.setBackground(new Color(0, 0, 0, 0));
        header.setForeground(Color.BLACK);
        header.setFont(new Font("Roboto Mono", Font.BOLD, 16));
        header.setBorder(null);

        header.setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                           boolean hasFocus, int row, int column) {
                JLabel label = new JLabel(value.toString());
                label.setOpaque(false);
                label.setForeground(Color.BLACK);
                label.setFont(new Font("Roboto Mono", Font.BOLD, 15));
                label.setHorizontalAlignment(JLabel.CENTER);
                label.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK),
                        BorderFactory.createEmptyBorder(1, 2, 0, 2)
                ));
                return label;
            }
        });

        class WrappingCellRenderer extends DefaultTableCellRenderer {
            private final JTextArea textArea;

            public WrappingCellRenderer() {
                textArea = new JTextArea();
                textArea.setFont(new Font("Roboto Mono", Font.PLAIN, 14));
                textArea.setLineWrap(true);
                textArea.setWrapStyleWord(true);
                textArea.setOpaque(false);
                textArea.setBorder(BorderFactory.createEmptyBorder(2, 5, 5, 5));
            }

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                textArea.setText(value != null ? value.toString() : "");
                if (isSelected) {
                    textArea.setOpaque(false);
                    textArea.setBackground(table.getSelectionBackground());
                    textArea.setForeground(table.getSelectionForeground());
                } else {
                    textArea.setOpaque(false);
                    textArea.setForeground(table.getForeground());
                }
                return textArea;
            }
        }

        for (int i = 0; i < displayTable.getColumnCount(); i++) {
            displayTable.getColumnModel().getColumn(i).setCellRenderer(new WrappingCellRenderer());
        }

        displayTable.getColumnModel().getColumn(0).setPreferredWidth(100);
        displayTable.getColumnModel().getColumn(1).setPreferredWidth(100);
        displayTable.getColumnModel().getColumn(2).setPreferredWidth(50);
        displayTable.getColumnModel().getColumn(3).setPreferredWidth(90);
        displayTable.getColumnModel().getColumn(4).setPreferredWidth(90);
        displayTable.getColumnModel().getColumn(5).setPreferredWidth(120);
        displayTable.getColumnModel().getColumn(6).setPreferredWidth(100);

        JScrollPane tableScrollPane = new JScrollPane(displayTable);
        tableScrollPane.setBounds(30, 390, 690, 109);
        tableScrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        styleScrollBar(tableScrollPane);
        panel.add(tableScrollPane);

        JSeparator separator3 = new JSeparator();
        separator3.setForeground(new Color(100, 100, 100));
        separator3.setBounds(15, 529, 710, 2);
        panel.add(separator3);

        JLabel medlbl = new JLabel("⛑️ MEDICAL INFORMATION");
        medlbl.setFont(new Font("SansSerif", Font.BOLD, 20));
        medlbl.setBounds(25, 535, 400, 30);
        panel.add(medlbl);

        JLabel conditionslbl = new JLabel("Condition(s)");
        conditionslbl.setForeground(new Color(100, 100, 100));
        conditionslbl.setFont(labelFont);
        conditionslbl.setBounds(30, 563, 400, 25);
        panel.add(conditionslbl);

        JCheckBox diabetes = new JCheckBox("Diabetes");
        JCheckBox stroke = new JCheckBox("Stroke");
        JCheckBox heartProb = new JCheckBox("Heart Problems");
        JCheckBox cancer = new JCheckBox("Cancer");
        JCheckBox highBlood = new JCheckBox("High Blood Pressure");
        JCheckBox lungProb = new JCheckBox("Lung Problems");
        JCheckBox arthritis = new JCheckBox("Arthritis");
        JCheckBox osteoporosis = new JCheckBox("Osteoporosis");
        JCheckBox epilepsy = new JCheckBox("Epilepsy");
        JCheckBox kidneyProb = new JCheckBox("Kidney Problems");

        medicalCheckboxes = new JCheckBox[]{diabetes, stroke, heartProb, cancer, highBlood, lungProb, arthritis, osteoporosis, epilepsy, kidneyProb};
        for (JCheckBox cb : medicalCheckboxes) {
            cb.setFont(labelFont);
            cb.setOpaque(false);
            cb.setFocusPainted(false);
            cb.setEnabled(false);
            cb.setUI(new MinimalCheckBoxUI());
        }

        if (memberData.medicalConditions != null) {
            diabetes.setSelected(memberData.medicalConditions.getOrDefault("diabetes", false));
            stroke.setSelected(memberData.medicalConditions.getOrDefault("stroke", false));
            heartProb.setSelected(memberData.medicalConditions.getOrDefault("heart_problems", false));
            cancer.setSelected(memberData.medicalConditions.getOrDefault("cancer", false));
            highBlood.setSelected(memberData.medicalConditions.getOrDefault("high_blood", false));
            lungProb.setSelected(memberData.medicalConditions.getOrDefault("lung_problems", false));
            arthritis.setSelected(memberData.medicalConditions.getOrDefault("arthritis", false));
            osteoporosis.setSelected(memberData.medicalConditions.getOrDefault("osteoporosis", false));
            epilepsy.setSelected(memberData.medicalConditions.getOrDefault("epilepsy", false));
            kidneyProb.setSelected(memberData.medicalConditions.getOrDefault("kidney_problems", false));
        }

        diabetes.setBounds(30, 585, 150, 25);
        stroke.setBounds(30, 610, 150, 25);
        heartProb.setBounds(30, 635, 180, 25);
        cancer.setBounds(30, 660, 150, 25);
        arthritis.setBounds(30, 685, 150, 25);
        highBlood.setBounds(350, 585, 180, 25);
        lungProb.setBounds(350, 610, 180, 25);
        osteoporosis.setBounds(350, 635, 180, 25);
        epilepsy.setBounds(350, 660, 150, 25);
        kidneyProb.setBounds(350, 685, 180, 25);

        for (JCheckBox cb : medicalCheckboxes) {
            panel.add(cb);
        }

        JLabel otherLabel = new JLabel("Others (Please Specify):");
        otherLabel.setFont(labelFont);
        otherLabel.setBounds(30, 710, 200, 30);
        panel.add(otherLabel);

        otherMedHis = new JTextField(memberData.otherConditions != null ? memberData.otherConditions : "");
        otherMedHis.setFont(fieldFont);
        otherMedHis.setBounds(30, 740, 680, 30);
        otherMedHis.setBorder(new LineBorder(Color.BLACK));
        otherMedHis.setEditable(false);
        otherMedHis.setFocusable(false);
        panel.add(otherMedHis);

        JLabel medicationslbl = new JLabel("Medications");
        medicationslbl.setForeground(new Color(100, 100, 100));
        medicationslbl.setFont(labelFont);
        medicationslbl.setBounds(30, 780, 400, 30);
        panel.add(medicationslbl);

        JLabel lblTakingMeds = new JLabel("Do You Take Any Prescribed Medications:");
        lblTakingMeds.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
        lblTakingMeds.setBounds(30, 810, 400, 30);
        panel.add(lblTakingMeds);

        takingMeds = new JCheckBox("Yes");
        notTakingMeds = new JCheckBox("No");
        ButtonGroup checkBoxGroup = new ButtonGroup();
        checkBoxGroup.add(takingMeds);
        checkBoxGroup.add(notTakingMeds);

        takingMeds.setFont(fieldFont);
        takingMeds.setOpaque(false);
        takingMeds.setFocusPainted(false);
        takingMeds.setUI(new MinimalCheckBoxUI());
        takingMeds.setEnabled(false);
        notTakingMeds.setFont(fieldFont);
        notTakingMeds.setOpaque(false);
        notTakingMeds.setFocusPainted(false);
        notTakingMeds.setUI(new MinimalCheckBoxUI());
        notTakingMeds.setEnabled(false);

        takingMeds.setSelected(memberData.takesMedications);
        notTakingMeds.setSelected(!memberData.takesMedications);

        takingMeds.setBounds(380, 810, 70, 30);
        notTakingMeds.setBounds(460, 810, 100, 30);

        panel.add(takingMeds);
        panel.add(notTakingMeds);

        JLabel lblMedsHeader = new JLabel("List All Medications You Are Currently Taking:");
        lblMedsHeader.setFont(new Font("Trebuchet MS", Font.ITALIC, 16));
        lblMedsHeader.setBounds(30, 840, 450, 30);
        lblMedsHeader.setVisible(takingMeds.isSelected());
        panel.add(lblMedsHeader);

        medsPane = new JPanel(null);
        int medCount = memberData.medications != null ? memberData.medications.size() : 0;
        int displayCount = isEditing ? 15 : Math.max(1, medCount);
        int medPanelHeight = displayCount * 35 + 20;
        medsPane.setPreferredSize(new Dimension(660, medPanelHeight));
        JScrollPane medScrollPane = new JScrollPane(medsPane);
        medScrollPane.setBounds(30, 870, 690, Math.min(200, medPanelHeight));
        medScrollPane.setBorder(null);
        medScrollPane.setOpaque(false);
        medScrollPane.getViewport().setOpaque(false);
        medScrollPane.setVisible(takingMeds.isSelected());
        medScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        styleScrollBar(medScrollPane);
        panel.add(medScrollPane);

        medFields = new JTextField[15];
        for (int i = 0; i < medFields.length; i++) {
            medFields[i] = new JTextField();
            medFields[i].setFont(fieldFont);
            medFields[i].setBounds(40, 15 + i * 35, 610, 25);
            medFields[i].setBorder(null);
            medFields[i].setEditable(false);
            medFields[i].setFocusable(false);
            medFields[i].setBackground(null);
            if (memberData.medications != null && i < memberData.medications.size()) {
                medFields[i].setText(memberData.medications.get(i));
            }
            if (i < displayCount) {
                JLabel medNum = new JLabel((i + 1) + ".");
                medNum.setFont(fieldFont);
                medNum.setBounds(10, 15 + i * 35, 30, 25);
                medsPane.add(medNum);
                medsPane.add(medFields[i]);
            }
        }

        ItemListener visibilityListener = e -> {
            boolean isTakingMeds = takingMeds.isSelected();
            lblMedsHeader.setVisible(isTakingMeds);
            medScrollPane.setVisible(isTakingMeds);
            adjustPanelAndFrameHeight();
            panel.revalidate();
            panel.repaint();
        };
        takingMeds.addItemListener(visibilityListener);
        notTakingMeds.addItemListener(visibilityListener);

        adjustPanelAndFrameHeight();

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                mainPage.instance.removeOpenMemberPage(viewMembers_InfoPage.this);
            }
        });
        mainPage.instance.addOpenMemberPage(this);
    }

    private void adjustPanelAndFrameHeight() {
        int displayCount = isEditing ? 15 : (memberData.medications != null ? memberData.medications.size() : 0);
        displayCount = Math.max(displayCount, 1);
        int medPanelHeight = displayCount * 35 + 20;
        medsPane.setPreferredSize(new Dimension(660, medPanelHeight));

        JScrollPane medScrollPane = (JScrollPane) medsPane.getParent().getParent();
        int scrollPaneHeight = Math.min(medPanelHeight, 200);
        medScrollPane.setPreferredSize(new Dimension(690, scrollPaneHeight));
        medScrollPane.setBounds(30, 870, 690, scrollPaneHeight);

        int medicalSectionBottom = takingMeds.isSelected() ? (870 + scrollPaneHeight + 20) : 850;
        int newPanelHeight = medicalSectionBottom + 20;
        JPanel panel = (JPanel) scrollPane.getViewport().getView();
        panel.setPreferredSize(new Dimension(750, newPanelHeight));

        int maxScrollHeight = 550;
        int scrollHeight = Math.min(newPanelHeight, maxScrollHeight);
        scrollPane.setPreferredSize(new Dimension(750, scrollHeight));
        scrollPane.setBounds(0, 120, 750, scrollHeight);

        int frameHeight = topPanel.getPreferredSize().height + scrollHeight;
        setSize(750, frameHeight);
        revalidate();
        repaint();
    }

    private void toggleEditSave() {
        if (!isEditing) {
            isEditing = true;
            editSaveBtn.setText("SAVE +");
            editSaveBtn.setBackgroundColor(new Color(255, 228, 113));
            editSaveBtn.setBorderColor(null);
            editSaveBtn.repaint();

            JTextField[] fields = {fullName, pwdIdNum, disabilityType, birthday, age,
                    placeOfBirth, educationLevel, occupation, address, mobileNum, fbName,
                    email, guardianName, guardianRelation, guardianMobile, otherMedHis};
            for (JTextField field : fields) {
                field.setEditable(true);
                field.setFocusable(true);
                field.setOpaque(true);
                field.setBorder(new LineBorder(Color.BLACK));
            }

            statusCombo.setEnabled(true);
            statusCombo.setFocusable(true);
            statusCombo.setForeground(Color.BLACK);
            statusCombo.setOpaque(true);
            statusCombo.setBackground(Color.WHITE);
            statusCombo.setBorder(new LineBorder(Color.BLACK));
            sexCombo.setEnabled(true);
            sexCombo.setFocusable(true);
            sexCombo.setForeground(Color.BLACK);
            sexCombo.setOpaque(true);
            sexCombo.setBackground(Color.WHITE);
            sexCombo.setBorder(new LineBorder(Color.BLACK));
            civilStatusCombo.setEnabled(true);
            civilStatusCombo.setFocusable(true);
            civilStatusCombo.setForeground(Color.BLACK);
            civilStatusCombo.setOpaque(true);
            civilStatusCombo.setBackground(Color.WHITE);
            civilStatusCombo.setBorder(new LineBorder(Color.BLACK));

            JTable table = findHouseholdTable();
            if (table != null) {
                table.setEnabled(true);
            }

            for (JCheckBox cb : medicalCheckboxes) {
                cb.setEnabled(true);
            }

            takingMeds.setEnabled(true);
            notTakingMeds.setEnabled(true);

            medsPane.removeAll();
            for (int i = 0; i < medFields.length; i++) {
                JLabel medNum = new JLabel((i + 1) + ".");
                medNum.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
                medNum.setBounds(10, 15 + i * 35, 30, 25);
                medsPane.add(medNum);

                medFields[i].setEditable(true);
                medFields[i].setFocusable(true);
                medFields[i].setBorder(new LineBorder(Color.GRAY));
                medsPane.add(medFields[i]);
            }
            adjustPanelAndFrameHeight();
            medsPane.revalidate();
            medsPane.repaint();

            revalidate();
            repaint();

        } else {
            String errorMessage = validateAllFields();
            if (!errorMessage.isEmpty()) {
                JOptionPane.showMessageDialog(this, errorMessage, "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                saveChanges();
                isEditing = false;
                editSaveBtn.setText("EDIT 🖊");
                editSaveBtn.setBackgroundColor(new Color(180, 180, 180));
                editSaveBtn.repaint();

                JTextField[] fields = {fullName, pwdIdNum, disabilityType, birthday, age,
                        placeOfBirth, educationLevel, occupation, address, mobileNum, fbName,
                        email, guardianName, guardianRelation, guardianMobile, otherMedHis};
                for (JTextField field : fields) {
                    field.setEditable(false);
                    field.setFocusable(false);
                    field.setBorder(null);
                }

                statusCombo.setEnabled(false);
                statusCombo.setFocusable(false);
                statusCombo.setForeground(Color.BLACK);
                sexCombo.setEnabled(false);
                sexCombo.setFocusable(false);
                sexCombo.setForeground(Color.BLACK);
                civilStatusCombo.setEnabled(false);
                civilStatusCombo.setFocusable(false);
                civilStatusCombo.setForeground(Color.BLACK);

                JTable table = findHouseholdTable();
                if (table != null) {
                    table.setEnabled(false);
                }

                for (JCheckBox cb : medicalCheckboxes) {
                    cb.setEnabled(false);
                }

                takingMeds.setEnabled(false);
                notTakingMeds.setEnabled(false);

                medsPane.removeAll();
                int medCount = memberData.medications != null ? memberData.medications.size() : 0;
                int displayCount = Math.max(1, medCount);
                for (int i = 0; i < displayCount; i++) {
                    JLabel medNum = new JLabel((i + 1) + ".");
                    medNum.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
                    medNum.setBounds(10, 15 + i * 35, 30, 25);
                    medsPane.add(medNum);

                    medFields[i].setEditable(false);
                    medFields[i].setFocusable(false);
                    medFields[i].setBorder(null);
                    medsPane.add(medFields[i]);
                }
                adjustPanelAndFrameHeight();
                medsPane.revalidate();
                medsPane.repaint();

                revalidate();
                repaint();

                JOptionPane.showMessageDialog(this, "Changes saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

                if (recordsMembers != null) {
                    recordsMembers.loadMembers();
                }

                if (homePagePanel != null) {
                    homePagePanel.reloadData();
                }

                if (mainPage.instance != null) {
                    mainPage.instance.refreshAttendancePage();
                }
            } catch (SQLException | ParseException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private String validateAllFields() {
        StringBuilder errors = new StringBuilder();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

        if (fullName.getText().trim().isEmpty()) errors.append("Full Name is required.\n");
        if (pwdIdNum.getText().trim().isEmpty()) errors.append("PWD ID Number is required.\n");
        if (disabilityType.getText().trim().isEmpty()) errors.append("Type of Disability is required.\n");
        if (statusCombo.getSelectedItem() == null) errors.append("Status is required.\n");
        if (birthday.getText().trim().isEmpty()) {
            errors.append("Birthday is required.\n");
        } else if (!isValidDate(birthday.getText().trim())) {
            errors.append("Birthday must be in MM/DD/YYYY format.\n");
        }
        if (age.getText().trim().isEmpty()) {
            errors.append("Age is required.\n");
        } else {
            try {
                Integer.parseInt(age.getText().trim());
            } catch (NumberFormatException e) {
                errors.append("Age must be a valid number.\n");
            }
        }
        if (sexCombo.getSelectedItem() == null) errors.append("Sex is required.\n");
        if (civilStatusCombo.getSelectedItem() == null) errors.append("Civil Status is required.\n");
        if (address.getText().trim().isEmpty()) errors.append("Address is required.\n");
        if (mobileNum.getText().trim().isEmpty()) errors.append("Mobile Number is required.\n");
        if (guardianName.getText().trim().isEmpty()) errors.append("Guardian Name is required.\n");
        if (guardianRelation.getText().trim().isEmpty()) errors.append("Guardian Relation is required.\n");
        if (guardianMobile.getText().trim().isEmpty()) errors.append("Guardian Mobile Number is required.\n");
        if (!takingMeds.isSelected() && !notTakingMeds.isSelected()) {
            errors.append("Please indicate whether you take prescribed medications (select 'Yes' or 'No').\n");
        }

        return errors.toString();
    }

    private void saveChanges() throws SQLException, ParseException {
        try (Connection conn = database.getConnection()) {
            membersDAO membersDAO = new membersDAO(conn);
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

            String fullNameText = fullName.getText().trim();
            String pwdIdText = pwdIdNum.getText().trim();
            String disabilityText = disabilityType.getText().trim();
            String statusText = statusCombo.getSelectedItem().toString();
            Date birthdateDate = new Date(sdf.parse(birthday.getText().trim()).getTime());
            int ageValue = Integer.parseInt(age.getText().trim());
            String sexText = sexCombo.getSelectedItem().toString();
            String civilStatusText = civilStatusCombo.getSelectedItem().toString();
            String placeOfBirthText = placeOfBirth.getText().trim();
            String educationLevelText = educationLevel.getText().trim();
            String occupationText = occupation.getText().trim();
            String addressText = address.getText().trim();
            String mobileNumberText = mobileNum.getText().trim();
            String emailText = email.getText().trim();
            String fbAccountText = fbName.getText().trim();
            String guardianNameText = guardianName.getText().trim();
            String guardianRelationText = guardianRelation.getText().trim();
            String guardianMobileText = guardianMobile.getText().trim();
            boolean takesMedications = takingMeds.isSelected();

            List<Map<String, Object>> householdMembers = new ArrayList<>();
            for (int i = 0; i < tableModel.getRowCount(); i++) {
                Map<String, Object> member = new HashMap<>();
                member.put("name", tableModel.getValueAt(i, 0).toString().trim());
                member.put("relationship", tableModel.getValueAt(i, 1).toString().trim());
                String ageStr = tableModel.getValueAt(i, 2).toString().trim();
                member.put("age", ageStr.isEmpty() ? null : ageStr);
                String birthdateStr = tableModel.getValueAt(i, 3).toString().trim();
                if (!birthdateStr.isEmpty()) {
                    try {
                        java.util.Date utilDate = sdf.parse(birthdateStr);
                        member.put("birthdate", new java.sql.Date(utilDate.getTime()));
                    } catch (ParseException e) {
                        member.put("birthdate", null);
                    }
                } else {
                    member.put("birthdate", null);
                }
                member.put("civilStatus", tableModel.getValueAt(i, 4).toString().trim());
                member.put("education", tableModel.getValueAt(i, 5).toString().trim());
                member.put("occupation", tableModel.getValueAt(i, 6).toString().trim());
                householdMembers.add(member);
            }

            Map<String, Boolean> medicalConditions = new HashMap<>();
            medicalConditions.put("diabetes", medicalCheckboxes[0].isSelected());
            medicalConditions.put("stroke", medicalCheckboxes[1].isSelected());
            medicalConditions.put("heart_problems", medicalCheckboxes[2].isSelected());
            medicalConditions.put("cancer", medicalCheckboxes[3].isSelected());
            medicalConditions.put("high_blood", medicalCheckboxes[4].isSelected());
            medicalConditions.put("lung_problems", medicalCheckboxes[5].isSelected());
            medicalConditions.put("arthritis", medicalCheckboxes[6].isSelected());
            medicalConditions.put("osteoporosis", medicalCheckboxes[7].isSelected());
            medicalConditions.put("epilepsy", medicalCheckboxes[8].isSelected());
            medicalConditions.put("kidney_problems", medicalCheckboxes[9].isSelected());
            String otherConditionsText = otherMedHis.getText().trim();

            List<String> medications = new ArrayList<>();
            if (takesMedications) {
                for (JTextField medField : medFields) {
                    String medText = medField.getText().trim();
                    if (!medText.isEmpty()) {
                        medications.add(medText);
                    }
                }
            }

            boolean success = membersDAO.updateMember(
                    memberData.memberId,
                    fullName.getText().trim(),
                    pwdIdNum.getText().trim(),
                    disabilityType.getText().trim(),
                    statusCombo.getSelectedItem().toString(),
                    memberData.dateIssued,
                    memberData.idValidUntil,
                    new Date(sdf.parse(birthday.getText().trim()).getTime()),
                    Integer.parseInt(age.getText().trim()),
                    sexCombo.getSelectedItem().toString(),
                    civilStatusCombo.getSelectedItem().toString(),
                    placeOfBirth.getText().trim(),
                    educationLevel.getText().trim(),
                    occupation.getText().trim(),
                    address.getText().trim(),
                    mobileNum.getText().trim(),
                    email.getText().trim(),
                    fbName.getText().trim(),
                    guardianName.getText().trim(),
                    guardianRelation.getText().trim(),
                    guardianMobile.getText().trim(),
                    takingMeds.isSelected(),
                    householdMembers,
                    medicalConditions,
                    otherMedHis.getText().trim(),
                    medications
            );

            if (!success) {
                throw new SQLException("Failed to update member.");
            }

            memberData.fullName = fullNameText;
            memberData.pwdIdNumber = pwdIdText;
            memberData.disabilityType = disabilityText;
            memberData.status = statusText;
            memberData.birthdate = birthdateDate;
            memberData.age = ageValue;
            memberData.sex = sexText;
            memberData.civilStatus = civilStatusText;
            memberData.placeOfBirth = placeOfBirthText;
            memberData.educationLevel = educationLevelText;
            memberData.occupation = occupationText;
            memberData.address = addressText;
            memberData.mobileNumber = mobileNumberText;
            memberData.email = emailText;
            memberData.fbAccountName = fbAccountText;
            memberData.guardianName = guardianNameText;
            memberData.guardianRelation = guardianRelationText;
            memberData.guardianMobile = guardianMobileText;
            memberData.takesMedications = takesMedications;
            memberData.householdMembers = householdMembers;
            memberData.medicalConditions = medicalConditions;
            memberData.otherConditions = otherConditionsText;
            memberData.medications = medications;

            adjustPanelAndFrameHeight();
        }
    }

    private boolean isValidDate(String date) {
        if (date == null || !date.matches("\\d{2}/\\d{2}/\\d{4}")) return false;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
            sdf.setLenient(false);
            sdf.parse(date);
            String[] parts = date.split("/");
            int month = Integer.parseInt(parts[0]);
            int day = Integer.parseInt(parts[1]);
            int year = Integer.parseInt(parts[2]);
            return !(month < 1 || month > 12 || day < 1 || day > 31 || year < 1900 || year > 2099);
        } catch (Exception e) {
            return false;
        }
    }

    public int getMemberId() {
        return memberId;
    }

    public void reloadData() {
        try (Connection conn = database.getConnection()) {
            membersDAO membersDAO = new membersDAO(conn);
            List<membersDAO.MemberData> members = membersDAO.getMembers();
            for (membersDAO.MemberData member : members) {
                if (member.memberId == memberId) {
                    this.memberData = member;
                    updateUIWithMemberData();
                    break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error reloading member data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateUIWithMemberData() {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        fullName.setText(memberData.fullName != null ? memberData.fullName : "");
        pwdIdNum.setText(memberData.pwdIdNumber != null ? memberData.pwdIdNumber : "");
        disabilityType.setText(memberData.disabilityType != null ? memberData.disabilityType : "");
        idValidity.setText(memberData.idValidUntil != null ? sdf.format(memberData.idValidUntil) : "");
        statusCombo.setSelectedItem(memberData.status != null ? memberData.status : "Pending");
        birthday.setText(memberData.birthdate != null ? sdf.format(memberData.birthdate) : "");
        age.setText(String.valueOf(memberData.age));
        sexCombo.setSelectedItem(memberData.sex != null ? memberData.sex : "Other");
        civilStatusCombo.setSelectedItem(memberData.civilStatus != null ? memberData.civilStatus : "Single");
        placeOfBirth.setText(memberData.placeOfBirth != null ? memberData.placeOfBirth : "");
        educationLevel.setText(memberData.educationLevel != null ? memberData.educationLevel : "");
        occupation.setText(memberData.occupation != null ? memberData.occupation : "");
        address.setText(memberData.address != null ? memberData.address : "");
        mobileNum.setText(memberData.mobileNumber != null ? memberData.mobileNumber : "");
        fbName.setText(memberData.fbAccountName != null ? memberData.fbAccountName : "");
        email.setText(memberData.email != null ? memberData.email : "");
        guardianName.setText(memberData.guardianName != null ? memberData.guardianName : "");
        guardianRelation.setText(memberData.guardianRelation != null ? memberData.guardianRelation : "");
        guardianMobile.setText(memberData.guardianMobile != null ? memberData.guardianMobile : "");

        tableModel.setRowCount(0);
        if (memberData.householdMembers != null) {
            for (Map<String, Object> household : memberData.householdMembers) {
                tableModel.addRow(new Object[]{
                        household.get("name") != null ? household.get("name") : "",
                        household.get("relationship") != null ? household.get("relationship") : "",
                        household.get("age") != null ? household.get("age") : "",
                        household.get("birthdate") != null ? sdf.format(household.get("birthdate")) : "",
                        household.get("civilStatus") != null ? household.get("civilStatus") : "",
                        household.get("education") != null ? household.get("education") : "",
                        household.get("occupation") != null ? household.get("occupation") : ""
                });
            }
        }

        if (memberData.medicalConditions != null) {
            medicalCheckboxes[0].setSelected(memberData.medicalConditions.getOrDefault("diabetes", false));
            medicalCheckboxes[1].setSelected(memberData.medicalConditions.getOrDefault("stroke", false));
            medicalCheckboxes[2].setSelected(memberData.medicalConditions.getOrDefault("heart_problems", false));
            medicalCheckboxes[3].setSelected(memberData.medicalConditions.getOrDefault("cancer", false));
            medicalCheckboxes[4].setSelected(memberData.medicalConditions.getOrDefault("high_blood", false));
            medicalCheckboxes[5].setSelected(memberData.medicalConditions.getOrDefault("lung_problems", false));
            medicalCheckboxes[6].setSelected(memberData.medicalConditions.getOrDefault("arthritis", false));
            medicalCheckboxes[7].setSelected(memberData.medicalConditions.getOrDefault("osteoporosis", false));
            medicalCheckboxes[8].setSelected(memberData.medicalConditions.getOrDefault("epilepsy", false));
            medicalCheckboxes[9].setSelected(memberData.medicalConditions.getOrDefault("kidney_problems", false));
        }
        otherMedHis.setText(memberData.otherConditions != null ? memberData.otherConditions : "");

        takingMeds.setSelected(memberData.takesMedications);
        notTakingMeds.setSelected(!memberData.takesMedications);
        medsPane.removeAll();
        int medCount = memberData.medications != null ? memberData.medications.size() : 0;
        int displayCount = isEditing ? 15 : Math.max(1, medCount);
        for (int i = 0; i < displayCount; i++) {
            JLabel medNum = new JLabel((i + 1) + ".");
            medNum.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
            medNum.setBounds(10, 15 + i * 35, 30, 25);
            medsPane.add(medNum);

            medFields[i].setText((memberData.medications != null && i < memberData.medications.size()) ? memberData.medications.get(i) : "");
            if (!isEditing) {
                medFields[i].setBorder(null);
                medFields[i].setEditable(false);
                medFields[i].setFocusable(false);
            }
            medsPane.add(medFields[i]);
        }
        adjustPanelAndFrameHeight();
        revalidate();
        repaint();
    }

    private void styleScrollBar(JScrollPane scrollPane) {
        JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
        verticalScrollBar.setUI(new javax.swing.plaf.basic.BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = new Color(150, 150, 150);
                this.thumbDarkShadowColor = new Color(150, 150, 150);
                this.thumbHighlightColor = new Color(150, 150, 150);
                this.thumbLightShadowColor = new Color(150, 150, 150);
                this.trackColor = new Color(245, 245, 245);
            }

            @Override
            protected JButton createDecreaseButton(int orientation) {
                return createZeroButton();
            }

            @Override
            protected JButton createIncreaseButton(int orientation) {
                return createZeroButton();
            }

            private JButton createZeroButton() {
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(0, 0));
                button.setMinimumSize(new Dimension(0, 0));
                button.setMaximumSize(new Dimension(0, 0));
                button.setBorder(BorderFactory.createEmptyBorder());
                button.setContentAreaFilled(false);
                button.setFocusable(false);
                return button;
            }

            @Override
            protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setPaint(thumbColor);
                g2.fillRoundRect(thumbBounds.x, thumbBounds.y, thumbBounds.width, thumbBounds.height, 10, 10);
                g2.dispose();
            }
        });

        verticalScrollBar.setPreferredSize(new Dimension(8, Integer.MAX_VALUE));
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
    }

    @Override
    public void dispose() {
        instance = null;
        super.dispose();
        if (mainPage.instance != null) {
            mainPage.instance.hideDim();
        }
    }

    private static class RoundedButton extends JButton {
        private final int radius = 30;
        private Color bgColor;
        private Color borderColor;

        public RoundedButton(String text, Color bgColor) {
            this(text, bgColor, null);
        }

        public RoundedButton(String text, Color bgColor, Color borderColor) {
            super(text);
            this.bgColor = bgColor;
            this.borderColor = borderColor;
            setFont(new Font("Arial", Font.BOLD, 14));
            setForeground(Color.BLACK);
            setFocusPainted(false);
            setContentAreaFilled(false);
            setOpaque(false);
            setBorderPainted(borderColor != null);
            setMargin(new Insets(5, 10, 5, 10));
        }

        public void setBackgroundColor(Color bgColor) {
            this.bgColor = bgColor;
        }

        public void setBorderColor(Color borderColor) {
            this.borderColor = borderColor;
            setBorderPainted(borderColor != null);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(bgColor);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
            super.paintComponent(g);
            g2.dispose();
        }

        @Override
        protected void paintBorder(Graphics g) {
            if (borderColor != null) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(borderColor);
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);
                g2.dispose();
            }
        }
    }

    private class MinimalCheckBoxUI extends javax.swing.plaf.basic.BasicCheckBoxUI {
        @Override
        public void paint(Graphics g, JComponent c) {
            AbstractButton b = (AbstractButton) c;
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int size = 14;
            int x = 0;
            int y = (b.getHeight() - size) / 2;

            g2.setColor(Color.BLACK);
            g2.setStroke(new BasicStroke(1));
            g2.drawRect(x, y, size, size);

            if (b.isSelected()) {
                g2.setColor(new Color(56, 96, 193));
                g2.setStroke(new BasicStroke(2));
                g2.drawLine(x + 3, y + 6, x + 6, y + 9);
                g2.drawLine(x + 6, y + 9, x + 11, y + 3);
            }

            FontMetrics fm = g2.getFontMetrics();
            String text = b.getText();
            int textX = x + size + 5;
            int textY = (b.getHeight() - fm.getHeight()) / 2 + fm.getAscent();
            g2.setColor(b.getForeground());
            g2.drawString(text, textX, textY);
            g2.dispose();
        }

        @Override
        public Dimension getPreferredSize(JComponent c) {
            AbstractButton b = (AbstractButton) c;
            FontMetrics fm = c.getFontMetrics(c.getFont());
            return new Dimension(14 + 5 + fm.stringWidth(b.getText()), Math.max(14, fm.getHeight()));
        }
    }

    private class CustomComboBoxUI extends BasicComboBoxUI {
        @Override
        protected JButton createArrowButton() {
            return new JButton() {
                @Override
                public void paint(Graphics g) {
                    if (!isEditing) {
                        return;
                    }

                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    g2.setColor(new Color(237, 237, 237));
                    g2.fillRect(0, 0, getWidth(), getHeight());
                    g2.setColor(Color.BLACK);
                    int[] xPoints = {getWidth() / 2 - 5, getWidth() / 2 + 5, getWidth() / 2};
                    int[] yPoints = {getHeight() / 2 - 3, getHeight() / 2 - 3, getHeight() / 2 + 3};
                    g2.fillPolygon(xPoints, yPoints, 3);
                    g2.dispose();
                }

                @Override
                public Dimension getPreferredSize() {
                    return isEditing ? new Dimension(20, comboBox.getHeight()) : new Dimension(0, comboBox.getHeight());
                }
            };
        }

        @Override
        protected void installDefaults() {
            super.installDefaults();
            comboBox.setForeground(Color.BLACK);
            comboBox.setOpaque(true);
        }

        @Override
        public void paintCurrentValue(Graphics g, Rectangle bounds, boolean hasFocus) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            g2.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);

            g2.setColor(Color.BLACK);
            g2.setFont(comboBox.getFont());
            FontMetrics fm = g2.getFontMetrics();
            String text = comboBox.getSelectedItem() != null ? comboBox.getSelectedItem().toString() : "";
            int x = bounds.x + 5;
            int y = bounds.y + fm.getAscent() + (bounds.height - fm.getHeight()) / 2;
            g2.drawString(text, x, y);
            g2.dispose();
        }

        @Override
        public void paintCurrentValueBackground(Graphics g, Rectangle bounds, boolean hasFocus) {
            g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
        }
    }

    private JTable findHouseholdTable() {
        Component view = scrollPane.getViewport().getView();
        if (view instanceof JPanel) {
            JPanel panel = (JPanel) view;
            for (Component comp : panel.getComponents()) {
                if (comp instanceof JScrollPane) {
                    JScrollPane tableScrollPane = (JScrollPane) comp;
                    Component tableView = tableScrollPane.getViewport().getView();
                    if (tableView instanceof JTable) {
                        return (JTable) tableView;
                    }
                }
            }
        }
        return null;
    }
}