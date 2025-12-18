package src.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClubPresidentWindow {
    public ClubPresidentWindow() {
        JFrame frame = new JFrame("President Dashboard");
        frame.setSize(420, 420);
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

        seeEventsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "See events clicked", "Info", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        joinClubButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Join club clicked", "Info", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        quitClubButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Quit club clicked", "Info", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        seeMembersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "See members clicked", "Info", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        addActivityButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddActivityWindow();
            }
        });

        frame.setVisible(true);
    }
}
