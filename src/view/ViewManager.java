package view;

import java.awt.BorderLayout;

import javax.swing.JOptionPane;
import javax.swing.Timer;

public class ViewManager {

	private static ViewManager viewManager;
	private WelcomeFrame welcomeFrame;
	private WelcomePanel welcomePanel;
	private GameFrame gameFrame;
	private GamePanel gamePanel;
	private Timer timer;
	int delay = 100;

	public ViewManager() {
		welcomeFrame = new WelcomeFrame("Welcome to Game of Life", 250, 550);
		gameFrame = new GameFrame("Game of Life", 540, 910);
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

	public GameGrid getGrid() {
		return gamePanel.getGrid();
	}
	
	public GamePanel getPanel(){
		return gamePanel;
	}

	public void welcome() {
		welcomeFrame.setLayout(new BorderLayout());
		welcomePanel = new WelcomePanel();
		welcomeFrame.add(welcomePanel);
		welcomeFrame.pack();
		welcomeFrame.setLocationRelativeTo(null);
		welcomeFrame.setVisible(true);
	}

	public void game() {
		gameFrame.setLayout(new BorderLayout());
		gamePanel = new GamePanel();
		gameFrame.add(gamePanel);
		gameFrame.pack();
		gameFrame.setLocationRelativeTo(null);
		gameFrame.setVisible(true);
	}

	public void disposeWelcome() {
		welcomeFrame.dispose();
	}

	public void disposeGame() {
		gameFrame.dispose();
	}

	public void setPlaying(boolean bool) {
		gamePanel.setPlaying(bool);
	}

	public void enableGameStart(boolean bool) {
		gamePanel.enableGameStart(bool);
	}

	public void showDialog(String message) {
		gamePanel.setRestart();
		JOptionPane.showMessageDialog(gameFrame, message, "Information",
				JOptionPane.WARNING_MESSAGE);
		if (timer.isRunning()) {
			timer.stop();
		}
	}

	public void restartGame() {
		gamePanel.restart();
	}

}
