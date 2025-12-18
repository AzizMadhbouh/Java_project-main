
// File: GUI/HomeWindow.java
package src.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeWindow {

    private JFrame frame;

    public HomeWindow() {
        frame = new JFrame("Welcome");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(520, 620);
        frame.setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(41, 128, 185));
        mainPanel.setLayout(new GridBagLayout());

        JPanel card = new JPanel();
        card.setPreferredSize(new Dimension(360, 420));
        card.setBackground(Color.WHITE);
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        JLabel title = new JLabel("Welcome to ClubApp");
        title.setFont(new Font("SansSerif", Font.BOLD, 26));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitle = new JLabel("Manage clubs and activities");
        subtitle.setFont(new Font("SansSerif", Font.PLAIN, 14));
        subtitle.setForeground(Color.DARK_GRAY);
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        card.add(title);
        card.add(Box.createRigidArea(new Dimension(0, 12)));
        card.add(subtitle);
        card.add(Box.createRigidArea(new Dimension(0, 30)));

        JButton loginBtn = new JButton("LOGIN");
        stylePrimaryButton(loginBtn);
        loginBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));

        JButton registerBtn = new JButton("REGISTER");
        stylePrimaryButton(registerBtn);
        registerBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        registerBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));

        card.add(loginBtn);
        card.add(Box.createRigidArea(new Dimension(0, 16)));
        card.add(registerBtn);
        card.add(Box.createRigidArea(new Dimension(0, 20)));

        JButton exitBtn = new JButton("Exit");
        exitBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(exitBtn);

        mainPanel.add(card);
        frame.add(mainPanel);

        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                LoginWindow lw = new LoginWindow();
                lw.show();
            }
        });

        registerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new RegisterWindow();
            }
        });

        exitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        frame.setVisible(true);
    }

    private void stylePrimaryButton(JButton b) {
        b.setBackground(new Color(41, 128, 185));
        b.setForeground(Color.WHITE);
        b.setOpaque(true);
        b.setBorderPainted(false);
        b.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
}
