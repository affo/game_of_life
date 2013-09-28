package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controller.EpochRunner;

public class EpochListener implements ActionListener {


	@Override
	public void actionPerformed(ActionEvent arg) {
		GameGrid grid = ViewManager.getManager().getGrid();
		EpochRunner runner = new EpochRunner(grid.getAlives());

		//TODO rimuove action listener da tutti i bottoni
		
		//while(true){
			grid.update(runner.runEpoch());
		//}

	}

}
