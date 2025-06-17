package pages;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Path2D;
import java.text.SimpleDateFormat;
import java.util.Date;

public class homePage extends JPanel {

    public homePage() {
        setLayout(null);
        setBorder(new EmptyBorder(10, 10, 10, 10));
        setBackground(new Color(227, 227, 227));

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

        JLabel bdTableHeader = new JLabel("       NAME                              PWD ID NO.                   DATE") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Rounded background
                g2.setColor(new Color(0, 202, 238));
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

        DefaultTableModel bdModel = new DefaultTableModel(
                new String[]{"name", "PWD ID No.", "Date"}, 0
        );

        // Populate the model with all birthday celebrant data (replace with actual data source)
        bdModel.addRow(new Object[]{"Jana Agustin", "CH-0000-0000-0000", "12-29-04"});
        bdModel.addRow(new Object[]{"Joseph Desalit", "CH-0000-0000-0000", "07-18-04"});
        bdModel.addRow(new Object[]{"Peavey Iya Capacio", "CH-0000-0000-0000", "05-22-05"});
        bdModel.addRow(new Object[]{"John Doe", "CH-0000-0000-0001", "06-15-05"}); // Extra rows for testing

        JTable table = new JTable(bdModel);
        table.setRowHeight(36);
        table.getColumnModel().getColumn(0).setPreferredWidth(165); // Name
        table.getColumnModel().getColumn(1).setPreferredWidth(183); // PWD ID No.
        table.getColumnModel().getColumn(2).setPreferredWidth(70); // Date
        table.setTableHeader(null);
        table.setShowGrid(false);
        table.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
        table.setBackground(new Color(238, 235, 235));
        table.setForeground(new Color(58, 58, 58));
        table.setSelectionBackground(new Color(220, 240, 255));
        table.setSelectionForeground(Color.BLACK);

        JScrollPane scrollPane = new RoundedScrollPane(table);
        scrollPane.setBounds(22, 108, 480, 98); // Height for exactly 2 rows (36px per row)
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER); // Disable scrolling
        birthdayPanel.add(scrollPane);

        bdModel.addRow(new Object[]{"Jana Agustin", "CH-0000-0000-0000", "12-29-04"});
        bdModel.addRow(new Object[]{"Joseph Desalit", "CH-0000-0000-0000", "07-18-04"});

        // Birthday Panel
        JButton bdBtn = new JButton("See All");
        bdBtn.setBounds(223, 209, 80, 30);
        bdBtn.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
        bdBtn.setForeground(new Color(56, 56, 56));
        bdBtn.setBorderPainted(false);
        bdBtn.setContentAreaFilled(false);
        bdBtn.setFocusPainted(false);
        bdBtn.setOpaque(true);
        birthdayPanel.add(bdBtn);

        // Create JDialog for fullBdPane
        JDialog fullBdDialog = new JDialog(SwingUtilities.getWindowAncestor(this), "Full Birthday List", Dialog.ModalityType.APPLICATION_MODAL);
        fullBdDialog.setSize(660, 490);
        fullBdDialog.setLayout(null);
        fullBdDialog.setLocationRelativeTo(this);
        fullBdDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        fullBdDialog.getContentPane().setBackground(new Color(238, 235, 235));

