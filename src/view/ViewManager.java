package view;

import view.frames.GameFrame;
import view.frames.GameFrame.GamePanel;
import view.frames.SampleFrame;
import view.frames.WelcomeFrame;

public class ViewManager {

    private static ViewManager viewManager;
    private WelcomeFrame welcomeFrame;
    private GameFrame gameFrame;
    private SampleFrame sampleFrame;

    public static synchronized ViewManager getManager() {
	if (viewManager == null) {
	    viewManager = new ViewManager();
	}
	return viewManager;
    }

    public JGrid getGrid() {
	return getGamePanel().getGrid();
    }

    public GamePanel getGamePanel() {
	return gameFrame.getGamePanel();
    }

    public GameFrame getGameFrame() {
	return gameFrame;
    }

    public void welcome() {
	welcomeFrame = new WelcomeFrame("Welcome to Game of Life");
	welcomeFrame.pack();
	welcomeFrame.setLocationRelativeTo(null);
	welcomeFrame.setVisible(true);
    }

    public void game() {
	gameFrame = new GameFrame("Game of Life");
	gameFrame.pack();
	gameFrame.setLocationRelativeTo(null);
	gameFrame.setVisible(true);
    }

    public void showSamples() {
	sampleFrame = new SampleFrame("Samples");
	sampleFrame.pack();
	sampleFrame.setLocationRelativeTo(null);
	sampleFrame.setVisible(true);
    }

    public void disposeWelcome() {
	welcomeFrame.dispose();
    }

    public void disposeSample() {
	sampleFrame.dispose();
    }
}
