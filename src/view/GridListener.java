package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Position;

public class GridListener implements ActionListener {

	Position pos;
	JEntity caller;
	
	public GridListener(Position pos, JEntity caller) {
		this.pos = pos;
		this.caller = caller;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(!caller.isAlive())
			ViewManager.getManager().getGrid().rise(pos, caller);
		else
			ViewManager.getManager().getGrid().kill(pos, caller);
	}

}
