package view.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.JEntity;
import view.JGrid;
import view.ViewManager;

public class GridListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent arg) {
	JEntity entityContainer = (JEntity) arg.getSource();
	JGrid grid = ViewManager.getManager().getGrid();
	if (!entityContainer.isAlive()) {
	    grid.rise(entityContainer);
	} else {
	    grid.kill(entityContainer);
	}
    }

}
