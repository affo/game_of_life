package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PauseListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		
		ViewManager viewManager = ViewManager.getManager();
		GameGrid grid = viewManager.getGrid();
		
		viewManager.enableButtons();
		
	}

}
