package src.gui;

import src.backend.Login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginWindow {
    private JFrame frame;

    public LoginWindow() {
        frame = new JFrame("Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 600);
        frame.setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(41, 128, 185));
        mainPanel.setLayout(new GridBagLayout());

        JPanel loginCard = new JPanel();
        loginCard.setPreferredSize(new Dimension(320, 400));
        loginCard.setBackground(Color.WHITE);
        loginCard.setLayout(new BoxLayout(loginCard, BoxLayout.Y_AXIS));
        loginCard.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        JLabel title = new JLabel("Login");
        title.setFont(new Font("SansSerif", Font.BOLD, 26));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField userField = new JTextField();
        userField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
        userField.setBorder(BorderFactory.createTitledBorder("Username"));

        JPasswordField passField = new JPasswordField();
        passField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
        passField.setBorder(BorderFactory.createTitledBorder("Password"));

        JButton loginBtn = new JButton("CONTINUE");
        loginBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginBtn.setBackground(new Color(41, 128, 185));
        loginBtn.setForeground(Color.WHITE);
        loginBtn.setOpaque(true);
        loginBtn.setBorderPainted(false);
        loginBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JLabel registerLink = new JLabel("Don't have an account?");
        registerLink.setAlignmentX(Component.CENTER_ALIGNMENT);
        registerLink.setForeground(new Color(41, 128, 185));
        registerLink.setCursor(new Cursor(Cursor.HAND_CURSOR));

        registerLink.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
                new RegisterWindow();
            }
        });

        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String user = userField.getText();
                String pass = new String(passField.getPassword());
                String role = Login.authenticate(user, pass);
                if (role != null) {
                    JOptionPane.showMessageDialog(frame, "Login successful", "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                    frame.dispose();
                    if ("Club President".equalsIgnoreCase(role)) {
                        new ClubPresidentWindow();
                    } else {
                        new NormalUserWindow();
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid credentials", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        loginCard.add(title);
        loginCard.add(Box.createRigidArea(new Dimension(0, 40)));
        loginCard.add(userField);
        loginCard.add(Box.createRigidArea(new Dimension(0, 15)));
        loginCard.add(passField);
        loginCard.add(Box.createRigidArea(new Dimension(0, 40)));
        loginCard.add(loginBtn);
        loginCard.add(Box.createRigidArea(new Dimension(0, 20)));
        loginCard.add(registerLink);

        mainPanel.add(loginCard);
        frame.add(mainPanel);
    }

    public void show() {
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        LoginWindow window = new LoginWindow();
        window.show();
    }
}