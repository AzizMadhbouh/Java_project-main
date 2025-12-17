package GUI;

import javax.swing.*;
import java.awt.*;

public class ClubPresidentWindow {
    public ClubPresidentWindow() {
        JFrame frame = new JFrame("President Dashboard");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Main panel with buttons
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(5, 1, 10, 10)); // 5 buttons vertically
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50)); // padding

        JButton seeEventsButton = new JButton("See Events of Joined Clubs");
        JButton joinClubButton = new JButton("Join a Club");
        JButton quitClubButton = new JButton("Quit a Club");
        JButton seeMembersButton = new JButton("See Members of My Club");
        JButton addActivityButton = new JButton("Add Activity");

        mainPanel.add(seeEventsButton);
        mainPanel.add(joinClubButton);
        mainPanel.add(quitClubButton);
        mainPanel.add(seeMembersButton);
        mainPanel.add(addActivityButton);

        frame.add(mainPanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }
}
