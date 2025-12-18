
// File: GUI/HomeWindow.java
package src.gui;

import javax.swing.*;
import java.awt.*;

public class HomeWindow {

    private JFrame home;

    public HomeWindow() {
        home = new JFrame("Welcome");
        home.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        home.setSize(360, 220);
        home.setLocationRelativeTo(null); // center

        JPanel main = new JPanel(new GridBagLayout());
        main.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Title
        JLabel title = new JLabel("Welcome");
        title.setFont(new Font("Segoe UI", Font.BOLD, 18));
        title.setHorizontalAlignment(SwingConstants.CENTER);

        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        main.add(title, gbc);

        // Radio buttons
        JRadioButton login = new JRadioButton("Login");
        JRadioButton register = new JRadioButton("Register");

        ButtonGroup group = new ButtonGroup();
        group.add(login);
        group.add(register);
        login.setSelected(true);

        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridy = 1;
        main.add(login, gbc);

        gbc.gridy = 2;
        main.add(register, gbc);

        // Buttons panel
        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton okButton = new JButton("OK");
        JButton cancelButton = new JButton("Cancel");

        buttons.add(okButton);
        buttons.add(cancelButton);

        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = new Insets(15, 5, 5, 5);
        main.add(buttons, gbc);

        home.setContentPane(main);
        home.setVisible(true);
    }
}