        JLabel FullBdTableHeader = new JLabel("       NAME                                         PWD ID NO.                               DATE") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Rounded background
                g2.setColor(new Color(0, 202, 238));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 12, 12);

                super.paintComponent(g);
                g2.dispose();
            }
        };
        FullBdTableHeader.setBounds(23, 15, 600, 30);
        FullBdTableHeader.setOpaque(false);
        FullBdTableHeader.setForeground(Color.BLACK);
        FullBdTableHeader.setFont(new Font("Arial", Font.BOLD, 16));
        FullBdTableHeader.setHorizontalAlignment(SwingConstants.LEFT);
        fullBdDialog.add(FullBdTableHeader);

        // JTable for fullBdDialog (shows all rows)
        JTable fullBdTable = new JTable(bdModel); // Use same model
        fullBdTable.setRowHeight(36);
        fullBdTable.getColumnModel().getColumn(0).setPreferredWidth(165);
        fullBdTable.getColumnModel().getColumn(1).setPreferredWidth(183);
        fullBdTable.getColumnModel().getColumn(2).setPreferredWidth(30);
        fullBdTable.setTableHeader(null);
        fullBdTable.setShowGrid(false);
        fullBdTable.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
        fullBdTable.setBackground(new Color(238, 235, 235));
        fullBdTable.setForeground(new Color(58, 58, 58));
        fullBdTable.setSelectionBackground(new Color(220, 240, 255));
        fullBdTable.setSelectionForeground(Color.BLACK);

        JScrollPane fullBdScrollPane = new RoundedScrollPane(fullBdTable);
        fullBdScrollPane.setBounds(23, 55, 600, 375);
        fullBdDialog.add(fullBdScrollPane);

        bdBtn.addActionListener(e -> {
            fullBdDialog.setVisible(true); // Show the dialog
        });


        // Expiring Members Panel ------------------------------------------------------------------------------------------------------
        JPanel expiringMemPanel = createRoundedPanel();
        expiringMemPanel.setBounds(24, 285, 528, 245);
        add(expiringMemPanel);

        // exp = expiring
        JLabel expIcon = new JLabel("⚠️");
        expIcon.setBounds(22, 5, 60, 60);
        expIcon.setFont(new Font("", Font.PLAIN, 19));
        expiringMemPanel.add(expIcon);

        JLabel expHeader = new JLabel("Expiring PWD IDs");
        expHeader.setBounds(50, 22, 300, 22);
        expHeader.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
        expiringMemPanel.add(expHeader);

        JLabel expSub = new JLabel("List of members whose PWD IDs are nearing expiration");
        expSub.setBounds(51, 38, 350, 22);
        expSub.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
        expiringMemPanel.add(expSub);

        JLabel expTableHeader = new JLabel("       NAME                              PWD ID NO.                   DATE") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                g2.setColor(new Color(0, 202, 238));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 12, 12);

                super.paintComponent(g);
                g2.dispose();
            }
        };
        expTableHeader.setBounds(22, 65, 480, 30);
        expTableHeader.setOpaque(false); // Important so we can draw custom background
        expTableHeader.setForeground(Color.BLACK);
        expTableHeader.setFont(new Font("Arial", Font.BOLD, 16));
        expTableHeader.setHorizontalAlignment(SwingConstants.LEFT);
        expiringMemPanel.add(expTableHeader);

        DefaultTableModel expModel = new DefaultTableModel(
                new String[]{"name", "PWD ID No.", "Date"}, 0
        );

        JTable expTable = new JTable(expModel);
        expTable.setRowHeight(39);
        expTable.getColumnModel().getColumn(0).setPreferredWidth(165); // Name
        expTable.getColumnModel().getColumn(1).setPreferredWidth(183); // PWD ID No.
        expTable.getColumnModel().getColumn(2).setPreferredWidth(70); // Date
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

        expModel.addRow(new Object[]{"Jana Agustin", "CH-0000-0000-0000", "12-29-04"});
        expModel.addRow(new Object[]{"Joseph Desalit", "CH-0000-0000-0000", "07-18-04"});
        expModel.addRow(new Object[]{"Peavey Iya Capacio", "CH-0000-0000-0000", "05-22-05"});

        JButton expBtn = new JButton("See All");
        expBtn.setBounds(214,206, 100,30);
        expBtn.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
        expBtn.setForeground(new Color(56, 56, 56));
        expBtn.setBorderPainted(false);
        expBtn.setContentAreaFilled(false);
        expBtn.setFocusPainted(false);
        expBtn.setOpaque(true);
        expiringMemPanel.add(expBtn);

        // Create JDialog for fullBdPane
        JDialog fullExpDialog = new JDialog(SwingUtilities.getWindowAncestor(this), "Full Expiring Membership List", Dialog.ModalityType.APPLICATION_MODAL);
        fullExpDialog.setSize(660, 490);
        fullExpDialog.setLayout(null);
        fullExpDialog.setLocationRelativeTo(this);
        fullExpDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        fullExpDialog.getContentPane().setBackground(new Color(238, 235, 235));

        JLabel FullExpTableHeader = new JLabel("       NAME                                         PWD ID NO.                               DATE") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Rounded background
                g2.setColor(new Color(0, 202, 238));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 12, 12);

                super.paintComponent(g);
                g2.dispose();
            }
        };
        FullExpTableHeader.setBounds(23, 15, 600, 30);
        FullExpTableHeader.setOpaque(false);
        FullExpTableHeader.setForeground(Color.BLACK);
        FullExpTableHeader.setFont(new Font("Arial", Font.BOLD, 16));
        FullExpTableHeader.setHorizontalAlignment(SwingConstants.LEFT);
        fullExpDialog.add(FullExpTableHeader);

        // JTable for fullBdDialog (shows all rows)
        JTable fullExpTable = new JTable(expModel); // Use same model
        fullExpTable.setRowHeight(36);
        fullExpTable.getColumnModel().getColumn(0).setPreferredWidth(165);
        fullExpTable.getColumnModel().getColumn(1).setPreferredWidth(183);
        fullExpTable.getColumnModel().getColumn(2).setPreferredWidth(30);
        fullExpTable.setTableHeader(null);
        fullExpTable.setShowGrid(false);
        fullExpTable.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
        fullExpTable.setBackground(new Color(238, 235, 235));
        fullExpTable.setForeground(new Color(58, 58, 58));
        fullExpTable.setSelectionBackground(new Color(220, 240, 255));
        fullExpTable.setSelectionForeground(Color.BLACK);

        JScrollPane fullExpScrollPane = new RoundedScrollPane(fullExpTable);
        fullExpScrollPane.setBounds(23, 55, 600, 375);
        fullExpDialog.add(fullExpScrollPane);

        expBtn.addActionListener(e -> {
            fullExpDialog.setVisible(true); // Show the dialog
        });

        // Quick Access Panel -----------------------------------------------------------------------------------------------------------------
        JPanel quickAccessPanel = createRoundedPanel();
        quickAccessPanel.setBounds(573, 20, 390, 90);
        add(quickAccessPanel);

        // qa for Quick Access
        JLabel qaIcon = new JLabel("👆");
        qaIcon.setBounds(26, 10, 30, 28);
        qaIcon.setFont(new Font("", Font.BOLD, 21));
        quickAccessPanel.add(qaIcon);

        JLabel qaHeader = new JLabel("Member's List | Quick Access Bar");
        qaHeader.setBounds(57, 12, 310, 22);
        qaHeader.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
        quickAccessPanel.add(qaHeader);

        RoundedButton qaAddBtn = new RoundedButton("+ ADD MEMBER", new Color(73, 230, 127));
        qaAddBtn.setBounds(50, 42, 142, 36);
        quickAccessPanel.add(qaAddBtn);
        qaAddBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pages.records_membersbtn.addMembersPage.launch();
            }
        } );

        RoundedButton qaUpdBtn = new RoundedButton("🔺 UPDATE", new Color(0, 202, 238));
        qaUpdBtn.setBounds(200, 42, 120, 36);
        quickAccessPanel.add(qaUpdBtn);

        // Notif Panel ---------------------------------------------------------------------------------------------------------------------
        JPanel notifPane = createRoundedPanel();
        notifPane.setBounds(573, 122, 390, 248);
        add(notifPane);

        JLabel notifIcon = new JLabel("🔔");
        notifIcon.setBounds(26, 10, 30, 28);
        notifIcon.setFont(new Font("", Font.BOLD, 21));
        notifPane.add(notifIcon);

        JLabel notifHeader = new JLabel("Notifications");
        notifHeader.setBounds(55, 13, 310, 22);
        notifHeader.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
        notifPane.add(notifHeader);

        JPanel notifMissingMem = createRoundedPanel();
        notifMissingMem.setBounds(23, 45, 345, 55);
        notifPane.add(notifMissingMem);

        JLabel MissingMemHeader = new JLabel("Incomplete Demographic Sheet");
        MissingMemHeader.setBounds(68, 10, 300, 20);
        MissingMemHeader.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
        notifMissingMem.add(MissingMemHeader);

        JLabel MissingMemSub = new JLabel("Members with Incomplete Demographic Sheet");
        MissingMemSub.setBounds(69, 24, 300, 20);
        MissingMemSub.setForeground(new Color(90,90,90));
        MissingMemSub.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
        notifMissingMem.add(MissingMemSub);

        JPanel notifNewMem = createRoundedPanel();
        notifNewMem.setBounds(23, 110, 345, 55);
        notifPane.add(notifNewMem);

        JLabel newMemHeader = new JLabel("New Members Added");
        newMemHeader.setBounds(68, 10, 300, 20);
        newMemHeader.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
        notifNewMem.add(newMemHeader);

        JLabel newMemSub = new JLabel("Newly Registered PWD Members");
        newMemSub.setBounds(69, 24, 300, 20);
        newMemSub.setForeground(new Color(90,90,90));
        newMemSub.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
        notifNewMem.add(newMemSub);

        JPanel notifInactiveMem = createRoundedPanel();
        notifInactiveMem.setBounds(23, 175, 345, 55);
        notifPane.add(notifInactiveMem);

        JLabel inactiveMemHeader = new JLabel("Inactive Members");
        inactiveMemHeader.setBounds(68, 10, 300, 20);
        inactiveMemHeader.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
        notifInactiveMem.add(inactiveMemHeader);

        JLabel inactiveMemSub = new JLabel("Members that are currently Inactive");
        inactiveMemSub.setBounds(69, 24, 300, 20);
        inactiveMemSub.setForeground(new Color(90,90,90));
        inactiveMemSub.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
        notifInactiveMem.add(inactiveMemSub);


        SimpleDateFormat monthYear = new SimpleDateFormat("MMMM yyyy");
        Date now = new Date();
        String moYearString = monthYear.format(now);


        // Total Member Pane ------------------------------------------------------------------------------------------------------------------
        JPanel totalMembPane = createRoundedPanel();
        totalMembPane.setBounds(620, 380, 166, 155);
        add(totalMembPane);

        JLabel totalMemHeader = new JLabel("Total Registered PWDs");
        totalMemHeader.setBounds(10, 10, 310, 22);
        totalMemHeader.setFont(new Font("Trebuchet MS",Font.ITALIC, 14));
        totalMembPane.add(totalMemHeader);

        JLabel totalMemSub = new JLabel("As of " + moYearString);
        totalMemSub.setBounds(10, 25, 310, 22);
        totalMemSub.setForeground(new Color(90,90,90));
        totalMemSub.setFont(new Font("Trebuchet MS",Font.PLAIN, 12));
        totalMembPane.add(totalMemSub);

        JSeparator separator = new JSeparator();
        separator.setBounds(10, 50, 140, 3);
        separator.setForeground(new Color(80,80,80));
        totalMembPane.add(separator);

        JLabel totalMemNum = new JLabel("100");
        totalMemNum.setBounds(0, 58, 166, 59);
        totalMemNum.setForeground(new Color(56, 113, 193));
        totalMemNum.setFont(new Font("Trebuchet MS",Font.BOLD, 58));
        totalMemNum.setHorizontalAlignment(JLabel.CENTER);
        totalMembPane.add(totalMemNum);

        JLabel totalMemText = new JLabel("PWD Members");
        totalMemText.setBounds(0, 123, 166, 17);
        totalMemText.setForeground(new Color(56, 113, 193));
        totalMemText.setFont(new Font("Trebuchet MS",Font.BOLD, 16));
        totalMemText.setHorizontalAlignment(JLabel.CENTER);
        totalMembPane.add(totalMemText);

        // Popu by Sex Pane -------------------------------------------------------------------------------------------------------------------
        JPanel sexPopuPane = createRoundedPanel();
        sexPopuPane.setBounds(794, 380, 163, 155);
        add(sexPopuPane);

        JLabel sexPopuHeader = new JLabel("Population by Sex");
        sexPopuHeader.setBounds(10, 10, 310, 22);
        sexPopuHeader.setFont(new Font("Trebuchet MS",Font.ITALIC, 14));
        sexPopuPane.add(sexPopuHeader);

        JLabel sexPopuSub = new JLabel("As of " + moYearString);
        sexPopuSub.setBounds(10, 25, 310, 22);
        sexPopuSub.setForeground(new Color(90,90,90));
        sexPopuSub.setFont(new Font("Trebuchet MS",Font.PLAIN, 12));
        sexPopuPane.add(sexPopuSub);

        ImageIcon femaleIcon = new ImageIcon("imgs/femaleLogo.png");
        Image scaledFemImg = femaleIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        ImageIcon scaledFemIcon = new ImageIcon(scaledFemImg);

        JLabel womenLbl = new JLabel();
        womenLbl.setBounds(1, 50, 161, 52);
        womenLbl.setOpaque(true);
        womenLbl.setForeground(Color.WHITE);
        womenLbl.setBackground(new Color(255, 105, 192));
        womenLbl.setFont(new Font("Arial", Font.BOLD, 46));
        womenLbl.setHorizontalAlignment(JLabel.CENTER);
        womenLbl.setIcon(scaledFemIcon);
        womenLbl.setText("97");
        sexPopuPane.add(womenLbl);

        ImageIcon maleIcon = new ImageIcon("imgs/maleLogo.png");
        Image scaledMaleImg = maleIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        ImageIcon scaledMaleIcon = new ImageIcon(scaledMaleImg);

        JLabel menLbl = new JLabel("97", scaledMaleIcon, JLabel.CENTER) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                int w = getWidth();
                int h = getHeight();
                int arc = 18;

                // Create a shape with only bottom corners rounded
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

                super.paintComponent(g); // draw text and icon
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
    }

    // Reusable method to reduce repetition
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

            // Background fill
            g2.setColor(bgColor);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);

            // Optional: draw text manually if needed (default works fine)
            super.paintComponent(g);
            g2.dispose();
        }

        @Override
        protected void paintBorder(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getForeground());
            g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);
            g2.dispose();
        }
    }
}
