package controller;

import java.util.Set;
import java.util.concurrent.Semaphore;

import model.Entity;
import model.WorldInterface;

public class CheckListFiller implements Runnable {
	private Set<Entity> checkList;
	private Semaphore exit;
	private Entity entity;
	private WorldInterface world;

	public CheckListFiller(Set<Entity> checkList, Semaphore exit,
			Entity entity, WorldInterface world) {
		super();
		this.checkList = checkList;
		this.exit = exit;
		this.entity = entity;
		this.world = world;
	}

	@Override
	public void run() {

		synchronized (this) {
			Set<Entity> adjacents = world.getAdjacents(entity);
			checkList.addAll(adjacents);
			checkList.add(entity);
		}

		exit.release();
	}
}
