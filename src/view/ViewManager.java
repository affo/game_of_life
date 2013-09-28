package view;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import resources.Resources;

public class ViewManager {
	
	private static ViewManager viewManager;
	private WelcomeFrame welcomeFrame;
	private GameFrame gameFrame;
	private GamePanel gamePanel;
	
	public ViewManager() {
		welcomeFrame = new WelcomeFrame("Welcome to Game of Life", 170, 550);
		gameFrame = new GameFrame("Game of Life", 520, 910);
	}
	
	public static synchronized ViewManager getManager() {
		if (viewManager == null) {
			viewManager = new ViewManager();
		}
		return viewManager;
	}

	public GamePanel getGrid() {
		return gamePanel;
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
		JButton play = new JButton("play");
		JButton pause = new JButton("stop");
		gameFrame.setLayout(new BorderLayout());
		gamePanel = new GamePanel();
		gameFrame.add(gamePanel, BorderLayout.PAGE_START);
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(play);
		buttonPanel.add(pause);
		gameFrame.add(buttonPanel, BorderLayout.PAGE_END);
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
