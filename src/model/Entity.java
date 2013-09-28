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
		Entity e = new Entity(false, p);
		
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
	
	public boolean equals(Entity entity){
		if(this.getPosition().getRow() == entity.getPosition().getRow() &&
				this.getPosition().getColumn() == entity.getPosition().getColumn())
			return true;
		return false;
		
	}
	
}
