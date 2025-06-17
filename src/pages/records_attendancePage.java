package pages;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.*;
import java.awt.*;


public class records_attendancePage extends JPanel {
    private final JTable table;
    private final DefaultTableModel tableModel;
    private final JTextField search;
    private JLabel label; // Moved label to be accessible in the whole class

    public records_attendancePage() {
        setLayout(null);
        setBorder(new EmptyBorder(10, 10, 10, 10));

        // Top bar container
        JPanel topBar = new JPanel();
        topBar.setBackground(new Color(210, 210, 210));
        topBar.setBounds(0, 0, 991, 50);
        topBar.setLayout(null);
        add(topBar);

        String[] choices = {
                "2025", "2024", "2023", "2022"
        };

        JComboBox<String> month = new JComboBox<>(choices);
        month.setFont(new Font("Trebuchet MS", Font.BOLD, 17));
        month.setBounds(34, 10, 82, 32);
        month.setBackground(Color.WHITE);
        month.setForeground(Color.BLACK);
        month.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        month.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                          boolean isSelected, boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                label.setHorizontalAlignment(SwingConstants.CENTER);
                return label;
            }
        });
        topBar.add(month);
        month.addActionListener(e -> {
            String selectedYear = (String) month.getSelectedItem();
            label.setText(selectedYear + " total month/s: 11");
        });

        String chosenMonth = String.valueOf(month.getSelectedItem());

        label = new JLabel(month.getSelectedItem() + " Attendance Tracked for: 11 Months");
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
                BorderFactory.createEmptyBorder(0, 10, 0, 0) // top, left, bottom, right
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

        tableModel = new DefaultTableModel(
                new String[]{"#", "Member Name", "Last Month attended", "Total Attendance"}, 0
        ) {
        };

        // Table
        table = new JTable(tableModel);

        // Center-align text in non-checkbox columns
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);

        // Set preferred column widths
        table.getColumnModel().getColumn(0).setPreferredWidth(20);   // "#"
        table.getColumnModel().getColumn(1).setPreferredWidth(260);  // "Member Name"
        table.getColumnModel().getColumn(2).setPreferredWidth(130);  // "Start"
        table.getColumnModel().getColumn(3).setPreferredWidth(120);  // "End"

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

        // Table visual setup
        table.setFont(new Font("Trebuchet MS", Font.BOLD, 17));
        table.setRowHeight(43);
        table.setShowGrid(false);
        table.setIntercellSpacing(new Dimension(0, 0));
        table.setForeground(Color.BLACK);
        table.setShowVerticalLines(false);
        table.setShowHorizontalLines(false);
        table.setSelectionBackground(Color.WHITE);

        // Scroll pane with table
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(30, 100, 930, 393);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        add(scrollPane);


        // Add rows with initial Boolean values for Start and End
        String[] names = {"Jana Agustin", "Peavey Capacio", "Joseph Desalit", "Eliand Penus", "Earl Perucho"};
        for (int i = 0; i < 15; i++) {
            String name = names[i % names.length]; // loop through names repeatedly
            tableModel.addRow(new Object[]{String.valueOf(i + 1), name, "December", "11"});
        }
    }
}
