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
		int rows = JGrid.ROWS;
		int columns = JGrid.COLUMNS;

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				boolean b = nextBool();
				if (b) {
					turtle.penDown().penUp().right();
				} else {
					turtle.right();
				}
			}

			turtle.down().left().times(columns);
		}

		return (Set<Position>) turtle.getDraft();
	}

	private boolean nextBool() {
		return Math.random() < 0.2;
	}

}
