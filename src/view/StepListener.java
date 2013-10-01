package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StepListener implements ActionListener {
		
	@Override
	public void actionPerformed(ActionEvent arg) {

		GameGrid grid = ViewManager.getManager().getGrid();
		 
		if(grid.isFirstRun()){
			//grid.removeGridListeners();
			grid.setInitialConfiguration();
		}
		
		grid.runEpoch();

	}

}
