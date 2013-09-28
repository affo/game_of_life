package view;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import resources.Resources;

public class ViewManager {
	
	private static ViewManager viewManager;
	private WelcomeFrame welcomeFrame;
	private GameFrame gameFrame;
	
	public ViewManager() {
		welcomeFrame = new WelcomeFrame("Welcome to Game of Life", 150, 550);
		gameFrame = new GameFrame("Game of Life", 480, 910);
	}
	
	public static synchronized ViewManager getManager() {
		if (viewManager == null) {
			viewManager = new ViewManager();
		}
		return viewManager;
	}

	public void welcome() {
		welcomeFrame.setLayout(new BorderLayout());
		JLabel logo = new JLabel(new ImageIcon(Resources.class.getResource("img/logo.png")));
		welcomeFrame.add(logo, BorderLayout.NORTH);
		WelcomePanel welcomePanel = new WelcomePanel();
		welcomeFrame.add(welcomePanel, BorderLayout.SOUTH);
		welcomeFrame.pack();
		welcomeFrame.setLocationRelativeTo(null);
		welcomeFrame.setVisible(true);	
	}

	public void initGame() {
		GamePanel gamePanel = new GamePanel();
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
