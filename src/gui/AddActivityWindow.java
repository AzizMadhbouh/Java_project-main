package src.gui;

import src.backend.AddActivity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddActivityWindow {

    public AddActivityWindow() {
        JFrame frame = new JFrame("Add Activity");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(420, 320);
        frame.setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(new Color(41, 128, 185));

        JPanel card = new JPanel();
        card.setPreferredSize(new Dimension(340, 260));
        card.setBackground(Color.WHITE);
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));

        JLabel title = new JLabel("Add Activity");
        title.setFont(new Font("SansSerif", Font.BOLD, 20));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField activityField = new JTextField();
        activityField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 36));
        activityField.setBorder(BorderFactory.createTitledBorder("Activity"));

        JTextField typeField = new JTextField();
        typeField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 36));
        typeField.setBorder(BorderFactory.createTitledBorder("Type"));

        JTextField dateField = new JTextField();
        dateField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 36));
        dateField.setBorder(BorderFactory.createTitledBorder("Date"));

        JPanel btnRow = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnRow.setOpaque(false);
        JButton ok = new JButton("OK");
        JButton cancel = new JButton("Cancel");
        btnRow.add(ok);
        btnRow.add(cancel);

        card.add(title);
        card.add(Box.createRigidArea(new Dimension(0, 12)));
        card.add(activityField);
        card.add(Box.createRigidArea(new Dimension(0, 8)));
        card.add(typeField);
        card.add(Box.createRigidArea(new Dimension(0, 8)));
        card.add(dateField);
        card.add(Box.createRigidArea(new Dimension(0, 12)));
        card.add(btnRow);

        mainPanel.add(card);
        frame.add(mainPanel);

        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddActivity a = new AddActivity();
                a.activity = activityField.getText();
                a.type = typeField.getText();
                a.date = dateField.getText();
                boolean saved = a.save();
                if (saved) {
                    JOptionPane.showMessageDialog(frame, "Activity added", "Success", JOptionPane.INFORMATION_MESSAGE);
                    frame.dispose();
                } else {
                    JOptionPane.showMessageDialog(frame, "Please enter an activity name", "Error", JOptionPane.ERROR_MESSAGE);
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
