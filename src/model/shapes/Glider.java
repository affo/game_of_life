package model.shapes;

import java.util.Set;

import model.Position;
import model.Turtle;
import model.WorldTurtle;

public class Glider implements Shape {
    private Turtle turtle;

    @SuppressWarnings("unchecked")
    @Override
    public Set<Position> makeShape(Position start) {
	turtle = new WorldTurtle(start);
	return (Set<Position>) turtle.penDown().right().times(2).penUp().down()
		.left().times(2).penDown().penUp().down().right().penDown()
		.getDraft();
    }
}