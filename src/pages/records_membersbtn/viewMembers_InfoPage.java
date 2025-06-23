package pages.records_membersbtn;

import dal.members.membersDAO;
import pages.mainPage;

import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.text.SimpleDateFormat;
import java.util.Map;

public class viewMembers_InfoPage extends JFrame {
    private static viewMembers_InfoPage instance;
    private DefaultTableModel tableModel;

    public static void launch(membersDAO.MemberData memberData) {
        try {
            if (instance == null || !instance.isDisplayable()) {
                instance = new viewMembers_InfoPage(memberData);
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

    public viewMembers_InfoPage(membersDAO.MemberData memberData) {
        setUndecorated(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(750, 650);
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(null);
        topPanel.setPreferredSize(new Dimension(750, 120));
        add(topPanel, BorderLayout.NORTH);

        // Custom title bar
        JPanel titleBar = new JPanel();
        titleBar.setBackground(new Color(37, 37, 37));
        titleBar.setBounds(0, 0, 750, 30);
        titleBar.setLayout(null);
        topPanel.add(titleBar);

        // Title label
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

        // Allow dragging the window
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

        // Profile image
        ImageIcon incImg = new ImageIcon("imgs/memberProf.png");
        JLabel incompleteImg = new JLabel(incImg);
        incompleteImg.setBounds(20, 40, 65, 65);
        topPanel.add(incompleteImg);

        // Full Name
        JTextField fullName = new JTextField(memberData.fullName != null ? memberData.fullName : "");
        fullName.setFont(headerFont);
        fullName.setBorder(null);
        fullName.setEditable(false);
        fullName.setOpaque(false);
        fullName.setBounds(105, 38, 500, 33);
        topPanel.add(fullName);

        // PWD ID Number
        JTextField pwdIdNum = new JTextField(memberData.pwdIdNumber != null ? memberData.pwdIdNumber : "");
        pwdIdNum.setFont(fieldFont);
        pwdIdNum.setBorder(null);
        pwdIdNum.setEditable(false);
        pwdIdNum.setOpaque(false);
        pwdIdNum.setBounds(107, 59, 190, 33);
        topPanel.add(pwdIdNum);

        // Disability Type
        JTextField disabilityType = new JTextField(memberData.disabilityType != null ? memberData.disabilityType : "");
        disabilityType.setFont(fieldFont);
        disabilityType.setBorder(null);
        disabilityType.setEditable(false);
        disabilityType.setOpaque(false);
        disabilityType.setBounds(107, 76, 290, 33);
        topPanel.add(disabilityType);

        // Status
        JLabel statuslbl = new JLabel("Status:");
        statuslbl.setForeground(new Color(100, 100, 100));
        statuslbl.setFont(labelFont);
        statuslbl.setBounds(260, 60, 400, 30);
        topPanel.add(statuslbl);

        JTextField statusField = new JTextField(memberData.status != null ? memberData.status : "");
        statusField.setFont(fieldFont);
        statusField.setBorder(null);
        statusField.setEditable(false);
        statusField.setOpaque(false);
        statusField.setBounds(260, 77, 95, 33);
        topPanel.add(statusField);

        JSeparator separator = new JSeparator();
        separator.setForeground(new Color(100, 100, 100));
        separator.setBounds(15, 115, 710, 2);
        topPanel.add(separator);

        JPanel panel = new JPanel(null);
        panel.setPreferredSize(new Dimension(750, 780));
        add(panel);

        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setBounds(0, 0, 750, 780);
        styleScrollBar(scrollPane);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(null);

        // Basic Information Section
        JLabel basicInformationlbl = new JLabel("\uD83D\uDEC8  BASIC INFORMATION");
        basicInformationlbl.setFont(new Font(" ", Font.BOLD, 20));
        basicInformationlbl.setBounds(25, 0, 400, 30);
        panel.add(basicInformationlbl);

        JLabel birthdaylbl = new JLabel("Birthday");
        birthdaylbl.setForeground(new Color(100, 100, 100));
        birthdaylbl.setFont(labelFont);
        birthdaylbl.setBounds(33, 30, 400, 30);
        panel.add(birthdaylbl);

        JTextField birthday = new JTextField(memberData.birthdate != null ? sdf.format(memberData.birthdate) : "");
        birthday.setFont(fieldFont);
        birthday.setBorder(null);
        birthday.setEditable(false);
        birthday.setOpaque(false);
        birthday.setBounds(34, 47, 290, 33);
        panel.add(birthday);

        JLabel agelbl = new JLabel("Age");
        agelbl.setForeground(new Color(100, 100, 100));
        agelbl.setFont(labelFont);
        agelbl.setBounds(33, 70, 400, 30);
        panel.add(agelbl);

        JTextField age = new JTextField(String.valueOf(memberData.age));
        age.setFont(fieldFont);
        age.setBorder(null);
        age.setEditable(false);
        age.setOpaque(false);
        age.setBounds(34, 89, 290, 33);
        panel.add(age);

        JLabel sexlbl = new JLabel("Sex");
        sexlbl.setForeground(new Color(100, 100, 100));
        sexlbl.setFont(labelFont);
        sexlbl.setBounds(220, 30, 400, 30);
        panel.add(sexlbl);

        JTextField sex = new JTextField(memberData.sex != null ? memberData.sex : "");
        sex.setFont(fieldFont);
        sex.setBorder(null);
        sex.setEditable(false);
        sex.setOpaque(false);
        sex.setBounds(220, 47, 290, 33);
        panel.add(sex);

        JLabel civilStatuslbl = new JLabel("Civil Status");
        civilStatuslbl.setForeground(new Color(100, 100, 100));
        civilStatuslbl.setFont(labelFont);
        civilStatuslbl.setBounds(220, 70, 400, 30);
        panel.add(civilStatuslbl);

        JTextField civilStatus = new JTextField(memberData.civilStatus != null ? memberData.civilStatus : "");
        civilStatus.setFont(fieldFont);
        civilStatus.setBorder(null);
        civilStatus.setEditable(false);
        civilStatus.setOpaque(false);
        civilStatus.setBounds(220, 89, 290, 33);
        panel.add(civilStatus);

        JLabel placeOfBirthlbl = new JLabel("Place of Birth");
        placeOfBirthlbl.setForeground(new Color(100, 100, 100));
        placeOfBirthlbl.setFont(labelFont);
        placeOfBirthlbl.setBounds(400, 30, 400, 30);
        panel.add(placeOfBirthlbl);

        JTextField placeOfBirth = new JTextField(memberData.placeOfBirth != null ? memberData.placeOfBirth : "");
        placeOfBirth.setFont(fieldFont);
        placeOfBirth.setBorder(null);
        placeOfBirth.setEditable(false);
        placeOfBirth.setOpaque(false);
        placeOfBirth.setBounds(400, 47, 290, 33);
        panel.add(placeOfBirth);

        JLabel educationLevellbl = new JLabel("Education Level");
        educationLevellbl.setForeground(new Color(100, 100, 100));
        educationLevellbl.setFont(labelFont);
        educationLevellbl.setBounds(400, 70, 400, 30);
        panel.add(educationLevellbl);

        JTextField educationLevel = new JTextField(memberData.educationLevel != null ? memberData.educationLevel : "");
        educationLevel.setFont(fieldFont);
        educationLevel.setBorder(null);
        educationLevel.setEditable(false);
        educationLevel.setOpaque(false);
        educationLevel.setBounds(400, 89, 290, 33);
        panel.add(educationLevel);

        JLabel occupationlbl = new JLabel("Occupation");
        occupationlbl.setForeground(new Color(100, 100, 100));
        occupationlbl.setFont(labelFont);
        occupationlbl.setBounds(600, 30, 400, 30);
        panel.add(occupationlbl);

        JTextField occupation = new JTextField(memberData.occupation != null ? memberData.occupation : "");
        occupation.setFont(fieldFont);
        occupation.setBorder(null);
        occupation.setEditable(false);
        occupation.setOpaque(false);
        occupation.setBounds(600, 47, 290, 33);
        panel.add(occupation);

        JSeparator separator1 = new JSeparator();
        separator1.setForeground(new Color(110, 100, 100));
        separator1.setBounds(15, 130, 710, 2);
        panel.add(separator1);

        // Contact Information Section
        JLabel contactInformationlbl = new JLabel("📞 CONTACT INFORMATION");
        contactInformationlbl.setFont(new Font(" ", Font.BOLD, 20));
        contactInformationlbl.setBounds(25, 135, 400, 30);
        panel.add(contactInformationlbl);

        JLabel mobileNumlbl = new JLabel("Mobile Number");
        mobileNumlbl.setForeground(new Color(100, 100, 100));
        mobileNumlbl.setFont(labelFont);
        mobileNumlbl.setBounds(30, 404, 400, 30);
        panel.add(mobileNumlbl);

        JTextField mobileNum = new JTextField(memberData.mobileNumber != null ? memberData.mobileNumber : "");
        mobileNum.setFont(fieldFont);
        mobileNum.setBorder(null);
        mobileNum.setEditable(false);
        mobileNum.setOpaque(false);
        mobileNum.setBounds(30, 421, 290, 33);
        panel.add(mobileNum);

        JLabel addresslbl = new JLabel("Address");
        addresslbl.setForeground(new Color(100, 100, 100));
        addresslbl.setFont(labelFont);
        addresslbl.setBounds(30, 451, 400, 30);
        panel.add(addresslbl);

        JTextField address = new JTextField(memberData.address != null ? memberData.address : "");
        address.setFont(fieldFont);
        address.setBorder(null);
        address.setEditable(false);
        address.setOpaque(false);
        address.setBounds(30, 468, 290, 33);
        panel.add(address);

        JLabel fbNamelbl = new JLabel("Facebook Name");
        fbNamelbl.setForeground(new Color(100, 100, 100));
        fbNamelbl.setFont(labelFont);
        fbNamelbl.setBounds(30, 498, 400, 30);
        panel.add(fbNamelbl);

        JTextField fbName = new JTextField(memberData.fbAccountName != null ? memberData.fbAccountName : "");
        fbName.setFont(fieldFont);
        fbName.setBorder(null);
        fbName.setEditable(false);
        fbName.setOpaque(false);
        fbName.setBounds(30, 515, 290, 33);
        panel.add(fbName);

        JLabel emaillbl = new JLabel("Email");
        emaillbl.setForeground(new Color(100, 100, 100));
        emaillbl.setFont(labelFont);
        emaillbl.setBounds(30, 545, 400, 30);
        panel.add(emaillbl);

        JTextField email = new JTextField(memberData.email != null ? memberData.email : "");
        email.setFont(fieldFont);
        email.setBorder(null);
        email.setEditable(false);
        email.setOpaque(false);
        email.setBounds(30, 562, 290, 33);
        panel.add(email);

        JLabel guardianNamelbl = new JLabel("Guardian Name");
        guardianNamelbl.setForeground(new Color(100, 100, 100));
        guardianNamelbl.setFont(labelFont);
        guardianNamelbl.setBounds(30, 592, 400, 30);
        panel.add(guardianNamelbl);

        JTextField guardianName = new JTextField(memberData.guardianName != null ? memberData.guardianName : "");
        guardianName.setFont(fieldFont);
        guardianName.setBorder(null);
        guardianName.setEditable(false);
        guardianName.setOpaque(false);
        guardianName.setBounds(30, 609, 290, 33);
        panel.add(guardianName);

        JLabel guardianRelationlbl = new JLabel("Guardian Relation");
        guardianRelationlbl.setForeground(new Color(100, 100, 100));
        guardianRelationlbl.setFont(labelFont);
        guardianRelationlbl.setBounds(30, 639, 400, 30);
        panel.add(guardianRelationlbl);

        JTextField guardianRelation = new JTextField(memberData.guardianRelation != null ? memberData.guardianRelation : "");
        guardianRelation.setFont(fieldFont);
        guardianRelation.setBorder(null);
        guardianRelation.setEditable(false);
        guardianRelation.setOpaque(false);
        guardianRelation.setBounds(30, 656, 290, 33);
        panel.add(guardianRelation);

        JLabel guardianMobilelbl = new JLabel("Guardian Mobile");
        guardianMobilelbl.setForeground(new Color(100, 100, 100));
        guardianMobilelbl.setFont(labelFont);
        guardianMobilelbl.setBounds(30, 686, 400, 30);
        panel.add(guardianMobilelbl);

        JTextField guardianMobile = new JTextField(memberData.guardianMobile != null ? memberData.guardianMobile : "");
        guardianMobile.setFont(fieldFont);
        guardianMobile.setBorder(null);
        guardianMobile.setEditable(false);
        guardianMobile.setOpaque(false);
        guardianMobile.setBounds(30, 703, 290, 33);
        panel.add(guardianMobile);

        JSeparator separator2 = new JSeparator();
        separator2.setForeground(new Color(110, 100, 100));
        separator2.setBounds(15, 736, 710, 2);
        panel.add(separator2);

        // Household Members Section
        JLabel householdlbl = new JLabel("🏠 HOUSEHOLD MEMBERS");
        householdlbl.setFont(new Font(" ", Font.BOLD, 20));
        householdlbl.setBounds(25, 746, 400, 30);
        panel.add(householdlbl);

        String[] columnNames = {"Name", "Relationship", "Age", "Birthdate", "Civil Status", "Education", "Occupation"};
        tableModel = new DefaultTableModel(columnNames, 0);
        JTable displayTable = new JTable(tableModel);
        displayTable.setFont(new Font("Roboto Mono", Font.PLAIN, 14));
        displayTable.setRowHeight(41);
        displayTable.setShowGrid(true);
        displayTable.setGridColor(new Color(80, 80, 80));
        displayTable.setIntercellSpacing(new Dimension(2, 2));
        displayTable.setRowMargin(2);
        displayTable.setIntercellSpacing(new Dimension(0, 0));
        displayTable.setRowMargin(0);

        // Populate household members table
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
        tableScrollPane.setBounds(30, 780, 690, 150);
        tableScrollPane.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK, 1),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        styleScrollBar(tableScrollPane);
        tableScrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        panel.add(tableScrollPane);

        JSeparator separator3 = new JSeparator();
        separator3.setForeground(new Color(110, 100, 100));
        separator3.setBounds(15, 940, 710, 2);
        panel.add(separator3);

        // Medical Information Section
        JLabel medlbl = new JLabel("⛑️  MEDICAL INFORMATION");
        medlbl.setFont(new Font(" ", Font.BOLD, 20));
        medlbl.setBounds(25, 950, 400, 30);
        panel.add(medlbl);

        JLabel conditionslbl = new JLabel("Condition(s)");
        conditionslbl.setForeground(new Color(100, 100, 100));
        conditionslbl.setFont(labelFont);
        conditionslbl.setBounds(30, 980, 400, 30);
        panel.add(conditionslbl);

        JPanel conditionPanel = new JPanel(null);
        conditionPanel.setBounds(30, 1008, 690, 61);
        panel.add(conditionPanel);

        // Populate medical conditions
        int conditionY = 0;
        if (memberData.medicalConditions != null && !memberData.medicalConditions.isEmpty()) {
            for (Map.Entry<String, Boolean> condition : memberData.medicalConditions.entrySet()) {
                if (condition.getValue()) {
                    JTextField conditionField = new JTextField("● " + condition.getKey());
                    conditionField.setFont(fieldFont);
                    conditionField.setBorder(null);
                    conditionField.setEditable(false);
                    conditionField.setOpaque(false);
                    conditionField.setBounds(2, conditionY, 290, 18);
                    conditionPanel.add(conditionField);
                    conditionY += 20;
                }
            }
        }
        if (memberData.otherConditions != null && !memberData.otherConditions.isEmpty()) {
            JTextField otherConditionField = new JTextField("● " + memberData.otherConditions);
            otherConditionField.setFont(fieldFont);
            otherConditionField.setBorder(null);
            otherConditionField.setEditable(false);
            otherConditionField.setOpaque(false);
            otherConditionField.setBounds(2, conditionY, 290, 18);
            conditionPanel.add(otherConditionField);
            conditionY += 20;
        }
        if (conditionY == 0) {
            JTextField noConditionField = new JTextField("● None");
            noConditionField.setFont(fieldFont);
            noConditionField.setBorder(null);
            noConditionField.setEditable(false);
            noConditionField.setOpaque(false);
            noConditionField.setBounds(2, 0, 290, 18);
            conditionPanel.add(noConditionField);
        }

        JLabel medicationslbl = new JLabel("Medications");
        medicationslbl.setForeground(new Color(100, 100, 100));
        medicationslbl.setFont(labelFont);
        medicationslbl.setBounds(30, 1078, 400, 30);
        panel.add(medicationslbl);

        JPanel medicationsPanel = new JPanel(null);
        medicationsPanel.setBounds(30, 1108, 690, 61);
        panel.add(medicationsPanel);

        // Populate medications
        int medicationY = 0;
        if (memberData.medications != null && !memberData.medications.isEmpty()) {
            for (String medication : memberData.medications) {
                if (!medication.trim().isEmpty()) {
                    JTextField medicationField = new JTextField("● " + medication);
                    medicationField.setFont(fieldFont);
                    medicationField.setBorder(null);
                    medicationField.setEditable(false);
                    medicationField.setOpaque(false);
                    medicationField.setBounds(2, medicationY, 290, 18);
                    medicationsPanel.add(medicationField);
                    medicationY += 20;
                }
            }
        } else {
            JTextField noMedicationField = new JTextField("● None");
            noMedicationField.setFont(fieldFont);
            noMedicationField.setBorder(null);
            noMedicationField.setEditable(false);
            noMedicationField.setOpaque(false);
            noMedicationField.setBounds(2, 0, 290, 18);
            medicationsPanel.add(noMedicationField);
        }

        add(scrollPane);
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

    static class MinimalComboBoxUI extends BasicComboBoxUI {
        @Override
        protected JButton createArrowButton() {
            JButton button = new JButton() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    g2.setColor(new Color(100, 100, 100));
                    int[] xPoints = {getWidth() - 12, getWidth() - 6, getWidth() - 9};
                    int[] yPoints = {getHeight() / 2 - 2, getHeight() / 2 - 2, getHeight() / 2 + 2};
                    g2.fillPolygon(xPoints, yPoints, 3);
                    g2.dispose();
                }
            };
            button.setBackground(new Color(248, 248, 248));
            button.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 10));
            button.setContentAreaFilled(false);
            button.setFocusPainted(false);
            button.setCursor(new Cursor(Cursor.HAND_CURSOR));
            return button;
        }

        @Override
        protected void installDefaults() {
            super.installDefaults();
            comboBox.setOpaque(true);
        }
    }

    @Override
    public void dispose() {
        instance = null;
        super.dispose();
        if (mainPage.instance != null) {
            mainPage.instance.hideDim();
        }
    }
}