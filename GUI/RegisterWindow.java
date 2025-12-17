package GUI;

import javax.swing.*;
import java.awt.*;

public class RegisterWindow {

    private JFrame register;

    public RegisterWindow() {
        register = new JFrame("Register");
        register.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        register.setSize(420, 260);
        register.setLocationRelativeTo(null);

        JPanel main = new JPanel(new GridBagLayout());
        main.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // ===== Labels & Fields =====
        JLabel lblName = new JLabel("Name:");
        JTextField txtName = new JTextField(20);

        JLabel lblFirstName = new JLabel("First Name:");
        JTextField txtFirstName = new JTextField(20);

        JLabel lblEmail = new JLabel("E-mail:");
        JTextField txtEmail = new JTextField(20);

        // Row 0
        gbc.gridx = 0;
        gbc.gridy = 0;
        main.add(lblName, gbc);

        gbc.gridx = 1;
        main.add(txtName, gbc);

        // Row 1
        gbc.gridx = 0;
        gbc.gridy = 1;
        main.add(lblFirstName, gbc);

        gbc.gridx = 1;
        main.add(txtFirstName, gbc);

        // Row 2
        gbc.gridx = 0;
        gbc.gridy = 2;
        main.add(lblEmail, gbc);

        gbc.gridx = 1;
        main.add(txtEmail, gbc);

        // ===== Buttons =====
        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton okButton = new JButton("OK");
        JButton cancelButton = new JButton("Cancel");

        buttons.add(okButton);
        buttons.add(cancelButton);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(15, 6, 6, 6);
        main.add(buttons, gbc);

        register.setContentPane(main);
        register.setVisible(true);
    }
}

