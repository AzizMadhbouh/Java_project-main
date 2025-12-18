package src.gui;

import javax.swing.*;
import java.awt.*;

public class CalendarWindow {
	public CalendarWindow() {
		JFrame frame = new JFrame("Calendar");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(420, 360);
		frame.setLocationRelativeTo(null);

		JPanel mainPanel = new JPanel(new GridBagLayout());
		mainPanel.setBackground(new Color(41,128,185));

		JPanel card = new JPanel();
		card.setBackground(Color.WHITE);
		card.setPreferredSize(new Dimension(340, 260));
		card.setBorder(BorderFactory.createEmptyBorder(16,16,16,16));

		JLabel title = new JLabel("Calendar (placeholder)");
		title.setFont(new Font("SansSerif", Font.BOLD, 18));
		title.setHorizontalAlignment(SwingConstants.CENTER);

		card.add(title);
		mainPanel.add(card);
		frame.add(mainPanel);
		frame.setVisible(true);
	}
}
