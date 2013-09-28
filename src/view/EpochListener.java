package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.WorldInterface;
import controller.EpochRunner;

public class EpochListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		EpochRunner runner = new EpochRunner(ViewManager.getManager().getGrid().getAlives());
		
		WorldInterface world;
		while(true){
			 world = runner.runEpoch();
			 ViewManager.getManager().getGrid().update(world);
		}

	}

}
