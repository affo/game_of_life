package model.shapes;

import java.util.Set;

import model.Position;
import model.Turtle;
import model.WorldTurtle;

public class CannonGlider implements Shape {

	@SuppressWarnings("unchecked")
	@Override
	public Set<Position> makeShape(Position start) {
		Turtle turtle = new WorldTurtle(start);
		return (Set<Position>) turtle.penDown().right().down().left().penUp()
				.right().times(10).up().right().penDown().down().down().penUp()
				.right().down().penDown().penDown().right().down().penDown()
				.right().penUp().right().up().right().penDown().penUp().right()
				.up().penDown().up().right().left().up().penUp().up().left()
				.penDown().penUp().down().down().left().penDown().penUp().up()
				.times(3).left().penDown().left().penUp().left().down()
				.penDown().getDraft();
	}

}
