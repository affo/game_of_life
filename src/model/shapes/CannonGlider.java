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
	return (Set<Position>) turtle
		.penDown()
		.right()
		.down()
		.left()
		.penUp()
		// square
		.right()
		.times(9)
		// starting with first shape
		.up().right().penDown().down().down().penUp().right().down()
		.penDown().penDown().penUp().right().down().penDown().right()
		.penUp().right().up().right().penDown().penUp().right().up()
		.penDown().up().right().left().up().penUp().up().left()
		.penDown().penUp().down().down().left().penDown().penUp().up()
		.times(3).left().penDown()
		.left()
		.penUp()
		.left()
		.down()
		.penDown()
		.penUp()
		// first shape finished
		// moving to next shape
		.up().times(3)
		.right()
		.times(13)
		// second shape
		.penDown().down().penUp().left().left().penDown().penUp()
		.left().down().penDown().down().times(2).left().up().times(2)
		.penUp().down().times(3).right().times(2).penDown().penUp()
		.right().right().penDown().down().penUp()
		// second shape completed, moving to the last square
		.right().times(10).up().times(4)
		// last square
		.penDown().down().right().up()
		// getting the draft
		.getDraft();
    }

}
