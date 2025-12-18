package src.gui;

import javax.swing.*;
import java.awt.*;

public class NormalUserWindow {
    public NormalUserWindow() {
        JFrame frame = new JFrame("User Dashboard");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        JPanel main = new JPanel(new GridBagLayout());
        main.setBackground(new Color(240, 244, 248));

        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(Color.WHITE);
        card.setPreferredSize(new Dimension(400, 360));
        card.setBorder(BorderFactory.createEmptyBorder(24, 24, 24, 24));

        JLabel title = new JLabel("User Dashboard");
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

        card.add(title);
        card.add(Box.createRigidArea(new Dimension(0, 14)));
        card.add(seeEventsButton);
        card.add(Box.createRigidArea(new Dimension(0, 8)));
        card.add(joinClubButton);
        card.add(Box.createRigidArea(new Dimension(0, 8)));
        card.add(quitClubButton);

        main.add(card);
        frame.add(main);
        frame.setVisible(true);
    }
}
