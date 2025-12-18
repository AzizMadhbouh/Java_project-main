package src.gui;

import javax.swing.*;
import java.awt.*;

public class ClubPresidentWindow {
    public ClubPresidentWindow() {
        JFrame frame = new JFrame("President Dashboard");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        JPanel main = new JPanel(new GridBagLayout());
        main.setBackground(new Color(240, 244, 248));

        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(Color.WHITE);
        card.setPreferredSize(new Dimension(400, 420));
        card.setBorder(BorderFactory.createEmptyBorder(24, 24, 24, 24));

        JLabel title = new JLabel("President Dashboard");
        title.setFont(new Font("SansSerif", Font.BOLD, 20));
        title.setAlignmentX(Component.LEFT_ALIGNMENT);

        JButton seeEventsButton = new JButton("See Events of Joined Clubs");
        GuiUtils.stylePrimaryButton(seeEventsButton);
        seeEventsButton.setAlignmentX(Component.LEFT_ALIGNMENT);

        JButton joinClubButton = new JButton("Join a Club");
        GuiUtils.styleSecondaryButton(joinClubButton);
        joinClubButton.setAlignmentX(Component.LEFT_ALIGNMENT);

        JButton quitClubButton = new JButton("Quit a Club");
        GuiUtils.styleSecondaryButton(quitClubButton);
        quitClubButton.setAlignmentX(Component.LEFT_ALIGNMENT);

        JButton seeMembersButton = new JButton("See Members of My Club");
        GuiUtils.styleSecondaryButton(seeMembersButton);
        seeMembersButton.setAlignmentX(Component.LEFT_ALIGNMENT);

        JButton addActivityButton = new JButton("Add Activity");
        GuiUtils.stylePrimaryButton(addActivityButton);
        addActivityButton.setAlignmentX(Component.LEFT_ALIGNMENT);

        card.add(title);
        card.add(Box.createRigidArea(new Dimension(0, 12)));
        card.add(seeEventsButton);
        card.add(Box.createRigidArea(new Dimension(0, 8)));
        card.add(joinClubButton);
        card.add(Box.createRigidArea(new Dimension(0, 8)));
        card.add(quitClubButton);
        card.add(Box.createRigidArea(new Dimension(0, 8)));
        card.add(seeMembersButton);
        card.add(Box.createRigidArea(new Dimension(0, 8)));
        card.add(addActivityButton);

        main.add(card);
        frame.add(main);
        frame.setVisible(true);
    }
}
