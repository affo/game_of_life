package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;

import view.listeners.StepListener;

public class ViewManager {

    private static ViewManager viewManager;
    private JFrame welcomeFrame;
    private JFrame gameFrame;
    private JFrame sampleFrame;
    private WelcomePanel welcomePanel;
    private GamePanel gamePanel;
    private Timer timer;
    int delay = 100;

    public ViewManager() {
	timer = new Timer(delay, new StepListener());
    }

    public synchronized Timer getTimer() {
	return timer;
    }

    public static synchronized ViewManager getManager() {
	if (viewManager == null) {
	    viewManager = new ViewManager();
	}
	return viewManager;
    }

    public JGrid getGrid() {
	return gamePanel.getGrid();
    }

    public void welcome() {
	welcomeFrame = new JFrame("Welcome to Game of Life");
	welcomeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	welcomeFrame.setPreferredSize(new Dimension(550, 250));
	welcomeFrame.setResizable(false);
	welcomeFrame.setLayout(new BorderLayout());
	welcomePanel = new WelcomePanel();
	welcomeFrame.add(welcomePanel);
	welcomeFrame.pack();
	welcomeFrame.setLocationRelativeTo(null);
	welcomeFrame.setVisible(true);
    }

    public void game() {
	gameFrame = new JFrame("Game of Life");
	gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	gameFrame.setPreferredSize(new Dimension(910, 570));
	gameFrame.setResizable(false);
	gameFrame.setLayout(new BorderLayout());
	gamePanel = new GamePanel();
	gameFrame.add(gamePanel);
	gameFrame.pack();
	gameFrame.setLocationRelativeTo(null);
	gameFrame.setVisible(true);
    }

    public void showSamples() {
	sampleFrame = new SampleFrame("Samples", gameFrame.getHeight(), 150);
	sampleFrame.pack();
	sampleFrame.setLocationRelativeTo(null);
	Point location = new Point(gameFrame.getWidth()
		+ sampleFrame.getWidth() + 36, gameFrame.getHeight() - 475);
	sampleFrame.setLocation(location);
	sampleFrame.setVisible(true);
    }

    public void disposeWelcome() {
	welcomeFrame.dispose();
    }

    public void setPlaying(boolean bool) {
	gamePanel.setPlaying(bool);
    }

    public void enableGameStart(boolean bool) {
	gamePanel.enableGameStart(bool);
    }

    public void updateLabel() {
	gamePanel.updateLabel();
    }

    public void showEndDialog() {
	if (timer.isRunning()) {
	    timer.stop();
	}
	JOptionPane.showMessageDialog(gameFrame, "Computation ended!",
		"Information", JOptionPane.WARNING_MESSAGE);
	restartGame();
    }

    public void restartGame() {
	if (timer.isRunning()) {
	    timer.stop();
	}
	gamePanel.restart();
    }
}
