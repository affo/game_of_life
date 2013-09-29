package view;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class GamePanel extends JPanel {

	private static final long serialVersionUID = 1860979716621182121L;
	private JPanel buttonsPanel;
	private JButton play;
	private GameGrid grid;

	public GamePanel() {
		super();
		setLayout(new BorderLayout());
		buttonsPanel = new JPanel();
		play = new JButton("play");
		play.addActionListener(new EpochListener());
		buttonsPanel.add(play);
		grid = new GameGrid();
		add(grid, BorderLayout.PAGE_START);
		add(buttonsPanel, BorderLayout.CENTER);
	}

	public GameGrid getGrid() {
		return grid;
	}

}
