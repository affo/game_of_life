package model.shapes;

import java.util.Set;

import model.Position;

public interface Shape {
	
	public Set<Position> makeShape(Position start);

}
