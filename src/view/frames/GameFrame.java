package view.frames;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import view.JGrid;
import view.ViewManager;

public class GameFrame extends JFrame {
    private static final long serialVersionUID = 2729314215802559201L;
    private GamePanel gamePanel;
    private Timer timer;
    int delay = 100;

    public GameFrame(String title) {
	super(title);
	this.setLayout(new BorderLayout());
	gamePanel = new GamePanel();
	this.add(gamePanel);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setResizable(false);
	timer = new Timer(delay, new StepListener());
    }

    public GamePanel getGamePanel() {
	return gamePanel;
    }

    public synchronized Timer getTimer() {
	return timer;
    }

    public void showEndDialog() {
	if (timer.isRunning()) {
	    timer.stop();
	}
	JOptionPane.showMessageDialog(this, "Computation ended!",
		"Information", JOptionPane.WARNING_MESSAGE);
	gamePanel.restart();
    }

    public class GamePanel extends JPanel {
	private static final long serialVersionUID = 1860979716621182121L;
	private JGrid grid;
	private JPanel buttonsPanel;
	private JPanel epochCounter;
	private JButton play;
	private JButton pause;
	private JButton step;
	private JButton stop;
	private JButton samples;
	private JLabelCounter epoch;

	public GamePanel() {
	    super();
	    setLayout(new BorderLayout());
	    addGameGrid();
	    addButtonsPanel();
	    addEpochCounter();
	}

	public JGrid getGrid() {
	    return grid;
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
	    samples = new JButton("sample");
	    samples.setEnabled(true);
	    samples.addActionListener(new SampleListener());
	    buttonsPanel = new JPanel();
	    buttonsPanel.setLayout(new FlowLayout());
	    buttonsPanel.add(play);
	    buttonsPanel.add(pause);
	    buttonsPanel.add(step);
	    buttonsPanel.add(stop);
	    buttonsPanel.add(samples);
	    add(buttonsPanel, BorderLayout.CENTER);
	}

	private void addEpochCounter() {
	    epoch = new JLabelCounter("Epoch 0");
	    epochCounter = new JPanel();
	    epochCounter.add(epoch);
	    epochCounter.setBorder(BorderFactory.createEmptyBorder(10, 10, 10,
		    10));
	    add(epochCounter, BorderLayout.PAGE_END);
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

	public void disableSamples() {
	    samples.setEnabled(false);
	}

	public void restart() {
	    if (timer.isRunning()) {
		timer.stop();
	    }
	    removeAll();
	    addButtonsPanel();
	    addGameGrid();
	    addEpochCounter();
	    revalidate();
	}

	public void updateLabel() {
	    epoch.update();
	}
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

    private class SampleListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
	    ViewManager.getManager().showSamples();

	}
    }

    private class PauseListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
	    gamePanel.setPlaying(false);
	    getTimer().stop();
	}

    }

    private class PlayListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
	    gamePanel.setPlaying(true);
	    getTimer().start();
	}
    }

    private class RestartListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
	    gamePanel.restart();
	}
    }

    public class StepListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg) {
	    JGrid grid = ViewManager.getManager().getGrid();

	    if (grid.isFirstRun()) {
		grid.removeGridListeners();
		grid.setInitialConfiguration();
		gamePanel.disableSamples();
	    }
	    grid.runEpoch();
	    gamePanel.updateLabel();
	}
    }

}
