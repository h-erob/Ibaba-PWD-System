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
    private final JButton delBtn;
    private final JButton updBtn;

    public records_members() {
        setLayout(null);
        setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel topBar = new JPanel();
        topBar.setBackground(new Color(210, 210, 210));
        topBar.setBounds(0, 0, 991, 50);
        topBar.setLayout(null);
        add(topBar);

        JLabel label = new JLabel("Total Members in the System: ");
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

        addBtn = new JButton("Add");
        addBtn.setBounds(670, 58, 90, 35);
        addBtn.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
        add(addBtn);
        addBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pages.records_membersbtn.addMembersPage.launch();
            }
        } );

        delBtn = new JButton("Delete");
        delBtn.setBounds(766, 58, 90, 35);
        delBtn.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
        add(delBtn);

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



        /* Add rows with initial Boolean values for Start and End
        String[] names = {"Jana Agustin", "Peavey Capacio", "Joseph Desalit", "Eliand Penus", "Earl Perucho"};
        for (int i = 0; i < 15; i++) {
            String name = names[i % names.length]; // loop through names repeatedly
            tableModel.addRow(new Object[]{String.valueOf(i + 1), name, "December", "11"});
        } */
    }
}
