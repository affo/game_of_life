package view.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.ViewManager;

public class RestartListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
	ViewManager.getManager().restartGame();
    }

}
