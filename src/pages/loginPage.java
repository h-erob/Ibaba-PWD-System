package pages;
import dal.admins.adminDAO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;


public class loginPage extends JFrame {
    private final adminDAO adminDao = new adminDAO();
    private final JTextField Username;
    private final JPasswordField passwordField;
    private final JCheckBox showPass;
    private final JButton proceed;

    public static void launch() {
        try {
            loginPage frame = new loginPage();
            frame.setTitle("Barangay Ibaba PWD Department");
            frame.setResizable(false);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public loginPage() {
        setUndecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 645, 415);
        JPanel contentPane = new JPanel();
        contentPane.setBackground(new Color(37, 37, 37));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel titleBar = new JPanel();
        titleBar.setBackground(new Color(37, 37, 37));
        titleBar.setBounds(0, 0, 645, 25);
        titleBar.setLayout(null);
        contentPane.add(titleBar);

        // Title label
        JLabel titleLabel = new JLabel("Barangay Ibaba Persons With Disability Association");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
        titleLabel.setBounds(10, 0, 350, 25);
        titleBar.add(titleLabel);

        JButton closeButton = new JButton("X");
        closeButton.setBounds(605, 0, 40, 25); // now it won't spill over
        closeButton.setBackground(new Color(200, 50, 50));
        closeButton.setForeground(Color.WHITE);
        closeButton.setFont(new Font("Arial", Font.BOLD, 14)); // Explicit smaller font
        closeButton.setFocusPainted(false);
        closeButton.setMargin(new Insets(0, 0, 0, 0)); // Remove internal padding
        closeButton.setBorderPainted(false); // Optional: flat button look
        closeButton.setContentAreaFilled(true); // Make sure background is shown
        closeButton.addActionListener(e -> {
            dispose();
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
                Point currPoint = e.getLocationOnScreen();
                setLocation(currPoint.x - mousePoint[0].x, currPoint.y - mousePoint[0].y);
            }
        });

        JTextField dummy = new JTextField();
        dummy.setBounds(0, 0, 0, 0);
        contentPane.add(dummy);
        dummy.setFocusable(true);
        dummy.requestFocusInWindow();

        JPanel bgPanel = new JPanel();
        bgPanel.setBounds(248, 25, 400, 413);
        contentPane.add(bgPanel);
        bgPanel.setLayout(null);

        ImageIcon brgyLogo = new ImageIcon("imgs/loginLogo.png");
        JLabel BRGYLogo = new JLabel(brgyLogo);
        BRGYLogo.setBounds(30, 52, 173, 55);
        contentPane.add(BRGYLogo);

        ImageIcon bgLogo = new ImageIcon("imgs/loginBg.png");
        JLabel BGLogo = new JLabel(bgLogo);
        BGLogo.setBounds(0, 0, 484, 423);
        bgPanel.add(BGLogo);

        Username = new JTextField();
        Username.setFont(new Font("Tahoma", Font.PLAIN, 10));
        Username.setBackground(new Color(37, 37, 37));
        Username.setForeground(Color.GRAY);
        Username.setText("Username");
        Username.setBounds(42, 160, 154, 26);
        Username.setCaretColor(new Color(255, 255, 255));
        Username.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY, 1),
                BorderFactory.createEmptyBorder(0, 7, 0, 0) // top, left, bottom, right
        ));
        contentPane.add(Username);
        Username.setColumns(10);

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Tahoma", Font.PLAIN, 10));
        passwordField.setBackground(new Color(37, 37, 37));
        passwordField.setForeground(Color.GRAY);
        passwordField.setCaretColor(new Color(255, 255, 255));
        passwordField.setText("Password");
        passwordField.setEchoChar((char) 0);
        passwordField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY, 1),
                BorderFactory.createEmptyBorder(0, 7, 0, 0) // top, left, bottom, right
        ));
        contentPane.add(passwordField);

        ImageIcon rawIcon = new ImageIcon("imgs/showPass.png");
        Image scaledImage = rawIcon.getImage().getScaledInstance(12, 12, Image.SCALE_SMOOTH);
        ImageIcon resizedShowIcon = new ImageIcon(scaledImage);

        ImageIcon rawSelectedIcon = new ImageIcon("imgs/hidePass.png");
        Image scaledSelectedImage = rawSelectedIcon.getImage().getScaledInstance(12, 12, Image.SCALE_SMOOTH);
        ImageIcon resizedHideIcon = new ImageIcon(scaledSelectedImage);

        showPass = new JCheckBox();
        showPass.setIcon(resizedShowIcon);
        showPass.setSelectedIcon(resizedHideIcon);
        showPass.setBorderPainted(false);
        showPass.setFocusPainted(false);
        showPass.setContentAreaFilled(false);
        showPass.addActionListener(e -> showPassword());
        contentPane.add(showPass);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBounds(42, 197, 154, 26);
        passwordField.setBounds(0, 0, 154, 26);
        showPass.setBounds(130, 3, 20, 20); // Inside passwordField, right-aligned
        layeredPane.add(passwordField, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(showPass, JLayeredPane.PALETTE_LAYER); // Higher layer
        contentPane.add(layeredPane);

        JLabel signInTXT = new JLabel("Sign In");
        signInTXT.setFont(new Font("Tahoma", Font.BOLD, 16));
        signInTXT.setForeground(new Color(255, 255, 255));
        signInTXT.setBounds(87, 122, 71, 26);
        contentPane.add(signInTXT);


        proceed = new JButton(">");
        proceed.setFont(new Font("Arial", Font.BOLD, 30));
        proceed.setBounds(94, 285, 42, 40);
        proceed.setBackground(new Color(254, 239, 25));
        proceed.setForeground(Color.BLACK);
        proceed.setBorderPainted(false);
        proceed.setFocusPainted(false);
        proceed.setOpaque(true);
        proceed.setMargin(new Insets(0, 0, 0, 0));
        proceed.setVerticalAlignment(SwingConstants.CENTER);
        proceed.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(proceed);
        getRootPane().setDefaultButton(proceed);
        proceed.addActionListener(e -> handleLogin());

        Username.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                if (Username.getText().equals("Username")) {
                    Username.setText("");
                    Username.setForeground(new Color(255, 255, 255));
                    Username.setFont(new Font("Tahoma", Font.BOLD, 10));
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                if (Username.getText().isEmpty()) {
                    Username.setText("Username");
                    Username.setForeground(Color.GRAY);
                }
            }
        });

        passwordField.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                String input = new String(passwordField.getPassword());
                if (input.equals("Password")) {
                    passwordField.setText("");
                    passwordField.setForeground(new Color(255, 255, 255));
                    passwordField.setFont(new Font("Tahoma", Font.BOLD, 10));
                }

                if (showPass.isSelected()) {
                    passwordField.setEchoChar((char) 0); // show password
                } else {
                    passwordField.setEchoChar('•'); // mask password
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                String input = new String(passwordField.getPassword());
                if (input.isEmpty()) {
                    passwordField.setText("Password");
                    passwordField.setEchoChar((char) 0); // Show plain text
                    passwordField.setForeground(Color.GRAY);
                }
            }
        });

    }

    // Checks the database if username and password is valid
    private void handleLogin() {
        String username = Username.getText();
        String password = new String(passwordField.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter both username and password.");
            return;
        }

        boolean valid = adminDao.checkIfAdminExists(username, password);
        if (valid) {
            mainPage main = new mainPage();
            main.setLocationRelativeTo(null);
            main.setVisible(true);

            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password.");
        }
    }

    // Method for showing Password
    private void showPassword() {
        String input = new String(passwordField.getPassword());
        boolean selected = showPass.isSelected();

        if (input.equals("Password")) {
            passwordField.setEchoChar((char) 0);
        } else {
            if (selected) {
                passwordField.setEchoChar((char) 0); // Show real password
            } else {
                passwordField.setEchoChar('•'); // Mask real password
            }
        }
    }

}
