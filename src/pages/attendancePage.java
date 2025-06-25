package pages;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.sql.SQLException;
import dal.members.membersDAO;
import db.database;

public class attendancePage extends JPanel {
    private final JTable table;
    private final JTextField search;
    private final DefaultTableModel tableModel;
    private JLabel presentNumLbl;
    private JLabel absentNumLbl;
    private JLabel excusedNumLbl;
    private membersDAO dao;
    private List<Object[]> allRows; // Store all rows for filtering

    public attendancePage() {
        setLayout(null);
        setBorder(new EmptyBorder(10, 10, 10, 10));

        // Initialize DAO
        try {
            dao = new membersDAO(database.getConnection());
        } catch (SQLException e) {
            e.printStackTrace();
            dao = null;
        }

        // Top bar container
        JPanel topBar = new JPanel();
        topBar.setBackground(new Color(210, 210, 210));
        topBar.setBounds(0, 0, 991, 50);
        topBar.setLayout(null);
        add(topBar);

        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);

        SimpleDateFormat wholeDate = new SimpleDateFormat("MM/dd/yyyy");
        Date now = new Date();
        String wholeDateText = wholeDate.format(now);

        JPanel datePane = new JPanel();
        datePane.setBackground(new Color(240, 240, 240));
        datePane.setBounds(30, 6, 130, 38);
        datePane.setBorder(border);
        datePane.setLayout(null);
        topBar.add(datePane);

        JLabel dateIcon = new JLabel("📆");
        dateIcon.setBounds(6, 5, 30, 26);
        dateIcon.setFont(new Font("", Font.BOLD, 25));
        datePane.add(dateIcon);

        JLabel totalMemHeader = new JLabel("DATE");
        totalMemHeader.setBounds(39, 6, 100, 15);
        totalMemHeader.setFont(new Font("Arial", Font.BOLD, 13));
        datePane.add(totalMemHeader);

        JLabel totalMemSub = new JLabel(wholeDateText);
        totalMemSub.setBounds(39, 16, 100, 22);
        totalMemSub.setForeground(new Color(70, 70, 70));
        totalMemSub.setFont(new Font("Arial", Font.PLAIN, 13));
        datePane.add(totalMemSub);

        // Header label
        JLabel label = new JLabel("BARANGAY IBABA: PWD ATTENDANCE MONITORING SHEET");
        label.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
        label.setBounds(32, 63, 900, 30);
        add(label);

