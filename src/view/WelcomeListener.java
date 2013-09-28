package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomeListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		ViewManager viewManager = ViewManager.getManager();
		viewManager.game();
		viewManager.disposeWelcome();
	}

}
