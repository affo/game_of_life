package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GridListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg) {
		JEntity caller = (JEntity) arg.getSource();
		GameGrid grid = ViewManager.getManager().getGrid();
		if(!caller.isAlive()){
			caller.rise();
			grid.addAlive(caller);
		}else{
			caller.die();
			grid.removeAlive(caller);
		}
	}

}
