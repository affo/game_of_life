package view.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.ViewManager;


public class PlayListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {

	ViewManager viewManager = ViewManager.getManager();

	viewManager.setPlaying(true);
	viewManager.getTimer().start();

    }
}
