package pages.recordsPanel;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.awt.*;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Map;

import dal.members.membersDAO;
import db.database;

public class records_attendancePage extends JPanel {
    private final JTable table;
    private final DefaultTableModel tableModel;
    private final JTextField search;
    private JLabel label;
    private JComboBox<String> yearComboBox;
    private membersDAO dao;
    private List<Object[]> allRows; // Store all rows for filtering

    public records_attendancePage() {
        setLayout(null);
        setBorder(new EmptyBorder(10, 10, 10, 10));

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

        yearComboBox = new JComboBox<>();
        yearComboBox.setFont(new Font("Trebuchet MS", Font.BOLD, 17));
        yearComboBox.setBounds(34, 10, 82, 32);
        yearComboBox.setBackground(Color.WHITE);
        yearComboBox.setForeground(Color.BLACK);
        yearComboBox.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        yearComboBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                          boolean isSelected, boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                label.setHorizontalAlignment(SwingConstants.CENTER);
                return label;
            }
        });
        topBar.add(yearComboBox);

        // Add action listener to yearComboBox
        yearComboBox.addActionListener(e -> {
            String selectedYear = (String) yearComboBox.getSelectedItem();
            if (selectedYear != null) {
                loadAttendanceData(selectedYear);
            }
        });

        label = new JLabel("");
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

        allRows = new ArrayList<>();

        tableModel = new DefaultTableModel(
                new String[]{" ", "Member Name", "Last Date Attended", "Total Present"}, 0
        );

        table = new JTable(tableModel);

        LabelCellRenderer centerRenderer = new LabelCellRenderer();

        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(1).setCellRenderer(new TextAreaRenderer());
        table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);

        table.getColumnModel().getColumn(0).setPreferredWidth(52);
        table.getColumnModel().getColumn(1).setPreferredWidth(420);
        table.getColumnModel().getColumn(2).setPreferredWidth(232);
        table.getColumnModel().getColumn(3).setPreferredWidth(223);

        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
        header.setBackground(new Color(245, 245, 245));
        header.setPreferredSize(new Dimension(header.getPreferredSize().width, 50));
        header.setForeground(Color.BLACK);
        header.setOpaque(true);
        header.setReorderingAllowed(false);
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
        table.setRowHeight(43);
        table.setShowGrid(false);
        table.setIntercellSpacing(new Dimension(0, 0));
        table.setForeground(Color.BLACK);
        table.setShowVerticalLines(false);
        table.setShowHorizontalLines(false);
        table.setSelectionBackground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(32, 100, 935, 418);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        styleScrollBar(scrollPane);
        add(scrollPane);

        // Defer combo box population and initial selection
        SwingUtilities.invokeLater(() -> {
            if (dao != null) {
                try {
                    List<String> years = dao.getAttendanceYears();
                    for (String year : years) {
                        yearComboBox.addItem(year);
                    }
                    if (!years.isEmpty()) {
                        yearComboBox.setSelectedIndex(0); // Triggers loadAttendanceData
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void reloadAttendanceData() {
        String selectedYear = (String) yearComboBox.getSelectedItem();
        if (selectedYear != null) {
            loadAttendanceData(selectedYear);
            // Force table UI update
            table.revalidate();
            table.repaint();
        }
    }

    private void loadAttendanceData(String year) {
        if (dao == null) return;
        try {
            int uniqueDates = dao.getUniqueAttendanceDatesCount(year);
            label.setText(year + " Attendance - " + uniqueDates + " in total");

            // Fetch all members
            List<membersDAO.MemberData> allMembers = dao.getMembers();
            // Fetch attendance records for the year
            List<membersDAO.AttendanceRecord> attendanceRecords = dao.getAttendanceRecordsForYear(year);

            // Create a map for quick lookup of attendance records by member ID
            Map<Integer, membersDAO.AttendanceRecord> attendanceMap = new HashMap<>();
            for (membersDAO.AttendanceRecord record : attendanceRecords) {
                attendanceMap.put(record.memberId, record);
            }

            tableModel.setRowCount(0);
            allRows.clear();
            for (int i = 0; i < allMembers.size(); i++) {
                membersDAO.MemberData member = allMembers.get(i);
                membersDAO.AttendanceRecord record = attendanceMap.get(member.memberId);
                String lastDate = (record != null && record.lastAttendanceDate != null) ?
                        new SimpleDateFormat("MM/dd/yyyy").format(record.lastAttendanceDate) : "N/A";
                String totalPresent = record != null ? String.valueOf(record.totalAttendance) : "0";
                Object[] row = new Object[]{String.valueOf(i + 1), member.fullName, lastDate, totalPresent};
                tableModel.addRow(row);
                allRows.add(row);
            }

            if (search != null) filterTable();
            table.revalidate();
            table.repaint();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void filterTable() {
        String searchText = search.getText().trim().toLowerCase();
        if (searchText.equals("search")) searchText = "";

        tableModel.setRowCount(0); // Clear current rows
        int rowIndex = 1;
        for (Object[] row : allRows) {
            String memberName = ((String) row[1]).toLowerCase();
            if (searchText.isEmpty() || memberName.contains(searchText)) {
                // Update the index for the first column
                Object[] newRow = row.clone();
                newRow[0] = String.valueOf(rowIndex++);
                tableModel.addRow(newRow);
            }
        }
    }



    class TextAreaRenderer extends JTextArea implements TableCellRenderer {
        public TextAreaRenderer() {
            setBorder(BorderFactory.createEmptyBorder(12, 10, 12, 10));
            setLineWrap(true);
            setWrapStyleWord(true);
            setOpaque(true);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus, int row, int column) {
            setText(value == null ? "" : value.toString());
            setFont(new Font("Trebuchet MS", Font.BOLD, 19));
            setForeground(Color.BLACK);
            setBackground(isSelected ? table.getSelectionBackground() : table.getBackground());

            setSize(table.getColumnModel().getColumn(column).getWidth(), Short.MAX_VALUE);

            int height = getPreferredSize().height;
            if (table.getRowHeight(row) != height) {
                table.setRowHeight(row, height);
            }

            return this;
        }
    }

    class LabelCellRenderer extends JLabel implements TableCellRenderer {
        public LabelCellRenderer() {
            setOpaque(true);
            setHorizontalAlignment(SwingConstants.CENTER);
            setVerticalAlignment(SwingConstants.CENTER);
            setFont(new Font("Trebuchet MS", Font.BOLD, 16));
            setForeground(Color.BLACK);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus, int row, int column) {
            setText(value == null ? "" : value.toString());
            setBackground(isSelected ? table.getSelectionBackground() : Color.WHITE);
            return this;
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


}