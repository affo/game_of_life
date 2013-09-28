package view;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import model.Position;
import resources.Resources;

public class JEntity extends JButton {

	private static final long serialVersionUID = 6094768595944547213L;
	protected static final ImageIcon DEAD = new ImageIcon(
			Resources.class.getResource("img/dead.png"));;
	protected static final ImageIcon ALIVE = new ImageIcon(
			Resources.class.getResource("img/alive.png"));;
	private Position position;
	private boolean alive;
	

	public JEntity(ImageIcon i, Position pos) {
		super(i);
		alive = false;
		position = pos;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public boolean isAlive() {
		return alive;
	}

	public void rise() {
		alive = true;
		setIcon(ALIVE);
	}

	public void die() {
		alive = false;
		setIcon(DEAD);
	}

}
