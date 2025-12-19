package src.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClubPresidentWindow {
    private String presidentEmail;

    public ClubPresidentWindow(String email) {
        this.presidentEmail = (email != null) ? email.trim().toLowerCase() : null;
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
                java.util.List<String> joinedClubs = src.backend.ClubManager.getJoinedClubs(presidentEmail);
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
                    boolean success = src.backend.ClubManager.joinClub(presidentEmail, selectedClub);
                    if (success) {
                        JOptionPane.showMessageDialog(frame, "Successfully joined " + selectedClub, "Success",
                                JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(frame,
                                "Failed to join club. Possible reasons:\n- You are already a member\n- Presidents are limited to 2 clubs total",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        quitClubButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                java.util.List<String> joinedClubs = src.backend.ClubManager.getJoinedClubs(presidentEmail);
                java.util.List<String> ownedClubs = src.backend.ClubManager.getOwnedClubs(presidentEmail);

                // Use iterator to remove all clubs user leads from the "quit" list
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
                    boolean success = src.backend.ClubManager.quitClub(presidentEmail, selectedClub);
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

        seeMembersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                java.util.List<String> members = src.backend.ClubManager.getMembersByPresident(presidentEmail);
                new ViewUsersWindow(members);
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
