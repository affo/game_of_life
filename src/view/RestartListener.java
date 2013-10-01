package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RestartListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
	ViewManager.getManager().restartGame();
    }

}
