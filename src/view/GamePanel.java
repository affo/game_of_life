package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class GamePanel extends JPanel {

	private static final long serialVersionUID = 1860979716621182121L;
	private JPanel buttonsPanel;
	private JButton play;
	private JButton pause;
	private JButton step;
	private GameGrid grid;

	public GamePanel() {
		super();
		setLayout(new BorderLayout());
		play = new JButton("play");
		play.addActionListener(new PlayListener());
		pause = new JButton("pause");
		pause.addActionListener(new PauseListener());
		step = new JButton("step");
		step.addActionListener(new StepListener());
		buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new FlowLayout());
		buttonsPanel.add(play);
		buttonsPanel.add(pause);
		buttonsPanel.add(step);
		grid = new GameGrid();
		add(grid, BorderLayout.PAGE_START);
		add(buttonsPanel, BorderLayout.CENTER);
	}

	public GameGrid getGrid() {
		return grid;
	}

	public void disableButtons() {
		play.setEnabled(false);
		step.setEnabled(false);
	}
	
	public void enableButtons() {
		play.setEnabled(true);
		step.setEnabled(true);	
	}
	
}
