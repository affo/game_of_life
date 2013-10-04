package model;

import java.util.Set;

public class Glider implements Shape{
	private Turtle turtle;

	public Glider(Position start) {
		super();
		turtle = new WorldTurtle(start);
	}

	@Override
	public Set<Position> getShape() {
		return turtle.penDown().right().times(2).penUp()
				.down().left().times(2).penDown().penUp().down().right()
				.penDown().getDraft();
	}

}