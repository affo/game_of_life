package view.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.ViewManager;


public class PauseListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {

	ViewManager viewManager = ViewManager.getManager();

	viewManager.setPlaying(false);
	viewManager.getTimer().stop();

    }

}
