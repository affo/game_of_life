package view;

import model.EmptyPositionException;
import model.Entity;
import model.Position;
import model.WorldInterface;
import model.WorldOutOfBoundsException;

public class Printer {
	private static final Integer BOUND = 13;

	public void printWorld(WorldInterface world) {

		for (int i = -BOUND; i < BOUND; i++) {
			for (int j = -BOUND; j < BOUND; j++) {
				Position p = new Position(i, j);
				Entity e;
				try {
					e = world.getEntity(p);
					
					if(e.isAlive()){
						System.out.print("O ");
					}else{
						System.out.print("- ");
					}
				} catch (WorldOutOfBoundsException e1) {
					System.out.println(e1.getMessage());
				} catch (EmptyPositionException e1) {
					System.out.print("- ");
				}
			}
			
			System.out.println();
		}

	}

}
