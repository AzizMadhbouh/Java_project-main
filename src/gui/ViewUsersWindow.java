package src.gui;

import javax.swing.*;
import java.awt.*;

public class ViewUsersWindow {
	public ViewUsersWindow() {
		JFrame frame = new JFrame("View Users");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

		JPanel main = new JPanel(new GridBagLayout());
		main.setBackground(new Color(240, 244, 248));

		JPanel card = new JPanel();
		card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
		card.setBackground(Color.WHITE);
		card.setPreferredSize(new Dimension(500, 420));
		card.setBorder(BorderFactory.createEmptyBorder(24, 24, 24, 24));

		JLabel title = new JLabel("Users");
		title.setFont(new Font("SansSerif", Font.BOLD, 18));
		title.setAlignmentX(Component.LEFT_ALIGNMENT);

		JList<String> list = new JList<>(new String[]{"User1", "User2", "User3"});
		list.setAlignmentX(Component.LEFT_ALIGNMENT);

		card.add(title);
		card.add(Box.createRigidArea(new Dimension(0, 12)));
		card.add(new JScrollPane(list));

		main.add(card);
		frame.add(main);
		frame.setVisible(true);
	}
}
