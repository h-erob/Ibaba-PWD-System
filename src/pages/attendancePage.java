package pages;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.*;
import javax.swing.table.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class attendancePage extends JPanel {
    private final JTable table;
    private final JTextField search;
    private final DefaultTableModel tableModel;
    private final JButton saveBtn;

    public attendancePage() {
        setLayout(null);
        setBorder(new EmptyBorder(10, 10, 10, 10));

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
        totalMemHeader.setFont(new Font("Arial",Font.BOLD, 13));
        datePane.add(totalMemHeader);

        JLabel totalMemSub = new JLabel(wholeDateText);
        totalMemSub.setBounds(39, 16, 100, 22);
        totalMemSub.setForeground(new Color(70,70,70));
        totalMemSub.setFont(new Font("Arial",Font.PLAIN, 13));
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
                new String[]{"", "Member Name", "Status"}, 0
        ) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 2) {
                    return Boolean.class;
                }
                return super.getColumnClass(columnIndex);
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 2;
            }
        };


        // Table
        table = new JTable(tableModel);
        final boolean isChecked = false;
        // Custom checkbox renderer
        TableCellRenderer customCheckboxRenderer = new TableCellRenderer() {

            private final JCheckBox checkBox = new JCheckBox() {
                @Override
                protected void paintComponent(Graphics g) {
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    int size = 20;
                    int x = (getWidth() - size) / 2; // Center horizontally
                    int y = (getHeight() - size) / 2; // Center vertically
                    g2.setColor(isSelected() ? new Color(25, 161, 25) : Color.RED);
                    g2.fillRoundRect(x, y, size, size, 10, 10);
                    g2.setColor(Color.WHITE);
                    g2.drawRoundRect(x, y, size, size, 10, 10);
                    if (isSelected()) {
                        g2.setColor(new Color(25, 161, 25));
                        g2.setStroke(new BasicStroke(2));
                        g2.drawLine(x + 4, y + size / 2, x + size / 2, y + size - 4);
                        g2.drawLine(x + size / 2, y + size - 4, x + size - 4, y + 4);
                    }
                    g2.dispose();

                }
            };

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                           boolean hasFocus, int row, int column) {
                checkBox.setSelected(value != null && (Boolean) value);
                checkBox.setOpaque(false);
                checkBox.setBorder(BorderFactory.createEmptyBorder());
                checkBox.setHorizontalAlignment(SwingConstants.CENTER);
                return checkBox;
            }
        };

        // Custom checkbox editor
        TableCellEditor customCheckboxEditor = new DefaultCellEditor(new JCheckBox() {
            {
                setOpaque(false);
                setBorder(BorderFactory.createEmptyBorder());
                setHorizontalAlignment(SwingConstants.CENTER);
            }

            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                int size = 20;
                int x = (getWidth() - size) / 2; // Center horizontally
                int y = (getHeight() - size) / 2; // Center vertically
                g2.setColor(isSelected() ? new Color(25, 161, 25) : Color.RED);
                g2.fillRoundRect(x, y, size, size, 10, 10);
                g2.setColor(Color.LIGHT_GRAY);
                g2.drawRoundRect(x, y, size, size, 10, 10);
                if (isSelected()) {
                    g2.setColor(new Color(25, 161, 25));
                    g2.setStroke(new BasicStroke(2));
                    g2.drawLine(x + 4, y + size / 2, x + size / 2, y + size - 4);
                    g2.drawLine(x + size / 2, y + size - 4, x + size - 4, y + 4);
                }
                g2.dispose();
            }
        }) {
            @Override
            public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
                JCheckBox editor = (JCheckBox) super.getTableCellEditorComponent(table, value, isSelected, row, column);
                editor.setSelected(value != null && (Boolean) value);
                editor.repaint(); // Ensure repaint on state change
                return editor;
            }
        };



        // Apply renderer and editor to Last column
        table.getColumnModel().getColumn(2).setCellRenderer(customCheckboxRenderer);
        table.getColumnModel().getColumn(2).setCellEditor(customCheckboxEditor);

        // Center-align text in non-checkbox columns
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);

        // Set preferred column widths
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.getColumnModel().getColumn(0).setPreferredWidth(60);   // "#"
        table.getColumnModel().getColumn(1).setPreferredWidth(390);  // "Member Name"
        table.getColumnModel().getColumn(2).setPreferredWidth(225);  // Status

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

        // Table visual setup
        table.setFont(new Font("Trebuchet MS", Font.BOLD, 17));
        table.setRowHeight(48);
        table.setShowGrid(false);
        table.setIntercellSpacing(new Dimension(0, 0));
        table.setForeground(Color.BLACK);
        table.setShowVerticalLines(false);
        table.setShowHorizontalLines(false);
        table.setSelectionBackground(Color.WHITE);

        // Scroll pane with table
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(265, 100, 693, 393);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        add(scrollPane);

        // Add TableModelListener to update Remarks column when Start or End changes
        tableModel.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if (e.getType() == TableModelEvent.UPDATE && (e.getColumn() == 2)) {
                    int row = e.getFirstRow();
                    Boolean start = (Boolean) tableModel.getValueAt(row, 2);
                    Boolean end = (Boolean) tableModel.getValueAt(row, 3);
                    String status = (start && end) ? "Present" : "Absent";
                    tableModel.setValueAt(status, row, 4);
                }
            }
        });

        // Add rows with initial Boolean values for Start and End
        String[] names = {"Jana Agustin", "Peavey Capacio", "Joseph Desalit", "Eliand Penus", "Earl Perucho"};
        for (int i = 0; i < 15; i++) {
            String name = names[i % names.length]; // loop through names repeatedly
            tableModel.addRow(new Object[]{String.valueOf(i + 1), name, false, false, "Absent"});
        }

        saveBtn = new JButton("Save");
        saveBtn.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
        saveBtn.setBounds(854, 505, 90, 35); // move above or just below the table
        add(saveBtn);
    }

    // Method to get checkbox states for all rows
    public List<CheckboxStates> getCheckboxStates() {
        List<CheckboxStates> states = new ArrayList<>();
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            boolean start = (Boolean) tableModel.getValueAt(i, 2);
            boolean end = (Boolean) tableModel.getValueAt(i, 3);
            states.add(new CheckboxStates(start, end));
        }
        return states;
    }

    public static class CheckboxStates {
        private final boolean start;
        private final boolean end;

        public CheckboxStates(boolean start, boolean end) {
            this.start = start;
            this.end = end;
        }
        
    }


}