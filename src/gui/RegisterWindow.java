package src.gui;

import src.backend.Register;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterWindow {

    private JFrame frame;

    public RegisterWindow() {
        frame = new JFrame("Register");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(420, 380);
        frame.setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(41, 128, 185));
        mainPanel.setLayout(new GridBagLayout());

        JPanel card = new JPanel();
        card.setPreferredSize(new Dimension(340, 300));
        card.setBackground(Color.WHITE);
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel title = new JLabel("Register");
        title.setFont(new Font("SansSerif", Font.BOLD, 22));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField nameField = new JTextField();
        nameField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        nameField.setBorder(BorderFactory.createTitledBorder("Name"));

        JTextField firstNameField = new JTextField();
        firstNameField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        firstNameField.setBorder(BorderFactory.createTitledBorder("First Name"));

        JTextField emailField = new JTextField();
        emailField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        emailField.setBorder(BorderFactory.createTitledBorder("E-mail"));

        String[] roles = { "Normal User", "Club President" };
        JComboBox<String> roleBox = new JComboBox<>(roles);
        roleBox.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        roleBox.setBorder(BorderFactory.createTitledBorder("Role"));

        JPanel btnRow = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton ok = new JButton("OK");
        JButton cancel = new JButton("Cancel");
        btnRow.setOpaque(false);
        btnRow.add(ok);
        btnRow.add(cancel);

        card.add(title);
        card.add(Box.createRigidArea(new Dimension(0, 16)));
        card.add(nameField);
        card.add(Box.createRigidArea(new Dimension(0, 8)));
        card.add(firstNameField);
        card.add(Box.createRigidArea(new Dimension(0, 8)));
        card.add(emailField);
        card.add(Box.createRigidArea(new Dimension(0, 8)));
        card.add(roleBox);
        card.add(Box.createRigidArea(new Dimension(0, 16)));
        card.add(btnRow);

        mainPanel.add(card);
        frame.add(mainPanel);

        // Listeners
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Register r = new Register();
                r.lastName = nameField.getText();
                r.firstName = firstNameField.getText();
                r.email = emailField.getText();
                r.role = (String) roleBox.getSelectedItem();
                boolean ok = r.save();
                if (ok) {
                    String msg = "Registered successfully.\n" +
                            "Username: " + r.email + "\n" +
                            "Password: " + r.generatedPassword;
                    JTextArea textArea = new JTextArea(msg);
                    textArea.setEditable(false);
                    JOptionPane.showMessageDialog(frame, textArea, "Success", JOptionPane.INFORMATION_MESSAGE);
                    frame.dispose();
                    new LoginWindow().show();
                } else {
                    JOptionPane.showMessageDialog(frame, "Please enter valid information or existing email", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        frame.setVisible(true);
    }
}
