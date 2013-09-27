package controller;

import java.util.ArrayList;
import java.util.List;

import model.Entity;
import model.Position;
import model.World;
import model.WorldInterface;

public class EpochRunner implements EpochRunnerInterface {
	private WorldInterface world;

	@Override
	public WorldInterface runEpoch() {
		world = generatePermutation();
		return world;
	}

	private WorldInterface generatePermutation() {
		World newWorld = new World();
		List<Position> positions = world.getAvailablePositions();
		
		for(Position p : positions){
			Entity e = world.getEntity(p);
			Entity temp;
			
			if(!e.isAlive() && toRise(e)){
				temp = e.clone().rise();
			}else if(e.isAlive() && toLeave(e)){
				temp = e.clone().survive();
			}else{
				temp = e.clone().die();
			}
			
			newWorld.putEntity(p, temp);
		}

		return newWorld;
	};

	private boolean toRise(Entity e) {
		List<Entity> adjacents = getAdjacents(e);
		Integer aliveCount = 0;
		
		for(Entity element : adjacents){
			if(element!= null && element.isAlive()){
				aliveCount++;
			}
		}
		
		if(aliveCount == 3){
			return true;
		}
		
		return false;
	};
	
	private boolean toLeave(Entity e){
		List<Entity> adjacents = getAdjacents(e);
		Integer aliveCount = 0;
		
		for(Entity element : adjacents){
			if(element!= null && element.isAlive()){
				aliveCount++;
			}
		}
		
		if(aliveCount == 3 || aliveCount == 2){
			return true;
		}
		
		return false;
	}

	private List<Entity> getAdjacents(Entity e) {
		Position p = e.getPosition();
		List<Entity> adjacents = new ArrayList<Entity>();

		adjacents.add(world.getEntity(new Position(p.getRow() - 1, p.getColumn() - 1)));
		adjacents.add(world.getEntity(new Position(p.getRow() - 1, p.getColumn())));
		adjacents.add(world.getEntity(new Position(p.getRow() - 1, p.getColumn() + 1)));
		adjacents.add(world.getEntity(new Position(p.getRow(), p.getColumn() + 1)));
		adjacents.add(world.getEntity(new Position(p.getRow() + 1, p.getColumn() + 1)));
		adjacents.add(world.getEntity(new Position(p.getRow() + 1, p.getColumn())));
		adjacents.add(world.getEntity(new Position(p.getRow() + 1, p.getColumn() - 1)));
		adjacents.add(world.getEntity(new Position(p.getRow(), p.getColumn() - 1)));

		return adjacents;
	}

}
