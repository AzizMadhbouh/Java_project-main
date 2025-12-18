package src.gui;

import javax.swing.*;
import java.awt.*;

public class RegisterWindow {

    public RegisterWindow() {
        JFrame frame = new JFrame("Register");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        JPanel main = new JPanel(new GridBagLayout());
        main.setBackground(new Color(240, 244, 248));

        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(Color.WHITE);
        card.setPreferredSize(new Dimension(400, 420));
        card.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));

        JLabel title = new JLabel("Register");
        title.setFont(new Font("SansSerif", Font.BOLD, 24));
        title.setAlignmentX(Component.LEFT_ALIGNMENT);

        JTextField nameField = new JTextField();
        nameField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        nameField.setAlignmentX(Component.LEFT_ALIGNMENT);

        JTextField firstNameField = new JTextField();
        firstNameField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        firstNameField.setAlignmentX(Component.LEFT_ALIGNMENT);

        JTextField emailField = new JTextField();
        emailField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        emailField.setAlignmentX(Component.LEFT_ALIGNMENT);

        card.add(title);
        card.add(Box.createRigidArea(new Dimension(0, 20)));
        card.add(new JLabel("Name:"));
        card.add(Box.createRigidArea(new Dimension(0, 6)));
        card.add(nameField);
        card.add(Box.createRigidArea(new Dimension(0, 10)));
        card.add(new JLabel("First Name:"));
        card.add(Box.createRigidArea(new Dimension(0, 6)));
        card.add(firstNameField);
        card.add(Box.createRigidArea(new Dimension(0, 10)));
        card.add(new JLabel("E-mail:"));
        card.add(Box.createRigidArea(new Dimension(0, 6)));
        card.add(emailField);

        main.add(card);
        frame.add(main);
        frame.setVisible(true);
    }
}

