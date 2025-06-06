package pages;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class homePage extends JPanel {

    public homePage() {
        setLayout(null);
        setBorder(new EmptyBorder(10, 10, 10, 10));

        ImageIcon background = new ImageIcon("imgs/background.png");
        Image scaled1 = background.getImage().getScaledInstance(1000, 590, Image.SCALE_SMOOTH);
        ImageIcon resized1 = new ImageIcon(scaled1);
        JLabel bgImg = new JLabel(resized1);
        bgImg.setBounds(0, 0, 1000, 590);
        add(bgImg);

    }


}