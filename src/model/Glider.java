package model;

import java.util.Set;

public class Glider implements Shape{
	private Turtle turtle;

	@Override
	public Set<Position> makeShape(Position start) {
		turtle = new WorldTurtle(start);
		return turtle.penDown().right().times(2).penUp()
				.down().left().times(2).penDown().penUp().down().right()
				.penDown().getDraft();
	}

}