package GUI;

import javax.swing.*;
import java.awt.*;

public class LoginWindow {

    private JFrame loginFrame;

    public LoginWindow() {
        loginFrame = new JFrame("Login");
        loginFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        loginFrame.setSize(380, 220);
        loginFrame.setLocationRelativeTo(null);

        JPanel main = new JPanel(new GridBagLayout());
        main.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // ===== Fields =====
        JLabel lblUsername = new JLabel("Username:");
        JTextField txtUsername = new JTextField(20);

        JLabel lblPassword = new JLabel("Password:");
        JPasswordField txtPassword = new JPasswordField(20);

        // Row 0 - Username
        gbc.gridx = 0;
        gbc.gridy = 0;
        main.add(lblUsername, gbc);

        gbc.gridx = 1;
        main.add(txtUsername, gbc);

        // Row 1 - Password
        gbc.gridx = 0;
        gbc.gridy = 1;
        main.add(lblPassword, gbc);

        gbc.gridx = 1;
        main.add(txtPassword, gbc);

        // ===== Buttons =====
        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton okButton = new JButton("OK");
        JButton cancelButton = new JButton("Cancel");

        buttons.add(okButton);
        buttons.add(cancelButton);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(15, 6, 6, 6);
        main.add(buttons, gbc);

        loginFrame.setContentPane(main);
        loginFrame.setVisible(true);
    }
}
