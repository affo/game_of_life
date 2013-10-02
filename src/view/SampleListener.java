package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SampleListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		ViewManager.getManager().sample();
	}

}
