package model.shapes;

import java.util.Set;

import model.Position;
import model.Turtle;
import model.WorldTurtle;
import view.JGrid;

public class Random implements Shape {

    @SuppressWarnings("unchecked")
    @Override
    public Set<Position> makeShape(Position start) {
	Turtle turtle = new WorldTurtle(new Position(0, 0));
	java.util.Random r = new java.util.Random();
	int rows = JGrid.ROWS;
	int columns = JGrid.COLUMNS;

	for (int i = 0; i < rows; i++) {
	    for (int j = 0; j < columns; j++) {
		boolean b = r.nextBoolean();
		if (b) {
		    turtle.penDown().penUp().right();
		} else {
		    turtle.right();
		}
	    }

	    turtle.down().left().times(columns - 1);
	}

	return (Set<Position>) turtle.getDraft();
    }

}
