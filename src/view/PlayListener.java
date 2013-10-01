package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class PlayListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {

		// ViewManager viewManager = ViewManager.getManager();
		// GameGrid grid = viewManager.getGrid();
		//
		// viewManager.setPlaying(true);
		//
		// if (grid.isFirstRun()) {
		// grid.removeGridListeners();
		// grid.setInitialConfiguration();
		// }
		//
		// for(int i = 0; i<1000; i++){
		// try {
		// Thread.sleep(500);
		// grid.runEpoch();
		// } catch (InterruptedException e1) {
		// // TODO Auto-generated catch block
		// e1.printStackTrace();
		// }
		// }

		StepListener stepper = new StepListener();
		int delay = 300;

		new Timer(delay, stepper).start();
	}

}
