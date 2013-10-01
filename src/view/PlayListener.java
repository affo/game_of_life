package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {

		ViewManager viewManager = ViewManager.getManager();
		GameGrid grid = viewManager.getGrid();
		
		viewManager.disableButtons();

		if(grid.isFirstRun()){
			//grid.removeGridListeners();
			grid.setInitialConfiguration();
		}
		
		//TODO loop
		grid.runEpoch();

	}

}
