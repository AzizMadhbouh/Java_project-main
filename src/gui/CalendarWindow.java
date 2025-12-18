package src.gui;

import javax.swing.*;
import java.awt.*;

public class CalendarWindow {
	public CalendarWindow() {
		JFrame frame = new JFrame("Calendar");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

		JPanel main = new JPanel(new GridBagLayout());
		main.setBackground(new Color(240, 244, 248));

		JPanel card = new JPanel();
		card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
		card.setBackground(Color.WHITE);
		card.setPreferredSize(new Dimension(400, 300));
		card.setBorder(BorderFactory.createEmptyBorder(24, 24, 24, 24));

		JLabel title = new JLabel("Calendar");
		title.setFont(new Font("SansSerif", Font.BOLD, 20));
		title.setAlignmentX(Component.LEFT_ALIGNMENT);

		JLabel placeholder = new JLabel("(calendar placeholder)");
		placeholder.setForeground(Color.DARK_GRAY);
		placeholder.setAlignmentX(Component.LEFT_ALIGNMENT);

		card.add(title);
		card.add(Box.createRigidArea(new Dimension(0, 12)));
		card.add(placeholder);

		main.add(card);
		frame.add(main);
		frame.setVisible(true);
	}
}
