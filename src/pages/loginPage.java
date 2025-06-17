package pages;
import dal.admins.adminDAO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;


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
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 645, 415);
        JPanel contentPane = new JPanel();
        contentPane.setBackground(new Color(57, 57, 57));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        /*
        ImageIcon Logo = new ImageIcon("imgs/loginLogo.png");
        Image LogoSize = Logo.getImage().getScaledInstance(200,150, Image.SCALE_SMOOTH);
        ImageIcon resizedLogo = new ImageIcon(LogoSize);
        setIconImage(resizedLogo.getImage()); */

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JTextField dummy = new JTextField();
        dummy.setBounds(0, 0, 0, 0);
        contentPane.add(dummy);
        dummy.setFocusable(true);
        dummy.requestFocusInWindow();

        JPanel bgPanel = new JPanel();
        bgPanel.setBounds(248, 0, 400, 413);
        contentPane.add(bgPanel);
        bgPanel.setLayout(null);

        ImageIcon brgyLogo = new ImageIcon("imgs/loginLogo.png");
        Image size = brgyLogo.getImage().getScaledInstance(173, 100, Image.SCALE_SMOOTH);
        ImageIcon resize1 = new ImageIcon(size);
        JLabel BRGYLogo = new JLabel(resize1);
        BRGYLogo.setBounds(30, 27, 173, 71);
        contentPane.add(BRGYLogo);

        ImageIcon bgLogo = new ImageIcon("imgs/loginBg.jpg");
        Image bgsize = bgLogo.getImage().getScaledInstance(482, 413, Image.SCALE_SMOOTH);
        ImageIcon resize = new ImageIcon(bgsize);
        JLabel BGLogo = new JLabel(resize);
        BGLogo.setBounds(0, 0, 384, 379);
        bgPanel.add(BGLogo);

        Username = new JTextField();
        Username.setFont(new Font("Tahoma", Font.PLAIN, 10));
        Username.setBackground(new Color(57, 57, 57));
        Username.setForeground(Color.GRAY);
        Username.setText("Username");
        Username.setBounds(42, 130, 154, 26);
        Username.setCaretColor(new Color(255, 255, 255));
        Username.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY, 1),
                BorderFactory.createEmptyBorder(0, 7, 0, 0) // top, left, bottom, right
        ));
        contentPane.add(Username);
        Username.setColumns(10);

        passwordField = new JPasswordField();
        //passwordField.setBounds(42, 167, 154, 26); // Position below Username field
        passwordField.setFont(new Font("Tahoma", Font.PLAIN, 10));
        passwordField.setBackground(new Color(57, 57, 57));
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
        //showPass.setBounds(170, 167, 20, 20); // Adjusted to appear on the right side of passwordField
        showPass.setIcon(resizedShowIcon);
        showPass.setSelectedIcon(resizedHideIcon);
        showPass.setBorderPainted(false);
        showPass.setFocusPainted(false);
        showPass.setContentAreaFilled(false);
        showPass.addActionListener(e -> showPassword());
        contentPane.add(showPass);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBounds(42, 167, 154, 26);
        passwordField.setBounds(0, 0, 154, 26);
        showPass.setBounds(130, 3, 20, 20); // Inside passwordField, right-aligned
        layeredPane.add(passwordField, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(showPass, JLayeredPane.PALETTE_LAYER); // Higher layer
        contentPane.add(layeredPane);

        JLabel signInTXT = new JLabel("Sign In");
        signInTXT.setFont(new Font("Tahoma", Font.BOLD, 16));
        signInTXT.setForeground(new Color(255, 255, 255));
        signInTXT.setBounds(87, 97, 71, 26);
        contentPane.add(signInTXT);


        proceed = new JButton(">");
        proceed.setFont(new Font("Arial", Font.BOLD, 30));
        proceed.setBounds(94, 260, 42, 40);
        proceed.setBackground(new Color(0, 202, 238));
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
            JOptionPane.showMessageDialog(this, "Login successful!");
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
