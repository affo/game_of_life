package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GamePanel extends JPanel {

	private static final long serialVersionUID = 1860979716621182121L;
	private JPanel buttonsPanel;
	private JButton play;
	private JButton pause;
	private JButton step;
	private JButton stop;
	private JButton restart;
	private JButton sample;
	private JLabelCounter epoch;
	private GameGrid grid;

	public GamePanel() {
		super();
		setLayout(new BorderLayout());
		addGameGrid();
		addButtonsPanel();
	}

	private void addGameGrid() {
		grid = new GameGrid();
		add(grid, BorderLayout.PAGE_START);
	}

	private void addButtonsPanel() {
		epoch = new JLabelCounter("Epoch 0");
		play = new JButton("play");
		play.addActionListener(new PlayListener());
		play.setEnabled(false);
		pause = new JButton("pause");
		pause.addActionListener(new PauseListener());
		pause.setEnabled(false);
		step = new JButton("step");
		step.addActionListener(new StepListener());
		step.setEnabled(false);
		stop = new JButton("stop");
		stop.addActionListener(new RestartListener());
		stop.setEnabled(false);
		sample = new JButton("Samples");
		sample.addActionListener(new SampleListener());
		sample.setEnabled(true);
		buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new FlowLayout());
		buttonsPanel.add(epoch);
		buttonsPanel.add(play);
		buttonsPanel.add(pause);
		buttonsPanel.add(step);
		buttonsPanel.add(stop);
		buttonsPanel.add(sample);
		add(buttonsPanel, BorderLayout.CENTER);
	}

	public GameGrid getGrid() {
		return grid;
	}

	public void setPlaying(boolean bool) {
		play.setEnabled(!bool);
		step.setEnabled(!bool);
		pause.setEnabled(bool);
	}

	public void enableGameStart(boolean bool) {
		play.setEnabled(bool);
		step.setEnabled(bool);
		stop.setEnabled(bool);
	}

	public void setRestart() {
		buttonsPanel.removeAll();
		restart = new JButton("restart");
		restart.addActionListener(new RestartListener());
		buttonsPanel.add(restart);
		buttonsPanel.repaint();
	}

	public void restart() {
		removeAll();
		addButtonsPanel();
		addGameGrid();
		revalidate();
	}

	public void updateLabel() {
		epoch.update();
	}

	private class JLabelCounter extends JLabel {
		private static final long serialVersionUID = -92322416682174109L;
		private Integer count;

		public JLabelCounter(String text) {
			super(text);
			count = 0;
		}

		public void update() {
			this.setText("Epoch " + count++);
		}
	}

}
