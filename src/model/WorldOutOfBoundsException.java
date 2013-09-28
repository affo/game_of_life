package model;

public class WorldOutOfBoundsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3425925106332691855L;

	@Override
	public String getMessage() {
		return "Out of bounds!";
	}

}
