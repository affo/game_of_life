package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {

	ViewManager viewManager = ViewManager.getManager();

	viewManager.setPlaying(true);
	viewManager.getTimer().start();

    }
}
