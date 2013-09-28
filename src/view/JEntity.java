package view;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import resources.Resources;

import model.Entity;
import model.Position;

public class JEntity extends JButton {

	private static final long serialVersionUID = 6094768595944547213L;
	protected static final ImageIcon DEAD = new ImageIcon(
			Resources.class.getResource("img/dead.png"));;
	protected static final ImageIcon ALIVE = new ImageIcon(
			Resources.class.getResource("img/alive.png"));;
	private Entity entity;

	public JEntity(ImageIcon i, Position pos) {
		super(i);
		entity = new Entity();
		entity.setPosition(pos);
	}

	public Position getPosition() {
		return entity.getPosition();
	}

	public void setPosition(Position position) {
		entity.setPosition(position);
	}

	public Entity getEntity() {
		return entity;
	}

	public void setEntity(Entity entity) {
		this.entity = entity;
	}

	public boolean isAlive() {
		return entity.isAlive();
	}

	public void rise() {
		entity = entity.rise();
		setIcon(ALIVE);
	}

	public void die() {
		entity = entity.die();
		setIcon(DEAD);
	}

}
