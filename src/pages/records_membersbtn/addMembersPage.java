package pages.records_membersbtn;

import pages.homePage;
import pages.mainPage;
import dal.members.membersDAO;
import db.database;
import pages.recordsPanel.records_members;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.text.Document;
import javax.swing.event.DocumentListener;
import javax.swing.event.DocumentEvent;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class addMembersPage extends JFrame {
    private JPanel mainPanel;
    private CardLayout cardLayout;
    private JPanel page1, page2, page3, page4, page5;
    private JLabel personalInfoLbl, contactLbl, familyComplbl, medicalHistoryLbl, medicationsLbl;
    // Page1 fields
    private JTextField fillUpDate, fullName, pwdIdNo, disabilityType, dateIssued, idValidity, birthdate, age, birthPlace, educAttainment, occupation;
    private JRadioButton f, m;
    private JComboBox<String> civilStatusCombo;
    // Page2 fields
    private JTextField mobileNum, emailAdd, fbName, guardianName, relationToMem, guardianMobileNum;
    private JTextArea houseNum;
    // Page3 table
    private DefaultTableModel tableModel;
    // Page4 fields
    private JCheckBox[] medicalCheckboxes;
    private JTextField otherMedHis;
    // Page5 fields
    private JTextField[] medFields;
    private JCheckBox takingMeds;
    private JCheckBox notTakingMeds;
    private records_members recordsMembersPanel;
    private homePage homePagePanel;

    public static void launch(records_members recordsMembers, homePage homePage) {
        try {
            addMembersPage frame = new addMembersPage(recordsMembers, homePage);
            frame.setTitle("Membership Forms");
            frame.setResizable(false);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public addMembersPage(records_members recordsMembers, homePage homePage) {
        this.recordsMembersPanel = recordsMembers;
        this.homePagePanel = homePage;
        setUndecorated(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(715, 590);
        setLayout(new BorderLayout());

        // Container for top section
        JPanel topPanel = new JPanel(null);
        topPanel.setPreferredSize(new Dimension(715, 80));
        topPanel.setBackground(Color.WHITE);
        add(topPanel, BorderLayout.NORTH);

        // Custom title bar
        JPanel titleBar = new JPanel();
        titleBar.setBackground(new Color(37, 37, 37));
        titleBar.setBounds(0, 0, 715, 30);
        titleBar.setLayout(null);
        topPanel.add(titleBar);

        // Title label
        JLabel titleLabel = new JLabel("Membership Forms");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
        titleLabel.setBounds(10, 0, 200, 30);
        titleBar.add(titleLabel);

        JButton closeButton = new JButton("X");
        closeButton.setBounds(675, 0, 40, 30);
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

        // Step bar
        JPanel stepBarPanel = new JPanel(null);
        stepBarPanel.setBounds(0, 30, 715, 50);
        stepBarPanel.setOpaque(true);
        stepBarPanel.setBackground(new Color(235, 235, 235));
        topPanel.add(stepBarPanel);

        // Step labels
        personalInfoLbl = new JLabel("PERSONAL INFO");
        personalInfoLbl.setForeground(new Color(56, 113, 193));
        personalInfoLbl.setFont(new Font("Trebuchet MS", Font.BOLD, 13));
        personalInfoLbl.setBounds(20, 8, 100, 30);
        stepBarPanel.add(personalInfoLbl);

        JLabel headerSymbol1 = new JLabel(">");
        headerSymbol1.setForeground(new Color(100, 100, 100));
        headerSymbol1.setFont(new Font("Agency FB", Font.BOLD, 19));
        headerSymbol1.setBounds(125, 7, 10, 30);
        stepBarPanel.add(headerSymbol1);

        contactLbl = new JLabel("CONTACT");
        contactLbl.setForeground(new Color(100, 100, 100));
        contactLbl.setFont(new Font("Trebuchet MS", Font.BOLD, 13));
        contactLbl.setBounds(139, 8, 100, 30);
        stepBarPanel.add(contactLbl);

        JLabel headerSymbol2 = new JLabel(">");
        headerSymbol2.setForeground(new Color(100, 100, 100));
        headerSymbol2.setFont(new Font("Agency FB", Font.BOLD, 19));
        headerSymbol2.setBounds(205, 7, 10, 30);
        stepBarPanel.add(headerSymbol2);

        familyComplbl = new JLabel("FAMILY COMPOSITION");
        familyComplbl.setForeground(new Color(100, 100, 100));
        familyComplbl.setFont(new Font("Trebuchet MS", Font.BOLD, 13));
        familyComplbl.setBounds(218, 8, 150, 30);
        stepBarPanel.add(familyComplbl);

        JLabel headerSymbol3 = new JLabel(">");
        headerSymbol3.setForeground(new Color(100, 100, 100));
        headerSymbol3.setFont(new Font("Agency FB", Font.BOLD, 19));
        headerSymbol3.setBounds(360, 7, 10, 30);
        stepBarPanel.add(headerSymbol3);

        medicalHistoryLbl = new JLabel("MEDICAL HISTORY");
        medicalHistoryLbl.setForeground(new Color(100, 100, 100));
        medicalHistoryLbl.setFont(new Font("Trebuchet MS", Font.BOLD, 13));
        medicalHistoryLbl.setBounds(373, 8, 120, 30);
        stepBarPanel.add(medicalHistoryLbl);

        JLabel headerSymbol4 = new JLabel(">");
        headerSymbol4.setForeground(new Color(100, 100, 100));
        headerSymbol4.setFont(new Font("Agency FB", Font.BOLD, 19));
        headerSymbol4.setBounds(493, 7, 10, 30);
        stepBarPanel.add(headerSymbol4);

        medicationsLbl = new JLabel("MEDICATIONS");
        medicationsLbl.setForeground(new Color(100, 100, 100));
        medicationsLbl.setFont(new Font("Trebuchet MS", Font.BOLD, 13));
        medicationsLbl.setBounds(508, 8, 100, 30);
        stepBarPanel.add(medicationsLbl);

        JSeparator separator = new JSeparator();
        separator.setForeground(new Color(100, 100, 100));
        separator.setBounds(20, 42, 675, 2);
        stepBarPanel.add(separator);

        // Main panel with CardLayout
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        mainPanel.setPreferredSize(new Dimension(715, 510));
        add(mainPanel, BorderLayout.CENTER);

        // Initialize pages
        page1 = createPage1();
        page2 = createPage2();
        page3 = createPage3();
        page4 = createPage4();
        page5 = createPage5();

        mainPanel.add(page1, "Page1");
        mainPanel.add(page2, "Page2");
        mainPanel.add(page3, "Page3");
        mainPanel.add(page4, "Page4");
        mainPanel.add(page5, "Page5");

        // Navigation buttons
        RoundedButton nextButton = new RoundedButton("Next        >", new Color(50, 50, 50));
        nextButton.setBounds(566, 462, 115, 34);
        page1.add(nextButton);
        nextButton.addActionListener(e -> {
            cardLayout.next(mainPanel);
            personalInfoLbl.setForeground(new Color(100, 100, 100));
            contactLbl.setForeground(new Color(56, 113, 193));
            familyComplbl.setForeground(new Color(100, 100, 100));
            medicalHistoryLbl.setForeground(new Color(100, 100, 100));
            medicationsLbl.setForeground(new Color(100, 100, 100));
            mainPanel.revalidate();
            mainPanel.repaint();
            getContentPane().revalidate();
            getContentPane().repaint();
        });

        RoundedButton nextButton2 = new RoundedButton("Next        >", new Color(50, 50, 50));
        nextButton2.setBounds(566, 462, 115, 34);
        page2.add(nextButton2);
        nextButton2.addActionListener(e -> {
            cardLayout.next(mainPanel);
            personalInfoLbl.setForeground(new Color(100, 100, 100));
            contactLbl.setForeground(new Color(100, 100, 100));
            familyComplbl.setForeground(new Color(56, 113, 193));
            medicalHistoryLbl.setForeground(new Color(100, 100, 100));
            medicationsLbl.setForeground(new Color(100, 100, 100));
            mainPanel.revalidate();
            mainPanel.repaint();
            getContentPane().revalidate();
            getContentPane().repaint();
        });

        TransparentButton returnButton2 = new TransparentButton("<        Back");
        returnButton2.setBounds(12, 462, 115, 34);
        page2.add(returnButton2);
        returnButton2.addActionListener(e -> {
            cardLayout.previous(mainPanel);
            personalInfoLbl.setForeground(new Color(56, 113, 193));
            contactLbl.setForeground(new Color(100, 100, 100));
            familyComplbl.setForeground(new Color(100, 100, 100));
            medicalHistoryLbl.setForeground(new Color(100, 100, 100));
            medicationsLbl.setForeground(new Color(100, 100, 100));
            mainPanel.revalidate();
            mainPanel.repaint();
            getContentPane().revalidate();
            getContentPane().repaint();
        });

        RoundedButton nextButton3 = new RoundedButton("Next        >", new Color(50, 50, 50));
        nextButton3.setBounds(566, 462, 115, 34);
        page3.add(nextButton3);
        nextButton3.addActionListener(e -> {
            cardLayout.next(mainPanel);
            personalInfoLbl.setForeground(new Color(100, 100, 100));
            contactLbl.setForeground(new Color(100, 100, 100));
            familyComplbl.setForeground(new Color(100, 100, 100));
            medicalHistoryLbl.setForeground(new Color(56, 113, 193));
            medicationsLbl.setForeground(new Color(100, 100, 100));
            mainPanel.revalidate();
            mainPanel.repaint();
            getContentPane().revalidate();
            getContentPane().repaint();
        });

        TransparentButton returnButton3 = new TransparentButton("<        Back");
        returnButton3.setBounds(12, 462, 115, 34);
        page3.add(returnButton3);
        returnButton3.addActionListener(e -> {
            cardLayout.previous(mainPanel);
            personalInfoLbl.setForeground(new Color(100, 100, 100));
            contactLbl.setForeground(new Color(56, 113, 193));
            familyComplbl.setForeground(new Color(100, 100, 100));
            medicalHistoryLbl.setForeground(new Color(100, 100, 100));
            medicationsLbl.setForeground(new Color(100, 100, 100));
            mainPanel.revalidate();
            mainPanel.repaint();
            getContentPane().revalidate();
            getContentPane().repaint();
        });

        RoundedButton nextButton4 = new RoundedButton("Next        >", new Color(50, 50, 50));
        nextButton4.setBounds(566, 462, 115, 34);
        page4.add(nextButton4);
        nextButton4.addActionListener(e -> {
            cardLayout.next(mainPanel);
            personalInfoLbl.setForeground(new Color(100, 100, 100));
            contactLbl.setForeground(new Color(100, 100, 100));
            familyComplbl.setForeground(new Color(100, 100, 100));
            medicalHistoryLbl.setForeground(new Color(100, 100, 100));
            medicationsLbl.setForeground(new Color(56, 113, 193));
            mainPanel.revalidate();
            mainPanel.repaint();
            getContentPane().revalidate();
            getContentPane().repaint();
        });

        TransparentButton returnButton4 = new TransparentButton("<        Back");
        returnButton4.setBounds(12, 462, 115, 34);
        page4.add(returnButton4);
        returnButton4.addActionListener(e -> {
            cardLayout.previous(mainPanel);
            personalInfoLbl.setForeground(new Color(100, 100, 100));
            familyComplbl.setForeground(new Color(56, 113, 193));
            contactLbl.setForeground(new Color(100, 100, 100));
            medicalHistoryLbl.setForeground(new Color(100, 100, 100));
            medicationsLbl.setForeground(new Color(100, 100, 100));
            mainPanel.revalidate();
            mainPanel.repaint();
            getContentPane().revalidate();
            getContentPane().repaint();
        });

        TransparentButton returnButton5 = new TransparentButton("<        Back");
        returnButton5.setBounds(12, 462, 115, 34);
        page5.add(returnButton5);
        returnButton5.addActionListener(e -> {
            cardLayout.previous(mainPanel);
            personalInfoLbl.setForeground(new Color(100, 100, 100));
            medicalHistoryLbl.setForeground(new Color(56, 113, 193));
            contactLbl.setForeground(new Color(100, 100, 100));
            familyComplbl.setForeground(new Color(100, 100, 100));
            medicationsLbl.setForeground(new Color(100, 100, 100));
            mainPanel.revalidate();
            mainPanel.repaint();
            getContentPane().revalidate();
            getContentPane().repaint();
        });
    }

    private JPanel createPage1() {
        JPanel panel = new JPanel(null);
        Font labelFont = new Font("Trebuchet MS", Font.PLAIN, 15);
        Font fieldFont = new Font("Trebuchet MS", Font.PLAIN, 16);
        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
        Border fieldBorder = BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JLabel datelbl = new JLabel("Date:");
        datelbl.setFont(labelFont);
        datelbl.setBounds(520, 0, 200, 30);
        panel.add(datelbl);

        fillUpDate = new JTextField(20);
        fillUpDate.setBounds(563, 7, 120, 16);
        fillUpDate.setHorizontalAlignment(JTextField.CENTER);
        fillUpDate.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        fillUpDate.setFont(new Font("Arial", Font.PLAIN, 16));
        fillUpDate.setOpaque(false);
        panel.add(fillUpDate);

        JLabel dateFormatlbl = new JLabel("MM/DD/YYYY");
        dateFormatlbl.setForeground(new Color(100, 100, 100));
        dateFormatlbl.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
        dateFormatlbl.setBounds(587, 14, 200, 30);
        panel.add(dateFormatlbl);

        JLabel demographicLbl = new JLabel("DEMOGRAPHIC SHEET");
        demographicLbl.setFont(new Font("Arial", Font.BOLD, 20));
        demographicLbl.setBounds(24, 0, 400, 30);
        panel.add(demographicLbl);

        JLabel namelbl = new JLabel("Full Name:");
        namelbl.setFont(labelFont);
        namelbl.setBounds(30, 30, 400, 30);
        panel.add(namelbl);

        fullName = new JTextField();
        fullName.setFont(fieldFont);
        fullName.setBounds(30, 60, 290, 33);
        fullName.setBorder(fieldBorder);
        panel.add(fullName);

        JLabel nameFormatlbl = new JLabel("Last Name              First Name                Middle Name");
        nameFormatlbl.setForeground(new Color(100, 100, 100));
        nameFormatlbl.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
        nameFormatlbl.setBounds(31, 85, 400, 30);
        panel.add(nameFormatlbl);

        JLabel lblSex = new JLabel("Sex:");
        lblSex.setFont(labelFont);
        lblSex.setBounds(390, 30, 100, 30);
        panel.add(lblSex);

        ButtonGroup btnSex = new ButtonGroup();
        f = new JRadioButton("Female");
        f.setFont(fieldFont);
        f.setBounds(390, 60, 100, 30);
        btnSex.add(f);
        panel.add(f);

        m = new JRadioButton("Male");
        m.setFont(fieldFont);
        m.setBounds(500, 60, 100, 30);
        btnSex.add(m);
        panel.add(m);

        JLabel pwdIdNolbl = new JLabel("PWD ID Number:");
        pwdIdNolbl.setFont(labelFont);
        pwdIdNolbl.setBounds(30, 110, 400, 30);
        panel.add(pwdIdNolbl);

        pwdIdNo = new JTextField();
        pwdIdNo.setFont(fieldFont);
        pwdIdNo.setBounds(30, 140, 290, 33);
        pwdIdNo.setBorder(fieldBorder);
        panel.add(pwdIdNo);

        JLabel typeOfDisabilitylbl = new JLabel("Type of Disability:");
        typeOfDisabilitylbl.setFont(labelFont);
        typeOfDisabilitylbl.setBounds(390, 110, 400, 30);
        panel.add(typeOfDisabilitylbl);

        disabilityType = new JTextField();
        disabilityType.setFont(fieldFont);
        disabilityType.setBounds(390, 140, 290, 33);
        disabilityType.setBorder(fieldBorder);
        panel.add(disabilityType);

        JLabel dateIssuedlbl = new JLabel("Date Issued:");
        dateIssuedlbl.setFont(labelFont);
        dateIssuedlbl.setBounds(30, 180, 400, 30);
        panel.add(dateIssuedlbl);

        dateIssued = new JTextField();
        dateIssued.setFont(fieldFont);
        dateIssued.setBounds(30, 210, 290, 33);
        dateIssued.setBorder(fieldBorder);
        panel.add(dateIssued);

        JLabel idValiditylbl = new JLabel("ID Validity:");
        idValiditylbl.setFont(labelFont);
        idValiditylbl.setBounds(390, 180, 400, 30);
        panel.add(idValiditylbl);

        idValidity = new JTextField();
        idValidity.setFont(fieldFont);
        idValidity.setBounds(390, 210, 290, 33);
        idValidity.setBorder(fieldBorder);
        panel.add(idValidity);

        // Add DocumentListener for dateIssued to validate and update idValidity
        dateIssued.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) { updateIdValidity(); }
            @Override
            public void removeUpdate(DocumentEvent e) { updateIdValidity(); }
            @Override
            public void changedUpdate(DocumentEvent e) { updateIdValidity(); }

            private void updateIdValidity() {
                String dateText = dateIssued.getText().trim();
                if (isValidDate(dateText)) {
                    String newValidity = addYearsToDate(dateText, 5);
                    if (!newValidity.equals(idValidity.getText())) {
                        idValidity.setText(newValidity);
                    }
                } else {
                    if (dateText.isEmpty()) {
                        idValidity.setText("");
                    }
                }
            }
        });

        // Add DocumentListener for idValidity to validate format
        idValidity.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) { validateIdValidity(); }
            @Override
            public void removeUpdate(DocumentEvent e) { validateIdValidity(); }
            @Override
            public void changedUpdate(DocumentEvent e) { validateIdValidity(); }

            private void validateIdValidity() {
                String validityText = idValidity.getText().trim();
                if (!validityText.isEmpty() && !isValidDate(validityText)) {
                    idValidity.setForeground(Color.RED);
                } else {
                    idValidity.setForeground(Color.BLACK);
                }
            }
        });

        JLabel birthdatelbl = new JLabel("Birthdate:");
        birthdatelbl.setFont(labelFont);
        birthdatelbl.setBounds(30, 250, 400, 30);
        panel.add(birthdatelbl);

        birthdate = new JTextField();
        birthdate.setFont(fieldFont);
        birthdate.setBounds(30, 280, 290, 33);
        birthdate.setBorder(fieldBorder);
        panel.add(birthdate);

        // Add DocumentListener for birthdate to calculate age
        birthdate.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) { updateAge(); }
            @Override
            public void removeUpdate(DocumentEvent e) { updateAge(); }
            @Override
            public void changedUpdate(DocumentEvent e) { updateAge(); }

            private void updateAge() {
                String birthdateText = birthdate.getText().trim();
                if (isValidDate(birthdateText)) {
                    String ageText = calculateAge(birthdateText);
                    if (!ageText.equals(age.getText())) {
                        age.setText(ageText);
                    }
                } else {
                    if (birthdateText.isEmpty()) {
                        age.setText("");
                    }
                }
            }
        });

        JLabel agelbl = new JLabel("Age:");
        agelbl.setFont(labelFont);
        agelbl.setBounds(390, 250, 400, 30);
        panel.add(agelbl);

        age = new JTextField();
        age.setFont(fieldFont);
        age.setBounds(390, 280, 290, 33);
        age.setBorder(fieldBorder);
        panel.add(age);

        JLabel birthPlacelbl = new JLabel("Place of Birth:");
        birthPlacelbl.setFont(labelFont);
        birthPlacelbl.setBounds(30, 313, 400, 30);
        panel.add(birthPlacelbl);

        birthPlace = new JTextField();
        birthPlace.setFont(fieldFont);
        birthPlace.setBounds(30, 343, 290, 33);
        birthPlace.setBorder(fieldBorder);
        panel.add(birthPlace);

        JLabel civilStatuslbl = new JLabel("Civil Status:");
        civilStatuslbl.setFont(labelFont);
        civilStatuslbl.setBounds(390, 313, 400, 30);
        panel.add(civilStatuslbl);

        civilStatusCombo = new JComboBox<>(new String[]{"Single", "Married", "Widowed", "Separated", "Divorced"});
        civilStatusCombo.setFont(fieldFont);
        civilStatusCombo.setBounds(390, 343, 290, 33);
        civilStatusCombo.setFocusable(false);
        civilStatusCombo.setBackground(Color.WHITE);
        civilStatusCombo.setForeground(Color.BLACK);
        civilStatusCombo.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                          boolean isSelected, boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, false, false);
                label.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
                label.setBackground(Color.WHITE);
                label.setForeground(Color.BLACK);
                return label;
            }
        });
        civilStatusCombo.setUI(new javax.swing.plaf.basic.BasicComboBoxUI() {
            @Override
            protected JButton createArrowButton() {
                JButton button = new JButton();
                button.setBorder(BorderFactory.createEmptyBorder());
                button.setContentAreaFilled(false);
                button.setFocusable(false);
                return button;
            }

            @Override
            public void paintCurrentValueBackground(Graphics g, Rectangle bounds, boolean hasFocus) {
                g.setColor(Color.WHITE);
                g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
            }

            @Override
            protected void installDefaults() {
                super.installDefaults();
                comboBox.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                comboBox.setOpaque(false);
            }
        });
        panel.add(civilStatusCombo);

        JLabel educAttainmentlbl = new JLabel("Educational Attainment:");
        educAttainmentlbl.setFont(labelFont);
        educAttainmentlbl.setBounds(30, 385, 400, 30);
        panel.add(educAttainmentlbl);

        educAttainment = new JTextField();
        educAttainment.setFont(fieldFont);
        educAttainment.setBounds(30, 415, 290, 33);
        educAttainment.setBorder(fieldBorder);
        panel.add(educAttainment);

        JLabel occupationlbl = new JLabel("Occupation:");
        occupationlbl.setFont(labelFont);
        occupationlbl.setBounds(390, 385, 400, 30);
        panel.add(occupationlbl);

        occupation = new JTextField();
        occupation.setFont(fieldFont);
        occupation.setBounds(390, 415, 290, 33);
        occupation.setBorder(fieldBorder);
        panel.add(occupation);

        return panel;
    }

    private JPanel createPage2() {
        JPanel panel = new JPanel(null);
        Font labelFont = new Font("Trebuchet MS", Font.PLAIN, 15);
        Font fieldFont = new Font("Trebuchet MS", Font.PLAIN, 16);
        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
        Border fieldBorder = BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JLabel contactDets = new JLabel("CONTACT DETAILS");
        contactDets.setFont(new Font("Arial", Font.BOLD, 20));
        contactDets.setBounds(24, 0, 400, 30);
        panel.add(contactDets);

        JLabel mobileNumlbl = new JLabel("Mobile Number:");
        mobileNumlbl.setFont(labelFont);
        mobileNumlbl.setBounds(30, 30, 400, 30);
        panel.add(mobileNumlbl);

        mobileNum = new JTextField();
        mobileNum.setFont(fieldFont);
        mobileNum.setBounds(30, 60, 290, 33);
        mobileNum.setBorder(fieldBorder);
        panel.add(mobileNum);

        JLabel emailAddlbl = new JLabel("Email Address:");
        emailAddlbl.setFont(labelFont);
        emailAddlbl.setBounds(30, 100, 400, 30);
        panel.add(emailAddlbl);

        emailAdd = new JTextField();
        emailAdd.setFont(fieldFont);
        emailAdd.setBounds(30, 130, 290, 33);
        emailAdd.setBorder(fieldBorder);
        panel.add(emailAdd);

        JLabel fbNamelbl = new JLabel("FB Account Name:");
        fbNamelbl.setFont(labelFont);
        fbNamelbl.setBounds(30, 170, 400, 30);
        panel.add(fbNamelbl);

        fbName = new JTextField();
        fbName.setFont(fieldFont);
        fbName.setBounds(30, 200, 290, 33);
        fbName.setBorder(fieldBorder);
        panel.add(fbName);

        JLabel lblHouseNum = new JLabel("Address:");
        lblHouseNum.setFont(labelFont);
        lblHouseNum.setBounds(380, 30, 200, 30);
        panel.add(lblHouseNum);

        houseNum = new JTextArea();
        houseNum.setFont(fieldFont);
        houseNum.setBounds(380, 60, 300, 104);
        houseNum.setLineWrap(true);
        houseNum.setWrapStyleWord(true);
        houseNum.setBorder(fieldBorder);
        panel.add(houseNum);

        JLabel emergencyContactlbl = new JLabel("CONTACT IN CASE OF EMERGENCY");
        emergencyContactlbl.setFont(new Font("Arial", Font.BOLD, 20));
        emergencyContactlbl.setBounds(24, 255, 400, 30);
        panel.add(emergencyContactlbl);

        JLabel guardianNamelbl = new JLabel("Name of Guardian/Representative:");
        guardianNamelbl.setFont(labelFont);
        guardianNamelbl.setBounds(30, 290, 400, 30);
        panel.add(guardianNamelbl);

        guardianName = new JTextField();
        guardianName.setFont(fieldFont);
        guardianName.setBounds(30, 320, 290, 33);
        guardianName.setBorder(fieldBorder);
        panel.add(guardianName);

        JLabel relationlbl = new JLabel("Relation to Member:");
        relationlbl.setFont(labelFont);
        relationlbl.setBounds(380, 290, 400, 30);
        panel.add(relationlbl);

        relationToMem = new JTextField();
        relationToMem.setFont(fieldFont);
        relationToMem.setBounds(380, 320, 290, 33);
        relationToMem.setBorder(fieldBorder);
        panel.add(relationToMem);

        JLabel guardianMobileNumlbl = new JLabel("Mobile Number:");
        guardianMobileNumlbl.setFont(labelFont);
        guardianMobileNumlbl.setBounds(30, 360, 400, 30);
        panel.add(guardianMobileNumlbl);

        guardianMobileNum = new JTextField();
        guardianMobileNum.setFont(fieldFont);
        guardianMobileNum.setBounds(30, 390, 290, 33);
        guardianMobileNum.setBorder(fieldBorder);
        panel.add(guardianMobileNum);

        panel.revalidate();
        panel.repaint();
        return panel;
    }

    private JPanel createPage3() {
        JPanel panel = new JPanel(null);
        panel.setPreferredSize(new Dimension(715, 470));
        panel.setBackground(new Color(235, 235, 235));

        Font labelFont = new Font("Trebuchet MS", Font.PLAIN, 16);
        Font fieldFont = new Font("Trebuchet MS", Font.PLAIN, 15);
        Font buttonFont = new Font("Trebuchet MS", Font.BOLD, 14);
        Border fieldBorder = BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK, 1),
                BorderFactory.createEmptyBorder(5, 8, 5, 8)
        );

        JLabel famComplbl = new JLabel("FAMILY COMPOSITION (HOUSEHOLD MEMBERS)");
        famComplbl.setFont(new Font("Arial", Font.BOLD, 20));
        famComplbl.setBounds(24, 0, 500, 30);
        panel.add(famComplbl);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(labelFont);
        nameLabel.setBounds(30, 30, 400, 30);
        panel.add(nameLabel);
        JTextField nameField = new JTextField();
        nameField.setFont(fieldFont);
        nameField.setBorder(fieldBorder);
        nameField.setBounds(30, 60, 290, 30);
        panel.add(nameField);

        JLabel relationshipLabel = new JLabel("Relationship:");
        relationshipLabel.setFont(labelFont);
        relationshipLabel.setBounds(390, 30, 400, 30);
        panel.add(relationshipLabel);
        JTextField relationshipField = new JTextField();
        relationshipField.setFont(fieldFont);
        relationshipField.setBorder(fieldBorder);
        relationshipField.setBounds(390, 60, 290, 30);
        panel.add(relationshipField);

        JLabel ageLabel = new JLabel("Age:");
        ageLabel.setFont(labelFont);
        ageLabel.setBounds(30, 90, 400, 30);
        panel.add(ageLabel);
        JTextField ageField = new JTextField();
        ageField.setFont(fieldFont);
        ageField.setBorder(fieldBorder);
        ageField.setBounds(30, 120, 290, 30);
        panel.add(ageField);

        JLabel birthdateLabel = new JLabel("Birthdate:");
        birthdateLabel.setFont(labelFont);
        birthdateLabel.setBounds(390, 90, 400, 30);
        panel.add(birthdateLabel);
        JTextField birthdateField = new JTextField();
        birthdateField.setFont(fieldFont);
        birthdateField.setBorder(fieldBorder);
        birthdateField.setBounds(390, 120, 290, 30);
        panel.add(birthdateField);

        JLabel civilStatusLabel = new JLabel("Civil Status:");
        civilStatusLabel.setFont(labelFont);
        civilStatusLabel.setBounds(30, 150, 400, 30);
        panel.add(civilStatusLabel);
        JTextField civilStatusField = new JTextField();
        civilStatusField.setFont(fieldFont);
        civilStatusField.setBorder(fieldBorder);
        civilStatusField.setBounds(30, 180, 290, 30);
        panel.add(civilStatusField);

        JLabel educationLabel = new JLabel("Educational Attainment:");
        educationLabel.setFont(labelFont);
        educationLabel.setBounds(390, 150, 400, 30);
        panel.add(educationLabel);
        JTextField educationField = new JTextField();
        educationField.setFont(fieldFont);
        educationField.setBorder(fieldBorder);
        educationField.setBounds(390, 180, 290, 30);
        panel.add(educationField);

        JLabel occupationLabel = new JLabel("Occupation:");
        occupationLabel.setFont(labelFont);
        occupationLabel.setBounds(30, 210, 400, 30);
        panel.add(occupationLabel);
        JTextField occupationField = new JTextField();
        occupationField.setFont(fieldFont);
        occupationField.setBorder(fieldBorder);
        occupationField.setBounds(30, 240, 290, 30);
        panel.add(occupationField);

        // Add Button
        JButton addButton = new JButton("Add");
        addButton.setFont(buttonFont);
        addButton.setBackground(new Color(90,90,90));
        addButton.setForeground(Color.WHITE);
        addButton.setFocusPainted(false);
        addButton.setBorder(new LineBorder(Color.BLACK));
        addButton.setBounds(600, 237, 80, 33);
        panel.add(addButton);

        // Table for Display
        String[] columnNames = {"Name", "Relationship", "Age", "Birthdate", "Civil Status", "Education", "Occupation"};
        tableModel = new DefaultTableModel(columnNames, 0);
        JTable displayTable = new JTable(tableModel);
        displayTable.setFont(new Font("Roboto Mono", Font.PLAIN, 14));
        displayTable.setRowHeight(40);
        displayTable.setGridColor(new Color(80, 80, 80));
        displayTable.setShowGrid(true);
        displayTable.setBackground(Color.WHITE);

        // Custom renderer for text wrapping and center alignment
        class WrappingCellRenderer extends DefaultTableCellRenderer {
            private final JTextArea textArea;

            public WrappingCellRenderer() {
                textArea = new JTextArea();
                textArea.setFont(new Font("Roboto Mono", Font.PLAIN, 14));
                textArea.setLineWrap(true);
                textArea.setWrapStyleWord(true);
                textArea.setBackground(Color.WHITE);
                textArea.setAlignmentX(Component.CENTER_ALIGNMENT);
            }

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                textArea.setText(value != null ? value.toString() : "");
                if (isSelected) {
                    textArea.setBackground(table.getSelectionBackground());
                    textArea.setForeground(table.getSelectionForeground());
                } else {
                    textArea.setBackground(table.getBackground());
                    textArea.setForeground(table.getForeground());
                }
                return textArea;
            }
        }

        for (int i = 0; i < displayTable.getColumnCount(); i++) {
            displayTable.getColumnModel().getColumn(i).setCellRenderer(new WrappingCellRenderer());
        }

        displayTable.getColumnModel().getColumn(0).setPreferredWidth(120);
        displayTable.getColumnModel().getColumn(1).setPreferredWidth(100);
        displayTable.getColumnModel().getColumn(2).setPreferredWidth(50);
        displayTable.getColumnModel().getColumn(3).setPreferredWidth(90);
        displayTable.getColumnModel().getColumn(4).setPreferredWidth(90);
        displayTable.getColumnModel().getColumn(5).setPreferredWidth(120);
        displayTable.getColumnModel().getColumn(6).setPreferredWidth(100);

        JScrollPane scrollPane = new JScrollPane(displayTable);
        scrollPane.setBounds(30, 280, 650, 175);
        scrollPane.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK, 1),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        styleScrollBar(scrollPane);
        panel.add(scrollPane);

        addButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            String relationship = relationshipField.getText().trim();
            String age = ageField.getText().trim();
            String birthdate = birthdateField.getText().trim();
            String civilStatus = civilStatusField.getText().trim();
            String education = educationField.getText().trim();
            String occupation = occupationField.getText().trim();

            if (!name.isEmpty()) {
                String[] rowData = {name, relationship, age, birthdate, civilStatus, education, occupation};
                tableModel.addRow(rowData);

                nameField.setText("");
                relationshipField.setText("");
                ageField.setText("");
                birthdateField.setText("");
                civilStatusField.setText("");
                educationField.setText("");
                occupationField.setText("");

                displayTable.scrollRectToVisible(displayTable.getCellRect(tableModel.getRowCount() - 1, 0, true));
            } else {
                JOptionPane.showMessageDialog(panel, "Name is required!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        return panel;
    }

    private JPanel createPage4() {
        JPanel panel = new JPanel(null);
        Font labelFont = new Font("Trebuchet MS", Font.PLAIN, 15);
        Font fieldFont = new Font("Trebuchet MS", Font.PLAIN, 16);
        Font titleFont = new Font("Arial", Font.BOLD, 20);
        Font checkboxFont = new Font("Trebuchet MS", Font.PLAIN, 15);
        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
        Border fieldBorder = BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JLabel medicalHistorylbl = new JLabel("MEDICAL HISTORY");
        medicalHistorylbl.setFont(titleFont);
        medicalHistorylbl.setBounds(24, 0, 400, 30);
        panel.add(medicalHistorylbl);

        JLabel instructionLabel = new JLabel("Please Tick the Boxes if You Had/Have:");
        instructionLabel.setFont(new Font("Trebuchet MS", Font.ITALIC, 16));
        instructionLabel.setBounds(30, 35, 300, 30);
        panel.add(instructionLabel);

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
        for (JCheckBox checkbox : medicalCheckboxes) {
            checkbox.setFont(checkboxFont);
            checkbox.setOpaque(false);
            checkbox.setFocusPainted(false);
            checkbox.setUI(new MinimalCheckBoxUI());
        }

        diabetes.setBounds(30, 70, 150, 25);
        stroke.setBounds(30, 95, 150, 25);
        heartProb.setBounds(30, 120, 180, 25);
        cancer.setBounds(30, 145, 150, 25);
        arthritis.setBounds(30, 170, 150, 25);
        highBlood.setBounds(350, 70, 180, 25);
        lungProb.setBounds(350, 95, 180, 25);
        osteoporosis.setBounds(350, 120, 180, 25);
        epilepsy.setBounds(350, 145, 150, 25);
        kidneyProb.setBounds(350, 170, 180, 25);

        for (JCheckBox checkbox : medicalCheckboxes) {
            panel.add(checkbox);
        }

        JLabel otherLabel = new JLabel("Others (Please Specify):");
        otherLabel.setFont(labelFont);
        otherLabel.setBounds(30, 210, 200, 30);
        panel.add(otherLabel);

        otherMedHis = new JTextField();
        otherMedHis.setFont(fieldFont);
        otherMedHis.setBounds(30, 240, 650, 33);
        otherMedHis.setBorder(fieldBorder);
        panel.add(otherMedHis);

        panel.revalidate();
        panel.repaint();
        return panel;
    }

    private JPanel createPage5() {
        JPanel panel = new JPanel(null);
        Font labelFont = new Font("Trebuchet MS", Font.PLAIN, 15);
        Font fieldFont = new Font("Trebuchet MS", Font.PLAIN, 16);
        Font titleFont = new Font("Arial", Font.BOLD, 20);
        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
        Border fieldBorder = BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JLabel medicationslbl = new JLabel("MEDICATIONS");
        medicationslbl.setFont(titleFont);
        medicationslbl.setBounds(24, 0, 400, 30);
        panel.add(medicationslbl);

        JLabel lblTakingMeds = new JLabel("Do You Take Any Prescribed Medications: ");
        lblTakingMeds.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
        lblTakingMeds.setBounds(30, 30, 400, 30);
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
        notTakingMeds.setFont(fieldFont);
        notTakingMeds.setOpaque(false);
        notTakingMeds.setFocusPainted(false);
        notTakingMeds.setUI(new MinimalCheckBoxUI());
        takingMeds.setBounds(380, 30, 70, 30);
        notTakingMeds.setBounds(460, 30, 100, 30);

        panel.add(takingMeds);
        panel.add(notTakingMeds);

        JLabel lblMedsHeader = new JLabel("List All Medications You Are Currently Taking:");
        lblMedsHeader.setFont(new Font("Trebuchet MS", Font.ITALIC, 16));
        lblMedsHeader.setBounds(30, 60, 450, 30);
        lblMedsHeader.setVisible(false);
        panel.add(lblMedsHeader);

        JPanel medsPane = new JPanel(null);
        medsPane.setPreferredSize(new Dimension(630, 450));
        JScrollPane scrollPane = new JScrollPane(medsPane);
        scrollPane.setBounds(30, 95, 650, 350);
        scrollPane.setBorder(fieldBorder);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setVisible(false);
        styleScrollBar(scrollPane);
        panel.add(scrollPane);

        medFields = new JTextField[15];
        for (int i = 0; i < 15; i++) {
            JLabel medNum = new JLabel((i + 1) + ".");
            medNum.setFont(fieldFont);
            medNum.setBounds(10, 10 + i * 30, 30, 25);
            medsPane.add(medNum);

            medFields[i] = new JTextField();
            medFields[i].setFont(fieldFont);
            medFields[i].setBounds(40, 10 + i * 30, 570, 25);
            medFields[i].setBorder(fieldBorder);
            medFields[i].setBackground(Color.WHITE);
            medsPane.add(medFields[i]);
        }

        ItemListener visibilityListener = e -> {
            boolean isTakingMeds = takingMeds.isSelected();
            lblMedsHeader.setVisible(isTakingMeds);
            scrollPane.setVisible(isTakingMeds);
            panel.revalidate();
            panel.repaint();
        };
        takingMeds.addItemListener(visibilityListener);
        notTakingMeds.addItemListener(visibilityListener);

        RoundedButton submitButton = new RoundedButton("Submit", new Color(73, 230, 127), Color.BLACK);
        submitButton.setBounds(566, 462, 115, 34);
        submitButton.setForeground(new Color(50,50,50));
        submitButton.addActionListener(e -> {
            String errorMessage = validateAllFields();
            if (!errorMessage.isEmpty()) {
                JOptionPane.showMessageDialog(this, errorMessage, "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String warningMessage = checkOptionalFields();
            if (!warningMessage.isEmpty()) {
                // Save current UIManager settings
                Object originalButtonUI = UIManager.get("ButtonUI");
                Object originalButtonBackground = UIManager.get("Button.background");
                Object originalButtonForeground = UIManager.get("Button.foreground");
                Object originalButtonBorder = UIManager.get("Button.border");
                Object originalButtonFont = UIManager.get("Button.font");

                // Set custom UIManager settings for dialog buttons
                UIManager.put("ButtonUI", MinimalButtonUI.class.getName());
                UIManager.put("Button.background", new Color(50, 50, 50));
                UIManager.put("Button.foreground", Color.WHITE);
                UIManager.put("Button.border", BorderFactory.createEmptyBorder());
                UIManager.put("Button.font", new Font("Trebuchet MS", Font.BOLD, 14));

                JOptionPane optionPane = new JOptionPane(
                        "Warning: Some optional fields are missing:\n" + warningMessage + "\nDo you want to proceed?",
                        JOptionPane.WARNING_MESSAGE,
                        JOptionPane.YES_NO_OPTION,
                        null,
                        new String[]{"Proceed", "Go Back"},
                        "Go Back"
                );
                JDialog dialog = optionPane.createDialog(this, "Missing Optional Fields");
                dialog.setVisible(true);
                Object choice = optionPane.getValue();

                // Restore original UIManager settings
                UIManager.put("ButtonUI", originalButtonUI);
                UIManager.put("Button.background", originalButtonBackground);
                UIManager.put("Button.foreground", originalButtonForeground);
                UIManager.put("Button.border", originalButtonBorder);
                UIManager.put("Button.font", originalButtonFont);

                // Handle dialog choice
                if (choice != null && choice.equals("Proceed")) {
                    submitForm();
                }
                // If "Go Back" or dialog is closed, do nothing (stay on form)
            } else {
                submitForm();
            }
        });
        panel.add(submitButton);

        panel.revalidate();
        panel.repaint();
        return panel;
    }

    // Helper method to validate all required fields
    private String validateAllFields() {
        StringBuilder errors = new StringBuilder();

        // Page 1 validation
        if (fillUpDate.getText().trim().isEmpty()) {
            errors.append("Fill Up Date is required.\n");
        } else if (!isValidDate(fillUpDate.getText().trim())) {
            errors.append("Fill Up Date must be in MM/DD/YYYY format.\n");
        }
        if (fullName.getText().trim().isEmpty()) {
            errors.append("Full Name is required.\n");
        }
        if (!f.isSelected() && !m.isSelected()) {
            errors.append("Sex must be selected.\n");
        }
        if (pwdIdNo.getText().trim().isEmpty()) {
            errors.append("PWD ID Number is required.\n");
        }
        if (disabilityType.getText().trim().isEmpty()) {
            errors.append("Type of Disability is required.\n");
        }
        if (dateIssued.getText().trim().isEmpty()) {
            errors.append("Date Issued is required.\n");
        } else if (!isValidDate(dateIssued.getText().trim())) {
            errors.append("Date Issued must be in MM/DD/YYYY format.\n");
        }
        if (idValidity.getText().trim().isEmpty()) {
            errors.append("ID Validity is required.\n");
        } else if (!isValidDate(idValidity.getText().trim())) {
            errors.append("ID Validity must be in MM/DD/YYYY format.\n");
        }
        if (birthdate.getText().trim().isEmpty()) {
            errors.append("Birthdate is required.\n");
        } else if (!isValidDate(birthdate.getText().trim())) {
            errors.append("Birthdate must be in MM/DD/YYYY format.\n");
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
        if (civilStatusCombo.getSelectedItem() == null) {
            errors.append("Civil Status is required.\n");
        }

        // Page 2 validation
        if (mobileNum.getText().trim().isEmpty()) {
            errors.append("Mobile Number is required.\n");
        }
        if (houseNum.getText().trim().isEmpty()) {
            errors.append("Address is required.\n");
        }
        if (guardianName.getText().trim().isEmpty()) {
            errors.append("Guardian Name is required.\n");
        }
        if (relationToMem.getText().trim().isEmpty()) {
            errors.append("Relation to Member is required.\n");
        }
        if (guardianMobileNum.getText().trim().isEmpty()) {
            errors.append("Guardian Mobile Number is required.\n");
        }

        if (!takingMeds.isSelected() && !notTakingMeds.isSelected()) {
            errors.append("Please indicate whether you take prescribed medications (select 'Yes' or 'No').\n");
        }

        return errors.toString();
    }

    private void submitForm() {
        try (Connection conn = database.getConnection()) {
            membersDAO membersDAO = new membersDAO(conn);
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
            records_members records = new records_members();

            // Page 1: Personal Info
            String fullNameText = fullName.getText().trim();
            String pwdIdText = pwdIdNo.getText().trim();
            String disabilityText = disabilityType.getText().trim();
            Date dateIssuedDate = new Date(sdf.parse(dateIssued.getText().trim()).getTime());
            Date idValidUntilDate = new Date(sdf.parse(idValidity.getText().trim()).getTime());
            Date birthdateDate = new Date(sdf.parse(birthdate.getText().trim()).getTime());
            int ageValue = Integer.parseInt(age.getText().trim());
            String sex = m.isSelected() ? "Male" : f.isSelected() ? "Female" : "Other";
            String civilStatus = civilStatusCombo.getSelectedItem().toString();
            String placeOfBirthText = birthPlace.getText().trim();
            String educationLevel = educAttainment.getText().trim();
            String occupationText = occupation.getText().trim();

            // Page 2: Contact
            String mobileNumber = mobileNum.getText().trim();
            String emailText = emailAdd.getText().trim();
            String fbAccount = fbName.getText().trim();
            String addressText = houseNum.getText().trim();
            String guardianNameText = guardianName.getText().trim();
            String guardianRelation = relationToMem.getText().trim();
            String guardianMobile = guardianMobileNum.getText().trim();

            // Page 3: Family Composition
            List<Map<String, Object>> householdMembers = new ArrayList<>();
            for (int i = 0; i < tableModel.getRowCount(); i++) {
                Map<String, Object> member = new HashMap<>();
                member.put("name", tableModel.getValueAt(i, 0).toString());
                member.put("relationship", tableModel.getValueAt(i, 1).toString());
                member.put("age", tableModel.getValueAt(i, 2).toString());
                String birthdateStr = tableModel.getValueAt(i, 3).toString();
                if (!birthdateStr.isEmpty()) {
                    // Convert MM/dd/yyyy to yyyy-MM-dd for SQL
                    String[] parts = birthdateStr.split("/");
                    if (parts.length == 3) {
                        birthdateStr = parts[2] + "-" + parts[0] + "-" + parts[1];
                    }
                }
                member.put("birthdate", birthdateStr);
                member.put("civilStatus", tableModel.getValueAt(i, 4).toString());
                member.put("education", tableModel.getValueAt(i, 5).toString());
                member.put("occupation", tableModel.getValueAt(i, 6).toString());
                householdMembers.add(member);
            }

            // Page 4: Medical History
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
            String otherConditions = otherMedHis.getText().trim();

            // Page 5: Medications
            boolean takesMedications = takingMeds.isSelected();
            List<String> medications = new ArrayList<>();
            if (takesMedications) {
                for (JTextField medField : medFields) {
                    String med = medField.getText().trim();
                    if (!med.isEmpty()) {
                        medications.add(med);
                    }
                }
            }

            // Call addMember
            boolean success = membersDAO.addMember(
                    fullNameText, pwdIdText, disabilityText, dateIssuedDate, idValidUntilDate,
                    birthdateDate, ageValue, sex, civilStatus, placeOfBirthText, educationLevel,
                    occupationText, addressText, mobileNumber, emailText, fbAccount,
                    guardianNameText, guardianRelation, guardianMobile, takesMedications,
                    householdMembers, medicalConditions, otherConditions, medications
            );

            if (success) {
                JOptionPane.showMessageDialog(this, "Member added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                // Notify records_members to reload
                if (recordsMembersPanel != null) {
                    recordsMembersPanel.loadMembers();
                }
                // Notify homePage to reload
                if (homePagePanel != null) {
                    homePagePanel.reloadData();
                }
                dispose();
                if (mainPage.instance != null) {
                    mainPage.instance.hideDim();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add member.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Helper method to check optional fields on Pages 3–5
    private String checkOptionalFields() {
        StringBuilder warnings = new StringBuilder();

        // Page 3: Family Composition
        if (tableModel.getRowCount() == 0) {
            warnings.append("- No family members added in Family Composition.\n");
        }

        // Page 4: Medical History
        boolean anyCheckboxSelected = false;
        for (JCheckBox checkbox : medicalCheckboxes) {
            if (checkbox.isSelected()) {
                anyCheckboxSelected = true;
                break;
            }
        }
        if (!anyCheckboxSelected && otherMedHis.getText().trim().isEmpty()) {
            warnings.append("- No medical history conditions specified.\n");
        }

        // Page 5: Medications
        if (takingMeds.isSelected()) {
            boolean anyMedicationFilled = false;
            for (JTextField medField : medFields) {
                if (!medField.getText().trim().isEmpty()) {
                    anyMedicationFilled = true;
                    break;
                }
            }
            if (!anyMedicationFilled) {
                warnings.append("- No medications listed.\n");
            }
        }

        return warnings.toString();
    }

    // Helper method to validate date format (MM/DD/YYYY)
    private boolean isValidDate(String date) {
        if (date == null || !date.matches("\\d{2}/\\d{2}/\\d{4}")) {
            return false;
        }
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
            sdf.setLenient(false);
            sdf.parse(date);
            String[] parts = date.split("/");
            int month = Integer.parseInt(parts[0]);
            int day = Integer.parseInt(parts[1]);
            int year = Integer.parseInt(parts[2]);
            if (month < 1 || month > 12 || day < 1 || day > 31 || year < 1900 || year > 2099) {
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Helper method to calculate age from birthdate
    private String calculateAge(String birthdate) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
            sdf.setLenient(false);
            java.util.Date birthDate = sdf.parse(birthdate);
            java.util.Calendar birthCal = java.util.Calendar.getInstance();
            birthCal.setTime(birthDate);

            // Use current date (June 23, 2025)
            java.util.Calendar currentCal = java.util.Calendar.getInstance();
            currentCal.set(2025, java.util.Calendar.JUNE, 23);

            int age = currentCal.get(java.util.Calendar.YEAR) - birthCal.get(java.util.Calendar.YEAR);
            if (currentCal.get(java.util.Calendar.DAY_OF_YEAR) < birthCal.get(java.util.Calendar.DAY_OF_YEAR)) {
                age--;
            }
            return String.valueOf(age);
        } catch (Exception e) {
            return "";
        }
    }

    // Helper method to add years to a date
    private String addYearsToDate(String date, int years) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
            java.util.Calendar cal = java.util.Calendar.getInstance();
            cal.setTime(sdf.parse(date));
            cal.add(java.util.Calendar.YEAR, years);
            return sdf.format(cal.getTime());
        } catch (Exception e) {
            return "";
        }
    }

    private static class RoundedButton extends JButton {
        private final int radius = 30;
        private final Color bgColor;
        private Color borderColor; // Null means no border

        public RoundedButton(String text, Color bgColor) {
            this(text, bgColor, null); // No border by default
        }

        public RoundedButton(String text, Color bgColor, Color borderColor) {
            super(text);
            this.bgColor = bgColor;
            this.borderColor = borderColor;
            setFont(new Font("Trebuchet MS", Font.BOLD, 14));
            setForeground(Color.WHITE);
            setFocusPainted(false);
            setContentAreaFilled(false);
            setOpaque(false);
            setBorderPainted(borderColor != null); // Enable border only if borderColor is set
            setMargin(new Insets(5, 10, 7, 10));
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

    // Custom Button UI
    private static class MinimalButtonUI extends BasicButtonUI {
        private final int radius = 20;

        @Override
        public void installUI(JComponent c) {
            super.installUI(c);
            AbstractButton button = (AbstractButton) c;
            button.setOpaque(false);
            button.setBorderPainted(false);
            button.setFocusPainted(false);
            button.setContentAreaFilled(false);
            button.setMargin(new Insets(5, 15, 5, 15));
        }

        @Override
        public void paint(Graphics g, JComponent c) {
            AbstractButton button = (AbstractButton) c;
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int width = button.getWidth();
            int height = button.getHeight();
            String text = button.getText();

            // Use black background for both buttons
            g2.setColor(new Color(50, 50, 50)); // Solid black
            g2.fillRoundRect(0, 0, width, height, radius, radius);
            g2.setColor(Color.WHITE); // White text

            // Draw text
            FontMetrics fm = g2.getFontMetrics();
            int textX = (width - fm.stringWidth(text)) / 2;
            int textY = (height - fm.getHeight()) / 2 + fm.getAscent();
            g2.drawString(text, textX, textY);

            g2.dispose();
        }

        @Override
        public Dimension getPreferredSize(JComponent c) {
            AbstractButton button = (AbstractButton) c;
            FontMetrics fm = c.getFontMetrics(c.getFont());
            String text = button.getText();
            int width = fm.stringWidth(text) + 30; // Add padding
            int height = fm.getHeight() + 10;
            return new Dimension(width, height);
        }
    }

    private static class TransparentButton extends JButton {
        public TransparentButton(String text) {
            super(text);
            setFont(new Font("Trebuchet MS", Font.BOLD, 14));
            setForeground(Color.BLACK);
            setContentAreaFilled(false);
            setOpaque(false);
            setBorderPainted(false);
            setFocusPainted(false);
            setMargin(new Insets(4, 10, 8, 10));
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
        }

        @Override
        protected void paintBorder(Graphics g) {
        }
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
        verticalScrollBar.setOpaque(false);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
    }

    class MinimalCheckBoxUI extends javax.swing.plaf.basic.BasicCheckBoxUI {
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

            if (b.getModel().isSelected()) {
                g2.setColor(new Color(56, 113, 193));
                g2.setStroke(new BasicStroke(2));
                g2.drawLine(x + 3, y + 7, x + 6, y + 10);
                g2.drawLine(x + 6, y + 10, x + 11, y + 3);
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
}