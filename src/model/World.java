package model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class World implements WorldInterface {

	private static final int BOUND = 100;
	private Map<Position, Entity> world;

	public World() {
		super();
		world = new HashMap<Position, Entity>();
	}

	public void putEntity(Position p, Entity e)
			throws WorldOutOfBoundsException {
		if (inBound(p)) {
			world.put(p, e);
		} else {
			throw new WorldOutOfBoundsException();
		}
	}

	public Entity getEntity(Position p) throws WorldOutOfBoundsException,
			EmptyPositionException {
		if (inBound(p)) {
			Entity e = world.get(p);
			if (e != null) {
				return e;
			}

			throw new EmptyPositionException();
		}

		throw new WorldOutOfBoundsException();
	}

	private Boolean inBound(Position p) {
		if (Math.abs(p.getColumn()) > BOUND || Math.abs(p.getRow()) > BOUND) {
			return false;
		}

		return true;
	}

	public Set<Entity> getAliveEntities() {
		Set<Position> positions = world.keySet();
		Set<Entity> entities = new HashSet<Entity>();

		for (Position p : positions) {
			try {
				try {
					Entity e = getEntity(p);

					if (e.isAlive()) {
						entities.add(e);
					}
				} catch (EmptyPositionException e) {
					System.out.println(e.getMessage());
				}
			} catch (WorldOutOfBoundsException e) {
				System.out.println(e.getMessage());
			}
		}

		return entities;
	}

	public Set<Entity> getAdjacents(Entity e) {
		Position p = e.getPosition();
		Set<Entity> adjacents = new HashSet<Entity>();
		Position tempPos = null;

		try {
			try {
				tempPos = new Position(p.getRow() - 1, p.getColumn() - 1);
				adjacents.add(getEntity(tempPos));
			} catch (EmptyPositionException e1) {
				adjacents.add(new Entity(false, tempPos));
			}
			try {
				tempPos = new Position(p.getRow() - 1, p.getColumn());
				adjacents.add(getEntity(tempPos));
			} catch (EmptyPositionException e1) {
				adjacents.add(new Entity(false, tempPos));
			}
			try {
				tempPos = new Position(p.getRow() - 1, p.getColumn() + 1);
				adjacents.add(getEntity(tempPos));
			} catch (EmptyPositionException e1) {
				adjacents.add(new Entity(false, tempPos));
			}
			try {
				tempPos = new Position(p.getRow(), p.getColumn() + 1);
				adjacents.add(getEntity(tempPos));
			} catch (EmptyPositionException e1) {
				adjacents.add(new Entity(false, tempPos));
			}
			try {
				tempPos = new Position(p.getRow() + 1, p.getColumn() + 1);
				adjacents.add(getEntity(tempPos));
			} catch (EmptyPositionException e1) {
				adjacents.add(new Entity(false, tempPos));
			}
			try {
				tempPos = new Position(p.getRow() + 1, p.getColumn());
				adjacents.add(getEntity(tempPos));
			} catch (EmptyPositionException e1) {
				adjacents.add(new Entity(false, tempPos));
			}
			try {
				tempPos = new Position(p.getRow() + 1, p.getColumn() - 1);
				adjacents.add(getEntity(tempPos));
			} catch (EmptyPositionException e1) {
				adjacents.add(new Entity(false, tempPos));
			}
			try {
				tempPos = new Position(p.getRow(), p.getColumn() - 1);
				adjacents.add(getEntity(tempPos));
			} catch (EmptyPositionException e1) {
				adjacents.add(new Entity(false, tempPos));
			}
		} catch (WorldOutOfBoundsException e1) {
			System.out.println(e1.getMessage());
		}

		return adjacents;
	}
}
