package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class World implements WorldInterface {

	private Map<Position, Entity> world;

	public World() {
		super();
		world = new HashMap<Position, Entity>();
	}

	public void putEntity(Position p, Entity e) {
		world.put(p, e);
	}

	public Entity getEntity(Position p) {
		return world.get(p);
	}

	public List<Position> getAvailablePositions() {
		List<Position> positions = new ArrayList<Position>();

		for (Position p : world.keySet()) {
			positions.add(p);
		}

		return positions;
	}
}
