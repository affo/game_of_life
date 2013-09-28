package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GridListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg) {
		JEntity entityContainer = (JEntity) arg.getSource();
		GameGrid grid = ViewManager.getManager().getGrid();
		if(!entityContainer.isAlive()){
			entityContainer.rise();
			grid.addToInitialConfiguration(entityContainer);
		}else{
			entityContainer.die();
			grid.removeFromInitialConfiguration(entityContainer);
		}
	}

}
