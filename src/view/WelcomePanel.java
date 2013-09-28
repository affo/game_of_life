package view;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class WelcomePanel extends JPanel {

	private static final long serialVersionUID = -3228318903039850883L;
	private JButton start = new JButton("start the game");

	public WelcomePanel() {
		super();
		setLayout(new GridLayout(2, 1));
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		buttonPanel.add(start);
		start.addActionListener(new WelcomeListener());
		add(buttonPanel);
	}
}