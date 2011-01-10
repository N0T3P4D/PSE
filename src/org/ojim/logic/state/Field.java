package org.ojim.logic.state;

public class Field {
	
	protected int position;
	protected String name;
	
	public Field(String name, int position) {
		this.name = name;
		this.position = position;
	}

	/**
	 * Returns the position of the field.
	 * @return the position of the field.
	 */
	public int getPosition() {
		return this.position;
	}

	public String getName() {
		return this.name;
	}
	
}
