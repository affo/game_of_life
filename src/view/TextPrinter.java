package view;

import java.util.Collection;

import model.EmptyPositionException;
import model.Entity;
import model.Position;
import model.WorldInterface;
import model.WorldOutOfBoundsException;

public class TextPrinter {
    private static final Integer BOUND = 13;

    public void printWorld(WorldInterface world) {

	System.out.print("\t");
	for (int j = -BOUND; j < BOUND; j++) {
	    System.out.print(j + " ");
	}
	System.out.println();

	for (int i = -BOUND; i < BOUND; i++) {
	    System.out.print(i + "\t");
	    for (int j = -BOUND; j < BOUND; j++) {
		Position p = new Position(i, j);
		Entity e;
		try {
		    e = world.getEntity(p);

		    String out = "";

		    if (j < 0) {
			out = " ";
		    }

		    if (e.isAlive() && Math.abs(j) > 9) {
			out += "O  ";
		    } else if (!e.isAlive() && Math.abs(j) > 9) {
			out += "-  ";
		    } else if (e.isAlive() && Math.abs(j) <= 9) {
			out += "O ";
		    } else {
			out += "- ";
		    }

		    System.out.print(out);
		} catch (WorldOutOfBoundsException e1) {
		    System.out.println(e1.getMessage());
		} catch (EmptyPositionException e1) {
		    String out = "";

		    if (j < 0) {
			out = " ";
		    }

		    if (Math.abs(j) > 9) {
			out += "-  ";

		    } else {
			out += "- ";
		    }
		    System.out.print(out);
		}
	    }

	    System.out.println();
	}

    }

    public void printCollection(String msg, Collection<?> coll) {
	System.out.print(msg + " ");

	for (Object o : coll) {
	    System.out.print(o.toString() + " ");
	}

	System.out.println();
    }

}