        search = new JTextField("Search");
        search.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
        search.setBounds(773, 63, 168, 31);
        search.setBackground(Color.WHITE);
        search.setForeground(Color.GRAY);
        search.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY, 1),
                BorderFactory.createEmptyBorder(0, 10, 0, 0)
        ));
        add(search);

        search.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (search.getText().equals("Search")) {
                    search.setText("");
                    search.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (search.getText().isEmpty()) {
                    search.setText("Search");
                    search.setForeground(Color.GRAY);
                }
            }
        });

        // Add key listener for search functionality
        search.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                filterTable();
            }
        });

        // Table model with Member ID
        tableModel = new DefaultTableModel(
                new String[]{"Member ID", " ", "Member Name", "Status"}, 0
        ) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 0) return Integer.class;
                if (columnIndex == 3) return String.class;
                return super.getColumnClass(columnIndex);
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 3;
            }
        };

        // Populate table with database data, sorted alphabetically
        allRows = new ArrayList<>();
        if (dao != null) {
            try {
                List<membersDAO.MemberData> members = dao.getMembers();
                // Sort members by fullName
                members.sort(Comparator.comparing(m -> m.fullName.toLowerCase()));
                for (int i = 0; i < members.size(); i++) {
                    membersDAO.MemberData member = members.get(i);
                    Object[] row = new Object[]{member.memberId, String.valueOf(i + 1), member.fullName, "Absent"};
                    tableModel.addRow(row);
                    allRows.add(row);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        table = new JTable(tableModel);

        table.setCellSelectionEnabled(true);
        table.setRowSelectionAllowed(false);
        table.setColumnSelectionAllowed(false);

        TableCellRenderer radioRenderer = new TableCellRenderer() {
            private final JPanel cellPanel = new JPanel(new GridBagLayout());
            private final JPanel radioPanel = new JPanel();
            private final JRadioButton presentBtn = new JRadioButton();
            private final JLabel presentLabel = new JLabel("Present");
            private final JRadioButton absentBtn = new JRadioButton();
            private final JLabel absentLabel = new JLabel("Absent");
            private final JRadioButton excusedBtn = new JRadioButton();
            private final JLabel excusedLabel = new JLabel("Excused");

            {
                radioPanel.setLayout(new BoxLayout(radioPanel, BoxLayout.X_AXIS));
                radioPanel.setOpaque(false);

                JPanel presentPanel = new JPanel();
                presentPanel.setLayout(new BoxLayout(presentPanel, BoxLayout.Y_AXIS));
                presentPanel.setOpaque(false);
                presentPanel.add(presentBtn);
                presentPanel.add(Box.createVerticalStrut(5));
                presentPanel.add(presentLabel);

                JPanel absentPanel = new JPanel();
                absentPanel.setLayout(new BoxLayout(absentPanel, BoxLayout.Y_AXIS));
                absentPanel.setOpaque(false);
                absentPanel.add(absentBtn);
                absentPanel.add(Box.createVerticalStrut(5));
                absentPanel.add(absentLabel);

                JPanel excusedPanel = new JPanel();
                excusedPanel.setLayout(new BoxLayout(excusedPanel, BoxLayout.Y_AXIS));
                excusedPanel.setOpaque(false);
                excusedPanel.add(excusedBtn);
                excusedPanel.add(Box.createVerticalStrut(5));
                excusedPanel.add(excusedLabel);

                radioPanel.add(presentPanel);
                radioPanel.add(Box.createHorizontalStrut(10));
                radioPanel.add(absentPanel);
                radioPanel.add(Box.createHorizontalStrut(10));
                radioPanel.add(excusedPanel);

                GridBagConstraints gbc = new GridBagConstraints();
                gbc.gridx = 0;
                gbc.gridy = 0;
                gbc.anchor = GridBagConstraints.CENTER;
                cellPanel.add(radioPanel, gbc);
                cellPanel.setOpaque(false);

                presentBtn.setOpaque(false);
                absentBtn.setOpaque(false);
                excusedBtn.setOpaque(false);
                presentBtn.setFocusPainted(false);
                absentBtn.setFocusPainted(false);
                excusedBtn.setFocusPainted(false);

                presentLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
                absentLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
                excusedLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 14));

                presentLabel.setForeground(new Color(25, 161, 25));
                absentLabel.setForeground(Color.RED);
                excusedLabel.setForeground(new Color(223, 199, 72));

                presentBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
                presentLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                absentBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
                absentLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                excusedBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
                excusedLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            }

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                           boolean hasFocus, int row, int column) {
                String status = (String) value;
                presentBtn.setSelected("Present".equals(status));
                absentBtn.setSelected("Absent".equals(status));
                excusedBtn.setSelected("Excused".equals(status));
                return cellPanel;
            }
        };

        TableCellEditor radioEditor = new RadioEditor();

        table.removeColumn(table.getColumnModel().getColumn(0)); // Hide Member ID column

        table.getColumnModel().getColumn(0).setCellRenderer(new DefaultTableCellRenderer() {{
            setHorizontalAlignment(SwingConstants.CENTER);
        }});
        table.getColumnModel().getColumn(1).setCellRenderer(new DefaultTableCellRenderer() {{
            setHorizontalAlignment(SwingConstants.CENTER);
        }});
        table.getColumnModel().getColumn(2).setCellRenderer(radioRenderer);
        table.getColumnModel().getColumn(2).setCellEditor(radioEditor);

        table.getColumnModel().getColumn(0).setPreferredWidth(50);
        table.getColumnModel().getColumn(1).setPreferredWidth(283);
        table.getColumnModel().getColumn(2).setPreferredWidth(350);

        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
        header.setBackground(new Color(245, 245, 245));
        header.setPreferredSize(new Dimension(header.getPreferredSize().width, 50));
        header.setForeground(Color.BLACK);
        header.setReorderingAllowed(false);
        header.setOpaque(true);
        header.setBorder(new Border() {
            private final int radius = 10;
            private final Color borderColor = Color.GRAY;

            @Override
            public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(borderColor);
                g2.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
                g2.dispose();
            }

            @Override
            public Insets getBorderInsets(Component c) {
                return new Insets(radius, radius, radius, radius);
            }

            @Override
            public boolean isBorderOpaque() {
                return false;
            }
        });
        ((DefaultTableCellRenderer) header.getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);

        table.setFont(new Font("Trebuchet MS", Font.BOLD, 17));
        table.setRowHeight(70);
        table.setShowGrid(false);
        table.setIntercellSpacing(new Dimension(0, 0));
        table.setForeground(Color.BLACK);
        table.setShowVerticalLines(false);
        table.setShowHorizontalLines(false);
        table.setSelectionBackground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(265, 100, 693, 393);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        styleScrollBar(scrollPane);
        add(scrollPane);

        tableModel.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if (e.getType() == TableModelEvent.UPDATE && e.getColumn() == 3) {
                    int row = e.getFirstRow();
                    String status = (String) tableModel.getValueAt(row, 3);
                    updateCounts();
                    // Update allRows to reflect the new status
                    for (Object[] rowData : allRows) {
                        if (rowData[0].equals(tableModel.getValueAt(row, 0))) {
                            rowData[3] = status;
                            break;
                        }
                    }
                }
            }
        });

        RoundedButton saveBtn = new RoundedButton("SAVE", new Color(73, 230, 127));
        saveBtn.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
        saveBtn.setBounds(854, 505, 90, 35);
        saveBtn.addActionListener(e -> {
            if (dao != null) {
                java.sql.Date attendanceDate = new java.sql.Date(System.currentTimeMillis());
                List<membersDAO.AttendanceEntry> entries = new ArrayList<>();
                for (int i = 0; i < tableModel.getRowCount(); i++) {
                    int memberId = (int) tableModel.getValueAt(i, 0);
                    String status = (String) tableModel.getValueAt(i, 3);
                    entries.add(new membersDAO.AttendanceEntry(memberId, status));
                }
                try {
                    dao.addAttendance(attendanceDate, entries);
                    JOptionPane.showMessageDialog(this, "Attendance saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    resetAttendanceTable(); // Reset the table after successful save
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Error saving attendance: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        add(saveBtn);

        JPanel totalPresentPane = createRoundedPanel();
        totalPresentPane.setBackground(new Color(51, 150, 51));
        totalPresentPane.setBounds(35, 101, 210, 126);
        add(totalPresentPane);

        presentNumLbl = new JLabel("0");
        presentNumLbl.setBounds(1, 18, 200, 66);
        presentNumLbl.setForeground(Color.WHITE);
        presentNumLbl.setFont(new Font("Arial", Font.BOLD, 66));
        presentNumLbl.setHorizontalAlignment(JLabel.CENTER);
        totalPresentPane.add(presentNumLbl);

        JLabel presentLbl = new JLabel("Present");
        presentLbl.setBounds(3, 82, 200, 25);
        presentLbl.setForeground(Color.WHITE);
        presentLbl.setFont(new Font("Trebuchet MS", Font.BOLD, 26));
        presentLbl.setHorizontalAlignment(JLabel.CENTER);
        totalPresentPane.add(presentLbl);

        JPanel totalAbsentPane = createRoundedPanel();
        totalAbsentPane.setBackground(new Color(194, 40, 51));
        totalAbsentPane.setBounds(35, 233, 210, 126);
        add(totalAbsentPane);

        absentNumLbl = new JLabel("0");
        absentNumLbl.setBounds(1, 18, 200, 66);
        absentNumLbl.setForeground(Color.WHITE);
        absentNumLbl.setFont(new Font("Arial", Font.BOLD, 66));
        absentNumLbl.setHorizontalAlignment(JLabel.CENTER);
        totalAbsentPane.add(absentNumLbl);

        JLabel absentLbl = new JLabel("Absent");
        absentLbl.setBounds(3, 82, 200, 25);
        absentLbl.setForeground(Color.WHITE);
        absentLbl.setFont(new Font("Trebuchet MS", Font.BOLD, 26));
        absentLbl.setHorizontalAlignment(JLabel.CENTER);
        totalAbsentPane.add(absentLbl);

        JPanel totalExcusedPane = createRoundedPanel();
        totalExcusedPane.setBackground(new Color(223, 199, 72));
        totalExcusedPane.setBounds(35, 364, 210, 126);
        add(totalExcusedPane);

        excusedNumLbl = new JLabel("0");
        excusedNumLbl.setBounds(2, 18, 200, 66);
        excusedNumLbl.setForeground(Color.WHITE);
        excusedNumLbl.setFont(new Font("Arial", Font.BOLD, 66));
        excusedNumLbl.setHorizontalAlignment(JLabel.CENTER);
        totalExcusedPane.add(excusedNumLbl);

        JLabel excusedLbl = new JLabel("Excused");
        excusedLbl.setBounds(4, 82, 200, 25);
        excusedLbl.setForeground(Color.WHITE);
        excusedLbl.setFont(new Font("Trebuchet MS", Font.BOLD, 26));
        excusedLbl.setHorizontalAlignment(JLabel.CENTER);
        totalExcusedPane.add(excusedLbl);

        updateCounts();
    }

    private void updateCounts() {
        int presentCount = 0, absentCount = 0, excusedCount = 0;
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            String status = (String) tableModel.getValueAt(i, 3);
            switch (status) {
                case "Present": presentCount++; break;
                case "Absent": absentCount++; break;
                case "Excused": excusedCount++; break;
            }
        }
        presentNumLbl.setText(String.valueOf(presentCount));
        absentNumLbl.setText(String.valueOf(absentCount));
        excusedNumLbl.setText(String.valueOf(excusedCount));
    }

    private void resetAttendanceTable() {
        SwingUtilities.invokeLater(() -> {
            for (int i = 0; i < tableModel.getRowCount(); i++) {
                tableModel.setValueAt("Absent", i, 3);
            }
            // Update allRows to reflect reset
            for (Object[] row : allRows) {
                row[3] = "Absent";
            }
            updateCounts();
            filterTable(); // Re-apply search filter
        });
    }

    private void filterTable() {
        String searchText = search.getText().trim().toLowerCase();
        if (searchText.equals("search")) searchText = "";

        tableModel.setRowCount(0); // Clear current rows
        // Sort filtered rows alphabetically by member name (index 2)
        List<Object[]> filteredRows = new ArrayList<>();
        for (Object[] row : allRows) {
            String memberName = ((String) row[2]).toLowerCase();
            if (searchText.isEmpty() || memberName.contains(searchText)) {
                filteredRows.add(row);
            }
        }
        filteredRows.sort(Comparator.comparing(row -> ((String) row[2]).toLowerCase()));

        // Add sorted rows to table
        int rowIndex = 1;
        for (Object[] row : filteredRows) {
            Object[] newRow = row.clone();
            newRow[1] = String.valueOf(rowIndex++); // Update index
            tableModel.addRow(newRow);
        }
    }

    private class RadioEditor extends AbstractCellEditor implements TableCellEditor {
        private final JPanel cellPanel = new JPanel(new GridBagLayout());
        private final JPanel radioPanel = new JPanel();
        private final JRadioButton presentBtn = new JRadioButton();
        private final JLabel presentLabel = new JLabel("Present");
        private final JRadioButton absentBtn = new JRadioButton();
        private final JLabel absentLabel = new JLabel("Absent");
        private final JRadioButton excusedBtn = new JRadioButton();
        private final JLabel excusedLabel = new JLabel("Excused");
        private final ButtonGroup group = new ButtonGroup();
        private String selectedStatus;

        public RadioEditor() {
            radioPanel.setLayout(new BoxLayout(radioPanel, BoxLayout.X_AXIS));
            radioPanel.setOpaque(false);

            JPanel presentPanel = new JPanel();
            presentPanel.setLayout(new BoxLayout(presentPanel, BoxLayout.Y_AXIS));
            presentPanel.setOpaque(false);
            presentPanel.add(presentBtn);
            presentPanel.add(Box.createVerticalStrut(5));
            presentPanel.add(presentLabel);

            JPanel absentPanel = new JPanel();
            absentPanel.setLayout(new BoxLayout(absentPanel, BoxLayout.Y_AXIS));
            absentPanel.setOpaque(false);
            absentPanel.add(absentBtn);
            absentPanel.add(Box.createVerticalStrut(5));
            absentPanel.add(absentLabel);

            JPanel excusedPanel = new JPanel();
            excusedPanel.setLayout(new BoxLayout(excusedPanel, BoxLayout.Y_AXIS));
            excusedPanel.setOpaque(false);
            excusedPanel.add(excusedBtn);
            excusedPanel.add(Box.createVerticalStrut(5));
            excusedPanel.add(excusedLabel);

            radioPanel.add(presentPanel);
            radioPanel.add(Box.createHorizontalStrut(10));
            radioPanel.add(absentPanel);
            radioPanel.add(Box.createHorizontalStrut(10));
            radioPanel.add(excusedPanel);

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.anchor = GridBagConstraints.CENTER;
            cellPanel.add(radioPanel, gbc);
            cellPanel.setOpaque(false);

            group.add(presentBtn);
            group.add(absentBtn);
            group.add(excusedBtn);

            presentBtn.setOpaque(false);
            absentBtn.setOpaque(false);
            excusedBtn.setOpaque(false);
            presentBtn.setFocusPainted(false);
            absentBtn.setFocusPainted(false);
            excusedBtn.setFocusPainted(false);

            presentLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
            absentLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
            excusedLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 14));

            presentLabel.setForeground(new Color(25, 161, 25));
            absentLabel.setForeground(Color.RED);
            excusedLabel.setForeground(new Color(223, 199, 72));

            presentBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
            presentLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            absentBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
            absentLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            excusedBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
            excusedLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            ActionListener listener = e -> {
                if (presentBtn.isSelected()) selectedStatus = "Present";
                else if (absentBtn.isSelected()) selectedStatus = "Absent";
                else if (excusedBtn.isSelected()) selectedStatus = "Excused";
                fireEditingStopped();
            };

            presentBtn.addActionListener(listener);
            absentBtn.addActionListener(listener);
            excusedBtn.addActionListener(listener);
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            String status = (String) value;
            presentBtn.setSelected("Present".equals(status));
            absentBtn.setSelected("Absent".equals(status));
            excusedBtn.setSelected("Excused".equals(status));
            selectedStatus = status;
            return cellPanel;
        }

        @Override
        public Object getCellEditorValue() {
            return selectedStatus;
        }
    }

    public List<RadioStates> getRadioStates() {
        List<RadioStates> states = new ArrayList<>();
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            String status = (String) tableModel.getValueAt(i, 3);
            states.add(new RadioStates(status));
        }
        return states;
    }

    public static class RadioStates {
        private final String status;

        public RadioStates(String status) {
            this.status = status;
        }

        public String getStatus() {
            return status;
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
            g2.setColor(getForeground());
            g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);
            g2.dispose();
        }
    }
}