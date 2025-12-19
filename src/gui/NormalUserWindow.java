package src.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NormalUserWindow {
    private String userEmail;

    public NormalUserWindow(String email) {
        this.userEmail = (email != null) ? email.trim().toLowerCase() : null;
        JFrame frame = new JFrame("User Dashboard");
        frame.setSize(420, 360);
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

        // Listeners
        seeEventsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                java.util.List<String> joinedClubs = src.backend.ClubManager.getJoinedClubs(userEmail);
                if (joinedClubs.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "You haven't joined any clubs yet.", "Joined Clubs",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    StringBuilder sb = new StringBuilder("You are in the following clubs:\n");
                    for (String club : joinedClubs) {
                        sb.append("- ").append(club).append("\n");
                    }
                    JOptionPane.showMessageDialog(frame, sb.toString(), "Joined Clubs",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        joinClubButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                java.util.List<String> clubs = src.backend.ClubManager.getAllClubs();
                if (clubs.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "No clubs available to join.", "Info",
                            JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                String[] clubArray = clubs.toArray(new String[0]);
                String selectedClub = (String) JOptionPane.showInputDialog(
                        frame,
                        "Select a Club to Join:",
                        "Join a Club",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        clubArray,
                        clubArray[0]);

                if (selectedClub != null) {
                    boolean success = src.backend.ClubManager.joinClub(userEmail, selectedClub);
                    if (success) {
                        JOptionPane.showMessageDialog(frame, "Successfully joined " + selectedClub, "Success",
                                JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(frame,
                                "Failed to join club. You may already be a member of this club.", "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        quitClubButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                java.util.List<String> joinedClubs = src.backend.ClubManager.getJoinedClubs(userEmail);
                java.util.List<String> ownedClubs = src.backend.ClubManager.getOwnedClubs(userEmail);

                // Defensive: Even if a President is in the "Normal User" window, prevent them
                // from quitting their own club
                java.util.Iterator<String> it = joinedClubs.iterator();
                while (it.hasNext()) {
                    String club = it.next();
                    for (String owned : ownedClubs) {
                        if (club.trim().equalsIgnoreCase(owned.trim())) {
                            it.remove();
                            break;
                        }
                    }
                }

                if (joinedClubs.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "You are not a member of any other clubs that you can quit.",
                            "Quit a Club",
                            JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                String[] clubArray = joinedClubs.toArray(new String[0]);
                String selectedClub = (String) JOptionPane.showInputDialog(
                        frame,
                        "Select a Club to Quit:",
                        "Quit a Club",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        clubArray,
                        clubArray[0]);

                if (selectedClub != null) {
                    boolean success = src.backend.ClubManager.quitClub(userEmail, selectedClub);
                    if (success) {
                        JOptionPane.showMessageDialog(frame, "Successfully quit " + selectedClub, "Success",
                                JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(frame, "Failed to quit club. Please try again.", "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        createClubLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                // Custom panel for input
                JPanel panel = new JPanel(new GridLayout(0, 1));
                JTextField clubNameField = new JTextField();
                JTextField maxMembersField = new JTextField();
                JTextField categoryField = new JTextField();

                panel.add(new JLabel("Club Name:"));
                panel.add(clubNameField);
                panel.add(new JLabel("Max Members:"));
                panel.add(maxMembersField);
                panel.add(new JLabel("Activity Category:"));
                panel.add(categoryField);

                int result = JOptionPane.showConfirmDialog(frame, panel, "Create Club", JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE);

                if (result == JOptionPane.OK_OPTION) {
                    String clubName = clubNameField.getText();
                    String maxMembersStr = maxMembersField.getText();
                    String category = categoryField.getText();

                    if (clubName != null && !clubName.trim().isEmpty() && maxMembersStr != null
                            && !maxMembersStr.trim().isEmpty() && category != null && !category.trim().isEmpty()) {
                        try {
                            int maxMembers = Integer.parseInt(maxMembersStr.trim());
                            boolean success = src.backend.ClubManager.createClub(userEmail, clubName, maxMembers,
                                    category);
                            if (success) {
                                JOptionPane.showMessageDialog(frame,
                                        "Club created successfully! You are now a Club President.", "Success",
                                        JOptionPane.INFORMATION_MESSAGE);
                                frame.dispose();
                                new ClubPresidentWindow(userEmail);
                            } else {
                                JOptionPane.showMessageDialog(frame,
                                        "Failed to create club. Possible reasons:\n- Club name already exists\n- You have reached the 2-club limit",
                                        "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(frame, "Max Members must be a valid number.", "Invalid Input",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(frame, "All fields are required.", "Invalid Input",
                                JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        });

        frame.setVisible(true);
    }
}
