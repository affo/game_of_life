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
    private JButton stop;
    private JButton restart;
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

}
