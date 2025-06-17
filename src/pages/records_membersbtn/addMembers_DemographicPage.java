package pages.records_membersbtn;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class addMembers_DemographicPage extends JPanel {
    private JPanel page2;
    private JPanel page3;
    private JPanel page4;
    private final JTable table;
    private JScrollPane scrollPane;
    private final DefaultTableModel tableModel;
    private final JButton addButton;
    private final JButton updButton;
    private final JButton delButton;

    public addMembers_DemographicPage() {
        setLayout(null);
        setBorder(new EmptyBorder(10, 10, 10, 10));

        Font labelFont = new Font("Trebuchet MS", Font.PLAIN, 16);
        Font fieldFont = new Font("Trebuchet MS", Font.PLAIN, 18);

        // Border for text fields
        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
        Border fieldBorder = BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(5, 5, 5, 5));

        // Page 1 Components (this panel)
        JLabel lblmemb = new JLabel("Additional Member Information");
        lblmemb.setFont(new Font("Trebuchet MS", Font.ITALIC, 16));
        lblmemb.setBounds(10, 5, 850, 30);
        add(lblmemb);

        JSeparator separator = new JSeparator();
        separator.setBounds(242, 22, 450, 2);
        separator.setForeground(new Color(150, 150, 150));
        add(separator);

        JLabel lblDisabilityType = new JLabel("Type of Disability:");
        lblDisabilityType.setFont(labelFont);
        lblDisabilityType.setBounds(35, 32, 200, 30);
        add(lblDisabilityType);

        JTextField DisabilityType = new JTextField();
        DisabilityType.setFont(fieldFont);
        DisabilityType.setBounds(35, 62, 295, 38);
        DisabilityType.setBorder(fieldBorder);
        add(DisabilityType);

        JLabel lblAge = new JLabel("Age:");
        lblAge.setFont(labelFont);
        lblAge.setBounds(375, 32, 200, 30);
        add(lblAge);

        JTextField age = new JTextField();
        age.setFont(fieldFont);
        age.setBounds(375, 62, 295, 38);
        age.setBorder(fieldBorder);
        add(age);

        JLabel lblCivilStatus = new JLabel("Civil Status:");
        lblCivilStatus.setFont(labelFont);
        lblCivilStatus.setBounds(35, 105, 200, 30);
        add(lblCivilStatus);

        JTextField civilStatus = new JTextField();
        civilStatus.setFont(fieldFont);
        civilStatus.setBounds(35, 135, 295, 38);
        civilStatus.setBorder(fieldBorder);
        add(civilStatus);

        JLabel lblEduAttainment = new JLabel("Educational Attainment:");
        lblEduAttainment.setFont(labelFont);
        lblEduAttainment.setBounds(375, 105, 200, 30);
        add(lblEduAttainment);

        JTextField educAttainment = new JTextField();
        educAttainment.setFont(fieldFont);
        educAttainment.setBounds(375, 135, 295, 38);
        educAttainment.setBorder(fieldBorder);
        add(educAttainment);

        JLabel lblOccupation = new JLabel("Occupation:");
        lblOccupation.setFont(labelFont);
        lblOccupation.setBounds(35, 178, 200, 30);
        add(lblOccupation);

        JTextField occupation = new JTextField();
        occupation.setFont(fieldFont);
        occupation.setBounds(35, 208, 295, 38);
        occupation.setBorder(fieldBorder);
        add(occupation);

        JLabel lblContactDeets = new JLabel("Contact Details");
        lblContactDeets.setFont(new Font("Trebuchet MS", Font.ITALIC, 16));
        lblContactDeets.setBounds(10, 254, 850, 30);
        add(lblContactDeets);

        JSeparator separator1 = new JSeparator();
        separator1.setBounds(132, 271, 560, 2);
        separator1.setForeground(new Color(150, 150, 150));
        add(separator1);

        JLabel lblMobileNo = new JLabel("Mobile Number:");
        lblMobileNo.setFont(labelFont);
        lblMobileNo.setBounds(35, 281, 200, 30);
        add(lblMobileNo);

        JTextField mobileNo = new JTextField();
        mobileNo.setFont(fieldFont);
        mobileNo.setBounds(35, 311, 295, 38);
        mobileNo.setBorder(fieldBorder);
        add(mobileNo);

        JLabel lblEmailAdd = new JLabel("Email Address:");
        lblEmailAdd.setFont(labelFont);
        lblEmailAdd.setBounds(375, 281, 200, 30);
        add(lblEmailAdd);

        JTextField emailAdd = new JTextField();
        emailAdd.setFont(fieldFont);
        emailAdd.setBounds(375, 311, 295, 38);
        emailAdd.setBorder(fieldBorder);
        add(emailAdd);

        JLabel lblFbAcc = new JLabel("FB Account Name:");
        lblFbAcc.setFont(labelFont);
        lblFbAcc.setBounds(35, 354, 200, 30);
        add(lblFbAcc);

        JTextField fbAcc = new JTextField();
        fbAcc.setFont(fieldFont);
        fbAcc.setBounds(35, 384, 295, 38);
        fbAcc.setBorder(fieldBorder);
        add(fbAcc);

        JLabel lblPage1 = new JLabel("1");
        lblPage1.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
        lblPage1.setBounds(351, 451, 50, 50);
        lblPage1.setForeground(Color.BLACK);
        add(lblPage1);

        JButton next1 = new JButton(">");
        next1.setFont(new Font("Agency FB", Font.BOLD, 24));
        next1.setBounds(358, 461, 21, 26);
        next1.setBackground(new Color(240, 240, 240));
        next1.setForeground(Color.BLACK);
        next1.setBorderPainted(false);
        next1.setFocusPainted(false);
        next1.setContentAreaFilled(false);
        next1.setOpaque(true);
        next1.setMargin(new Insets(0, 0, 0, 0));
        next1.setVerticalAlignment(SwingConstants.CENTER);
        next1.setHorizontalAlignment(SwingConstants.CENTER);
        add(next1);

        // Page 2 Initialization
        page2 = new JPanel(null);
        page2.setBounds(0, 0, 730, 590);
        page2.setVisible(false);
        add(page2);
        setComponentZOrder(page2, 0);

        JLabel lblEmergencyContact = new JLabel("To Contact In Case of Emergency");
        lblEmergencyContact.setFont(new Font("Trebuchet MS", Font.ITALIC, 16));
        lblEmergencyContact.setBounds(10, 5, 850, 30);
        page2.add(lblEmergencyContact);

        JSeparator separator3 = new JSeparator();
        separator3.setBounds(252, 22, 440, 2);
        separator3.setForeground(new Color(150, 150, 150));
        page2.add(separator3);

        JLabel lblGuardianName = new JLabel("Name of Guardian/Representative:");
        lblGuardianName.setFont(labelFont);
        lblGuardianName.setBounds(35, 32, 250, 30);
        page2.add(lblGuardianName);

        JTextField guardianName = new JTextField();
        guardianName.setFont(fieldFont);
        guardianName.setBounds(35, 62, 295, 38);
        guardianName.setBorder(fieldBorder);
        page2.add(guardianName);

        JLabel lblMembRelation = new JLabel("Relation to Member:");
        lblMembRelation.setFont(labelFont);
        lblMembRelation.setBounds(375, 32, 200, 30);
        page2.add(lblMembRelation);

        JTextField memberRelation = new JTextField();
        memberRelation.setFont(fieldFont);
        memberRelation.setBounds(375, 62, 295, 38);
        memberRelation.setBorder(fieldBorder);
        page2.add(memberRelation);

        JLabel lblGuardianNo = new JLabel("Mobile Number:");
        lblGuardianNo.setFont(labelFont);
        lblGuardianNo.setBounds(35, 105, 200, 30);
        page2.add(lblGuardianNo);

        JTextField guardianNo = new JTextField();
        guardianNo.setFont(fieldFont);
        guardianNo.setBounds(35, 135, 295, 38);
        guardianNo.setBorder(fieldBorder);
        page2.add(guardianNo);

        JLabel lblFamCompo = new JLabel("Family Composition (Household Members)");
        lblFamCompo.setFont(new Font("Trebuchet MS", Font.ITALIC, 16));
        lblFamCompo.setBounds(10, 177, 850, 30);
        page2.add(lblFamCompo);

        JSeparator separator4 = new JSeparator();
        separator4.setBounds(320, 194, 372, 2);
        separator4.setForeground(new Color(150, 150, 150));
        page2.add(separator4);

        addButton = new JButton("Add");
        addButton.setBounds(35, 210, 70, 30);
        page2.add(addButton);

        updButton = new JButton("Update");
        updButton.setBounds(110, 210, 75, 30);
        page2.add(updButton);

        delButton = new JButton("Delete");
        delButton.setBounds(190, 210, 75, 30);
        page2.add(delButton);

        tableModel = new DefaultTableModel(
                new String[]{"Name", "Relationship", "Age", "Birthdate", "Civil Status", "Educational Attainment", "Occupation"}, 0
        );

        table = new JTable(tableModel);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        table.getColumnModel().getColumn(0).setPreferredWidth(150);
        table.getColumnModel().getColumn(1).setPreferredWidth(100);
        table.getColumnModel().getColumn(2).setPreferredWidth(50);
        table.getColumnModel().getColumn(3).setPreferredWidth(100);
        table.getColumnModel().getColumn(4).setPreferredWidth(90);
        table.getColumnModel().getColumn(5).setPreferredWidth(150);
        table.getColumnModel().getColumn(6).setPreferredWidth(100);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
        header.setBackground(new Color(245, 245, 245));
        header.setPreferredSize(new Dimension(header.getPreferredSize().width, 35));
        header.setForeground(Color.BLACK);
        header.setOpaque(true);
        header.setReorderingAllowed(false);
        ((DefaultTableCellRenderer) header.getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);

        table.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
        table.setRowHeight(30);
        table.setIntercellSpacing(new Dimension(0, 0));
        table.setForeground(Color.BLACK);
        table.setSelectionBackground(Color.WHITE);

        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(35, 250, 640, 205);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        page2.add(scrollPane);

        tableModel.addRow(new Object[]{"Jana Beatrice Agustin", "Sister", "20", "12-29-04", "Single", "College Student", "None"});
        tableModel.addRow(new Object[]{"Jana Beatrice Agustin", "Sister", "20", "12-29-04", "Single", "College Student", "None"});

        JLabel lblPage2 = new JLabel("2");
        lblPage2.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
        lblPage2.setBounds(350, 451, 50, 50);
        lblPage2.setForeground(Color.BLACK);
        page2.add(lblPage2);

        JButton next2 = new JButton(">");
        next2.setFont(new Font("Agency FB", Font.BOLD, 24));
        next2.setBounds(358, 461, 21, 26);
        next2.setBackground(new Color(240, 240, 240));
        next2.setForeground(Color.BLACK);
        next2.setBorderPainted(false);
        next2.setFocusPainted(false);
        next2.setContentAreaFilled(false);
        next2.setOpaque(true);
        next2.setMargin(new Insets(0, 0, 0, 0));
        next2.setVerticalAlignment(SwingConstants.CENTER);
        next2.setHorizontalAlignment(SwingConstants.CENTER);
        page2.add(next2);

        JButton back2 = new JButton("<");
        back2.setFont(new Font("Agency FB", Font.BOLD, 24));
        back2.setBounds(329, 460, 21, 26);
        back2.setBackground(new Color(240, 240, 240));
        back2.setForeground(Color.BLACK);
        back2.setBorderPainted(false);
        back2.setFocusPainted(false);
        back2.setContentAreaFilled(false);
        back2.setOpaque(true);
        back2.setMargin(new Insets(0, 0, 0, 0));
        back2.setVerticalAlignment(SwingConstants.CENTER);
        back2.setHorizontalAlignment(SwingConstants.CENTER);
        page2.add(back2);

        // Page 3 Initialization
        page3 = new JPanel(null);
        page3.setBounds(0, 0, 730, 590);
        page3.setVisible(false);
        add(page3);
        setComponentZOrder(page3, 0);

        JLabel lblMedHistory = new JLabel("Medical History");
        lblMedHistory.setFont(new Font("Trebuchet MS", Font.ITALIC, 16));
        lblMedHistory.setBounds(10, 5, 850, 30);
        page3.add(lblMedHistory);

        JSeparator separator5 = new JSeparator();
        separator5.setBounds(132, 22, 560, 2);
        separator5.setForeground(new Color(150, 150, 150));
        page3.add(separator5);

        JLabel lblMedReminder = new JLabel("Please Tick the Boxes if You Had/Have:");
        lblMedReminder.setFont(new Font("Trebuchet MS", Font.ITALIC, 16));
        lblMedReminder.setBounds(50, 35, 300, 30);
        page3.add(lblMedReminder);

        Font checkbox = new Font("Trebuchet MS", Font.PLAIN, 16);

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

        diabetes.setOpaque(false);
        stroke.setOpaque(false);
        heartProb.setOpaque(false);
        cancer.setOpaque(false);
        highBlood.setOpaque(false);
        lungProb.setOpaque(false);
        arthritis.setOpaque(false);
        osteoporosis.setOpaque(false);
        epilepsy.setOpaque(false);
        kidneyProb.setOpaque(false);

        diabetes.setFocusPainted(false);
        stroke.setFocusPainted(false);
        heartProb.setFocusPainted(false);
        cancer.setFocusPainted(false);
        highBlood.setFocusPainted(false);
        lungProb.setFocusPainted(false);
        arthritis.setFocusPainted(false);
        osteoporosis.setFocusPainted(false);
        epilepsy.setFocusPainted(false);
        kidneyProb.setFocusPainted(false);

        diabetes.setBounds(50, 70, 150, 30);
        stroke.setBounds(50, 90, 150, 30);
        heartProb.setBounds(50, 110, 180, 30);
        cancer.setBounds(50, 130, 150, 30);
        highBlood.setBounds(210, 70, 180, 30);
        lungProb.setBounds(210, 90, 180, 30);
        arthritis.setBounds(210, 110, 150, 30);
        osteoporosis.setBounds(210, 130, 180, 30);
        epilepsy.setBounds(400, 70, 150, 30);
        kidneyProb.setBounds(400, 90, 180, 30);

        diabetes.setFont(checkbox);
        stroke.setFont(checkbox);
        heartProb.setFont(checkbox);
        cancer.setFont(checkbox);
        highBlood.setFont(checkbox);
        lungProb.setFont(checkbox);
        arthritis.setFont(checkbox);
        osteoporosis.setFont(checkbox);
        epilepsy.setFont(checkbox);
        kidneyProb.setFont(checkbox);

        page3.add(diabetes);
        page3.add(stroke);
        page3.add(heartProb);
        page3.add(cancer);
        page3.add(highBlood);
        page3.add(lungProb);
        page3.add(arthritis);
        page3.add(osteoporosis);
        page3.add(epilepsy);
        page3.add(kidneyProb);

        JLabel lblOtherMedHis = new JLabel("Others (Please Specify):");
        lblOtherMedHis.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
        lblOtherMedHis.setBounds(50, 170, 200, 30);
        page3.add(lblOtherMedHis);

        JTextField otherMedHis = new JTextField();
        otherMedHis.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
        otherMedHis.setBounds(230, 172, 350, 28);
        page3.add(otherMedHis);

        JLabel lblMedications = new JLabel("Medications");
        lblMedications.setFont(new Font("Trebuchet MS", Font.ITALIC, 16));
        lblMedications.setBounds(10, 202, 850, 30);
        page3.add(lblMedications);

        JSeparator separator6 = new JSeparator();
        separator6.setBounds(107, 219, 585, 2);
        separator6.setForeground(new Color(150, 150, 150));
        page3.add(separator6);

        JLabel lblTakingMeds = new JLabel("Do You Take Any Prescribed Medications: ");
        lblTakingMeds.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
        lblTakingMeds.setBounds(50, 232, 400, 30);
        page3.add(lblTakingMeds);

        JCheckBox takingMeds = new JCheckBox("Yes");
        JCheckBox notTakingMeds = new JCheckBox("No");
        ButtonGroup checkBoxGroup = new ButtonGroup();
        checkBoxGroup.add(takingMeds);
        checkBoxGroup.add(notTakingMeds);

        takingMeds.setFocusPainted(false);
        notTakingMeds.setFocusPainted(false);
        takingMeds.setFont(checkbox);
        notTakingMeds.setFont(checkbox);
        takingMeds.setBounds(380, 232, 80, 30);
        notTakingMeds.setBounds(460, 232, 100, 30);

        page3.add(takingMeds);
        page3.add(notTakingMeds);

        JPanel medsPane = new JPanel();
        medsPane.setLayout(null);
        medsPane.setBounds(0, 270, 710, 190);
        medsPane.setVisible(false);
        page3.add(medsPane);

        JLabel lblMedsHeader = new JLabel("Please List Down All Medications You're Currently Taking:");
        lblMedsHeader.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
        lblMedsHeader.setBounds(50, 2, 450, 30);
        medsPane.add(lblMedsHeader);

        JLabel medNum1 = new JLabel("1.");
        medNum1.setBounds(55, 38, 450, 20);
        medNum1.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
        medsPane.add(medNum1);

        JTextField med1 = new JTextField();
        med1.setBounds(74, 36, 500, 20);
        med1.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
        med1.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(80,80,80)));
        med1.setBackground(new Color(240,240,240));
        medsPane.add(med1);

        JLabel medNum2 = new JLabel("2.");
        medNum2.setBounds(55, 70, 450, 20);
        medNum2.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
        medsPane.add(medNum2);

        JTextField med2 = new JTextField();
        med2.setBounds(74, 68, 500, 20);
        med2.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
        med2.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(80,80,80)));
        med2.setBackground(new Color(240,240,240));
        medsPane.add(med2);

        JLabel medNum3 = new JLabel("3.");
        medNum3.setBounds(55, 102, 450, 20);
        medNum3.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
        medsPane.add(medNum3);

        JTextField med3 = new JTextField();
        med3.setBounds(74, 100, 500, 20);
        med3.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
        med3.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(80,80,80)));
        med3.setBackground(new Color(240,240,240));
        medsPane.add(med3);

        JLabel medNum4 = new JLabel("4.");
        medNum4.setBounds(55, 136, 450, 20);
        medNum4.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
        medsPane.add(medNum4);

        JTextField med4 = new JTextField();
        med4.setBounds(74, 132, 500, 20);
        med4.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
        med4.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(80,80,80)));
        med4.setBackground(new Color(240,240,240));
        medsPane.add(med4);

        JLabel medNum5 = new JLabel("5.");
        medNum5.setBounds(55, 170, 450, 20);
        medNum5.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
        medsPane.add(medNum5);

        JTextField med5 = new JTextField();
        med5.setBounds(74, 164, 500, 20);
        med5.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
        med5.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(80,80,80)));
        med5.setBackground(new Color(240,240,240));
        medsPane.add(med5);

        JLabel lblPage3 = new JLabel("3");
        lblPage3.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
        lblPage3.setBounds(350, 451, 50, 50);
        lblPage3.setForeground(Color.BLACK);
        page3.add(lblPage3);

        JButton next3 = new JButton(">");
        next3.setFont(new Font("Agency FB", Font.BOLD, 24));
        next3.setBounds(358, 461, 21, 26);
        next3.setBackground(new Color(240, 240, 240));
        next3.setVisible(false);
        next3.setForeground(Color.BLACK);
        next3.setBorderPainted(false);
        next3.setFocusPainted(false);
        next3.setContentAreaFilled(false);
        next3.setOpaque(true);
        next3.setMargin(new Insets(0, 0, 0, 0));
        next3.setVerticalAlignment(SwingConstants.CENTER);
        next3.setHorizontalAlignment(SwingConstants.CENTER);
        page3.add(next3);

        JButton back3 = new JButton("<");
        back3.setFont(new Font("Agency FB", Font.BOLD, 24));
        back3.setBounds(329, 460, 21, 26);
        back3.setBackground(new Color(240, 240, 240));
        back3.setForeground(Color.BLACK);
        back3.setBorderPainted(false);
        back3.setFocusPainted(false);
        back3.setContentAreaFilled(false);
        back3.setOpaque(true);
        back3.setMargin(new Insets(0, 0, 0, 0));
        back3.setVerticalAlignment(SwingConstants.CENTER);
        back3.setHorizontalAlignment(SwingConstants.CENTER);
        page3.add(back3);

        // Page 4 Initialization
        page4 = new JPanel(null);
        page4.setBounds(0, 0, 730, 590);
        page4.setVisible(false);
        add(page4);
        setComponentZOrder(page4, 0);

        JLabel lblMedicationsCont = new JLabel("Medications (Continued)");
        lblMedicationsCont.setFont(new Font("Trebuchet MS", Font.ITALIC, 16));
        lblMedicationsCont.setBounds(10, 5, 850, 30);
        page4.add(lblMedicationsCont);

        JSeparator separator7 = new JSeparator();
        separator7.setBounds(190, 22, 502, 2);
        separator7.setForeground(new Color(150, 150, 150));
        page4.add(separator7);

        JLabel medNum6 = new JLabel("6.");
        medNum6.setBounds(55, 58, 450, 20);
        medNum6.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
        page4.add(medNum6);

        JTextField med6 = new JTextField();
        med6.setBounds(74, 56, 500, 20);
        med6.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
        med6.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(80,80,80)));
        med6.setBackground(new Color(240,240,240));
        page4.add(med6);

        JLabel medNum7 = new JLabel("7.");
        medNum7.setBounds(55, 92, 450, 20);
        medNum7.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
        page4.add(medNum7);

        JTextField med7 = new JTextField();
        med7.setBounds(74, 90, 500, 20);
        med7.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
        med7.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(80,80,80)));
        med7.setBackground(new Color(240,240,240));
        page4.add(med7);

        JLabel medNum8 = new JLabel("8.");
        medNum8.setBounds(55, 124, 450, 20);
        medNum8.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
        page4.add(medNum8);

        JTextField med8 = new JTextField();
        med8.setBounds(74, 122, 500, 20);
        med8.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
        med8.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(80,80,80)));
        med8.setBackground(new Color(240,240,240));
        page4.add(med8);

        JLabel medNum9 = new JLabel("9.");
        medNum9.setBounds(55, 156, 450, 20);
        medNum9.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
        page4.add(medNum9);

        JTextField med9 = new JTextField();
        med9.setBounds(74, 154, 500, 20);
        med9.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
        med9.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(80,80,80)));
        med9.setBackground(new Color(240,240,240));
        page4.add(med9);

        JLabel medNum10 = new JLabel("10.");
        medNum10.setBounds(46, 188, 450, 20);
        medNum10.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
        page4.add(medNum10);

        JTextField med10 = new JTextField();
        med10.setBounds(74, 186, 500, 20);
        med10.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
        med10.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(80,80,80)));
        med10.setBackground(new Color(240,240,240));
        page4.add(med10);

        JLabel medNum11 = new JLabel("11.");
        medNum11.setBounds(46, 220, 450, 20);
        medNum11.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
        page4.add(medNum11);

        JTextField med11 = new JTextField();
        med11.setBounds(74, 218, 500, 20);
        med11.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
        med11.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(80,80,80)));
        med11.setBackground(new Color(240,240,240));
        page4.add(med11);

        JLabel medNum12 = new JLabel("12.");
        medNum12.setBounds(46, 252, 450, 20);
        medNum12.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
        page4.add(medNum12);

        JTextField med12 = new JTextField();
        med12.setBounds(74, 250, 500, 20);
        med12.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
        med12.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(80,80,80)));
        med12.setBackground(new Color(240,240,240));
        page4.add(med12);

        JLabel medNum13 = new JLabel("13.");
        medNum13.setBounds(46, 284, 450, 20);
        medNum13.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
        page4.add(medNum13);

        JTextField med13 = new JTextField();
        med13.setBounds(74, 282, 500, 20);
        med13.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
        med13.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(80,80,80)));
        med13.setBackground(new Color(240,240,240));
        page4.add(med13);

        JLabel medNum14 = new JLabel("14.");
        medNum14.setBounds(46, 316, 450, 20);
        medNum14.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
        page4.add(medNum14);

        JTextField med14 = new JTextField();
        med14.setBounds(74, 314, 500, 20);
        med14.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
        med14.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(80,80,80)));
        med14.setBackground(new Color(240,240,240));
        page4.add(med14);

        JLabel medNum15 = new JLabel("15.");
        medNum15.setBounds(46, 348, 450, 20);
        medNum15.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
        page4.add(medNum15);

        JTextField med15 = new JTextField();
        med15.setBounds(74, 346, 500, 20);
        med15.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
        med15.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(80,80,80)));
        med15.setBackground(new Color(240,240,240));
        page4.add(med15);

        JLabel lblPage4 = new JLabel("4");
        lblPage4.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
        lblPage4.setBounds(350, 451, 50, 50);
        lblPage4.setForeground(Color.BLACK);
        page4.add(lblPage4);

        JButton back4 = new JButton("<");
        back4.setFont(new Font("Agency FB", Font.BOLD, 24));
        back4.setBounds(329, 460, 21, 26);
        back4.setBackground(new Color(240, 240, 240));
        back4.setForeground(Color.BLACK);
        back4.setBorderPainted(false);
        back4.setFocusPainted(false);
        back4.setContentAreaFilled(false);
        back4.setOpaque(true);
        back4.setMargin(new Insets(0, 0, 0, 0));
        back4.setVerticalAlignment(SwingConstants.CENTER);
        back4.setHorizontalAlignment(SwingConstants.CENTER);
        page4.add(back4);

        // Navigation Logic
        next1.addActionListener(e -> {
            setPageComponentsVisible(false);
            page2.setVisible(true);
            page3.setVisible(false);
            page4.setVisible(false);
        });

        next2.addActionListener(e -> {
            page2.setVisible(false);
            page3.setVisible(true);
            page4.setVisible(false);
            setPageComponentsVisible(false);
        });

        next3.addActionListener(e -> {
            page2.setVisible(false);
            page3.setVisible(false);
            page4.setVisible(true);
            setPageComponentsVisible(false);
        });

        back2.addActionListener(e -> {
            page2.setVisible(false);
            setPageComponentsVisible(true);
            page3.setVisible(false);
            page4.setVisible(false);
        });

        back3.addActionListener(e -> {
            page3.setVisible(false);
            page2.setVisible(true);
            page4.setVisible(false);
            setPageComponentsVisible(false);
        });

        back4.addActionListener(e -> {
            page4.setVisible(false);
            page3.setVisible(true);
            page2.setVisible(false);
            setPageComponentsVisible(false);
        });

        takingMeds.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    medsPane.setVisible(true);
                    next3.setVisible(true);
                } else {
                    medsPane.setVisible(false);
                    next3.setVisible(false);
                    page4.setVisible(false);
                }
            }
        });
    }

    // Helper method to toggle visibility of page1 components
    private void setPageComponentsVisible(boolean visible) {
        for (Component comp : getComponents()) {
            if (comp != page2 && comp != page3 && comp != page4) {
                comp.setVisible(visible);
            }
        }
    }
}