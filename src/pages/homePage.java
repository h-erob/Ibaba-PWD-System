package pages;

import dal.members.membersDAO;
import db.database;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Path2D;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class homePage extends JPanel {
    private final DefaultTableModel bdModel;
    private final DefaultTableModel expModel;
    private final DefaultTableModel fullExpModel;
    private final JLabel totalMemNum;
    private final JLabel womenLbl;
    private final JLabel menLbl;
    private final JLabel newMemSub;
    private final JLabel activeMemSub;
    private final JLabel inactiveMemSub;
    private DefaultTableModel newMemModel;
    private DefaultTableModel activeMemModel;
    private DefaultTableModel inactiveMemModel;

    public homePage() {
        setLayout(null);
        setBorder(new EmptyBorder(10, 10, 10, 10));
        setBackground(new Color(227, 227, 227));

        // Birthday Panel
        JPanel birthdayPanel = createRoundedPanel();
        birthdayPanel.setBounds(24, 20, 528, 248);
        birthdayPanel.setLayout(null);
        add(birthdayPanel);

        JLabel bdIcon = new JLabel("🎂");
        bdIcon.setBounds(22, 22, 20, 20);
        bdIcon.setFont(new Font("", Font.PLAIN, 17));
        birthdayPanel.add(bdIcon);

        JLabel bdHeader = new JLabel("This Month's Birthday Celebrant");
        bdHeader.setBounds(50, 20, 300, 22);
        bdHeader.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
        birthdayPanel.add(bdHeader);

        JLabel bdSub = new JLabel("PWD Members Celebrating this month");
        bdSub.setBounds(51, 35, 300, 22);
        bdSub.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
        birthdayPanel.add(bdSub);

        JLabel bdTableHeader = new JLabel("       NAME                                   PWD ID NO.               DATE") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(254, 239, 25));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 12, 12);
                super.paintComponent(g);
                g2.dispose();
            }
        };
        bdTableHeader.setBounds(22, 65, 480, 30);
        bdTableHeader.setOpaque(false);
        bdTableHeader.setForeground(Color.BLACK);
        bdTableHeader.setFont(new Font("Arial", Font.BOLD, 16));
        bdTableHeader.setHorizontalAlignment(SwingConstants.LEFT);
        birthdayPanel.add(bdTableHeader);

        bdModel = new DefaultTableModel(new String[]{"name", "PWD ID No.", "Date"}, 0);
        JTable table = new JTable(bdModel);
        table.setRowHeight(36);
        table.getColumnModel().getColumn(0).setPreferredWidth(190);
        table.getColumnModel().getColumn(1).setPreferredWidth(183);
        table.getColumnModel().getColumn(2).setPreferredWidth(70);
        table.setTableHeader(null);
        table.setShowGrid(false);
        table.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
        table.setBackground(new Color(238, 235, 235));
        table.setForeground(new Color(58, 58, 58));
        table.setSelectionBackground(new Color(220, 240, 255));
        table.setSelectionForeground(Color.BLACK);

        JScrollPane scrollPane = new RoundedScrollPane(table);
        scrollPane.setBounds(22, 108, 480, 98);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        birthdayPanel.add(scrollPane);

        JButton bdBtn = new JButton("See All");
        bdBtn.setBounds(223, 209, 80, 30);
        bdBtn.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
        bdBtn.setForeground(new Color(56, 56, 56));
        bdBtn.setBorderPainted(false);
        bdBtn.setContentAreaFilled(false);
        bdBtn.setFocusPainted(false);
        bdBtn.setOpaque(true);
        birthdayPanel.add(bdBtn);

        JDialog fullBdDialog = new JDialog(SwingUtilities.getWindowAncestor(this), "Full Birthday List", Dialog.ModalityType.APPLICATION_MODAL);
        fullBdDialog.setSize(700, 500);
        fullBdDialog.setLayout(null);
        fullBdDialog.setLocationRelativeTo(this);
        fullBdDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        fullBdDialog.getContentPane().setBackground(new Color(238, 235, 235));

        JLabel FullBdTableHeader = new JLabel("    NAME                                                             PWD ID NO.                         DATE") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(254, 239, 25));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 12, 12);
                super.paintComponent(g);
                g2.dispose();
            }
        };
        FullBdTableHeader.setBounds(23, 15, 635, 30);
        FullBdTableHeader.setOpaque(false);
        FullBdTableHeader.setForeground(Color.BLACK);
        FullBdTableHeader.setFont(new Font("Arial", Font.BOLD, 16));
        FullBdTableHeader.setHorizontalAlignment(SwingConstants.LEFT);
        fullBdDialog.add(FullBdTableHeader);

        JTable fullBdTable = new JTable(bdModel);
        fullBdTable.setRowHeight(36);
        fullBdTable.getColumnModel().getColumn(0).setPreferredWidth(280);
        fullBdTable.getColumnModel().getColumn(1).setPreferredWidth(200);
        fullBdTable.getColumnModel().getColumn(2).setPreferredWidth(60);
        fullBdTable.setTableHeader(null);
        fullBdTable.setShowGrid(false);
        fullBdTable.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
        fullBdTable.setBackground(new Color(238, 235, 235));
        fullBdTable.setForeground(new Color(58, 58, 58));
        fullBdTable.setSelectionBackground(new Color(220, 240, 255));
        fullBdTable.setSelectionForeground(Color.BLACK);

        JScrollPane fullBdScrollPane = new RoundedScrollPane(fullBdTable);
        fullBdScrollPane.setBounds(23, 55, 635, 375);
        fullBdDialog.add(fullBdScrollPane);

        bdBtn.addActionListener(e -> {
            mainPage.instance.showDim();
            fullBdDialog.setVisible(true);
            mainPage.instance.hideDim();
        });

        // Expiring Members Panel
        JPanel expiringMemPanel = createRoundedPanel();
        expiringMemPanel.setBounds(24, 285, 528, 245);
        add(expiringMemPanel);

        JLabel expIcon = new JLabel("⚠️");
        expIcon.setBounds(22, 5, 60, 60);
        expIcon.setFont(new Font("", Font.PLAIN, 19));
        expiringMemPanel.add(expIcon);

        JLabel expHeader = new JLabel("Expiring and Expired PWD IDs");
        expHeader.setBounds(50, 22, 300, 22);
        expHeader.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
        expiringMemPanel.add(expHeader);

        JLabel expSub = new JLabel("List of members whose PWD IDs are expired or nearing expiration");
        expSub.setBounds(51, 39, 400, 22);
        expSub.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
        expiringMemPanel.add(expSub);

        JLabel expTableHeader = new JLabel("       NAME                                   PWD ID NO.               DATE") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(254, 239, 25));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 12, 12);
                super.paintComponent(g);
                g2.dispose();
            }
        };
        expTableHeader.setBounds(22, 65, 480, 30);
        expTableHeader.setOpaque(false);
        expTableHeader.setForeground(Color.BLACK);
        expTableHeader.setFont(new Font("Arial", Font.BOLD, 16));
        expTableHeader.setHorizontalAlignment(SwingConstants.LEFT);
        expiringMemPanel.add(expTableHeader);

        expModel = new DefaultTableModel(new String[]{"name", "PWD ID No.", "Date"}, 0);
        JTable expTable = new JTable(expModel);
        expTable.setRowHeight(39);
        expTable.getColumnModel().getColumn(0).setPreferredWidth(190);
        expTable.getColumnModel().getColumn(1).setPreferredWidth(183);
        expTable.getColumnModel().getColumn(2).setPreferredWidth(70);
        expTable.setTableHeader(null);
        expTable.setShowGrid(false);
        expTable.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
        expTable.setBackground(new Color(238, 235, 235));
        expTable.setForeground(new Color(58, 58, 58));
        expTable.setSelectionBackground(new Color(220, 240, 255));
        expTable.setSelectionForeground(Color.BLACK);

        JScrollPane expScrollPane = new RoundedScrollPane(expTable);
        expScrollPane.setBounds(22, 103, 480, 98);
        expScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        expiringMemPanel.add(expScrollPane);

        JButton expBtn = new JButton("See All");
        expBtn.setBounds(214, 206, 100, 30);
        expBtn.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
        expBtn.setForeground(new Color(56, 56, 56));
        expBtn.setBorderPainted(false);
        expBtn.setContentAreaFilled(false);
        expBtn.setFocusPainted(false);
        expBtn.setOpaque(true);
        expiringMemPanel.add(expBtn);

        JDialog fullExpDialog = new JDialog(SwingUtilities.getWindowAncestor(this), "Full Expiring and Expired Membership List", Dialog.ModalityType.APPLICATION_MODAL);
        fullExpDialog.setSize(720, 500);
        fullExpDialog.setLayout(null);
        fullExpDialog.setLocationRelativeTo(this);
        fullExpDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        fullExpDialog.getContentPane().setBackground(new Color(238, 235, 235));

        JLabel FullExpTableHeader = new JLabel("    NAME                                                   PWD ID NO.               DATE") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(254, 239, 25));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 12, 12);
                super.paintComponent(g);
                g2.dispose();
            }
        };
        FullExpTableHeader.setBounds(23, 15, 655, 30);
        FullExpTableHeader.setOpaque(false);
        FullExpTableHeader.setForeground(Color.BLACK);
        FullExpTableHeader.setFont(new Font("Arial", Font.BOLD, 16));
        FullExpTableHeader.setHorizontalAlignment(SwingConstants.LEFT);
        fullExpDialog.add(FullExpTableHeader);

        fullExpModel = new DefaultTableModel(new String[]{"name", "PWD ID No.", "Date", "Action"}, 0);
        JTable fullExpTable = new JTable(fullExpModel);
        fullExpTable.setRowHeight(36);
        fullExpTable.getColumnModel().getColumn(0).setPreferredWidth(260);
        fullExpTable.getColumnModel().getColumn(1).setPreferredWidth(195);
        fullExpTable.getColumnModel().getColumn(2).setPreferredWidth(95);
        fullExpTable.getColumnModel().getColumn(3).setPreferredWidth(100);
        fullExpTable.setTableHeader(null);
        fullExpTable.setShowGrid(false);
        fullExpTable.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
        fullExpTable.setBackground(new Color(238, 235, 235));
        fullExpTable.setForeground(new Color(58, 58, 58));
        fullExpTable.setSelectionBackground(new Color(220, 240, 255));
        fullExpTable.setSelectionForeground(Color.BLACK);
        fullExpTable.getColumnModel().getColumn(3).setCellRenderer(new ButtonRenderer());
        fullExpTable.getColumnModel().getColumn(3).setCellEditor(new ButtonEditor());

        JScrollPane fullExpScrollPane = new RoundedScrollPane(fullExpTable);
        fullExpScrollPane.setBounds(23, 55, 655, 375);
        fullExpDialog.add(fullExpScrollPane);

        expBtn.addActionListener(e -> {
            mainPage.instance.showDim();
            fullExpDialog.setVisible(true);
            mainPage.instance.hideDim();
        });

        // Quick Access Panel
        JPanel quickAccessPanel = createRoundedPanel();
        quickAccessPanel.setBounds(573, 20, 390, 90);
        add(quickAccessPanel);

        JLabel qaIcon = new JLabel("👆");
        qaIcon.setBounds(26, 10, 30, 28);
        qaIcon.setFont(new Font("", Font.BOLD, 21));
        quickAccessPanel.add(qaIcon);

        JLabel qaHeader = new JLabel("Member's List | Quick Access Bar");
        qaHeader.setBounds(57, 12, 310, 22);
        qaHeader.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
        quickAccessPanel.add(qaHeader);

        RoundedButton qaAddBtn = new RoundedButton("+ ADD MEMBER", new Color(73, 230, 127));
        qaAddBtn.setBounds(52, 42, 142, 36);
        quickAccessPanel.add(qaAddBtn);
        qaAddBtn.addActionListener(e -> {
            pages.records_membersbtn.addMembersPage.launch(null, this);
            mainPage.instance.showDim();
        });

        RoundedButton qaUpdBtn = new RoundedButton("🔺 UPDATE", new Color(0, 202, 238));
        qaUpdBtn.setBounds(200, 42, 120, 36);
        quickAccessPanel.add(qaUpdBtn);
        qaUpdBtn.addActionListener(e -> mainPage.instance.selectMemberListButton());

        // Member Status Panel
        JPanel notifPane = createRoundedPanel();
        notifPane.setBounds(573, 122, 390, 248);
        add(notifPane);

        JLabel notifIcon = new JLabel("🔔");
        notifIcon.setBounds(26, 10, 30, 28);
        notifIcon.setFont(new Font("", Font.BOLD, 21));
        notifPane.add(notifIcon);

        JLabel notifHeader = new JLabel("Member Status");
        notifHeader.setBounds(55, 13, 310, 22);
        notifHeader.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
        notifPane.add(notifHeader);

        // New Members Panel
        JPanel notifNewMem = createRoundedPanel();
        notifNewMem.setBounds(23, 47, 345, 55);
        notifNewMem.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        notifPane.add(notifNewMem);

        ImageIcon newMem = new ImageIcon("imgs/newMem.png");
        JLabel newImg = new JLabel(newMem);
        newImg.setBounds(10, 7, 40, 40);
        notifNewMem.add(newImg);

        JLabel newMemHeader = new JLabel("New Members Added");
        newMemHeader.setBounds(55, 11, 300, 20);
        newMemHeader.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
        notifNewMem.add(newMemHeader);

        newMemSub = new JLabel("0 New Members");
        newMemSub.setBounds(56, 25, 300, 20);
        newMemSub.setForeground(new Color(90, 90, 90));
        newMemSub.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
        notifNewMem.add(newMemSub);

        JButton newMemBtn = new JButton("▶");
        newMemBtn.setBounds(293, 11, 50, 30);
        newMemBtn.setFont(new Font(" ", Font.BOLD, 14));
        newMemBtn.setForeground(new Color(56, 56, 56));
        newMemBtn.setBorderPainted(false);
        newMemBtn.setContentAreaFilled(false);
        newMemBtn.setFocusPainted(false);
        notifNewMem.add(newMemBtn);

        // Active Members Panel
        JPanel activeMemPanel = createRoundedPanel();
        activeMemPanel.setBounds(23, 110, 345, 55);
        activeMemPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        notifPane.add(activeMemPanel);

        ImageIcon activeImg = new ImageIcon("imgs/activeMem.png");
        JLabel activeIMG = new JLabel(activeImg);
        activeIMG.setBounds(10, 7, 40, 40);
        activeMemPanel.add(activeIMG);

        JLabel activeMemHeader = new JLabel("Active Members");
        activeMemHeader.setBounds(55, 11, 300, 20);
        activeMemHeader.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
        activeMemPanel.add(activeMemHeader);

        activeMemSub = new JLabel("0 Members are Active");
        activeMemSub.setBounds(56, 25, 300, 20);
        activeMemSub.setForeground(new Color(90, 90, 90));
        activeMemSub.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
        activeMemPanel.add(activeMemSub);

        JButton activeMemBtn = new JButton("▶");
        activeMemBtn.setBounds(293, 11, 50, 30);
        activeMemBtn.setFont(new Font("", Font.BOLD, 14));
        activeMemBtn.setForeground(new Color(56, 56, 56));
        activeMemBtn.setBorderPainted(false);
        activeMemBtn.setContentAreaFilled(false);
        activeMemBtn.setFocusPainted(false);
        activeMemPanel.add(activeMemBtn);

        // Inactive Members Panel
        JPanel notifInactiveMem = createRoundedPanel();
        notifInactiveMem.setBounds(23, 175, 345, 55);
        notifInactiveMem.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        notifPane.add(notifInactiveMem);

        ImageIcon inactiveMem = new ImageIcon("imgs/inactiveMem.png");
        JLabel inactImg = new JLabel(inactiveMem);
        inactImg.setBounds(10, 7, 40, 40);
        notifInactiveMem.add(inactImg);

        JLabel inactiveMemHeader = new JLabel("Inactive Members");
        inactiveMemHeader.setBounds(55, 11, 300, 20);
        inactiveMemHeader.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
        notifInactiveMem.add(inactiveMemHeader);

        inactiveMemSub = new JLabel("0 Members are Inactive");
        inactiveMemSub.setBounds(56, 25, 300, 20);
        inactiveMemSub.setForeground(new Color(90, 90, 90));
        inactiveMemSub.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
        notifInactiveMem.add(inactiveMemSub);

        JButton inactiveMemBtn = new JButton("▶");
        inactiveMemBtn.setBounds(293, 11, 50, 30);
        inactiveMemBtn.setFont(new Font("", Font.BOLD, 14));
        inactiveMemBtn.setForeground(new Color(56, 56, 56));
        inactiveMemBtn.setBorderPainted(false);
        inactiveMemBtn.setContentAreaFilled(false);
        inactiveMemBtn.setFocusPainted(false);
        notifInactiveMem.add(inactiveMemBtn);

        // New Members Dialog
        JDialog fullNewMemDialog = new JDialog(SwingUtilities.getWindowAncestor(this), "New Members List", Dialog.ModalityType.APPLICATION_MODAL);
        fullNewMemDialog.setSize(700, 500);
        fullNewMemDialog.setLayout(null);
        fullNewMemDialog.setLocationRelativeTo(this);
        fullNewMemDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        fullNewMemDialog.getContentPane().setBackground(new Color(238, 235, 235));
        fullNewMemDialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                mainPage.instance.hideDim();
            }
        });

        JLabel fullNewMemTableHeader = new JLabel("    NAME                                                        PWD ID NO.                          DATE") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(254, 239, 25));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 12, 12);
                super.paintComponent(g);
                g2.dispose();
            }
        };
        fullNewMemTableHeader.setBounds(23, 15, 637, 30);
        fullNewMemTableHeader.setOpaque(false);
        fullNewMemTableHeader.setForeground(Color.BLACK);
        fullNewMemTableHeader.setFont(new Font("Arial", Font.BOLD, 16));
        fullNewMemTableHeader.setHorizontalAlignment(SwingConstants.LEFT);
        fullNewMemDialog.add(fullNewMemTableHeader);

        newMemModel = new DefaultTableModel(new String[]{"Name", "PWD ID No.", "Date Issued"}, 0);
        JTable fullNewMemTable = new JTable(newMemModel);
        fullNewMemTable.setRowHeight(36);
        fullNewMemTable.getColumnModel().getColumn(0).setPreferredWidth(215);
        fullNewMemTable.getColumnModel().getColumn(1).setPreferredWidth(178);
        fullNewMemTable.getColumnModel().getColumn(2).setPreferredWidth(40);
        fullNewMemTable.getColumnModel().getColumn(0).setResizable(false);
        fullNewMemTable.getColumnModel().getColumn(1).setResizable(false);
        fullNewMemTable.getColumnModel().getColumn(2).setResizable(false);
        fullNewMemTable.setTableHeader(null);
        fullNewMemTable.setShowGrid(false);
        fullNewMemTable.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
        fullNewMemTable.setBackground(new Color(238, 235, 235));
        fullNewMemTable.setForeground(new Color(58, 58, 58));
        fullNewMemTable.setSelectionBackground(new Color(220, 240, 255));
        fullNewMemTable.setSelectionForeground(Color.BLACK);

        JScrollPane fullNewMemScrollPane = new RoundedScrollPane(fullNewMemTable);
        fullNewMemScrollPane.setBounds(23, 55, 635, 375);
        fullNewMemDialog.add(fullNewMemScrollPane);

        // Active Members Dialog
        JDialog fullActiveMemDialog = new JDialog(SwingUtilities.getWindowAncestor(this), "Active Members List", Dialog.ModalityType.APPLICATION_MODAL);
        fullActiveMemDialog.setSize(700, 500);
        fullActiveMemDialog.setLayout(null);
        fullActiveMemDialog.setLocationRelativeTo(this);
        fullActiveMemDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        fullActiveMemDialog.getContentPane().setBackground(new Color(238, 235, 235));
        fullActiveMemDialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                mainPage.instance.hideDim();
            }
        });

        JLabel fullActiveMemTableHeader = new JLabel("    NAME                                                     PWD ID NO.             TOTAL ATTENDANCE") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(254, 239, 25));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 12, 12);
                super.paintComponent(g);
                g2.dispose();
            }
        };
        fullActiveMemTableHeader.setBounds(23, 15, 638, 30);
        fullActiveMemTableHeader.setOpaque(false);
        fullActiveMemTableHeader.setForeground(Color.BLACK);
        fullActiveMemTableHeader.setFont(new Font("Arial", Font.BOLD, 16));
        fullActiveMemTableHeader.setHorizontalAlignment(SwingConstants.LEFT);
        fullActiveMemDialog.add(fullActiveMemTableHeader);

        activeMemModel = new DefaultTableModel(new String[]{"Name", "PWD ID No.", "Total Attendance"}, 0);
        JTable fullActiveMemTable = new JTable(activeMemModel);
        fullActiveMemTable.setRowHeight(36);
        fullActiveMemTable.getColumnModel().getColumn(0).setPreferredWidth(220);
        fullActiveMemTable.getColumnModel().getColumn(1).setPreferredWidth(225);
        fullActiveMemTable.getColumnModel().getColumn(2).setPreferredWidth(30);
        fullActiveMemTable.getColumnModel().getColumn(0).setResizable(false);
        fullActiveMemTable.getColumnModel().getColumn(1).setResizable(false);
        fullActiveMemTable.getColumnModel().getColumn(2).setResizable(false);
        fullActiveMemTable.setTableHeader(null);
        fullActiveMemTable.setShowGrid(false);
        fullActiveMemTable.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
        fullActiveMemTable.setBackground(new Color(238, 235, 235));
        fullActiveMemTable.setForeground(new Color(58, 58, 58));
        fullActiveMemTable.setSelectionBackground(new Color(220, 240, 255));
        fullActiveMemTable.setSelectionForeground(Color.BLACK);

        JScrollPane fullActiveMemScrollPane = new RoundedScrollPane(fullActiveMemTable);
        fullActiveMemScrollPane.setBounds(23, 55, 635, 375);
        fullActiveMemDialog.add(fullActiveMemScrollPane);

        // Inactive Members Dialog
        JDialog fullInactiveMemDialog = new JDialog(SwingUtilities.getWindowAncestor(this), "Inactive Members List", Dialog.ModalityType.APPLICATION_MODAL);
        fullInactiveMemDialog.setSize(700, 500);
        fullInactiveMemDialog.setLayout(null);
        fullInactiveMemDialog.setLocationRelativeTo(this);
        fullInactiveMemDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        fullInactiveMemDialog.getContentPane().setBackground(new Color(238, 235, 235));
        fullInactiveMemDialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                mainPage.instance.hideDim();
            }
        });

        JLabel fullInactiveMemTableHeader = new JLabel("    NAME                                                    PWD ID NO.             TOTAL ATTENDANCE") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(254, 239, 25));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 12, 12);
                super.paintComponent(g);
                g2.dispose();
            }
        };
        fullInactiveMemTableHeader.setBounds(23, 15, 638, 30);
        fullInactiveMemTableHeader.setOpaque(false);
        fullInactiveMemTableHeader.setForeground(Color.BLACK);
        fullInactiveMemTableHeader.setFont(new Font("Arial", Font.BOLD, 16));
        fullInactiveMemTableHeader.setHorizontalAlignment(SwingConstants.LEFT);
        fullInactiveMemDialog.add(fullInactiveMemTableHeader);

        inactiveMemModel = new DefaultTableModel(new String[]{"Name", "PWD ID No.", "Total Attendance"}, 0);
        JTable fullInactiveMemTable = new JTable(inactiveMemModel);
        fullInactiveMemTable.setRowHeight(36);
        fullInactiveMemTable.getColumnModel().getColumn(0).setPreferredWidth(220);
        fullInactiveMemTable.getColumnModel().getColumn(1).setPreferredWidth(225);
        fullInactiveMemTable.getColumnModel().getColumn(2).setPreferredWidth(30);
        fullInactiveMemTable.getColumnModel().getColumn(0).setResizable(false);
        fullInactiveMemTable.getColumnModel().getColumn(1).setResizable(false);
        fullInactiveMemTable.getColumnModel().getColumn(2).setResizable(false);
        fullInactiveMemTable.setTableHeader(null);
        fullInactiveMemTable.setShowGrid(false);
        fullInactiveMemTable.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
        fullInactiveMemTable.setBackground(new Color(238, 235, 235));
        fullInactiveMemTable.setForeground(new Color(58, 58, 58));
        fullInactiveMemTable.setSelectionBackground(new Color(220, 240, 255));
        fullInactiveMemTable.setSelectionForeground(Color.BLACK);

        JScrollPane fullInactiveMemScrollPane = new RoundedScrollPane(fullInactiveMemTable);
        fullInactiveMemScrollPane.setBounds(23, 55, 635, 375);
        fullInactiveMemDialog.add(fullInactiveMemScrollPane);

        // Add MouseListeners to make panels clickable
        notifNewMem.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                populateNewMembersTable();
                fullNewMemDialog.setVisible(true);
            }
        });

        activeMemPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                populateActiveMembersTable();
                fullActiveMemDialog.setVisible(true);
            }
        });

        notifInactiveMem.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                populateInactiveMembersTable();
                fullInactiveMemDialog.setVisible(true);
            }
        });

        // Add ActionListeners to buttons
        newMemBtn.addActionListener(e -> {
            mainPage.instance.showDim();
            populateNewMembersTable();
            fullNewMemDialog.setVisible(true);
        });

        activeMemBtn.addActionListener(e -> {
            mainPage.instance.showDim();
            populateActiveMembersTable();
            fullActiveMemDialog.setVisible(true);
        });

        inactiveMemBtn.addActionListener(e -> {
            mainPage.instance.showDim();
            populateInactiveMembersTable();
            fullInactiveMemDialog.setVisible(true);
        });

        // Total Member Pane
        JPanel totalMembPane = createRoundedPanel();
        totalMembPane.setBounds(620, 380, 166, 155);
        add(totalMembPane);

        JLabel totalMemHeader = new JLabel("Total Registered PWDs");
        totalMemHeader.setBounds(10, 10, 310, 22);
        totalMemHeader.setFont(new Font("Trebuchet MS", Font.ITALIC, 14));
        totalMembPane.add(totalMemHeader);

        SimpleDateFormat monthYear = new SimpleDateFormat("MMMM yyyy");
        Date now = new Date();
        String moYearString = monthYear.format(now);

        JLabel totalMemSub = new JLabel("As of " + moYearString);
        totalMemSub.setBounds(10, 25, 310, 22);
        totalMemSub.setForeground(new Color(90, 90, 90));
        totalMemSub.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
        totalMembPane.add(totalMemSub);

        JSeparator separator = new JSeparator();
        separator.setBounds(10, 50, 140, 3);
        separator.setForeground(new Color(80, 80, 80));
        totalMembPane.add(separator);

        totalMemNum = new JLabel("0");
        totalMemNum.setBounds(0, 58, 166, 59);
        totalMemNum.setForeground(new Color(56, 113, 193));
        totalMemNum.setFont(new Font("Trebuchet MS", Font.BOLD, 58));
        totalMemNum.setHorizontalAlignment(JLabel.CENTER);
        totalMembPane.add(totalMemNum);

        JLabel totalMemText = new JLabel("PWD Members");
        totalMemText.setBounds(0, 123, 166, 17);
        totalMemText.setForeground(new Color(56, 113, 193));
        totalMemText.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
        totalMemText.setHorizontalAlignment(JLabel.CENTER);
        totalMembPane.add(totalMemText);

        // Population by Sex Pane
        JPanel sexPopuPane = createRoundedPanel();
        sexPopuPane.setBounds(794, 380, 163, 155);
        add(sexPopuPane);

        JLabel sexPopuHeader = new JLabel("Population by Sex");
        sexPopuHeader.setBounds(10, 10, 310, 22);
        sexPopuHeader.setFont(new Font("Trebuchet MS", Font.ITALIC, 14));
        sexPopuPane.add(sexPopuHeader);

        JLabel sexPopuSub = new JLabel("As of " + moYearString);
        sexPopuSub.setBounds(10, 25, 310, 22);
        sexPopuSub.setForeground(new Color(90, 90, 90));
        sexPopuSub.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
        sexPopuPane.add(sexPopuSub);

        ImageIcon femaleIcon = new ImageIcon("imgs/femaleLogo.png");
        Image scaledFemImg = femaleIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        ImageIcon scaledFemIcon = new ImageIcon(scaledFemImg);

        womenLbl = new JLabel("0", scaledFemIcon, JLabel.CENTER);
        womenLbl.setBounds(1, 50, 161, 52);
        womenLbl.setOpaque(true);
        womenLbl.setForeground(Color.WHITE);
        womenLbl.setBackground(new Color(255, 105, 192));
        womenLbl.setFont(new Font("Arial", Font.BOLD, 46));
        womenLbl.setHorizontalAlignment(JLabel.CENTER);
        sexPopuPane.add(womenLbl);

        ImageIcon maleIcon = new ImageIcon("imgs/maleLogo.png");
        Image scaledMaleImg = maleIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        ImageIcon scaledMaleIcon = new ImageIcon(scaledMaleImg);

        menLbl = new JLabel("0", scaledMaleIcon, JLabel.CENTER) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                int w = getWidth();
                int h = getHeight();
                int arc = 18;
                Path2D path = new Path2D.Float();
                path.moveTo(0, 0);
                path.lineTo(w, 0);
                path.lineTo(w, h - arc);
                path.quadTo(w, h, w - arc, h);
                path.lineTo(arc, h);
                path.quadTo(0, h, 0, h - arc);
                path.closePath();
                g2.setColor(getBackground());
                g2.fill(path);
                g2.dispose();
                super.paintComponent(g);
            }

            @Override
            public boolean isOpaque() {
                return false;
            }
        };
        menLbl.setBounds(1, 102, 161, 52);
        menLbl.setBackground(new Color(0, 202, 238));
        menLbl.setForeground(Color.WHITE);
        menLbl.setFont(new Font("Arial", Font.BOLD, 46));
        menLbl.setHorizontalAlignment(JLabel.CENTER);
        menLbl.setIconTextGap(10);
        menLbl.setHorizontalTextPosition(SwingConstants.RIGHT);
        sexPopuPane.add(menLbl);

        // Load initial data
        reloadData();
    }

    public void reloadData() {
        try (Connection conn = database.getConnection()) {
            membersDAO membersDAO = new membersDAO(conn);
            List<membersDAO.MemberData> members = membersDAO.getMembers();
            SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yy");
            Calendar now = Calendar.getInstance();
            int currentMonth = now.get(Calendar.MONTH) + 1;
            Calendar threeMonthsFromNow = Calendar.getInstance();
            threeMonthsFromNow.add(Calendar.MONTH, 3);
            Calendar twoMonthsAgo = Calendar.getInstance();
            twoMonthsAgo.add(Calendar.MONTH, -2);
            String currentYear = String.valueOf(now.get(Calendar.YEAR));

            // Update member statuses if ID has expired
            Date currentDate = new Date();
            for (membersDAO.MemberData member : members) {
                if (member.status != null && (member.status.equals("Alive") || member.status.equals("Renewed"))) {
                    if (member.idValidUntil != null && member.idValidUntil.before(currentDate)) {
                        membersDAO.updateMemberStatus(member.memberId, "Expired");
                        member.status = "Expired";
                    }
                }
            }

            // Clear existing data
            bdModel.setRowCount(0);
            expModel.setRowCount(0);
            fullExpModel.setRowCount(0);

            // Counters for statistics
            int totalMembers = 0;
            int femaleCount = 0;
            int maleCount = 0;
            int newMembersCount = 0;

            // Process members
            for (membersDAO.MemberData member : members) {
                if (member.status != null && !"Deceased".equals(member.status)) {
                    totalMembers++;

                    if ("Female".equals(member.sex)) {
                        femaleCount++;
                    } else if ("Male".equals(member.sex)) {
                        maleCount++;
                    }

                    if (member.birthdate != null) {
                        Calendar birthCal = Calendar.getInstance();
                        birthCal.setTime(member.birthdate);
                        int birthMonth = birthCal.get(Calendar.MONTH) + 1;
                        if (birthMonth == currentMonth) {
                            bdModel.addRow(new Object[]{
                                    member.fullName,
                                    member.pwdIdNumber,
                                    sdf.format(member.birthdate)
                            });
                        }
                    }

                    if (member.idValidUntil != null) {
                        Calendar expiryCal = Calendar.getInstance();
                        expiryCal.setTime(member.idValidUntil);
                        if (!expiryCal.after(threeMonthsFromNow)) {
                            boolean isExpired = member.idValidUntil.before(currentDate);
                            expModel.addRow(new Object[]{
                                    member.fullName,
                                    member.pwdIdNumber,
                                    sdf.format(member.idValidUntil)
                            });
                            fullExpModel.addRow(new Object[]{
                                    member.fullName,
                                    member.pwdIdNumber,
                                    sdf.format(member.idValidUntil),
                                    new ActionData(member.memberId, isExpired)
                            });
                        }
                    }

                    if (member.dateIssued != null && member.dateIssued.after(twoMonthsAgo.getTime())) {
                        newMembersCount++;
                    }
                }
            }

            // Active and Inactive Counts
            int N = membersDAO.getUniqueAttendanceDatesCount(currentYear);
            List<membersDAO.AttendanceRecord> attendanceRecords = membersDAO.getAttendanceRecordsForYear(currentYear);
            int activeCount = 0;
            int inactiveCount = 0;
            for (membersDAO.AttendanceRecord record : attendanceRecords) {
                if (!"Deceased".equals(record.status)) {
                    if (record.totalAttendance >= N - 1) {
                        activeCount++;
                    } else {
                        inactiveCount++;
                    }
                }
            }

            // Update UI components
            totalMemNum.setText(String.valueOf(totalMembers));
            womenLbl.setText(String.valueOf(femaleCount));
            menLbl.setText(String.valueOf(maleCount));
            newMemSub.setText(newMembersCount + " New Member(s)");
            activeMemSub.setText(activeCount + " Member(s) are Active");
            inactiveMemSub.setText(inactiveCount + " Member(s) are Inactive");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void renewMember(int memberId) {
        try (Connection conn = database.getConnection()) {
            membersDAO membersDAO = new membersDAO(conn);
            String selectSql = "SELECT id_valid_until FROM members WHERE member_id = ?";
            PreparedStatement selectPstmt = conn.prepareStatement(selectSql);
            selectPstmt.setInt(1, memberId);
            ResultSet rs = selectPstmt.executeQuery();
            if (rs.next()) {
                java.sql.Date currentValidUntil = rs.getDate("id_valid_until");
                Calendar cal = Calendar.getInstance();
                cal.setTime(currentValidUntil);
                cal.add(Calendar.YEAR, 5);
                java.sql.Date newValidUntil = new java.sql.Date(cal.getTimeInMillis());

                String updateSql = "UPDATE members SET status = 'Renewed', id_valid_until = ? WHERE member_id = ?";
                PreparedStatement updatePstmt = conn.prepareStatement(updateSql);
                updatePstmt.setDate(1, newValidUntil);
                updatePstmt.setInt(2, memberId);
                updatePstmt.executeUpdate();

                reloadData();
            }
            rs.close();
            selectPstmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error renewing member: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void populateNewMembersTable() {
        newMemModel.setRowCount(0);
        try (Connection conn = database.getConnection()) {
            membersDAO membersDAO = new membersDAO(conn);
            List<membersDAO.MemberData> members = membersDAO.getMembers();
            SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yy");
            Calendar twoMonthsAgo = Calendar.getInstance();
            twoMonthsAgo.add(Calendar.MONTH, -2);
            for (membersDAO.MemberData member : members) {
                if (member.dateIssued != null && member.dateIssued.after(twoMonthsAgo.getTime())) {
                    newMemModel.addRow(new Object[]{
                            member.fullName,
                            member.pwdIdNumber,
                            sdf.format(member.dateIssued)
                    });
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading new members: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void populateActiveMembersTable() {
        activeMemModel.setRowCount(0);
        try (Connection conn = database.getConnection()) {
            membersDAO membersDAO = new membersDAO(conn);
            String currentYear = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
            int N = membersDAO.getUniqueAttendanceDatesCount(currentYear);
            List<membersDAO.AttendanceRecord> records = membersDAO.getAttendanceRecordsForYear(currentYear);
            for (membersDAO.AttendanceRecord record : records) {
                if (!"Deceased".equals(record.status) && record.totalAttendance >= N - 1) {
                    activeMemModel.addRow(new Object[]{
                            record.fullName,
                            record.pwdIdNumber,
                            record.totalAttendance
                    });
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading active members: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void populateInactiveMembersTable() {
        inactiveMemModel.setRowCount(0);
        try (Connection conn = database.getConnection()) {
            membersDAO membersDAO = new membersDAO(conn);
            String currentYear = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
            int N = membersDAO.getUniqueAttendanceDatesCount(currentYear);
            List<membersDAO.AttendanceRecord> records = membersDAO.getAttendanceRecordsForYear(currentYear);
            for (membersDAO.AttendanceRecord record : records) {
                if (!"Deceased".equals(record.status) && record.totalAttendance < N - 1) {
                    inactiveMemModel.addRow(new Object[]{
                            record.fullName,
                            record.pwdIdNumber,
                            record.totalAttendance
                    });
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading inactive members: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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

    private static class RoundedScrollPane extends JScrollPane {
        private final int arc = 30;
        private final Color backgroundColor = new Color(238, 235, 235);
        private final Color borderColor = new Color(56, 56, 56);

        public RoundedScrollPane(JTable table) {
            super(table);
            setOpaque(false);
            getViewport().setOpaque(false);
            setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(backgroundColor);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), arc, arc);
            g2.setColor(borderColor);
            g2.setStroke(new BasicStroke(1));
            g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, arc, arc);
            g2.dispose();
        }
    }

    private static class RoundedButton extends JButton {
        private final int radius = 20;
        private final Color bgColor;

        public RoundedButton(String text, Color bgColor) {
            super(text);
            this.bgColor = bgColor;
            setFont(new Font(" ", Font.BOLD, 14));
            setForeground(new Color(56, 56, 56));
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
            super.paintComponent(g);
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

    private static class ActionData {
        int memberId;
        boolean isExpired;

        ActionData(int memberId, boolean isExpired) {
            this.memberId = memberId;
            this.isExpired = isExpired;
        }
    }

    private class ButtonRenderer extends JPanel implements javax.swing.table.TableCellRenderer {
        private JButton button;

        public ButtonRenderer() {
            setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
            button = new RoundedButton("Renew", new Color(40, 40, 40));
            button.setForeground(new Color(240,240,240));
            add(button);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            if (value instanceof ActionData) {
                ActionData data = (ActionData) value;
                button.setEnabled(data.isExpired);
            } else {
                button.setEnabled(false);
            }
            return this;
        }
    }

    private class ButtonEditor extends javax.swing.AbstractCellEditor implements javax.swing.table.TableCellEditor {
        private JPanel panel;
        private JButton button;
        private ActionData data;

        public ButtonEditor() {
            panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
            button = new RoundedButton("Renew", new Color(40, 40, 40));
            button.setForeground(new Color(240,240,240));
            panel.add(button);
            button.addActionListener(e -> {
                if (data != null && data.isExpired) {
                    renewMember(data.memberId);
                }
                fireEditingStopped();
            });
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            if (value instanceof ActionData) {
                data = (ActionData) value;
                button.setEnabled(data.isExpired);
            } else {
                data = null;
                button.setEnabled(false);
            }
            return panel;
        }

        @Override
        public Object getCellEditorValue() {
            return data;
        }
    }
}