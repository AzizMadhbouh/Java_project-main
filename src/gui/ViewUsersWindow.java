package src.gui;

import javax.swing.*;
import java.awt.*;

public class ViewUsersWindow {
	public ViewUsersWindow() {
		JFrame frame = new JFrame("View Users");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(500, 400);
		frame.setLocationRelativeTo(null);

		JPanel main = new JPanel(new BorderLayout());
		main.setBorder(BorderFactory.createEmptyBorder(12,12,12,12));

		JLabel title = new JLabel("Users (placeholder)");
		title.setFont(new Font("SansSerif", Font.BOLD, 18));
		title.setHorizontalAlignment(SwingConstants.CENTER);

		main.add(title, BorderLayout.NORTH);
		JList<String> list = new JList<>(new String[]{"User1", "User2", "User3"});
		main.add(new JScrollPane(list), BorderLayout.CENTER);

		frame.add(main);
		frame.setVisible(true);
	}
}
