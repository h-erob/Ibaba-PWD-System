package pages;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class records_members extends JPanel {
    private final JTable table;
    private final DefaultTableModel tableModel;
    private final JTextField search;
    private final JButton addBtn;
    private final JButton updBtn;

    public records_members() {
        setLayout(null);
        setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel topBar = new JPanel();
        topBar.setBackground(new Color(210, 210, 210));
        topBar.setBounds(0, 0, 991, 50);
        topBar.setLayout(null);
        add(topBar);

        JLabel label = new JLabel("Total Members: ");
        label.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
        label.setBounds(34, 11, 900, 30);
        topBar.add(label);

        search = new JTextField("Search");
        search.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
        search.setBounds(773, 11, 168, 31);
        search.setBackground(Color.WHITE);
        search.setForeground(Color.GRAY);
        search.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY, 1),
                BorderFactory.createEmptyBorder(0, 10, 0, 0) // top, left, bottom, right
        ));
        topBar.add(search);

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

        JCheckBox viewDemo = new JCheckBox("View Demographic Sheet") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                int boxSize = 20;
                int boxX = 0;
                int boxY = (getHeight() - boxSize) / 2;

                if (getModel().isSelected()) {
                    g2.setColor(Color.BLACK);
                    g2.fillRoundRect(boxX, boxY, boxSize, boxSize, 5, 5);

                    g2.setColor(Color.WHITE);
                    g2.setStroke(new BasicStroke(4));
                    g2.drawLine(boxX + 5, boxY + 10, boxX + 9, boxY + 14);
                    g2.drawLine(boxX + 9, boxY + 14, boxX + 15, boxY + 6);
                } else {
                    g2.setColor(Color.BLACK);
                    g2.drawRoundRect(boxX, boxY, boxSize, boxSize, 5, 5);
                }

                // Draw the label text manually
                g2.setFont(getFont());
                g2.setColor(getForeground());

                FontMetrics fm = g2.getFontMetrics();
                int textX = boxX + boxSize + 6;
                int textY = (getHeight() - fm.getHeight()) / 2 + fm.getAscent();
                g2.drawString(getText(), textX, textY);

                g2.dispose();
            }

            @Override
            public boolean isOpaque() {
                return false;
            }
        };

        viewDemo.setFocusPainted(false);
        viewDemo.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
        viewDemo.setForeground(Color.BLACK);
        viewDemo.setBounds(34, 58, 300, 30); // width adjusted for full label
        add(viewDemo);

        addBtn = new JButton("Add");
        addBtn.setBounds(765, 58, 90, 35);
        addBtn.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
        add(addBtn);
        addBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pages.records_membersbtn.addMembersPage.launch();
            }
        } );

        updBtn = new JButton("Update");
        updBtn.setBounds(862, 58, 90, 35);
        updBtn.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
        add(updBtn);


        tableModel = new DefaultTableModel(
                new String[]{"#", "Member Name", "PWD-ID #", "Birthday", "Sex", "PWD #", "Guardian #", "Remarks" }, 0
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
        table.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(7).setCellRenderer(centerRenderer);

        // Set preferred column widths
        table.getColumnModel().getColumn(0).setPreferredWidth(45);   // "#"
        table.getColumnModel().getColumn(1).setPreferredWidth(260);  // "Member Name"
        table.getColumnModel().getColumn(2).setPreferredWidth(200);  // "PWD id num"
        table.getColumnModel().getColumn(3).setPreferredWidth(153);  // "Birthday"
        table.getColumnModel().getColumn(4).setPreferredWidth(80);   // "Sex"
        table.getColumnModel().getColumn(5).setPreferredWidth(180);  // "PWD num"
        table.getColumnModel().getColumn(6).setPreferredWidth(200);  // "Guardian num"
        table.getColumnModel().getColumn(7).setPreferredWidth(140);  // Remarks

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
        scrollPane.setBounds(32, 100, 935, 418);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        add(scrollPane);

        tableModel.addRow(new Object[]{1,"Jana Agustin", "CH-0000-0000-0000", "12-29-04", "F", "00000000000", "09953649868", "Deceased"});
        tableModel.addRow(new Object[]{2,"Joseph Desalit", "CH-0000-0000-0000", "07-18-04", "M", "00000000000", "09051231234", "Alive"});
        tableModel.addRow(new Object[]{3,"Jana Agustin", "CH-0000-0000-0000", "12-29-04", "F", "00000000000", "09953649868", "Deceased"});
        tableModel.addRow(new Object[]{4,"Joseph Desalit", "CH-0000-0000-0000", "07-18-04", "M", "00000000000", "09051231234", "Alive"});
        tableModel.addRow(new Object[]{5,"Jana Agustin", "CH-0000-0000-0000", "12-29-04", "F", "00000000000", "09953649868", "Deceased"});
        tableModel.addRow(new Object[]{6,"Joseph Desalit", "CH-0000-0000-0000", "07-18-04", "M", "00000000000", "09051231234", "Alive"});
        tableModel.addRow(new Object[]{7,"Jana Agustin", "CH-0000-0000-0000", "12-29-04", "F", "00000000000", "09953649868", "Deceased"});
        tableModel.addRow(new Object[]{8,"Joseph Desalit", "CH-0000-0000-0000", "07-18-04", "M", "00000000000", "09051231234", "Alive"});
        tableModel.addRow(new Object[]{9,"Joseph Desalit", "CH-0000-0000-0000", "07-18-04", "M", "00000000000", "09051231234", "Alive"});
    }
}
