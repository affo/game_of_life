package model;

import java.util.List;

public interface WorldInterface {
    
	public List<Position> getAvailablePositions();
	public Entity getEntity(Position p);
}