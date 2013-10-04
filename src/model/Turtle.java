package model;

import java.util.Set;

public interface Turtle {

	public Turtle penDown();

	public Turtle penUp();

	public Turtle right();

	public Turtle left();

	public Turtle up();

	public Turtle down();
	
	public Set<Position> getDraft();
	
	public Turtle times(Integer times);
}
