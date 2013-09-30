package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EpochListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg) {
		
		GameGrid grid = ViewManager.getManager().getGrid();
		//TODO rimuove action listener da tutti i bottoni
		grid.setInitialConfiguration();
		
		//while(true){
			grid.runEpoch();
		//}

	}

}
