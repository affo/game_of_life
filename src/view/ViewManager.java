package view;

import java.awt.BorderLayout;

public class ViewManager {
	
	private static ViewManager viewManager;
	private WelcomeFrame welcomeFrame;
	private WelcomePanel welcomePanel;
	private GameFrame gameFrame;
	private GamePanel gamePanel;
	
	public ViewManager() {
		welcomeFrame = new WelcomeFrame("Welcome to Game of Life", 250, 550);
		welcomePanel = new WelcomePanel();
		gameFrame = new GameFrame("Game of Life", 530, 910);
		gamePanel = new GamePanel();
		
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

	public void welcome() {
		welcomeFrame.setLayout(new BorderLayout());
		welcomeFrame.add(welcomePanel);
		welcomeFrame.pack();
		welcomeFrame.setLocationRelativeTo(null);
		welcomeFrame.setVisible(true);	
	}

	public void game() {
		gameFrame.setLayout(new BorderLayout());
		gameFrame.add(gamePanel);
		gameFrame.pack();
		gameFrame.setLocationRelativeTo(null);
		gameFrame.setVisible(true);	
	}
	
	public void disposeWelcome(){
		welcomeFrame.dispose();
	}
	
	public void disposeGame(){
		gameFrame.dispose();
	}

}
