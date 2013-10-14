package model.shapes;

import java.util.Set;

import model.Position;
import model.Turtle;
import model.WorldTurtle;

public class Toad implements Shape {

	@SuppressWarnings("unchecked")
	@Override
	public Set<Position> makeShape(Position start) {
		Turtle turtle = new WorldTurtle(start);
		return (Set<Position>) turtle.penDown().right().times(2).penUp().down()
				.left().penDown().right().times(2).getDraft();
	}

}
