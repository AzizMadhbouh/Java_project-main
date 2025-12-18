package src.gui;

import javax.swing.*;
import java.awt.*;

public class AddActivityWindow {

    public AddActivityWindow() {
        JFrame frame = new JFrame("Add Activity");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        JPanel main = new JPanel(new GridBagLayout());
        main.setBackground(new Color(240, 244, 248));

        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(Color.WHITE);
        card.setPreferredSize(new Dimension(400, 320));
        card.setBorder(BorderFactory.createEmptyBorder(24, 24, 24, 24));

        JLabel title = new JLabel("Add Activity");
        title.setFont(new Font("SansSerif", Font.BOLD, 22));
        title.setAlignmentX(Component.LEFT_ALIGNMENT);

        JTextField activityField = new JTextField();
        activityField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        activityField.setAlignmentX(Component.LEFT_ALIGNMENT);

        JTextField typeField = new JTextField();
        typeField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        typeField.setAlignmentX(Component.LEFT_ALIGNMENT);

        JTextField dateField = new JTextField();
        dateField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        dateField.setAlignmentX(Component.LEFT_ALIGNMENT);

        card.add(title);
        card.add(Box.createRigidArea(new Dimension(0, 16)));
        card.add(new JLabel("Activity:"));
        card.add(Box.createRigidArea(new Dimension(0, 6)));
        card.add(activityField);
        card.add(Box.createRigidArea(new Dimension(0, 10)));
        card.add(new JLabel("Type:"));
        card.add(Box.createRigidArea(new Dimension(0, 6)));
        card.add(typeField);
        card.add(Box.createRigidArea(new Dimension(0, 10)));
        card.add(new JLabel("Date:"));
        card.add(Box.createRigidArea(new Dimension(0, 6)));
        card.add(dateField);

        main.add(card);
        frame.add(main);
        frame.setVisible(true);
    }
}
