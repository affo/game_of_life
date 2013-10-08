package controller;

import java.util.Set;
import java.util.concurrent.Semaphore;

import model.Entity;
import model.WorldInterface;
import model.WorldOutOfBoundsException;

public class LawsApplier implements Runnable {
	private Semaphore exit;
	private Entity entity;
	private WorldInterface world, newWorld;

	public LawsApplier(Semaphore exit, Entity entity, WorldInterface world,
			WorldInterface newWorld) {
		super();
		this.exit = exit;
		this.entity = entity;
		this.world = world;
		this.newWorld = newWorld;
	}

	@Override
	public void run() {
		Entity temp;

		if (!entity.isAlive() && toRise(entity)) {
			temp = entity.clone().rise();
		} else if (entity.isAlive() && toLeave(entity)) {
			temp = entity.clone().survive();
		} else {
			temp = entity.clone().die();
		}

		try {
			newWorld.putEntity(entity.getPosition(), temp);
		} catch (WorldOutOfBoundsException e1) {
			System.out.println(e1.getMessage());
		}

		exit.release();

	}

	private boolean toRise(Entity e) {
		Integer aliveCount = 0;

		Set<Entity> adjacents = world.getAdjacents(e);

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
		Integer aliveCount = 0;

		Set<Entity> adjacents = world.getAdjacents(e);

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

}
