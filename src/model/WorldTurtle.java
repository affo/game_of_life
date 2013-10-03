package model;

import java.util.HashSet;
import java.util.Set;

public class WorldTurtle implements Turtle {
	private Set<Position> positions;
	private Position current;
	private Boolean drawing;

	public WorldTurtle(Position start) {
		super();
		current = start;
		drawing = false;
		positions = new HashSet<Position>();
	}

	@Override
	public Turtle penDown() {
		positions.add(current);
		drawing = true;
		return this;
	}

	@Override
	public Turtle penUp() {
		drawing = false;
		return this;
	}

	@Override
	public Turtle right() {
		current = current.right();
		if (drawing) {
			positions.add(current);
		}

		return this;
	}

	@Override
	public Turtle left() {
		current = current.left();
		if (drawing) {
			positions.add(current);
		}

		return this;
	}

	@Override
	public Turtle up() {
		current = current.up();
		if (drawing) {
			positions.add(current);
		}

		return this;
	}

	@Override
	public Turtle down() {
		current = current.down();
		if (drawing) {
			positions.add(current);
		}

		return this;
	}

	@Override
	public Set<Position> getDraft() {
		return positions;
	}

}
