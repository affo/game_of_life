package model;

public class Position {
	private Integer row;
	private Integer column;

	public Position(Integer row, Integer column) {
		super();
		this.row = row;
		this.column = column;
	}

	public Integer getRow() {
		return row;
	}

	public void setRow(Integer row) {
		this.row = row;
	}

	public Integer getColumn() {
		return column;
	}

	public void setColumn(Integer column) {
		this.column = column;
	}
	
	public boolean equals(Position pos){
		if(this.row == pos.getRow() && this.column == pos.getColumn())
			return true;
		return false;
		
	}
}
