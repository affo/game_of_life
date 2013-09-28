package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import model.EmptyPositionException;
import model.Entity;
import model.Position;
import model.World;
import model.WorldInterface;
import model.WorldOutOfBoundsException;

public class EpochRunner implements EpochRunnerInterface {
	private WorldInterface world;

	public EpochRunner(List<Position> positions) {
		super();
		world = new World();
		
		for(Position p :  positions){
			try {
				world.putEntity(p, new Entity(false, p));
			} catch (WorldOutOfBoundsException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	@Override
	public WorldInterface runEpoch() {
		world = generatePermutation();
		return world;
	}

	private WorldInterface generatePermutation() {
		World newWorld = new World();
		Set<Entity> entities = generateCheckList();

		for (Entity e : entities) {

			Entity temp;

			if (!e.isAlive() && toRise(e)) {
				temp = e.clone().rise();
			} else if (e.isAlive() && toLeave(e)) {
				temp = e.clone().survive();
			} else {
				temp = e.clone().die();
			}

			try {
				newWorld.putEntity(e.getPosition(), temp);
			} catch (WorldOutOfBoundsException e1) {
				System.out.println(e1.getMessage());
			}
		}

		return newWorld;
	};

	private Set<Entity> generateCheckList() {
		Set<Entity> newCheck = world.getAvailableEntities();
		Set<Entity> available = world.getAvailableEntities();

		for (Entity e : available) {
			newCheck.add(e);
			List<Entity> adjacents = getAdjacents(e);

			for (Entity a : adjacents) {
				newCheck.add(a);
			}
		}

		return newCheck;
	}

	private boolean toRise(Entity e) {
		List<Entity> adjacents = getAdjacents(e);
		Integer aliveCount = 0;

		for (Entity element : adjacents) {
			if (element != null && element.isAlive()) {
				aliveCount++;
			}
		}

		if (aliveCount == 3) {
			return true;
		}

		return false;
	};

	private boolean toLeave(Entity e) {
		List<Entity> adjacents = getAdjacents(e);
		Integer aliveCount = 0;

		for (Entity element : adjacents) {
			if (element != null && element.isAlive()) {
				aliveCount++;
			}
		}

		if (aliveCount == 3 || aliveCount == 2) {
			return true;
		}

		return false;
	}

	private List<Entity> getAdjacents(Entity e) {
		Position p = e.getPosition();
		List<Entity> adjacents = new ArrayList<Entity>();
		Position tempPos = null;

		try {
			try {
				tempPos = new Position(p.getRow() - 1, p.getColumn() - 1);
				adjacents.add(world.getEntity(tempPos));
			} catch (EmptyPositionException e1) {
				adjacents.add(new Entity(false, tempPos));
			}
			try {
				tempPos = new Position(p.getRow() - 1, p.getColumn());
				adjacents.add(world.getEntity(tempPos));
			} catch (EmptyPositionException e1) {
				adjacents.add(new Entity(false, tempPos));
			}
			try {
				tempPos = new Position(p.getRow() - 1, p.getColumn() + 1);
				adjacents.add(world.getEntity(tempPos));
			} catch (EmptyPositionException e1) {
				adjacents.add(new Entity(false, tempPos));
			}
			try {
				tempPos = new Position(p.getRow(), p.getColumn() + 1);
				adjacents.add(world.getEntity(tempPos));
			} catch (EmptyPositionException e1) {
				adjacents.add(new Entity(false, tempPos));
			}
			try {
				tempPos = new Position(p.getRow() + 1, p.getColumn() + 1);
				adjacents.add(world.getEntity(tempPos));
			} catch (EmptyPositionException e1) {
				adjacents.add(new Entity(false, tempPos));
			}
			try {
				tempPos = new Position(p.getRow() + 1, p.getColumn());
				adjacents.add(world.getEntity(tempPos));
			} catch (EmptyPositionException e1) {
				adjacents.add(new Entity(false, tempPos));
			}
			try {
				tempPos = new Position(p.getRow() + 1, p.getColumn() - 1);
				adjacents.add(world.getEntity(tempPos));
			} catch (EmptyPositionException e1) {
				adjacents.add(new Entity(false, tempPos));
			}
			try {
				tempPos = new Position(p.getRow(), p.getColumn() - 1);
				adjacents.add(world.getEntity(tempPos));
			} catch (EmptyPositionException e1) {
				adjacents.add(new Entity(false, tempPos));
			}
		} catch (WorldOutOfBoundsException e1) {
			System.out.println(e1.getMessage());
		}

		return adjacents;
	}

}
