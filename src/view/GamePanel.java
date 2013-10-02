package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import view.listeners.PauseListener;
import view.listeners.PlayListener;
import view.listeners.RestartListener;
import view.listeners.StepListener;

public class GamePanel extends JPanel {

    private static final long serialVersionUID = 1860979716621182121L;
    private JGrid grid;
    private JPanel buttonsPanel;
    private JPanel epochCounter;
    private JButton play;
    private JButton pause;
    private JButton step;
    private JButton stop;
    private JLabelCounter epoch;

    public GamePanel() {
	super();
	setLayout(new BorderLayout());
	addGameGrid();
	addButtonsPanel();
	addEpochCounter();
    }

    private void addGameGrid() {
	grid = new JGrid();
	add(grid, BorderLayout.PAGE_START);
    }

    private void addButtonsPanel() {
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
	buttonsPanel = new JPanel();
	buttonsPanel.setLayout(new FlowLayout());
	buttonsPanel.add(play);
	buttonsPanel.add(pause);
	buttonsPanel.add(step);
	buttonsPanel.add(stop);
	add(buttonsPanel, BorderLayout.CENTER);
    }

    private void addEpochCounter() {
	epoch = new JLabelCounter("Epoch 0");
	epochCounter = new JPanel();
	epochCounter.add(epoch);
	epochCounter.setBorder(BorderFactory.createCompoundBorder(null,
		BorderFactory.createEmptyBorder(10, 10, 10, 10)));
	add(epochCounter, BorderLayout.PAGE_END);
    }

    public JGrid getGrid() {
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

    public void restart() {
	removeAll();
	addButtonsPanel();
	addGameGrid();
	addEpochCounter();
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
