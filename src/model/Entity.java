package model;

public class Entity implements EntityInterface, Cloneable {
	private Boolean alive;
	private Position position;

	public Entity() {
		super();
		this.alive = false;
	}

	public Entity(Boolean alive, Position position) {
		super();
		this.alive = alive;
		this.position = position;
	}

	public Entity clone() {
		Position p = this.getPosition();
		Entity e = new Entity(this.isAlive(), p);

		return e;
	}

	public Boolean isAlive() {
		return alive;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((position == null) ? 0 : position.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Entity other = (Entity) obj;
		if (position == null) {
			if (other.position != null)
				return false;
		} else if (!position.equals(other.position))
			return false;

		if (position.equals(other.position)) {
			return true;
		}
		return false;
	}

	@Override
	public Entity die() {
		this.alive = false;
		return this;
	}

	@Override
	public Entity survive() {
		return this;
	}

	@Override
	public Entity rise() {
		this.alive = true;
		return this;
	}

	@Override
	public String toString() {
		String status = "";

		if (alive) {
			status = "V";
		} else {
			status = "X";
		}

		return status + "(" + getPosition().getRow() + ", "
				+ getPosition().getColumn() + ")";
	}

}
