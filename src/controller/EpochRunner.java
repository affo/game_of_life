package controller;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Semaphore;

import model.Entity;
import model.Position;
import model.World;
import model.WorldInterface;
import model.WorldOutOfBoundsException;

public class EpochRunner implements EpochRunnerInterface {
	private WorldInterface world;

	public EpochRunner(Set<Position> positions) {
		super();
		world = new World();

		for (Position p : positions) {
			try {
				world.putEntity(p, new Entity(true, p));
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

	public WorldInterface getWorld() {
		return world;
	}

	private WorldInterface generatePermutation() {
		World newWorld = new World();
		Set<Entity> checkList = new HashSet<Entity>();
		Set<Entity> alive = world.getAliveEntities();
		Semaphore stepper = new Semaphore(0);

		for (Entity e : alive) {
			CheckListFiller filler = new CheckListFiller(checkList, stepper, e,
					world);
			Thread t = new Thread(filler);
			t.run();
		}

		int n = alive.size();
		try {
			stepper.acquire(n);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		/*
		// TODO delete
		Printer printer = new Printer();
		printer.printCollection("CHECKLIST: ", checkList);
		*/

		for (Entity e : checkList) {
			LawsApplier applier = new LawsApplier(stepper, e, world, newWorld);
			Thread t = new Thread(applier);
			t.run();
		}

		n = checkList.size();
		try {
			stepper.acquire(n);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		return newWorld;
	};
}