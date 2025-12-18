package src.gui;

import javax.swing.*;
import java.awt.*;

public class NormalUserWindow {
    public NormalUserWindow() {
        JFrame frame = new JFrame("User Dashboard");
        frame.setSize(400, 350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Main panel with buttons
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(4, 1, 10, 10)); // 4 buttons vertically
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50)); // padding

        JButton seeEventsButton = new JButton("See Events of Joined Clubs");
        JButton joinClubButton = new JButton("Join a Club");
        JButton quitClubButton = new JButton("Quit a Club");

        mainPanel.add(seeEventsButton);
        mainPanel.add(joinClubButton);
        mainPanel.add(quitClubButton);

        // "Create your own club" as a link-like label
        JLabel createClubLabel = new JLabel("<HTML><U>Create Your Own Club</U></HTML>");
        createClubLabel.setForeground(Color.BLUE);
        createClubLabel.setHorizontalAlignment(SwingConstants.CENTER);
        createClubLabel.setCursor(new Cursor(Cursor.HAND_CURSOR)); // looks clickable

        frame.add(mainPanel, BorderLayout.CENTER);
        frame.add(createClubLabel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }
}
