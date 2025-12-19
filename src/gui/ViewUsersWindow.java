package src.gui;

import javax.swing.*;
import java.awt.*;

public class ViewUsersWindow {
	public ViewUsersWindow(java.util.List<String> users) {
		JFrame frame = new JFrame("Club Members");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(500, 400);
		frame.setLocationRelativeTo(null);

		JPanel main = new JPanel(new BorderLayout());
		main.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

		JLabel title = new JLabel("Members of Your Club");
		title.setFont(new Font("SansSerif", Font.BOLD, 18));
		title.setHorizontalAlignment(SwingConstants.CENTER);

		main.add(title, BorderLayout.NORTH);

		String[] userArray = users.isEmpty() ? new String[] { "No members found" } : users.toArray(new String[0]);
		JList<String> list = new JList<>(userArray);
		main.add(new JScrollPane(list), BorderLayout.CENTER);

		frame.add(main);
		frame.setVisible(true);
	}
}
