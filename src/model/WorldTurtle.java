package model;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

public class WorldTurtle implements Turtle {
	private Set<Position> positions;
	private Position current;
	private Boolean drawing;
	private Method lastAction;

	public WorldTurtle(Position start) {
		super();
		lastAction = null;
		current = start;
		drawing = false;
		positions = new HashSet<Position>();
	}

	@Override
	public Turtle penDown() {
		try {
			lastAction = getClass().getDeclaredMethod("penDown", (Class<?>[]) null);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		
		positions.add(current);
		drawing = true;
		return this;
	}

	@Override
	public Turtle penUp() {
		try {
			lastAction = getClass().getDeclaredMethod("penUp", (Class<?>[]) null);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		
		drawing = false;
		return this;
	}

	@Override
	public Turtle right() {
		try {
			lastAction = getClass().getDeclaredMethod("right", (Class<?>[]) null);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		
		current = current.right();
		if (drawing) {
			positions.add(current);
		}

		return this;
	}

	@Override
	public Turtle left() {
		try {
			lastAction = getClass().getDeclaredMethod("left", (Class<?>[]) null);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		
		current = current.left();
		if (drawing) {
			positions.add(current);
		}

		return this;
	}

	@Override
	public Turtle up() {
		try {
			lastAction = getClass().getDeclaredMethod("up", (Class<?>[]) null);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		
		current = current.up();
		if (drawing) {
			positions.add(current);
		}

		return this;
	}

	@Override
	public Turtle down() {
		try {
			lastAction = getClass().getDeclaredMethod("down", (Class<?>[]) null);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		
		current = current.down();
		if (drawing) {
			positions.add(current);
		}

		return this;
	}

	@Override
	public Set<Position> getDraft() {
		return positions;
	}

	public Turtle times(Integer times) {
		times -= 1;
		for (int i = 0; i < times; i++) {
			try {
				lastAction.invoke(this, (Object[]) null);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		
		return this;
	}

}
