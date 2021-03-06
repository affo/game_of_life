package model;

import java.util.Set;

public interface WorldInterface {

	public Set<Entity> getAliveEntities();

	public Entity getEntity(Position p) throws WorldOutOfBoundsException,
			EmptyPositionException;

	public void putEntity(Position p, Entity e)
			throws WorldOutOfBoundsException;
	
	public Set<Entity> getAdjacents(Entity e);
}